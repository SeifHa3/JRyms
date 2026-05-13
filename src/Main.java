import javafx.application.Application;
import javafx.stage.Stage;
import model.device.SmartAC;
import model.hub.DeviceRegistry;
import service.CommandService;
import service.DeviceService;
import controller.DashboardController;
import view.DashboardView;
import view.ViewManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        ViewManager.getInstance().init(primaryStage);
        bootstrapDevices();

        DeviceService deviceService = new DeviceService();
        CommandService commandService = new CommandService();

        DashboardView dashboardView = new DashboardView();
        DashboardController controller = new DashboardController(deviceService, commandService);
        controller.init(dashboardView);

        ViewManager.getInstance().showDashboard();
    }

    private void bootstrapDevices() {
        DeviceRegistry registry = DeviceRegistry.getInstance();
        SmartAC ac1 = new SmartAC("ac1", "Living Room AC");
        SmartAC ac2 = new SmartAC("ac2", "Bedroom AC");
        registry.register(ac1);
        registry.register(ac2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}