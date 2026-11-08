package cl.duoc.vivero.controller;

import cl.duoc.vivero.model.Planta;
import cl.duoc.vivero.model.PlantaExterior;
import cl.duoc.vivero.model.PlantaInterior;
import cl.duoc.vivero.repository.PersistenciaException;
import cl.duoc.vivero.repository.PlantaRepository;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainController {
    // Constantes para tipoChoice
    public final static String TIPO_EXTERIOR = "Planta Exterior";
    public final static String TIPO_INTERIOR = "Planta Interior";
    // Constantes para alertaLabel
    public final static String ALERTA_DEFAULT = "Sin Alerta";
    public final static String ALERTA_EXITO = "Registro Exitoso";
    public final static String ALERTA_NO_VALIDO = "Dato no valido";
    public final static String ALERTA_ELIMINADO = "Registro eliminado";
    public final static String ALERTA_NO_SELECCIONADO = "Falta de seleccion";
    public final static String ALERTA_LECTURA = "Error al leer archivo";
    public final static String ALERTA_ESCRITURA = "Error al escribir archivo";

    // Espacio para alertas
    @FXML private Label alertaLabel;
    // Formulario de Planta
    // Campos Comunes
    @FXML private ChoiceBox<String> tipoChoice;
    @FXML private TextField nombreText;
    @FXML private TextField alturaText;
    @FXML private TextField cantidadText;
    // Campos Planta Interior (interiorBox agrupa todos los datos propios de la planta de interior)
    @FXML private VBox interiorBox;
    @FXML private TextField volumenText;
    // Campos Planta Exterior (exteriorBox agrupa todos los datos propios de la planta de exterior)
    @FXML private VBox exteriorBox;
    @FXML private TextField especieText;
    @FXML private CheckBox resistenteCheck;
    @FXML private CheckBox disponibleCheck;

    // TableView
    @FXML private TableView<Planta> plantaTableView;
    @FXML private TableColumn<Planta, String> tipoColumn;
    @FXML private TableColumn<Planta, String> nombreColumn;
    @FXML private TableColumn<Planta, Integer> alturaColumn;
    @FXML private TableColumn<Planta, Integer> cantidadColumn;
    @FXML private TableColumn<Planta, String> precioColumn;

    // Implementar el repositorio en el controlador
    private final PlantaRepository repository = new PlantaRepository();
    private final ObservableList<Planta> plantas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Inicializando tipos de datos
        this.tipoChoice.getItems().add(TIPO_INTERIOR);
        this.tipoChoice.getItems().add(TIPO_EXTERIOR);
        // Sin tipo elegido: ambos grupos de campos quedan deshabilitados
        this.habilitarCamposPorTipo(null);
        this.configurarTableView();
        try{
            this.repository.cargar();
            this.actualizarTableView();
        }catch (PersistenciaException e){
            this.setAlertaLabelMensaje(e.getMessage(),"Error en carga inicial de datos");
        }
    }

    private void configurarTableView(){
        this.tipoColumn.setCellValueFactory(celda -> new ReadOnlyStringWrapper(celda.getValue().getTipo()));
        this.nombreColumn.setCellValueFactory(celda -> new ReadOnlyStringWrapper(celda.getValue().getNombre()));
        this.alturaColumn.setCellValueFactory(celda -> new ReadOnlyObjectWrapper<>(celda.getValue().getAlturaCm()));
        this.cantidadColumn.setCellValueFactory(celda -> new ReadOnlyObjectWrapper<>(celda.getValue().getCantidad()));
        this.precioColumn.setCellValueFactory(celda -> new ReadOnlyStringWrapper(Planta.pesos(celda.getValue().calcularPrecio())));
        this.plantaTableView.setItems(this.plantas);
    }

    private void actualizarTableView(){
        plantas.setAll(repository.listar());
    }

    @FXML
    public void onSeleccionarTipo(){
        this.habilitarCamposPorTipo(this.tipoChoice.getValue());
    }

    /** Habilita solo el grupo de campos del tipo elegido; sin tipo válido deshabilita ambos, sin aviso. */
    private void habilitarCamposPorTipo(String tipoPlanta){
        this.interiorBox.setDisable(!TIPO_INTERIOR.equals(tipoPlanta));
        this.exteriorBox.setDisable(!TIPO_EXTERIOR.equals(tipoPlanta));
    }

    @FXML
    public void onGuardar(){
        // Se crea variable para almacenamiento de datos
        Planta planta;
        try {
            // Se asignan las variables de los controladores generales
            String tipo = this.tipoChoice.getValue();
            if(tipo == null || tipo.isEmpty()){
                this.setAlertaLabelMensaje(ALERTA_NO_SELECCIONADO, "Campo Tipo de Planta: debe seleccionar un tipo");
                return;
            }
            String nombre = this.nombreText.getText();
            Integer alturaCm = this.leerEntero(this.alturaText, "altura");
            if(alturaCm == null){
                return;
            }
            Integer cantidad = this.leerEntero(this.cantidadText, "cantidad");
            if(cantidad == null){
                return;
            }
            switch (tipo){
                case TIPO_INTERIOR:
                    double volumenMacetaLitros;
                    try{
                        volumenMacetaLitros = Double.parseDouble(this.volumenText.getText().trim().replace(',', '.'));
                    }catch (NumberFormatException e){
                        this.setAlertaLabelMensaje(ALERTA_NO_VALIDO, "Campo volumen debe ser un número (acepta decimales)");
                        return;
                    }
                    if(volumenMacetaLitros <= 0.0){
                        this.setAlertaLabelMensaje(ALERTA_NO_VALIDO, "Campo volumen debe ser mayor que cero");
                        return;
                    }
                    planta = new PlantaInterior(nombre,alturaCm,cantidad,volumenMacetaLitros);
                    break;
                case TIPO_EXTERIOR:
                    String especie = this.especieText.getText();
                    if(especie.isEmpty() || especie.trim().isBlank()){
                        this.setAlertaLabelMensaje(ALERTA_NO_VALIDO, "campo especie no puede estar vacia ni contener solo espacios");
                        return;
                    }
                    boolean resistenteHeladas = this.resistenteCheck.isSelected();
                    boolean listaParaDespacho = this.disponibleCheck.isSelected();
                    planta = new PlantaExterior(nombre,alturaCm,cantidad,especie,resistenteHeladas);
                    if(listaParaDespacho){
                        ((PlantaExterior) planta).marcarListaParaDespacho();
                    }
                    break;
                default:
                    this.setAlertaLabelMensaje(ALERTA_NO_SELECCIONADO, "Campo Tipo de Planta");
                    return;
            }

            try{
                this.repository.registrar(planta);
                this.actualizarTableView();
            }catch (PersistenciaException e){
                this.setAlertaLabelMensaje(e.getMessage(),"Error guardando nuevo registro");
                return;
            }

            this.setAlertaLabelMensaje(ALERTA_EXITO, "Planta - "+planta.getNombre());
            // El formulario queda listo para un nuevo registro (se conserva el mensaje de éxito)
            this.limpiarCampos();

        }catch (IllegalArgumentException e){
            this.setAlertaLabelMensaje(ALERTA_NO_VALIDO,e.getMessage());
        }
    }

    /** Lee un entero del campo; si no es un número entero, informa el campo y devuelve null. */
    private Integer leerEntero(TextField campo, String nombreCampo){
        try{
            return Integer.parseInt(campo.getText().trim());
        }catch (NumberFormatException e){
            this.setAlertaLabelMensaje(ALERTA_NO_VALIDO, "Campo " + nombreCampo + " debe ser un número entero");
            return null;
        }
    }

    @FXML
    public void onLimpiar(){
        this.limpiarCampos();
        this.setAlertaLabelMensaje(ALERTA_DEFAULT,"");
    }

    private void limpiarCampos(){
        this.tipoChoice.setValue(null);
        this.nombreText.setText("");
        this.alturaText.setText("");
        this.cantidadText.setText("");
        this.volumenText.setText("");
        this.especieText.setText("");
        this.resistenteCheck.setSelected(false);
        this.disponibleCheck.setSelected(false);
    }

    private void setAlertaLabelMensaje(String tipo,String mensaje){
        if(mensaje.trim().isBlank()){
            mensaje = tipo;
        }else{
            mensaje = tipo + ": "+ mensaje;
        }
        switch(tipo){
            case ALERTA_EXITO:
            case ALERTA_ELIMINADO:
                this.alertaLabel.setVisible(true);
                this.alertaLabel.setTextFill(Color.GREEN);
                break;
            case ALERTA_NO_VALIDO:
            case ALERTA_NO_SELECCIONADO:
            case ALERTA_LECTURA:
            case ALERTA_ESCRITURA:
                this.alertaLabel.setVisible(true);
                this.alertaLabel.setTextFill(Color.RED);
                break;
            default:
                this.alertaLabel.setVisible(false);
                this.alertaLabel.setTextFill(Color.BLACK);
        }

        this.alertaLabel.setText(mensaje);
    }

    @FXML
    public void onEliminar(){
        Planta seleccionada = this.plantaTableView.getSelectionModel().getSelectedItem();
        if(seleccionada == null){
            this.setAlertaLabelMensaje(ALERTA_NO_SELECCIONADO, "Debe indicarse la planta a eliminar del registro");
            return;
        }
        try{
            if(!this.repository.eliminar(seleccionada)){
                this.setAlertaLabelMensaje(ALERTA_NO_VALIDO, "No se encuentra lo solicitado en el registro");
                this.actualizarTableView();
                return;
            }
        } catch (PersistenciaException e){
            this.setAlertaLabelMensaje(ALERTA_ESCRITURA, "No se puede guardar los cambios");
            return;
        }
        this.actualizarTableView();
        this.setAlertaLabelMensaje(ALERTA_ELIMINADO, "Planta - " + seleccionada.getNombre() + " (" + seleccionada.getTipo() + ")");
    }

}
