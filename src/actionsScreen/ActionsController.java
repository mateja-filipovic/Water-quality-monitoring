package actionsScreen;

import homeScreen.HomeScreenController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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
        this.homeScreenController.getSimulation().terminate();
        Platform.exit();
    }

    private ObservableList<User> userList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        actionListview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                currentAction = workActionMap.get(actionListview.getSelectionModel().getSelectedItem());
                updateView();
            }
        });

    }

    public void createEntry(ActionEvent event) {
        String name = nameField.getText();
        String location = locationField.getText();
        String time = timeField.getText();
        sqlMethods.createWorkAction(name, location, time, this.homeScreenController.getCurrentUser().getId());
        updateView();
    }

    @Override
    public void setHomeScreenController(HomeScreenController homeScreenController) {
        this.homeScreenController = homeScreenController;
        updateView();
    }

    @Override
    public void updateView() {

        actionListview.getItems().clear();

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
        Platform.runLater(() -> {
            ObservableList<WorkApplication> comboList = applicationsTableview.getItems();
            comboList.setAll(FXCollections.observableArrayList(workApplicationList));
        });
        //applicationsTableview.getItems().addAll(workApplicationList);
    }
}
