module jv.interfazligafutbol {
    requires javafx.controls;
    requires javafx.fxml;


    opens jv.interfazligafutbol to javafx.fxml;
    exports jv.interfazligafutbol;
}