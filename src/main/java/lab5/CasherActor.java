package lab5;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class CasherActor extends AbstractActor {


    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match();
    }
}
