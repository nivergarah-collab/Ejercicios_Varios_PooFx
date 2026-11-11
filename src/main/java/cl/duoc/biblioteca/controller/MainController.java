package cl.duoc.biblioteca.controller;



import cl.duoc.biblioteca.model.*;
import cl.duoc.biblioteca.repository.PersistenciaException;
import cl.duoc.biblioteca.repository.PrestamoRepository;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class MainController {

    public static final String TIPO_LIBRO = "Libro";
    public static final String TIPO_AUDIOVISUAL = "Audiovisual";

    public static final String ALERTA_DEFAULT = "Sin aviso";
    public static final String ALERTA_EXITO = "Exito al guardar el registro";
    public static final String ALERTA_NO_VALIDO = "Dato no valido";
    public static final String ALERTA_ELIMINADO = "Exito al eliminar el registro";
    public static final String ALERTA_NO_SELECCIONADO = "No seleccionado";
    public static final String ALERTA_LECTURA = "Error al leer archivo";
    public static final String ALERTA_ESCRITURA = "Error al escribir archivo";

    @FXML private ChoiceBox<String> tipoChoice;
    @FXML private TextField socioText;
    @FXML private TextField diasText;
    @FXML private TextField materialesText;

    @FXML private HBox libroBox;
    @FXML private TextField tituloText;
    @FXML private CheckBox novedadCheck;
    @FXML private CheckBox renovadoCheck;

    @FXML private HBox audioLibroBox;
    @FXML private TextField formatoText;
    @FXML private TextField duracionText;

    @FXML private TableView<Prestamo> prestamoTableView;
    @FXML private TableColumn<Prestamo, String> tipoColumn;
    @FXML private TableColumn<Prestamo, String> socioColumn;
    @FXML private TableColumn<Prestamo, Integer> diasColumn;
    @FXML private TableColumn<Prestamo, Integer> materialesColumn;
    @FXML private TableColumn<Prestamo, String> tarifaColumn;

    @FXML private Label alertaLabel;

    private final PrestamoRepository repository = new PrestamoRepository();
    private final ObservableList<Prestamo> prestamos = FXCollections.observableArrayList();


    @FXML public void initialize(){
        this.tipoChoice.getItems().add(TIPO_LIBRO);
        this.tipoChoice.getItems().add(TIPO_AUDIOVISUAL);
        this.habilitarCamposPortipo(null);
        this.configurarTableView();
        try {
            this.repository.cargar();
            this.actualizarTableView();
        } catch (PersistenciaException e){
            this.setAlertaTextMensaje(ALERTA_LECTURA, "Error al cargar los datos al iniciar");
        }
    }


    public void configurarTableView(){
        this.tipoColumn.setCellValueFactory(celda -> new ReadOnlyStringWrapper(celda.getValue().getTipo()));
        this.socioColumn.setCellValueFactory(celda -> new ReadOnlyStringWrapper(celda.getValue().getSocio()));
        this.diasColumn.setCellValueFactory(celda -> new ReadOnlyObjectWrapper<>(celda.getValue().getDiasPrestamo()));
        this.materialesColumn.setCellValueFactory(celda -> new ReadOnlyObjectWrapper<>(celda.getValue().getMateriales()));
        this.tarifaColumn.setCellValueFactory(celda -> new ReadOnlyStringWrapper(Prestamo.pesos(celda.getValue().calcularTarifa())));
        this.prestamoTableView.setItems(this.prestamos);
    }

    public void actualizarTableView(){
        this.prestamos.setAll(this.repository.listar());
    }

    public void setAlertaTextMensaje(String tipo, String mensaje) {
        if (mensaje.trim().isBlank()) {
            mensaje = tipo;
        } else {
            mensaje = tipo + ": " + mensaje;
        }
        switch (tipo){
            case ALERTA_EXITO:
            case ALERTA_ELIMINADO:
                this.alertaLabel.setTextFill(Color.GREEN);
                this.alertaLabel.setVisible(true);
                break;
            case ALERTA_ESCRITURA:
            case ALERTA_LECTURA:
            case ALERTA_NO_SELECCIONADO:
            case ALERTA_NO_VALIDO:
                this.alertaLabel.setTextFill(Color.RED);
                this.alertaLabel.setVisible(true);
                break;
            default:
                this.alertaLabel.setTextFill(Color.BLACK);
                this.alertaLabel.setVisible(false);
        }
        this.alertaLabel.setText(mensaje);
    }

    public void habilitarCamposPortipo(String tipo){
        this.libroBox.setDisable(!TIPO_LIBRO.equals(tipo));
        this.audioLibroBox.setDisable(!TIPO_AUDIOVISUAL.equals(tipo));
    }

    @FXML
    public void onSeleccionarTipo() {
        this.habilitarCamposPortipo(this.tipoChoice.getValue());
    }

    public void limpiarCampos(){
        this.tipoChoice.setValue(null);
        this.socioText.setText("");
        this.diasText.setText("");
        this.materialesText.setText("");
        this.tituloText.setText("");
        this.novedadCheck.setSelected(false);
        this.renovadoCheck.setSelected(false);
        this.formatoText.setText("");
        this.duracionText.setText("");
    }

    public Integer leerEnteros(TextField campo, String nombreCampo){
        try {
            return Integer.parseInt(campo.getText().trim());
        }catch (NumberFormatException e){
            this.setAlertaTextMensaje(ALERTA_NO_VALIDO, "Campo: " + nombreCampo + " debe ser un numero entero");
            return null;
        }
    }



    @FXML
    public void onRegistrar() {
        Prestamo prestamo;
        try {
            String tipo = this.tipoChoice.getValue();
            if(tipo == null || tipo.isEmpty()){
                setAlertaTextMensaje(ALERTA_NO_SELECCIONADO, "Debe elegir al menos un tipo");
                return;
            }
            String socio = this.socioText.getText();
            Integer diasPrestamo = leerEnteros(diasText, "dias");
            if(diasPrestamo == null) {
                return;
            }
            Integer materiales = leerEnteros(materialesText, "materiales");
            if(materiales == null) {
                return;
            }

            switch (tipo){
                case TIPO_LIBRO:
                    String titulo = this.tituloText.getText();
                    boolean novedad = this.novedadCheck.isSelected();
                    boolean renovado = this.renovadoCheck.isSelected();
                    prestamo = new PrestamoLibro(socio, diasPrestamo, materiales, titulo,novedad);
                    if(renovado){
                        ((PrestamoLibro) prestamo).renovar();
                    }
                    break;
                case TIPO_AUDIOVISUAL:
                    String formato = this.formatoText.getText();
                    Integer duracionMinutos = this.leerEnteros(duracionText, "duracion");
                    if(duracionMinutos == null){
                        return;
                    }
                    prestamo = new PrestamoAudiovisual(socio, diasPrestamo, materiales, formato, duracionMinutos);
                    break;
                default:
                    this.setAlertaTextMensaje(ALERTA_NO_SELECCIONADO, "debe seleccionar un tipo de prestamo.");
                    return;
            }
            try {
                this.repository.registrar(prestamo);
                this.actualizarTableView();
            } catch (PersistenciaException e){
                this.setAlertaTextMensaje(ALERTA_ESCRITURA, "No se a guardado el registro");
                return;
            }
            this.setAlertaTextMensaje(ALERTA_EXITO, "El prestamo de " + prestamo.getSocio() + " se ha registrado correctamente");
            this.limpiarCampos();
        } catch (IllegalArgumentException e) {
        this.setAlertaTextMensaje(ALERTA_NO_VALIDO, e.getMessage());

        }

    }

    @FXML
    public void onEliminar() {
        Prestamo seleccionado = this.prestamoTableView.getSelectionModel().getSelectedItem();
        if(seleccionado == null){
            this.setAlertaTextMensaje(ALERTA_NO_SELECCIONADO, "Debe seleccionar al menos un registro");
            return;
        }
        try{
            if(!this.repository.eliminar(seleccionado)){
                this.setAlertaTextMensaje(ALERTA_NO_VALIDO, "Registro no valido o encontrado");
                this.actualizarTableView();
                return;
            }
        }catch (PersistenciaException e){
            this.setAlertaTextMensaje(ALERTA_ESCRITURA, "No fue posible eliminar registro");
            return;
        }
        this.actualizarTableView();
        this.setAlertaTextMensaje(ALERTA_ELIMINADO, "Prestamo de " + seleccionado.getSocio() + " eliminado de los registros");
    }




}
