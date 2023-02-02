package ua.family.forfamily;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class WifePageController extends MainController {

    @FXML
    private Label AmountMoney;

    @FXML
    private Button CostsButtonW;

    @FXML
    private TextField CostsFieldW;

    @FXML
    private ComboBox<String> CostBoxW;

    @FXML
    private Button GainButtonW;

    @FXML
    private Button BackButtonW;

    @FXML
    private TextField GainFieldW;

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
        showAmount();

        CostBoxW.setItems(costs);

        BackButtonW.setOnAction(actionEvent -> {
            BackButtonW.getScene().getWindow().hide();
            try {
                openHomeScene(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        GainButtonW.setOnAction(actionEvent -> {
            if(isNumber(GainFieldW.getText())) {
                JSONParse obj = new JSONParse();
                setAmount(GainFieldW.getText());
                obj.addMoney(GainFieldW.getText(), getAmount(), currentData, "Wife");
                GainFieldW.clear();

                showAmount();
            }
        });

        CostsButtonW.setOnAction(actionEvent -> {
            if(isNumber(CostsFieldW.getText())){
                if(CostBoxW.getValue() != null) {
                    JSONParse obj = new JSONParse();
                    setAmount("-" + CostsFieldW.getText());
                    obj.takeMoney(CostsFieldW.getText(), getAmount(), currentData, "Wife", CostBoxW.getValue());
                    CostsFieldW.clear();

                    showAmount();
                }
            }
        });

    }

}
