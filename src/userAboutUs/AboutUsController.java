package userAboutUs;

import homeScreen.HomeScreenController;
import javafx.application.Platform;
import userHomeScreen.UserHomescreenController;
import util.UserControllerObserver;

public class AboutUsController implements UserControllerObserver {

    private HomeScreenController homeScreenController;

    @Override
    public void setHomeScreenController(UserHomescreenController userHomescreenController) {
        this.homeScreenController = homeScreenController;
    }

    @Override
    public void updateView() {

    }

    public void exitApp(){
        Platform.exit();
    }
}
