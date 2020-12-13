package lab4.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import lab4.messages.ExecMessage;
import lab4.messages.GetMessage;
import lab4.messages.PutMessage;

import java.util.ArrayList;
import java.util.List;

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
                .match(ExecMessage.class, r ->
                        router.route(r, self()))
                .match(PutMessage.class, r ->
                        storage.tell(r, self()))
                .match(GetMessage.class, r ->
                        )
                .build();
    }
}
