package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Persona;

public class ActividadBController implements Initializable {

    @FXML
    private Button btnAgregarPersona;

    @FXML
    private TableColumn<Persona, String> columnaApellidos;

    @FXML
    private TableColumn<Persona, Integer> columnaEdad;

    @FXML
    private TableColumn<Persona, String> columnaNombre;

    @FXML
    private TableView<Persona> tablaPersonas;

    @FXML
    private TextField tfApellidos;

    @FXML
    private TextField tfEdad;

    @FXML
    private TextField tfNombre;
    

    @FXML
    void agregarPersona(ActionEvent event) {
    	
		try {
			Stage stage = new Stage();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AgregarPersona.fxml"));
			GridPane rootPersona = loader.load();
			AgregarPersonaController controller = loader.getController();
			controller.pasarTabla(tablaPersonas);
			
			
			Scene scene = new Scene(rootPersona);
			stage.setResizable(false);
			stage.setTitle("Agregar Persona");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	
    }
    


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		columnaApellidos.setCellValueFactory(param -> {
			Persona persona = param.getValue();
			if (persona != null && persona.getApellidos() != null) {					
				return new SimpleStringProperty(persona.getApellidos());
			}
			return new SimpleStringProperty("");
		});
		
		columnaNombre.setCellValueFactory(param -> {
			Persona persona = param.getValue();
			if (persona != null && persona.getNombre() != null) {					
				return new SimpleStringProperty(persona.getNombre());
			}
			return new SimpleStringProperty("");
		});
		
		columnaEdad.setCellValueFactory(param -> {
			Persona persona = param.getValue();
			if (persona.getEdad() >= 0) {
				return new SimpleIntegerProperty(persona.getEdad()).asObject();
			}
			return  new SimpleIntegerProperty().asObject();
		});
		
		tablaPersonas.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Persona>() {

			@Override
			public void onChanged(Change<? extends Persona> c) {
				Persona persona = tablaPersonas.getSelectionModel().getSelectedItem();
				if (persona != null ) {
					tfNombre.setText(persona.getNombre());
					tfApellidos.setText(persona.getApellidos());
					tfEdad.setText(persona.getEdad() >= 0 ? Integer.toString(persona.getEdad()) : "");
				}
			}
			
		});
		
	
	}
}
