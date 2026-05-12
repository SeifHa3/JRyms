package model.device;

import java.util.List;
import java.util.Observer;

public abstract class SmartDevice {

    protected final String  Id;
    protected String name;
    protected String location;
    
    protected Room room;
    protected DeviceStatus status;
    protected List<DeviceObserver> observers;

    public SmartDevice (String id, String name ){
        this.Id = id;
        this.name = name;
        this.status = DeviceStatus.ONLINE;
        observers = new List<DeviceObserver>();
    }
    public void executeCommand( Command c){}//abstracted
    public String getStatusSummery(){
        return "";
    }//abstracted
    public void addObserver(DeviceObserver o){
        observers.add(o);
    }
    public void removeObserver (DeviceObserver o){
        observers.remove(o);
    }

    protected void notifyObservers(DeviceEvent){//iterates along the list of observers and cals onDeviceUpdate on each one

    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Room getRoom(){
        return Room;
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
