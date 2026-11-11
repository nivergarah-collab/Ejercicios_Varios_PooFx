package cl.duoc.peliculas.controller;

import cl.duoc.peliculas.model.Funcion;
import cl.duoc.peliculas.model.FuncionPopular;
import cl.duoc.peliculas.model.FuncionRegular;
import cl.duoc.peliculas.repository.FuncionRepository;
import cl.duoc.peliculas.repository.PersistenciaException;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class MainController {
    public static final String ALERTA_LECTURA = "Error al leer archivo";
    public static final String ALERTA_ESCRITURA = "Error al escribir archivo";
    public static final String ALERTA_DEFAULT = "Sin alerta";
    public static final String ALERTA_EXITO = "Registro exitoso";
    public static final String ALERTA_ELIMINADO = "Eliminacion exitosa";
    public static final String ALERTA_NO_VALIDO = "Dato no valido";
    public static final String ALERTA_NO_SELECCIONADO = "Falta de seleccion";

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

    private final FuncionRepository repository = new FuncionRepository();
    private final ObservableList<Funcion> funcionesTabla = FXCollections.observableArrayList();


    @FXML
    public void initialize(){
        this.cmbTipo.getItems().add(FuncionRegular.TIPO);
        this.cmbTipo.getItems().add(FuncionPopular.TIPO);
        this.habilitarCamposPorTipo(null);
        this.configurarTableView();
        try{
            this.repository.cargar();
            this.actualizarTableView();
        } catch (PersistenciaException e){
            this.setAlertalblMensaje(ALERTA_LECTURA, "Error en carga inicial de datos");
        }
    }

    public void habilitarCamposPorTipo(String tipoFuncion){
        this.boxRegular.setDisable(!FuncionRegular.TIPO.equals(tipoFuncion));
        this.boxPopular.setDisable(!FuncionPopular.TIPO.equals(tipoFuncion));
    }

    @FXML
    public void onSeleccionarTipo(){
        this.habilitarCamposPorTipo(this.cmbTipo.getValue());
    }

    public void configurarTableView(){
        this.colTipo.setCellValueFactory(celda -> new ReadOnlyStringWrapper(celda.getValue().getTipo()));
        this.colPelicula.setCellValueFactory(celda -> new ReadOnlyStringWrapper(celda.getValue().getPelicula()));
        this.colDuracion.setCellValueFactory(celda -> new ReadOnlyObjectWrapper<>(celda.getValue().getDuracionMin()));
        this.colEntradas.setCellValueFactory(celda -> new ReadOnlyObjectWrapper<>(celda.getValue().getEntradas()));
        this.colPrecio.setCellValueFactory(celda -> new ReadOnlyStringWrapper(Funcion.pesos(celda.getValue().calcularPrecioEntrada())));
        this.tblFunciones.setItems(funcionesTabla);
    }
    private void actualizarTableView(){
        this.funcionesTabla.setAll(repository.listar());
    }

    private void setAlertalblMensaje(String tipo, String mensaje){
        if (mensaje.trim().isBlank()) {
            mensaje =  tipo;
        }else {
            mensaje = tipo + ": " + mensaje;
        }
        switch (tipo){
            case ALERTA_EXITO:
            case ALERTA_ELIMINADO:
                this.lblMensaje.setTextFill(Color.GREEN);
                this.lblMensaje.setVisible(true);
                break;
            case ALERTA_ESCRITURA:
            case ALERTA_LECTURA:
            case ALERTA_NO_SELECCIONADO:
            case ALERTA_NO_VALIDO:
                this.lblMensaje.setTextFill(Color.RED);
                this.lblMensaje.setVisible(true);
                break;
            default:
                this.lblMensaje.setTextFill(Color.BLACK);
                this.lblMensaje.setVisible(false);
        }
        this.lblMensaje.setText(mensaje);
    }

    public void limpiarDatos(){
        this.cmbTipo.setValue(null);
        this.txtPelicula.setText("");
        this.txtDuracion.setText("");
        this.txtEntradas.setText("");
        this.txtSala.setText("");
        this.txtDistribuidora.setText("");
        this.txtDias.setText("");
        this.chk3D.setSelected(false);
        this.chkReembolsada.setSelected(false);
    }

    public Integer leerEntero(TextField campo, String nombreCampo){
        try {
            return Integer.parseInt(campo.getText().trim());
        }catch (NumberFormatException e){
            setAlertalblMensaje(ALERTA_NO_VALIDO,"Campo: " + nombreCampo + " debe ser un numero entero");
            return null;
        }
    }



    @FXML
    public void onRegistrar() {
        Funcion funcion;
        try{
            String tipo = this.cmbTipo.getValue();
            if (tipo == null || tipo.isEmpty()){
                this.setAlertalblMensaje(ALERTA_NO_SELECCIONADO, "Tipo no seleccionado");
                return;
            }
            String pelicula = this.txtPelicula.getText();
            Integer duracion = leerEntero(this.txtDuracion, "duracion");
            if (duracion == null){
                return;
            }
            Integer entradas = leerEntero(this.txtEntradas, "entradas");
            if (entradas == null){
                return;
            }
            switch (tipo){
                case FuncionRegular.TIPO:
                    String sala = this.txtSala.getText();
                    boolean es3D = this.chk3D.isSelected();
                    funcion = new FuncionRegular(pelicula, duracion, entradas, sala, es3D);
                    break;
                case FuncionPopular.TIPO:
                    String distribuidora = this.txtDistribuidora.getText();
                    Integer dias = this.leerEntero(this.txtDias, "dias");
                    if(dias == null){
                        return;
                    }
                    boolean reembolsada = this.chkReembolsada.isSelected();
                    funcion = new FuncionPopular(pelicula, duracion, entradas, distribuidora, dias);
                    if(reembolsada){
                        ((FuncionPopular) funcion).marcarReembolsada();
                    }
                    break;
                default:
                    this.setAlertalblMensaje(ALERTA_NO_SELECCIONADO, "Debe seleccionar Funcion Popular o Regular");
                    return;
            }
            try {
                this.repository.registrar(funcion);
                this.actualizarTableView();
            } catch (PersistenciaException e) {
                this.setAlertalblMensaje(ALERTA_ESCRITURA, "No se a registrado la funcion.");
                return;
            }
            this.setAlertalblMensaje(ALERTA_EXITO, "Funcion - " + funcion.getPelicula() + " registrada");
            this.limpiarDatos();
        }catch (IllegalArgumentException e){
            this.setAlertalblMensaje(ALERTA_NO_VALIDO, e.getMessage());
        }
    }

    @FXML
    public void onEliminar() {
        Funcion seleccionada = this.tblFunciones.getSelectionModel().getSelectedItem();
        if (seleccionada == null){
            this.setAlertalblMensaje(ALERTA_NO_SELECCIONADO, "funcion no seleccionada");
            return;
        }
        try {
            if(!this.repository.eliminar(seleccionada)){
                this.setAlertalblMensaje(ALERTA_NO_VALIDO, "Registro no encontrado");
                this.actualizarTableView();
                return;
            }
        }catch (PersistenciaException e){
            this.setAlertalblMensaje(ALERTA_ESCRITURA, "No se a completado la eliminacion del registro");
            return;
        }
        this.actualizarTableView();
        this.setAlertalblMensaje(ALERTA_ELIMINADO, "Funcion - " + seleccionada.getPelicula() + " eliminada con exito");
    }

}
