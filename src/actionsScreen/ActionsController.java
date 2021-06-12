package actionsScreen;

import homeScreen.HomeScreenController;
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
import models.WorkAction;
import models.WorkApplication;
import util.ControllerObserver;
import util.SQLMethods;

import java.net.URL;
import java.util.*;

public class ActionsController implements Initializable, ControllerObserver {
    @FXML public TextField nameField;
    @FXML public TextField locationField;
    @FXML public TextField timeField;
    @FXML public ListView<String> actionListview;
    @FXML public TableView<WorkApplication> applicationsTableview;
    @FXML public TableColumn<WorkApplication, String> nameColumn;
    @FXML public TableColumn<WorkApplication, String> lastNameColumn;
    @FXML public TableColumn<WorkApplication, String> contactColumn;
    private WorkAction currentAction;
    private HomeScreenController homeScreenController;
    private SQLMethods sqlMethods = new SQLMethods();

    private Map<String, WorkAction> workActionMap = new HashMap<>();
    private List<WorkApplication> workApplicationList;

    public void exitApp(ActionEvent actionEvent) {
        Platform.exit();
    }

    private ObservableList<User> userList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
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
        applicationsTableview.setItems(userList);*/

    }

    public void createEntry(ActionEvent event) {
        String name = nameField.getText();
        String location = locationField.getText();
        String time = timeField.getText();
        sqlMethods.createWorkAction(name, location, time, this.homeScreenController.getCurrentUser().getId());
    }

    @Override
    public void setHomeScreenController(HomeScreenController homeScreenController) {
        this.homeScreenController = homeScreenController;
        updateView();
    }

    @Override
    public void updateView() {
        List<WorkAction> temp = sqlMethods.getAllWorkActions();

        for(WorkAction workAction : temp)
            workActionMap.put(workAction.getName(), workAction);

        actionListview.getItems().addAll(workActionMap.keySet());
        if(currentAction == null)
            currentAction = temp.get(0);

        workApplicationList = sqlMethods.getWorkApplications(currentAction.getId());
        nameColumn.setCellValueFactory(new PropertyValueFactory<WorkApplication, String>("name"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<WorkApplication, String>("lastName"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<WorkApplication, String>("email"));
        applicationsTableview.getItems().addAll(workApplicationList);
    }
}
