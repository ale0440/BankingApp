import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class IbanController {
    @FXML
    public void initialize(){
        init();
    }

    private void init(){
        chAlias.setSelected(false);
        chIBAN.setSelected(false);
        System.out.println(vBox.getChildren().size());
    }

    public void checkIBAN(ActionEvent event){
        //no both check boxes can be selected simultaneously
        if(chAlias.isSelected() == true)
            chAlias.setSelected(false);


        //delete the corresponding items from checkAlias
        if(vBox.getChildren().size() == 8){
            for(int i = 0; i < 2; i++)
                vBox.getChildren().remove(vBox.getChildren().size() - 1);
        }

        //add the corresponding items for checkIBAN
        createLabel("IBAN");
        createTextField("txtIBAN");
        createLabel("Beneficiary's name");
        createTextField("txtBeneficiaryName");

    }

    public void checkAlias(ActionEvent event){
        //no both check boxes can be selected simultaneously
        if(chIBAN.isSelected() == true)
            chIBAN.setSelected(false);

        //delete the corresponding items from checkIBAN
        if(vBox.getChildren().size() == 10){
            for(int i = 0; i < 4; i++)
             vBox.getChildren().remove(vBox.getChildren().size() - 1);
        }

        //add the corresponding items for checkAlias
        createLabel("Phone Number");
        createTextField("txtPhone");
    }

    public void completePayment(ActionEvent event){
        if(chIBAN.isSelected() == false && chAlias.isSelected() == false)
            showAlert();
        else{
            //update IBAN DATABASE
            System.out.println("Database to be updated");
        }
    }

    private void showAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING!");
        alert.setContentText("You must select a transfer method: IBAN or Alias Pay");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void createLabel(String s){
        Label label = new Label(s);
        Font font = new Font("Segoe UI Bold", 20.0);
        label.setFont(font);
        vBox.setMargin(label, new Insets(20,0,0,0));
        vBox.getChildren().add(label);
    }

    private void createTextField(String id){
        TextField textField = new TextField();
        Font font = new Font("Segoe UI Bold", 20.0);
        textField.setFont(font);
        textField.setId(id);
        textField.setMaxWidth(260);
        textField.setPrefWidth(260);
        textField.setMinWidth(260);
        vBox.setMargin(textField, new Insets(5,0,0,0));
        vBox.getChildren().add(textField);
    }

    public void changeToMainDisplay(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/studentchestv1001/main-display-view.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private CheckBox chIBAN;
    @FXML
    private CheckBox chAlias;
    @FXML
    private TextField txtSum;
    @FXML
    private TextField txtPaymentDetails;
    @FXML
    private VBox vBox;
}
