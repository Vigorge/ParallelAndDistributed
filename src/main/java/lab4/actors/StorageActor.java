package lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import lab4.asists.GetMessage;
import lab4.asists.PutMessage;
import lab4.asists.ResultMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageActor extends AbstractActor {
    private Map<String, List<String>> storage = new HashMap<>();

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(PutMessage.class, r ->
                        storage.put(r.getPackID(), r.getResult()))
                .match(GetMessage.class, r ->
                        sender().tell(new ResultMessage(r.getPackID(), storage.get(r.getPackID())), self()))
                .build();
    }
}
