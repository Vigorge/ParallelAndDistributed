package lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import junit.framework.TestResult;

import java.security.MessageDigest;

public class StorageActor extends AbstractActor {
    private Map<String, String>

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestResult.class, r -> {

                })
                .build();
    }
}
