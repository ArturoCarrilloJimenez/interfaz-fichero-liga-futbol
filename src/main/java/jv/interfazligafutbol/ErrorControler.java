package jv.interfazligafutbol;

import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Clase que controla los mensajes de error
 *
 * @version 1.0
 * @since 2024
 * @autor Arturo Carrillo Jimenez
 */
public class ErrorControler {

    /**
     * Metodo que redirige a la vista index
     */
    @FXML
    private void index() throws IOException {
        LigaApplication.setRoot("index-liga-view");
    }
}
