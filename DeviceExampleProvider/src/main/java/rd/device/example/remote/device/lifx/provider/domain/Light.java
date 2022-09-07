package rd.device.example.remote.device.lifx.provider.domain;

public class Light {
    private String lightId;
    private LightStateEnum lightState;

    public Light(String lightId) {
        this.lightId = lightId;
        this.lightState = LightStateEnum.OFF;
    }

    public Light(String lightId, LightStateEnum lightState) {
        this.lightId = lightId;
        this.lightState = lightState;
    }

    public boolean isOn() {
        return this.lightState.equals(LightStateEnum.ON);
    }

    public boolean isOff() {
        return this.lightState.equals(LightStateEnum.OFF);
    }

    public LightStateEnum pressSwitch() {
        synchronized (lightState) {
            try {
                Thread.sleep(1000);
                if (isOn()) lightState = LightStateEnum.OFF;
                else lightState = LightStateEnum.ON;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return lightState;
        }
    }

    public void turnOnLight() throws LightException {
        if (this.isOff() && this.pressSwitch().equals(LightStateEnum.ON)) ;
        else throw new LightException(LightErrorMessages.LIGHT_ALREADY_ON);
    }

    public void turnOffLight() throws LightException {
        if (this.isOn() && this.pressSwitch().equals(LightStateEnum.OFF)) ;
        else throw new LightException(LightErrorMessages.LIGHT_ALREADY_OFF);
    }

    public LightStateEnum getLightState() {
        return lightState;
    }

    public String getLightId() {
        return lightId;
    }
}
