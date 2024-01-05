package com.example.studentchestv1001;

import com.example.studentchestv1001.AppState;
import com.example.studentchestv1001.DatabaseConnection;
import com.example.studentchestv1001.LoginController;
import com.example.studentchestv1001.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CardSettingsController {
    @FXML
    public void initialize(){
        updateButtonState();
        init();
    }

    private void init(){
        //initialize buttons
        setButton(btnBlock, true, "activ");
        setButton(btnUnblock, false, "inactiv");
        btnBack.setOnAction(event -> {
            try {
                login.switchToMainDisplay(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        try{
            //set card data
            this.lblCardName.setText(main.lblCardName.getText());
            this.lblCardNumber.setText(main.lblCardNumber.getText());

            //sest current payment limits and card name
            String query = "select card_name, payment_limit, withdraw_limit from card where idcard = " + login.getId();
            Statement statement = connectNow.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                txtCardName.setPromptText(resultSet.getString(1));
                txtPaymentLimit.setPromptText(Double.toString(resultSet.getDouble(2)));
                txtWithdrawLimit.setPromptText(Double.toString(resultSet.getDouble(3)));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        AppState.setCardSettingsController(this);
    }

    private void setButton(Button button, boolean isBlocked, String status){
        button.setOnAction(event -> {
            try {
                cardStatus(event, isBlocked, status);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void cardStatus(ActionEvent event, boolean isBlocked, String status) throws IOException {
        setIsCardBlocked(isBlocked);
        updateCardStatus(status);
        login.switchToMainDisplay(event);
    }

    public void updateDatabase(ActionEvent event) throws IOException {
        String newCardName;
        Double newPaymentLimit, newWithdrawLimit;
        if(!txtCardName.getText().isEmpty())
            newCardName = txtCardName.getText();
        else
            newCardName = txtCardName.getPromptText();
        if(!txtPaymentLimit.getText().isEmpty())
            newPaymentLimit = Double.parseDouble(txtPaymentLimit.getText());
        else
            newPaymentLimit = Double.parseDouble(txtPaymentLimit.getPromptText());
        if(!txtWithdrawLimit.getText().isEmpty())
            newWithdrawLimit = Double.parseDouble(txtWithdrawLimit.getText());
        else
            newWithdrawLimit = Double.parseDouble(txtWithdrawLimit.getPromptText());

        try{
            String query = String.format("update card set card_name = '%s', payment_limit = %f, withdraw_limit = %f where idcard = %d;", newCardName, newPaymentLimit, newWithdrawLimit, login.getId());
            Statement statement = connectNow.createStatement();
            statement.executeUpdate(query);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateCardStatus(String status){
        try{
            String query = String.format("update card set status = '%s' where idcard = %d;", status, login.getId());
            Statement statement = connectNow.createStatement();
            statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateButtonState() {
        isCardBlocked = main.cardBlocked;
        if(isCardBlocked == true){
            lblBlock.setText("Unblock your card anytime.");
        }else lblBlock.setText("Hard times, but you can block your card.");
        btnChangeName.setDisable(isCardBlocked);
        btnLimit.setDisable(isCardBlocked);
        btnUnblock.setVisible(isCardBlocked);
        btnBlock.setVisible(!isCardBlocked);
    }

    public void setIsCardBlocked (boolean ok){
        isCardBlocked = ok;
    }


    @FXML
    private Button btnChangeName;
    @FXML
    private Button btnLimit;
    @FXML
    public Button btnBlock;
    @FXML
    public Button btnUnblock;
    @FXML
    public Button btnBack;
    @FXML
    public Label lblCardNumber;
    @FXML
    public Label lblCardName;
    @FXML
    public Label lblBlock;
    @FXML
    private TextField txtCardName;
    @FXML
    private TextField txtPaymentLimit;
    @FXML
    private TextField txtWithdrawLimit;

    private DatabaseConnection connectDB = new DatabaseConnection();
    private Connection connectNow = connectDB.getConnection();
    private LoginController login = AppState.getLoginController();
    private MainController main = AppState.getMainController();

    public boolean isCardBlocked;
}
