module cl.duoc.turnos {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires com.google.gson;

    opens cl.duoc.turnos.model to com.google.gson, javafx.base;
    opens cl.duoc.turnos.controller to javafx.fxml;

    exports cl.duoc.turnos;
}
