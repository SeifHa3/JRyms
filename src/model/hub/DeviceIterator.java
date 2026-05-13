package model.hub;

import model.device.SmartDevice;
import util.Iterator;
import java.util.List;

public class DeviceIterator implements Iterator<SmartDevice> {

    private final List<SmartDevice> devices;
    private int index = 0;

    public DeviceIterator(List<SmartDevice> devices) {
        this.devices = devices;
    }

    @Override
    public boolean hasNext() {
        return index < devices.size();
    }

    @Override
    public SmartDevice next() {
        return devices.get(index++);
    }
}