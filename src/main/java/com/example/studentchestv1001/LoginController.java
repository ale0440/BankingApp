package com.example.studentchestv1001;

import com.example.studentchestv1001.AppState;
import com.example.studentchestv1001.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.*;

public class LoginController {
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;

    public int id;

    public void changeToMainDisplay(ActionEvent event) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if(username != null && !username.isEmpty() && username.length() <= 20 &&
                password != null && !password.isEmpty() && password.length() <= 20) {
            validate(event);
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

    public void validate(ActionEvent event){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "select count(1) from user_login where username = '" + txtUsername.getText() + "' and password_hash = '" + txtPassword.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    findId(statement);
                    queryResult.close();
                    statement.close();
                    AppState.setLoginController(this);

                    Parent root = FXMLLoader.load(getClass().getResource("main-display-view.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }else{
                    showErrorAlert("Invalid login. Please try again!");
                    queryResult.close();
                    statement.close();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void findId(Statement statement) {
        try{
            String queryID = "select idcustomer from user_login where username = '" + txtUsername.getText() + "'";
            ResultSet resultID = statement.executeQuery(queryID);
            if(resultID.next()){
                id = resultID.getInt(1);
            }
            else
                System.out.println("Cannot get the ID");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }
}
