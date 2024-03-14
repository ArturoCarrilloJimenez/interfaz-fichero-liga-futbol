package jv.interfazligafutbol;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;

/**
 * Clase que controla la vista de modificar un equipo
 *
 * @version 1.0
 * @since 2024
 * @autor Arturo Carrillo Jimenez
 */
public class ModificarEquipo2 extends FicheroFutbol {

    @FXML
    private Label mensaje_error_label;

    @FXML
    private TextField partJ,partG,partE;

    private static String equipo_modificar;

    /**
     * Metodo que inicializa la vista
     */
    public void iniciar(String equipoModificar) throws IOException {
        LigaApplication.setRoot("modificarEquipo2");
        equipo_modificar = equipoModificar;
    }

    /**
     * Metodo que modifica un equipo
     * Se obtienen los valores de los campos de texto y se comprueban si son validos
     * Si los partidos jugados, ganados o empatados son menores a 0 o si los partidos ganados o empatados son mayores a los jugados, se muestra un mensaje de error
     * Si no hay errores, se calcula el numero de partidos perdidos y los puntos
     * Si se guarda el equipo correctamente, se redirige a la vista correcto, si no, a la vista error
     */
    @FXML
    private void modificar() throws IOException {
        int partJugados = -1, partGanados = -1, partEmpatados = -1, mensaje_error = 0;

        // Se obtienen los valores de los campos de texto
        try {
            partJugados = Integer.parseInt(partJ.getText());
            partGanados = Integer.parseInt(partG.getText());
            partEmpatados = Integer.parseInt(partE.getText());
        } catch (Exception exception) {
            mensaje_error = -1;
        }

        // Si el mensaje de error es -1, se muestra un mensaje de error
        if (mensaje_error == -1) {
            mensaje_error_label.setText("Partidos Jugado, Ganados y Empatados deven ser un numero");
        } else if ((partJugados < 0) || ((partGanados < 0) || (partGanados > partJugados)) || ((partEmpatados < 0) || (partEmpatados > (partJugados - partGanados)))) {
            // Si los partidos jugados, ganados o empatados son menores a 0 o si los partidos ganados o empatados son mayores a los jugados, se muestra un mensaje de error
            mensaje_error_label.setText("La suma de Partidos Ganados y Empatados no puede superar a los Jugados");
        } else { // Si no hay errores, se calcula el numero de partidos perdidos y los puntos
            int partPerdido = (partJugados - (partGanados + partEmpatados));
            int puntos = ((partGanados * 3) + partEmpatados);

            int error = modificarEquipo(equipo_modificar, partJugados, partGanados, partEmpatados, partPerdido, puntos);

            if (error == 0) {
                LigaApplication.setRoot("correcto");
            } else {
                LigaApplication.setRoot("error");
            }
        }
    }
}
