package actionsScreen;

import javafx.application.Platform;
import javafx.event.ActionEvent;

public class ActionsController {
    public void exitApp(ActionEvent actionEvent) {
        Platform.exit();
    }
}
