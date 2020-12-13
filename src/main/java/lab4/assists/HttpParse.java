package lab4.assists;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.Route;

import static akka.http.javadsl.server.Directives.*;

public class HttpParse {
    private final ActorRef router;
    private final ActorSystem system;

    public HttpParse(ActorSystem system, ActorRef router) {
        this.system = system;
        this.router = router;
    }

    public Route createRoute() {
        return route(
                get(() -> parameter("packageID", (pID) -> {

                })),
                post(() -> entity(Jackson.unmarshaller(PackageData.class), msg -> {

                }))
        );
    }
}
