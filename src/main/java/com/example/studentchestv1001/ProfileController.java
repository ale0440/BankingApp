package com.example.studentchestv1001;

import com.example.studentchestv1001.AppState;
import com.example.studentchestv1001.DatabaseConnection;
import com.example.studentchestv1001.LoginController;
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
        //String update = String.format() +

        try{
            Statement statement = connectNow.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email_address");

                if (firstName != null) {
                    txtFirstName.setText(firstName);
                } else {
                    txtFirstName.clear();
                }
                //txtFirstName.setText(resultSet.getString("first_name"));
                if (lastName != null) {
                    txtLastName.setText(lastName);
                } else {
                    txtLastName.clear();
                }
                //txtLastName.setText(resultSet.getString("last_name"));
                if (phone != null) {
                    txtPhone.setText(phone);
                } else {
                    txtPhone.clear();
                }
                //txtPhone.setText(resultSet.getString("phone"));
                if (email != null) {
                    txtEmail.setText(email);
                } else {
                    txtEmail.clear();
                }
                //txtEmail.setText(resultSet.getString("email_address"));
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

    public void updateData(ActionEvent event) throws IOException {
        DatabaseConnection connectDB = new DatabaseConnection();
        Connection connectNow = connectDB.getConnection();
        LoginController login = AppState.getLoginController();
        try{
            Statement statement = connectNow.createStatement();
            String query = String.format("update customer set first_name = '%s', last_name = '%s', phone = '%s', email_address = '%s' where idcustomer = " + login.getId(), txtFirstName.getText(), txtLastName.getText(), txtPhone.getText(), txtEmail.getText());
            statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
