module cl.duoc.inventario {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires com.google.gson;

    opens cl.duoc.inventario.model to com.google.gson, javafx.base;
    opens cl.duoc.inventario.controller to javafx.fxml;

    exports cl.duoc.inventario;
}
