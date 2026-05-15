package view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.device.SmartAC;
import service.CommandService;

public class ViewManager {

    private static volatile ViewManager instance;
    private Stage primaryStage;
    private CommandService commandService;

    private ViewManager() {}

    public static ViewManager getInstance() {
        if (instance == null) {
            synchronized (ViewManager.class) {
                if (instance == null) instance = new ViewManager();
            }
        }
        return instance;
    }

    public void init(Stage stage) {
        this.primaryStage = stage;
        this.commandService = new CommandService();
    }

    public CommandService getCommandService() { return commandService; }

    public void showDashboard() {
        service.DeviceService deviceService = new service.DeviceService();
        DashboardView dashboardView = new DashboardView();
        controller.DashboardController ctrl =
                new controller.DashboardController(deviceService, commandService);
        ctrl.init(dashboardView);
        Scene scene = new Scene(dashboardView.build(), 960, 660);
        primaryStage.setScene(scene);
        primaryStage.setTitle(util.AppConstants.APP_NAME);
        primaryStage.show();
    }

    public void showDashboard(Parent root) {
        Scene scene = new Scene(root, 960, 660);
        primaryStage.setScene(scene);
        primaryStage.setTitle(util.AppConstants.APP_NAME);
        primaryStage.show();
    }

    public void showACControl(SmartAC ac) {
        ACControlView view = new ACControlView();
        controller.ACController ctrl =
                new controller.ACController(ac, commandService);
        ctrl.init(view);
        Scene scene = new Scene(view.build(ac), 720, 620);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showAddDevice() {
        new AddDeviceFlow(
                new service.AddDeviceService(),
                new service.RoomService()
        ).show();
    }
}