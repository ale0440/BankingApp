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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainController {
    @FXML
    private void initialize(){
        updateButtons();
        init();
    }

    private void init(){
        DatabaseConnection connectDB = new DatabaseConnection();
        Connection connectNow = connectDB.getConnection();
        LoginController login = AppState.getLoginController();

        String query = "select card_number, balance from account a join customer c on a.idcustomer = c.idcustomer join card ca on ca.idaccount = a.idaccount where c.idcustomer = " + login.getId();
        String query1 = "select card_name from card c where c.idaccount = " + login.getId();
        // String query = "select first_name, last_name, card_number, balance from account a join customer c on a.idcustomer = c.idcustomer join card ca on ca.idaccount = a.idaccount where c.idcustomer = " + login.getId();
        try{
            Statement statement = connectNow.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                Statement statement1 = connectNow.createStatement();
                ResultSet resultSet1 = statement1.executeQuery(query1);
                if(resultSet1.next())
                     lblCardName.setText(resultSet1.getString(1));
                cardNumber = resultSet.getString(1);
                cardNumber = maskCardNumber(cardNumber);
                lblCardNumber.setText(cardNumber);
                balance = resultSet.getDouble(2);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        AppState.setMainController(this);
    }

    public void seeBalance(ActionEvent event) throws IOException{
        if(time%2==0){
            lblMoney.setText(String.valueOf(balance));
        }
        else
            lblMoney.setText("$$$");
        time++;
    }

    public String maskCardNumber(String input){
        if(input != null && input.length() >=4){
            String lastFour = input.substring(input.length() - 4);
            return "****"+lastFour;
        }
        else
            return input;
    }

    public void switchToProfile(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/studentchestv1001/profile-view.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDetails(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/studentchestv1001/details-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHistory(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/studentchestv1001/history-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToIBAN(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/studentchestv1001/IBAN-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToTransfer(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/studentchestv1001/transfer-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToWithdraw(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/studentchestv1001/withdraw-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSettings(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/studentchestv1001/card-settings-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateButtons() {
        if(settings != null) {
            cardBlocked = settings.isCardBlocked;
            btnTransfer.setDisable(settings.isCardBlocked);
            btnDetails.setDisable(settings.isCardBlocked);
            btnHistory.setDisable(settings.isCardBlocked);
            btnIBAN.setDisable(settings.isCardBlocked);
            btnWithdraw.setDisable(settings.isCardBlocked);
        }
    }


    @FXML
    public Button btnTransfer;
    @FXML
    public Button btnHistory;
    @FXML
    public Button btnDetails;
    @FXML
    public Button btnSettings;
    @FXML
    public Button btnWithdraw;
    @FXML
    public Button btnIBAN;
    @FXML
    public Label lblCardName;
    @FXML
    public Label lblCardNumber;
    @FXML
    private Label lblMoney;

    private double balance;
    private int time = 0;
    public String cardNumber;
    public boolean cardBlocked = false;
    private com.example.studentchestv1001.CardSettingsController settings = AppState.getCardSettingsController();

}
