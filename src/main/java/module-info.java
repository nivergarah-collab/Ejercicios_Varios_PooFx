module cl.duoc.torneo {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires com.google.gson;

    opens cl.duoc.torneo.model to com.google.gson, javafx.base;
    opens cl.duoc.torneo.controller to javafx.fxml;

    exports cl.duoc.torneo;
}
