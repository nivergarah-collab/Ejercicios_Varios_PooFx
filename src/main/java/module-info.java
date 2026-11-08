module cl.duoc.peliculas {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires com.google.gson;

    opens cl.duoc.peliculas.model to com.google.gson, javafx.base;
    opens cl.duoc.peliculas.controller to javafx.fxml;

    exports cl.duoc.peliculas;
}
