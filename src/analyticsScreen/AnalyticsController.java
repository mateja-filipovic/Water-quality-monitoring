package analyticsScreen;

import homeScreen.HomeScreenController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import util.ControllerObserver;
import util.SQLMethods;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AnalyticsController implements Initializable, ControllerObserver {

    @FXML public BarChart<?, ?> phChart;
    @FXML public CategoryAxis phChartX;
    @FXML public NumberAxis phChartY;

    @FXML public BarChart<?, ?> turbidityChart;
    @FXML public CategoryAxis turbidityChartX;
    @FXML public NumberAxis turbidityChartY;

    @FXML public BarChart<?, ?> doChart;
    @FXML public CategoryAxis doChartX;
    @FXML public NumberAxis doChartY;

    @FXML public BarChart<?, ?> orpChart;
    @FXML public CategoryAxis orpChartX;
    @FXML public NumberAxis orpChartY;

    @FXML
    public BarChart<?, ?> ammoniaChart;
    @FXML public CategoryAxis ammoniaChartX;
    @FXML public NumberAxis ammoniaChartY;

    private HomeScreenController homeScreenController;
    private SQLMethods dbController;
    private List<List<Double>> paramsMatrix;

    private BarChart<?, ?>[] allCharts = {phChart, turbidityChart, doChart, ammoniaChart, orpChart};

    public void exitApp(){
        this.homeScreenController.getSimulation().terminate();
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
        XYChart.Series set1 = new XYChart.Series<>();
        set1.getData().add(new XYChart.Data<>("0", 7));
        set1.getData().add(new XYChart.Data<>("1", 5));
        set1.getData().add(new XYChart.Data<>("2", 11));
        set1.getData().add(new XYChart.Data<>("3", 3));
        set1.getData().add(new XYChart.Data<>("4", 8));
        set1.getData().add(new XYChart.Data<>("5", 7.5));
        set1.getData().add(new XYChart.Data<>("6", 4));
        set1.getData().add(new XYChart.Data<>("7", 11.1));
        set1.getData().add(new XYChart.Data<>("8", 9));
        set1.getData().add(new XYChart.Data<>("9", 8));
        doChart.getData().addAll(set1);
        doChart.setLegendVisible(false);*/

    }

    @Override
    public void setHomeScreenController(HomeScreenController homeScreenController) {
        this.homeScreenController = homeScreenController;
        updateView();
    }

    @Override
    public void updateView() {
        XYChart.Series[] sets = new XYChart.Series[5];
        for(int i = 0; i < 5; i++)
            sets[i] = new XYChart.Series<>();

        dbController = new SQLMethods();
        paramsMatrix = dbController.getParams(this.homeScreenController.getCurrentDevice());

        int index = 0;
        for(List<Double> wrapper : paramsMatrix){
            int currentParam = 0;
            for(Double value : wrapper){
                sets[currentParam].getData().add(new XYChart.Data<>(Integer.toString(index - 4), value));
                currentParam++;
            }
            index++;
        }

        BarChart<?, ?>[] allCharts = {phChart, turbidityChart, doChart, ammoniaChart, orpChart};
        for(int i = 0; i < 5; i++){
            allCharts[i].getData().addAll(sets[i]);
            allCharts[i].setLegendVisible(false);
        }

    }
}
