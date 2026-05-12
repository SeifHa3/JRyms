package model.hub;

import model.device.SmartDevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;

public class DeviceRegistry {
    private static volatile DeviceRegistry instance;
    private final Map<String, SmartDevice> devices = new HashMap<>();

    private DeviceRegistry() {
    }

    public static DeviceRegistry getInstance() {
        if (instance == null) {
            synchronized (DeviceRegistry.class) {
                if (instance == null) {
                    instance = new DeviceRegistry();
                }
            }
        }
    return instance;}


    public void register(SmartDevice device) {
        devices.put(device.getId(), device);
    }

    public SmartDevice getDevice(String id) {
        return devices.get(id);
    }

    public List<SmartDevice> getAllDevices() {
        return new ArrayList<>(devices.values());
    }

    public void removeDevice(String id) {
         devices.remove(id);
    }

    public boolean contains(String id){
        return devices.containsKey(id);
    }
}
