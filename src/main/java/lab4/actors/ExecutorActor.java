package lab4.actors;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import lab4.messages.ExecMessage;
import lab4.messages.PutMessage;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ExecutorActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), self());

    private String execute(ExecMessage r) {
        String result;
        try {
            ScriptEngine e = new ScriptEngineManager().getEngineByName("nashorn");
            e.eval(r.getJsScript());
            Invocable in = (Invocable) e;
            result = in.invokeFunction(r.getFuncName(), r.getParams().toArray()).toString();
        } catch (Exception e) {
            return String.format("%s: ERROR, %s", r.getTestName(), e.toString());
        }
        if (result.equals(r.getExpRes()))
            return String.format("%s: OK, result: %s", r.getTestName(), result);
        else
            return String.format("%s: FAIL, expected: %s, got: %s", r.getTestName(), r.getExpRes(), result);
    }

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(ExecMessage.class, r ->
                        sender().tell(new PutMessage(r.getPackID(), execute(r)), self()))
                .matchAny(o -> log.info("recieved unknown message"))
                .build();
    }
}
