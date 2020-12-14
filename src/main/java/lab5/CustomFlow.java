package lab5;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import akka.util.Timeout;
import scala.concurrent.Future;

import java.time.Duration;

import static akka.http.javadsl.server.Directives.*;

public class CustomFlow<HttpRequest, HttpResponse, NotUsed> extends Flow {
    private final ActorRef router;
    private final static Timeout TIMEOUT = Timeout.create(Duration.ofSeconds(5));

    public CustomFlow(ActorRef router) {
        this.router = router;
    }

    public Flow<HttpRequest, HttpResponse, NotUsed> createFlow(ActorMaterializer materializer, ) {
        return
    }
}
