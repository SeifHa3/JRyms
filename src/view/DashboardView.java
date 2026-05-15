package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import util.UIStyles;

public class DashboardView {

    private VBox deviceRowsBox;
    private Button addDeviceBtn;
    private Button undoBtn;
    private Button redoBtn;

    public DashboardView() {
        deviceRowsBox = new VBox(16);
        addDeviceBtn  = new Button("＋  Add Device");
        undoBtn       = new Button("↩  Undo");
        redoBtn       = new Button("↪  Redo");
        addDeviceBtn.setStyle(UIStyles.BTN_PRIMARY);
        undoBtn.setStyle(UIStyles.BTN_SECONDARY);
        redoBtn.setStyle(UIStyles.BTN_SECONDARY);
    }

    public BorderPane build() {
        BorderPane root = new BorderPane();
        root.setLeft(buildSidebar());
        root.setCenter(buildContentArea());
        return root;
    }

    private VBox buildSidebar() {
        VBox sidebar = new VBox();
        sidebar.setPrefWidth(230);
        sidebar.setMinWidth(230);
        sidebar.setStyle("-fx-background-color: #1a1a2e;");

        // Logo area
        VBox logoArea = new VBox(6);
        logoArea.setPadding(new Insets(28, 24, 24, 24));
        logoArea.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #0f0c29, #302b63);"
        );
        Label icon = new Label("🏠");
        icon.setStyle("-fx-font-size: 30px;");
        Label appName = new Label("JRyms");
        appName.setStyle(
                "-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;"
        );
        Label appSub = new Label("Smart Home Controller");
        appSub.setStyle("-fx-font-size: 10px; -fx-text-fill: #8892b0;");
        logoArea.getChildren().addAll(icon, appName, appSub);

        // Divider
        Region div = new Region();
        div.setPrefHeight(1);
        div.setStyle("-fx-background-color: #2d3561;");

        // Nav label
        Label navLabel = new Label("MENU");
        navLabel.setStyle(
                "-fx-font-size: 9px; -fx-font-weight: bold;" +
                        "-fx-text-fill: #4a5568; -fx-padding: 20 24 8 24;"
        );

        // Nav items
        VBox nav = new VBox(2,
                buildNavItem("📊", "Dashboard", true),
                buildNavItem("💡", "Devices",   false),
                buildNavItem("🚪", "Rooms",     false),
                buildNavItem("⚙️",  "Settings",  false)
        );

        // Spacer
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // Bottom
        VBox bottom = new VBox(4);
        bottom.setPadding(new Insets(14, 24, 18, 24));
        bottom.setStyle("-fx-background-color: #16213e;");
        Label ver = new Label("●  Phase 1  ·  v1.0");
        ver.setStyle("-fx-font-size: 10px; -fx-text-fill: #4a5568;");
        bottom.getChildren().add(ver);

        sidebar.getChildren().addAll(logoArea, div, navLabel, nav, spacer, bottom);
        return sidebar;
    }

    private HBox buildNavItem(String icon, String text, boolean active) {
        HBox item = new HBox(12);
        item.setAlignment(Pos.CENTER_LEFT);
        item.setPadding(new Insets(11, 24, 11, 20));

        Label iconLbl = new Label(icon);
        iconLbl.setStyle("-fx-font-size: 14px;");

        Label textLbl = new Label(text);

        if (active) {
            item.setStyle(
                    "-fx-background-color: linear-gradient(to right, #6c63ff, #8b83ff);" +
                            "-fx-background-radius: 0 8 8 0;"
            );
            Region bar = new Region();
            bar.setPrefSize(3, 20);
            bar.setStyle("-fx-background-color: white; -fx-background-radius: 2;");
            textLbl.setStyle(
                    "-fx-font-size: 13px; -fx-text-fill: white; -fx-font-weight: bold;"
            );
            item.getChildren().addAll(bar, iconLbl, textLbl);
        } else {
            textLbl.setStyle("-fx-font-size: 13px; -fx-text-fill: #8892b0;");
            item.getChildren().addAll(iconLbl, textLbl);
            item.setOnMouseEntered(e ->
                    item.setStyle("-fx-background-color: #16213e; -fx-cursor: hand; -fx-background-radius: 0 8 8 0;")
            );
            item.setOnMouseExited(e ->
                    item.setStyle("-fx-background-color: transparent;")
            );
        }
        return item;
    }

    private VBox buildContentArea() {
        VBox content = new VBox();
        content.setStyle("-fx-background-color: #f0f2f5;");

        // Header bar
        HBox header = new HBox(10);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(18, 28, 18, 28));
        header.setStyle(
                "-fx-background-color: white;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.06), 8, 0, 0, 2);"
        );
        VBox titleBox = new VBox(2);
        Label title    = new Label("Dashboard");
        Label subtitle = new Label("Monitor and control your smart devices");
        title.setStyle(UIStyles.LABEL_PAGE_TITLE);
        subtitle.setStyle(UIStyles.LABEL_SUBTITLE);
        titleBox.getChildren().addAll(title, subtitle);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox actions = new HBox(8, undoBtn, redoBtn, addDeviceBtn);
        actions.setAlignment(Pos.CENTER_RIGHT);

        header.getChildren().addAll(titleBox, spacer, actions);

        // Scrollable content
        VBox wrapper = new VBox(deviceRowsBox);
        wrapper.setPadding(new Insets(24, 28, 24, 28));
        wrapper.setStyle("-fx-background-color: #f0f2f5;");

        ScrollPane scroll = new ScrollPane(wrapper);
        scroll.setFitToWidth(true);
        scroll.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background: transparent;" +
                        "-fx-border-color: transparent;"
        );
        VBox.setVgrow(scroll, Priority.ALWAYS);

        content.getChildren().addAll(header, scroll);
        return content;
    }

    public VBox getDeviceRowsBox()  { return deviceRowsBox; }
    public Button getAddDeviceBtn() { return addDeviceBtn; }
    public Button getUndoBtn()      { return undoBtn; }
    public Button getRedoBtn()      { return redoBtn; }
}