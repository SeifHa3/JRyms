package controller;

import javafx.application.Platform;
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

        List<Room> rooms = new service.RoomService().getAllRooms();
        for (Room room : rooms) {
            if (room.getDeviceCount() > 0) {
                rows.getChildren().add(new RoomGroupView(room).buildSection());
            }
        }
    }

    private HBox buildDeviceRow(SmartDevice device) {
        Label nameLabel   = new Label(device.getName());
        Label statusLabel = new Label(device.getStatus().toString());
        Button controlBtn = new Button("Control");

        controlBtn.setOnAction(e -> {
            if (device instanceof SmartAC) {
                ViewManager.getInstance().showACControl((SmartAC) device);
            }
        });

        HBox row = new HBox(15, nameLabel, statusLabel, controlBtn);
        return row;
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