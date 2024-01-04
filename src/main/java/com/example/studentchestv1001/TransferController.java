package com.example.studentchestv1001;

import com.example.studentchestv1001.AppState;
import com.example.studentchestv1001.DatabaseConnection;
import com.example.studentchestv1001.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

public class TransferController {
    @FXML
    private void initialize(){
        init();
    }

    private void init(){
        AppState.setTransferController(this);
        setChoiceBox();
    }

    private void setHistory(){
        String query = "select account1, account2, type, amount, payment_details, transfer_date from transfer;";
        int index = -1;
        try{
            Statement statement = connectNow.createStatement();
            ResultSet queryResult = statement.executeQuery(query);
            String labelText = "";
            while(queryResult.next()){
                labelText = "";
                if(mode == 0)
                    index = 0;
                else
                    index++;
                if(queryResult.getInt(1) == login.getId()){
                    //if account1 is the logged in user's id => add a label to the anchor pane with the
                    //message: "You sent/requested $$ to account2's card name"

                    //creating the string to be displayed on the label
                    labelText = "You ";
                    int type = 1;
                    if("send".equals(queryResult.getString(3))){
                        labelText = labelText + "sent ";
                    }else{
                        labelText = labelText + "requested ";
                        type = 2;
                    }
                    labelText += queryResult.getDouble(4);
                    if(type == 1){
                        labelText += " to ";
                    }else labelText += " from ";

                    String queryName = "select first_name, last_name from customer c join account a on a.idcustomer = c.idcustomer join transfer t on t.account2 = a.idaccount where t.account2 = '"+ queryResult.getInt(2) +"'";
                    Statement statement1 = connectNow.createStatement();
                    ResultSet queryResult1 = statement1.executeQuery(queryName);
                    String name = "";
                    if(queryResult1.next()){
                        name = queryResult1.getString(1) + " " + queryResult1.getString(2);
                    }
                    labelText += name;
                    statement1.close();
                    queryResult1.close();
                    if(index >= 0 && index <= vBox.getChildren().size())
                        addLabel(labelText, queryResult.getString(5) + "\n" + queryResult.getString(6), index);
                }
                else if(queryResult.getInt(2) == login.getId()){
                    //if account2 is the logged in user's id => add a label to the anchor pane with the
                    //message: "account1's name sent you $$"
                    String queryName = "select first_name, last_name from customer c join account a on a.idcustomer = c.idcustomer join transfer t on t.account1 = a.idaccount where t.account1 = '"+ queryResult.getInt(1) +"'";
                    Statement statement1 = connectNow.createStatement();
                    ResultSet queryResult1 = statement1.executeQuery(queryName);
                    String name = "";
                    if(queryResult1.next()){
                        name = queryResult1.getString(1) + " " + queryResult1.getString(2);
                    }
                    labelText += name + " sent you " + queryResult.getDouble(4);
                    statement1.close();
                    queryResult1.close();

                        addLabel(labelText, queryResult.getString(5) + "\n" + queryResult.getString(6), index);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setChoiceBox(){
        choiceBox.getItems().addAll(sort);
        choiceBox.setOnAction(this::setOrder);
        choiceBox.setValue(sort[0]);
        choiceBox.fireEvent(new ActionEvent(choiceBox, ActionEvent.NULL_SOURCE_TARGET));
    }

    private void setOrder(ActionEvent event){
        vBox.getChildren().clear();
        if(choiceBox.getValue().equals(sort[0]))
            mode = 0; //newest
        else
            mode = 1; //oldest
        setHistory();
    }

    private void addLabel(String labelText, String paymentDetails, int index){

        //creating the new label
        Label label = new Label(labelText);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        label.setStyle("-fx-background-color:  #c9c9c9;");
        label.setWrapText(true);
        vBox.setMargin(label, new Insets(10,10,0,10));
        label.setPadding(new Insets(5,10,5,10));
        label.setMinHeight(60);
        label.setPrefHeight(60);
        label.setMaxHeight(60);
        label.setPrefWidth(315);

        Tooltip tooltip = new Tooltip(paymentDetails);
        Tooltip.install(label,tooltip);

        vBox.getChildren().add(index, label);
    }

    public void sendMoneyTo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("com/example/studentchestv1001/agenda-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        com.example.studentchestv1001.AgendaController controller = loader.getController();
        controller.setType(1);
    }

    public void requestMoneyFrom(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("com/example/studentchestv1001/agenda-view.fxml"));
        Parent root = loader.load();
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/example/studentchestv1001/agenda-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        com.example.studentchestv1001.AgendaController controller = loader.getController();
        controller.setType(2);
    }

    public void changeToMainDisplay(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/studentchestv1001/main-display-view.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vBox;
    @FXML
    private ChoiceBox<String> choiceBox;
    private String[] sort = {"Newest", "Oldest"};
    private int mode = 0;
    private DatabaseConnection connectDB = new DatabaseConnection();
    private Connection connectNow = connectDB.getConnection();
    private LoginController login = AppState.getLoginController();
}
