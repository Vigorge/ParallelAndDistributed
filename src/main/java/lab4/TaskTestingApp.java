package lab4;

import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.stream.ActorMaterializer;
import org.apache.http.HttpRequest;

public class TaskTestingApp {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("webtest");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        //добавить обработку запросов
        final Flow<HttpRequest, >

    }
}
