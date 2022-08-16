package rd.device.example.remote.device.lifx.provider.handler;

import com.stuntguy3000.lifxlansdk.helper.LightHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import rd.device.framework.api.handler.ErrorHandler;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static java.lang.String.format;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class LIFX_LightsController {

    public Mono<ServerResponse> handleCommand(ServerRequest request) {
        if (!request.pathVariable("device").isEmpty())
            Objects.requireNonNull(LightHelper.getLightByLabel(request.pathVariable("device"))).setLightPower(0, 1000, true);
        return formatResponse(Mono.justOrEmpty(true));
    }

    public Mono<ServerResponse> handleStatus(ServerRequest request) {
        return ServerResponse.status(200).build();
    }

    private Mono<ServerResponse> formatResponse(Mono<Boolean> result) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .header("Cache-Control", format("max-age=%s", 0))
                .body(result, Boolean.class);
    }

}
