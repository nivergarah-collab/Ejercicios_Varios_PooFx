package cl.duoc.playlist.controller;

import cl.duoc.playlist.model.Pista;
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
    @FXML private TextField txtArtista;
    @FXML private TextField txtDuracion;

    @FXML private HBox boxCancion;
    @FXML private TextField txtAlbum;
    @FXML private CheckBox chkEstreno;

    @FXML private HBox boxPodcast;
    @FXML private TextField txtEpisodio;
    @FXML private CheckBox chkSincronizado;

    @FXML private TableView<Pista> tblPistas;
    @FXML private TableColumn<Pista, String> colTipo;
    @FXML private TableColumn<Pista, String> colTitulo;
    @FXML private TableColumn<Pista, String> colArtista;
    @FXML private TableColumn<Pista, Integer> colDuracion;
    @FXML private TableColumn<Pista, String> colLicencia;

    @FXML private Label lblMensaje;

    @FXML
    public void onRegistrar(ActionEvent event) {

    }

    @FXML
    public void onEliminar(ActionEvent event) {

    }

}
