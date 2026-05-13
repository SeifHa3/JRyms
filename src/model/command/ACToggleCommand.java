package model.command;

import model.device.SmartAC;

public class ACToggleCommand implements Command {

    private final SmartAC device;
    private boolean prevState;

    public ACToggleCommand(SmartAC device) {
        this.device = device;
    }

    @Override
    public void execute() {
        prevState = device.isOn();
        if (prevState) device.turnOff();
        else device.turnOn();
    }

    @Override
    public void undo() {
        if (prevState) device.turnOn();
        else device.turnOff();
    }
}