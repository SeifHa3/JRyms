package view;

import javafx.stage.Stage;
import model.device.SmartAC;

public class ViewManager {

    private static volatile ViewManager instance;
    private Stage primaryStage;

    private ViewManager() {}

    public static ViewManager getInstance() {
        if (instance == null) {
            synchronized (ViewManager.class) {
                if (instance == null) {
                    instance = new ViewManager();
                }
            }
        }
        return instance;
    }

    private service.CommandService commandService;

    public void init(Stage stage) {
        this.primaryStage = stage;
        this.commandService = new service.CommandService();
    }

    public void showDashboard() {
        service.DeviceService deviceService = new service.DeviceService();

        DashboardView dashboardView = new DashboardView();
        controller.DashboardController ctrl = new controller.DashboardController(
                deviceService, commandService
        );
        ctrl.init(dashboardView);

        javafx.scene.Scene scene = new javafx.scene.Scene(dashboardView.build(), 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle(util.AppConstants.APP_NAME);
        primaryStage.show();
    }

    public void showACControl(SmartAC ac) {
        ACControlView view = new ACControlView();
        controller.ACController controller = new controller.ACController(ac, commandService);
        controller.init(view);
        javafx.scene.Scene scene = new javafx.scene.Scene(view.build(ac), 500, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showDashboard(javafx.scene.layout.VBox root) {
        javafx.scene.Scene scene = new javafx.scene.Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle(util.AppConstants.APP_NAME);
        primaryStage.show();
    }

    public void showAddDevice() {
        AddDeviceFlow flow = new AddDeviceFlow(
                new service.AddDeviceService(),
                new service.RoomService()
        );
        flow.show();
    }
    public service.CommandService getCommandService() {
        return commandService;
    }
}