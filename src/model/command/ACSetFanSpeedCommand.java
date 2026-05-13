package model.command;

import model.device.SmartAC;

public class ACSetFanSpeedCommand implements Command {

    private final SmartAC device;
    private SmartAC.FanSpeed prevSpeed;
    private final SmartAC.FanSpeed newSpeed;

    public ACSetFanSpeedCommand(SmartAC device, SmartAC.FanSpeed newSpeed) {
        this.device = device;
        this.newSpeed = newSpeed;
    }

    @Override
    public void execute() {
        prevSpeed = device.getFanSpeed();
        device.setFanSpeed(newSpeed);
    }

    @Override
    public void undo() {
        device.setFanSpeed(prevSpeed);
    }
}