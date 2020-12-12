package lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import lab4.asists.GetMessage;
import lab4.asists.PutMessage;

public class ExecutorActor extends AbstractActor {

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(PutMessage.class, r ->
                        storage.put(r.getPackID(), r.getResult()))
                .match(GetMessage.class, r ->
                        sender().tell(new PutMessage(r.getPackID(), storage.get(r.getPackID())), self()))
                .build();
    }
}
