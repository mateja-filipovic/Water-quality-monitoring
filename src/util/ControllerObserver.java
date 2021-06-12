package util;

import homeScreen.HomeScreenController;

public interface ControllerObserver {

    void setHomeScreenController(HomeScreenController homeScreenController);
    void updateView();
}
