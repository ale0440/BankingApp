package com.example.studentchestv1001;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.*;

public class Profile {
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnUpdate;
    String loggedInUser;
    private SceneController sceneController;

    public Profile(){
        sceneController = new SceneController();
    }

    public void setLoggedInUser(String username){
        loggedInUser = username;
    }

    @FXML
    private void init(){
        btnUpdate.setOnAction(this::printUsername);
    }

    private void printUsername(ActionEvent event){
        //database connection
        //DatabaseConnection connectNow = new DatabaseConnection();
        //Connection connectDB = connectNow.getConnection();

        //String query = "select username, password_hash from user_login where username = '" + loggedInUser + "'";

        System.out.println(loggedInUser);

        /*
        try{
            PreparedStatement statement = connectDB.prepareStatement(query);
            statement.setString(1, loggedInUser);
            ResultSet queryResult = statement.executeQuery();

            if(queryResult.next()){
                txtFirstName.setText(queryResult.getString("first_name"));
                txtLastName.setText(queryResult.getString("last_name"));
                txtPhone.setText(queryResult.getString("phone"));
                txtEmail.setText(queryResult.getString("email_address"));
            }

            //close resources
            queryResult.close();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        */
    }
}
