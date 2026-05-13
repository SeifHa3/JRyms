package view;

import javafx.scene.Scene;
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

    public void init(Stage stage) {
        this.primaryStage = stage;
    }

    public void showDashboard(javafx.scene.layout.VBox root) {
        javafx.scene.Scene scene = new javafx.scene.Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle(util.AppConstants.APP_NAME);
        primaryStage.show();
    }

    public void showACControl(SmartAC ac) {
        ACControlView view = new ACControlView();
        controller.ACController controller = new controller.ACController(ac,
                new service.CommandService());
        controller.init(view);
        javafx.scene.Scene scene = new javafx.scene.Scene(view.build(ac), 500, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showAddDevice() {
        // M5 will implement AddDeviceFlow
        // placeholder for now
    }
}