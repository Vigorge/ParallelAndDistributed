package lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import lab4.assists.TestData;
import lab4.messages.ExecMessage;
import lab4.messages.PutMessage;

import javax.script.*;
import java.io.Reader;

public class ExecutorActor extends AbstractActor {

    private String execute(TestData test) {
        ScriptEngine e = new ScriptEngine() {
            @Override
            public Object eval(String script, ScriptContext context) throws ScriptException {
                return null;
            }

            @Override
            public Object eval(Reader reader, ScriptContext context) throws ScriptException {
                return null;
            }

            @Override
            public Object eval(String script) throws ScriptException {
                return null;
            }

            @Override
            public Object eval(Reader reader) throws ScriptException {
                return null;
            }

            @Override
            public Object eval(String script, Bindings n) throws ScriptException {
                return null;
            }

            @Override
            public Object eval(Reader reader, Bindings n) throws ScriptException {
                return null;
            }

            @Override
            public void put(String key, Object value) {

            }

            @Override
            public Object get(String key) {
                return null;
            }

            @Override
            public Bindings getBindings(int scope) {
                return null;
            }

            @Override
            public void setBindings(Bindings bindings, int scope) {

            }

            @Override
            public Bindings createBindings() {
                return null;
            }

            @Override
            public ScriptContext getContext() {
                return null;
            }

            @Override
            public void setContext(ScriptContext context) {

            }

            @Override
            public ScriptEngineFactory getFactory() {
                return null;
            }
        }
    }

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(ExecMessage.class, r ->
                        sender().tell(new PutMessage(), getContext().getParent()))
                .build();
    }
}
