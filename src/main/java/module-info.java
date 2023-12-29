module com.example.studentchestv1001 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires de.jensd.fx.glyphs.fontawesome;
    requires de.jensd.fx.glyphs.commons;

    opens com.example.studentchestv1001 to javafx.fxml;
    exports com.example.studentchestv1001;
}