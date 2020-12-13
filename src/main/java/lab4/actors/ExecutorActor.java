package lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import lab4.assists.TestData;
import lab4.messages.ExecMessage;
import lab4.messages.PutMessage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ExecutorActor extends AbstractActor {

    private String execute(TestData test) {
        ScriptEngine e = new ScriptEngineManager().getEngineByName("nashorn");
        
    }

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(ExecMessage.class, r ->
                        sender().tell(new PutMessage(), getContext().getParent()))
                .build();
    }
}
