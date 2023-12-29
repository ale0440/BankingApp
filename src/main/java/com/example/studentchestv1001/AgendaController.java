import com.example.studentchestv1001.DatabaseConnection;
//import com.mysql.cj.jdbc.MysqlSQLXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AgendaController {
    @FXML
    private VBox vBox;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private void initialize(){
        init();
    }

    private void init(){
        DatabaseConnection connectDB = new DatabaseConnection();
        Connection connectNow = connectDB.getConnection();
        String query = "select first_name, last_name, phone from customer;";

        try{
            Statement statement = connectNow.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                String info = resultSet.getString(1) + " " + resultSet.getString(2) + "\n" + resultSet.getString(3);
                addButtonToVBox(info);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addButtonToVBox(String info){
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
                paymentDetails();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        vBox.setMargin(button, new Insets(10,0,0,0));
        vBox.getChildren().add(button);
    }

    @FXML
    private void paymentDetails() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/example/studentchestv1001/send-money-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //APPLICATION MODAL means you cannot interact with other window until this new window is closed
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void makePayement(ActionEvent event) throws IOException {
        //update the database

        /*
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/example/studentchestv1001/main-display-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

         */
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
                    addButtonToVBox(info);
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
}
