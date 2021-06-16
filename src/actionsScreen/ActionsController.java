package actionsScreen;

import homeScreen.HomeScreenController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import models.User;
import models.WorkAction;
import models.WorkApplication;
import util.ControllerInterface;
import util.SQLMethods;

import java.net.URL;
import java.util.*;

public class ActionsController implements Initializable, ControllerInterface {

    // entry creation inputs
    @FXML private TextField nameField;
    @FXML private TextField locationField;
    @FXML private TextField timeField;

    //WorkAction list
    @FXML private ListView<String> actionListview;

    //table
    @FXML private TableView<WorkApplication> applicationsTableview;
    @FXML private TableColumn<WorkApplication, String> nameColumn;
    @FXML private TableColumn<WorkApplication, String> lastNameColumn;
    @FXML private TableColumn<WorkApplication, String> contactColumn;

    private HomeScreenController homeScreenController; // to get the current user

    private WorkAction currentAction; // to load applications for this action

    private SQLMethods sqlMethods = new SQLMethods(); //db connection

    private Map<String, WorkAction> workActionMap = new HashMap<>(); // map action name to the object

    private List<WorkApplication> workApplicationList; // populating the Table

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //todo
    }

    public void changeSelection(ActionEvent e){
        currentAction = workActionMap.get(actionListview.getSelectionModel().getSelectedItem());
        updateView();
    }

    // create new WorkAction
    public void createEntry(ActionEvent event) {
        String name = nameField.getText();
        String location = locationField.getText();
        String time = timeField.getText();
        // check if there are empty fields
        if(name.equals("") || location.equals("") || time.equals("")){
            errorAlert();
            return;
        }
        sqlMethods.createWorkAction(name, location, time, this.homeScreenController.getCurrentUser().getId());
        updateView();
    }

    // all fields required notification
    private void errorAlert() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.initStyle(StageStyle.UNDECORATED);
        errorAlert.setHeaderText(null);
        errorAlert.setGraphic(null);
        errorAlert.setContentText("All fields required");
        errorAlert.showAndWait();
    }


    @Override
    public void setHomeScreenController(HomeScreenController homeScreenController) {
        this.homeScreenController = homeScreenController;
        updateView();
    }

    @Override
    public void updateView() {

        actionListview.getItems().clear();

        //get WorkActions from db
        List<WorkAction> temp = sqlMethods.getAllWorkActions();
        for(WorkAction workAction : temp)
            workActionMap.put(workAction.getName(), workAction);
        // list must be populated with strings!
        actionListview.getItems().addAll(workActionMap.keySet());

        if(currentAction == null)
            currentAction = temp.get(0);

        // get WorkApplications for the current WorkAction
        workApplicationList = sqlMethods.getWorkApplications(currentAction.getId());
        nameColumn.setCellValueFactory(new PropertyValueFactory<WorkApplication, String>("name"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<WorkApplication, String>("lastName"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<WorkApplication, String>("email"));

        // populate the gui
        Platform.runLater(() -> {
            ObservableList<WorkApplication> comboList = applicationsTableview.getItems();
            comboList.setAll(FXCollections.observableArrayList(workApplicationList));
        });

        //applicationsTableview.getItems().addAll(workApplicationList);
    }

    public void exitApp(ActionEvent actionEvent) {
        this.homeScreenController.getSimulation().terminate();
        Platform.exit();
    }
}
