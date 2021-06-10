package loginScreen;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


import java.io.IOException;

public class LoginController {

    @FXML public TextField usernameField;

    @FXML public PasswordField passwordField;

    public void exitApp(){
        Platform.exit();
    }

    public void login(ActionEvent event) throws IOException {

        /*if(!usernameField.getText().equals("admin") || !passwordField.getText().equals("admin")){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.initStyle(StageStyle.UNDECORATED);
            errorAlert.setHeaderText(null);
            errorAlert.setGraphic(null);
            errorAlert.setContentText("Incorrect username or password");
            errorAlert.showAndWait();
            return;
        }*/

        Parent paramsView = FXMLLoader.load(getClass().getResource("/homeScreen/homeScreen.fxml"));
        Scene newScene = new Scene(paramsView);
        Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
        window.setScene(newScene);
        window.show();
    }
}
