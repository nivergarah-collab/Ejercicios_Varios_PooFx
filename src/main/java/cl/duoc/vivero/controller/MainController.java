package cl.duoc.vivero.controller;

import cl.duoc.vivero.model.Planta;
import cl.duoc.vivero.repository.PersistenciaException;
import cl.duoc.vivero.repository.PlantaRepository;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainController {
    public final static String TIPO_INTERIOR = "Planta interior";
    public final static String TIPO_EXTERIOR = "Planta Exterior";

    public final static String ALERTA_DEFAULT = "Sin alerta";
    public final static String ALERTA_EXITO = "Registro exitoso";
    public final static String ALERTA_NO_VALIDO = "Dato no valido";
    public final static String ALERTA_ELIMINADO = "Registro Eliminado";
    public final static String ALERTA_NO_SELECCIONADO = "Falta de seleccion";
    public final static String ALERTA_LECTURA = "Error al leer archivo";
    public final static String ALERTA_ESCRITURA = "Error al escribir archivo";








    //Builder
    @FXML private Label alertaLabel;

    @FXML private ChoiceBox<String> tipoChoice;
    @FXML private TextField nombreText;
    @FXML private TextField alturaText;
    @FXML private TextField cantidadText;

    @FXML private VBox interiorBox;
    @FXML private TextField volumenText;

    @FXML private VBox exteriorBox;
    @FXML private TextField especieText;
    @FXML private CheckBox resistenteCheck;
    @FXML private CheckBox disponibleCheck;




    @FXML private TableView<Planta> plantaTableView;
    @FXML private TableColumn<Planta, String> tipoColumn;
    @FXML private TableColumn<Planta, String> nombreColumn;
    @FXML private TableColumn<Planta, Integer> alturaColumn;
    @FXML private TableColumn<Planta, Integer> cantidadColumn;
    @FXML private TableColumn<Planta, String> precioColumn;

    //Repo
    private final PlantaRepository repository = new PlantaRepository();
    private final ObservableList<Planta> plantas = FXCollections.observableArrayList();


    @FXML
    public void initialize(){
        this.tipoChoice.getItems().add(TIPO_INTERIOR);
        this.tipoChoice.getItems().add(TIPO_EXTERIOR);
        this.habilitarCamposPorTipo(null);
        //Table view
        this.configurarTableView();
        try {
            this.repository.cargar();
            this.actualizarTableView();
        } catch (PersistenciaException e) {
            this.setAlertaLabelMensaje(e.getMessage(), "Error en carga inicial de datos");
        }
    }

    public void habilitarCamposPorTipo (String tipoPlanta){
        this.interiorBox.setDisable(!TIPO_INTERIOR.equals(tipoPlanta));
        this.exteriorBox.setDisable(!TIPO_EXTERIOR.equals(tipoPlanta));
    }

    @FXML
    public void onSeleccionarTipo(){
        this.habilitarCamposPorTipo(this.tipoChoice.getValue());
    }

    public void configurarTableView(){
        this.tipoColumn.setCellValueFactory(celda -> new ReadOnlyStringWrapper(celda.getValue().getTipo()));
        this.nombreColumn.setCellValueFactory(celda -> new ReadOnlyStringWrapper(celda.getValue().getNombre()));
        this.alturaColumn.setCellValueFactory(celda -> new ReadOnlyObjectWrapper<>(celda.getValue().getAlturaCm()));
        this.cantidadColumn.setCellValueFactory(celda -> new ReadOnlyObjectWrapper<>(celda.getValue().getCantidad()));
        this.precioColumn.setCellValueFactory(celda -> new ReadOnlyStringWrapper(Planta.pesos(celda.getValue().calcularPrecio())));
        this.plantaTableView.setItems(this.plantas);
    }
    public void actualizarTableView(){
        plantas.setAll(repository.listar());
    }
    public void setAlertaLabelMensaje(String tipo, String mensaje){
        if (mensaje.trim().isBlank()){
            mensaje = tipo;
        } else {
            mensaje = tipo + ": " + mensaje;
        }
        switch (tipo){
            case ALERTA_EXITO:
            case ALERTA_ELIMINADO:
                alertaLabel.setTextFill(Color.GREEN);
                alertaLabel.setVisible(true);
                break;
            case ALERTA_NO_VALIDO:
            case ALERTA_NO_SELECCIONADO:
            case ALERTA_ESCRITURA:
            case ALERTA_LECTURA:
                alertaLabel.setTextFill(Color.RED);
                alertaLabel.setVisible(true);
                break;
            default:
                alertaLabel.setTextFill(Color.BLACK);
                alertaLabel.setVisible(false);
        }
        this.alertaLabel.setText(mensaje);
    }

    public void limpiarCampos(){
        this.tipoChoice.setValue(null);
        this.nombreText.setText("");
        this.alturaText.setText("");
        this.cantidadText.setText("");
        this.volumenText.setText("");
        this.especieText.setText("");
        this.resistenteCheck.setSelected(false);
        this.disponibleCheck.setSelected(false);
    }

    @FXML
    public void onLimpiar(ActionEvent event) {
    this.limpiarCampos();
    this.setAlertaLabelMensaje(ALERTA_DEFAULT, "");
    }


    public Integer leerEntero(TextField campo, String nombreCampo) {
        try {
            return Integer.parseInt(campo.getText().trim());
        } catch (NumberFormatException e) {
            this.setAlertaLabelMensaje(ALERTA_NO_VALIDO, "Campo " + nombreCampo + " debe ser un numero entero");
            return null;
    }
    }


    @FXML
    public void onGuardar(ActionEvent event) {
    }



    @FXML
    public void onEliminar(ActionEvent event) {

    }



}
