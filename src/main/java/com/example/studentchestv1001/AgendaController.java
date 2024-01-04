package com.example.studentchestv1001;

import com.example.studentchestv1001.AppState;
import com.example.studentchestv1001.DatabaseConnection;
import com.example.studentchestv1001.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class AgendaController {
    public void setType(int type){
        this.type = type;
    }
    @FXML
    private void initialize(){
        init();
    }

    private void init(){
        //initialize the choice box
        choiceBox.getItems().addAll(filter);
        choiceBox.setValue(filter[0]);
        choiceBox.setOnAction(this::setAgenda);
        choiceBox.fireEvent(new ActionEvent(choiceBox, ActionEvent.NULL_SOURCE_TARGET));
    }

    private void setAgenda(ActionEvent event){
        Statement statement;
        ResultSet resultSet;
        String query = "", info = "";
        vBox.getChildren().clear();
        if(choiceBox.getValue().equals(filter[1])){
            query = "select account2, count(*) as interaction_count from transfer where account1 = " + login.getId() + " " +
                    "group by account2 " +
                    "order by interaction_count desc;";

            try{
                statement = connectNow.createStatement();
                resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    String query1 = "select first_name, last_name, phone from customer where idcustomer = " + resultSet.getInt(1);
                    Statement statement1 = connectNow.createStatement();
                    ResultSet resultSet1 = statement1.executeQuery(query1);
                    if(resultSet1.next()){
                        info = resultSet1.getString(1) + " " + resultSet1.getString(2) + "\n" + resultSet1.getString(3);
                        addButtonToVBox(info, resultSet1.getString(3));
                    }
                }
                statement.close();
                resultSet.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        } else if (choiceBox.getValue().equals(filter[0])) {
            query = "select first_name, last_name, phone, idcustomer from customer order by first_name;";
            try {
                statement = connectNow.createStatement();
                resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    if(resultSet.getInt(4) != login.getId()){
                        info = resultSet.getString(1) + " " + resultSet.getString(2) + "\n" + resultSet.getString(3);
                        addButtonToVBox(info, resultSet.getString(3));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void addButtonToVBox(String info, String phone){
        Button button = new Button();

        Label label = new Label(info);
        label.setAlignment(Pos.CENTER);
        label.setContentDisplay(ContentDisplay.CENTER);
        label.setPrefWidth(300);
        label.setMinHeight(70);
        label.setPrefHeight(70);
        label.setMaxHeight(70);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setFont(Font.font("Segoe UI Bold", 20));

        button.setMnemonicParsing(false);
        button.setGraphic(label);
        button.setOnAction(e -> {
            try {
                paymentDetails(phone);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        vBox.setMargin(button, new Insets(10,0,0,0));
        vBox.getChildren().add(button);
    }

    @FXML
    private void paymentDetails(String phone) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("com/example/studentchestv1001/send-money-view.fxml"));
        Parent root = loader.load();
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/example/studentchestv1001/send-money-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //APPLICATION MODAL means you cannot interact with other window until this new window is closed
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

        com.example.studentchestv1001.SendMoneyController sendMoneyController = loader.getController();
        sendMoneyController.initializeData(phone, type);
    }

    public void switchToTransfer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/example/studentchestv1001/transfer-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static String transfromString(String input){
        return input.replaceAll("\\s", "").toLowerCase();
    }

    public void search(ActionEvent event) throws IOException {
        String who = txtSearch.getText();
        who = transfromString(who);

        DatabaseConnection connectDB = new DatabaseConnection();
        Connection connectNow = connectDB.getConnection();
        String query = "select first_name, last_name, phone from customer;";
        vBox.getChildren().clear();
        try{
            Statement statement = connectNow.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String info = resultSet.getString(1) + " " + resultSet.getString(2);
                String transformed = transfromString(info);
                if(transformed.contains(who)){
                    //display that person

                    info += "\n" + resultSet.getString(3);
                    addButtonToVBox(info, resultSet.getString(3));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showAgenda(ActionEvent event) throws IOException {
        vBox.getChildren().clear();
        init();
    }


    public int type;
    @FXML
    private VBox vBox;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private ChoiceBox<String> choiceBox;
    private String phoneNumber;
    private String[] filter = {"Alphabetic", "Frequently used"};
    private DatabaseConnection connectDB = new DatabaseConnection();
    private Connection connectNow = connectDB.getConnection();
    private LoginController login = AppState.getLoginController();
}
