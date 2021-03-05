package rd.command.devices.api.lightcontroller.adaptor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import rd.command.devices.api.lightcontroller.domain.LightState;
import org.springframework.stereotype.Service;
import rd.command.framework.DeviceAdaptor;
import rd.command.framework.domain.Command;

import java.util.concurrent.Callable;

@Service
public class LightAdaptor implements DeviceAdaptor<Object, LightState> {
    @Override
    public Callable<LightState> action(Command<Object> command) {
        return () -> {
            // Perform some computation
            Thread.sleep(1000);
            return LightState.ON;
        };
    }

    public WebClient buildWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080/remote/device")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .build();

    }
}
