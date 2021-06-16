package userHomeScreen;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import models.User;
import simulation.Device;
import simulation.Simulation;
import util.ControllerObserver;
import util.UserControllerObserver;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class UserHomescreenController implements Initializable{

    @FXML public Button paramsButton;
    @FXML public Button actionsButton;
    @FXML public Button aboutButton;
    @FXML private BorderPane mainPane;

    private Map<String, String> screens;
    private Map<Integer, Button> buttonSelectors;

    private int selected = 0; // 0 params, 1 devices, 2 analytics, 3 actions

    private User currentUser;
    private Device device;
    UserControllerObserver currentController;
    private Device currentDevice;


    public void onClickParams(ActionEvent event) {
        handleMenuClick(0, "userParams");
    }

    public void onClickActions(ActionEvent event) {
        handleMenuClick(1, "userActions");
    }

    public void onClickAbout(ActionEvent event) {
        handleMenuClick(2, "userAboutUs");
    }

    public void setCurrentUser(User currentUser){
        this.currentUser = currentUser;
    }
    public User getCurrentUser(){ return this.currentUser; }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        screens = new HashMap<>();
        screens.put("userParams", "/userParams/userparams.fxml");
        screens.put("userActions", "/userActions/useractions.fxml");
        screens.put("userAboutUs", "/userAboutUs/aboutus.fxml");

        buttonSelectors = new HashMap<>();
        buttonSelectors.put(0, paramsButton);
        buttonSelectors.put(1, actionsButton);
        buttonSelectors.put(2, aboutButton);

        Pane view = getPage("userParams");
        buttonSelectors.get(selected).getStyleClass().add("active-element");
        mainPane.setCenter(view);
    }

    private Pane getPage(String fileName) {
        Pane view = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(screens.get(fileName)));
            view = loader.load();
            currentController = (UserControllerObserver) loader.getController();
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

    private void handleMenuClick(int mySelector, String fileName){
        if(selected == mySelector)
            return;
        changeScene(getPage(fileName));
        buttonSelectors.get(selected).getStyleClass().remove("active-element");
        selected = mySelector;
        buttonSelectors.get(selected).getStyleClass().addAll("active-element");
    }


}
