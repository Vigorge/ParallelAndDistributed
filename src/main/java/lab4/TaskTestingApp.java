package lab4;

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
import lab4.actors.RouterActor;
import lab4.assists.HttpParse;

import java.util.concurrent.CompletionStage;

public class TaskTestingApp {
    private static final int PORT = 8088;
    private static final String HOST = "localhost";
    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("webtest");
        ActorRef routerActor = system.actorOf(Props.create(RouterActor.class), "router");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        HttpParse instance = new HttpParse(routerActor);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = instance.createRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost(HOST, PORT)
        )
        System.out.printf("Server online at https://%s:%d/\nPress ENTER to stop\n", HOST, PORT);
        System.in.read();
        //binding.thenCompose(ServerBinding::unbind).thenAccept(unbound -> system.terminate());
    }
}
