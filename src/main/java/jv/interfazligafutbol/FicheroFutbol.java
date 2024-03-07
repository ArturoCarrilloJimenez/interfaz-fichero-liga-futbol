package jv.interfazligafutbol;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Clase que se encarga de gestionar el fichero de la liga de futbol
 *
 * @version 1.0
 * @since 1.0
 * @see FicheroFutbol
 * @author Arturo Carrillo Jimenez
 */
public class FicheroFutbol {
    
    /**
     * Nombre del archivo donde se guardan los datos de la liga
     */
    private static final String NOMBRE_ARCHIVO = "MiLiga.txt";
    /**
     * Maximo de equipos que se pueden guardar en el fichero
     */
    protected static final int  MAXIMO_EQUIPOS = 15;

    /**
     * Metodo que guarda un equipo en el fichero
     *
     * @param nobreEquipo nombre del equipo
     * @param partJugados partidos jugados
     * @param partGanados partidos ganados
     * @param partEmpatados partidos empatados
     * @param partPerdidos partidos perdidos
     * @param puntos puntos del equipo
     * @return 0 si se a guardado correctamente, -1 si a habido un error
     */
    public int guardarEquipo(String nobreEquipo, int partJugados, int partGanados, int partEmpatados, int partPerdidos, int puntos) {
        int mensajeError = 0;
        
        try {
            FileWriter fileWriter = new FileWriter(NOMBRE_ARCHIVO, true);

            fileWriter.write(nobreEquipo + ";" + partJugados + ";" + partGanados + ";" + partEmpatados + ";" + partPerdidos + ";" + puntos + "\n");

            fileWriter.close();
        } catch (Exception e) {
            mensajeError = -1;
        }

        return mensajeError;
    }

    /**
     * Metodo que comprueba si se a llegado al limite de equipos
     *
     * @return true si se a llegado al limite de equipos, false si no
     */
    public boolean limiteEquipos() {
        boolean limite = false;
        ArrayList<String[]> arrayList = arrayLiga(); // Guardo en un array dinamico los datos de cada equipo
        int i = 0;

        if (arrayList != null) {
            for (int j = 0;j < arrayList.size();j++) {
                i++;
            }
        }

        if (i >=  MAXIMO_EQUIPOS) {
            limite = true;      
        }

        return limite;
    }

    /**
     * Metodo que devuelve un array dinamico con los datos de la liga
     *
     * @return array dinamico con los datos de la liga
     */
    public ArrayList<String[]> arrayLiga() {
        ArrayList<String[]> arrayList = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(NOMBRE_ARCHIVO);
            BufferedReader bufferedReader = new BufferedReader(fileReader); // Creo esta clase para poder leer lieas de texto en vez de ir de bit en bit
            
            String linea = "";
            String[] datosEquipo;

            // Bucle que lee el texto y lo guarda de una forma mas optima para su lectura
            while ((linea = bufferedReader.readLine()) != null) {
                datosEquipo = linea.split(";"); // Guardo en el array las fila de cada equipo

                // Añado los datos del equipo
                arrayList.add(datosEquipo);
            }

            // Cierro los abjetos que e utilizado al realizar la lectura
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            arrayList = null; // En caso de fallo este devuelve un null
        }

