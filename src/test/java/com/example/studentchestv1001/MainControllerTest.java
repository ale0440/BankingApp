package com.example.studentchestv1001;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainControllerTest extends ApplicationTest {
    MainController mainController;
    LoginController loginController;
    FXMLLoader loader;

    @Override
    public void start(Stage stage) throws Exception {
        loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Parent root = loader.load();
        loginController = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void goToMainDisplay() throws IOException {
        clickOn(loginController.txtUsername).write("owen.anderson");
        clickOn(loginController.txtPassword).write("password1");
        clickOn(loginController.btnLogin);

        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("main-display-view.fxml"));
        Parent mainRoot = mainLoader.load();
        mainController = mainLoader.getController();


        Platform.runLater(() -> {
            Stage mainStage = new Stage();
            mainStage.setScene(new Scene(mainRoot));
            mainStage.show();
        });

    }

    @Test
    public void testSeeBalance() throws IOException {
        goToMainDisplay();
        assertEquals("$$$", mainController.lblMoney.getText());
        mainController.balance = 100.0; // Set an initial balance
        mainController.seeBalance(null); // Call the method once

        assertEquals("100.0", mainController.lblMoney.getText()); // Check if the balance is displayed correctly

        mainController.balance = 150.0; // Set a different balance
        mainController.seeBalance(null);

        assertEquals("$$$", mainController.lblMoney.getText());
    }


}
