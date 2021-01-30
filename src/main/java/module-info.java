module br.edu.ifsp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires java.sql;
    requires sqlite.jdbc;

    opens br.edu.ifsp to javafx.fxml;
    exports br.edu.ifsp;
    exports br.edu.ifsp.domain.ui;
    exports br.edu.ifsp.domain.controller;
}