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
import util.ControllerInterface;

import java.net.URL;
import java.util.ResourceBundle;

public class ParamsController implements Initializable, ControllerInterface {

    // param labels
    @FXML private Label ammoniaLabel;
    @FXML private Label doLabel;
    @FXML private Label orpLabel;
    @FXML private Label turbidityLabel;

    // ph barchart
    @FXML private BarChart<?, ?> phChart;
    @FXML private CategoryAxis phChartX;
    @FXML private NumberAxis phChartY;

    private HomeScreenController homeScreenController;

    public void exitApp(){
        Platform.exit();
        this.homeScreenController.getSimulation().terminate();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // gui bug fix
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
        // get the device to read params from
        Device currentDevice = homeScreenController.getCurrentDevice();
        // read and display param values
        orpLabel.setText(Integer.toString(currentDevice.getOrp()));
        doLabel.setText(String.format("%.3f", currentDevice.getO2()));
        turbidityLabel.setText(String.format("%.3f", currentDevice.getAvgTur()));
        ammoniaLabel.setText(String.format("%.3f", currentDevice.getNh3()));

        // make a data set for pH chart
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
