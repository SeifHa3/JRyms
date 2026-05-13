package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DashboardView {

    private VBox deviceRowsBox;
    private Button addDeviceBtn;
    private Button undoBtn;
    private Button redoBtn;

    public DashboardView() {
        deviceRowsBox = new VBox(8);
        addDeviceBtn  = new Button("+ Add Device");
        undoBtn       = new Button("Undo");
        redoBtn       = new Button("Redo");
    }

    public VBox build() {
        Label titleLabel = new Label("SmartHome Controller");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        HBox topBar = new HBox(10, titleLabel, addDeviceBtn, undoBtn, redoBtn);
        topBar.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane(deviceRowsBox);
        scrollPane.setFitToWidth(true);

        VBox root = new VBox(topBar, scrollPane);
        root.setPadding(new Insets(10));

        return root;
    }

    public VBox getDeviceRowsBox()  { return deviceRowsBox; }
    public Button getAddDeviceBtn() { return addDeviceBtn; }
    public Button getUndoBtn()      { return undoBtn; }
    public Button getRedoBtn()      { return redoBtn; }
}