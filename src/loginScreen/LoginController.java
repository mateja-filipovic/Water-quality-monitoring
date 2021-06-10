package loginScreen;

import homeScreen.HomeScreenController;
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
import models.User;


import java.io.IOException;

public class LoginController {

    @FXML public TextField usernameField;

    @FXML public PasswordField passwordField;

    public void exitApp(){
        Platform.exit();
    }

    public void login(ActionEvent event) throws IOException {

        /*
        User currentUser = get user from database;
        if(currentUser == null){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.initStyle(StageStyle.UNDECORATED);
            errorAlert.setHeaderText(null);
            errorAlert.setGraphic(null);
            errorAlert.setContentText("Incorrect username or password");
            errorAlert.showAndWait();
            return;
        }
        */

        // dummy user
        User user = new User("Joe", "Doe", "joedoe", "password", "email@gmail.com", 1);
        // dummy user

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/homeScreen/homeScreen.fxml"));
        Parent paramsView = loader.load();

        HomeScreenController controller = loader.getController();
        controller.setCurrentUser(user);

        Scene newScene = new Scene(paramsView);
        Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
        window.setScene(newScene);
        window.show();
    }
}
