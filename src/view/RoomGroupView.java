package view;

import decorator.DeviceDecorator;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.device.SmartAC;
import model.device.SmartDevice;
import model.room.Room;

public class RoomGroupView {

    private final Room room;

    public RoomGroupView(Room room) {
        this.room = room;
    }

    public VBox buildSection() {
        Label roomLabel = new Label(room.getIcon() + "  " + room.getName());
        roomLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        VBox section = new VBox(6);
        section.setPadding(new Insets(8, 0, 8, 0));
        section.getChildren().add(roomLabel);

        for (SmartDevice device : room.getDevices()) {
            Label nameLabel   = new Label(device.getName());
            Label statusLabel = new Label(device.getStatus().toString());
            Button controlBtn = new Button("Control");

            controlBtn.setOnAction(e -> {
                SmartAC ac = extractAC(device);
                if (ac != null) ViewManager.getInstance().showACControl(ac);
            });

            HBox row = new HBox(15, nameLabel, statusLabel, controlBtn);
            row.setPadding(new Insets(2, 0, 2, 16));
            section.getChildren().add(row);
        }

        return section;
    }

    private SmartAC extractAC(SmartDevice device) {
        if (device instanceof SmartAC) return (SmartAC) device;
        if (device instanceof DeviceDecorator) {
            return extractAC(((DeviceDecorator) device).getWrapped());
        }
        return null;
    }
}