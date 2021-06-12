package paramsScreen;

import homeScreen.HomeScreenController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import simulation.Device;
import util.ControllerObserver;

import javax.sound.midi.ControllerEventListener;
import java.net.URL;
import java.util.ResourceBundle;

public class ParamsController implements Initializable, ControllerObserver {

    @FXML public Label ammoniaLabel;
    @FXML public Label doLabel;
    @FXML public Label orpLabel;
    @FXML public Label turbidityLabel;
    @FXML public BarChart<?, ?> phChart;
    @FXML public CategoryAxis phChartX;
    @FXML public NumberAxis phChartY;
    private HomeScreenController homeScreenController;
    private Device currentDevice;

    public void exitApp(){
        Platform.exit();
        this.homeScreenController.getSimulation().terminate();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
        // dummy values
        double amonia = 10.2;
        double disslovedoxy = 5.1;
        double turbidity = 3.3;
        double orp = 1.1;
        // dummy values
        currentDevice = this.home

        orpLabel.setText(Integer.toString(sonda.getOrp()));
        doLabel.setText(Double.toString(sonda.getO2()));
        turbidityLabel.setText(Double.toString(sonda.getTurbidity()));
        ammoniaLabel.setText(Double.toString(sonda.getNh3()));

        // dummy chart values
        XYChart.Series set1 = new XYChart.Series<>();
        double[] phArr = sonda.getArray();
        set1.getData().add(new XYChart.Data<>("-5", phArr[0]));
        set1.getData().add(new XYChart.Data<>("-4", phArr[1]));
        set1.getData().add(new XYChart.Data<>("-3", phArr[2]));
        set1.getData().add(new XYChart.Data<>("-2", phArr[3]));
        set1.getData().add(new XYChart.Data<>("-1", phArr[4]));
        set1.getData().add(new XYChart.Data<>("0", phArr[5]));
        // dummy chart values

        phChart.getData().addAll(set1);
        phChart.setLegendVisible(false);
        */
        phChart.setAnimated(false);
    }

    public void changeAmmonia(double newValue){
        ammoniaLabel.setText(Double.toString(newValue));
    }
    public void changeDO(double newValue){
        doLabel.setText(Double.toString(newValue));
    }
    public void changeORP(double newValue){
        orpLabel.setText(Double.toString(newValue));
    }
    public void changeTurbidity(double newValue){
        turbidityLabel.setText(Double.toString(newValue));
    }

    @Override
    public void setHomeScreenController(HomeScreenController homeScreenController) {
        this.homeScreenController = homeScreenController;
        viewLoadHelper();
    }

    @Override
    public void updateView() {
        Platform.runLater(() -> {
            viewLoadHelper();
        });
    }

    private void viewLoadHelper(){
        currentDevice  = homeScreenController.getCurrentDevice();
        orpLabel.setText(Integer.toString(currentDevice.getOrp()));
        doLabel.setText(String.format("%.3f", currentDevice.getO2()));
        turbidityLabel.setText(String.format("%.3f", currentDevice.getAvgTur()));
        ammoniaLabel.setText(String.format("%.3f", currentDevice.getNh3()));

        XYChart.Series set1 = new XYChart.Series<>();
        double[] phArr = currentDevice.getArray();
        set1.getData().add(new XYChart.Data<>("-5", phArr[0]));
        set1.getData().add(new XYChart.Data<>("-4", phArr[1]));
        set1.getData().add(new XYChart.Data<>("-3", phArr[2]));
        set1.getData().add(new XYChart.Data<>("-2", phArr[3]));
        set1.getData().add(new XYChart.Data<>("-1", phArr[4]));
        set1.getData().add(new XYChart.Data<>("0", phArr[5]));
        phChart.getData().clear();
        phChart.getData().addAll(set1);
        phChart.setLegendVisible(false);
    }
}
