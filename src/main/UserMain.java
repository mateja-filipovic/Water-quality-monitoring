package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UserMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //smoothing za fontove
        System.setProperty("prism.lcdtext", "false");
        System.setProperty("prism.text", "t2k");
        //izbaci windows bordere
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("/userLogin/userlogin.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 375, 667));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
