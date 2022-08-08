package rd.device.example.remote.device.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rd.device.example.remote.device.provider.domain.Light;
import rd.device.example.remote.device.provider.domain.LightException;
import rd.device.example.remote.device.provider.domain.LightState;
import rd.device.example.remote.device.provider.domain.LightDeviceMap;
import rd.device.example.remote.device.provider.model.LightDeviceResult;

@RestController
@RequestMapping(value = "/mock/devices")
public class RemoteLightsController {

    @Autowired
    LightDeviceMap lights;

    @RequestMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpStatus handle() {
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/{devicename}/{requiredstate}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public LightDeviceResult handle(@PathVariable String devicename, @PathVariable String requiredstate) throws LightException {
        Light light = lights.getLightById(devicename);
        if (light != null) {
            if (LightState.ON.name().equals(requiredstate))
                light.turnOnLight();
            else if (LightState.OFF.name().equals(requiredstate))
                light.turnOffLight();
            return new LightDeviceResult(light.getLightState().name());
        } else
            throw new LightException("device name missing in path");
    }
}
