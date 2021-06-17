package loginScreen;

import homeScreen.HomeScreenController;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import main.Main;
import models.User;
import simulation.Simulation;
import util.SQLMethods;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML public TextField usernameField;
    @FXML public PasswordField passwordField;

    private Simulation simulation;

    public void exitApp(){
        if(simulation != null)
            simulation.terminate();
        Platform.exit();
    }

    public void login(ActionEvent event) throws IOException {
        // get the user from db
        User currentUser = new SQLMethods().getUserFromDB(usernameField.getText(), passwordField.getText(), 1);
        // if there is no such user, display error notification
        if(currentUser == null){
            displayErrorNotif();
            return;
        }

        // load the fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/homeScreen/homeScreen.fxml"));
        Parent paramsView = loader.load();

        // set the current user
        HomeScreenController controller = loader.getController();
        controller.setCurrentUser(currentUser);
        controller.setLoginController(this);

        // add the screen to the stage
        Scene newScene = new Scene(paramsView);
        Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
        window.setScene(newScene);

        window.show();
    }

    private void displayErrorNotif(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.initStyle(StageStyle.UNDECORATED);
        errorAlert.setHeaderText(null);
        errorAlert.setGraphic(null);
        errorAlert.setContentText("Incorrect username or password");
        errorAlert.showAndWait();
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
