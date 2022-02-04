package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    public RegisterModel Register = new RegisterModel();
    public loginModel Login = new loginModel();
    @FXML
    AnchorPane Rpane;

    @FXML
    private Label isConnected;

    @FXML
    private TextField txt_userName;

    @FXML
    private TextField txt_password1;

    @FXML
    private TextField txt_password2;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_phoneNumber;
    @FXML
    RadioButton RadioBtnSeller,  RadioBtnCustomer;
    String roleDB;

    @FXML
    ImageView imageR;

    /**
     * initialize() method
     * the initialize() method has access to the @FXML fields and is called after the @FXML members
     * in this method I am able to populate the image view as well as set the background colors for the various anchor panes and panes
     * All pre populated labels and text fields are defined in this method
     * and this methods calls other methods within the RegisterController class which need to be initialized on creation of the screen
     * */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("https://cdn3.iconfinder.com/data/icons/user-interface-2-8/34/169-512.png");
        imageR.setImage(image);
        Rpane.styleProperty().set("-fx-background-color: #DCAE96");

    }
    /**
     * GetRole() method
     * radio buttons which get data from user
     * */
    public void  GetRole(){
        if (RadioBtnCustomer.isSelected()){
            roleDB = "customer";
        } else if(RadioBtnSeller.isSelected()){
            roleDB = "seller";
        }
    }


    /**
     * AppRegister() method
     * AppRegiser method calls various methods from the register model class which does checks
     * to see if the data entered by the user are correct
     * this method also contains the code that returns the user to the login FXML page
     * */

    public void AppRegister(javafx.event.ActionEvent actionEvent) throws SQLException {
        GetRole();
        if (Register.validEmail(txt_email.getText())) {
            if (Register.validPhoneNumber(txt_phoneNumber.getText())) {
                if (Register.validPassword(txt_password1.getText())) {
                    if (Register.passwordMatch(txt_password1.getText(), txt_password2.getText())) {

                        if (Login.isUser(txt_email.getText(), txt_phoneNumber.getText())) {
                            isConnected.setText("email/phone already registered");
                        } else {
                            if (Register.isRegistered(txt_userName.getText(), txt_email.getText(), txt_phoneNumber.getText(), txt_password1.getText(), roleDB)) {
                                try {
                                    ((Node) actionEvent.getSource()).getScene().getWindow().hide();
                                    Stage primaryStage = new Stage();
                                    Pane root = FXMLLoader.load(getClass().getResource("login.fxml"));
                                    Scene scene = new Scene(root);
                                    primaryStage.setScene(scene);
                                    primaryStage.show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                isConnected.setText("Registration failed");
                            }
                        }
                    } else {
                        isConnected.setText("passwords do not match");
                    }
                }else {
                    isConnected.setText("invalid Password : a digit must occur at least once\n a lower case letter must occur at least once\n an upper case letter must occur at least once\n a special character must occur at least once\n no whitespace allowed in the entire string\n password must be least eight places though");
                }
            }else{
                isConnected.setText("invalid phone number");
            }
        }else{
            isConnected.setText("invalid email address");
        }


    }
    /**
     * Home() method
     * this method contains the code that returns the user back to the StoreFrontController FXML page
     * here the Admin FXML stage is closed and the StroreFrontController FXML is displayed
     * this method also contains data that gets parsed to the StoreFrontController() method
     * */
    public void Home(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StoreFront.fxml"));
            Parent viewParent =  (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(viewParent));
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * LoginPage() method
     * this method contains the code that returns the user back to the login FXML page
     * here the Register FXML stage is closed and the login FXML is displayed
     * */

    public void LoginPage(ActionEvent event)  {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent viewParent =  (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(viewParent));
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
