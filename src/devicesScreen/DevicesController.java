package devicesScreen;

import homeScreen.HomeScreenController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.shape.Circle;
import simulation.Device;
import util.ControllerObserver;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DevicesController implements Initializable, ControllerObserver {

    @FXML public ChoiceBox<Device> deviceChoicebox;
    @FXML public Circle deviceCircle;
    private List<Device> devices;
    private HomeScreenController homeScreenController;

    public void exitApp(ActionEvent actionEvent) {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // ovo prebaciti na devices!
        //devices = this.homeScreenController.getSimulation().getAllDevices();
        //String[] arr = new String[items.size()];
        //items.toArray(arr);
        //deviceChoicebox.getItems().addAll(devices);
        //deviceChoicebox.getSelectionModel().selectFirst();
        //deviceChoicebox.setOnAction(this::choiceChangeHandler);
    }

    private void choiceChangeHandler(ActionEvent event){
        deviceCircle.setLayoutY(deviceCircle.getLayoutY()-5);
    }

    @Override
    public void setHomeScreenController(HomeScreenController homeScreenController) {
        this.homeScreenController = homeScreenController;
        updateView();
    }

    @Override
    public void updateView() {
        // ovo prebaciti na devices!
        devices = this.homeScreenController.getSimulation().getAllDevices();
        //String[] arr = new String[items.size()];
        //items.toArray(arr);
        deviceChoicebox.getItems().addAll(devices);
        deviceChoicebox.getSelectionModel().selectFirst();
        deviceChoicebox.setOnAction(this::choiceChangeHandler);
    }


}
