package lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import lab4.messages.ExecMessage;
import lab4.messages.PutMessage;

public class ExecutorActor extends AbstractActor {

    

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(ExecMessage.class, r ->
                        sender().tell(new PutMessage(), getContext().getParent()))
                .build();
    }
}
