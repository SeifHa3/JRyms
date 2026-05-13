package model.command;

import model.device.SmartAC;

public class ACSetModeCommand implements Command {

    private final SmartAC device;
    private SmartAC.ACMode prevMode;
    private final SmartAC.ACMode newMode;

    public ACSetModeCommand(SmartAC device, SmartAC.ACMode newMode) {
        this.device = device;
        this.newMode = newMode;
    }

    @Override
    public void execute() {
        prevMode = device.getMode();
        device.setMode(newMode);
    }

    @Override
    public void undo() {
        device.setMode(prevMode);
    }
}