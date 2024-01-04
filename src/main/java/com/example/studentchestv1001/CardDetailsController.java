package com.example.studentchestv1001;

import com.example.studentchestv1001.AppState;
import com.example.studentchestv1001.CardSettingsController;
import com.example.studentchestv1001.DatabaseConnection;
import com.example.studentchestv1001.LoginController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CardDetailsController {
    @FXML
    public void initialize(){
        init();
    }

    private void init(){
        //data initialization
        DatabaseConnection connectDB = new DatabaseConnection();
        Connection connectNow = connectDB.getConnection();
        LoginController login = AppState.getLoginController();

        String query = "select balance, cvv, card_number, expiry_date from account a join  card c on c.idcard = a.idcard where c.idcard = " + login.getId();
        try{
            Statement statement = connectNow.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                balance = resultSet.getDouble(1);
                lblCVV.setText(resultSet.getString(2));
                lblCardNumber.setText(resultSet.getString(3));
                lblDate.setText(resultSet.getString(4));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //timer initialization
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            updateTimerLabel();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    private void updateTimerLabel() {
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        lblTime.setText(String.format("%02d:%02d", minutes,seconds));
        remainingSeconds--;

        if(remainingSeconds < 0){
                Platform.runLater(() -> {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/example/studentchestv1001/main-display-view.fxml"));
                        Scene scene = new Scene(root);
                        Stage current = (Stage)lblTime.getScene().getWindow();
                        current.setScene(scene);
                        current.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                timeline.stop();
        }
    }

    public void seeBalance(ActionEvent event) throws IOException {
        if(time%2==0){
            lblMoney.setText(String.valueOf(balance));
        }
        else
            lblMoney.setText("$$$");
        time++;
    }

    public void changeToMainDisplay(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/studentchestv1001/main-display-view.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Label lblMoney;
    @FXML
    private Label  lblCVV;
    @FXML
    private Label  lblCardNumber;
    @FXML
    private Label  lblDate;
    @FXML
    private Label  lblTime;
    private double balance;
    private int time = 0;
    private int remainingSeconds = 15;
    private Timeline timeline;
}
