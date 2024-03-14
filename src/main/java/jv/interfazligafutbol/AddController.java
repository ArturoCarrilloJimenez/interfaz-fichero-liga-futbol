package jv.interfazligafutbol;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;

/**
 * Clase que controla la vista de agregar un equipo
 *
 * @version 1.0
 * @since 2024
 * @author Arturo Carrillo Jimenez
 */
public class AddController extends FicheroFutbol {

    @FXML
    private TextField name,partJ,partG,partE;

    @FXML
    private Label mensaje_error;

    /**
     * Metodo que agrega un equipo y valida los campos de texto
     * En primer lugar comprueba que no se haya superado el limite de equipos
     * Si el equipo ya existe, se muestra un mensaje de error
     * Si el nombre esta vacio o tiene mas de 30 caracteres, se muestra un mensaje de error
     * Si los partidos jugados, ganados o empatados son menores a 0 o si los partidos ganados o empatados son mayores a los jugados, se muestra un mensaje de error
     * Si no hay errores, se calcula el numero de partidos perdidos y los puntos
     * Si se guarda el equipo correctamente, se redirige a la vista correcto, si no, a la vista error
     */
    @FXML
    private void add() throws IOException {
        boolean limite = limiteEquipos();
        String[] equipo = buscarEquipo(name.getText().toLowerCase());

        // Si el limite de equipos es verdadero, se redirige a la vista error
        if (limite) {
            mensaje_error.setText("Se a superado el limite de equipos");
        } else if (equipo != null) {
            mensaje_error.setText("Este equipo ya existe");
        } else {
            int partJugados = -1, partGanados = -1, partEmpatados = -1, mensajeError = 0;


            mensaje_error.setText("");

            // Se obtienen los valores de los campos de texto
            try {
                partJugados = Integer.parseInt(partJ.getText());
                partGanados = Integer.parseInt(partG.getText());
                partEmpatados = Integer.parseInt(partE.getText());
            } catch (Exception exception) {
                mensajeError = -1;
            }

            // Si el mensaje de error es -1, se muestra un mensaje de error
            if (mensajeError == -1) {
                mensaje_error.setText("Partidos Jugado, Ganados y Empatados deven ser un numero");
            } else if ((name.getText().isEmpty()) || (name.getText().length() >= 30)) { // Si el nombre esta vacio o tiene mas de 30 caracteres, se muestra un mensaje de error
                mensaje_error.setText("El nombre debe de tener como maximo 30 caracteres");
            } else if ((partJugados < 0) || ((partGanados < 0) || (partGanados > partJugados)) || ((partEmpatados < 0) || (partEmpatados > (partJugados - partGanados)))) {
                // Si los partidos jugados, ganados o empatados son menores a 0 o si los partidos ganados o empatados son mayores a los jugados, se muestra un mensaje de error
                mensaje_error.setText("La suma de Partidos Ganados y Empatados no puede superar a los Jugados");
            } else { // Si no hay errores, se calcula el numero de partidos perdidos y los puntos
                int partPerdido = (partJugados - (partGanados + partEmpatados));
                int puntos = ((partGanados * 3) + partEmpatados);

                // Se guarda el equipo
                mensajeError = guardarEquipo(name.getText().toLowerCase(), partJugados, partGanados, partEmpatados, partPerdido, puntos);

                // Si el mensaje de error es 0, se redirige a la vista correcto, si no, a la vista error
                if (mensajeError == 0) {
                    LigaApplication.setRoot("correcto");
                } else {
                    LigaApplication.setRoot("error");
                }
            }
        }
    }
}