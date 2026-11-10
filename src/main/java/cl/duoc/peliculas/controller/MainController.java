package cl.duoc.peliculas.controller;

import cl.duoc.peliculas.model.Funcion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class MainController {

    @FXML private ComboBox<String> cmbTipo;
    @FXML private TextField txtPelicula;
    @FXML private TextField txtDuracion;
    @FXML private TextField txtEntradas;

    @FXML private HBox boxRegular;
    @FXML private TextField txtSala;
    @FXML private CheckBox chk3D;

    @FXML private HBox boxPopular;
    @FXML private TextField txtDistribuidora;
    @FXML private TextField txtDias;
    @FXML private CheckBox chkReembolsada;

    @FXML private TableView<Funcion> tblFunciones;
    @FXML private TableColumn<Funcion, String> colTipo;
    @FXML private TableColumn<Funcion, String> colPelicula;
    @FXML private TableColumn<Funcion, Integer> colDuracion;
    @FXML private TableColumn<Funcion, Integer> colEntradas;
    @FXML private TableColumn<Funcion, String> colPrecio;

    @FXML private Label lblMensaje;

    @FXML
    public void onRegistrar(ActionEvent event) {

    }

    @FXML
    public void onEliminar(ActionEvent event) {

    }

}
