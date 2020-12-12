package lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class StorageActor extends AbstractActor {

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match()
                .build();
    }
}