        return arrayList;
    }

    /**
     * Metodo que ordena la liga por puntos o por partidos ganados
     *
     * @param tipo 1 para ordenar crecientemente, 2 para ordenar decrecientemente
     * @return 0 si se a ordenado correctamente, -1 si a habido un error
     */
    public int ordenarLiga(int tipo) {
        int mensajeError = 0;
        ArrayList<String[]> arrayList = arrayLiga(); // Guardo en un array dinamico los datos de cada equipo

        if (arrayList != null) {
            int[] puntos = new int[arrayList.size()]; // Creo un array de puntos con el tamaño del array dinamico
            int[] partGanador = new int[arrayList.size()]; // Creo un array de puntos con el tamaño del array dinamico

           // Guardo los puntos de cada equipo en un array
            int posicionEquipo = 0;
            for (String[] datosEquipo : arrayList) {
                puntos[posicionEquipo] = Integer.parseInt(datosEquipo[5]);
                partGanador[posicionEquipo] = Integer.parseInt(datosEquipo[2]);
                posicionEquipo++;
            }

             if (tipo == 1) {arrayList = ordenarCrecientemente(arrayList, puntos, partGanador);}
             else if (tipo == 2) {arrayList = ordenarDecreciente(arrayList, puntos, partGanador);}
        }
        else {mensajeError = -1;}

        // Guardo los datos ordenados en el fichero
        if (mensajeError == 0) {
            rescribirFichero(arrayList);
        }

        return mensajeError;
    }

    /**
     * Metodo que realiza la comparacion de ordenacion creciente por puntos y goles a favor
     * @param puntos array de puntos
     * @param partGanados array de partidos ganados
     * @param posicion posicion del array
     * @return -1 si el array no esta ordenado, 0 si esta ordenado
     */
    private int compararOrdenacionCreciente(int puntos[], int[] partGanados, int posicion) {
        int comparacion = 0;
        
        if (puntos[posicion] < puntos[posicion + 1]) {comparacion = -1;}
        else if ((puntos[posicion] == puntos[posicion + 1]) && (partGanados[posicion] < partGanados[posicion + 1])) {comparacion = -1;}

        return comparacion;
    }

    /**
     * Metodo que ordena crecientemente la liga
     * @param arrayList array dinamico con los datos de la liga
     * @param puntos array de puntos
     * @param partGanados array de partidos ganados
     * @return array dinamico con los datos de la liga ordenado crecientemente
     */
    private ArrayList<String[]> ordenarCrecientemente(ArrayList<String[]> arrayList, int[] puntos, int[] partGanados) {
        // Realiza la comprobacion de que los puntos, en caso de no estar ordenado canvia la posiocion el los dos arrays
        for (int j = 0;j < (puntos.length - 1);j++) {
            int comparacion  = compararOrdenacionCreciente(puntos, partGanados, j);

            if (comparacion == -1) {
                // Canvio la posicion de los puntos
                int puntuacionMenor = puntos[j];
                puntos[j] = puntos[j + 1];

                puntos[j + 1] = puntuacionMenor;

                // Canvio la posicion de los partidos ganados
                int partGanadorMenor = partGanados[j];
                partGanados[j] = partGanados[j + 1];

                partGanados[j + 1] = partGanadorMenor;

                // Canvio la posicion de los datos del equipo
                String[] datosEquipoMenor = arrayList.get(j);
                
                arrayList.set(j, arrayList.get(j + 1));
                arrayList.set(j + 1, datosEquipoMenor);

                j = -1; // Vuelvo a empezar el bucle (Este es -1 para que al sumarle 1 en el bucle se quede en 0)
            }
        }

        return  arrayList;
    }

    /**
     * Metodo que realiza la comparacion de ordenacion decreciente por puntos y goles a favor
     *
     * @param puntos array de puntos
     * @param partGanados  array de partidos ganados
     * @param posicion posicion del array
     * @return -1 si el array no esta ordenado, 0 si esta ordenado
     */
    private int compararOrdenacionDecreciente(int puntos[], int[] partGanados, int posicion) {
        int comparacion = 0;
        
        if (puntos[posicion + 1] < puntos[posicion]) {comparacion = -1;}
        else if ((puntos[posicion + 1] == puntos[posicion]) && (partGanados[posicion + 1] < partGanados[posicion])) {comparacion = -1;}

        return comparacion;
    }

    /**
     * Metodo que ordena decrecientemente la liga
     *
     * @param arrayList array dinamico con los datos de la liga
     * @param puntos array de puntos
     * @param partGanados array de partidos ganados
     * @return array dinamico con los datos de la liga ordenado decrecientemente
     */
    private ArrayList<String[]> ordenarDecreciente(ArrayList<String[]> arrayList, int[] puntos, int[] partGanados) {
        // Realiza la comprobacion de que los puntos, en caso de no estar ordenado canvia la posiocion el los dos arrays
        for (int j = 0;j < (puntos.length - 1);j++) {
            int comparacion = compararOrdenacionDecreciente(puntos, partGanados, j);

            if (comparacion == -1) {
                // Canvio la posicion de los puntos
                int puntuacionMenor = puntos[j + 1];
                puntos[j + 1] = puntos[j];

                puntos[j] = puntuacionMenor;

                // Canvio la posicion de los partidos ganados
                int partGanadorMenor = partGanados[j + 1];
                partGanados[j + 1] = partGanados[j];

                partGanados[j] = partGanadorMenor;

                // Canvio la posicion de los datos del equipo
                String[] datosEquipoMenor = arrayList.get(j + 1);
                
                arrayList.set(j + 1, arrayList.get(j));
                arrayList.set(j, datosEquipoMenor);

                j = -1; // Vuelvo a empezar el bucle (Este es -1 para que al sumarle 1 en el bucle se quede en 0)
            }
        }
        return  arrayList;
    }

    /**
     * Metodo que borra el contenido del fichero
     *
     * @return 0 si se a borrado correctamente, -1 si a habido un error
     */
    private int borrarContenidoFichero() {
        int mensajeError = 0;
        try {
            FileWriter fileWriter = new FileWriter(NOMBRE_ARCHIVO);

            fileWriter.write("");

            fileWriter.close();
        } catch (Exception e) {
            mensajeError = -1;
        }

        return mensajeError;
    }

    /**
     * Metodo que rescribe el fichero con los datos actuales
     *
     * @param arrayList array dinamico con los datos de la liga
     * @return 0 si se a rescribido correctamente, -1 si a habido un error
     */
    private int rescribirFichero(ArrayList<String[]> arrayList) {
        int mensajeError = 0;

        // Borro el contenido del fichero
        mensajeError = borrarContenidoFichero();

        for (String[] datosEquipo : arrayList) {
            mensajeError = guardarEquipo(datosEquipo[0], Integer.parseInt(datosEquipo[1]), Integer.parseInt(datosEquipo[2]), Integer.parseInt(datosEquipo[3]), Integer.parseInt(datosEquipo[4]), Integer.parseInt(datosEquipo[5]));
        }

        return  mensajeError;
    }

    /**
     * Metodo que busca un equipo en el fichero
     *
     * @param nombreEquipo nombre del equipo
     * @return array con los datos del equipo
     */
    public String[] buscarEquipo(String nombreEquipo) {
        String[] equipoBuscado = null;
        ArrayList<String[]> arrayList = arrayLiga(); // Guardo en un array dinamico los datos de cada equipo

        if (arrayList != null) {
            for (String[] equipo : arrayList) {
                if (equipo[0].equals(nombreEquipo)) {
                    equipoBuscado = equipo;
                }
            }
        }

        return equipoBuscado;
    }

    /**
     * Metodo que borra un equipo del fichero
     *
     * @param nombreEquipo nombre del equipo
     * @return 0 si se a borrado correctamente, -1 si a habido un error
     */
    public int borrarEquipo(String nombreEquipo) {
        int mensajeError = 0;
        ArrayList<String[]> arrayList = arrayLiga(); // Guardo en un array dinamico los datos de cada equipo

        if (arrayList  != null) {
            String[] equipo = buscarEquipo(nombreEquipo);

            if (equipo != null) {

                boolean centinela = false; // Valida que se alla borrado el equipo

                // Bucle que recorre el arrayList asta que se alla borrado el equipo
                for (int i = 0;centinela != true;i++) {
                    if (Arrays.equals(equipo, arrayList.get(i))) { // Cuando los dos  arrays son iguales es porque hemos encontrado al equipo a eliminar
                        arrayList.remove(i);
                        centinela = true;
                    }
                }

                mensajeError = rescribirFichero(arrayList);
            }
            else {mensajeError = -1;}
        }
        else {mensajeError = -1;}

        return mensajeError;
    }

    /**
     * Metodo que modifica un equipo del fichero
     *
     * @param nobreEquipo nombre del equipo
     * @param partJugados partidos jugados
     * @param partGanados partidos ganados
     * @param partEmpatados partidos empatados
     * @param partPerdidos partidos perdidos
     * @param puntos puntos del equipo
     * @return 0 si se a modificado correctamente, -1 si a habido un error
     */
    public int modificarEquipo(String nobreEquipo, int partJugados, int partGanados, int partEmpatados, int partPerdidos, int puntos){
        int mensajeError = 0;
        ArrayList<String[]> arrayList = arrayLiga(); // Guardo en un array dinamico los datos de cada equipo

        String[] equipoParaActualizar = buscarEquipo(nobreEquipo);

        boolean centinela = false;
        for (int i = 0;centinela != true &&  i < arrayList.size() ;i++ ) {
            if (Arrays.equals(equipoParaActualizar, arrayList.get(i))) {

                // Guarda los datos  del nuevo equipo en la posicion correspondiente
                equipoParaActualizar[1] = Integer.toString(partJugados);
                equipoParaActualizar[2] = Integer.toString(partGanados);
                equipoParaActualizar[3] = Integer.toString(partEmpatados);
                equipoParaActualizar[4] = Integer.toString(partPerdidos);
                equipoParaActualizar[5] = Integer.toString(puntos);

                // Rescribe el array con los datos actuales
                arrayList.set(i, equipoParaActualizar);

                centinela = true;
            }
        }

        mensajeError = rescribirFichero(arrayList);

        return mensajeError;
    }
}
