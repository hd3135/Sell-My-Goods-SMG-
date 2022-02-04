package sample;

import javafx.animation.PauseTransition;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
* SellerPortalController() class
* This class holds the functionality for the SellerPortal GUI
* here the is the java code that defines the SellerPortal GUIs behavior for interacting with the user
* */

public class SellerPortalController implements Initializable {
    DatabaseConnection connectionNow = new DatabaseConnection();
    Connection connectDB = connectionNow.getConnection();
    @FXML
    Button btnAdd;
    @FXML
    Label lbLabel,  txtStoreName,  txtName, txtEmail, txtPhone, txtRole, txtOwner, txtStore,txtStroreLocation,txtOwnerEmail,txtAccountName,txtAccountNumber,txtAccountType,txtBranch,txtBranchCode;
    @FXML
    AnchorPane aPane;
    @FXML
    Pane UserPane, sPane;
    String StoreName;
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
    @FXML
    TextField txfProductName, txfProductPrice,  txfQuantity, txfStoreName, txfImageLink, txfOwnersName, txfContact, txfLocation;
    @FXML
    RadioButton rbtKH, rbtA, rbtS, rbtCE;
    @FXML
    ImageView sellerImage;

    String name1, storename1, doesntexist;
    String GetNameOfStore;
    String RadioButtonCategory;
    ObservableList<SellerModel> observableList = FXCollections.observableArrayList();

    /**
    * initialize() method
    * the initialize() method has access to the @FXML fields and is called after the @FXML members
    * in this method I am able to populate the image view as well as set the background colors for the various anchor panes and panes
    * All pre populated labels and text fields are defined in this method
    * and this methods calls other methods within the SellerPortalController class which need to be initialized on creation of the screen
    * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("https://static.thenounproject.com/png/94050-200.png");
        sellerImage.setImage(image);
        aPane.styleProperty().set("-fx-background-color: #add8e6");
        UserPane.styleProperty().set("-fx-background-color: #DCAE96");
        sPane.styleProperty().set("-fx-background-color: #DCAE96");
        lbLabel.setText("**Note** \n To remove product select product \n from table then click the below button");


    }


    /**
     * RecieveFromStoreController() method
     * the RecieveFromStoreController() method retrieves information parsed from the StoreFrontController() class
     * here the database is accessed by retrieving information parsed in from the StoreFrontController() class
     * and populates the table view with seller information from the database
     * */

    public void RecieveFromStoreController(String storename, String password) throws SQLException {
        GetNameOfStore = storename;
        String txfname = "";
        String txfphone = "";
        String txflocation = "";
        txtStoreName.setText(storename);
        StoreName = storename;
        PreparedStatement pst  = null;
        ResultSet rst = null;
        String query = "SELECT name, email, phone, role FROM userdbinfo WHERE password = ?";
        pst = connectDB.prepareStatement(query);
        pst.setString(1, password);
        rst = pst.executeQuery();
        while (rst.next()){
            txfname = rst.getString("name");
            txfphone = rst.getString("phone");
            txtName.setText(rst.getString("name"));
            txtEmail.setText(rst.getString("email"));
            txtRole.setText(rst.getString("role"));
            txtPhone.setText(rst.getString("phone"));
        }
        PreparedStatement statement = null;
        ResultSet set = null;
        String q = "SELECT seller_name, seller_location,  seller_store, seller_email, account_name, account_no, account_type, branch, branch_code FROM sellerinfo WHERE seller_store = ?";
        statement = connectDB.prepareStatement(q);
        statement.setString(1, StoreName);
        set = statement.executeQuery();
        while(set.next()){
            txtOwner.setText(set.getString("seller_name"));
            txtStroreLocation.setText(set.getString("seller_location"));
            txtStore.setText(set.getString("seller_store"));
            txtOwnerEmail.setText(set.getString("seller_email"));
            txtAccountName.setText(set.getString("account_name"));
            txtAccountNumber.setText(set.getString("account_no"));
            txtAccountType.setText(set.getString("account_type"));
            txtBranch.setText(set.getString("branch"));
            txtBranchCode.setText(set.getString("branch_code"));

            txflocation = set.getString("seller_location");
        }

        col_ProductName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        col_Price.setCellValueFactory(new PropertyValueFactory<>("ProductPrice"));
        col_Category.setCellValueFactory(new PropertyValueFactory<>("ProdcutCategory"));
        col_Quantity.setCellValueFactory(new PropertyValueFactory<>("ProductQuantity"));
        col_Store.setCellValueFactory(new PropertyValueFactory<>("ProductStore"));
        col_ImaageLink.setCellValueFactory(new PropertyValueFactory<>("ProductLinkImage"));
        col_Owner.setCellValueFactory(new PropertyValueFactory<>("ProductOwner"));
        col_OwnerContact.setCellValueFactory(new PropertyValueFactory<>("ProductOwnerDetails"));
        col_Location.setCellValueFactory(new PropertyValueFactory<>("ProductLocation"));

        PreparedStatement p =null;
        ResultSet s =null;
        String qt = "SELECT * FROM productinfo WHERE product_store = ?";
        p =  connectDB.prepareStatement(qt);
        p.setString(1,  txtStoreName.getText());
        s = p.executeQuery();
        while (s.next()){
            observableList.add(new SellerModel(s.getString("prduct_name"), s.getInt("product_price"),s.getString("product_category"),s.getInt("product_quantity"),s.getString("product_store"),s.getString("product_linkimage"),s.getString("product_owner"),s.getString("owner_details"),s.getString("product_location") ));

        }

        table.setItems(observableList );
        txfStoreName.setText(txtStoreName.getText());
        txfContact.setText(txfphone);
        txfOwnersName.setText(txfname);
        txfLocation.setText(txflocation);





    }

