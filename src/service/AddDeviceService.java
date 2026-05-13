package service;

import decorator.DeviceDecorator;
import decorator.LoggingDecorator;
import factory.DecoratorType;
import factory.DeviceFactory;
import factory.DeviceType;
import model.device.SmartDevice;
import model.hub.DeviceEvent;
import model.hub.DeviceRegistry;
import util.AppConstants;
import util.EventBus;

import java.util.List;

public class AddDeviceService {

    private final DeviceRegistry deviceRegistry;
    private final RoomService roomService;

    public AddDeviceService() {
        this.deviceRegistry = DeviceRegistry.getInstance();
        this.roomService = new RoomService();
    }

    public SmartDevice spawnDevice(DeviceType type, String name,
                                   String roomId, List<DecoratorType> decorators) {

        String id = type.name().toLowerCase() + "_" + System.currentTimeMillis();

        SmartDevice device = DeviceFactory.createDevice(type, id, name);

        // 3. wrap in decorators — LOGGING first, then ACCESS_CONTROL
        for (DecoratorType dt : decorators) {
            if (dt == DecoratorType.LOGGING) {
                device = new LoggingDecorator(device);
            }
            // ACCESS_CONTROL placeholder — no role system yet
        }


        deviceRegistry.register(device);

        roomService.assignDeviceToRoom(device.getId(), roomId);

        EventBus.getInstance().publish(
                new DeviceEvent(device.getId(), AppConstants.EventType.DEVICE_ADDED, device)
        );

        return device;
    }

    public void removeDevice(String id) {
        deviceRegistry.removeDevice(id);
        EventBus.getInstance().publish(
                new DeviceEvent(id, AppConstants.EventType.DEVICE_REMOVED, id)
        );
    }
}