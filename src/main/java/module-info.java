module com.example.oopp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires jasperreports;


    opens com.example.oopp to javafx.fxml;
    exports com.example.oopp;
    exports jasper;
    opens jasper to javafx.fxml;
}