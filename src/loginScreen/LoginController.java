package loginScreen;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class LoginController {

    public void exitApp(){
        Platform.exit();
    }

    public void login(ActionEvent event) throws IOException {
        Parent paramsView = FXMLLoader.load(getClass().getResource("/homeScreen/homeScreen.fxml"));
        Scene newScene = new Scene(paramsView);
        Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
        window.setScene(newScene);
        window.show();
    }
}
