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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Optional;

public class WithdrawController {

    @FXML
    public void initialize(){
        init();
    }

    private void init(){
        btnBack.setOnAction(event -> {
            try {
                login.switchToMainDisplay(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        this.lblCardName.setText(main.lblCardName.getText());
        this.lblCardNumber.setText(main.lblCardNumber.getText());
        this.lblPhone.setText(main.phone);
        setAmount(amount);
    }

    private void setAmount(double amount){
        txtAmount.setText(String.valueOf(amount));
    }

    public void increaseSum(ActionEvent event){
        amount+=10;
        setAmount(amount);
    }

    public void decreaseSum(ActionEvent event){
        amount-=10;
        setAmount(amount);
    }

    public void makeWithdrawal(ActionEvent event) throws  IOException{
        amount = Double.parseDouble(txtAmount.getText());

        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Withdraw");
        alert.setContentText("Did you manage to complete the withdrawal?");
        ButtonType buttonTypeOne = new ButtonType("YES");
        ButtonType buttonTypeTwo = new ButtonType("CANCEL IT");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> restult = alert.showAndWait();

        if(restult.isPresent()){
            String status = "";
            if(restult.get() == buttonTypeOne){
                status = "completed";
            } else if(restult.get() == buttonTypeTwo){
                status = "canceled";
                amount = 0;
            }
            updateDatabase(status);
        }

        login.switchToMainDisplay(event);
    }

    private void updateDatabase(String status){
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        String query = "update account set balance = " + (main.balance - amount) + " where idaccount = " + login.getId();
        String query1 = String.format("insert into withdraw(idaccount, amount, phone, status, withdraw_date) values(%d, %f, '%s', '%s', '%s');", login.getId(), amount, main.phone, status, currentTimestamp);
        try{
            Statement statement = connectNow.createStatement();
            statement.executeUpdate(query);
            statement.executeUpdate(query1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void seeBalance(ActionEvent event) throws IOException {
        if(time%2==0){
            lblMoney.setText(String.valueOf(main.balance));
        }
        else
            lblMoney.setText("$$$");
        time++;
    }

    private int time = 0;
    private MainController main = AppState.getMainController();
    private LoginController login = AppState.getLoginController();
    @FXML
    public Button btnBack;
    @FXML
    public Label lblCardNumber;
    @FXML
    public Label lblCardName;
    @FXML
    public Label lblMoney;
    @FXML
    public Label lblPhone;
    @FXML
    public TextField txtAmount;
    private double amount = 50;
    private DatabaseConnection connectDB = new DatabaseConnection();
    private Connection connectNow = connectDB.getConnection();
}
