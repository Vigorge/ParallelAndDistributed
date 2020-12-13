package lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import lab4.assists.TestData;
import lab4.messages.ExecMessage;
import lab4.messages.PutMessage;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ExecutorActor extends AbstractActor {

    private String execute(ExecMessage r) {
        String result;
        try {
            ScriptEngine e = new ScriptEngineManager().getEngineByName("nashorn");
            e.eval(r.getJsScript());
            Invocable in = (Invocable) e;
            result = in.invokeFunction(r.getFuncName(), r.getParams()).toString();
        } catch (Exception e) {
            return "ERROR " + e.toString();
        }

        return result;
    }

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(ExecMessage.class, r ->
                        sender().tell(new PutMessage(r.getPackID(), execute(r)), self()))
                .build();
    }
}
