package cl.duoc.vivero.controller;
import cl.duoc.vivero.model.Planta;
import cl.duoc.vivero.model.PlantaExterior;
import cl.duoc.vivero.model.PlantaInterior;
// import cl.duoc.vivero.repository.PersistenciaException;  // se descomenta al crear la clase (paso 11)
// import cl.duoc.vivero.repository.PlantaRepository;       // se descomenta al crear la clase (paso 12)
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;






public class MainController {
    //Paso 01:
    public static final String TIPO_INTERIOR = "Planta Interior";
    public static final String TIPO_EXTERIOR = "Planta Exterior";

    public static final String ALERTA_DEFAULT = "Sin Alerta";
    public static final String ALERTA_EXITO = "Registro Exitoso";
    public static final String ALERTA_NO_VALIDO = "Dato no valido";
    public static final String ALERTA_NO_SELECCIONADO = "Falta de seleccion";
    public static final String ALERTA_ELIMINADO = "Registro eliminado";
    public static final String ALERTA_LECTURA = "Error al leer archivo";
    public static final String ALERTA_ESCRITURA = "Error al escribir archivo";

    //Paso 02:
    @FXML private Label alertaLabel;
    @FXML private TextField alturaText;
    @FXML private TextField cantidadText;
    @FXML private CheckBox disponibleCheck;
    @FXML private TextField especieText;
    @FXML private VBox exteriorBox;
    @FXML private VBox interiorBox;
    @FXML private TextField nombreText;
    @FXML private CheckBox resistenteCheck;
    @FXML private ChoiceBox<String> tipoChoice;
    @FXML private TextField volumenText;


    @FXML private TableView<Planta> plantaTableView;
    @FXML private TableColumn<Planta, String> tipoColumn;
    @FXML private TableColumn<Planta, String> nombreColumn;
    @FXML private TableColumn<Planta, Integer> alturaColumn;
    @FXML private TableColumn<Planta, Integer> cantidadColumn;
    @FXML private TableColumn<Planta, String> precioColumn;

    //Paso 03 + 04:
    @FXML
    public void initialize(){
        this.tipoChoice.getItems().add(TIPO_INTERIOR);
        this.tipoChoice.getItems().add(TIPO_EXTERIOR);

        this.habilitarCamposPorTipo(null);
    }


    private void habilitarCamposPorTipo(String tipo){
        this.interiorBox.setDisable(!TIPO_INTERIOR.equals(tipo));
        this.exteriorBox.setDisable(!TIPO_EXTERIOR.equals(tipo));
    }

    @FXML
    public void onSeleccionarTipo() {
        this.habilitarCamposPorTipo(this.tipoChoice.getValue());
    }


    // paso 05
    private void setAlertaLabelMensaje(String tipo, String detalle){
        String mensaje = detalle.isBlank() ? tipo : tipo + ": " + detalle;
        switch (tipo){
            case ALERTA_ELIMINADO:
            case ALERTA_EXITO:
                this.alertaLabel.setVisible(true);
                this.alertaLabel.setTextFill(Color.GREEN);
                break;
            case ALERTA_NO_SELECCIONADO:
            case ALERTA_LECTURA:
            case ALERTA_ESCRITURA:
            case ALERTA_NO_VALIDO:
                this.alertaLabel.setVisible(true);
                this.alertaLabel.setTextFill(Color.RED);
                break;
            default:
                this.alertaLabel.setVisible(false);
                this.alertaLabel.setTextFill(Color.BLACK);
        }
        this.alertaLabel.setText(mensaje);
    }

    //Paso 06
    private Integer leerEntero(TextField campo, String nombreCampo){
        /*
        try {
            return Integer.parseInt(campo.getText().trim());
        } catch (NumberFormatException e) {
            this.setAlertaLabelMensaje(ALERTA_NO_VALIDO, "Campo: " + nombreCampo + " debe ser un numero entero");
            return null;
        }
         */
        return null;

    }

    //Paso 07
    private void limpiarCampos(){
        this.tipoChoice.setValue(null);
        this.nombreText.clear();
        this.alturaText.clear();
        this.cantidadText.clear();
        this.volumenText.clear();
        this.especieText.clear();
        this.resistenteCheck.setSelected(false);
        this.disponibleCheck.setSelected(false);
    }
    @FXML
    public void onLimpiar() {
        this.limpiarCampos();
        this.setAlertaLabelMensaje(ALERTA_DEFAULT, "");
    }

    @FXML
    public void onGuardar() {
    }

    @FXML
    public void onEliminar() {

    }





}
