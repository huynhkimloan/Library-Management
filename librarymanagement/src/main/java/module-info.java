module com.nhom2.librarymanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    
    opens com.nhom2.librarymanagement to javafx.fxml;
    exports com.nhom2.librarymanagement;
}
