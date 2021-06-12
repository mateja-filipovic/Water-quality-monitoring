package homeScreen;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import loginScreen.LoginController;
import models.User;
import simulation.Device;
import simulation.Simulation;
import util.ControllerObserver;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable {

    @FXML public Button paramsButton;
    @FXML public Button devicesButton;
    @FXML public Button analyticsButton;
    @FXML public Button actionsButton;
    @FXML public Label userInfo;
    @FXML private BorderPane mainPane;
    private Map<String, String> screens;
    private Map<Integer, Button> buttonSelectors;
    private int selected = 0; // 0 params, 1 devices, 2 analytics, 3 actions
    private User currentUser;
    private Device device;
    ControllerObserver currentController;
    private Simulation simulation;
    private Device currentDevice;
    private boolean simulationStarted = false;

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
        loginController.setSimulation(this.simulation);
    }

    private LoginController loginController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        screens = new HashMap<>();
        screens.put("paramsScreen", "/paramsScreen/params.fxml");
        screens.put("analyticsScreen", "/analyticsScreen/analytics.fxml");
        screens.put("devicesScreen", "/devicesScreen/devices.fxml");
        screens.put("applicationsScreen", "/actionsScreen/actions.fxml");
        screens.put("devicesScreen", "/devicesScreen/devices.fxml");

        buttonSelectors = new HashMap<>();
        buttonSelectors.put(0, paramsButton);
        buttonSelectors.put(1, devicesButton);
        buttonSelectors.put(2, analyticsButton);
        buttonSelectors.put(3, actionsButton);

        simulation = new Simulation(5, this);
        currentDevice = new Device(1, 10, "Arandjelovac");
        simulation.addDevice(currentDevice);


        Pane view = getPage("paramsScreen");
        mainPane.setCenter(view);
    }

    public void displayUserInfo(){
        userInfo.setText(currentUser.getName() + " " + currentUser.getLastName());
    }

    private Pane getPage(String fileName) {
        Pane view = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(screens.get(fileName)));
            view = loader.load();
            currentController = (ControllerObserver) loader.getController();
            currentController.setHomeScreenController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }

    private void changeScene(Pane newPane){
        FadeTransition ft = new FadeTransition(Duration.millis(1000), newPane);
        ft.setFromValue(0.5);
        ft.setToValue(1.0);
        ft.play();
        mainPane.setCenter(newPane);
    }

    public void onClickParams(){
        handleMenuClick(0, "paramsScreen");
    }

    public void onClickDevices(){
        handleMenuClick(1, "devicesScreen");
    }

    public void onClickAnalytics(){
        handleMenuClick(2, "analyticsScreen");
    }

    public void onClickApplications(){
        handleMenuClick(3, "applicationsScreen");
    }

    private void handleMenuClick(int mySelector, String fileName){
        if(selected == mySelector)
            return;
        changeScene(getPage(fileName));
        buttonSelectors.get(selected).getStyleClass().remove("active-element");
        selected = mySelector;
        buttonSelectors.get(selected).getStyleClass().addAll("active-element");
    }



    public void logOut(ActionEvent actionEvent) throws IOException {
        Parent paramsView = FXMLLoader.load(getClass().getResource("/loginScreen/login.fxml"));
        Scene newScene = new Scene(paramsView);
        Stage window = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        window.setScene(newScene);
        window.show();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        displayUserInfo();
    }

    public void updateView(){
        if(selected == 0)
            currentController.updateView();
    }

    public Device getCurrentDevice() {
        return currentDevice;
    }

    public void setCurrentDevice(Device currentDevice) {
        this.currentDevice = currentDevice;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void startSimulation(ActionEvent event) {
        if(simulationStarted)
            return;
        simulationStarted = true;
        this.simulation.start();
    }
}
