package jv.interfazligafutbol;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class DeleteController extends FicheroFutbol {

    @FXML
    private TextField equipoBorrar;

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
