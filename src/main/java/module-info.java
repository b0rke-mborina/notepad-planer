module notepadplaner.notepadplaner {
    requires javafx.controls;
    requires javafx.fxml;


    opens notepadplaner.notepadplaner to javafx.fxml;
    exports notepadplaner.notepadplaner;
    exports notepadplaner.controllers;
    opens notepadplaner.controllers to javafx.fxml;
    exports notepadplaner;
    opens notepadplaner to javafx.fxml;
}