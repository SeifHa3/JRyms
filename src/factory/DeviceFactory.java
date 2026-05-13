package factory;

import model.device.SmartAC;
import model.device.SmartDevice;

public class DeviceFactory {

    public static SmartDevice createDevice(DeviceType type, String id, String name) {
        return switch (type) {
            case AC -> new SmartAC(id, name);
            default -> throw new UnsupportedOperationException(
                    "Device type " + type + " coming in phase two entazerona!"
            );
        };
    }
}