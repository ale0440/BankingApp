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
    public Label lblCardNumber;
    @FXML
    public Label lblCardName;
    @FXML
    private TextField txtCardName;
    @FXML
    private TextField txtPaymentLimit;
    @FXML
    private TextField txtWithdrawLimit;
    @FXML
    private Button btnBlock;
    @FXML
    private Button btnUnblock;
    private DatabaseConnection connectDB = new DatabaseConnection();
    private Connection connectNow = connectDB.getConnection();
    private LoginController login = AppState.getLoginController();

    @FXML
    public void initialize(){
        init();
    }

    private void init(){

        btnUnblock.setVisible(false);

        try{
            MainController main = AppState.getMainController();
            //LoginController login = AppState.getLoginController();
            this.lblCardName.setText(main.lblCardName.getText());
            this.lblCardNumber.setText(main.lblCardNumber.getText());

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
    }

    public void change(ActionEvent event) throws IOException {
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

    public void blockCard(ActionEvent event) throws IOException {
        
    }

    public void changeToMainDisplay(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/studentchestv1001/main-display-view.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
