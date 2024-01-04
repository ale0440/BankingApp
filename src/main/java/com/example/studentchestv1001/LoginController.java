package com.example.studentchestv1001;

import com.example.studentchestv1001.AppState;
import com.example.studentchestv1001.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

//import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.*;

public class LoginController {
    @FXML
    public void initialize(){
        init();
    }

    private void init(){
        setSignInLabel();
        String query = "select username, password, checked from remember_me;";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                if(resultSet.getBoolean(3) == true){
                    //chRememberMe.setSelected(true);
                    txtUsername.setText(resultSet.getString(1));
                    txtPassword.setText(resultSet.getString(2));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setSignInLabel(){
        EventHandler<MouseEvent> clickedLabel = event -> {
            if(event.getClickCount() == 1){
                try {
                    switchToSignIn(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        lblSignIn.setOnMouseClicked(clickedLabel);
    }

    public void rememberData(ActionEvent event) throws SQLException {
        setUsernamePassword();
        //find if the username and password introduced are in the database
        String query1 = "select username, password_hash from user_login";
        boolean ok = false;
        try{
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(query1);
            while(resultSet.next() && ok == false){
                if(resultSet.getString(1).equals(username) && resultSet.getString(2).equals(password))
                    ok = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        if(ok == true){
            String query = String.format("update remember_me set username = '%s', password = '%s' where id = 1;", username, password);
            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(query);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(chRememberMe.isSelected() == true){
            query1 = "update remember_me set checked = true";

        }else{
            query1 = "update remember_me set checked = false";
        }
        Statement statement1 = connectDB.createStatement();
        statement1.executeUpdate(query1);
    }

    private void setUsernamePassword(){
        if(!txtUsername.getText().isEmpty())
            username = txtUsername.getText();
        if(!txtPassword.getText().isEmpty())
            password = txtPassword.getText();
    }

    public void changeToMainDisplay(ActionEvent event) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if(username != null && !username.isEmpty() && username.length() <= 20 &&
                password != null && !password.isEmpty() && password.length() <= 20) {
            validate(event);
        }
        else
            showErrorAlert("Invalid credentials. Please re-enter both username and password (max 20 characters).");
    }

    private void showErrorAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }

    public void validate(ActionEvent event){
        String verifyLogin = "select count(1) from user_login where username = '" + txtUsername.getText() + "' and password_hash = '" + txtPassword.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            Statement statement1 = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    findId(statement1);
                    AppState.setLoginController(this);

                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/studentchestv1001/main-display-view.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }else{
                    showErrorAlert("Invalid login. Please try again!");
                }
            }

            String query = "update remember_me set checked = " + chRememberMe.isSelected();
            statement1.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void findId(Statement statement) {
        try{
            String queryID = "select idcustomer from user_login where username = '" + txtUsername.getText() + "'";
            ResultSet resultID = statement.executeQuery(queryID);
            if(resultID.next()){
                id = resultID.getInt(1);
            }
            else
                System.out.println("Cannot get the ID");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    private void switchToSignIn(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/studentchestv1001/signin-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    public CheckBox chRememberMe;
    @FXML
    public Label lblSignIn;

    public int id;
    private String username = "", password = "";

    private DatabaseConnection connectNow = new DatabaseConnection();
    private Connection connectDB = connectNow.getConnection();
}
