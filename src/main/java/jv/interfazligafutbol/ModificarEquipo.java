package jv.interfazligafutbol;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Clase que controla la vista de modificar un equipo
 *
 * @version 1.0
 */
public class ModificarEquipo extends FicheroFutbol {

    @FXML
    private TextField equipoModificar;

    @FXML
    private Label mensaje_error;

    /**
     * Metodo que comprueba si existe el equipo y redirige a la vista de modificar
     */
    @FXML
    private void modificar() throws IOException {
        String[] equipo = buscarEquipo(equipoModificar.getText());

        if (equipo != null) {
            ModificarEquipo2 modificarEquipo2 = new ModificarEquipo2();
            modificarEquipo2.iniciar(equipoModificar.getText());
        } else {
            mensaje_error.setText("No existe el equipo");
        }
    }
}
