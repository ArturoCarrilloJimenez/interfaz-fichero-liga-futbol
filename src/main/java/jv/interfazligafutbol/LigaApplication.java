package jv.interfazligafutbol;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 * Clase que inicia la aplicacion
 *
 * @version 1.0
 * @since 2024
 * @author Arturo Carrillo Jimenez
 */
public class LigaApplication extends Application {

    private static Scene scene;

    /**
     * Metodo que inicia la aplicacion
     *
     * @param stage Stage
     * @throws IOException Excepcion
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("index-liga-view"), 640, 400);
        stage.setTitle("Liga de futbol");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metodo que cambia la vista
     *
     * @param fxml String
     * @throws IOException Excepcion
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Metodo que carga el archivo FXML
     *
     * @param fxml String
     * @return Parent
     * @throws IOException Excepcion
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LigaApplication.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Metodo que inicia la aplicacion
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        launch();
    }
}