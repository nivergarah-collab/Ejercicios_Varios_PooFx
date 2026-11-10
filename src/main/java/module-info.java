module cl.duoc.vivero {
    requires com.google.gson;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;

    opens cl.duoc.vivero.model to com.google.gson;
    opens cl.duoc.vivero.controller to javafx.fxml;

    exports cl.duoc.vivero;
}