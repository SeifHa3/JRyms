package model.device;
import model.command.Command;
import model.hub.DeviceEvent;
import model.room.Room;
import observer.DeviceObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class SmartDevice {

    protected final String id;
    protected String name;
    protected Room room;
    protected DeviceStatus status;
    protected List<DeviceObserver> observers;

    protected SmartDevice (String id, String name ){
        this.id = id;
        this.name = name;
        this.status = DeviceStatus.OFFLINE;
        observers = new ArrayList<>();
    }
    public abstract void executeCommand( Command c); //abstracted
    public abstract String getStatusSummary(); //abstracted
    public void addObserver(DeviceObserver o){
        observers.add(o);
    }
    public void removeObserver (DeviceObserver o){
        observers.remove(o);
    }

    protected void notifyObservers(DeviceEvent event){//iterates along the list of observers and cals onDeviceUpdate on each one
        for (DeviceObserver observer: observers){
            observer.onDeviceUpdate(event);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Room getRoom(){
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public DeviceStatus getStatus() {
        return status;
    }
    public void setStatus(DeviceStatus status){
        this.status = status;
    }
}
