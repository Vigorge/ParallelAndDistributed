package lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import lab4.asists.TestData;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class StorageActor extends AbstractActor {
    private Map<String, String> storage = new HashMap<>();

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestData.class, r -> {
                    storage.put(r.getPackID(), r.getResult());

                })
                .build();
    }
}
