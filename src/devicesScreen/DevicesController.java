package devicesScreen;

import homeScreen.HomeScreenController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.shape.Circle;
import javafx.util.Pair;
import simulation.Device;
import util.ControllerInterface;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class DevicesController implements Initializable, ControllerInterface {

    @FXML private ChoiceBox<Device> deviceChoicebox;

    @FXML private Circle deviceCircle;

    private List<Device> devices; // to populate the choicebox
    private Map<Device, Pair<Double, Double>> deviceCoordinatesMap = new HashMap<>(); // deviceCoordinatesMap;

    private HomeScreenController homeScreenController; // to access the current user

    public void exitApp(ActionEvent actionEvent) {
        this.homeScreenController.getSimulation().terminate();
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private void choiceChangeHandler(ActionEvent event){
        Device currentDevice = deviceChoicebox.getSelectionModel().getSelectedItem();
        // update indicator coordinates
        deviceCircle.setLayoutX(deviceCoordinatesMap.get(currentDevice).getKey());
        deviceCircle.setLayoutY(deviceCoordinatesMap.get(currentDevice).getValue());
        // update current device in homescreen controller
        this.homeScreenController.setCurrentDevice(deviceChoicebox.getSelectionModel().getSelectedItem());
    }

    @Override
    public void setHomeScreenController(HomeScreenController homeScreenController) {
        this.homeScreenController = homeScreenController;
        loadDevices();
        updateView();
    }

    private void loadDevices(){
        this.devices = this.homeScreenController.getSimulation().getAllDevices();
        this.deviceCoordinatesMap.put(devices.get(0), new Pair<>(208.0, 275.0));
        this.deviceCoordinatesMap.put(devices.get(1), new Pair<>(109.0, 278.0));
        this.deviceCoordinatesMap.put(devices.get(2), new Pair<>(199.0, 231.0));
    }

    @Override
    public void updateView() {
        //populate the choicebox and set default selection
        deviceChoicebox.getItems().addAll(devices);
        deviceChoicebox.getSelectionModel().select(this.homeScreenController.getCurrentDevice());
        deviceChoicebox.setOnAction(this::choiceChangeHandler);
        deviceCircle.setLayoutX(deviceCoordinatesMap.get(deviceChoicebox.getSelectionModel().getSelectedItem()).getKey());
        deviceCircle.setLayoutY(deviceCoordinatesMap.get(deviceChoicebox.getSelectionModel().getSelectedItem()).getValue());
    }


}
