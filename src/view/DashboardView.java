package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class DashboardView {

    private VBox deviceRowsBox;

    public DashboardView() {
        deviceRowsBox = new VBox(8);
    }

    public VBox build() {

        Label titleLabel = new Label("SmartHome Controller");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button addDeviceBtn = new Button("+ Add Device");
        Button undoBtn = new Button("Undo");
        Button redoBtn = new Button("Redo");

        HBox topBar = new HBox(10, titleLabel, addDeviceBtn, undoBtn, redoBtn);
        topBar.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane(deviceRowsBox);
        scrollPane.setFitToWidth(true);

        VBox root = new VBox(topBar, scrollPane);
        root.setPadding(new Insets(10));

        return root;
    }

    public VBox getDeviceRowsBox() {
        return deviceRowsBox;
    }
}