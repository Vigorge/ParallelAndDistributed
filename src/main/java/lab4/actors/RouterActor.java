package lab4.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.Router;

public class RouterActor extends AbstractActor {
    private Router router;
    private ActorRef storage = getContext().actorOf(Props.create(StorageActor.class));

    public Receive createReceive() {
        
    }
}
