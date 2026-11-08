module cl.duoc.biblioteca {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires com.google.gson;

    opens cl.duoc.biblioteca.model to com.google.gson, javafx.base;
    opens cl.duoc.biblioteca.controller to javafx.fxml;

    exports cl.duoc.biblioteca;
}
