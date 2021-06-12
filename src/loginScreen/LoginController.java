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
import simulation.Simulation;
import util.SQLMethods;


import java.io.IOException;

public class LoginController {

    @FXML public TextField usernameField;

    @FXML public PasswordField passwordField;

    private Simulation simulation;

    public void exitApp(){
        if(simulation != null)
            simulation.terminate();
        Platform.exit();
    }

    public void login(ActionEvent event) throws IOException {


        User currentUser = new SQLMethods().getUserFromDB(usernameField.getText(), passwordField.getText(), 1);
        if(currentUser == null){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.initStyle(StageStyle.UNDECORATED);
            errorAlert.setHeaderText(null);
            errorAlert.setGraphic(null);
            errorAlert.setContentText("Incorrect username or password");
            errorAlert.showAndWait();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/homeScreen/homeScreen.fxml"));
        Parent paramsView = loader.load();


        HomeScreenController controller = loader.getController();
        controller.setCurrentUser(currentUser);
        controller.setLoginController(this);

        Scene newScene = new Scene(paramsView);
        Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
        window.setScene(newScene);
        window.show();
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
}
