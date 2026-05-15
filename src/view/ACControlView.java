package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.device.SmartAC;
import util.UIStyles;

public class ACControlView {

    private Label nameLabel;
    private Button onOffBtn;
    private Label tempLabel;
    private Slider tempSlider;
    private ToggleGroup modeGroup;
    private ToggleButton coolBtn, heatBtn, fanBtn, autoBtn;
    private ToggleGroup fanGroup;
    private ToggleButton lowBtn, medBtn, highBtn;
    private Button undoBtn, redoBtn, backBtn;

    public ACControlView() {
        nameLabel  = new Label();
        onOffBtn   = new Button("⏻  Turn ON");
        tempLabel  = new Label("22.0°C");
        tempSlider = new Slider(16, 30, 22);
        tempSlider.setShowTickLabels(true);
        tempSlider.setShowTickMarks(true);
        tempSlider.setMajorTickUnit(2);
        tempSlider.setBlockIncrement(1);
        tempSlider.setStyle("-fx-accent: #6c63ff;");

        modeGroup = new ToggleGroup();
        coolBtn = new ToggleButton("❄️  COOL");
        heatBtn = new ToggleButton("🔥  HEAT");
        fanBtn  = new ToggleButton("💨  FAN");
        autoBtn = new ToggleButton("⚡  AUTO");
        for (ToggleButton tb : new ToggleButton[]{coolBtn, heatBtn, fanBtn, autoBtn}) {
            tb.setToggleGroup(modeGroup);
            tb.setStyle(UIStyles.TOGGLE_UNSELECTED);
        }

        fanGroup = new ToggleGroup();
        lowBtn  = new ToggleButton("🌬  LOW");
        medBtn  = new ToggleButton("💨  MED");
        highBtn = new ToggleButton("🌀  HIGH");
        for (ToggleButton tb : new ToggleButton[]{lowBtn, medBtn, highBtn}) {
            tb.setToggleGroup(fanGroup);
            tb.setStyle(UIStyles.TOGGLE_UNSELECTED);
        }

        onOffBtn = new Button("⏻  Turn ON");
        undoBtn  = new Button("↩  Undo");
        redoBtn  = new Button("↪  Redo");
        backBtn  = new Button("←  Back");

        onOffBtn.setStyle(UIStyles.BTN_SUCCESS);
        undoBtn.setStyle(UIStyles.BTN_HEADER_GHOST);
        redoBtn.setStyle(UIStyles.BTN_HEADER_GHOST);
        backBtn.setStyle(UIStyles.BTN_HEADER_GHOST);
    }

    public BorderPane build(SmartAC ac) {
        nameLabel.setText(ac.getName());

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f0f2f5;");
        root.setTop(buildHeader());

        ScrollPane scroll = new ScrollPane(buildContent());
        scroll.setFitToWidth(true);
        scroll.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background: transparent;" +
                        "-fx-border-color: transparent;"
        );
        root.setCenter(scroll);
        return root;
    }

    private HBox buildHeader() {
        HBox header = new HBox(12);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(18, 28, 18, 28));
        header.setStyle(
                "-fx-background-color: linear-gradient(to right, #1a1a2e, #302b63);" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);"
        );

        VBox titleBox = new VBox(2);
        nameLabel.setStyle(
                "-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;"
        );
        Label sub = new Label("AC Control Panel");
        sub.setStyle("-fx-font-size: 11px; -fx-text-fill: #8892b0;");
        titleBox.getChildren().addAll(nameLabel, sub);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox actions = new HBox(8, undoBtn, redoBtn, onOffBtn);
        actions.setAlignment(Pos.CENTER_RIGHT);

        header.getChildren().addAll(backBtn, titleBox, spacer, actions);
        return header;
    }

    private VBox buildContent() {
        VBox content = new VBox(16);
        content.setPadding(new Insets(24, 28, 24, 28));
        content.setStyle("-fx-background-color: #f0f2f5;");
        content.getChildren().addAll(
                buildTempCard(),
                buildModeCard(),
                buildFanCard()
        );
        return content;
    }

    private VBox buildTempCard() {
        VBox card = new VBox(16);
        card.setPadding(new Insets(22));
        card.setStyle(UIStyles.CARD);

        Label sectionTitle = new Label("🌡   TEMPERATURE");
        sectionTitle.setStyle(UIStyles.LABEL_SECTION_HEADER);

        VBox tempDisplay = new VBox(4);
        tempDisplay.setAlignment(Pos.CENTER);
        tempLabel.setStyle(
                "-fx-font-size: 52px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #6c63ff;"
        );
        Label hint = new Label("Drag to adjust target temperature");
        hint.setStyle("-fx-font-size: 11px; -fx-text-fill: #b2bec3;");
        tempDisplay.getChildren().addAll(tempLabel, hint);

        HBox sliderRow = new HBox(10);
        sliderRow.setAlignment(Pos.CENTER);
        Label minLbl = new Label("16°");
        Label maxLbl = new Label("30°");
        minLbl.setStyle("-fx-font-size: 11px; -fx-text-fill: #b2bec3;");
        maxLbl.setStyle("-fx-font-size: 11px; -fx-text-fill: #b2bec3;");
        HBox.setHgrow(tempSlider, Priority.ALWAYS);
        sliderRow.getChildren().addAll(minLbl, tempSlider, maxLbl);

        card.getChildren().addAll(sectionTitle, tempDisplay, sliderRow);
        return card;
    }

    private VBox buildModeCard() {
        VBox card = new VBox(14);
        card.setPadding(new Insets(22));
        card.setStyle(UIStyles.CARD);

        Label sectionTitle = new Label("⚙️   OPERATING MODE");
        sectionTitle.setStyle(UIStyles.LABEL_SECTION_HEADER);

        HBox modeRow = new HBox(10, coolBtn, heatBtn, fanBtn, autoBtn);
        modeRow.setAlignment(Pos.CENTER_LEFT);

        card.getChildren().addAll(sectionTitle, modeRow);
        return card;
    }

    private VBox buildFanCard() {
        VBox card = new VBox(14);
        card.setPadding(new Insets(22));
        card.setStyle(UIStyles.CARD);

        Label sectionTitle = new Label("💨   FAN SPEED");
        sectionTitle.setStyle(UIStyles.LABEL_SECTION_HEADER);

        HBox fanRow = new HBox(10, lowBtn, medBtn, highBtn);
        fanRow.setAlignment(Pos.CENTER_LEFT);

        card.getChildren().addAll(sectionTitle, fanRow);
        return card;
    }

    public Button getOnOffBtn()          { return onOffBtn; }
    public Label getTempLabel()          { return tempLabel; }
    public Slider getTempSlider()        { return tempSlider; }
    public ToggleGroup getModeGroup()    { return modeGroup; }
    public ToggleButton getCoolBtn()     { return coolBtn; }
    public ToggleButton getHeatBtn()     { return heatBtn; }
    public ToggleButton getFanBtn()      { return fanBtn; }
    public ToggleButton getAutoBtn()     { return autoBtn; }
    public ToggleGroup getFanGroup()     { return fanGroup; }
    public ToggleButton getLowBtn()      { return lowBtn; }
    public ToggleButton getMedBtn()      { return medBtn; }
    public ToggleButton getHighBtn()     { return highBtn; }
    public Button getUndoBtn()           { return undoBtn; }
    public Button getRedoBtn()           { return redoBtn; }
    public Button getBackBtn()           { return backBtn; }
}