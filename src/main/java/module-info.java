module org.example.proyectointerfaces {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.proyectointerfaces to javafx.fxml;
    opens org.example.proyectointerfaces.controllers to javafx.fxml;
    opens org.example.proyectointerfaces.database to javafx.base;
    exports org.example.proyectointerfaces;
    opens org.example.proyectointerfaces.database.modelos to javafx.base;
}