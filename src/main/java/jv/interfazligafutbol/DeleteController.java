package jv.interfazligafutbol;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Clase que controla la vista de eliminar un equipo
 *
 * @version 1.0
 * @since 2024
 * @author Arturo Carrillo Jimenez
 */
public class DeleteController extends FicheroFutbol {

    @FXML
    private TextField equipoBorrar;

    /**
     * Metodo que elimina un equipo
     */
    @FXML
    private void delete() throws IOException {
        int error = borrarEquipo(equipoBorrar.getText());

        if (error == 0) {
            LigaApplication.setRoot("correcto");
        } else {
            LigaApplication.setRoot("error");
        }
    }
}
