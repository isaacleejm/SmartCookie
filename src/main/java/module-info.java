module cab302.iirtt.assignment1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.net.http;
    requires java.sql;
    requires jdk.jshell;


    opens cab302.iirtt.assignment1 to javafx.fxml;
    exports cab302.iirtt.assignment1;
}