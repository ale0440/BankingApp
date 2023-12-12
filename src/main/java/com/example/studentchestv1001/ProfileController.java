package com.example.studentchestv1001;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.LongFunction;

public class ProfileController {
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtEmail;

    @FXML
    private void initialize(){
        init();
    }
    private void init(){
        DatabaseConnection connectDB = new DatabaseConnection();
        Connection connectNow = connectDB.getConnection();
        LoginController login = AppState.getLoginController();

        String query = "select first_name, last_name, phone, email_address from customer where idcustomer = " + login.getId();
        try{
            Statement statement = connectNow.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                txtFirstName.setText(resultSet.getString("first_name"));
                txtLastName.setText(resultSet.getString("last_name"));
                txtPhone.setText(resultSet.getString("phone"));
                txtEmail.setText(resultSet.getString("email_address"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void changeToMainDisplay(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-display-view.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
