module ua.family.forfamily {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens ua.family.forfamily to javafx.fxml;
    exports ua.family.forfamily;
}