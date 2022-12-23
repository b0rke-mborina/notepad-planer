module notepadplaner.notepadplaner {
    requires javafx.controls;
    requires javafx.fxml;


    opens notepadplaner.notepadplaner to javafx.fxml;
    exports notepadplaner.notepadplaner;
}