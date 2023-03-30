module com.example.filemanipulator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.filemanipulator to javafx.fxml;
    exports com.example.filemanipulator;
}