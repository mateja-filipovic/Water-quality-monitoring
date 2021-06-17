package userLogin;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.User;
import userHomeScreen.UserHomescreenController;
import util.SQLMethods;
import util.UserControllerInterface;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserLoginController implements Initializable, UserControllerInterface {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    private UserHomescreenController userHomescreenController;


    public void onClickLogin(ActionEvent event) throws IOException {
        // get user from db
        User currentUser = new SQLMethods().getUserFromDB(usernameField.getText(), passwordField.getText(), 0);
        if(currentUser == null){
            displayErrorNotif();
            return;
        }

        // pull the fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/userHomeScreen/userhomescreen.fxml"));
        Parent paramsView = loader.load();

        UserHomescreenController userHomescreenController = loader.getController();
        userHomescreenController.setCurrentUser(currentUser);

        // load the scene
        Scene newScene = new Scene(paramsView);
        Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
        window.setScene(newScene);
        window.show();
    }

    // load the register scene
    public void onClickRegister(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/userRegister/userRegister.fxml"));
        Parent paramsView = loader.load();
        Scene newScene = new Scene(paramsView);
        Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
        window.setScene(newScene);
        window.show();
    }

    public void exitApp(ActionEvent event) {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setHomeScreenController(UserHomescreenController userHomescreenController) {
        this.userHomescreenController = userHomescreenController;
    }

    @Override
    public void updateView() {

    }

    private void displayErrorNotif(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.initStyle(StageStyle.UNDECORATED);
        errorAlert.setHeaderText(null);
        errorAlert.setGraphic(null);
        errorAlert.setContentText("Incorrect username or password");
        errorAlert.showAndWait();
    }
}
