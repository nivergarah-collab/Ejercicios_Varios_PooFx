module cl.duoc.playlist {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires com.google.gson;

    opens cl.duoc.playlist.model to com.google.gson, javafx.base;
    opens cl.duoc.playlist.controller to javafx.fxml;

    exports cl.duoc.playlist;
}
