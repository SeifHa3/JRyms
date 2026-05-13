package model.device;

import model.command.Command;
import model.hub.DeviceEvent;
import util.AppConstants;

public class SmartAC extends SmartDevice {
    public enum ACMode {
        COOL,
        HEAT,
        Fan,
        AUTO
    }
    public enum FanSpeed {
        LOW,
        HIGH,
        MED
    }


    private boolean isOn;
    private double currentTemp;
    private double targetTemp;
    private ACMode mode;
    private FanSpeed fanSpeed;

    public SmartAC (String id,String name){
        super(id,name);
        this.currentTemp = AppConstants.DEFAULT_TEMP;
        this.targetTemp = AppConstants.DEFAULT_TEMP;
        this.mode = ACMode.AUTO;
        this.fanSpeed = FanSpeed.MED;
    }

    public void executeCommand(Command c){
        c.execute();
    }

    public void turnOn(){
        this.isOn = true;
        this. status=DeviceStatus.ONLINE;
                notifyObservers(new DeviceEvent(id,AppConstants.EventType.DEVICE_TOGGLE,true));
    }

    public void turnOff(){
        this.isOn = false;
        notifyObservers(new DeviceEvent(id,AppConstants.EventType.DEVICE_TOGGLE,false));
    }

    public FanSpeed getFanSpeed() {
        return fanSpeed;
    }

    public void setFanSpeed(FanSpeed fanSpeed) {
        this.fanSpeed = fanSpeed;
    }

    public ACMode getMode() {
        return mode;
    }

    public void setMode(ACMode mode) {
        this.mode = mode;
    }

    public double getTargetTemp() {
        return targetTemp;
    }

    public void setTargetTemp(double targetTemp) {
        this.targetTemp = targetTemp;
    }

    public double getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(double currentTemp) {
        this.currentTemp = currentTemp;
    }

    @Override
    public String getStatusSummary() {
        return "AC [" + name + "] is " + (isOn ? "ON" : "OFF")
                + " | Temp: " + targetTemp + "C"
                + " | Mode: " + mode
                + " | Fan: " + fanSpeed;
    }

}
