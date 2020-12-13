package lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class ExecutorActor extends AbstractActor {

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match()
                .build();
    }
}
