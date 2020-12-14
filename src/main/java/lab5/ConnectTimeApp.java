package lab5;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.util.concurrent.CompletionStage;

public class ConnectTimeApp {
    private static final int PORT = 8088;
    private static final String HOST = "localhost";

    private static Flow<HttpRequest, HttpResponse, NotUsed> createFlow(ActorMaterializer materializer, ActorRef casher) {
        return
    }

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("webtest");
        ActorRef casherActor = system.actorOf(Props.create(CasherActor.class), "cash");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = createFlow(materializer, casherActor);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost(HOST, PORT),
                materializer
        );
        System.out.printf("Server online at https://%s:%d/\n", HOST, PORT);
        System.in.read();
        binding.thenCompose(ServerBinding::unbind).thenAccept(unbound -> system.terminate());
    }
    
}