    /**
     * AddProduct() method
     * the AddProduct() method adds a product to the sellerinfo database
     * by retrieving data from the various text fields populated by the user
     * the table view is also updated with the data entered into the text fields by the user
     * */

    public void AddProduct(ActionEvent event) throws SQLException {
        PreparedStatement p = null;
        ResultSet s = null;
        String query = "SELECT * FROM productinfo WHERE product_store = ?";
        p = connectDB.prepareStatement(query);
        p.setString(1, txfStoreName.getText());
        s = p.executeQuery();
        if (!s.next()){
            doesntexist = txfStoreName.getText();
        }

        if (rbtA.isSelected()){
            RadioButtonCategory = "Apparel";
        } else if (rbtCE.isSelected()){
            RadioButtonCategory = "Computer and Electronics";
        } else if (rbtKH.isSelected()){
            RadioButtonCategory = "Kitchen and Home";
        } else if (rbtS.isSelected()){
            RadioButtonCategory = "Sneakers";
        }

        observableList.add(new SellerModel(txfProductName.getText(),Integer.parseInt(txfProductPrice.getText()),RadioButtonCategory,Integer.parseInt(txfQuantity.getText()),txfStoreName.getText(),txfImageLink.getText(),txfOwnersName.getText(),txfContact.getText(),txfLocation.getText()));

        PreparedStatement steez = null;
        String q = "INSERT INTO productinfo (prduct_name, product_price, product_category, product_quantity, product_store, product_linkimage, product_owner, owner_details,product_location)  VALUES (?,?,?,?,?,?,?,?,?)";
        steez = connectDB.prepareStatement(q);
        steez.setString(1,txfProductName.getText());
        steez.setString(2, String.valueOf(Integer.parseInt(txfProductPrice.getText())));
        steez.setString(3,RadioButtonCategory);
        steez.setString(4,String.valueOf(Integer.parseInt(txfQuantity.getText())));
        steez.setString(5,txfStoreName.getText());
        steez.setString(6,txfImageLink.getText());
        steez.setString(7,txfOwnersName.getText());
        steez.setString(8,txfContact.getText());
        steez.setString(9,txfLocation.getText());
        steez.executeUpdate();


        txfProductName.setText("");
        txfProductPrice.setText("");
        txfImageLink.setText("");
        txfQuantity.setText("");
    }

    /**
     * Home() method
     * this method contains the code that returns the user back to the StoreFrontController FXML page
     * here the SellerPortal FXML stage is closed and the StroreFrontController FXML is displayed
     * this method also contains data that gets parsed to the StoreFrontController() method
     * */

