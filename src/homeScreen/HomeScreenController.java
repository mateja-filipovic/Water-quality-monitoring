package homeScreen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable {

    private Map<String, String> screens;
    @FXML
    private BorderPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        screens = new HashMap<>();
        screens.put("paramsScreen", "/paramsScreen/params.fxml");
        screens.put("analyticsScreen", "/analyticsScreen/analytics.fxml");
        screens.put("devicesScreen", "/devicesScreen/devices.fxml");
        screens.put("applicationsScreen", "/actionsScreen/actions.fxml");
        screens.put("devicesScreen", "/devicesScreen/devices.fxml");

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
        mainPane.setCenter(newPane);
    }

    public void onClickParams(){
        changeScene(getPage("paramsScreen"));
    }

    public void onClickAnalytics(){
        changeScene(getPage("analyticsScreen"));
    }

    public void onClickApplications(){
        changeScene((getPage("applicationsScreen")));
    }

    public void onClickDevices(){
        changeScene((getPage("devicesScreen")));
    }
}
