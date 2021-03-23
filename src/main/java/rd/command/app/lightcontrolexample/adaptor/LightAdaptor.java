package rd.command.app.lightcontrolexample.adaptor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import rd.command.app.lightcontrolexample.domain.LightState;
import rd.command.framework.DeviceAdaptor;
import rd.command.framework.domain.Command;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class LightAdaptor implements DeviceAdaptor<Object, LightState> {
    @Override
    public Future<LightState> action(Command<Object> command) {
        var result = switch (command.getCommandActionName()) {
            default -> LightState.OFF;
        };

        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return LightState.ON;
        });
    }

    public WebClient buildWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080/remote/device")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .build();

    }
}
