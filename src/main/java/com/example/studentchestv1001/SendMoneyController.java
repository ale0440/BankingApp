package com.example.studentchestv1001;

import com.example.studentchestv1001.AppState;
import com.example.studentchestv1001.DatabaseConnection;
import com.example.studentchestv1001.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

public class SendMoneyController {
    @FXML
    public void initialize(){
        init();
    }

    private void init(){
        txtPayment.setText("Transfer StudentChest");
    }

    public void initializeData(String phone, int type) {
        this.phoneNumber = phone;
        this.type = type;
    }

    public void makePayement(ActionEvent event) throws IOException {
        try{
            Statement statement = connectNow.createStatement();
            double amount = 0;
            if(!txtAmount.getText().isEmpty())
                amount = Double.parseDouble(txtAmount.getText());
            else
                showAlert();
            String payment = txtPayment.getText();
            account1 = login.getId(); account2 = 0;

            //find the id of account2
            String query1 = String.format("select idcustomer from customer where phone = '%s';", phoneNumber);
            ResultSet resultSet = statement.executeQuery(query1);
            if(resultSet.next()){
                account2 = resultSet.getInt(1);
            }
            String typeString = "";
            if(type == 1) {
                typeString = "send";

                //UPDATE DATABASE BALANCE
                //for account1
                double balance1 = getBalance(account1);
                //for account2
                double balance2 = getBalance(account2);
                updateBalance(balance1 - amount, account1);
                updateBalance(balance2 + amount, account2);
            }
            else typeString = "request";
            String query = String.format("insert into transfer(account1, account2, type, amount, payment_details) values('%d','%d','%s','%f', '%s');", account1, account2, typeString, amount, payment);
            statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    private double getBalance(int account){
        double balance = 0;
        String query = "select balance from account where idaccount = " + account;
        try{
            Statement statement = connectNow.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next())
                balance = resultSet.getDouble(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return balance;
    }

    private void updateBalance(double newBalance, int account){
        String query = String.format("update account set balance = %f where idaccount = %d;", newBalance, account);
        try{
            Statement statement = connectNow.createStatement();
            statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void showAlert(){
        String s = "You must introduce an amount!";
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING!");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }

    private String phoneNumber;
    private int type; // 1 - send, 2 - request
    @FXML
    private TextField txtAmount;
    @FXML
    private TextField txtPayment;

    public int account1, account2;

    private DatabaseConnection connectDB = new DatabaseConnection();
    private Connection connectNow = connectDB.getConnection();
    private LoginController login = AppState.getLoginController();
}
