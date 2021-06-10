package paramsScreen;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ParamsController implements Initializable {

    @FXML public Label ammoniaLabel;
    @FXML public Label doLabel;
    @FXML public Label orpLabel;
    @FXML public Label turbidityLabel;
    @FXML public BarChart<?, ?> phChart;
    @FXML public CategoryAxis phChartX;
    @FXML public NumberAxis phChartY;

    public void exitApp(){
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // dummy values
        double amonia = 10.2;
        double disslovedoxy = 5.1;
        double turbidity = 3.3;
        double orp = 1.1;
        // dummy values

        orpLabel.setText(Double.toString(orp));
        doLabel.setText(Double.toString(disslovedoxy));
        turbidityLabel.setText(Double.toString(turbidity));
        ammoniaLabel.setText(Double.toString(amonia));

        // dummy chart values
        XYChart.Series set1 = new XYChart.Series<>();
        set1.getData().add(new XYChart.Data<>("0", 7));
        set1.getData().add(new XYChart.Data<>("1", 5));
        set1.getData().add(new XYChart.Data<>("2", 11));
        set1.getData().add(new XYChart.Data<>("3", 3));
        set1.getData().add(new XYChart.Data<>("4", 8));
        set1.getData().add(new XYChart.Data<>("5", 7.5));
        set1.getData().add(new XYChart.Data<>("6", 4));
        // dummy chart values

        phChart.getData().addAll(set1);
        phChart.setLegendVisible(false);
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
}
