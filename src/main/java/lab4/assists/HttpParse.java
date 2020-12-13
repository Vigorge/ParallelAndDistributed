package lab4.assists;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import lab4.messages.GetMessage;

import java.util.concurrent.Future;

import static akka.http.javadsl.server.Directives.*;

public class HttpParse {
    private final ActorRef router;
    private final ActorSystem system;
    private 

    public HttpParse(ActorSystem system, ActorRef router) {
        this.system = system;
        this.router = router;
    }

    public Route createRoute() {
        return route(
                get(() -> parameter("packageID", (pID) -> {
                    Future<Object> future = Patterns.ask(router, new GetMessage(pID), )
                })),
                post(() -> entity(Jackson.unmarshaller(PackageData.class), msg -> {
                    router.tell(msg, ActorRef.noSender());
                    return complete("Tests executed and stored");
                }))
        );
    }
}
