package userRegister;

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
import util.SQLMethods;

import java.io.IOException;

public class UserRegisterController {

    @FXML private TextField nameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField lnameField;
    @FXML private TextField usernameField;
    @FXML private TextField emailField;

    private SQLMethods sqlMethods = new SQLMethods();


    public void onClickRegister(ActionEvent event) throws IOException {
        // get the inputs
        String name = nameField.getText();
        String password = passwordField.getText();
        String lname = lnameField.getText();
        String username = usernameField.getText();
        String email = emailField.getText();

        // check for empty inputs
        if(name.equals("") || password.equals("") || lname.equals("") || username.equals("") || email.equals("")){
            displayErrorNotif();
            return;
        }

        sqlMethods.insertUser(name, lname, username, password, email);

        // go back after registering
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/userLogin/userlogin.fxml"));
        Parent paramsView = loader.load();
        Scene newScene = new Scene(paramsView);
        Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
        window.setScene(newScene);
        window.show();
    }

    public void exitApp(){
        Platform.exit();
    }

    private void displayErrorNotif(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.initStyle(StageStyle.UNDECORATED);
        errorAlert.setHeaderText(null);
        errorAlert.setGraphic(null);
        errorAlert.setContentText("All fields required");
        errorAlert.showAndWait();
    }
}
