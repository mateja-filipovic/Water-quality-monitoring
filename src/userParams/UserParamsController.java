package userParams;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Pair;
import simulation.Device;
import userHomeScreen.UserHomescreenController;
import util.SQLMethods;
import util.UserControllerInterface;

import java.net.URL;
import java.util.*;

public class UserParamsController implements Initializable, UserControllerInterface {

    @FXML public Label phLabel;
    @FXML public Circle phCircle;

    @FXML public Label orpLabel;
    @FXML public Circle orpCircle;

    @FXML public Label doLabel;
    @FXML public Circle doCircle;

    @FXML public Label ammoniaLabel;
    @FXML public Circle ammoniaCircle;

    @FXML public Label turbLabel;
    @FXML public Circle turbidityCircle;

    private Map<Integer, Pair<Label, Circle>> paramsMap;

    private UserHomescreenController userHomescreenController;
    private Device device = new Device(1, 1, "Arandjelovac");
    private Device device2 = new Device(2, 2, "Ljubovija");
    private Device currentDevice = device;
    private SQLMethods sqlMethods = new SQLMethods();
    @FXML private ChoiceBox<Device> deviceChoicebox;
    List<Double> currentParams;
    List<Pair<Double, Double>> refValues;

    @Override
    public void setHomeScreenController(UserHomescreenController userHomescreenController) {
        this.userHomescreenController = userHomescreenController;
    }

    public void exitApp(){
        Platform.exit();
    }

    @Override
    public void updateView() {
        for(int i = 0; i < currentParams.size(); i++){
            boolean status = getParamStatus(i, currentParams.get(i));
            if(status){
                paramsMap.get(i).getKey().setText("Good");
                paramsMap.get(i).getValue().setFill(Color.DODGERBLUE);
            }else{
                paramsMap.get(i).getKey().setText("Bad");
                paramsMap.get(i).getValue().setFill(Color.DARKRED);
            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        paramsMap = new HashMap<>();
        refValues = new ArrayList<>();

        deviceChoicebox.getItems().addAll(device, device2);
        deviceChoicebox.getSelectionModel().select(device);
        deviceChoicebox.setOnAction(this::choiceChangeHandler);

        if(currentDevice == null)
            currentDevice = deviceChoicebox.getSelectionModel().getSelectedItem();

        paramsMap.put(0, new Pair<>(phLabel, phCircle));
        paramsMap.put(1, new Pair<>(turbLabel, turbidityCircle));
        paramsMap.put(2, new Pair<>(doLabel, doCircle));
        paramsMap.put(3, new Pair<>(ammoniaLabel, ammoniaCircle));
        paramsMap.put(4, new Pair<>(orpLabel, orpCircle));

        refValues.add(new Pair<>(6.5, 8.5));
        refValues.add(new Pair<>(0.0, 5.0));
        refValues.add(new Pair<>(0.0, 18.0));
        refValues.add(new Pair<>(0.0, 0.2));
        refValues.add(new Pair<>(300.0, 500.0));

        this.currentParams = sqlMethods.getParams(currentDevice).get(0);
        updateView();
    }

    private void choiceChangeHandler(ActionEvent event) {
        this.currentDevice = deviceChoicebox.getSelectionModel().getSelectedItem();
        this.currentParams = sqlMethods.getParams(currentDevice).get(4);
        updateView();
    }

    private boolean getParamStatus(int paramId, double value){
        if(value >= refValues.get(paramId).getKey() && value <= refValues.get(paramId).getValue())
            return true;
        else
            return false;
    }
}
