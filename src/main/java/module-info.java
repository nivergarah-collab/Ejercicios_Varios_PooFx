module cl.duoc.veterinaria {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires com.google.gson;

    opens cl.duoc.veterinaria.model to com.google.gson, javafx.base;
    opens cl.duoc.veterinaria.controller to javafx.fxml;

    exports cl.duoc.veterinaria;
}
