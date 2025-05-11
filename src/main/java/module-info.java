module cab302.iirtt.assignment1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.net.http;
    requires jdk.jshell;
    requires org.xerial.sqlitejdbc;
    requires java.sql;
    requires jdk.compiler;
    requires java.desktop;


    opens cab302.iirtt.assignment1 to javafx.fxml;
    exports cab302.iirtt.assignment1;
    exports cab302.iirtt.assignment1.controller;
    opens cab302.iirtt.assignment1.controller to javafx.fxml;
    exports cab302.iirtt.assignment1.model;
    opens cab302.iirtt.assignment1.model to javafx.fxml;
}