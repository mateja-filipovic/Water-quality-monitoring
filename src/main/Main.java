package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static Stage getPrimaryStage() {
        return pStage;
    }

    private static Stage pStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // font smoothing
        System.setProperty("prism.lcdtext", "false");
        System.setProperty("prism.text", "t2k");

        // remove default windows borders
        primaryStage.initStyle(StageStyle.UNDECORATED);

        // pull the fxml file
        Parent root = FXMLLoader.load(getClass().getResource("/loginScreen/login.fxml"));
        primaryStage.setTitle("SWAN - Smart Water Network");
        primaryStage.setScene(new Scene(root, 1000, 600));
        pStage = primaryStage;

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
