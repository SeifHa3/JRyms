package decorator;

import model.command.Command;
import observer.DeviceObserver;
import model.device.DeviceStatus;
import model.device.SmartDevice;
import model.room.Room;

public abstract class DeviceDecorator extends SmartDevice {

    protected final SmartDevice wrapped;

    public DeviceDecorator(SmartDevice wrapped) {
        super(wrapped.getId(), wrapped.getName());
        this.wrapped = wrapped;
    }

    @Override
    public void executeCommand(Command c) {
        wrapped.executeCommand(c);
    }

    @Override
    public String getStatusSummary() {
        return wrapped.getStatusSummary();
    }

    @Override
    public Room getRoom()                        { return wrapped.getRoom(); }
    @Override
    public void setRoom(Room room)               { wrapped.setRoom(room); }
    @Override
    public DeviceStatus getStatus()              { return wrapped.getStatus(); }
    @Override
    public void setStatus(DeviceStatus status)   { wrapped.setStatus(status); }
    @Override
    public String getName()                      { return wrapped.getName(); }
    @Override
    public void setName(String name)             { wrapped.setName(name); }
    @Override
    public void addObserver(DeviceObserver o)    { wrapped.addObserver(o); }
    @Override
    public void removeObserver(DeviceObserver o) { wrapped.removeObserver(o); }
}