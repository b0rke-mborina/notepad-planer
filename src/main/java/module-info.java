module notepadplaner {
    requires javafx.controls;
    requires javafx.fxml;


    opens notepadplaner.components;
    opens notepadplaner.controllers to javafx.fxml;
    exports notepadplaner;
    opens notepadplaner to javafx.fxml;
}