package jv.interfazligafutbol;

import javafx.fxml.FXML;

import java.io.IOException;

public class ErrorControler {

    @FXML
    private void index() throws IOException {
        LigaApplication.setRoot("index-liga-view");
    }
}
