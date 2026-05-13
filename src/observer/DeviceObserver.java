package observer;

import model.hub.DeviceEvent;

public interface DeviceObserver {
    void onDeviceUpdate(DeviceEvent event);
}
