package lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class StorageActor extends AbstractActor {

    public Receive createRecieve() {
        return ReceiveBuilder.create()
    }
}
