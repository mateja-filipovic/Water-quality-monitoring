package devicesScreen;

import javafx.application.Platform;
import javafx.event.ActionEvent;

public class DevicesController {

    public void exitApp(ActionEvent actionEvent) {
        Platform.exit();
    }
}
