package lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import junit.framework.TestResult;

import java.security.MessageDigest;

public class StorageActor extends AbstractActor {

    private static 
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestResult.class)
                .build();
    }
}
