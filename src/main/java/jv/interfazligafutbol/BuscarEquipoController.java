package jv.interfazligafutbol;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Clase que controla la busqueda de un equipo
 *
 * @version 1.0
 */
public class BuscarEquipoController extends FicheroFutbol {

    @FXML
    private TextField buscarEquipo;

    @FXML
    private Label mostrarEquipo;

    /**
     * Metodo que busca un equipo en la liga de futbol y muestra sus datos en la vista
     */
    @FXML
    private void buscar() {
        String equipo_buscar = buscarEquipo.getText();
        String[] equipo = buscarEquipo(equipo_buscar);

        if (equipo == null) {
            mostrarEquipo.setText("No existe el equipo " + equipo_buscar);
        } else {
            mostrarEquipo.setText("Equipo: " + equipo[0] + "\nJugados: " + equipo[1] + "\nGanados: " + equipo[2] + "\nEmpatados: " + equipo[3] + "\nPerdidos: " + equipo[4] + "\nPuntos: " + equipo[5]);
        }
    }
}
