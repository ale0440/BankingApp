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
import javafx.scene.control.Button;
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
    private void initialize(){
        init();
    }
    private void init(){
        setButton(btnBack, "main-display-view.fxml");
        setButton(btnSignOut,"login-view.fxml");
        setButtonsCardBlocked();

        String query = "select first_name, last_name, phone, email_address from customer where idcustomer = " + login.getId();

        try{
            Statement statement = connectNow.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email_address");

                if (firstName != null)
                    txtFirstName.setText(firstName);
                else txtFirstName.clear();
                if (lastName != null)
                    txtLastName.setText(lastName);
                else txtLastName.clear();
                if (phone != null)
                    txtPhone.setText(phone);
                else txtPhone.clear();
                if (email != null)
                    txtEmail.setText(email);
                else txtEmail.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setButton(Button button, String newScene){
        button.setOnAction(event -> {
            try {
                controller.changeToAnotherScene(event, newScene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void setButtonsCardBlocked(){
        btnUpdate.setDisable(controller.cardBlocked);
        txtEmail.setEditable(!controller.cardBlocked);
        txtPhone.setEditable(!controller.cardBlocked);
        txtLastName.setEditable(!controller.cardBlocked);
        txtFirstName.setEditable(!controller.cardBlocked);
    }

    public void updateData(ActionEvent event) throws IOException {
        try{
            Statement statement = connectNow.createStatement();
            String query = String.format("update customer set first_name = '%s', last_name = '%s', phone = '%s', email_address = '%s' where idcustomer = " + login.getId(), txtFirstName.getText(), txtLastName.getText(), txtPhone.getText(), txtEmail.getText());
            statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSignOut;
    private com.example.studentchestv1001.MainController controller = AppState.getMainController();
    private DatabaseConnection connectDB = new DatabaseConnection();
    private Connection connectNow = connectDB.getConnection();
    private LoginController login = AppState.getLoginController();

}
