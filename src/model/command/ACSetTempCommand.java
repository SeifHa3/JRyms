package model.command;

import model.device.SmartAC;

public class ACSetTempCommand  implements Command {

    private final SmartAC device;
    private double prevTemp;
    private final double newTemp;

    public ACSetTempCommand(SmartAC device, double newTemp) {
        this.device = device;
        this.newTemp = newTemp;
    }

    @Override
    public void execute() {
        prevTemp = device.getTargetTemp();
        device.setTargetTemp(newTemp);
    }

    @Override
    public void undo() {
        device.setTargetTemp(prevTemp);
    }
}