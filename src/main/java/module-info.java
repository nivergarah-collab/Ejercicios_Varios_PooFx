module cl.duoc.agenda {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires com.google.gson;

    opens cl.duoc.agenda.model to com.google.gson, javafx.base;
    opens cl.duoc.agenda.controller to javafx.fxml;

    exports cl.duoc.agenda;
}
