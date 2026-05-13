package view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.device.SmartAC;

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
        onOffBtn   = new Button();
        tempLabel  = new Label();
        tempSlider = new Slider(16, 30, 22);
        tempSlider.setShowTickLabels(true);
        tempSlider.setShowTickMarks(true);
        tempSlider.setMajorTickUnit(2);
        tempSlider.setBlockIncrement(1);

        modeGroup = new ToggleGroup();
        coolBtn = new ToggleButton("COOL");
        heatBtn = new ToggleButton("HEAT");
        fanBtn  = new ToggleButton("FAN");
        autoBtn = new ToggleButton("AUTO");
        coolBtn.setToggleGroup(modeGroup);
        heatBtn.setToggleGroup(modeGroup);
        fanBtn.setToggleGroup(modeGroup);
        autoBtn.setToggleGroup(modeGroup);

        fanGroup = new ToggleGroup();
        lowBtn  = new ToggleButton("LOW");
        medBtn  = new ToggleButton("MED");
        highBtn = new ToggleButton("HIGH");
        lowBtn.setToggleGroup(fanGroup);
        medBtn.setToggleGroup(fanGroup);
        highBtn.setToggleGroup(fanGroup);

        undoBtn = new Button("Undo");
        redoBtn = new Button("Redo");
        backBtn = new Button("← Back");
    }

    public VBox build(SmartAC ac) {
        nameLabel.setText("AC Control — " + ac.getName());
        nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        HBox modeBox   = new HBox(8, coolBtn, heatBtn, fanBtn, autoBtn);
        HBox fanBox    = new HBox(8, new Label("Fan:"), lowBtn, medBtn, highBtn);
        HBox actionBox = new HBox(10, undoBtn, redoBtn, backBtn);

        VBox root = new VBox(12,
                nameLabel,
                onOffBtn,
                tempLabel,
                tempSlider,
                new Label("Mode:"),
                modeBox,
                fanBox,
                actionBox
        );
        root.setPadding(new Insets(20));
        return root;
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