package ua.family.forfamily;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MonthResult{

    private static String currentMonth;

    private static String currentYear;

    @FXML
    private Button BackButton;

    @FXML
    private BarChart<String, Number> MonthGraph;

    @FXML
    private Label PageLabel;

    @FXML
    private Label CostsLabel;

    @FXML
    private Label GainLabel;

    void setCurrentMonth(String month){
        currentMonth = month;
    }

    void setCurrentYear(String year){
        currentYear = year;
    }

    String getCurrentMonth(){
        return currentMonth;
    }

    String getCurrentYear(){
        return currentYear;
    }

    @FXML
    void openHomeScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Головна сторінка");
        stage.show();

    }
    @FXML
    void initialize(){

        BackButton.setOnAction(actionEvent -> {
            BackButton.getScene().getWindow().hide();
            try {
                openHomeScene(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        PageLabel.setText(currentMonth);
        JSONParse jp = new JSONParse();


        MonthGraph.getData().add(jp.dataSeries(currentMonth, "Husband", currentYear));
        MonthGraph.getData().add(jp.dataSeries(currentMonth, "Wife", currentYear));

        GainLabel.setText("Зароблено: " + jp.getTotalGain());
        CostsLabel.setText("Витрачено: " + jp.getTotalCost());


    }
}
