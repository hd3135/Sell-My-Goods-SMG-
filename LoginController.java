package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    DatabaseConnection connectionNow = new DatabaseConnection();
    Connection connectDB = connectionNow.getConnection();
    String role;
    @FXML
    AnchorPane Lpane;
    @FXML
    TextField email, password;
    @FXML
    Label isConnected;
    @FXML
    ImageView loginImage;

    /**
     * initialize() method
     * the initialize() method has access to the @FXML fields and is called after the @FXML members
     * in this method I am able to populate the image view as well as set the background colors for the various anchor panes and panes
     * All pre populated labels and text fields are defined in this method
     * and this methods calls other methods within the LoginController class which need to be initialized on creation of the screen
     * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ-7f0rs8Iv0vbzwN5VI0Jj1zUJlqeCGX9gBQ&usqp=CAU");
        loginImage.setImage(image);
        Lpane.styleProperty().set("-fx-background-color: #DCAE96");

    }
    /**
     * AppLogin() method
     * this method logs the user in by comparing data enetered by user to data already existing in the database
     * this method aslo contains the code that returns the user back to the StoreFrontController FXML page if data is correct
     * here the Login FXML stage is closed and the StroreFrontController FXML is displayed
     * this method also contains data that gets parsed to the StoreFrontController() method
     * */

    public void AppLogin(javafx.event.ActionEvent actionEvent) throws SQLException {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        ResultSet rs2 = null;
        String query1 = "SELECT * FROM userdbinfo WHERE email= ? and password = ?";
        String query2 =  "SELECT role FROM userdbinfo WHERE email= ? and password = ?";
        try {
            ps1 = connectDB.prepareStatement(query1);
            ps1.setString(1, email.getText());
            ps1.setString(2, password.getText());
            rs = ps1.executeQuery();
            ps2 = connectDB.prepareStatement(query2);
            ps2.setString(1, email.getText());
            ps2.setString(2, password.getText());
            rs2 = ps2.executeQuery();
            while(rs2.next()) {
                role = (rs2.getString("role"));
            }
            if (rs.next()){
                try {
                    ((Node)actionEvent.getSource()).getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("StoreFront.fxml"));
                    Parent viewParent =  (Parent) loader.load();
                    StoreFrontController storeFront = loader.getController();
                    storeFront.getUser(email.getText(), role);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(viewParent));
                    stage.show();

                }catch (IOException e){
                    e.printStackTrace();
                }

            } else {
                isConnected.setText("Login failed, please try again.");
            }
        } catch (Exception e){
            e.printStackTrace();
            }
        finally {
            ps1.close();
            rs.close();
        }


    }

    /**
     * Register() method
     * this method contains the code that takes the user to the Register FXML page
     * here the LoginController FXML stage is closed and the Register FXML is displayed
     * */
    public void Register(javafx.event.ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
            Parent viewParent =  (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(viewParent));
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Home() method
     * this method contains the code that returns the user back to the StoreFrontController FXML page
     * here the Admin FXML stage is closed and the StroreFrontController FXML is displayed
     * this method also contains data that gets parsed to the StoreFrontController() method
     * */
    public void Home(javafx.event.ActionEvent event) {
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
}
