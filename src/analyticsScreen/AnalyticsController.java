package analyticsScreen;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class AnalyticsController implements Initializable {

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

    @FXML public BarChart<?, ?> ammoniaChart;
    @FXML public CategoryAxis ammoniaChartX;
    @FXML public NumberAxis ammoniaChartY;

    public void exitApp(){
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        doChart.setLegendVisible(false);
    }
}
