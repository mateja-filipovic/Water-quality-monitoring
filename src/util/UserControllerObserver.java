package util;

import homeScreen.HomeScreenController;
import userHomeScreen.UserHomescreenController;

public interface UserControllerObserver {

        void setHomeScreenController(UserHomescreenController userHomescreenController);
        void updateView();

}
