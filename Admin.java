package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Admin() class
 * This class holds the functionality for the Admin GUI
 * here the is the java code that defines the Admin GUIs behavior for interacting with the user
 * */

public class Admin implements Initializable {
    DatabaseConnection connectionNow = new DatabaseConnection();
    Connection connectDB = connectionNow.getConnection();
    @FXML
    ImageView AdminImage;
    @FXML
    Label LbAdminName;
    @FXML
    AnchorPane Apane;
    @FXML
    Pane pPane;

    @FXML
    private TableView<SellerModel> table;
    @FXML
    private TableColumn<SellerModel, String> col_ProductName;
    @FXML
    private TableColumn<SellerModel, Integer> col_Price;
    @FXML
    private TableColumn<SellerModel, String> col_Category;
    @FXML
    private TableColumn<SellerModel, Integer> col_Quantity;
    @FXML
    private TableColumn<SellerModel, String> col_Store;
    @FXML
    private TableColumn<SellerModel, String> col_ImaageLink;
    @FXML
    private TableColumn<SellerModel, String> col_Owner;
    @FXML
    private TableColumn<SellerModel, String> col_OwnerContact;
    @FXML
    private TableColumn<SellerModel, String> col_Location;

    ObservableList<SellerModel> observableList = FXCollections.observableArrayList();


    @FXML
    private TableView<SalesPersonModel> table2;
    @FXML
    private TableColumn<SalesPersonModel, String> col_Name;
    @FXML
    private TableColumn<SalesPersonModel, String> col_Email;
    @FXML
    private TableColumn<SalesPersonModel, String> col_Store1;
    @FXML
    private TableColumn<SalesPersonModel, String> col_Location1;
    @FXML
    private TableColumn<SalesPersonModel, String> col_AccountName;
    @FXML
    private TableColumn<SalesPersonModel, String> col_AccountNumber;
    @FXML
    private TableColumn<SalesPersonModel, String> col_AccountType;
    @FXML
    private TableColumn<SalesPersonModel, String> col_Branch;
    @FXML
    private TableColumn<SalesPersonModel, String> col_BranchCode;

    ObservableList<SalesPersonModel> observableList2 = FXCollections.observableArrayList();


    @FXML
    private TableView<AdminModel> table3;
    @FXML
    private TableColumn<AdminModel, String> namecol;
    @FXML
    private TableColumn<AdminModel, String> emailcol;
    @FXML
    private TableColumn<AdminModel, String> requestcol;
    ObservableList<AdminModel> observableList3 = FXCollections.observableArrayList();
    /**
     * initialize() method
     * the initialize() method has access to the @FXML fields and is called after the @FXML members
     * in this method I am able to populate the image view as well as set the background colors for the various anchor panes and panes
     * All pre populated labels and text fields are defined in this method
     * and this methods calls other methods within the Admin class which need to be initialized on creation of the screen
     * */


    @Override
    public void initialize(URL location, ResourceBundle resources){
        Image image = new Image("https://static.thenounproject.com/png/2303078-200.png");
        AdminImage.setImage(image);
        Apane.styleProperty().set("-fx-background-color: #DCAE96");
        ListAllProducts();
        ListAllSellers();
        ListAllRequests();
    }


