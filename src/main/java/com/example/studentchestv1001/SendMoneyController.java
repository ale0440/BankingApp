import com.example.studentchestv1001.AppState;
import com.example.studentchestv1001.DatabaseConnection;
import com.example.studentchestv1001.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SendMoneyController {
    private String phoneNumber;
    private int type; // 1 - send, 2 - request
    @FXML
    private TextField txtAmount;
    @FXML
    private TextField txtPayment;

    public void initializeData(String phone, int type) {
        this.phoneNumber = phone;
        this.type = type;
    }

    public void makePayement(ActionEvent event) throws IOException {
        //update the database
        DatabaseConnection connectDB = new DatabaseConnection();
        Connection connectNow = connectDB.getConnection();
        LoginController login = AppState.getLoginController();
        try{
            Statement statement = connectNow.createStatement();

            double amount = Double.parseDouble(txtAmount.getText());
            String payment = txtPayment.getText();
            int account1 = login.getId(), account2 = 0;

            //find the id of account2
            String query1 = String.format("select idcustomer from customer where phone = '%s';", phoneNumber);
            ResultSet resultSet = statement.executeQuery(query1);
            if(resultSet.next()){
                account2 = resultSet.getInt(1);
            }
            String typeString = "";
            if(type == 1)
                typeString = "send";
            else typeString = "request";
            String query = String.format("insert into transfer(account1, account2, type, amount, payment_details) values('%d','%d','%s','%f', '%s');", account1, account2, typeString, amount, payment);
            statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
