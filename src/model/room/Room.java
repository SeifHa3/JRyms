package model.room;

import model.device.SmartDevice;
import java.util.ArrayList;
import java.util.List;

public class Room {

    private final String id;
    private String name;
    private String icon;
    private final List<SmartDevice> devices;

    public Room(String id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.devices = new ArrayList<>();
    }

    public void addDevice(SmartDevice device) {
        devices.add(device);
    }

    public void removeDevice(String id) {
        devices.removeIf(d -> d.getId().equals(id));
    }

    public List<SmartDevice> getDevices()  { return new ArrayList<>(devices); }
    public int getDeviceCount()            { return devices.size(); }
    public String getId()                  { return id; }
    public String getName()                { return name; }
    public String getIcon()                { return icon; }
    public void setName(String name)       { this.name = name; }
}