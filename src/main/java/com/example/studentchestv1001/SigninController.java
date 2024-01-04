import com.example.studentchestv1001.DatabaseConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class SigninController {
    @FXML
    public void initialize(){
        init();
    }

    private void init(){
        setLogInLabel();
    }

    private int getNewId() throws SQLException {
        String query = "select idcustomer from customer order by idcustomer desc LIMIT 1;";
        Statement statement = connectNow.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        else return 0;
    }

    private boolean validatePersonalInfo(){
        boolean ok = true;
        for(Node node : vBox1.getChildren()){
            if(node instanceof TextField){
                TextField textField = (TextField) node;
                String text = textField.getText();
                if(text.isEmpty()){
                    showAlert("All data fields must be completed!");
                    ok = false;
                    break;
                }
                if(textField.getId().equals("txtCNP")){
                    if(text.length() != 13){
                        showAlert("Invalid CNP!");
                        ok = false;
                        break;
                    }
                }
                if(textField.getId().equals("txtPhone")){
                    if(text.length() != 10){
                        showAlert("Invalid phone number!");
                        ok = false;
                        break;
                    }
                }
                if(textField.getId().equals("txtAddress") && text.length() > 100){
                    showAlert("Address exceeds 100 allowed characters!");
                    ok = false;
                    break;
                }
                if((textField.getId().equals("txtFirstName") || textField.getId().equals("txtLastName") || textField.getId().equals("txtEmail")) && text.length() > 45){
                    showAlert("First name or last name or email exceeds 45 allowed characters!");
                    ok = false;
                    break;
                }
            }
        }
        return ok;
    }

    private boolean validateAccountInfo(){
        boolean ok = true;
        for(Node node : vBox2.getChildren()){
            if(node instanceof TextField){
                TextField textField = (TextField) node;
                String text = textField.getText();
                if(text.isEmpty()){
                    showAlert("All data fields must be completed!");
                    ok = false;
                    break;
                }
                if(textField.getId().equals("txtUsername")){
                    if(text.length() > 20){
                        showAlert("Username exceeds 20 characters allowed!");
                        ok = false;
                        break;
                    }
                }
                if(textField.getId().equals("txtPassword")){
                    if(text.length() > 50){
                        showAlert("Password exceeds 50 characters allowed!");
                        ok = false;
                        break;
                    }
                }
                if(textField.getId().equals("txtPin") && text.length() != 4){
                    showAlert("Invalid pin!");
                    ok = false;
                    break;
                }
            }
        }
        return ok;
    }

    private void showAlert(String s){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING!");
        alert.setContentText(s);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public void createUser(ActionEvent event) throws SQLException, IOException {
        if(validatePersonalInfo() == true && validateAccountInfo() == true){
            String first_name = txtFirstName.getText(), last_name = txtLastName.getText(), cnp = txtCNP.getText();
            String address = txtAddress.getText(), phone = txtPhone.getText(), email = txtEmail.getText();
            String username = txtUsername.getText(), password = txtPassword.getText(), pin = txtPin.getText();
            int id = getNewId() + 1;
            cardName = first_name + " " + last_name;

            String query = String.format("insert into customer(idcustomer, first_name, last_name, cnp, address, phone, email_address) values (%d, '%s','%s','%s','%s','%s','%s');", id, first_name, last_name, cnp, address, phone, email);
            String query1 = String.format("insert into user_login(iduser_login, idcustomer, username, password_hash, pin) values (%d, %d, '%s', '%s', '%s');", id, id, username, password, pin);
            try {
                Statement statement = connectNow.createStatement();
                statement.executeUpdate(query);
                statement.executeUpdate(query1);
                statement.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
            createCardAndAccount(id);
            switchToLogIn((Node) event.getSource());
        }
    }

    private void createCardAndAccount(int id) {
        randomCard();
        randomAccount();
        String query = String.format("insert into card(idcard, card_number, expiry_date, cvv, card_name) values(%d, '%s', '%s', '%s', '%s');", id, cardNumber, expiryDate, cvv, cardName);
        String query1 = String.format("insert into account(idaccount, idcustomer, iban, swift, idcard) values(%d, %d, '%s', '%s', %d);",id, id, iban, swift, id);
        try {
            Statement statement = connectNow.createStatement();
            statement.executeUpdate(query);
            statement.executeUpdate(query1);
            statement.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void randomCard(){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        //card number
        for(int i=0;i<16;i++){
            if(i > 0 && i % 4 == 0){
                stringBuilder.append('-');
            }
            int digit = random.nextInt(10);
            stringBuilder.append(digit);
        }
        cardNumber = stringBuilder.toString();

        //cvv
        cvv = String.valueOf(random.nextInt(900) + 100);

        //expiry date
        stringBuilder.setLength(0);
        stringBuilder.append(random.nextInt(1));
        stringBuilder.append(random.nextInt(10));
        stringBuilder.append('/');
        stringBuilder.append(random.nextInt(99) + 24);
        expiryDate = stringBuilder.toString();
    }

    private void randomAccount(){
        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        //iban
        stringBuilder.append("RO");
        for(int i = 0; i < 2; i++){
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        stringBuilder.append("STCH");
        for(int i = 0; i < 18; i++){
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        iban = stringBuilder.toString();

        //swift
        stringBuilder.setLength(0);
        stringBuilder.append("STCHRO");
        stringBuilder.append(random.nextInt(90) + 10);
        swift = stringBuilder.toString();
    }

    private void setLogInLabel(){
        lblLogIn.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1) {
                try {
                    switchToLogIn(lblLogIn);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void switchToLogIn(Node node) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/studentchestv1001/login-view.fxml"));
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtCNP;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtConfirmPassword;
    @FXML
    private TextField txtPin;
    @FXML
    private Button btnSignIn;
    @FXML
    private Label lblLogIn;
    @FXML
    private VBox vBox1;
    @FXML
    private VBox vBox2;
    private String cardName = "";
    private String cardNumber = "";
    private String cvv = "";
    private String expiryDate = "";
    private String iban = "";
    private String swift = "";


    private DatabaseConnection connectDB = new DatabaseConnection();
    private Connection connectNow = connectDB.getConnection();

}
