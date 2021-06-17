package userAboutUs;

import homeScreen.HomeScreenController;
import javafx.application.Platform;
import userHomeScreen.UserHomescreenController;
import util.UserControllerInterface;

public class AboutUsController implements UserControllerInterface {

    private HomeScreenController homeScreenController;

    @Override
    public void setHomeScreenController(UserHomescreenController userHomescreenController) {
        this.homeScreenController = homeScreenController;
    }

    @Override
    public void updateView() {
        // this view doesnt need updating
    }

    public void exitApp(){
        Platform.exit();
    }
}
