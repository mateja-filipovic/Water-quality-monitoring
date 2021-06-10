package actionsScreen;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.User;

import java.net.URL;
import java.util.ResourceBundle;

public class ActionsController implements Initializable {
    @FXML public TextField nameField;
    @FXML public TextField locationField;
    @FXML public TextField timeField;
    @FXML public ListView<String> actionListview;
    @FXML public TableView<User> applicationsTableview;
    @FXML public TableColumn<User, String> nameColumn;
    @FXML public TableColumn<User, String> lastNameColumn;
    @FXML public TableColumn<User, String> contactColumn;
    private String currentAction;

    public void exitApp(ActionEvent actionEvent) {
        Platform.exit();
    }

    private ObservableList<User> userList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] dummyData = {"ocistimo dunav", "ocistimo drinu", "zelena morava", "ocistimo dunav", "ocistimo drinu", "zelena morava", "ocistimo dunav", "ocistimo drinu", "zelena morava"};
        actionListview.getItems().addAll(dummyData);
        actionListview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                currentAction = actionListview.getSelectionModel().getSelectedItem();
                //todo ovde!
            }
        });

        userList.add(new User("Mateja", "Filipovic", "mata", "admin", "email@gmail.com", 1));
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        applicationsTableview.setItems(userList);
    }

    public void createEntry(ActionEvent event) {
        String name = nameField.getText();
        String location = locationField.getText();
        String time = timeField.getText();
        //radi povezivanje
    }
}
