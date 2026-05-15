package view;

import decorator.DeviceDecorator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import model.device.SmartAC;
import model.device.SmartDevice;
import model.room.Room;
import util.UIStyles;

public class RoomGroupView {

    private final Room room;

    public RoomGroupView(Room room) {
        this.room = room;
    }

    public VBox buildSection() {
        VBox section = new VBox(10);
        section.setPadding(new Insets(0, 0, 8, 0));

        // Room header
        HBox roomHeader = new HBox(8);
        roomHeader.setAlignment(Pos.CENTER_LEFT);
        roomHeader.setPadding(new Insets(0, 0, 4, 0));

        Label roomIcon = new Label(room.getIcon());
        roomIcon.setStyle("-fx-font-size: 15px;");

        Label roomName = new Label(room.getName().toUpperCase());
        roomName.setStyle(UIStyles.LABEL_SECTION_HEADER);

        Region line = new Region();
        line.setPrefHeight(1);
        line.setStyle("-fx-background-color: #e8eaed;");
        HBox.setHgrow(line, Priority.ALWAYS);

        Label countLbl = new Label(
                room.getDeviceCount() + " device" + (room.getDeviceCount() != 1 ? "s" : "")
        );
        countLbl.setStyle("-fx-font-size: 10px; -fx-text-fill: #b2bec3;");

        roomHeader.getChildren().addAll(roomIcon, roomName, line, countLbl);
        section.getChildren().add(roomHeader);

        for (SmartDevice device : room.getDevices()) {
            section.getChildren().add(buildDeviceCard(device));
        }
        return section;
    }

    private HBox buildDeviceCard(SmartDevice device) {
        HBox card = new HBox(14);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(14, 18, 14, 18));
        card.setStyle(UIStyles.CARD);

        // Icon bubble
        StackPane iconBg = new StackPane();
        iconBg.setPrefSize(44, 44);
        iconBg.setMinSize(44, 44);
        iconBg.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #ede9fe, #ddd6fe);" +
                        "-fx-background-radius: 10;"
        );
        Label iconLbl = new Label("❄️");
        iconLbl.setStyle("-fx-font-size: 20px;");
        iconBg.getChildren().add(iconLbl);

        // Info
        VBox info = new VBox(4);
        HBox.setHgrow(info, Priority.ALWAYS);

        Label nameLbl = new Label(device.getName());
        nameLbl.setStyle(UIStyles.LABEL_DEVICE_NAME);

        HBox statusRow = new HBox(5);
        statusRow.setAlignment(Pos.CENTER_LEFT);
        Region dot = new Region();
        dot.setStyle(UIStyles.statusDotStyle(device.getStatus().toString()));
        Label statusLbl = new Label(device.getStatus().toString());
        statusLbl.setStyle("-fx-font-size: 11px; -fx-text-fill: #636e72;");
        statusRow.getChildren().addAll(dot, statusLbl);

        info.getChildren().addAll(nameLbl, statusRow);

        // Control button
        Button controlBtn = new Button("Control →");
        controlBtn.setStyle(UIStyles.BTN_GHOST);
        controlBtn.setOnAction(e -> {
            SmartAC ac = extractAC(device);
            if (ac != null) ViewManager.getInstance().showACControl(ac);
        });

        card.setOnMouseEntered(e -> card.setStyle(UIStyles.CARD_HOVER));
        card.setOnMouseExited(e ->  card.setStyle(UIStyles.CARD));
        card.getChildren().addAll(iconBg, info, controlBtn);
        return card;
    }

    private SmartAC extractAC(SmartDevice device) {
        if (device instanceof SmartAC) return (SmartAC) device;
        if (device instanceof DeviceDecorator)
            return extractAC(((DeviceDecorator) device).getWrapped());
        return null;
    }
}