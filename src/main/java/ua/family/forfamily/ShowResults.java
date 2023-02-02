package ua.family.forfamily;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class ShowResults  extends MainController{

    @FXML
    private Button AprilButton;

    @FXML
    private Button AugustButton;

    @FXML
    private Button DecemberButton;

    @FXML
    private Button FebruaryButton;

    @FXML
    private Button JanuaryButton;

    @FXML
    private Button JulyButton;

    @FXML
    private Button JuneButton;

    @FXML
    private Button MarchButton;

    @FXML
    private Button MayButton;

    @FXML
    private Button NovemberButton;

    @FXML
    private Button OctoberButton;

    @FXML
    private Button SeptemberButton;

    @FXML
    private Button BackButton;

    @FXML
    private Label GraphLabel;

    @FXML
    private PieChart CurrentGraph;

    @FXML
    private ComboBox<String> YearBox;



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
    void initialize() {
        JSONParse jp = new JSONParse();
        MonthResult mr = new MonthResult();

        YearBox.setItems(years);

        BackButton.setOnAction(actionEvent -> {
            BackButton.getScene().getWindow().hide();
            try {
                openHomeScene(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        JanuaryButton.setOnAction(actionEvent -> {
            if (jp.isJSONContains("JANUARY", YearBox.getValue())) {
                JanuaryButton.getScene().getWindow().hide();
                mr.setCurrentMonth("JANUARY");
                mr.setCurrentYear(YearBox.getValue());
                showDetailedGraph();
            }
        });

        FebruaryButton.setOnAction(actionEvent -> {
            if (jp.isJSONContains("FEBRUARY", YearBox.getValue())) {
                FebruaryButton.getScene().getWindow().hide();
                mr.setCurrentMonth("FEBRUARY");
                mr.setCurrentYear(YearBox.getValue());
                showDetailedGraph();
            }
        });

        MarchButton.setOnAction(actionEvent -> {
            if (jp.isJSONContains("MARCH", YearBox.getValue())) {
                MarchButton.getScene().getWindow().hide();
                mr.setCurrentMonth("MARCH");
                mr.setCurrentYear(YearBox.getValue());
                showDetailedGraph();
            }
        });

        AprilButton.setOnAction(actionEvent -> {
            if (jp.isJSONContains("APRIL", YearBox.getValue())) {
                AprilButton.getScene().getWindow().hide();
                mr.setCurrentMonth("APRIL");
                mr.setCurrentYear(YearBox.getValue());
                showDetailedGraph();
            }
        });

        MayButton.setOnAction(actionEvent -> {
            if (jp.isJSONContains("MAY", YearBox.getValue())) {
                MayButton.getScene().getWindow().hide();
                mr.setCurrentMonth("MAY");
                mr.setCurrentYear(YearBox.getValue());
                showDetailedGraph();
            }
        });

        JuneButton.setOnAction(actionEvent -> {
            if (jp.isJSONContains("JUNE", YearBox.getValue())) {
                JuneButton.getScene().getWindow().hide();
                mr.setCurrentMonth("JUNE");
                mr.setCurrentYear(YearBox.getValue());
                showDetailedGraph();
            }
        });

        JulyButton.setOnAction(actionEvent -> {
            if (jp.isJSONContains("JULY", YearBox.getValue())) {
                JulyButton.getScene().getWindow().hide();
                mr.setCurrentMonth("JULY");
                mr.setCurrentYear(YearBox.getValue());
                showDetailedGraph();
            }
        });

        AugustButton.setOnAction(actionEvent -> {
            if (jp.isJSONContains("AUGUST", YearBox.getValue())) {
                AugustButton.getScene().getWindow().hide();
                mr.setCurrentMonth("AUGUST");
                mr.setCurrentYear(YearBox.getValue());
                showDetailedGraph();
            }
        });

        SeptemberButton.setOnAction(actionEvent -> {
            if (jp.isJSONContains("SEPTEMBER", YearBox.getValue())) {
                SeptemberButton.getScene().getWindow().hide();
                mr.setCurrentMonth("SEPTEMBER");
                mr.setCurrentYear(YearBox.getValue());
                showDetailedGraph();
            }
        });

        OctoberButton.setOnAction(actionEvent -> {
            if (jp.isJSONContains("OCTOBER", YearBox.getValue())) {
                OctoberButton.getScene().getWindow().hide();
                mr.setCurrentMonth("OCTOBER");
                mr.setCurrentYear(YearBox.getValue());
                showDetailedGraph();
            }
        });

        NovemberButton.setOnAction(actionEvent -> {
            if (jp.isJSONContains("NOVEMBER", YearBox.getValue())) {
                NovemberButton.getScene().getWindow().hide();
                mr.setCurrentMonth("NOVEMBER");
                mr.setCurrentYear(YearBox.getValue());
                showDetailedGraph();
            }
        });

        DecemberButton.setOnAction(actionEvent -> {
            if (jp.isJSONContains("DECEMBER", YearBox.getValue())) {
                DecemberButton.getScene().getWindow().hide();
                mr.setCurrentMonth("DECEMBER");
                mr.setCurrentYear(YearBox.getValue());
                showDetailedGraph();
            }
        });


        JSONParse jsonParse = new JSONParse();
        PieChart.Data slice1 = new PieChart.Data("Доходи", Double.parseDouble(jsonParse.getGain()));
        PieChart.Data slice2 = new PieChart.Data("Витрати", Double.parseDouble(jsonParse.getCosts()));


        CurrentGraph.getData().add(slice1);
        CurrentGraph.getData().add(slice2);
    }


    @FXML
    void showDetailedGraph(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("monthResult.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("От шо вийшло за місяць");
        stage.showAndWait();

    }
}

