package ua.family.forfamily;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;



public class MainController {

    protected ObservableList<String> costs = FXCollections.observableArrayList("Продукти", "Комуналка", "Одяг", "Медикаменти", "Всяка всячина");

    protected ObservableList<String> years = FXCollections.observableArrayList("2023", "2024","2025","2026","2027","2028","2029","2030","2031","2032","2033");

    protected String currentData = LocalDateTime.now().getYear() + " " + LocalDateTime.now().getMonth()  + " " + LocalDateTime.now().getDayOfMonth();

    private static String amount = "0";

    @FXML
    private Label AmountMoney;

    @FXML
    private Button isHusband;

    @FXML
    private Button isWife;

    @FXML
    private Button StatisticButton;


    public void showAmount(){
        JSONParse parse = new JSONParse();
        if(parse.AmountRead() != null){
            amount = parse.AmountRead();
        }
        AmountMoney.setText("От скільки грошей у вас зараз є: " + amount);
    }

    public String getAmount(){
        return amount;
    }

    public void setAmount(String gain){
        int temp = Integer.parseInt(amount) + Integer.parseInt(gain);
        amount = String.valueOf(temp);
    }

    @FXML
    void initialize() {

        showAmount();

        isHusband.setOnAction(actionEvent -> {
            isHusband.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("husbandPage.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Його сторінка");
            stage.showAndWait();
        });

        isWife.setOnAction(actionEvent -> {
            isWife.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("wifePage.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Її сторінка");
            stage.showAndWait();
        });

        StatisticButton.setOnAction(actionEvent -> {
            StatisticButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("showResults.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Ось шо вийшло");
            stage.show();
        });
        }

        public boolean isNumber(String str){
            try{
                Integer.parseInt(str);
                return true;
            }catch (NumberFormatException e){
                return false;
            }
        }

}