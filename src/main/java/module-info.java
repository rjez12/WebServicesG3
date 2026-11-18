
module com.desarrollo.webservices {

    requires javafx.controls;
    requires javafx.fxml;

    requires java.net.http;

    requires com.fasterxml.jackson.databind;

    opens com.desarrollo.webservices
            to javafx.fxml;

    opens com.desarrollo.webservices.controller
            to javafx.fxml;

    opens com.desarrollo.webservices.model
            to javafx.base, com.fasterxml.jackson.databind;

    exports com.desarrollo.webservices;
}
