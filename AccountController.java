package sample;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.scene.image.ImageViewBuilder.*;

public class AccountController implements Initializable {
    DatabaseConnection connectionNow = new DatabaseConnection();
    Connection connectDB = connectionNow.getConnection();
    public RegisterModel Register = new RegisterModel();
    public loginModel Login = new loginModel();
    @FXML
    AnchorPane AccountPane;
    @FXML
    Pane pPane;
    @FXML
    Label txt_Role, txt_Email, txt_Name, txt_Phone, status;
    @FXML
    TextField txf_Name, txf_Email, txf_Phone;
    @FXML
    RadioButton RadioBtnSeller,  RadioBtnCustomer;
    @FXML
    Button btnUpdate, btnDelete;
    String email;
    String role;
    String role11;
    String email1;
    @FXML
    ImageView AccountImage;
    /**
     * initialize() method
     * the initialize() method has access to the @FXML fields and is called after the @FXML members
     * in this method I am able to populate the image view as well as set the background colors for the various anchor panes and panes
     * All pre populated labels and text fields are defined in this method
     * and this methods calls other methods within the AccountController class which need to be initialized on creation of the screen
     * */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("https://image.flaticon.com/icons/png/512/61/61205.png");
        AccountImage.setImage(image);
        pPane.styleProperty().set("-fx-background-color: #add8e6");
        AccountPane.styleProperty().set("-fx-background-color: #DCAE96");


    }

    public void print(){
        System.out.println(email);
        System.out.println(role);
    }

    /**
     * funk() method
     * this method recieves information from the StoreFrontController class
     * and also performs a SELECT  query on the userdbinfo table to populate the relevant labels
     * */
    public void funk(String innit, String rl){
        txt_Email.setText(innit);
        txt_Role.setText(rl);
        email =innit;
        role = rl;

        PreparedStatement ps2 = null;
        ResultSet rs2 = null;
        String query2 = "SELECT name, phone FROM userdbinfo WHERE email= ?";
        try {
            ps2 = connectDB.prepareStatement(query2);
            ps2.setString(1, innit);
            rs2 = ps2.executeQuery();
            while (rs2.next()) {
                txt_Name.setText(rs2.getString("name"));
                txt_Phone.setText(rs2.getString("phone"));
            }


        } catch (Exception e){
            e.printStackTrace();
        }
        txf_Name.setText(txt_Name.getText());
        txf_Email.setText(txt_Email.getText());
        txf_Phone.setText(txt_Phone.getText());

    }
    /**
     * onDelete() method
     * this method produces a pop up window that allows the user to delete information from the database
     * a delete query is executed and the relevant information is removed from the database
     * */
    public void onDelete(javafx.event.ActionEvent actionEvent) {
        Stage popup = new Stage();
        VBox box = new VBox();
        box.setSpacing(30);
        box.setPadding(new Insets(10,10,10,10));
        Label enterPassword = new Label("Enter Password");
        PasswordField pField = new PasswordField();
        Button enterButton = new Button("Enter");
        enterPassword.setFont((new Font("Arial Bold", 20)));
        enterButton.setMaxSize(500, 500);
        box.getChildren().add(enterPassword);
        box.getChildren().add(pField);
        box.getChildren().add(enterButton);
        Scene stageScene = new Scene(box, 350,  290);
        String title = "Password Authentification";
        popup.setTitle(title);
        popup.setScene(stageScene);
        popup.show();

        enterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String pword = "";
               String p =  pField.getText();
               PreparedStatement ps2 = null;
               ResultSet rs2 = null;
               String query2 = "SELECT password FROM userdbinfo WHERE email= ?";
               try {
                   ps2 = connectDB.prepareStatement(query2);
                   ps2.setString(1, email);
                   rs2 = ps2.executeQuery();
                   while (rs2.next()) {
                       pword = rs2.getString("password");
                   }

                   if (p.equals(pword)){
                       popup.close();
                       Stage dmessage = new Stage();
                       VBox b = new VBox();
                       Label success = new Label("Account deleted succesfully");
                       success.setFont((new Font("Arial Bold", 20)));
                       b.getChildren().add(success);
                       Scene ns = new Scene(b, 280, 50);
                       dmessage.setScene(ns);
                       PauseTransition delay = new PauseTransition(Duration.seconds(1));
                       delay.setOnFinished(e -> dmessage.hide());
                       dmessage.initStyle(StageStyle.UNDECORATED);
                       dmessage.show();
                       delay.play();
                       PreparedStatement state = null;
                       PreparedStatement state1 = null;
                       //ResultSet set = null;
                       String query = "DELETE FROM userdbinfo WHERE email =? and password =?";
                       String qu = "DELETE FROM sellerinfo WHERE seller_email = ?";
                       state = connectDB.prepareStatement(query);
                       state1 = connectDB.prepareStatement(qu);
                       state.setString(1, email);
                       state.setString(2, pword);
                       state.executeUpdate();


                       state1.setString(1, email);
                       state1.executeUpdate();
                       txt_Email.setText("");





                       txt_Name.setText("");
                       txt_Role.setText("");
                       txt_Phone.setText("");

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
                       Label inccorrect = new Label("password attempt failed, please try again.");
                       inccorrect.setFont((new Font("Arial Bold", 15)));
                       box.getChildren().add(inccorrect);
                       System.out.println(":(");
                   }


                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });




    }
    /**
     * OnAddChanges() method
     * this method performs update query in userdbinfo table in the database
     * the Register.validEmail and Register.validPhone methods are called from the Register model class
     * to verify the data entered in by the user
     * */

    public void OnAddChanges(ActionEvent event) throws SQLException {




        String name1 = txf_Name.getText();
        email1 = txf_Email.getText();
        String phone1 = txf_Phone.getText();
        if (RadioBtnCustomer.isSelected()){
            role11 = "customer";
        } else if (RadioBtnSeller.isSelected()){
            role11 = "seller";
        }

        if (Register.validEmail(email1)){
            if (Register.validPhoneNumber(phone1)){
              //  if (Login.isUser(email1, phone1)){
                //    status.setText("email/phone already registered");
               // } else {
                    Stage popup = new Stage();
                    VBox box = new VBox();
                    box.setSpacing(30);
                    box.setPadding(new Insets(10,10,10,10));
                    Label enterPassword = new Label("Enter Password");
                    PasswordField pField = new PasswordField();
                    Button enterButton = new Button("Enter");
                    enterPassword.setFont((new Font("Arial Bold", 20)));
                    enterButton.setMaxSize(500, 500);
                    box.getChildren().add(enterPassword);
                    box.getChildren().add(pField);
                    box.getChildren().add(enterButton);
                    Scene stageScene = new Scene(box, 350,  290);
                    String title = "Password Authentification";
                    popup.setTitle(title);
                    popup.setScene(stageScene);
                    popup.show();

                    enterButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String pword = "";
                            String p = pField.getText();
                            PreparedStatement ps2 = null;
                            ResultSet rs2 = null;
                            String query2 = "SELECT password FROM userdbinfo WHERE email= ?";
                            try {
                                ps2 = connectDB.prepareStatement(query2);
                                ps2.setString(1, email);
                                rs2 = ps2.executeQuery();
                                while (rs2.next()) {
                                    pword = rs2.getString("password");
                                }
                                if (p.equals(pword)) {
                                    popup.close();
                                    Stage dmessage = new Stage();
                                    VBox b = new VBox();
                                    Label success = new Label("Account updated succesfully");
                                    success.setFont((new Font("Arial Bold", 20)));
                                    b.getChildren().add(success);
                                    Scene ns = new Scene(b, 280, 50);
                                    dmessage.setScene(ns);
                                    PauseTransition delay = new PauseTransition(Duration.seconds(1));
                                    delay.setOnFinished(e -> dmessage.hide());
                                    dmessage.initStyle(StageStyle.UNDECORATED);
                                    dmessage.show();
                                    delay.play();

                                    PreparedStatement pst = null;
                                    String query = "UPDATE userdbinfo SET name = ?, email = ?, phone = ?, role = ? WHERE password = ?";
                                    pst = connectDB.prepareStatement(query);
                                    pst.setString(1, name1);
                                    pst.setString(2, email1);
                                    pst.setString(3, phone1);
                                    pst.setString(4, role11);
                                    pst.setString(5, p);
                                    pst.executeUpdate();

                                    PreparedStatement pst1 = null;
                                    String query1 = "UPDATE sellerinfo SET seller_name = ?, seller_email = ? WHERE seller_name = ?";
                                    pst1 = connectDB.prepareStatement(query1);
                                    pst1.setString(1, name1);
                                    pst1.setString(2,email1);
                                    pst1.setString(3,txt_Name.getText());
                                    pst1.executeUpdate();


                                    txf_Email.setText("");
                                    txf_Phone.setText("");
                                    txf_Name.setText("");
                                    RadioBtnCustomer.isDisable();
                                    RadioBtnSeller.isDisable();

                                    txt_Name.setText(name1);
                                    txt_Phone.setText(phone1);
                                    txt_Role.setText(role11);
                                    txt_Email.setText(email1);




                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });



                }
            } else {
                status.setText("invalid phone number");
            }
       // } else {
         //   status.setText("invalid email address");
        //}
    }
    /**
     * bntReturnHome() method
     * this method contains the code that returns the user back to the StoreFrontController FXML page
     * here the Admin FXML stage is closed and the StroreFrontController FXML is displayed
     * this method also contains data that gets parsed to the StoreFrontController() method
     * */
    public void bntReturnHome(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StoreFront.fxml"));
            Parent viewParent =  (Parent) loader.load();
            StoreFrontController sc = loader.getController();

                sc.getUser2(txt_Email.getText(), txt_Role.getText());

            Stage stage = new Stage();
            stage.setScene(new Scene(viewParent));
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }


    }

    /**
     * btnChangePassword() method
     * this method produces a pop up window that allows the user to change there password
     * The password data is entered in by the user into the relavant text fields and the Register Model class is called to
     * to perform the passwordMatch() and isValidPassword() methods
     * an UPDATE query is perfomed in userdbinfo table to udpdate the users password
     * */

    public void btnChangePassword(javafx.event.ActionEvent actionEvent) {
        Stage popup = new Stage();
        VBox box = new VBox();
        box.setSpacing(30);
        box.setPadding(new Insets(10,10,10,10));
        Label enterPassword = new Label("Enter Password");
        Label enterNew = new Label("Enter new password");
        Label enterConfirm = new Label("Confirm new password");
        PasswordField pFieldOriginal = new PasswordField();
        PasswordField pFieldNew = new PasswordField();
        PasswordField pFieldConfirm = new PasswordField();
        Button enterButton = new Button("Enter");
        enterPassword.setFont((new Font("Arial Bold", 20)));
        enterNew.setFont((new Font("Arial Bold", 20)));
        enterConfirm.setFont((new Font("Arial Bold", 20)));
        enterButton.setMaxSize(500, 500);
        box.getChildren().add(enterPassword);
        box.getChildren().add(pFieldOriginal);
        box.getChildren().add(enterNew);
        box.getChildren().add(pFieldNew);
        box.getChildren().add(enterConfirm);
        box.getChildren().add(pFieldConfirm);
        box.getChildren().add(enterButton);
        Scene stageScene = new Scene(box, 550,  590);
        String title = "Password Change";
        popup.setTitle(title);
        popup.setScene(stageScene);
        popup.show();

        enterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String pNew = pFieldNew.getText();
                String pConfirm = pFieldConfirm.getText();
                String p =  pFieldOriginal.getText();
                String pword = "";
                PreparedStatement ps2 = null;
                ResultSet rs2 = null;
                String query2 = "SELECT password FROM userdbinfo WHERE email= ?";
                try {
                    ps2 = connectDB.prepareStatement(query2);
                    ps2.setString(1, email);
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        pword = rs2.getString("password");
                    }

                    if (p.equals(pword)) {
                      if (Register.validPassword(pNew)){
                          if (Register.passwordMatch(pNew, pConfirm)){
                              PreparedStatement ps = null;
                              String q = "UPDATE userdbinfo SET password = ? WHERE password =?";

                                  ps = connectDB.prepareStatement(q);
                                  ps.setString(1, pNew);
                                  ps.setString(2, pword);
                                  ps.executeUpdate();
                                  popup.close();
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
                              Label inccorrect = new Label("passwords do not match, please try again.");
                              inccorrect.setFont((new Font("Arial Bold", 15)));
                              box.getChildren().add(inccorrect);
                          }
                      } else {
                          Label inccorrect = new Label("invalid Password : a digit must occur at least once\n a lower case letter must occur at least once\n an upper case letter must occur at least once\n a special character must occur at least once\n no whitespace allowed in the entire string\n password must be least eight places though\"");
                          inccorrect.setFont((new Font("Arial Bold", 15)));
                          box.getChildren().add(inccorrect);
                      }
                    } else {
                        Label inccorrect = new Label("incorrect password, please try again.");
                        inccorrect.setFont((new Font("Arial Bold", 15)));
                        box.getChildren().add(inccorrect);
                    }
                    } catch (Exception e){
                        e.printStackTrace();
                }

            }
        });


    }
}
