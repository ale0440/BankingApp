import com.example.studentchestv1001.AppState;
import com.example.studentchestv1001.DatabaseConnection;
import com.example.studentchestv1001.LoginController;
import com.example.studentchestv1001.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TransactionController {

    @FXML
    public void initialize(){
        init();
    }

    private void init(){
        this.lblCardName.setText(main.lblCardName.getText());
        this.lblCardNumber.setText(main.lblCardNumber.getText());

        //initialize the scroll pane with the transactions
        try{
            DatabaseConnection connectDB = new DatabaseConnection();
            Connection connectNow = connectDB.getConnection();
            LoginController login = AppState.getLoginController();

            //all transactions from database
            String query = "select idaccount, amount, transaction_date, trader, code, address, type from transaction where idaccount = " + login.getId();
            Statement statement = connectNow.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String text1 = String.format("%s\n%s", resultSet.getString(4), resultSet.getString(6));
                String date = resultSet.getString(3);
                char sign = '+';
                if(resultSet.getString(7).equals("payed"))
                    sign = '-';
                String text2 = String.format("%c%.2f\n%s",sign,resultSet.getDouble(2), date.substring(0, 10));
                String details = String.format("%s\n%s\n%s", resultSet.getString(6), date, resultSet.getString(5));
                HBox hBox = createHBox(text1, text2, details);
                vBox.getChildren().add(hBox);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    private HBox createHBox(String text1, String text2, String details){
        //create the HBOX
        HBox hBox = new HBox();
        hBox.setPrefHeight(50);
        hBox.setPrefWidth(200);
        hBox.setStyle("-fx-background-color: c9c9c9;");
        HBox.setHgrow(hBox, Priority.ALWAYS);
        vBox.setMargin(hBox, new Insets(5, 5,5,5));

        //creating the first label
        Label label1 = createLabel(text1, "Segoe UI Bold",  Pos.CENTER_LEFT,  280, TextAlignment.LEFT);
        hBox.setHgrow(label1, Priority.ALWAYS);
        //creating the second label
        Label label2 = createLabel(text2, "Segoe UI Bold Italic", Pos.CENTER_RIGHT,  160, TextAlignment.RIGHT);
        hBox.setHgrow(label1, Priority.NEVER);

        Tooltip tooltip = new Tooltip(details);
        Tooltip.install(label1,tooltip);

        hBox.getChildren().addAll(label1, label2);

        return hBox;
    }

    private Label createLabel(String text, String fontName, Pos alignment, double prefWidth, TextAlignment textAlignment){
        Label label = new Label(text);
        label.setFont(new javafx.scene.text.Font(fontName, 14));
        label.setAlignment(alignment);
        label.setTextAlignment(textAlignment);
        label.setWrapText(true);
        label.setPrefWidth(prefWidth);
        label.setPadding(new Insets(5, 5, 0, 5));
        return label;
    }

    public void changeToMainDisplay(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/studentchestv1001/main-display-view.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    private MainController main = AppState.getMainController();
    private LoginController login = AppState.getLoginController();
    @FXML
    public Label lblCardNumber;
    @FXML
    public Label lblCardName;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vBox;
}
