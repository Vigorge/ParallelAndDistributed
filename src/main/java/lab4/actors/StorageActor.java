package lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import junit.framework.TestResult;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class StorageActor extends AbstractActor {
    private Map<String, String> storage = new HashMap<>();

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestResult.class, r -> {
                    storage.put(r.getPackID())
                })
                .build();
    }
}
