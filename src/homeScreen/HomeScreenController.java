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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    @FXML private BorderPane mainPane;
    private Map<String, String> screens;
    private Map<Integer, Button> buttonSelectors;
    private int selected = 0; // 0 params, 1 devices, 2 analytics, 3 actions


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

        Pane view = getPage("paramsScreen");
        mainPane.setCenter(view);
    }

    private Pane getPage(String fileName) {
        Pane view = null;
        try {
            view = FXMLLoader.load(getClass().getResource(screens.get(fileName)));
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
}
