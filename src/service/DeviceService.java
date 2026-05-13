package service;

import model.device.DeviceStatus;
import model.device.SmartDevice;
import model.hub.DeviceEvent;
import model.hub.DeviceRegistry;
import util.AppConstants;
import util.EventBus;
import java.util.List;
import java.util.stream.Collectors;

public class DeviceService {

    private final DeviceRegistry deviceRegistry;

    public DeviceService() {
        deviceRegistry = DeviceRegistry.getInstance();
    }

    public List<SmartDevice> getAllDevices() {
        return deviceRegistry.getAllDevices();
    }

    public SmartDevice getDevice(String id) {
        return deviceRegistry.getDevice(id);
    }

    public List<SmartDevice> getDevicesByRoom(String roomId) {
        return deviceRegistry.getAllDevices().stream()
                .filter(d -> d.getRoom() != null && d.getRoom().getId().equals(roomId))
                .collect(Collectors.toList());
    }

    public void updateDeviceStatus(String id, DeviceStatus status) {
        SmartDevice device = deviceRegistry.getDevice(id);
        if (device != null) {
            device.setStatus(status);
            EventBus.getInstance().publish(
                    new DeviceEvent(id, AppConstants.EventType.DEVICE_STATE_CHANGED, status)
            );
        }
    }
}