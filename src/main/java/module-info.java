module org.example.friendrecommendationsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens org.example.friendrecommendationsystem to javafx.fxml;
    exports org.example.friendrecommendationsystem;
    exports org.example.friendrecommendationsystem.controllers;
    opens org.example.friendrecommendationsystem.controllers to javafx.fxml;
}