package rd.device.example.client.device.app.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class LightDeviceHandler {
    public Mono<ServerResponse> handleStatus() {
        return ServerResponse.ok().build();
    }

    public Mono<ServerResponse> handleCommand(ServerRequest req) {
        return null;
    }
}
