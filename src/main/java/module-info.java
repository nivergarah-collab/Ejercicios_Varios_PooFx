module cl.duoc.biblioteca {
    requires com.google.gson;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;

    opens cl.duoc.biblioteca.model to com.google.gson;
    opens cl.duoc.biblioteca.controller to javafx.fxml;
    exports cl.duoc.biblioteca;
}