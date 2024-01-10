package com.example.studentchestv1001;

import com.example.studentchestv1001.*;
import com.example.studentchestv1001.AppState;
import com.example.studentchestv1001.DatabaseConnection;
import com.example.studentchestv1001.LoginController;
import com.example.studentchestv1001.SendMoneyController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

public class IbanController {
    @FXML
    public void initialize(){
        init();
    }

    private void init(){
        chAlias.setSelected(false);
        chIBAN.setSelected(false);
        btnBack.setOnAction(event -> {
            try {
                login.switchToMainDisplay(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void removeVBOX(){
        while(vBox.getChildren().size() > 6)
            vBox.getChildren().remove(vBox.getChildren().size() - 1);
    }

    public void checkIBAN(ActionEvent event){
        //no both check boxes can be selected simultaneously
        if(chAlias.isSelected() == true)
            chAlias.setSelected(false);

        removeVBOX();

        //add the corresponding items for checkIBAN
        createLabel("IBAN");
        createTextField("txtIBAN");
        createLabel("Beneficiary's name");
        createTextField("txtBeneficiaryName");

    }

    public void checkAlias(ActionEvent event){
        //no both check boxes can be selected simultaneously
        if(chIBAN.isSelected() == true)
            chIBAN.setSelected(false);

        removeVBOX();

        //add the corresponding items for checkAlias
        createLabel("Phone Number");
        createTextField("txtPhone");
    }

    public void completePayment(ActionEvent event) throws IOException {
        if(chIBAN.isSelected() == false && chAlias.isSelected() == false)
            showAlert("You must select a method: IBAN or Alias Pay!");
        else {
            ok = true;
            //traverse TextField children fo VBox and verify if they are empty or not
            for(Node node : vBox.getChildren()){
                if(node instanceof TextField){
                    TextField textField = (TextField) node;
                    if(textField.getText().isEmpty()){
                        showAlert("You must introduce data in each text field!");
                        ok = false;
                        break;
                    }
                    else{
                        validateTextField(textField);
                    }
                }
            }

            //if everything is correct, update the database
            if(ok == true){
                updateDatabase();
                login.switchToMainDisplay(event);
            }
        }
    }

    private void updateDatabase(){
        String details = "", phone = "", iban = "", name = "";
        double amount = 0;

        for(Node node : vBox.getChildren()){
            if(node instanceof TextField){
                TextField textField = (TextField) node;
                String text = textField.getText();

                //set the data to be inserted from each text field
                switch (textField.getId()){
                    case "txtSum":
                    {
                        amount = Double.parseDouble(text);
                        break;
                    }
                    case "txtPaymentDetails":
                    {
                        details = text;
                        break;
                    }
                    case "txtIBAN":
                    {
                        iban = text;
                        break;
                    }
                    case "txtBeneficiaryName":
                    {
                        name = text;
                        break;
                    }
                    case "txtPhone":
                    {
                        phone = text;
                        break;
                    }
                    default: break;
                }
            }
        }

        try{
            Statement statement = connectNow.createStatement();
            String query = "";
            if(chIBAN.isSelected() == true){
                //update iban_transfer table
                query = String.format("insert into iban_transfer(amount, payment_details, iban, beneficiary_name, idaccount, transfer_date) values (%f, '%s', '%s', '%s', %d, '%s');", amount, details, iban, name, login.getId(), currentTimestamp);
            } else if (chAlias.isSelected() == true) {
                //update alias_pay_transfer table
                query = String.format("insert into alias_pay_transfer(amount, payment_details, phone, idaccount, transfer_date) values (%f, '%s', '%s', %d, '%s');", amount, details, phone, login.getId(), currentTimestamp);
            }
            statement.executeUpdate(query);

            //update the balance after transaction
            updateBalance(main.balance - amount, login.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateBalance(double newBalance, int account){
        String query = String.format("update account set balance = %f where idaccount = %d;", newBalance, account);
        try{
            Statement statement = connectNow.createStatement();
            statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void validateTextField(TextField textField){
        String txtId = textField.getId();
        if(txtId.equals("txtSum")){
            try{
                Double.parseDouble(textField.getText());
            }catch (NumberFormatException e){
                showAlert("Not a valid amount of money!");
                ok = false;
            }
        }
        else if(txtId.equals("txtIBAN")){
            if(textField.getText().length() < 20 || textField.getText().length() > 34){
                showAlert("Invalid IBAN!");
                ok = false;
            }
        } else if(txtId.equals("txtPhone") && textField.getText().length() != 10){
            showAlert("Incorrect phone number!");
            ok = false;
        } else if(txtId.equals("txtPaymentDetails") && textField.getText().length() > 50){
            showAlert("Payment details exceed 50 characters!");
            ok = false;
        }
    }

    private void showAlert(String s){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING!");
        alert.setContentText(s);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void createLabel(String s){
        Label label = new Label(s);
        Font font = new Font("Segoe UI Bold", 20.0);
        label.setFont(font);
        vBox.setMargin(label, new Insets(20,0,0,0));
        vBox.getChildren().add(label);
    }

    private void createTextField(String id){
        TextField textField = new TextField();
        Font font = new Font("Segoe UI Bold", 20.0);
        textField.setFont(font);
        textField.setId(id);
        textField.setMaxWidth(260);
        textField.setPrefWidth(260);
        textField.setMinWidth(260);
        vBox.setMargin(textField, new Insets(5,0,0,0));
        vBox.getChildren().add(textField);
    }


    @FXML
    private Button btnBack;
    @FXML
    private CheckBox chIBAN;
    @FXML
    private CheckBox chAlias;
    @FXML
    private TextField txtSum;
    @FXML
    private TextField txtPaymentDetails;
    @FXML
    private VBox vBox;
    private DatabaseConnection connectDB = new DatabaseConnection();
    private Connection connectNow = connectDB.getConnection();
    private LoginController login = AppState.getLoginController();
    private com.example.studentchestv1001.MainController main = AppState.getMainController();
    private boolean ok;
    private Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
}
