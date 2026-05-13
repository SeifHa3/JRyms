package controller;

import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import model.command.ACSetModeCommand;
import model.command.ACSetTempCommand;
import model.command.ACToggleCommand;
import model.device.DeviceObserver;
import model.device.SmartAC;
import model.hub.DeviceEvent;
import service.CommandService;
import view.ACControlView;
import view.ViewManager;

public class ACController implements DeviceObserver {

    private final SmartAC ac;
    private final CommandService commandService;
    private ACControlView view;

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
            if (newVal == null) return;
            if (newVal == view.getCoolBtn()) onModeSelect(SmartAC.ACMode.COOL);
            else if (newVal == view.getHeatBtn()) onModeSelect(SmartAC.ACMode.HEAT);
            else if (newVal == view.getFanBtn())  onModeSelect(SmartAC.ACMode.FAN);
            else if (newVal == view.getAutoBtn()) onModeSelect(SmartAC.ACMode.AUTO);
        });

        view.getLowBtn().setOnAction(e ->
                commandService.execute(new ACSetModeCommand(ac, SmartAC.ACMode.FAN))
        );
        view.getMedBtn().setOnAction(e ->
                commandService.execute(new ACSetModeCommand(ac, SmartAC.ACMode.AUTO))
        );

        view.getUndoBtn().setOnAction(e -> { commandService.undo(); refreshButtonStates(); });
        view.getRedoBtn().setOnAction(e -> { commandService.redo(); refreshButtonStates(); });

        view.getBackBtn().setOnAction(e ->
                ViewManager.getInstance().showDashboard(
                        new view.DashboardView().build()
                )
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

    public void refreshButtonStates() {
        view.getUndoBtn().setDisable(!commandService.canUndo());
        view.getRedoBtn().setDisable(!commandService.canRedo());
    }

    public void refreshUI() {
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

        refreshButtonStates();
    }

    @Override
    public void onDeviceUpdate(DeviceEvent event) {
        refreshUI();
    }
}