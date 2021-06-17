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

    // labels for ph, orp, dissolved oxy, ammonia conc, turbidity
    @FXML private Label phLabel;
    @FXML private Circle phCircle;

    @FXML private Label orpLabel;
    @FXML private Circle orpCircle;

    @FXML private Label doLabel;
    @FXML private Circle doCircle;

    @FXML private Label ammoniaLabel;
    @FXML private Circle ammoniaCircle;

    @FXML private Label turbLabel;
    @FXML private Circle turbidityCircle;

    @FXML private ChoiceBox<Device> deviceChoicebox;

    // maps labels and status indicators to selectors
    private Map<Integer, Pair<Label, Circle>> paramsMap;

    private UserHomescreenController userHomescreenController;

    // available device list
    private Device deviceAR = new Device(1, 1, "Arandjelovac");
    private Device deviceLJ = new Device(2, 2, "Ljubovija");
    private Device deviceBG = new Device(3, 3, "Beograd");

    private Device currentDevice = deviceAR;

    private SQLMethods sqlMethods = new SQLMethods();

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
        // conditional rendering of indicators and labels
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

        deviceChoicebox.getItems().addAll(deviceAR, deviceLJ, deviceBG);
        deviceChoicebox.getSelectionModel().select(currentDevice);
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
        this.currentParams = sqlMethods.getParams(currentDevice).get(0);
        updateView();
    }

    // check if params are in allowed range
    private boolean getParamStatus(int paramId, double value){
        if(value >= refValues.get(paramId).getKey() && value <= refValues.get(paramId).getValue())
            return true;
        else
            return false;
    }
}
