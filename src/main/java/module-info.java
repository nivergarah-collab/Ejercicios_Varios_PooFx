module cl.duoc.gastos {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires com.google.gson;

    opens cl.duoc.gastos.model to com.google.gson, javafx.base;
    opens cl.duoc.gastos.controller to javafx.fxml;

    exports cl.duoc.gastos;
}
