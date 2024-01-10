package com.example.studentchestv1001;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.OutputStream;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginControllerTest extends ApplicationTest{

    private LoginController loginController;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Parent root = loader.load();
        loginController = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Test
    void rememberData() throws SQLException {
            clickOn(loginController.txtUsername).write("owen.anderson");
            clickOn(loginController.txtPassword).write("password1");
            loginController.setUsernamePassword();
            loginController.rememberData(null);
            assertEquals("owen.anderson", loginController.txtUsername.getText());

    }

}
