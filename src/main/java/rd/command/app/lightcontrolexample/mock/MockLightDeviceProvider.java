package rd.command.app.lightcontrolexample.mock;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rd.command.app.lightcontrolexample.domain.Light;
import rd.command.app.lightcontrolexample.domain.LightState;
import rd.command.app.lightcontrolexample.domain.command.LightException;
import rd.command.app.lightcontrolexample.model.LightDeviceResult;
import reactor.core.publisher.Mono;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping(value = "/mock/provider")
public class MockLightDeviceProvider {

    private ConcurrentHashMap<String, Light> lightStates;

    public MockLightDeviceProvider() {
        lightStates = new ConcurrentHashMap<>();
        lightStates.put("a", new Light("a"));
        lightStates.put("b", new Light("b"));
    }

    @RequestMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Mono<HttpStatus> handle() {
        return Mono.just(HttpStatus.OK);
    }

    @RequestMapping(value = "/{devicename}/{requiredstate}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public LightDeviceResult handle(@PathVariable String devicename, @PathVariable String requiredstate) {
        try {
            if (lightStates.containsKey(devicename)) {
                if (LightState.ON.name().equals(requiredstate))
                    lightStates.get(devicename).turnOnLight();
                else if (LightState.OFF.name().equals(requiredstate))
                    lightStates.get(devicename).turnOffLight();
                else
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid light state requested : " + requiredstate);
                return new LightDeviceResult(lightStates.get(devicename).getLightState().name());
            }
        } catch (LightException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "device " + devicename + " not found");
    }
}
