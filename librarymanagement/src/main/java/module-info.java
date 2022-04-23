module com.nhom2.librarymanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
//    requires org.junit.jupiter.api;

    opens com.nhom2.librarymanagement to javafx.fxml;
    exports com.nhom2.librarymanagement;
    exports com.nhom2.pojo;
}
