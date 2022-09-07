package rd.device.example.remote.device.lifx.provider.domain;

public class LightState {

    private final String label;
    private final Integer lightPower;

    public LightState(String label, Integer lightPower) {
        this.label = label;
        this.lightPower = lightPower;
    }

    public String getLabel() {
        return label;
    }

    public Integer getLightPower() {
        return lightPower;
    }
}
