package ua.family.forfamily;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class HusbandPageController extends MainController {

    @FXML
    private Button CostsButtonH;

    @FXML
    private TextField CostsFieldH;

    @FXML
    private ComboBox<String> CostBoxH;

    @FXML
    private Button GainButtonH;

    @FXML
    private Button BackButtonH;

    @FXML
    private TextField GainFieldH;


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

        CostBoxH.setItems(costs);

        BackButtonH.setOnAction(actionEvent -> {
            BackButtonH.getScene().getWindow().hide();
            try {
                openHomeScene(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        GainButtonH.setOnAction(actionEvent -> {
            if(isNumber(GainFieldH.getText())) {
                JSONParse obj = new JSONParse();
                setAmount(GainFieldH.getText());
                obj.addMoney(GainFieldH.getText(), getAmount(), currentData, "Husband");

                GainFieldH.clear();

                showAmount();
            }
        });

        CostsButtonH.setOnAction(actionEvent -> {
            if(isNumber(CostsFieldH.getText())){
                if(CostBoxH.getValue() != null) {
                    JSONParse obj = new JSONParse();
                    setAmount("-" + CostsFieldH.getText());
                    obj.takeMoney(CostsFieldH.getText(), getAmount(), currentData, "Husband", CostBoxH.getValue());

                    CostsFieldH.clear();

                    showAmount();
                }
            }
        });
    }
}
