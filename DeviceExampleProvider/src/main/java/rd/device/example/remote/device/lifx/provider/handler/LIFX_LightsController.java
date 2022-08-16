package rd.device.example.remote.device.lifx.provider.handler;

import com.stuntguy3000.lifxlansdk.helper.LightHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import rd.device.framework.api.exceptions.BadRequestException;
import rd.device.framework.api.exceptions.StatusResponse;
import rd.device.framework.api.handler.ErrorHandler;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CompletableFuture;

import static java.lang.String.format;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class LIFX_LightsController {
    private static final Logger logger = LoggerFactory.getLogger(LIFX_LightsController.class);

    private static final Scheduler lifx_pool;

    static {
        lifx_pool = Schedulers.newBoundedElastic(
                10, 1000, "LIFX_POOL", 1000, true);
    }

    public Mono<ServerResponse> handleDeviceOperation(ServerRequest request) {

        logger.info(Thread.currentThread() + "executing request " + request.uri().toString());
        if (!request.pathVariable("device").isEmpty())
            if (!request.pathVariable("operation").isEmpty()) {
                Mono.fromFuture(() -> blocking_call_extracted(request.pathVariable("device"), request.pathVariable("operation")))
                        .publishOn(lifx_pool).subscribe();
                return ServerResponse.accepted().build();
            }

        throw new BadRequestException("malformed operation");
    }

    private CompletableFuture<Boolean> blocking_call_extracted(final String device, final String operation) {
        return CompletableFuture.supplyAsync(() -> {
            logger.info(Thread.currentThread() + "executing " + device + " : " + operation);
            if (LightHelper.getLightByLabel(device) != null)
                LightHelper.getLightByLabel(device).setLightPower(operation.equals("ON") ? 32768 : 0, 100, false);
            return true;
        });
    }

    public Mono<ServerResponse> handleStatus(ServerRequest serverRequest) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .header("Cache-Control", format("max-age=%s", 0))
                .body(new StatusResponse(HttpStatus.OK, "success"), StatusResponse.class);
    }

}
