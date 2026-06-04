module org.example.proyectointerfaces {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.proyectointerfaces to javafx.fxml;
    exports org.example.proyectointerfaces;
}