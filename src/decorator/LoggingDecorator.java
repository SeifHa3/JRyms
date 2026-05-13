package decorator;

import model.command.Command;
import model.device.SmartDevice;
import java.time.LocalDateTime;

public class LoggingDecorator extends DeviceDecorator {

    public LoggingDecorator(SmartDevice wrapped) {
        super(wrapped);
    }

    @Override
    public void executeCommand(Command c) {
        System.out.println("[LOG] " + LocalDateTime.now()
                + " - " + wrapped.getName()
                + " - " + c.getClass().getSimpleName());
        super.executeCommand(c);
    }
}