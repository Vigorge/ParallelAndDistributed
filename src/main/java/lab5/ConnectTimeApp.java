package lab5;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.Query;
import akka.japi.Pair;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.asynchttpclient.AsyncHttpClient;
import static org.asynchttpclient.Dsl.asyncHttpClient;

public class ConnectTimeApp {
    private static final int PORT = 8088;
    private static final String HOST = "localhost";
    private static final String SYS_NAME = "webtimet";
    private static final String URL = "connect";
    private static final String COUNT = "repeat";
    private final static Duration TIMEOUT = Duration.ofSeconds(5);
    private static final Object LOG_SOURCE = System.out;
    private static LoggingAdapter l;

    private static Flow<HttpRequest, HttpResponse, NotUsed> createFlow(ActorMaterializer materializer, ActorRef casher) {
        return Flow.of(HttpRequest.class)
                .map((r) -> {
                    Query query = r.getUri().query();
                    String url = query.getOrElse(URL, HOST);
                    long count = Long.parseLong(query.getOrElse(COUNT, "0"));
                    return new Pair<>(url, count);
                        })
                .mapAsync(5, (Pair<String, Integer> p) ->
                        Patterns.ask(casher, p.first(), TIMEOUT).thenCompose((Object t) -> {
                            if ((float) t >= 0)
                                return CompletableFuture.completedFuture(new Pair<>(p.first(), (float) t));
                            Flow<Pair<String, Integer>, Float, NotUsed> f =
                                    Flow.<Pair<String, Integer>>create()
                                    .mapConcat(pr -> new ArrayList<>(Collections.nCopies(pr.second(), pr.first())))
                                    .mapAsync(p.second(), (String url) -> {
                                        AsyncHttpClient client = asyncHttpClient()
                                    })
                                }))
                .map();
    }

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create(SYS_NAME);
        ActorRef casherActor = system.actorOf(Props.create(CasherActor.class), "cash");
        l = Logging.getLogger(system, LOG_SOURCE);
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = createFlow(materializer, casherActor);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost(HOST, PORT),
                materializer
        );
        l.info("Server online at http://{}:{}/\n", HOST, PORT);
        System.in.read();
        binding.thenCompose(ServerBinding::unbind).thenAccept(unbound -> system.terminate());
    }

}
