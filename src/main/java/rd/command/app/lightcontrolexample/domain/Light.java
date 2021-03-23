package rd.command.app.lightcontrolexample.domain;

import rd.command.app.lightcontrolexample.domain.command.LightException;

public class Light {

    private LightState lightState;
    private String lightId;

    public Light(String deviceId) {
        this.lightId = deviceId;
        lightState = LightState.OFF;
    }

    public boolean isOn() {
        return this.lightState.equals(LightState.ON);
    }

    public boolean isOff() {
        return this.lightState.equals(LightState.OFF);
    }

    public LightState pressSwitch() {
        synchronized (lightState) {
            try {
                Thread.sleep(1000);
                if (isOn()) lightState = LightState.OFF;
                else lightState = LightState.ON;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return lightState;
        }
    }

    public void turnOnLight() throws LightException {
        if (this.isOff() && this.pressSwitch().equals(LightState.ON)) ;
        else throw new LightException(LightErrorMessages.LIGHT_ALREADY_ON);
    }

    public void turnOffLight() throws LightException {
        if (this.isOn() && this.pressSwitch().equals(LightState.OFF)) ;
        else throw new LightException(LightErrorMessages.LIGHT_ALREADY_OFF);
    }

    public LightState getLightState() {
        return lightState;
    }

    public String getLightId() {
        return lightId;
    }
}
