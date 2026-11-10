package cl.duoc.agenda.controller;

import cl.duoc.agenda.model.Compromiso;
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
    @FXML private TextField txtTitulo;
    @FXML private TextField txtDuracion;
    @FXML private TextField txtAsistentes;

    @FXML private HBox boxReunion;
    @FXML private TextField txtOrganizador;
    @FXML private CheckBox chkVirtual;

    @FXML private HBox boxEvento;
    @FXML private TextField txtLugar;
    @FXML private TextField txtAnticipacion;
    @FXML private CheckBox chkConfirmado;

    @FXML private TableView<Compromiso> tblCompromisos;
    @FXML private TableColumn<Compromiso, String> colTipo;
    @FXML private TableColumn<Compromiso, String> colTitulo;
    @FXML private TableColumn<Compromiso, Integer> colDuracion;
    @FXML private TableColumn<Compromiso, Integer> colAsistentes;
    @FXML private TableColumn<Compromiso, String> colCosto;

    @FXML private Label lblMensaje;

    @FXML
    public void onRegistrar(ActionEvent event) {

    }

    @FXML
    public void onEliminar(ActionEvent event) {

    }

}
