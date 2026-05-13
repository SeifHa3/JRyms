package controller;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.device.SmartAC;
import model.device.SmartDevice;
import model.hub.DeviceEvent;
import model.room.Room;
import observer.DeviceObserver;
import service.CommandService;
import service.DeviceService;
import util.AppConstants;
import util.EventBus;
import view.DashboardView;
import view.RoomGroupView;
import view.ViewManager;

import java.util.List;
import java.util.stream.Collectors;

public class DashboardController implements DeviceObserver {

    private final DeviceService deviceService;
    private final CommandService commandService;
    private DashboardView view;

    public DashboardController(DeviceService deviceService, CommandService commandService) {
        this.deviceService = deviceService;
        this.commandService = commandService;
    }

    public void init(DashboardView view) {
        this.view = view;

        view.getAddDeviceBtn().setOnAction(e -> onAddDeviceClick());
        view.getUndoBtn().setOnAction(e -> onUndoClick());
        view.getRedoBtn().setOnAction(e -> onRedoClick());

        EventBus.getInstance().subscribe(
                AppConstants.EventType.DEVICE_ADDED, e -> Platform.runLater(this::refreshDashboard)
        );
        EventBus.getInstance().subscribe(
                AppConstants.EventType.DEVICE_STATE_CHANGED, e -> Platform.runLater(this::refreshDashboard)
        );
        refreshDashboard();
    }

    public void refreshDashboard() {
        VBox rows = view.getDeviceRowsBox();
        rows.getChildren().clear();

        // rooms with devices
        List<Room> rooms = new service.RoomService().getAllRooms();
        for (Room room : rooms) {
            if (room.getDeviceCount() > 0) {
                rows.getChildren().add(new RoomGroupView(room).buildSection());
            }
        }

        // unassigned devices
        List<SmartDevice> unassigned = deviceService.getAllDevices().stream()
                .filter(d -> d.getRoom() == null)
                .collect(Collectors.toList());

        if (!unassigned.isEmpty()) {
            Label unassignedLabel = new Label("📦  Unassigned");
            unassignedLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            VBox section = new VBox(6);
            section.setPadding(new Insets(8, 0, 8, 0));
            section.getChildren().add(unassignedLabel);

            for (SmartDevice d : unassigned) {
                Label nameLabel   = new Label(d.getName());
                Label statusLabel = new Label(d.getStatus().toString());
                Button controlBtn = new Button("Control");
                controlBtn.setOnAction(e -> {
                    if (d instanceof SmartAC)
                        ViewManager.getInstance().showACControl((SmartAC) d);
                });
                HBox row = new HBox(15, nameLabel, statusLabel, controlBtn);
                row.setPadding(new Insets(2, 0, 2, 16));
                section.getChildren().add(row);
            }
            rows.getChildren().add(section);
        }
    }

    public void onUndoClick() { commandService.undo(); }
    public void onRedoClick() { commandService.redo(); }

    public void onAddDeviceClick() {
        ViewManager.getInstance().showAddDevice();
    }

    @Override
    public void onDeviceUpdate(DeviceEvent event) {
        Platform.runLater(this::refreshDashboard);
    }
}