    /**
     * ListAllProducts() method
     * the main functionality of this method is to populate the table view with all the products in the database
     * a SELECT query is performed on the productinfo table in the database
     * and the table view in the Admin FXML class is populated with product information
     * */
    public void ListAllProducts(){
        col_ProductName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        col_Price.setCellValueFactory(new PropertyValueFactory<>("ProductPrice"));
        col_Category.setCellValueFactory(new PropertyValueFactory<>("ProdcutCategory"));
        col_Quantity.setCellValueFactory(new PropertyValueFactory<>("ProductQuantity"));
        col_Store.setCellValueFactory(new PropertyValueFactory<>("ProductStore"));
        col_ImaageLink.setCellValueFactory(new PropertyValueFactory<>("ProductLinkImage"));
        col_Owner.setCellValueFactory(new PropertyValueFactory<>("ProductOwner"));
        col_OwnerContact.setCellValueFactory(new PropertyValueFactory<>("ProductOwnerDetails"));
        col_Location.setCellValueFactory(new PropertyValueFactory<>("ProductLocation"));

        PreparedStatement p = null;
        ResultSet s = null;
        String query = "SELECT * FROM productinfo";
        try {
            p = connectDB.prepareStatement(query);
            s = p.executeQuery();
            while (s.next()){
                String name = s.getString("prduct_name");
                int price = Integer.parseInt(s.getString("product_price"));
                String category = s.getString("product_category");
                int quantity = Integer.parseInt(s.getString("product_quantity"));
                String storename = s.getString("product_store");
                String imagelink = s.getString("product_linkimage");
                String owner = s.getString("product_owner");
                String ownerdetails = s.getString("owner_details");
                String storelocation = s.getString("product_location");
                observableList.add(new SellerModel(name, price, category, quantity, storename,imagelink, owner,ownerdetails,storelocation));


            }
            table.setItems(observableList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * ListAllSellers() method
     * the main functionality of this method is to populate the table view with all the sellers in the database
     * a SELECT query is performed on the sellerinfo table in the database
     * and the table view in the Admin FXML class is populated with seller information
     * */

    public void ListAllSellers(){
        col_Name.setCellValueFactory(new PropertyValueFactory<>("SellerName"));
        col_Location1.setCellValueFactory(new PropertyValueFactory<>("SellerLocation"));
        col_Store1.setCellValueFactory(new PropertyValueFactory<>("SellerStore"));
        col_Email.setCellValueFactory(new PropertyValueFactory<>("SellerEmail"));
        col_AccountName.setCellValueFactory(new PropertyValueFactory<>("AccountName"));
        col_AccountNumber.setCellValueFactory(new PropertyValueFactory<>("AccountNo"));
        col_AccountType.setCellValueFactory(new PropertyValueFactory<>("AccountType"));
        col_Branch.setCellValueFactory(new PropertyValueFactory<>("Branch"));
        col_BranchCode.setCellValueFactory(new PropertyValueFactory<>("BranchCode"));

        PreparedStatement p = null;
        ResultSet s = null;
        String query = "SELECT * FROM sellerinfo";
        try {
            p = connectDB.prepareStatement(query);
            s = p.executeQuery();
            while (s.next()){
                String name = s.getString("seller_name");
                String location1 = (s.getString("seller_location"));
                String sellerstore = s.getString("seller_store");
                String selleremail = (s.getString("seller_email"));
                String accountname = s.getString("account_name");
                String accountno = s.getString("account_no");
                String accounttype = s.getString("account_type");
                String branch = s.getString("branch");
                String branchcode = s.getString("branch_code");
                observableList2.add(new SalesPersonModel(name, location1, sellerstore, selleremail, accountname,accountno, accounttype,branch,branchcode));


            }
            table2.setItems(observableList2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




    }

    /**
     * ListAllRequests() method
     * the main functionality of this method is to populate the table view with all the request that we made and stored in the database
     * a SELECT query is performed on the request table in the database
     * and the table view in the Admin FXML class is populated with request information
     * */

    public void ListAllRequests(){
        namecol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        requestcol.setCellValueFactory(new PropertyValueFactory<>("Request"));

        PreparedStatement p = null;
        ResultSet s = null;
        String query = "SELECT * FROM requests";
        try {
            p = connectDB.prepareStatement(query);
            s = p.executeQuery();
            while (s.next()){
                String name = s.getString("name");
                String email = (s.getString("email"));
                String request = s.getString("request");

                observableList3.add(new AdminModel(name, email, request));


            }
            table3.setItems(observableList3);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * GetAdminUser() method
     * this method recieves information from the StoreFrontController class
     * and sets the label to the data recieved from the StoreFrontController class
     * */
    public void GetAdminUser(String AdminName){
        LbAdminName.setText(AdminName);
    }

    /**
     * ReturnHome() method
     * this method contains the code that returns the user back to the StoreFrontController FXML page
     * here the Admin FXML stage is closed and the StroreFrontController FXML is displayed
     * this method also contains data that gets parsed to the StoreFrontController() method
     * */

    public void ReturnHome(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StoreFront.fxml"));
            Parent viewParent =  (Parent) loader.load();
            StoreFrontController sc = loader.getController();

            sc.getAdminUser(LbAdminName.getText());

            Stage stage = new Stage();
            stage.setScene(new Scene(viewParent));
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * AdminRequests() method
     * the main functionality of this method is to display the data in the table view into a pop up window
     * data is selected from the observable list and dislayed on a new scene
     * this method also contains functionlality to delete a request from the database
     * by performing a delete query on the request table in the database
     * */

    public void AdminRequests(ActionEvent event) {
        observableList3 = table3.getSelectionModel().getSelectedItems();
        String name = observableList3.get(0).getName();
        String email = observableList3.get(0).getEmail();
        String request = observableList3.get(0).getRequest();


        Stage popup = new Stage();
        VBox box = new VBox();
        box.setSpacing(30);
        box.setPadding(new Insets(10,10,10,10));

        Label namequery = new Label("Name: " + name);
        Label emailquery = new Label("Email: " + email);
        Label requestquery = new Label("The request: \n" + request);
        requestquery.setMaxSize(300,300);

        namequery.setFont((new Font("Arial Bold", 20)));
        emailquery.setFont((new Font("Arial Bold", 20)));
        requestquery.setFont((new Font("Arial Bold", 20)));



        Button dl = new Button("Delete request");
        dl.setMaxSize(500, 500);

        box.getChildren().add(namequery);
        box.getChildren().add(emailquery);
        box.getChildren().add(requestquery);
        box.getChildren().add(dl);
        Scene stageScene = new Scene(box, 500,  600);
        String title = "View Requests";
        popup.setTitle(title);
        popup.setScene(stageScene);
        popup.show();

        dl.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PreparedStatement p = null;
                String query = "DELETE FROM requests WHERE name = ? and email = ? and request = ?";
                try {
                    p = connectDB.prepareStatement(query);
                    p.setString(1, namequery.getText());
                    p.setString(2, emailquery.getText());
                    p.setString(3, requestquery.getText());
                    p.executeUpdate();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                table3.getItems().removeAll(table3.getSelectionModel().getSelectedItem());

                popup.close();

            }
        });




    }

    /**
     * RemoveProduct() method
     * the main functionality of this method is to remove data from the table view in the Admin fxml class
     * as well as perform a delete query on the productinfo table in the database to delete the product from the database
     * the selected data is also deleted from the observable list
     * */

    public void RemoveProduct(ActionEvent event) throws SQLException {
        observableList = table.getSelectionModel().getSelectedItems();
        String productname = observableList.get(0).getProductName();
        String store = observableList.get(0).getProductStore();
        PreparedStatement ptsd = null;
        String qt = "DELETE FROM productinfo  WHERE prduct_name = ? and product_store = ? ";
        ptsd = connectDB.prepareStatement(qt);
        ptsd.setString(1, productname);
        ptsd.setString(2, store);
        ptsd.executeUpdate();

        table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
    }

    /**
     * RemoveSeller() method
     * the main functionality of this method is to remove data from the table view in the Admin fxml class
     * as well as perform a delete query on the sellerinfo table in the database to delete a seller from the database
     * the selected data is also deleted from the observable list
     * */

    public void RemoveSeller(ActionEvent event) throws SQLException  {
        observableList2 = table2.getSelectionModel().getSelectedItems();
        String sellername = observableList2.get(0).getSellerName();
        String sellerstore = observableList2.get(0).getSellerStore();
        PreparedStatement ptsd = null;
        PreparedStatement ptsd2 = null;
        String qt = "DELETE FROM productinfo  WHERE product_owner = ? and product_store = ? ";
        String qt2 = "DELETE FROM sellerinfo  WHERE seller_name = ? and seller_store = ? ";
        ptsd = connectDB.prepareStatement(qt);
        ptsd2 = connectDB.prepareStatement(qt2);
        ptsd.setString(1, sellername);
        ptsd.setString(2, sellerstore);

        ptsd2.setString(1, sellername);
        ptsd2.setString(2, sellerstore);
        ptsd.executeUpdate();
        ptsd2.executeUpdate();

        table2.getItems().removeAll(table2.getSelectionModel().getSelectedItem());
    }


}
