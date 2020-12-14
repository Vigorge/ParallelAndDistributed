package lab4.actors;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import lab4.messages.GetMessage;
import lab4.messages.PutMessage;
import lab4.messages.ResultMessage;

import java.util.*;

public class StorageActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), self());

    private Map<String, ArrayList<String>> storage = new HashMap<>();

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(PutMessage.class, r -> {
                        ArrayList<String>  results = storage.get(r.getPackID());
                        if (results != null) {
                            results.add(r.getResult());
                        } else {
                            results = new ArrayList<>();
                            results.add(r.getResult());
                            storage.put(r.getPackID(), results);
                        }
                        })
                .match(GetMessage.class, r ->
                        sender().tell(new ResultMessage(r.getPackID(), storage.get(r.getPackID())), getContext().parent()))
                .matchAny(o -> log.info("recieved unknown message"))
                .build();
    }
}
