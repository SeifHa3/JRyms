package controller;

import javafx.scene.control.ToggleButton;
import model.command.ACSetFanSpeedCommand;
import model.command.ACSetModeCommand;
import model.command.ACSetTempCommand;
import model.command.ACToggleCommand;
import observer.DeviceObserver;
import model.device.SmartAC;
import model.hub.DeviceEvent;
import service.CommandService;
import view.ACControlView;
import view.ViewManager;

public class ACController implements DeviceObserver {

    private final SmartAC ac;
    private final CommandService commandService;
    private ACControlView view;
    private boolean updatingUI = false;

    public ACController(SmartAC ac, CommandService commandService) {
        this.ac = ac;
        this.commandService = commandService;
    }

    public void init(ACControlView view) {
        this.view = view;
        ac.addObserver(this);
        wireButtons();
        refreshUI();
    }

    private void wireButtons() {
        view.getOnOffBtn().setOnAction(e -> onToggle());

        view.getTempSlider().setOnMouseReleased(e ->
                onSliderRelease(view.getTempSlider().getValue())
        );

        view.getModeGroup().selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || updatingUI) return;
            if (newVal == view.getCoolBtn())      onModeSelect(SmartAC.ACMode.COOL);
            else if (newVal == view.getHeatBtn()) onModeSelect(SmartAC.ACMode.HEAT);
            else if (newVal == view.getFanBtn())  onModeSelect(SmartAC.ACMode.FAN);
            else if (newVal == view.getAutoBtn()) onModeSelect(SmartAC.ACMode.AUTO);
        });

        view.getFanGroup().selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || updatingUI) return;
            if (newVal == view.getLowBtn())        onFanSpeedSelect(SmartAC.FanSpeed.LOW);
            else if (newVal == view.getMedBtn())   onFanSpeedSelect(SmartAC.FanSpeed.MED);
            else if (newVal == view.getHighBtn())  onFanSpeedSelect(SmartAC.FanSpeed.HIGH);
        });

        view.getUndoBtn().setOnAction(e -> { commandService.undo(); refreshButtonStates(); });
        view.getRedoBtn().setOnAction(e -> { commandService.redo(); refreshButtonStates(); });

        view.getBackBtn().setOnAction(e ->
                ViewManager.getInstance().showDashboard()
        );
    }

    public void onToggle() {
        commandService.execute(new ACToggleCommand(ac));
        refreshButtonStates();
    }

    public void onSliderRelease(double val) {
        commandService.execute(new ACSetTempCommand(ac, val));
        refreshButtonStates();
    }

    public void onModeSelect(SmartAC.ACMode mode) {
        commandService.execute(new ACSetModeCommand(ac, mode));
        refreshButtonStates();
    }

    public void onFanSpeedSelect(SmartAC.FanSpeed speed) {
        commandService.execute(new ACSetFanSpeedCommand(ac, speed));
        refreshButtonStates();
    }

    public void refreshButtonStates() {
        view.getUndoBtn().setDisable(!commandService.canUndo());
        view.getRedoBtn().setDisable(!commandService.canRedo());
    }

    public void refreshUI() {
        updatingUI = true;

        view.getOnOffBtn().setText(ac.isOn() ? "Turn OFF" : "Turn ON");
        view.getTempLabel().setText("Target Temp: " + ac.getTargetTemp() + "°C");
        view.getTempSlider().setValue(ac.getTargetTemp());

        ToggleButton toSelect = switch (ac.getMode()) {
            case COOL -> view.getCoolBtn();
            case HEAT -> view.getHeatBtn();
            case FAN  -> view.getFanBtn();
            case AUTO -> view.getAutoBtn();
        };
        toSelect.setSelected(true);

        ToggleButton fanToSelect = switch (ac.getFanSpeed()) {
            case LOW  -> view.getLowBtn();
            case MED  -> view.getMedBtn();
            case HIGH -> view.getHighBtn();
        };
        fanToSelect.setSelected(true);

        updatingUI = false;

        refreshButtonStates();
    }

    @Override
    public void onDeviceUpdate(DeviceEvent event) {
        refreshUI();
    }
}