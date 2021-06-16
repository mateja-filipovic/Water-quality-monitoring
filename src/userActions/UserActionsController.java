package userActions;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.WorkAction;
import userHomeScreen.UserHomescreenController;
import util.SQLMethods;
import util.UserControllerObserver;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserActionsController implements Initializable, UserControllerObserver {

    @FXML private ListView<WorkAction> actionListview;
    @FXML private Label nameLabel;
    @FXML private Label locationLabel;
    @FXML private Label dateLabel;
    @FXML private Button applyButton;
    private List<WorkAction> allWorkActions;
    private List<WorkAction> currentUserActions;
    private UserHomescreenController userHomescreenController;
    private SQLMethods sqlMethods = new SQLMethods();
    private WorkAction currentAction;
    private int applyLeaveFlag;

    public void changeSelection(ActionEvent event) {
        currentAction = actionListview.getSelectionModel().getSelectedItem();

        nameLabel.setText(currentAction.getName());
        locationLabel.setText(currentAction.getLocation());
        dateLabel.setText(currentAction.getTime());

        if(!currentUserActions.contains(currentAction)){
            applyButton.setText("APPLY");
            applyLeaveFlag = 1;
        }
        else{
            applyButton.setText("LEAVE");
            applyLeaveFlag = -1;
        }
    }

    public void exitApp(){
        Platform.exit();
    }

    public void onClickActionInfoBtn(ActionEvent e){
        if(applyLeaveFlag == 1)
            sqlMethods.insertWorkApplication(currentAction.getId(), userHomescreenController.getCurrentUser().getId());
        else
            sqlMethods.deleteWorkApplication(currentAction.getId(), userHomescreenController.getCurrentUser().getId());
        applyLeaveFlag = -applyLeaveFlag;
        updateView();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void setHomeScreenController(UserHomescreenController userHomescreenController) {
        this.userHomescreenController = userHomescreenController;
        updateView();
    }

    @Override
    public void updateView() {
        loadAllActions();
        loadUserActions();
        actionListview.getItems().clear();
        actionListview.getItems().addAll(allWorkActions);
        actionListview.getSelectionModel().selectFirst();
        if(currentAction == null)
            currentAction = allWorkActions.get(0);

        nameLabel.setText(currentAction.getName());
        locationLabel.setText(currentAction.getLocation());
        dateLabel.setText(currentAction.getTime());

        if(!currentUserActions.contains(currentAction)){
            applyButton.setText("APPLY");
            applyLeaveFlag = 1;
        }
        else{
            applyButton.setText("LEAVE");
            applyLeaveFlag = -1;
        }
    }

    private void loadAllActions(){
        allWorkActions = sqlMethods.getAllWorkActions();
    }

    private void loadUserActions(){
        currentUserActions = sqlMethods.getAllWorkActionsForUser(userHomescreenController.getCurrentUser().getId());
    }
}
