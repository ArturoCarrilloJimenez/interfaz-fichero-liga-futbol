package jv.interfazligafutbol;

import javafx.fxml.FXML;
import java.io.IOException;

/**
 * Clase que controla la navegacion de la aplicacion
 *
 * @version 1.0
 */
public class NavController extends FicheroFutbol {

    @FXML
    private void index() throws IOException {
        LigaApplication.setRoot("index-liga-view");
    }

    @FXML
    private void addEquipo() throws IOException {
        LigaApplication.setRoot("addEquipo");
    }

    @FXML
    private void mostrarEquipo() throws IOException {
        LigaApplication.setRoot("mostrarEquipo");
    }

    @FXML
    private void delateEquipo() throws IOException {
        LigaApplication.setRoot("deleteEquipo");
    }

    @FXML
    private void modificarEquipo() throws IOException {
        LigaApplication.setRoot("modificarEquipo");
    }

    @FXML
    private void orderAsc() throws IOException {
        int mensajeError = ordenarLiga(1);

        if (mensajeError == 0) {
            LigaApplication.setRoot("mostrarEquipo");
        } else {
            LigaApplication.setRoot("error");
        }
    }

    @FXML
    private void orderDesc() throws IOException {
        int mensajeError = ordenarLiga(2);

        if (mensajeError == 0) {
            LigaApplication.setRoot("mostrarEquipo");
        } else {
            LigaApplication.setRoot("error");
        }
    }

    @FXML
    private void buscar() throws IOException {
        LigaApplication.setRoot("buscarEquipo");
    }
}
