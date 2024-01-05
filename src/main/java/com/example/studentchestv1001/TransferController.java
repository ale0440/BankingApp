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
import javafx.scene.control.Button;
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

        //set buttons' actions
        setButton(btnSend, 1);
        setButton(btnRequest, 2);
        btnBack.setOnAction(event -> {
            try{
                login.switchToMainDisplay(event);
            }catch (IOException e){
                e.printStackTrace();
            }
        });
    }

    private void setButton(Button button, int type){
        button.setOnAction(event -> {
            try{
                selectUser(event, type);
            }catch (IOException e){
                e.printStackTrace();
            }
        });
    }

    private void setChoiceBox(){
        choiceBox.getItems().addAll(sort);
        choiceBox.setOnAction(this::setOrder); //set specific action for choice box
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

    private void setHistory(){
        String query = "select account1, account2, type, amount, payment_details, transfer_date from transfer where account1 = " + login.getId() + " or account2 = " + login.getId();
        int index = -1;
        int type = 0; //no delete option for label request
        try{
            //retrieve all rows from transfer table, where the current user is participant in the transfer
            Statement statement = connectNow.createStatement();
            ResultSet queryResult = statement.executeQuery(query);

            String labelText;
            while(queryResult.next()){
                type = 0;
                labelText = "";

                //set the place where the label to be added in the vbox to correspond for "newest" or for "oldest"
                if(mode == 0)
                    index = 0;
                else
                    index++;

                if(queryResult.getInt(1) == login.getId()){
                    //if account1 is the logged in user => add a label to the anchor pane with the
                    //message: "You sent/requested $$ to/from account2's name"

                    //creating the string to be displayed on the label
                    labelText = "You ";
                    if("send".equals(queryResult.getString(3))){
                        labelText += "sent to ";
                    }else{
                        labelText += "requested from ";
                        type = 1;
                    }
                    labelText += getAccountName(queryResult.getInt(2)) + " " + queryResult.getDouble(4) + "$";

                    if(index >= 0 && index <= vBox.getChildren().size())
                        addLabel(labelText, queryResult.getString(5), queryResult.getString(6), index, type);
                }
                else if(queryResult.getInt(2) == login.getId()){
                    //if account2 is the logged in user's id => add a label to the anchor pane with the
                    //message: "account1's name sent/(requested from) you $$"

                    //creating the string to be displayed on the label
                    labelText = getAccountName(queryResult.getInt(1));
                    if(queryResult.getString(3).equals("send"))
                        labelText += " sent you ";
                    else{
                        labelText += " requested from you ";
                        type = 1;
                    }

                    labelText += queryResult.getDouble(4) + "$";

                    if(index >= 0 && index <= vBox.getChildren().size())
                        addLabel(labelText, queryResult.getString(5), queryResult.getString(6), index, type);
                }
            }
            statement.close();
            queryResult.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getAccountName(int id){
        String queryName = "select first_name, last_name from customer c where idcustomer = " + id;
        String name = "";
        try {
            Statement statement = connectNow.createStatement();
            ResultSet queryResult = statement.executeQuery(queryName);
            if (queryResult.next())
                name = queryResult.getString(1) + " " + queryResult.getString(2);
            statement.close();
            queryResult.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return name;
    }

    private void addLabel(String labelText, String details, String date, int index, int type){
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

        label.setTooltip(new Tooltip(details + "\n" + date));

        if(type == 1) //add option to delete the request
        {
            label.setOnMouseClicked(event -> {
                deleteTransferRequest(label, date, index);
            });
        }

        vBox.getChildren().add(index, label);
    }

    private void deleteTransferRequest(Label label, String date, int index){
        String query = "delete from transfer where transfer_date = '" + date + "'";
        try{
            //delete from database
            Statement statement = connectNow.createStatement();
            statement.executeUpdate(query);

            //delete from vbox
            vBox.getChildren().remove(index);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void selectUser(ActionEvent event, int type) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("com/example/studentchestv1001/agenda-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        com.example.studentchestv1001.AgendaController controller = loader.getController();
        controller.setType(type);
    }


    @FXML
    private Button btnSend;
    @FXML
    private Button btnRequest;
    @FXML
    private Button btnBack;
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
