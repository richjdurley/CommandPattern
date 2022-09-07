package rd.device.example.remote.device.lifx.provider.handler;

import com.stuntguy3000.lifxlansdk.helper.LightHelper;
import com.stuntguy3000.lifxlansdk.object.product.Light;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rd.device.example.remote.device.lifx.provider.domain.LightState;
import rd.device.framework.api.exceptions.BadRequestException;
import rd.device.util.json.JSONUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/devices")
public class LIFX_LightsController {
    private static final Logger logger = LoggerFactory.getLogger(LIFX_LightsController.class);
    private static final Scheduler lifx_pool;
    protected static volatile ConcurrentHashMap<String, String> device_state = new ConcurrentHashMap<String, String>();

    static {
        lifx_pool = Schedulers.newBoundedElastic(
                10, 1000, "LIFX_POOL", 1000, true);
    }

    @PostMapping(value = "/{device}/{operation}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> handleDeviceOperation(@PathVariable String device, @PathVariable String operation) {
        logger.info(Thread.currentThread() + "executing request device:" + device + "& operation:" + operation);
        if (!device.isEmpty())
            if (!operation.isEmpty()) {
                Mono.fromFuture(() -> blocking_call_extracted(device, operation))
                        .publishOn(lifx_pool).subscribe();
                return Mono.just(ResponseEntity.accepted().build());
            }
        return Mono.just(ResponseEntity.notFound().eTag("device:" + device + "& operation:" + operation).build());
    }

    @GetMapping(value = "/events/{device}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamFlux(@PathVariable String device) {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence ->
                        Optional.ofNullable(LIFX_LightsController.device_state.remove(device)).orElse(""));
    }

    private CompletableFuture<Boolean> blocking_call_extracted(final String device, final String operation) {
        return CompletableFuture.supplyAsync(() -> {
            logger.info(Thread.currentThread() + "start executing " + device + " : " + operation);
            Light light = LightHelper.getLightByLabel(device);
            if (light != null) {
                light.setLightPower(operation.equals("ON") ? 32768 : 0, 0, false);
                device_state.put(device, JSONUtil.toJSONString(new LightState(light.getLabel(), light.getLightPower().getLevel())));
            } else {
                throw new BadRequestException("Malformed operation");
            }
            logger.info(Thread.currentThread() + "finished executing " + device + " : " + operation);
            return true;
        });
    }

}
