package lab5;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;
import java.util.Map;

public class CasherActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), self());

    private final Map<String, Float> cash = new HashMap<>();

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(String.class, r ->
                        sender().tell(cash.get(r), ActorRef.noSender()))
                .match(StoreMessage.class, r ->
                        cash.put(r.getUrl(), r.getAvgTime()))
                .matchAny(o -> log.info("recieved unknown message"))
                .build();
    }
}
