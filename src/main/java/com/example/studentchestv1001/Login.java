package com.example.studentchestv1001;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Stack;

public class Login {
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnLogin;

    private SceneController sceneController;

    public Login(){
        sceneController = new SceneController();
    }

    @FXML
    private void init(){
        btnLogin.setOnAction(this::handleLoginButton);
    }

    private void handleLoginButton(ActionEvent event){
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if(username != null && !username.isEmpty() && username.length() <= 20 &&
                password != null && !password.isEmpty() && password.length() <= 20) {
                validateLogin(event);
        }
        else
            showErrorAlert("Invalid credentials. Please re-enter both username and password (max 20 characters).");
    }

    private void showErrorAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }

    public void validateLogin(ActionEvent event) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "select count(1) from user_login where username = '" + txtUsername.getText() + "' and password_hash = '" + txtPassword.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    //update the entire app with the current logged-in user information
                    //PROFILE window
                    Profile profile = new Profile();
                    profile.setLoggedInUser(txtUsername.getText());

                    //go to the Main Display window
                    sceneController.switchToMainDisplay(event);
                }else{
                    showErrorAlert("Invalid login. Please try again!");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}