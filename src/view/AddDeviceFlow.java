package view;

import factory.DecoratorType;
import factory.DeviceType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import model.room.Room;
import service.AddDeviceService;
import service.RoomService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AddDeviceFlow {

    private final AddDeviceService addDeviceService;
    private final RoomService roomService;

    public AddDeviceFlow(AddDeviceService addDeviceService, RoomService roomService) {
        this.addDeviceService = addDeviceService;
        this.roomService = roomService;
    }

    public void show() {
        // Step 1 — pick device type
        ChoiceDialog<DeviceType> typeDialog = new ChoiceDialog<>(
                DeviceType.AC, DeviceType.values()
        );
        typeDialog.setTitle("Add Device");
        typeDialog.setHeaderText("Select device type");
        typeDialog.setContentText("Type:");

        Optional<DeviceType> typeResult = typeDialog.showAndWait();
        typeResult.ifPresent(type -> {

            // Step 2 — enter device name
            TextInputDialog nameDialog = new TextInputDialog("My Device");
            nameDialog.setTitle("Add Device");
            nameDialog.setHeaderText("Enter a name for your device");
            nameDialog.setContentText("Name:");

            Optional<String> nameResult = nameDialog.showAndWait();
            nameResult.ifPresent(name -> {

                // Step 3 — pick room
                List<Room> rooms = roomService.getAllRooms();
                ChoiceDialog<Room> roomDialog = new ChoiceDialog<>(rooms.get(0), rooms);
                roomDialog.setTitle("Add Device");
                roomDialog.setHeaderText("Assign to a room");
                roomDialog.setContentText("Room:");

                Optional<Room> roomResult = roomDialog.showAndWait();
                roomResult.ifPresent(room -> {
                    addDeviceService.spawnDevice(
                            type,
                            name,
                            room.getId(),
                            List.of(DecoratorType.LOGGING)
                    );
                });
            });
        });
    }
}