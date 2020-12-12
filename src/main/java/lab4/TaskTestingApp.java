package lab4;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

import java.util.concurrent.CompletionStage;

public class TaskTestingApp {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("webtest");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        //добавить обработку запросов
        //final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow =
        //final CompletionStage<ServerBinding> binding = http.bindAndHandle()
        
    }
}
