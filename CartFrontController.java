package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;

import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CartFrontController implements Initializable {
    ArrayList<String> lst = new ArrayList<String>();

    ArrayList<Integer> oo1 = new ArrayList<Integer>();
    ArrayList<String> ll1 = new ArrayList<String>();
    ArrayList<String> mm1 = new ArrayList<String>();

    ArrayList<ArrayList<String>> TwoDouter = new ArrayList<ArrayList<String>>();
    ArrayList<String> TwoDinner;
    @FXML
    private TextField  txf;
    @FXML
    private ListView lstView;
    @FXML
    private Label price, txtUser;
    @FXML
    private Button btnHome;
    @FXML
    AnchorPane aPane;
    ArrayList<String> ShopName = new ArrayList<String>();
    int si = 0;
    String [] store = new String[lst.size()];
    int totalNew;
    String roleGet = "";
    String emailGet = "";
    String [] bananna;


    String StoreName = "";
    String ProductName = "";
    int ProductPrice = 0;

    @FXML
    ImageView viewCart;


    /**
     * initialize() method
     * the initialize() method has access to the @FXML fields and is called after the @FXML members
     * in this method I am able to populate the image view as well as set the background colors for the various anchor panes and panes
     * All pre populated labels and text fields are defined in this method
     * and this methods calls other methods within the CartController class which need to be initialized on creation of the screen
     * */


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("https://i.pinimg.com/originals/65/17/5a/65175a608b1e5a740bd7ebcbb61bb59e.jpg");
        viewCart.setImage(image);
        aPane.styleProperty().set("-fx-background-color: #DCAE96");
        lstView.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial Bold'; -fx-control-inner-background: #add8e6 ");
    }


    /**
     * Proceed() method
     * this method contains the code that takes the user to the PaymentFront FXML page
     * here the CartFront FXML stage is closed and the PaymentFront FXML is displayed
     * this method also contains data that gets parsed to the PaymentFront() method
     * */

    public void Proceed(javafx.event.ActionEvent actionEvent){

        try {
            ((Node) actionEvent.getSource()).getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentFront.fxml"));
                Parent viewParent =  (Parent) loader.load();
                PaymentFrontController payment =  loader.getController();
                ArrayList<String> rollie = new ArrayList<String>();
                String [] sendthislist;

                for (int i = 0; i < lst.size(); i++) {
                    String info = lst.get(i);
                    sendthislist = lst.get(i).split("\n");
                    String [] jjabraham = lst.get(i).split("\n");
                    rollie.add(jjabraham[2].replaceAll(".+:", "").replaceFirst("^ *", ""));
                    rollie.add(jjabraham[0].replaceAll(".+:", "").replaceFirst("^ *", ""));
                    rollie.add(jjabraham[1].replaceAll(".+:", "").replaceFirst("^ *", "").replaceAll("R", ""));


                    String l = sendthislist[2].replaceAll(".+:", "").replaceFirst("^ *", "");
                    String m = sendthislist[0].replaceAll(".+:", "").replaceFirst("^ *", "");
                    int o = Integer.parseInt(sendthislist[1].replaceAll(".+:", "").replaceFirst("^ *", "").replaceAll("R", ""));




                    List<String> noduplicates = rollie.stream().distinct().collect(Collectors.toList());

                   mm1.add( noduplicates.get(0));
                   ll1.add(noduplicates.get(1));
                   oo1.add((Integer.parseInt(noduplicates.get(2))));



                    rollie.clear();

                }
                for (int q = 0; q < ll1.size(); q++){
                    //System.out.println(mm1.get(q) +" "+ ll1.get(q)+" "+oo1.get(q));
                }

                payment.myFunction2(totalNew, emailGet, roleGet, mm1, ll1, oo1);
               // System.out.println(ll1.size());
                //System.out.println(mm1);

                Stage stage = new Stage();
                stage.setScene(new Scene(viewParent));
                stage.show();

            }catch (IOException e){
                e.printStackTrace();
            }





    }


    /**
     * myFunction() method
     * this method recieves information from the StoreFrontController class
     * and sets the label to the data recieved from the StoreFrontController class
     * */
    public void myFunction(String item, int total, String emailUser, String roleUser, String stname, String prname, int pricename){
        StoreName = stname;
        ProductName = prname;
       ProductPrice =  pricename;
        System.out.println(StoreName);
        System.out.println(ProductName);
        System.out.println(ProductPrice);

        lst.add(item);
        lstView.getItems().add(item);
        price.setText("R: " + total);
        txtUser.setText(emailUser);
        si = lst.size();
        totalNew = total;
        roleGet = roleUser;
        emailGet = emailUser;


    }




}
