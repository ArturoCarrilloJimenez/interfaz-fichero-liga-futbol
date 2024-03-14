package jv.interfazligafutbol;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.TextFlow;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Clase que controla la vista de los equipos
 *
 * @version 1.0
 * @since 2024
 * @autor Arturo Carrillo Jimenez
 */
public class MostrarEquipoContoller extends FicheroFutbol implements Initializable {

    @FXML
    private TextFlow mostrarEquipo;

    /**
     * Metodo que inicializa la vista
     *
     * @param location URL
     * @param resources ResourceBundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String[]> equipos = arrayLiga();

        if (equipos == null) {
            mostrarEquipo.getChildren().add(new javafx.scene.text.Text("No hay equipos"));
        } else {
            mostrarEquipo.setTabSize(MAXIMO_EQUIPOS + 3);

            mostrarEquipo.getChildren().add(new javafx.scene.text.Text(String.format("%-30s %10s %10s %10s %10s %10s\n", "Equipo", "Jugados", "Ganados", "Empatados", "Perdidos", "Puntos")));

            for (String[] equipo : equipos) {
                String registro = String.format("%-30s %12s %14s %17s %16s %16s\n", equipo[0], equipo[1], equipo[2], equipo[3], equipo[4], equipo[5]);

                mostrarEquipo.getChildren().add(new javafx.scene.text.Text(registro));
            }

        }
    }
}
