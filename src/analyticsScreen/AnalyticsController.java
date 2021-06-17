package analyticsScreen;

import homeScreen.HomeScreenController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import util.ControllerInterface;
import util.SQLMethods;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AnalyticsController implements Initializable, ControllerInterface {

    // 5 bar charts - ph, turbidity, dissloved oxy, orp and ammonia conc.

    @FXML private BarChart<?, ?> phChart;
    @FXML private CategoryAxis phChartX;
    @FXML private NumberAxis phChartY;

    @FXML private BarChart<?, ?> turbidityChart;
    @FXML private CategoryAxis turbidityChartX;
    @FXML private NumberAxis turbidityChartY;

    @FXML private BarChart<?, ?> doChart;
    @FXML private CategoryAxis doChartX;
    @FXML private NumberAxis doChartY;

    @FXML private BarChart<?, ?> orpChart;
    @FXML private CategoryAxis orpChartX;
    @FXML private NumberAxis orpChartY;

    @FXML private BarChart<?, ?> ammoniaChart;
    @FXML private CategoryAxis ammoniaChartX;
    @FXML private NumberAxis ammoniaChartY;

    private HomeScreenController homeScreenController; // to get the current user

    private SQLMethods sqlMethods; //db connection

    private List<List<Double>> paramsMatrix;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void setHomeScreenController(HomeScreenController homeScreenController) {
        this.homeScreenController = homeScreenController;
        updateView();
    }

    @Override
    public void updateView() {
        // read params from db
        sqlMethods = new SQLMethods();
        paramsMatrix = sqlMethods.getParams(this.homeScreenController.getCurrentDevice());

        // create data sets
        XYChart.Series[] sets = new XYChart.Series[paramsMatrix.size()];
        for(int i = 0; i < sets.length; i++)
            sets[i] = new XYChart.Series<>();

        // populate data sets
        int index = 0;
        for(List<Double> wrapper : paramsMatrix){
            int currentParam = 0;
            for(Double value : wrapper){
                sets[currentParam].getData().add(new XYChart.Data<>(Integer.toString(index - 4), value));
                currentParam++;
            }
            index++;
        }

        // helper
        BarChart<?, ?>[] allCharts = {phChart, turbidityChart, doChart, ammoniaChart, orpChart};
        // fill the charts with corresponding data sets
        for(int i = 0; i < 5; i++){
            allCharts[i].getData().addAll(sets[i]);
            allCharts[i].setLegendVisible(false);
        }
    }

    public void exitApp(){
        this.homeScreenController.getSimulation().terminate();
        Platform.exit();
    }
}
