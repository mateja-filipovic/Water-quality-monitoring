package devicesScreen;

import homeScreen.HomeScreenController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.shape.Circle;
import simulation.Device;
import util.ControllerInterface;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DevicesController implements Initializable, ControllerInterface {

    @FXML private ChoiceBox<Device> deviceChoicebox;

    @FXML private Circle deviceCircle;

    private List<Device> devices; // to populate the choicebox

    private HomeScreenController homeScreenController; // to access the current user

    public void exitApp(ActionEvent actionEvent) {
        this.homeScreenController.getSimulation().terminate();
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private void choiceChangeHandler(ActionEvent event){
        // update circle coordinates
        deviceCircle.setLayoutY(deviceCircle.getLayoutY()-5);
    }

    @Override
    public void setHomeScreenController(HomeScreenController homeScreenController) {
        this.homeScreenController = homeScreenController;
        updateView();
    }

    @Override
    public void updateView() {
        // get the devices from the simulation class
        devices = this.homeScreenController.getSimulation().getAllDevices();

        //populate the choicebox and set default selection
        deviceChoicebox.getItems().addAll(devices);
        deviceChoicebox.getSelectionModel().selectFirst();
        deviceChoicebox.setOnAction(this::choiceChangeHandler);
    }


}
