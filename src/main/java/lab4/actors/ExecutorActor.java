package lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ExecutorActor extends AbstractActor {

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .matchAny(r -> {
                    ScriptEngine engine = new
                            ScriptEngineManager().getEngineByName("nashorn");
                    engine.eval(jscript);
                    Invocable invocable = (Invocable) engine;
                    return invocable.invokeFunction(functionName, params).toString();
                })
                .build();
    }
}