    public void Home(ActionEvent event) {

        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StoreFront.fxml"));
            Parent viewParent =  (Parent) loader.load();
            StoreFrontController sc = loader.getController();
            sc.getUserFromSellerPortal(txtEmail.getText(), txtRole.getText(), txtStoreName.getText());
            sc.Recieve(RadioButtonCategory, doesntexist);
            Stage stage = new Stage();
            stage.setScene(new Scene(viewParent));
            stage.show();

        }catch (IOException | SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Remove() method
     * the Remove() methods contains the functionality that removes an item from the observable list
     * which in turn removes the item from the table view component in the SellerPortalController.FXML
     * this method also deletes the selected item from the product database
     * */

    public void Remove(ActionEvent event) throws SQLException {
        observableList = table.getSelectionModel().getSelectedItems();
        name1 = observableList.get(0).getProductName();
        storename1 = observableList.get(0).getProductStore();
        PreparedStatement ptsd = null;
        String qt = "DELETE FROM productinfo  WHERE prduct_name = ? and product_store = ? ";
        ptsd = connectDB.prepareStatement(qt);
        ptsd.setString(1, name1);
        ptsd.setString(2, storename1);
        ptsd.executeUpdate();

        table.getItems().removeAll(table.getSelectionModel().getSelectedItem());






    }

    /**
     * DeleteStore() method
     * the delete store button removes all the information that populated the table view
     * as well as deleting the data from the relevant databases namely the sellerinfo table and the productinfo table in the database
     * here the observable list is cleared and so is the table view
     * */

    public void DeleteStore(ActionEvent event) throws SQLException {
        PreparedStatement p1 = null;
        PreparedStatement p2 = null;
        ResultSet s1 = null;
        ResultSet s2 = null;
        String query1 = "DELETE FROM sellerinfo WHERE seller_store = ?";
        String query2 = "DELETE FROM productinfo WHERE product_store = ?";
        p1 = connectDB.prepareStatement(query1);
        p2 = connectDB.prepareStatement(query2);

        p1.setString(1, txtStoreName.getText());
        p2.setString(1, txtStoreName.getText());

        p1.executeUpdate();
        p2.executeUpdate();

        observableList.clear();

        Stage dmessage = new Stage();
        VBox b = new VBox();
        Label success = new Label("Store deleted succesfully");
        success.setFont((new Font("Arial Bold", 30)));
        b.getChildren().add(success);
        Scene ns = new Scene(b, 400, 400);
        dmessage.setScene(ns);
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(e -> dmessage.hide());
        dmessage.initStyle(StageStyle.UNDECORATED);
        dmessage.show();
        delay.play();




    }

    /**
     * UpdateAccountInfo() method
     * the UpdateAccountInfo() method is an Action event method that produces a pop up window
     * this pop up window contains components that allows the user to populate which get parsed to the database
     * which excecutes and UPDATE query which updates the sellerinfo table
     * */

    public void UpdateAccountInfo(ActionEvent event) {
        Stage popup = new Stage();
        VBox box = new VBox();
        box.setSpacing(30);
        box.setPadding(new Insets(10,10,10,10));

        Label UpdateAccountName = new Label("Update account name");
        TextField txfUpdateAccountName = new TextField(txtAccountName.getText());

        Label UpdateAccountNumber = new Label("Update account number");
        TextField txfUpdateAccountNumber= new TextField(txtAccountNumber.getText());

        Label UpdateAccountType = new Label("Update account type");
        TextField txfUpdateAccountType = new TextField(txtAccountType.getText());

        Label UpdateBranch = new Label("Update branch");
        TextField txfUpdateBranch = new TextField(txtBranch.getText());

        Label UpdateBranchNo = new Label("Update branch");
        TextField txfUpdateBranchNo = new TextField(txtBranchCode.getText());


        Button UpdateButton = new Button("Update Info");

        UpdateAccountName.setFont((new Font("Arial Bold", 20)));
        UpdateAccountNumber.setFont((new Font("Arial Bold", 20)));
        UpdateAccountType.setFont((new Font("Arial Bold", 20)));
        UpdateBranch.setFont((new Font("Arial Bold", 20)));
        UpdateBranchNo.setFont((new Font("Arial Bold", 20)));

        txfUpdateAccountName.setFont((new Font("Arial", 15)));
        txfUpdateAccountNumber.setFont((new Font("Arial", 15)));
        txfUpdateAccountType.setFont((new Font("Arial", 15)));
        txfUpdateBranch.setFont((new Font("Arial", 15)));
        txfUpdateBranchNo.setFont((new Font("Arial", 15)));

        UpdateButton.setMaxSize(500, 500);

        box.getChildren().add(UpdateAccountName);
        box.getChildren().add(txfUpdateAccountName);
        box.getChildren().add(UpdateAccountNumber);
        box.getChildren().add(txfUpdateAccountNumber);
        box.getChildren().add(UpdateAccountType);
        box.getChildren().add(txfUpdateAccountType);
        box.getChildren().add(UpdateBranch);
        box.getChildren().add(txfUpdateBranch);
        box.getChildren().add(UpdateBranchNo);
        box.getChildren().add(txfUpdateBranchNo);

        box.getChildren().add(UpdateButton);
        Scene stageScene = new Scene(box, 500,  600);
        String title = "Update Personal Information";
        popup.setTitle(title);
        popup.setScene(stageScene);
        popup.show();

        UpdateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PreparedStatement p = null;
                String query = "UPDATE sellerinfo SET account_name = ?, account_no = ?, account_type = ?, branch = ?, branch_code = ? WHERE seller_name = ?";
                try {
                    p = connectDB.prepareStatement(query);
                    p.setString(1, txfUpdateAccountName.getText());
                    p.setString(2, txfUpdateAccountNumber.getText());
                    p.setString(3, txfUpdateAccountType.getText());
                    p.setString(4, txfUpdateBranch.getText());
                    p.setString(5, txfUpdateBranchNo.getText());
                    p.setString(6,txtName.getText() );
                    p.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                Stage dmessage = new Stage();
                VBox b = new VBox();
                Label success = new Label("Changes made succesfully \n log out then in \n to view changes");
                success.setFont((new Font("Arial Bold", 30)));
                b.getChildren().add(success);
                Scene ns = new Scene(b, 400, 400);
                dmessage.setScene(ns);
                PauseTransition delay = new PauseTransition(Duration.seconds(5));
                delay.setOnFinished(e -> dmessage.hide());
                dmessage.initStyle(StageStyle.UNDECORATED);
                dmessage.show();
                delay.play();
                popup.close();



            }
        });




    }

    /**
     * AdminRequest() method
     * the AdminRequest() method is an Action event method that produces a pop up window
     * this pop up window contains components that allows the user to populate which get parsed to the database
     * the ruquest table in the database gets parsed the information the user has entered into the relevant fields on the pop up window
     * */

    public void AdminRequest(ActionEvent event) throws SQLException {
        String ownername = "";
        String owneremail = "";

        PreparedStatement p = null;
        ResultSet s = null;
        String query = "SELECT seller_name, seller_email FROM sellerinfo WHERE seller_store = ?";
        p = connectDB.prepareStatement(query);
        p.setString(1, txtStoreName.getText());
        s = p.executeQuery();
        while(s.next()){
            ownername = s.getString("seller_name");
            owneremail = s.getString("seller_email");
        }

        Stage popup = new Stage();
        VBox box = new VBox();
        box.setSpacing(30);
        box.setPadding(new Insets(10,10,10,10));
        Label name = new Label("Name:");
        TextField NameField = new TextField(ownername);

        Label email = new Label("Email:");
        TextField EmailField = new TextField(owneremail);

        Label request = new Label("Your Request:");
        TextArea requestArea = new TextArea("please only 120 characters or less.");

        Button enterButton = new Button("Submit Request");



        name.setFont((new Font("Arial Bold", 20)));
        email.setFont((new Font("Arial Bold", 20)));
        request.setFont(new Font("Arial Bold", 20));

        NameField.setFont((new Font("Arial", 15)));
        EmailField.setFont((new Font("Arial", 15)));

        requestArea.setMaxSize(500,300);

        enterButton.setMaxSize(150, 100);

        box.getChildren().add(name);
        box.getChildren().add(NameField);

        box.getChildren().add(email);
        box.getChildren().add(EmailField);

        box.getChildren().add(request);
        box.getChildren().add(requestArea);


        box.getChildren().add(enterButton);



        Scene stageScene = new Scene(box, 500,  500);
        String title = "Admin Request";
        popup.setTitle(title);
        popup.setScene(stageScene);
        popup.show();


        enterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popup.close();
                Stage dmessage = new Stage();
                VBox b = new VBox();
                Label success = new Label("Request sent successfully!");
                success.setFont((new Font("Arial Bold", 20)));
                b.getChildren().add(success);
                Scene ns = new Scene(b, 280, 50);
                dmessage.setScene(ns);
                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(e -> dmessage.hide());
                dmessage.initStyle(StageStyle.UNDECORATED);
                dmessage.show();
                delay.play();
                String namefield = NameField.getText();
                String emailfield = EmailField.getText();
                String requestarea = requestArea.getText();


                PreparedStatement ps = null;

                String q = "INSERT INTO requests(name, email, request) VALUES(?,?,?)";
                try {
                    ps = connectDB.prepareStatement(q);
                    ps.setString(1, namefield);
                    ps.setString(2, emailfield);
                    ps.setString(3, requestarea);
                    ps.executeUpdate();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }





            }
        });


    }
}
