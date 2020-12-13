package lab4.assists;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.server.Route;
import akka.http.javadsl.server.PathMatchers;
import static akka.http.javadsl.server.Directives.route;

public class HttpParse {
    private final ActorRef router;
    private final ActorSystem system;

    public HttpParse(ActorSystem system, ActorRef router) {
        this.system = system;
        this.router = router;
    }

    public Route createRoute() {
        return route(
                
        )
    }
}
