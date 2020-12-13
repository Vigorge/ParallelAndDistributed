package lab4.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import lab4.assists.PackageData;
import lab4.assists.TestData;
import lab4.messages.ExecMessage;
import lab4.messages.GetMessage;
import lab4.messages.PutMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RouterActor extends AbstractActor {
    private Router router;
    private ActorRef storage;

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

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(PackageData.class, r -> {
                        for (TestData t : r.getTests()) {
                            router.route(new ExecMessage(r.getPackageID(), r.getFunctionName(), r.getJsScript(), t.getParams()), self());
                        }
                        }
                        )
                .match(PutMessage.class, r ->
                        storage.tell(r, sender()))
                .match(GetMessage.class, r ->
                        storage.tell(r, sender())
                        )
                .match(Terminated.class, t -> )
                .build();
    }
}
