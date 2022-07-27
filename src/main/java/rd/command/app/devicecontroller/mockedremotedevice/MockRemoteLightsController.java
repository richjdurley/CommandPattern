package rd.command.app.devicecontroller.mockedremotedevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rd.command.app.devicecontroller.domain.Light;
import rd.command.app.devicecontroller.domain.LightState;
import rd.command.app.devicecontroller.domain.command.LightException;
import rd.command.app.devicecontroller.model.LightDeviceResult;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/mock/provider")
public class MockRemoteLightsController {

    @Autowired
    Lights lights;

    @RequestMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Mono<HttpStatus> handle() {
        return Mono.just(HttpStatus.OK);
    }

    @RequestMapping(value = "/{devicename}/{requiredstate}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public LightDeviceResult handle(@PathVariable String devicename, @PathVariable String requiredstate) {
        try {
            Light light = lights.getLightById(devicename);
            if (light != null) {
                if (LightState.ON.name().equals(requiredstate))
                    light.turnOnLight();
                else if (LightState.OFF.name().equals(requiredstate))
                    light.turnOffLight();
                else
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid light state requested : " + requiredstate);
                return new LightDeviceResult(light.getLightState().name());
            }
        } catch (LightException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "device " + devicename + " not found");
    }
}
