package jv.interfazligafutbol;

import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Clase que controla los mensajes de error
 *
 * @version 1.0
 */
public class ErrorControler {

    @FXML
    private void index() throws IOException {
        LigaApplication.setRoot("index-liga-view");
    }
}
