package lab4.actors;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.DeciderBuilder;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import lab4.assists.PackageData;
import lab4.assists.TestData;
import lab4.messages.ExecMessage;
import lab4.messages.GetMessage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static akka.actor.SupervisorStrategy.restart;

public class RouterActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), self());
    private static final int MAX_RETRIES = 10;
    private static final Duration DURATION = Duration.ofMinutes(1);

    private Router router;
    private ActorRef storage;
    private static SupervisorStrategy strategy =
            new OneForOneStrategy(MAX_RETRIES, DURATION,
                    DeciderBuilder.matchAny(o -> restart()).build());


    public RouterActor() {
        storage = getContext().actorOf(Props.create(StorageActor.class), "storage");
        getContext().watch(storage);

        List<Routee> routees = new ArrayList<Routee>();
        for (int i = 0; i < 5; i++) {
            ActorRef r = getContext().actorOf(Props.create(ExecutorActor.class));
            getContext().watch(r);
            routees.add(new ActorRefRoutee(r));
        }
        router = new Router(new RoundRobinRoutingLogic(), routees);
    }

    private void executeTests(PackageData r) {
        for (TestData t : r.getTests()) {
            router.route(new ExecMessage(r.getPackageID(), r.getFunctionName(),
                    r.getJsScript(), t.getTestName(), t.getExpectedResult(),
                    t.getParams()), storage);
        }
    }

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(PackageData.class, this::executeTests)
                .match(GetMessage.class, r -> storage.tell(r, sender()))
                .matchAny(o -> log.info("recieved unknown message"))
                .build();
    }
}
