package rd.device.example.remote.device.lifx.provider.domain;

public class Light {
    private String lightId;
    private LightState lightState;

    public Light(String lightId) {
        this.lightId = lightId;
        this.lightState = LightState.OFF;
    }

    public Light(String lightId, LightState lightState) {
        this.lightId = lightId;
        this.lightState = lightState;
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
