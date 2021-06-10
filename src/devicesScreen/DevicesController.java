package devicesScreen;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.shape.Circle;
import models.Device;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DevicesController implements Initializable {

    @FXML public ChoiceBox<String> deviceChoicebox;
    @FXML public Circle deviceCircle;
    private List<Device> devices;

    public void exitApp(ActionEvent actionEvent) {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // ovo prebaciti na devices!
        List<String> items = new ArrayList<>();
        items.add("Belgrade");
        items.add("Ljubovija");
        items.add("AR na vodi");

        String[] arr = new String[items.size()];
        items.toArray(arr);
        deviceChoicebox.getItems().addAll(arr);
        deviceChoicebox.getSelectionModel().selectFirst();
        deviceChoicebox.setOnAction(this::choiceChangeHandler);
    }

    private void choiceChangeHandler(ActionEvent event){
        deviceCircle.setLayoutY(deviceCircle.getLayoutY()-5);
    }




}
