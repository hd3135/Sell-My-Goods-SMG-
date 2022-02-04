package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;


public class StoreFrontController implements Initializable {
    DatabaseConnection connectionNow = new DatabaseConnection();
    Connection connectDB = connectionNow.getConnection();
    int count = 11;
    int y1 = 0;
    int y2 = 0;

    @FXML
    private ImageView imageview;
    @FXML
    TextField txtCartCounter;
    @FXML
    GridPane gPane;
    @FXML
    Label label, lbProductDsiplay, email_Label;
    @FXML
    Hyperlink l1, l2, l3, l4, l5;
    @FXML
    Button btnClearCart, btnAccount, btnHome, btnView, btnSignIn, btnAdmin, btnSeller;
    ArrayList<Button> ButtonListAddtoCart = new ArrayList<>();
    ArrayList<Button> ButtonListViewMore = new ArrayList<>();
    ArrayList<Button> ButtonListRemoveItem = new ArrayList<>();
    ArrayList<Hyperlink> HyperLinkList = new ArrayList<>();
    @FXML
    ScrollPane spane;
    ArrayList<Integer> cost = new ArrayList<Integer>();
    ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
    ArrayList<String> inner;
    ArrayList<ArrayList<String>> ot = new ArrayList<ArrayList<String>>();
    ArrayList<String> in;
    ArrayList<ArrayList<String>> o = new ArrayList<ArrayList<String>>();
    ArrayList<String> inn;
    ArrayList<ArrayList<String>> oo = new ArrayList<ArrayList<String>>();
    ArrayList<String> innn;
    ArrayList<ArrayList<String>> ooo = new ArrayList<ArrayList<String>>();
    ArrayList<String> innnn;
    ArrayList<ArrayList<String>> SamsShoesOuter = new ArrayList<ArrayList<String>>();
    ArrayList<String> SamsShoesInner;
    ArrayList<ArrayList<String>> NadiasKitchenOuter = new ArrayList<ArrayList<String>>();
    ArrayList<String> NadiasKitchenInner;
    ArrayList<ArrayList<String>> ChoasComputersOuter = new ArrayList<ArrayList<String>>();
    ArrayList<String> ChoasComputersInner;
    @FXML
    ComboBox ComboBox = new ComboBox();
    @FXML
    VBox boxV  = new VBox();
    ArrayList<String> GettingDBStuff = new ArrayList<>();
    ArrayList<String> NewStores = new ArrayList<>();
    ObservableList<String> names = FXCollections.observableArrayList();
    int row;
    int total;
    String userEmail = "";
    String userRole = "";
    String catt = "";
    String NameOfStore = "";
    String eele1;

    /**
     * ButtonList() method
     * this method creates an array of button components
     * */

    public void ButtonList() {
        for (int i = 0; i < 21; i++) {
            Button btnViewMore = new Button("View more");
            Button btnAddtoCart = new Button("Add to cart");
            Button btnRemoveItem = new Button("Remove from cart");
            ButtonListAddtoCart.add(btnAddtoCart);
            ButtonListViewMore.add(btnViewMore);
            ButtonListRemoveItem.add(btnRemoveItem);

        }
    }
    /**
     * ClearCart() method
     * this method empties the cart component on the StoreFront FXML and sets the label text
     * */

    public void ClearCart(ActionEvent event) {
        names.clear();
        names.setAll("empty");

        txtCartCounter.setText("No. of items: 0");
        ComboBox.setItems(names);

    }

    /**
     * HyperLinkViewAll() method
     * these methods displays all the products that are present in the database onto the grid view
     * all the button componets seen in each grid frame are initilized in this method
     * */


    public void HyperLinkViewAll(ActionEvent event) {
        ArrayList<Label> LabelViewAll = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            Label lbgt12321 = new Label();
            LabelViewAll.add(lbgt12321);
        }

        ButtonList();
        lbProductDsiplay.setText("All Products");

        gPane.getChildren().retainAll(gPane.getChildren().get(0));
        // gPane.setGridLinesVisible(true);
        try {
            String query1 = "SELECT prduct_name,  product_store, product_price, product_category,   product_linkimage, product_owner, owner_details, product_location, product_quantity FROM productinfo";
            Statement st = connectDB.createStatement();
            ResultSet rs = st.executeQuery(query1);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNo = rsmd.getColumnCount();

            while (rs.next()) {
                inner = new ArrayList<String>();
                for (int i = 1; i <= columnNo; i++) {
                    inner.add(rs.getString(i));
                }
                outer.add(inner);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


        try {
            String q2 = "SELECT COUNT(*) AS rowcount FROM productinfo";
            Statement s = connectDB.createStatement();
            ResultSet r = s.executeQuery(q2);
            r.next();
            row = r.getInt("rowcount");


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {

                if (cnt <= (row - 1)) {

                    String text = "Product Name: " + outer.get(cnt).get(0) + "\nPrice: R" + outer.get(cnt).get(2) + "\nStore: " + outer.get(cnt).get(1);
                    LabelViewAll.get(cnt).setText(text);
                    LabelViewAll.get(cnt).setFont(new Font("Arial Bold", 15));
                    LabelViewAll.get(cnt).setMinWidth(50);
                    LabelViewAll.get(cnt).setMaxHeight(50);
                    gPane.add(LabelViewAll.get(cnt), i, j);
                    gPane.add(ButtonListAddtoCart.get(cnt), i, j);
                    gPane.add(ButtonListViewMore.get(cnt), i, j);
                    gPane.add(ButtonListRemoveItem.get(cnt), i, j);
                    LabelViewAll.get(cnt).setAlignment(Pos.TOP_RIGHT);
                    ButtonListViewMore.get(cnt).setAlignment(Pos.BOTTOM_RIGHT);
                    gPane.setValignment(LabelViewAll.get(cnt), VPos.TOP);
                    gPane.setHalignment(ButtonListRemoveItem.get(cnt), HPos.RIGHT);
                    gPane.setValignment(ButtonListAddtoCart.get(cnt), VPos.BOTTOM);
                    gPane.setHalignment(ButtonListViewMore.get(cnt), HPos.LEFT);
                    gPane.setVgap(5);
                    gPane.setHgap(5);
                    cnt++;

                } else if (cnt > (row - 1)) {
                    String text = "Product not available";
                    Label llb = new Label(text);
                    llb.setFont(new Font("Arial Italics", 13));
                    llb.setMinWidth(50);
                    llb.setMaxHeight(50);
                    gPane.add(llb, i, j);
                    llb.setAlignment(Pos.TOP_RIGHT);
                    gPane.setValignment(llb, VPos.TOP);
                    gPane.setVgap(5);
                    gPane.setHgap(5);


                }
            }
        }

        EventHandler<ActionEvent> event10 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelViewAll.get(0).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(0).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event20 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event20);
            }
        };
        ButtonListAddtoCart.get(0).setOnAction(event10);

        EventHandler<ActionEvent> event11 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelViewAll.get(1).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(1).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event21 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event21);
            }
        };
        ButtonListAddtoCart.get(1).setOnAction(event11);

        EventHandler<ActionEvent> event12 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelViewAll.get(2).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(2).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event22 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event22);
            }
        };
        ButtonListAddtoCart.get(2).setOnAction(event12);

        EventHandler<ActionEvent> event13 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelViewAll.get(3).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(3).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event23 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event23);
            }
        };
        ButtonListAddtoCart.get(3).setOnAction(event13);

        EventHandler<ActionEvent> event14 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelViewAll.get(4).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(4).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event24 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event24);
            }
        };
        ButtonListAddtoCart.get(4).setOnAction(event14);

        EventHandler<ActionEvent> event15 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelViewAll.get(5).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(5).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event25 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event25);
            }
        };
        ButtonListAddtoCart.get(5).setOnAction(event15);

        EventHandler<ActionEvent> event16 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelViewAll.get(6).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(6).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event26 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event26);
            }
        };
        ButtonListAddtoCart.get(6).setOnAction(event16);

        EventHandler<ActionEvent> event17 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelViewAll.get(7).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(7).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event27 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event27);
            }
        };
        ButtonListAddtoCart.get(7).setOnAction(event17);

        EventHandler<ActionEvent> event18 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelViewAll.get(8).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(8).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event28 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event28);
            }
        };
        ButtonListAddtoCart.get(8).setOnAction(event18);

        EventHandler<ActionEvent> event19 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelViewAll.get(9).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(9).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event29 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event29);
            }
        };
        ButtonListAddtoCart.get(9).setOnAction(event19);

        EventHandler<ActionEvent> event100 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelViewAll.get(10).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(10).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event200 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event200);
            }
        };
        ButtonListAddtoCart.get(10).setOnAction(event100);

        EventHandler<ActionEvent> event101 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelViewAll.get(11).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(11).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event201 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event201);
            }
        };
        ButtonListAddtoCart.get(11).setOnAction(event101);

        EventHandler<ActionEvent> event102 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelViewAll.get(12).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(12).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event202 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event202);
            }
        };
        ButtonListAddtoCart.get(12).setOnAction(event102);

        EventHandler<ActionEvent> event103 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelViewAll.get(13).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(13).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event203 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event203);
            }
        };
        ButtonListAddtoCart.get(13).setOnAction(event103);

        EventHandler<ActionEvent> event104 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelViewAll.get(14).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(14).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event204 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event204);
            }
        };
        ButtonListAddtoCart.get(14).setOnAction(event104);

        EventHandler<ActionEvent> event105 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelViewAll.get(15).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(15).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event205 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event205);
            }
        };
        ButtonListAddtoCart.get(15).setOnAction(event105);

        EventHandler<ActionEvent> event106 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelViewAll.get(16).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(16).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event206 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event206);
            }
        };
        ButtonListAddtoCart.get(16).setOnAction(event106);

        EventHandler<ActionEvent> event107 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelViewAll.get(17).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(17).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event207 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event207);
            }
        };
        ButtonListAddtoCart.get(17).setOnAction(event107);

        EventHandler<ActionEvent> event108 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelViewAll.get(18).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(18).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event208 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event208);
            }
        };
        ButtonListAddtoCart.get(18).setOnAction(event108);

        EventHandler<ActionEvent> event109 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelViewAll.get(19).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(19).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event209 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event209);
            }
        };
        ButtonListAddtoCart.get(19).setOnAction(event109);


        ButtonListViewMore.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(0).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(0).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(0).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(0).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(0).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(0).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(1).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(1).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(1).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(1).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(1).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(1).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(2).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(2).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(2).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(2).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(2).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(2).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(3).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(3).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(3).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(3).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(3).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(3).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(3).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(4).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(4).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(4).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(4).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(4).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(4).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(4).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(5).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(5).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(5).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(5).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(5).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(5).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(5).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(6).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(6).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(6).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(6).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(6).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(6).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(6).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(7).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(7).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(7).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(7).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(7).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(7).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(7).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(8).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(8).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(8).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(8).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(8).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(8).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(8).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(9).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(9).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(9).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(9).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(9).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(9).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(9).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(10).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(10).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(10).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(10).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(10).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(10).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(10).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(11).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(11).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(11).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(11).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(11).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(11).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(11).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(12).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(12).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(12).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(12).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(12).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(12).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(12).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(13).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(13).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(13).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(13).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(13).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(13).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(13).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(14).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(14).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(14).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(14).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(14).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(14).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(14).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(15).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(15).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(15).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(15).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(15).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(15).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(15).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(16).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(16).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(16).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(16).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(16).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(16).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(16).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(17).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(17).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(17).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(17).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(17).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(17).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(17).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(18).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(18).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(18).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(18).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(18).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(18).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(18).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(19).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(19).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(19).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(19).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(19).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(19).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(19).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });

        ButtonListRemoveItem.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelViewAll.get(0).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(0).get(2)));
            }
        });
        ButtonListRemoveItem.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelViewAll.get(1).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(1).get(2)));
            }
        });
        ButtonListRemoveItem.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelViewAll.get(2).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(2).get(2)));
            }
        });
        ButtonListRemoveItem.get(3).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelViewAll.get(3).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(3).get(2)));
            }
        });
        ButtonListRemoveItem.get(4).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelViewAll.get(4).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(4).get(2)));
            }
        });
        ButtonListRemoveItem.get(5).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelViewAll.get(5).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(5).get(2)));
            }
        });
        ButtonListRemoveItem.get(6).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelViewAll.get(6).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(6).get(2)));
            }
        });
        ButtonListRemoveItem.get(7).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelViewAll.get(7).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(7).get(2)));
            }
        });
        ButtonListRemoveItem.get(8).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelViewAll.get(8).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(8).get(2)));
            }
        });
        ButtonListRemoveItem.get(9).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelViewAll.get(9).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(9).get(2)));
            }
        });
        ButtonListRemoveItem.get(10).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelViewAll.get(10).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(10).get(2)));
            }
        });
        ButtonListRemoveItem.get(11).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelViewAll.get(11).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(11).get(2)));
            }
        });
        ButtonListRemoveItem.get(12).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelViewAll.get(12).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(12).get(2)));
            }
        });
        ButtonListRemoveItem.get(13).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelViewAll.get(13).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(13).get(2)));
            }
        });
        ButtonListRemoveItem.get(14).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelViewAll.get(14).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(14).get(2)));
            }
        });
        ButtonListRemoveItem.get(15).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelViewAll.get(15).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(15).get(2)));
            }
        });
        ButtonListRemoveItem.get(16).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelViewAll.get(16).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(16).get(2)));
            }
        });
        ButtonListRemoveItem.get(17).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelViewAll.get(17).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(17).get(2)));
            }
        });
        ButtonListRemoveItem.get(18).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelViewAll.get(18).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(18).get(2)));
            }
        });
        ButtonListRemoveItem.get(19).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelViewAll.get(19).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(19).get(2)));
            }
        });

    }

    /**
     * HyperLinkView1() method
     * this methods displays all the products that are present in the database under the computer and electronics category onto the grid view
     * all the button componets seen in each grid frame are initilized in this method
     * */
    public void HyperLink1(ActionEvent event) {
        ArrayList<Label> LabelComputernElectronicsInfo = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            Label l2g33678 = new Label();
            LabelComputernElectronicsInfo.add(l2g33678);
        }
        ButtonList();
        lbProductDsiplay.setText("Computer and Electronics");
        gPane.getChildren().retainAll(gPane.getChildren().get(0));
        String cat = "Computer and Electronics";
        try {
            String q2 = "SELECT COUNT(*) AS rowcount FROM productinfo WHERE product_category = '" + cat + "'";
            Statement s = connectDB.createStatement();
            ResultSet r = s.executeQuery(q2);
            r.next();
            row = r.getInt("rowcount");


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        Label lb;


        try {
            String query1 = "SELECT prduct_name,  product_store, product_price, product_category,   product_linkimage, product_owner, owner_details, product_location, product_quantity FROM productinfo WHERE product_category = '" + cat + "'";
            Statement st = connectDB.createStatement();
            ResultSet rs = st.executeQuery(query1);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNo = rsmd.getColumnCount();

            while (rs.next()) {
                in = new ArrayList<String>();
                for (int i = 1; i <= columnNo; i++) {
                    in.add(rs.getString(i));
                }
                ot.add(in);
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


        int cnt = 0;


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {

                if (cnt <= (row - 1)) {

                    String text = "Product Name: " + ot.get(cnt).get(0) + "\nPrice: R" + ot.get(cnt).get(2) + "\nStore: " + ot.get(cnt).get(1);
                    LabelComputernElectronicsInfo.get(cnt).setText(text);
                    LabelComputernElectronicsInfo.get(cnt).setFont(new Font("Arial Bold", 15));
                    LabelComputernElectronicsInfo.get(cnt).setMinWidth(50);
                    LabelComputernElectronicsInfo.get(cnt).setMaxHeight(50);
                    gPane.add(LabelComputernElectronicsInfo.get(cnt), i, j);
                    gPane.add(ButtonListRemoveItem.get(cnt), i, j);
                    gPane.add(ButtonListAddtoCart.get(cnt), i, j);
                    gPane.add(ButtonListViewMore.get(cnt), i, j);
                    LabelComputernElectronicsInfo.get(cnt).setAlignment(Pos.TOP_RIGHT);
                    ButtonListViewMore.get(cnt).setAlignment(Pos.BOTTOM_RIGHT);
                    gPane.setValignment(LabelComputernElectronicsInfo.get(cnt), VPos.TOP);
                    gPane.setValignment(ButtonListAddtoCart.get(cnt), VPos.BOTTOM);
                    gPane.setHalignment(ButtonListRemoveItem.get(cnt), HPos.RIGHT);
                    gPane.setHalignment(ButtonListViewMore.get(cnt), HPos.LEFT);
                    gPane.setVgap(5);
                    gPane.setHgap(5);
                    cnt++;

                } else if (cnt > (row - 1)) {
                    String text = "Product not available";
                    lb = new Label(text);
                    lb.setFont(new Font("Arial Italics", 13));
                    lb.setMinWidth(50);
                    lb.setMaxHeight(50);
                    gPane.add(lb, i, j);
                    lb.setAlignment(Pos.TOP_RIGHT);
                    gPane.setVgap(5);
                    gPane.setHgap(5);


                }
            }
        }


        EventHandler<ActionEvent> eventaa = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelComputernElectronicsInfo.get(0).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ot.get(0).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventaaa = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventaaa);
            }
        };
        ButtonListAddtoCart.get(0).setOnAction(eventaa);

        EventHandler<ActionEvent> eventbb = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelComputernElectronicsInfo.get(1).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ot.get(1).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventbbb = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventbbb);
            }
        };
        ButtonListAddtoCart.get(1).setOnAction(eventbb);

        EventHandler<ActionEvent> eventcc = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelComputernElectronicsInfo.get(2).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ot.get(2).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventccc = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventccc);
            }
        };
        ButtonListAddtoCart.get(2).setOnAction(eventcc);

        EventHandler<ActionEvent> eventdd = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelComputernElectronicsInfo.get(3).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ot.get(3).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventddd = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventddd);
            }
        };
        ButtonListAddtoCart.get(3).setOnAction(eventdd);

        EventHandler<ActionEvent> eventee = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelComputernElectronicsInfo.get(4).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ot.get(4).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventeee = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventeee);
            }
        };
        ButtonListAddtoCart.get(4).setOnAction(eventee);

        EventHandler<ActionEvent> eventff = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelComputernElectronicsInfo.get(5).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ot.get(5).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventfff = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventfff);
            }
        };
        ButtonListAddtoCart.get(5).setOnAction(eventff);

        EventHandler<ActionEvent> eventgg = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelComputernElectronicsInfo.get(6).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ot.get(6).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventggg = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventggg);
            }
        };
        ButtonListAddtoCart.get(6).setOnAction(eventgg);

        EventHandler<ActionEvent> eventhh = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelComputernElectronicsInfo.get(7).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ot.get(7).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventhhh = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventhhh);
            }
        };
        ButtonListAddtoCart.get(7).setOnAction(eventhh);

        EventHandler<ActionEvent> eventii = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelComputernElectronicsInfo.get(8).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ot.get(8).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventiii = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventiii);
            }
        };
        ButtonListAddtoCart.get(8).setOnAction(eventii);

        EventHandler<ActionEvent> eventjj = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelComputernElectronicsInfo.get(9).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ot.get(9).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventjjj = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventjjj);
            }
        };
        ButtonListAddtoCart.get(9).setOnAction(eventjj);

        EventHandler<ActionEvent> eventkk = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelComputernElectronicsInfo.get(10).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ot.get(10).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventkkk = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventkkk);
            }
        };
        ButtonListAddtoCart.get(10).setOnAction(eventkk);

        EventHandler<ActionEvent> eventll = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelComputernElectronicsInfo.get(11).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ot.get(11).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventlll = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventlll);
            }
        };
        ButtonListAddtoCart.get(11).setOnAction(eventll);

        EventHandler<ActionEvent> eventmm = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelComputernElectronicsInfo.get(12).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ot.get(12).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventmmm = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventmmm);
            }
        };
        ButtonListAddtoCart.get(12).setOnAction(eventmm);

        EventHandler<ActionEvent> eventnn = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelComputernElectronicsInfo.get(13).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ot.get(13).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventnnn = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventnnn);
            }
        };
        ButtonListAddtoCart.get(13).setOnAction(eventnn);

        EventHandler<ActionEvent> eventoo = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelComputernElectronicsInfo.get(14).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ot.get(14).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> evento00 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(evento00);
            }
        };
        ButtonListAddtoCart.get(14).setOnAction(eventoo);

        EventHandler<ActionEvent> eventpp = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelComputernElectronicsInfo.get(15).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ot.get(15).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventppp = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventppp);
            }
        };
        ButtonListAddtoCart.get(15).setOnAction(eventpp);

        EventHandler<ActionEvent> eventqq = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelComputernElectronicsInfo.get(16).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ot.get(16).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventqqq = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventqqq);
            }
        };
        ButtonListAddtoCart.get(16).setOnAction(eventqq);

        EventHandler<ActionEvent> eventrr = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelComputernElectronicsInfo.get(17).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ot.get(17).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventrrr = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();
                    }
                };
                btnClearCart.setOnAction(eventrrr);
            }
        };

        ButtonListAddtoCart.get(17).setOnAction(eventrr);
        EventHandler<ActionEvent> eventss = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelComputernElectronicsInfo.get(18).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ot.get(18).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventsss = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventsss);
            }
        };
        ButtonListAddtoCart.get(18).setOnAction(eventss);

        EventHandler<ActionEvent> eventtt = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelComputernElectronicsInfo.get(19).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ot.get(19).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventttt = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventttt);
            }
        };
        ButtonListAddtoCart.get(19).setOnAction(eventtt);

        ButtonListViewMore.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ot.get(0).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ot.get(0).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ot.get(0).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ot.get(0).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ot.get(0).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ot.get(0).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ot.get(1).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ot.get(1).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ot.get(1).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ot.get(1).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ot.get(1).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ot.get(1).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ot.get(2).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ot.get(2).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ot.get(2).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ot.get(2).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ot.get(2).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ot.get(2).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(3).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ot.get(3).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ot.get(3).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ot.get(3).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ot.get(3).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ot.get(3).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ot.get(3).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(4).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ot.get(4).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ot.get(4).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ot.get(4).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ot.get(4).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ot.get(4).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ot.get(4).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(5).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ot.get(5).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ot.get(5).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ot.get(5).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ot.get(5).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ot.get(5).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ot.get(5).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(6).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ot.get(6).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ot.get(6).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ot.get(6).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ot.get(6).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ot.get(6).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ot.get(6).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(7).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ot.get(7).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ot.get(7).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ot.get(7).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ot.get(7).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ot.get(7).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ot.get(7).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(8).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ot.get(8).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ot.get(8).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ot.get(8).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ot.get(8).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ot.get(8).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ot.get(8).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(9).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ot.get(9).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ot.get(9).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ot.get(9).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ot.get(9).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ot.get(9).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ot.get(9).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(10).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ot.get(10).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ot.get(10).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ot.get(10).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ot.get(10).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ot.get(10).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ot.get(10).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(11).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ot.get(11).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ot.get(11).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ot.get(11).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ot.get(11).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ot.get(11).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ot.get(11).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(12).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ot.get(12).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ot.get(12).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ot.get(12).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ot.get(12).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ot.get(12).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ot.get(12).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(13).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ot.get(13).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ot.get(13).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ot.get(13).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ot.get(13).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ot.get(13).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ot.get(13).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(14).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ot.get(14).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ot.get(14).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ot.get(14).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ot.get(14).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ot.get(14).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ot.get(14).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(15).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ot.get(15).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ot.get(15).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ot.get(15).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ot.get(15).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ot.get(15).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ot.get(15).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(16).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ot.get(16).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ot.get(16).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ot.get(16).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ot.get(16).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ot.get(16).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ot.get(16).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(17).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ot.get(17).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ot.get(17).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ot.get(17).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ot.get(17).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ot.get(17).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ot.get(17).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(18).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ot.get(18).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ot.get(18).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ot.get(18).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ot.get(18).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ot.get(18).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ot.get(18).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(19).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ot.get(19).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ot.get(19).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ot.get(19).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ot.get(19).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ot.get(19).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ot.get(19).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });

        ButtonListRemoveItem.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelComputernElectronicsInfo.get(0).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ot.get(0).get(2)));
            }
        });
        ButtonListRemoveItem.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelComputernElectronicsInfo.get(1).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ot.get(1).get(2)));
            }
        });
        ButtonListRemoveItem.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelComputernElectronicsInfo.get(2).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ot.get(2).get(2)));
            }
        });
        ButtonListRemoveItem.get(3).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelComputernElectronicsInfo.get(3).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ot.get(3).get(2)));
            }
        });
        ButtonListRemoveItem.get(4).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelComputernElectronicsInfo.get(4).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ot.get(4).get(2)));
            }
        });
        ButtonListRemoveItem.get(5).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelComputernElectronicsInfo.get(5).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ot.get(5).get(2)));
            }
        });
        ButtonListRemoveItem.get(6).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelComputernElectronicsInfo.get(6).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ot.get(6).get(2)));
            }
        });
        ButtonListRemoveItem.get(7).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelComputernElectronicsInfo.get(7).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ot.get(7).get(2)));
            }
        });
        ButtonListRemoveItem.get(8).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelComputernElectronicsInfo.get(8).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ot.get(8).get(2)));
            }
        });
        ButtonListRemoveItem.get(9).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelComputernElectronicsInfo.get(9).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ot.get(9).get(2)));
            }
        });
        ButtonListRemoveItem.get(10).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelComputernElectronicsInfo.get(10).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ot.get(10).get(2)));
            }
        });
        ButtonListRemoveItem.get(11).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelComputernElectronicsInfo.get(11).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ot.get(11).get(2)));
            }
        });
        ButtonListRemoveItem.get(12).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelComputernElectronicsInfo.get(12).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ot.get(12).get(2)));
            }
        });
        ButtonListRemoveItem.get(13).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelComputernElectronicsInfo.get(13).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ot.get(13).get(2)));
            }
        });
        ButtonListRemoveItem.get(14).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelComputernElectronicsInfo.get(14).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ot.get(14).get(2)));
            }
        });
        ButtonListRemoveItem.get(15).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelComputernElectronicsInfo.get(15).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ot.get(15).get(2)));
            }
        });
        ButtonListRemoveItem.get(16).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelComputernElectronicsInfo.get(16).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ot.get(16).get(2)));
            }
        });
        ButtonListRemoveItem.get(17).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelComputernElectronicsInfo.get(17).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ot.get(17).get(2)));
            }
        });
        ButtonListRemoveItem.get(18).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelComputernElectronicsInfo.get(18).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ot.get(18).get(2)));
            }
        });
        ButtonListRemoveItem.get(19).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelComputernElectronicsInfo.get(19).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ot.get(19).get(2)));
            }
        });

    }
    /**
     * HyperLinkView2() method
     * this methods displays all the products that are present in the database under the kitchen and home category onto the grid view
     * all the button componets seen in each grid frame are initilized in this method
     * */
    public void HyperLink2(ActionEvent event) {
        ArrayList<Label> LabelKitchennHomeInfo = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            Label l2g3367 = new Label();
            LabelKitchennHomeInfo.add(l2g3367);
        }
        ButtonList();
        lbProductDsiplay.setText("Kitchen and Home");
        gPane.getChildren().retainAll(gPane.getChildren().get(0));
        String cat = "Kitchen and Home";
        try {
            String q2 = "SELECT COUNT(*) AS rowcount FROM productinfo WHERE product_category = '" + cat + "'";
            Statement s = connectDB.createStatement();
            ResultSet r = s.executeQuery(q2);
            r.next();
            row = r.getInt("rowcount");


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        Label lb1;


        try {
            String query1 = "SELECT prduct_name,  product_store, product_price, product_category,    product_linkimage, product_owner, owner_details, product_location, product_quantity FROM productinfo WHERE product_category = '" + cat + "'";
            Statement st = connectDB.createStatement();
            ResultSet rs = st.executeQuery(query1);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNo = rsmd.getColumnCount();

            while (rs.next()) {
                inn = new ArrayList<String>();
                for (int i = 1; i <= columnNo; i++) {
                    inn.add(rs.getString(i));
                }
                o.add(inn);
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


        int cnt = 0;


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {

                if (cnt <= (row - 1)) {

                    String text = "Product Name: " + o.get(cnt).get(0) + "\nPrice: R" + o.get(cnt).get(2) + "\nStore: " + o.get(cnt).get(1);
                    LabelKitchennHomeInfo.get(cnt).setText(text);
                    LabelKitchennHomeInfo.get(cnt).setFont(new Font("Arial Bold", 15));
                    LabelKitchennHomeInfo.get(cnt).setMinWidth(50);
                    LabelKitchennHomeInfo.get(cnt).setMaxHeight(50);
                    gPane.add(LabelKitchennHomeInfo.get(cnt), i, j);
                    gPane.add(ButtonListAddtoCart.get(cnt), i, j);
                    gPane.add(ButtonListViewMore.get(cnt), i, j);
                    gPane.add(ButtonListRemoveItem.get(cnt), i, j);
                    LabelKitchennHomeInfo.get(cnt).setAlignment(Pos.TOP_RIGHT);
                    ButtonListViewMore.get(cnt).setAlignment(Pos.BOTTOM_RIGHT);
                    gPane.setValignment(LabelKitchennHomeInfo.get(cnt), VPos.TOP);
                    gPane.setHalignment(ButtonListRemoveItem.get(cnt), HPos.RIGHT);
                    gPane.setValignment(ButtonListAddtoCart.get(cnt), VPos.BOTTOM);
                    gPane.setHalignment(ButtonListViewMore.get(cnt), HPos.LEFT);
                    gPane.setVgap(5);
                    gPane.setHgap(5);
                    cnt++;

                } else if (cnt > (row - 1)) {
                    String text = "Product not available";
                    lb1 = new Label(text);

                    lb1.setFont(new Font("Arial Italics", 13));
                    lb1.setMinWidth(50);
                    lb1.setMaxHeight(50);
                    gPane.add(lb1, i, j);
                    lb1.setAlignment(Pos.TOP_RIGHT);
                    gPane.setValignment(lb1, VPos.TOP);
                    gPane.setVgap(5);
                    gPane.setHgap(5);


                }
            }
        }

        EventHandler<ActionEvent> eventaa = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");

                String text = LabelKitchennHomeInfo.get(0).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(o.get(0).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventaaa = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventaaa);
            }
        };
        ButtonListAddtoCart.get(0).setOnAction(eventaa);

        EventHandler<ActionEvent> eventbb = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelKitchennHomeInfo.get(1).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(o.get(1).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventbbb = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventbbb);
            }
        };
        ButtonListAddtoCart.get(1).setOnAction(eventbb);

        EventHandler<ActionEvent> eventcc = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelKitchennHomeInfo.get(2).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(o.get(2).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventccc = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventccc);
            }
        };
        ButtonListAddtoCart.get(2).setOnAction(eventcc);

        EventHandler<ActionEvent> eventdd = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelKitchennHomeInfo.get(3).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(o.get(3).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventddd = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventddd);
            }
        };
        ButtonListAddtoCart.get(3).setOnAction(eventdd);

        EventHandler<ActionEvent> eventee = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelKitchennHomeInfo.get(4).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(o.get(4).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventeee = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventeee);
            }
        };
        ButtonListAddtoCart.get(4).setOnAction(eventee);

        EventHandler<ActionEvent> eventff = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelKitchennHomeInfo.get(5).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(o.get(5).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventfff = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventfff);
            }
        };
        ButtonListAddtoCart.get(5).setOnAction(eventff);

        EventHandler<ActionEvent> eventgg = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelKitchennHomeInfo.get(6).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(o.get(6).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventggg = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventggg);
            }
        };
        ButtonListAddtoCart.get(6).setOnAction(eventgg);

        EventHandler<ActionEvent> eventhh = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelKitchennHomeInfo.get(7).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(o.get(7).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventhhh = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventhhh);
            }
        };
        ButtonListAddtoCart.get(7).setOnAction(eventhh);

        EventHandler<ActionEvent> eventii = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelKitchennHomeInfo.get(8).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(o.get(8).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventiii = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventiii);
            }
        };
        ButtonListAddtoCart.get(8).setOnAction(eventii);

        EventHandler<ActionEvent> eventjj = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelKitchennHomeInfo.get(9).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(o.get(9).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventjjj = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventjjj);
            }
        };
        ButtonListAddtoCart.get(9).setOnAction(eventjj);

        EventHandler<ActionEvent> eventkk = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelKitchennHomeInfo.get(10).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(o.get(10).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventkkk = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventkkk);
            }
        };
        ButtonListAddtoCart.get(10).setOnAction(eventkk);

        EventHandler<ActionEvent> eventll = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelKitchennHomeInfo.get(11).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(o.get(11).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventlll = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventlll);
            }
        };
        ButtonListAddtoCart.get(11).setOnAction(eventll);

        EventHandler<ActionEvent> eventmm = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelKitchennHomeInfo.get(12).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(o.get(12).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventmmm = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventmmm);
            }
        };
        ButtonListAddtoCart.get(12).setOnAction(eventmm);

        EventHandler<ActionEvent> eventnn = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");

                String text = LabelKitchennHomeInfo.get(13).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(o.get(13).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventnnn = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventnnn);
            }
        };
        ButtonListAddtoCart.get(13).setOnAction(eventnn);

        EventHandler<ActionEvent> eventoo = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelKitchennHomeInfo.get(14).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(o.get(14).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> evento00 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(evento00);
            }
        };
        ButtonListAddtoCart.get(14).setOnAction(eventoo);

        EventHandler<ActionEvent> eventpp = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelKitchennHomeInfo.get(15).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(o.get(15).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventppp = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventppp);
            }
        };
        ButtonListAddtoCart.get(15).setOnAction(eventpp);

        EventHandler<ActionEvent> eventqq = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelKitchennHomeInfo.get(16).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(o.get(16).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventqqq = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventqqq);
            }
        };
        ButtonListAddtoCart.get(16).setOnAction(eventqq);

        EventHandler<ActionEvent> eventrr = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelKitchennHomeInfo.get(17).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(o.get(17).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventrrr = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventrrr);
            }
        };
        ButtonListAddtoCart.get(17).setOnAction(eventrr);

        EventHandler<ActionEvent> eventss = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelKitchennHomeInfo.get(18).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(o.get(18).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventsss = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventsss);
            }
        };
        ButtonListAddtoCart.get(18).setOnAction(eventss);

        EventHandler<ActionEvent> eventtt = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelKitchennHomeInfo.get(19).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(o.get(19).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventttt = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventttt);
            }
        };
        ButtonListAddtoCart.get(19).setOnAction(eventtt);

        ButtonListViewMore.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = o.get(0).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (o.get(0).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + o.get(0).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + o.get(0).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + o.get(0).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = o.get(0).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = o.get(1).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (o.get(1).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + o.get(1).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + o.get(1).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + o.get(1).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = o.get(1).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = o.get(2).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (o.get(2).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + o.get(2).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + o.get(2).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + o.get(2).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = o.get(2).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(3).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = o.get(3).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (o.get(3).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + o.get(3).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + o.get(3).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + o.get(3).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = o.get(3).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(4).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = o.get(4).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (o.get(4).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + o.get(4).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + o.get(4).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + o.get(4).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = o.get(4).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(5).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = o.get(5).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (o.get(5).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + o.get(5).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + o.get(5).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + o.get(5).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = o.get(5).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(6).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = o.get(6).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (o.get(6).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + o.get(6).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + o.get(6).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + o.get(6).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = o.get(6).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(7).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = o.get(7).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (o.get(7).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + o.get(7).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + o.get(7).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + o.get(7).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = o.get(7).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(8).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = o.get(8).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (o.get(8).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + o.get(8).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + o.get(8).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + o.get(8).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = o.get(8).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(9).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = o.get(9).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (o.get(9).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + o.get(9).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + o.get(9).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + o.get(9).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = o.get(9).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(10).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = o.get(10).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (o.get(10).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + o.get(10).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + o.get(10).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + o.get(10).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = o.get(10).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(11).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = o.get(11).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (o.get(11).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + o.get(11).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + o.get(11).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + o.get(11).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = o.get(11).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(12).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = o.get(12).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (o.get(12).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + o.get(12).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + o.get(12).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + o.get(12).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = o.get(12).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(13).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = o.get(13).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (o.get(13).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + o.get(13).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + o.get(13).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + o.get(13).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = o.get(13).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(14).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = o.get(14).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (o.get(14).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + o.get(14).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + o.get(14).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + o.get(14).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = o.get(14).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(15).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = o.get(15).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (o.get(15).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + o.get(15).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + o.get(15).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + o.get(15).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = o.get(15).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(16).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = o.get(16).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (o.get(16).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + o.get(16).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + o.get(16).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + o.get(16).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = o.get(16).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(17).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = o.get(17).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (o.get(17).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + o.get(17).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + o.get(17).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + o.get(17).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = o.get(17).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(18).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = o.get(18).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (o.get(18).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + o.get(18).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + o.get(18).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + o.get(18).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = o.get(18).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(19).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = o.get(19).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (o.get(19).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + o.get(19).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + o.get(19).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + o.get(19).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = o.get(19).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });

        ButtonListRemoveItem.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelKitchennHomeInfo.get(0).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(o.get(0).get(2)));
            }
        });
        ButtonListRemoveItem.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelKitchennHomeInfo.get(1).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(o.get(1).get(2)));
            }
        });
        ButtonListRemoveItem.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelKitchennHomeInfo.get(2).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(o.get(2).get(2)));
            }
        });
        ButtonListRemoveItem.get(3).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelKitchennHomeInfo.get(3).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(o.get(3).get(2)));
            }
        });
        ButtonListRemoveItem.get(4).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelKitchennHomeInfo.get(4).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(o.get(4).get(2)));
            }
        });
        ButtonListRemoveItem.get(5).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelKitchennHomeInfo.get(5).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(o.get(5).get(2)));
            }
        });
        ButtonListRemoveItem.get(6).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelKitchennHomeInfo.get(6).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(o.get(6).get(2)));
            }
        });
        ButtonListRemoveItem.get(7).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelKitchennHomeInfo.get(7).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(o.get(7).get(2)));
            }
        });
        ButtonListRemoveItem.get(8).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelKitchennHomeInfo.get(8).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(o.get(8).get(2)));
            }
        });
        ButtonListRemoveItem.get(9).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelKitchennHomeInfo.get(9).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(o.get(9).get(2)));
            }
        });
        ButtonListRemoveItem.get(10).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelKitchennHomeInfo.get(10).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(o.get(10).get(2)));
            }
        });
        ButtonListRemoveItem.get(11).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelKitchennHomeInfo.get(11).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(o.get(11).get(2)));
            }
        });
        ButtonListRemoveItem.get(12).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelKitchennHomeInfo.get(12).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(o.get(12).get(2)));
            }
        });
        ButtonListRemoveItem.get(13).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelKitchennHomeInfo.get(13).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(o.get(13).get(2)));
            }
        });
        ButtonListRemoveItem.get(14).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelKitchennHomeInfo.get(14).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(o.get(14).get(2)));
            }
        });
        ButtonListRemoveItem.get(15).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelKitchennHomeInfo.get(15).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(o.get(15).get(2)));
            }
        });
        ButtonListRemoveItem.get(16).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelKitchennHomeInfo.get(16).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(o.get(16).get(2)));
            }
        });
        ButtonListRemoveItem.get(17).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelKitchennHomeInfo.get(17).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(o.get(17).get(2)));
            }
        });
        ButtonListRemoveItem.get(18).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelKitchennHomeInfo.get(18).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(o.get(18).get(2)));
            }
        });
        ButtonListRemoveItem.get(19).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelKitchennHomeInfo.get(19).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(o.get(19).get(2)));
            }
        });


    }
    /**
     * HyperLinkView3() method
     * this methods displays all the products that are present in the database under the apparel category onto the grid view
     * all the button componets seen in each grid frame are initilized in this method
     * */
    public void HyperLink3(ActionEvent event) {
        ArrayList<Label> LabelApparelInfo = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            Label l2g336 = new Label();
            LabelApparelInfo.add(l2g336);
        }
        ButtonList();
        lbProductDsiplay.setText("Apparel");
        gPane.getChildren().retainAll(gPane.getChildren().get(0));
        String cat = "Apparel";
        try {
            String q2 = "SELECT COUNT(*) AS rowcount FROM productinfo WHERE product_category = '" + cat + "'";
            Statement s = connectDB.createStatement();
            ResultSet r = s.executeQuery(q2);
            r.next();
            row = r.getInt("rowcount");


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        Label lb2;


        try {
            String query1 = "SELECT prduct_name,  product_store, product_price, product_category,  product_linkimage, product_owner, owner_details, product_location, product_quantity FROM productinfo WHERE product_category = '" + cat + "'";
            Statement st = connectDB.createStatement();
            ResultSet rs = st.executeQuery(query1);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNo = rsmd.getColumnCount();

            while (rs.next()) {
                innn = new ArrayList<String>();
                for (int i = 1; i <= columnNo; i++) {
                    innn.add(rs.getString(i));
                }
                oo.add(innn);
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


        int cnt = 0;


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {

                if (cnt <= (row - 1)) {

                    String text = "Product Name: " + oo.get(cnt).get(0) + "\nPrice: R" + oo.get(cnt).get(2) + "\nStore: " + oo.get(cnt).get(1);
                    LabelApparelInfo.get(cnt).setText(text);
                    LabelApparelInfo.get(cnt).setFont(new Font("Arial Bold", 15));
                    LabelApparelInfo.get(cnt).setMinWidth(50);
                    LabelApparelInfo.get(cnt).setMaxHeight(50);
                    gPane.add(LabelApparelInfo.get(cnt), i, j);
                    gPane.add(ButtonListAddtoCart.get(cnt), i, j);
                    gPane.add(ButtonListViewMore.get(cnt), i, j);
                    gPane.add(ButtonListRemoveItem.get(cnt), i, j);
                    LabelApparelInfo.get(cnt).setAlignment(Pos.TOP_RIGHT);
                    ButtonListViewMore.get(cnt).setAlignment(Pos.BOTTOM_RIGHT);
                    gPane.setValignment(LabelApparelInfo.get(cnt), VPos.TOP);
                    gPane.setHalignment(ButtonListRemoveItem.get(cnt), HPos.RIGHT);
                    gPane.setValignment(ButtonListAddtoCart.get(cnt), VPos.BOTTOM);
                    gPane.setHalignment(ButtonListViewMore.get(cnt), HPos.LEFT);
                    gPane.setVgap(5);
                    gPane.setHgap(5);
                    cnt++;

                } else if (cnt > (row - 1)) {
                    String text = "Product not available";
                    lb2 = new Label(text);

                    lb2.setFont(new Font("Arial Italics", 13));
                    lb2.setMinWidth(50);
                    lb2.setMaxHeight(50);
                    gPane.add(lb2, i, j);
                    lb2.setAlignment(Pos.TOP_RIGHT);
                    gPane.setValignment(lb2, VPos.TOP);
                    gPane.setVgap(5);
                    gPane.setHgap(5);


                }
            }
        }
        EventHandler<ActionEvent> eventa = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");

                String text = LabelApparelInfo.get(0).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(oo.get(0).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventaa = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventaa);
            }
        };
        ButtonListAddtoCart.get(0).setOnAction(eventa);


        EventHandler<ActionEvent> eventb = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelApparelInfo.get(1).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(oo.get(1).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventbb = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventbb);
            }
        };
        ButtonListAddtoCart.get(1).setOnAction(eventb);

        EventHandler<ActionEvent> eventc = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelApparelInfo.get(2).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(oo.get(2).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventcc = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventcc);
            }
        };
        ButtonListAddtoCart.get(2).setOnAction(eventc);

        EventHandler<ActionEvent> eventd = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelApparelInfo.get(3).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(oo.get(3).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventdd = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventdd);
            }
        };
        ButtonListAddtoCart.get(3).setOnAction(eventd);

        EventHandler<ActionEvent> evente = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelApparelInfo.get(4).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(oo.get(4).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventee = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventee);
            }
        };
        ButtonListAddtoCart.get(4).setOnAction(evente);

        EventHandler<ActionEvent> eventf = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelApparelInfo.get(5).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(oo.get(5).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventff = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventff);
            }
        };
        ButtonListAddtoCart.get(5).setOnAction(eventf);

        EventHandler<ActionEvent> eventg = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelApparelInfo.get(6).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(oo.get(6).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventgg = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventgg);
            }
        };
        ButtonListAddtoCart.get(6).setOnAction(eventg);

        EventHandler<ActionEvent> eventh = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelApparelInfo.get(7).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(oo.get(7).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventhh = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventhh);
            }
        };
        ButtonListAddtoCart.get(7).setOnAction(eventh);

        EventHandler<ActionEvent> eventi = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelApparelInfo.get(8).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(oo.get(8).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventii = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventii);
            }
        };
        ButtonListAddtoCart.get(8).setOnAction(eventi);

        EventHandler<ActionEvent> eventj = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelApparelInfo.get(9).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(oo.get(9).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventjj = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventjj);
            }
        };
        ButtonListAddtoCart.get(9).setOnAction(eventj);

        EventHandler<ActionEvent> eventk = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelApparelInfo.get(10).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(oo.get(10).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventkk = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventkk);
            }
        };
        ButtonListAddtoCart.get(10).setOnAction(eventk);

        EventHandler<ActionEvent> eventl = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelApparelInfo.get(11).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(oo.get(11).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventll = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventll);
            }
        };
        ButtonListAddtoCart.get(11).setOnAction(eventl);

        EventHandler<ActionEvent> eventm = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelApparelInfo.get(12).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(oo.get(12).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventmm = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventmm);
            }
        };
        ButtonListAddtoCart.get(12).setOnAction(eventm);

        EventHandler<ActionEvent> eventn = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelApparelInfo.get(13).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(oo.get(13).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventnn = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventnn);
            }
        };
        ButtonListAddtoCart.get(13).setOnAction(eventn);

        EventHandler<ActionEvent> evento = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelApparelInfo.get(14).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(oo.get(14).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event00 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event00);
            }
        };
        ButtonListAddtoCart.get(14).setOnAction(evento);

        EventHandler<ActionEvent> eventp = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelApparelInfo.get(15).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(oo.get(15).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventpp = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventpp);
            }
        };
        ButtonListAddtoCart.get(15).setOnAction(eventp);

        EventHandler<ActionEvent> eventq = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelApparelInfo.get(16).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(oo.get(16).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventqq = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventqq);
            }
        };
        ButtonListAddtoCart.get(16).setOnAction(eventq);

        EventHandler<ActionEvent> eventr = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelApparelInfo.get(17).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(oo.get(17).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventrr = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventrr);
            }
        };
        ButtonListAddtoCart.get(17).setOnAction(eventr);

        EventHandler<ActionEvent> events = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelApparelInfo.get(18).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(oo.get(18).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventss = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventss);
            }
        };
        ButtonListAddtoCart.get(18).setOnAction(events);

        EventHandler<ActionEvent> eventt = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelApparelInfo.get(19).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(oo.get(19).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> eventtt = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(eventtt);
            }
        };
        ButtonListAddtoCart.get(19).setOnAction(eventt);


        ButtonListViewMore.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = oo.get(0).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (oo.get(0).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + oo.get(0).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + oo.get(0).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + oo.get(0).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = oo.get(0).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = oo.get(1).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (oo.get(1).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + oo.get(1).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + oo.get(1).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + oo.get(1).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = oo.get(1).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = oo.get(2).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (oo.get(2).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + oo.get(2).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + oo.get(2).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + oo.get(2).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = oo.get(2).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(3).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = oo.get(3).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (oo.get(3).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + oo.get(3).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + oo.get(3).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + oo.get(3).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = oo.get(3).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(4).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = oo.get(4).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (oo.get(4).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + oo.get(4).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + oo.get(4).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + oo.get(4).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = oo.get(4).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(5).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = oo.get(5).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (oo.get(5).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + oo.get(5).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + oo.get(5).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + oo.get(5).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = oo.get(5).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(6).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = oo.get(6).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (oo.get(6).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + oo.get(6).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + oo.get(6).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + oo.get(6).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = oo.get(6).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(7).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = oo.get(7).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (oo.get(7).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + oo.get(7).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + oo.get(7).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + oo.get(7).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = oo.get(7).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(8).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = oo.get(8).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (oo.get(8).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + oo.get(8).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + oo.get(8).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + oo.get(8).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = oo.get(8).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(9).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = oo.get(9).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (oo.get(9).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + oo.get(9).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + oo.get(9).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + oo.get(9).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = oo.get(9).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(10).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = oo.get(10).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (oo.get(10).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + oo.get(10).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + oo.get(10).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + oo.get(10).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = oo.get(10).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(11).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = oo.get(11).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (oo.get(11).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + oo.get(11).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + oo.get(11).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + oo.get(11).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = oo.get(11).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(12).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = oo.get(12).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (oo.get(12).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + oo.get(12).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + oo.get(12).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + oo.get(12).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = oo.get(12).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(13).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = oo.get(13).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (oo.get(13).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + oo.get(13).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + oo.get(13).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + oo.get(13).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = oo.get(13).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(14).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = oo.get(14).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (oo.get(14).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + oo.get(14).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + oo.get(14).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + oo.get(14).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = oo.get(14).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(15).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = oo.get(15).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (oo.get(15).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + oo.get(15).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + oo.get(15).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + oo.get(15).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = oo.get(15).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(16).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = oo.get(16).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (oo.get(16).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + oo.get(16).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + oo.get(16).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + oo.get(16).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = oo.get(16).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(17).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = oo.get(17).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (oo.get(17).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + oo.get(17).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + oo.get(17).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + oo.get(17).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = oo.get(17).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(18).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = oo.get(18).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (oo.get(18).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + oo.get(18).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + oo.get(18).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + oo.get(18).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = oo.get(18).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(19).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = oo.get(19).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (oo.get(19).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + oo.get(19).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + oo.get(19).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + oo.get(19).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = oo.get(19).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });

        ButtonListRemoveItem.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelApparelInfo.get(0).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(oo.get(0).get(2)));
            }
        });
        ButtonListRemoveItem.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelApparelInfo.get(1).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(oo.get(1).get(2)));
            }
        });
        ButtonListRemoveItem.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelApparelInfo.get(2).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(oo.get(2).get(2)));
            }
        });
        ButtonListRemoveItem.get(3).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelApparelInfo.get(3).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(oo.get(3).get(2)));
            }
        });
        ButtonListRemoveItem.get(4).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelApparelInfo.get(4).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(oo.get(4).get(2)));
            }
        });
        ButtonListRemoveItem.get(5).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelApparelInfo.get(5).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(oo.get(5).get(2)));
            }
        });
        ButtonListRemoveItem.get(6).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelApparelInfo.get(6).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(oo.get(6).get(2)));
            }
        });
        ButtonListRemoveItem.get(7).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelApparelInfo.get(7).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(oo.get(7).get(2)));
            }
        });
        ButtonListRemoveItem.get(8).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelApparelInfo.get(8).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(oo.get(8).get(2)));
            }
        });
        ButtonListRemoveItem.get(9).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelApparelInfo.get(9).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(oo.get(9).get(2)));
            }
        });
        ButtonListRemoveItem.get(10).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelApparelInfo.get(10).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(oo.get(10).get(2)));
            }
        });
        ButtonListRemoveItem.get(11).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelApparelInfo.get(11).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(oo.get(11).get(2)));
            }
        });
        ButtonListRemoveItem.get(12).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelApparelInfo.get(12).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(oo.get(12).get(2)));
            }
        });
        ButtonListRemoveItem.get(13).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelApparelInfo.get(13).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(oo.get(13).get(2)));
            }
        });
        ButtonListRemoveItem.get(14).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelApparelInfo.get(14).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(oo.get(14).get(2)));
            }
        });
        ButtonListRemoveItem.get(15).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelApparelInfo.get(15).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(oo.get(15).get(2)));
            }
        });
        ButtonListRemoveItem.get(16).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelApparelInfo.get(16).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(oo.get(16).get(2)));
            }
        });
        ButtonListRemoveItem.get(17).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelApparelInfo.get(17).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(oo.get(17).get(2)));
            }
        });
        ButtonListRemoveItem.get(18).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelApparelInfo.get(18).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(oo.get(18).get(2)));
            }
        });
        ButtonListRemoveItem.get(19).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelApparelInfo.get(19).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(oo.get(19).get(2)));
            }
        });


    }
    /**
     * HyperLinkView4() method
     * this methods displays all the products that are present in the database under the sneaker category onto the grid view
     * all the button componets seen in each grid frame are initilized in this method
     **/

    public void HyperLink4(ActionEvent event) {
        ArrayList<Label> LabelSneakerInfo = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            Label l2g33 = new Label();
            LabelSneakerInfo.add(l2g33);
        }
        ButtonList();
        lbProductDsiplay.setText("Sneakers");
        gPane.getChildren().retainAll(gPane.getChildren().get(0));
        String cat = "Sneakers";
        try {
            String q2 = "SELECT COUNT(*) AS rowcount FROM productinfo WHERE product_category = '" + cat + "'";
            Statement s = connectDB.createStatement();
            ResultSet r = s.executeQuery(q2);
            r.next();
            row = r.getInt("rowcount");


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        Label lb2;


        try {
            String query1 = "SELECT prduct_name,  product_store, product_price, product_category,  product_linkimage, product_owner, owner_details, product_location, product_quantity  FROM productinfo WHERE product_category = '" + cat + "'";
            Statement st = connectDB.createStatement();
            ResultSet rs = st.executeQuery(query1);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNo = rsmd.getColumnCount();

            while (rs.next()) {
                innnn = new ArrayList<String>();
                for (int i = 1; i <= columnNo; i++) {
                    innnn.add(rs.getString(i));
                }
                ooo.add(innnn);
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


        int cnt = 0;


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {

                if (cnt <= (row - 1)) {

                    String text = "Product Name: " + ooo.get(cnt).get(0) + "\nPrice: R" + ooo.get(cnt).get(2) + "\nStore: " + ooo.get(cnt).get(1);
                    LabelSneakerInfo.get(cnt).setText(text);
                    LabelSneakerInfo.get(cnt).setFont(new Font("Arial Bold", 15));
                    LabelSneakerInfo.get(cnt).setMinWidth(50);
                    LabelSneakerInfo.get(cnt).setMaxHeight(50);
                    gPane.add(LabelSneakerInfo.get(cnt), i, j);
                    gPane.add(ButtonListAddtoCart.get(cnt), i, j);
                    gPane.add(ButtonListViewMore.get(cnt), i, j);
                    gPane.add(ButtonListRemoveItem.get(cnt), i, j);
                    LabelSneakerInfo.get(cnt).setAlignment(Pos.TOP_RIGHT);
                    ButtonListViewMore.get(cnt).setAlignment(Pos.BOTTOM_RIGHT);
                    gPane.setValignment(LabelSneakerInfo.get(cnt), VPos.TOP);
                    gPane.setHalignment(ButtonListRemoveItem.get(cnt), HPos.RIGHT);
                    gPane.setValignment(ButtonListAddtoCart.get(cnt), VPos.BOTTOM);
                    gPane.setHalignment(ButtonListViewMore.get(cnt), HPos.LEFT);
                    gPane.setVgap(5);
                    gPane.setHgap(5);
                    cnt++;

                } else if (cnt > (row - 1)) {
                    String text = "Product not available";
                    lb2 = new Label(text);
                    lb2.setFont(new Font("Arial Italics", 13));
                    lb2.setMinWidth(50);
                    lb2.setMaxHeight(50);
                    gPane.add(lb2, i, j);
                    lb2.setAlignment(Pos.TOP_RIGHT);
                    gPane.setValignment(lb2, VPos.TOP);
                    gPane.setVgap(5);
                    gPane.setHgap(5);


                }
            }
        }
        EventHandler<ActionEvent> event90 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSneakerInfo.get(0).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ooo.get(0).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event100 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event100);
            }
        };
        ButtonListAddtoCart.get(0).setOnAction(event90);

        EventHandler<ActionEvent> event91 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSneakerInfo.get(1).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ooo.get(1).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event101 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event101);
            }
        };
        ButtonListAddtoCart.get(1).setOnAction(event91);

        EventHandler<ActionEvent> event92 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSneakerInfo.get(2).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ooo.get(2).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event102 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event102);
            }
        };
        ButtonListAddtoCart.get(2).setOnAction(event92);

        EventHandler<ActionEvent> event93 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");

                String text = LabelSneakerInfo.get(3).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ooo.get(3).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event103 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event103);
            }
        };
        ButtonListAddtoCart.get(3).setOnAction(event93);

        EventHandler<ActionEvent> event94 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSneakerInfo.get(4).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ooo.get(4).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event104 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event104);
            }
        };
        ButtonListAddtoCart.get(4).setOnAction(event94);

        EventHandler<ActionEvent> event95 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSneakerInfo.get(5).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ooo.get(5).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event105 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event105);
            }
        };
        ButtonListAddtoCart.get(5).setOnAction(event95);

        EventHandler<ActionEvent> event96 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSneakerInfo.get(6).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ooo.get(6).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event106 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event106);
            }
        };
        ButtonListAddtoCart.get(6).setOnAction(event96);

        EventHandler<ActionEvent> event97 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSneakerInfo.get(7).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ooo.get(7).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event107 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event107);
            }
        };
        ButtonListAddtoCart.get(7).setOnAction(event97);

        EventHandler<ActionEvent> event98 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSneakerInfo.get(8).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ooo.get(8).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event108 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event108);
            }
        };
        ButtonListAddtoCart.get(8).setOnAction(event98);

        EventHandler<ActionEvent> event99 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSneakerInfo.get(9).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ooo.get(9).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event109 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event109);
            }
        };
        ButtonListAddtoCart.get(9).setOnAction(event99);

        EventHandler<ActionEvent> event090 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");

                String text = LabelSneakerInfo.get(10).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ooo.get(10).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event0911 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event0911);
            }
        };
        ButtonListAddtoCart.get(10).setOnAction(event090);

        EventHandler<ActionEvent> event091 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSneakerInfo.get(11).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ooo.get(11).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event0922 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event0922);
            }
        };
        ButtonListAddtoCart.get(11).setOnAction(event091);

        EventHandler<ActionEvent> event092 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSneakerInfo.get(12).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ooo.get(12).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event0933 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event0933);
            }
        };
        ButtonListAddtoCart.get(12).setOnAction(event092);

        EventHandler<ActionEvent> event093 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");

                String text = LabelSneakerInfo.get(13).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ooo.get(13).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event0944 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event0944);
            }
        };
        ButtonListAddtoCart.get(13).setOnAction(event093);

        EventHandler<ActionEvent> event094 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSneakerInfo.get(14).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ooo.get(14).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event0955 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event0955);
            }
        };
        ButtonListAddtoCart.get(14).setOnAction(event094);

        EventHandler<ActionEvent> event095 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSneakerInfo.get(15).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ooo.get(15).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event0966 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event0966);
            }
        };
        ButtonListAddtoCart.get(15).setOnAction(event095);

        EventHandler<ActionEvent> event096 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSneakerInfo.get(16).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ooo.get(16).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event0977 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event0977);
            }
        };
        ButtonListAddtoCart.get(16).setOnAction(event096);

        EventHandler<ActionEvent> event097 = new EventHandler<ActionEvent>() {
            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSneakerInfo.get(17).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ooo.get(17).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event0988 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event0988);
            }
        };
        ButtonListAddtoCart.get(17).setOnAction(event097);

        EventHandler<ActionEvent> event098 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSneakerInfo.get(18).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ooo.get(18).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event0999 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event0999);
            }
        };

        ButtonListAddtoCart.get(18).setOnAction(event098);

        EventHandler<ActionEvent> event099 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSneakerInfo.get(19).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ooo.get(19).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event0910 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event0910);
            }
        };
        ButtonListAddtoCart.get(19).setOnAction(event099);

        ButtonListViewMore.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ooo.get(0).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ooo.get(0).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ooo.get(0).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ooo.get(0).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ooo.get(0).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ooo.get(0).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ooo.get(1).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ooo.get(1).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ooo.get(1).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ooo.get(1).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ooo.get(1).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ooo.get(1).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ooo.get(2).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ooo.get(2).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ooo.get(2).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ooo.get(2).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ooo.get(2).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ooo.get(2).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(3).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ooo.get(3).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ooo.get(3).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ooo.get(3).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ooo.get(3).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ooo.get(3).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ooo.get(3).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(4).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ooo.get(4).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ooo.get(4).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ooo.get(4).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ooo.get(4).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ooo.get(4).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ooo.get(4).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(5).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ooo.get(5).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ooo.get(5).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ooo.get(5).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ooo.get(5).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ooo.get(5).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ooo.get(5).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(6).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ooo.get(6).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ooo.get(6).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ooo.get(6).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ooo.get(6).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ooo.get(6).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ooo.get(6).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(7).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ooo.get(7).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ooo.get(7).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ooo.get(7).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ooo.get(7).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ooo.get(7).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ooo.get(7).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(8).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ooo.get(8).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ooo.get(8).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ooo.get(8).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ooo.get(8).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ooo.get(8).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ooo.get(8).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(9).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ooo.get(9).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ooo.get(9).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ooo.get(9).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ooo.get(9).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ooo.get(9).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ooo.get(9).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(10).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ooo.get(10).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ooo.get(10).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ooo.get(10).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ooo.get(10).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ooo.get(10).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ooo.get(10).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(11).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ooo.get(11).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ooo.get(11).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ooo.get(11).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ooo.get(11).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ooo.get(11).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ooo.get(11).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(12).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ooo.get(12).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ooo.get(12).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ooo.get(12).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ooo.get(12).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ooo.get(12).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ooo.get(12).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(13).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ooo.get(13).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ooo.get(13).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ooo.get(13).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ooo.get(13).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ooo.get(13).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ooo.get(13).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(14).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ooo.get(14).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ooo.get(14).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ooo.get(14).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ooo.get(14).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ooo.get(14).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ooo.get(14).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(15).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ooo.get(15).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ooo.get(15).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ooo.get(15).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ooo.get(15).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ooo.get(15).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ooo.get(15).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(16).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ooo.get(16).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ooo.get(16).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ooo.get(16).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ooo.get(16).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ooo.get(16).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ooo.get(16).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(17).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ooo.get(17).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ooo.get(17).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ooo.get(17).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ooo.get(17).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ooo.get(17).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ooo.get(17).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(18).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ooo.get(18).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ooo.get(18).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ooo.get(18).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ooo.get(18).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ooo.get(18).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ooo.get(18).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(19).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ooo.get(19).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ooo.get(19).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ooo.get(19).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ooo.get(19).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ooo.get(19).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ooo.get(19).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });

        ButtonListRemoveItem.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSneakerInfo.get(0).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ooo.get(0).get(2)));
            }
        });
        ButtonListRemoveItem.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSneakerInfo.get(1).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ooo.get(1).get(2)));
            }
        });
        ButtonListRemoveItem.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSneakerInfo.get(2).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ooo.get(2).get(2)));
            }
        });
        ButtonListRemoveItem.get(3).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSneakerInfo.get(3).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ooo.get(3).get(2)));
            }
        });
        ButtonListRemoveItem.get(4).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSneakerInfo.get(4).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ooo.get(4).get(2)));
            }
        });
        ButtonListRemoveItem.get(5).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSneakerInfo.get(5).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ooo.get(5).get(2)));
            }
        });
        ButtonListRemoveItem.get(6).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSneakerInfo.get(6).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ooo.get(6).get(2)));
            }
        });
        ButtonListRemoveItem.get(7).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSneakerInfo.get(7).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ooo.get(7).get(2)));
            }
        });
        ButtonListRemoveItem.get(8).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSneakerInfo.get(8).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ooo.get(8).get(2)));
            }
        });
        ButtonListRemoveItem.get(9).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSneakerInfo.get(9).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ooo.get(9).get(2)));
            }
        });
        ButtonListRemoveItem.get(10).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSneakerInfo.get(10).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ooo.get(10).get(2)));
            }
        });
        ButtonListRemoveItem.get(11).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSneakerInfo.get(11).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ooo.get(11).get(2)));
            }
        });
        ButtonListRemoveItem.get(12).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSneakerInfo.get(12).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ooo.get(12).get(2)));
            }
        });
        ButtonListRemoveItem.get(13).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSneakerInfo.get(13).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ooo.get(13).get(2)));
            }
        });
        ButtonListRemoveItem.get(14).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSneakerInfo.get(14).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ooo.get(14).get(2)));
            }
        });
        ButtonListRemoveItem.get(15).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSneakerInfo.get(15).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ooo.get(15).get(2)));
            }
        });
        ButtonListRemoveItem.get(16).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSneakerInfo.get(16).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ooo.get(16).get(2)));
            }
        });
        ButtonListRemoveItem.get(17).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSneakerInfo.get(17).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ooo.get(17).get(2)));
            }
        });
        ButtonListRemoveItem.get(18).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSneakerInfo.get(18).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ooo.get(18).get(2)));
            }
        });
        ButtonListRemoveItem.get(19).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSneakerInfo.get(19).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ooo.get(19).get(2)));
            }
        });


    }

    /**
     * HyperLinkView5() method
     * this methods displays all the products that are present in the database under the store name of Sams Shoes onto the grid view
     * all the button componets seen in each grid frame are initilized in this method
     **/

    public void HyperLink5(ActionEvent event) {
        ArrayList<Label> LabelSamsInfo = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            Label l2g3 = new Label();
            LabelSamsInfo.add(l2g3);
        }
        ButtonList();
        lbProductDsiplay.setText("Sams Shoes");
        gPane.getChildren().retainAll(gPane.getChildren().get(0));
        String cat = "Sams Shoes";
        try {
            String q2 = "SELECT COUNT(*) AS rowcount FROM productinfo WHERE product_store = '" + cat + "'";
            Statement s = connectDB.createStatement();
            ResultSet r = s.executeQuery(q2);
            r.next();
            row = r.getInt("rowcount");


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        Label lbSamsShoes;


        try {
            String query1 = "SELECT prduct_name,  product_store, product_price, product_category,   product_linkimage, product_owner, owner_details, product_location, product_quantity FROM productinfo WHERE product_store = '" + cat + "'";
            Statement st = connectDB.createStatement();
            ResultSet rs = st.executeQuery(query1);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNo = rsmd.getColumnCount();

            while (rs.next()) {
                SamsShoesInner = new ArrayList<String>();
                for (int i = 1; i <= columnNo; i++) {
                    SamsShoesInner.add(rs.getString(i));
                }
                SamsShoesOuter.add(SamsShoesInner);
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


        int cnt = 0;


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {

                if (cnt <= (row - 1)) {

                    String text = "Product Name: " + SamsShoesOuter.get(cnt).get(0) + "\nPrice: R" + SamsShoesOuter.get(cnt).get(2) + "\nStore: " + SamsShoesOuter.get(cnt).get(1);
                    LabelSamsInfo.get(cnt).setText(text);
                    LabelSamsInfo.get(cnt).setFont(new Font("Arial Bold", 15));
                    LabelSamsInfo.get(cnt).setMinWidth(50);
                    LabelSamsInfo.get(cnt).setMaxHeight(50);
                    gPane.add(LabelSamsInfo.get(cnt), i, j);
                    gPane.add(ButtonListAddtoCart.get(cnt), i, j);
                    gPane.add(ButtonListViewMore.get(cnt), i, j);
                    gPane.add(ButtonListRemoveItem.get(cnt), i, j);
                    LabelSamsInfo.get(cnt).setAlignment(Pos.TOP_RIGHT);
                    ButtonListViewMore.get(cnt).setAlignment(Pos.BOTTOM_RIGHT);
                    gPane.setValignment(LabelSamsInfo.get(cnt), VPos.TOP);
                    gPane.setHalignment(ButtonListRemoveItem.get(cnt), HPos.RIGHT);
                    gPane.setValignment(ButtonListAddtoCart.get(cnt), VPos.BOTTOM);
                    gPane.setHalignment(ButtonListViewMore.get(cnt), HPos.LEFT);
                    gPane.setVgap(5);
                    gPane.setHgap(5);
                    cnt++;

                } else if (cnt > (row - 1)) {
                    String text = "Product not available";
                    lbSamsShoes = new Label(text);

                    lbSamsShoes.setFont(new Font("Arial Italics", 13));
                    lbSamsShoes.setMinWidth(50);
                    lbSamsShoes.setMaxHeight(50);
                    gPane.add(lbSamsShoes, i, j);
                    lbSamsShoes.setAlignment(Pos.TOP_RIGHT);
                    gPane.setValignment(lbSamsShoes, VPos.TOP);
                    gPane.setVgap(5);
                    gPane.setHgap(5);


                }
            }
        }


        EventHandler<ActionEvent> event70 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");

                String text = LabelSamsInfo.get(0).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(SamsShoesOuter.get(0).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event80 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event80);
            }
        };
        ButtonListAddtoCart.get(0).setOnAction(event70);


        EventHandler<ActionEvent> event71 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSamsInfo.get(1).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(SamsShoesOuter.get(1).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event81 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event81);
            }
        };
        ButtonListAddtoCart.get(1).setOnAction(event71);

        EventHandler<ActionEvent> event72 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSamsInfo.get(2).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(SamsShoesOuter.get(2).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event82 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event82);
            }
        };
        ButtonListAddtoCart.get(2).setOnAction(event72);

        EventHandler<ActionEvent> event73 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSamsInfo.get(3).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(SamsShoesOuter.get(3).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event83 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event83);
            }
        };
        ButtonListAddtoCart.get(3).setOnAction(event73);

        EventHandler<ActionEvent> event74 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSamsInfo.get(4).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(SamsShoesOuter.get(4).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event84 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event84);
            }
        };
        ButtonListAddtoCart.get(4).setOnAction(event74);

        EventHandler<ActionEvent> event75 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSamsInfo.get(5).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(SamsShoesOuter.get(5).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event85 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event85);
            }
        };
        ButtonListAddtoCart.get(5).setOnAction(event75);

        EventHandler<ActionEvent> event76 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSamsInfo.get(6).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(SamsShoesOuter.get(6).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event86 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event86);
            }
        };
        ButtonListAddtoCart.get(6).setOnAction(event76);

        EventHandler<ActionEvent> event77 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSamsInfo.get(7).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(SamsShoesOuter.get(7).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event87 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event87);
            }
        };
        ButtonListAddtoCart.get(7).setOnAction(event77);

        EventHandler<ActionEvent> event78 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSamsInfo.get(8).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(SamsShoesOuter.get(8).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event88 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event88);
            }
        };
        ButtonListAddtoCart.get(8).setOnAction(event78);

        EventHandler<ActionEvent> event79 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSamsInfo.get(9).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(SamsShoesOuter.get(9).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event89 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event89);
            }
        };
        ButtonListAddtoCart.get(9).setOnAction(event79);

        EventHandler<ActionEvent> event700 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");

                String text = LabelSamsInfo.get(10).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(SamsShoesOuter.get(10).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event800 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event800);
            }
        };
        ButtonListAddtoCart.get(10).setOnAction(event700);


        EventHandler<ActionEvent> event701 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSamsInfo.get(11).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(SamsShoesOuter.get(11).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event801 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event801);
            }
        };
        ButtonListAddtoCart.get(11).setOnAction(event701);

        EventHandler<ActionEvent> event702 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSamsInfo.get(12).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(SamsShoesOuter.get(12).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event802 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event802);
            }
        };
        ButtonListAddtoCart.get(12).setOnAction(event702);

        EventHandler<ActionEvent> event703 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");

                String text = LabelSamsInfo.get(13).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(SamsShoesOuter.get(13).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event803 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event803);
            }
        };
        ButtonListAddtoCart.get(13).setOnAction(event703);

        EventHandler<ActionEvent> event704 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSamsInfo.get(14).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(SamsShoesOuter.get(14).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event804 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event804);
            }
        };
        ButtonListAddtoCart.get(14).setOnAction(event704);

        EventHandler<ActionEvent> event705 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSamsInfo.get(15).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(SamsShoesOuter.get(15).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event805 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event805);
            }
        };
        ButtonListAddtoCart.get(15).setOnAction(event705);

        EventHandler<ActionEvent> event706 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSamsInfo.get(16).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(SamsShoesOuter.get(16).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event806 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event806);
            }
        };
        ButtonListAddtoCart.get(16).setOnAction(event706);

        EventHandler<ActionEvent> event707 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSamsInfo.get(17).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(SamsShoesOuter.get(17).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event807 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event807);
            }
        };
        ButtonListAddtoCart.get(17).setOnAction(event707);

        EventHandler<ActionEvent> event708 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSamsInfo.get(18).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(SamsShoesOuter.get(18).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event808 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event808);
            }
        };
        ButtonListAddtoCart.get(18).setOnAction(event708);

        EventHandler<ActionEvent> event709 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelSamsInfo.get(19).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(SamsShoesOuter.get(19).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event809 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();
                    }
                };
                btnClearCart.setOnAction(event809);
            }
        };
        ButtonListAddtoCart.get(19).setOnAction(event709);

        ButtonListViewMore.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = SamsShoesOuter.get(0).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (SamsShoesOuter.get(0).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + SamsShoesOuter.get(0).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + SamsShoesOuter.get(0).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + SamsShoesOuter.get(0).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = SamsShoesOuter.get(0).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = SamsShoesOuter.get(1).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (SamsShoesOuter.get(1).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + SamsShoesOuter.get(1).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + SamsShoesOuter.get(1).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + SamsShoesOuter.get(1).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = SamsShoesOuter.get(1).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = SamsShoesOuter.get(2).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (SamsShoesOuter.get(2).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + SamsShoesOuter.get(2).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + SamsShoesOuter.get(2).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + SamsShoesOuter.get(2).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = SamsShoesOuter.get(2).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(3).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = SamsShoesOuter.get(3).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (SamsShoesOuter.get(3).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + SamsShoesOuter.get(3).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + SamsShoesOuter.get(3).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + SamsShoesOuter.get(3).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = SamsShoesOuter.get(3).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(4).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = SamsShoesOuter.get(4).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (SamsShoesOuter.get(4).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + SamsShoesOuter.get(4).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + SamsShoesOuter.get(4).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + SamsShoesOuter.get(4).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = SamsShoesOuter.get(4).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(5).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = SamsShoesOuter.get(5).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (SamsShoesOuter.get(5).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + SamsShoesOuter.get(5).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + SamsShoesOuter.get(5).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + SamsShoesOuter.get(5).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = SamsShoesOuter.get(5).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(6).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = SamsShoesOuter.get(6).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (SamsShoesOuter.get(6).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + SamsShoesOuter.get(6).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + SamsShoesOuter.get(6).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + SamsShoesOuter.get(6).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = SamsShoesOuter.get(6).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(7).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = SamsShoesOuter.get(7).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (SamsShoesOuter.get(7).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + SamsShoesOuter.get(7).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + SamsShoesOuter.get(7).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + SamsShoesOuter.get(7).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = SamsShoesOuter.get(7).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(8).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = SamsShoesOuter.get(8).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (SamsShoesOuter.get(8).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + SamsShoesOuter.get(8).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + SamsShoesOuter.get(8).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + SamsShoesOuter.get(8).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = SamsShoesOuter.get(8).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(9).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = SamsShoesOuter.get(9).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (SamsShoesOuter.get(9).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + SamsShoesOuter.get(9).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + SamsShoesOuter.get(9).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + SamsShoesOuter.get(9).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = SamsShoesOuter.get(9).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(10).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = SamsShoesOuter.get(10).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (SamsShoesOuter.get(10).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + SamsShoesOuter.get(10).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + SamsShoesOuter.get(10).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + SamsShoesOuter.get(10).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = SamsShoesOuter.get(10).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(11).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = SamsShoesOuter.get(11).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (SamsShoesOuter.get(11).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + SamsShoesOuter.get(11).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + SamsShoesOuter.get(11).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + SamsShoesOuter.get(11).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = SamsShoesOuter.get(11).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(12).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = SamsShoesOuter.get(12).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (SamsShoesOuter.get(12).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + SamsShoesOuter.get(12).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + SamsShoesOuter.get(12).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + SamsShoesOuter.get(12).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = SamsShoesOuter.get(12).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(13).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = SamsShoesOuter.get(13).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (SamsShoesOuter.get(13).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + SamsShoesOuter.get(13).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + SamsShoesOuter.get(13).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + SamsShoesOuter.get(13).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = SamsShoesOuter.get(13).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(14).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = SamsShoesOuter.get(14).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (SamsShoesOuter.get(14).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + SamsShoesOuter.get(14).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + SamsShoesOuter.get(14).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + SamsShoesOuter.get(14).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = SamsShoesOuter.get(14).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(15).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = SamsShoesOuter.get(15).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (SamsShoesOuter.get(15).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + SamsShoesOuter.get(15).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + SamsShoesOuter.get(15).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + SamsShoesOuter.get(15).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = SamsShoesOuter.get(15).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(16).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = SamsShoesOuter.get(16).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (SamsShoesOuter.get(16).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + SamsShoesOuter.get(16).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + SamsShoesOuter.get(16).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + SamsShoesOuter.get(16).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = SamsShoesOuter.get(16).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(17).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = SamsShoesOuter.get(17).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (SamsShoesOuter.get(17).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + SamsShoesOuter.get(17).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + SamsShoesOuter.get(17).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + SamsShoesOuter.get(17).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = SamsShoesOuter.get(17).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(18).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = SamsShoesOuter.get(18).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (SamsShoesOuter.get(18).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + SamsShoesOuter.get(18).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + SamsShoesOuter.get(18).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + SamsShoesOuter.get(18).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = SamsShoesOuter.get(18).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(19).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = SamsShoesOuter.get(19).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (SamsShoesOuter.get(19).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + SamsShoesOuter.get(19).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + SamsShoesOuter.get(19).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + SamsShoesOuter.get(19).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = SamsShoesOuter.get(19).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });

        ButtonListRemoveItem.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSamsInfo.get(0).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(SamsShoesOuter.get(0).get(2)));
            }
        });
        ButtonListRemoveItem.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSamsInfo.get(1).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(SamsShoesOuter.get(1).get(2)));
            }
        });
        ButtonListRemoveItem.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSamsInfo.get(2).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(SamsShoesOuter.get(2).get(2)));
            }
        });
        ButtonListRemoveItem.get(3).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSamsInfo.get(3).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(SamsShoesOuter.get(3).get(2)));
            }
        });
        ButtonListRemoveItem.get(4).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSamsInfo.get(4).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(SamsShoesOuter.get(4).get(2)));
            }
        });
        ButtonListRemoveItem.get(5).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSamsInfo.get(5).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(SamsShoesOuter.get(5).get(2)));
            }
        });
        ButtonListRemoveItem.get(6).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSamsInfo.get(6).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(SamsShoesOuter.get(6).get(2)));
            }
        });
        ButtonListRemoveItem.get(7).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSamsInfo.get(7).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(SamsShoesOuter.get(7).get(2)));
            }
        });
        ButtonListRemoveItem.get(8).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSamsInfo.get(8).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(SamsShoesOuter.get(8).get(2)));
            }
        });
        ButtonListRemoveItem.get(9).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSamsInfo.get(9).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(SamsShoesOuter.get(9).get(2)));
            }
        });
        ButtonListRemoveItem.get(10).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSamsInfo.get(10).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(SamsShoesOuter.get(10).get(2)));
            }
        });
        ButtonListRemoveItem.get(11).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSamsInfo.get(11).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(SamsShoesOuter.get(11).get(2)));
            }
        });
        ButtonListRemoveItem.get(12).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSamsInfo.get(12).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(SamsShoesOuter.get(12).get(2)));
            }
        });
        ButtonListRemoveItem.get(13).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSamsInfo.get(13).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(SamsShoesOuter.get(13).get(2)));
            }
        });
        ButtonListRemoveItem.get(14).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSamsInfo.get(14).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(SamsShoesOuter.get(14).get(2)));
            }
        });
        ButtonListRemoveItem.get(15).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSamsInfo.get(15).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(SamsShoesOuter.get(15).get(2)));
            }
        });
        ButtonListRemoveItem.get(16).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSamsInfo.get(16).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(SamsShoesOuter.get(16).get(2)));
            }
        });
        ButtonListRemoveItem.get(17).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSamsInfo.get(17).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(SamsShoesOuter.get(17).get(2)));
            }
        });
        ButtonListRemoveItem.get(18).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSamsInfo.get(18).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(SamsShoesOuter.get(18).get(2)));
            }
        });
        ButtonListRemoveItem.get(19).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelSamsInfo.get(19).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(SamsShoesOuter.get(19).get(2)));
            }
        });


    }

    /**
     * HyperLinkView6() method
     * this methods displays all the products that are present in the database under the store name of Nadias Kitchen onto the grid view
     * all the button componets seen in each grid frame are initilized in this method
     **/

    public void HyperLink6(ActionEvent event) {
        ArrayList<Label> LabelNadiasInfo = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            Label l2g = new Label();
            LabelNadiasInfo.add(l2g);
        }
        ButtonList();
        lbProductDsiplay.setText("Nadias Kitchen");
        gPane.getChildren().retainAll(gPane.getChildren().get(0));
        String cat = "Nadias Kitchen";
        try {
            String q2 = "SELECT COUNT(*) AS rowcount FROM productinfo WHERE product_store = '" + cat + "'";
            Statement s = connectDB.createStatement();
            ResultSet r = s.executeQuery(q2);
            r.next();
            row = r.getInt("rowcount");


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        Label lbNadiasKitchen;


        try {
            String query1 = "SELECT prduct_name,  product_store, product_price, product_category,   product_linkimage, product_owner, owner_details, product_location, product_quantity FROM productinfo WHERE product_store = '" + cat + "'";
            Statement st = connectDB.createStatement();
            ResultSet rs = st.executeQuery(query1);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNo = rsmd.getColumnCount();

            while (rs.next()) {
                NadiasKitchenInner = new ArrayList<String>();
                for (int i = 1; i <= columnNo; i++) {
                    NadiasKitchenInner.add(rs.getString(i));
                }
                NadiasKitchenOuter.add(NadiasKitchenInner);
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


        int cnt = 0;


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {

                if (cnt <= (row - 1)) {

                    String text = "Product Name: " + NadiasKitchenOuter.get(cnt).get(0) + "\nPrice: R" + NadiasKitchenOuter.get(cnt).get(2) + "\nStore: " + NadiasKitchenOuter.get(cnt).get(1);
                    LabelNadiasInfo.get(cnt).setText(text);
                    LabelNadiasInfo.get(cnt).setFont(new Font("Arial Bold", 15));
                    LabelNadiasInfo.get(cnt).setMinWidth(50);
                    LabelNadiasInfo.get(cnt).setMaxHeight(50);
                    gPane.add(LabelNadiasInfo.get(cnt), i, j);
                    gPane.add(ButtonListAddtoCart.get(cnt), i, j);
                    gPane.add(ButtonListViewMore.get(cnt), i, j);
                    gPane.add(ButtonListRemoveItem.get(cnt), i, j);
                    LabelNadiasInfo.get(cnt).setAlignment(Pos.TOP_RIGHT);
                    ButtonListViewMore.get(cnt).setAlignment(Pos.BOTTOM_RIGHT);
                    gPane.setValignment(LabelNadiasInfo.get(cnt), VPos.TOP);
                    gPane.setValignment(ButtonListAddtoCart.get(cnt), VPos.BOTTOM);
                    gPane.setHalignment(ButtonListRemoveItem.get(cnt), HPos.RIGHT);
                    gPane.setHalignment(ButtonListViewMore.get(cnt), HPos.LEFT);
                    gPane.setVgap(5);
                    gPane.setHgap(5);
                    cnt++;

                } else if (cnt > (row - 1)) {
                    String text = "Product not available";
                    lbNadiasKitchen = new Label(text);
                    lbNadiasKitchen.setFont(new Font("Arial Italics", 13));
                    lbNadiasKitchen.setMinWidth(50);
                    lbNadiasKitchen.setMaxHeight(50);
                    gPane.add(lbNadiasKitchen, i, j);
                    lbNadiasKitchen.setAlignment(Pos.TOP_RIGHT);
                    gPane.setValignment(lbNadiasKitchen, VPos.TOP);
                    gPane.setVgap(5);
                    gPane.setHgap(5);


                }
            }
        }
        EventHandler<ActionEvent> event50 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");

                String text = LabelNadiasInfo.get(0).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(NadiasKitchenOuter.get(0).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event60 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event60);
            }
        };
        ButtonListAddtoCart.get(0).setOnAction(event50);


        EventHandler<ActionEvent> event51 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelNadiasInfo.get(1).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(NadiasKitchenOuter.get(1).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event61 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event61);
            }
        };
        ButtonListAddtoCart.get(1).setOnAction(event51);

        EventHandler<ActionEvent> event52 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelNadiasInfo.get(2).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(NadiasKitchenOuter.get(2).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event62 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event62);
            }
        };
        ButtonListAddtoCart.get(2).setOnAction(event52);

        EventHandler<ActionEvent> event53 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelNadiasInfo.get(3).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(NadiasKitchenOuter.get(3).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event63 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event63);
            }
        };
        ButtonListAddtoCart.get(3).setOnAction(event53);

        EventHandler<ActionEvent> event54 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelNadiasInfo.get(4).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(NadiasKitchenOuter.get(4).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event64 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event64);
            }
        };
        ButtonListAddtoCart.get(4).setOnAction(event54);

        EventHandler<ActionEvent> event55 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelNadiasInfo.get(5).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(NadiasKitchenOuter.get(5).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event65 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event65);
            }
        };
        ButtonListAddtoCart.get(5).setOnAction(event55);

        EventHandler<ActionEvent> event56 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelNadiasInfo.get(6).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(NadiasKitchenOuter.get(6).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event66 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event66);
            }
        };
        ButtonListAddtoCart.get(6).setOnAction(event56);

        EventHandler<ActionEvent> event57 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelNadiasInfo.get(7).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(NadiasKitchenOuter.get(7).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event67 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();
                    }
                };
                btnClearCart.setOnAction(event67);
            }
        };
        ButtonListAddtoCart.get(7).setOnAction(event57);

        EventHandler<ActionEvent> event58 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelNadiasInfo.get(8).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(NadiasKitchenOuter.get(8).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event68 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event68);
            }
        };
        ButtonListAddtoCart.get(8).setOnAction(event58);

        EventHandler<ActionEvent> event59 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelNadiasInfo.get(9).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(NadiasKitchenOuter.get(9).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event69 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event69);
            }
        };
        ButtonListAddtoCart.get(9).setOnAction(event59);


        EventHandler<ActionEvent> event500 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelNadiasInfo.get(10).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(NadiasKitchenOuter.get(10).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event600 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event600);
            }
        };
        ButtonListAddtoCart.get(10).setOnAction(event500);


        EventHandler<ActionEvent> event501 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelNadiasInfo.get(11).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(NadiasKitchenOuter.get(11).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event601 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event601);
            }
        };
        ButtonListAddtoCart.get(11).setOnAction(event501);

        EventHandler<ActionEvent> event502 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelNadiasInfo.get(12).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(NadiasKitchenOuter.get(12).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event602 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event602);
            }
        };
        ButtonListAddtoCart.get(12).setOnAction(event502);

        EventHandler<ActionEvent> event503 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelNadiasInfo.get(13).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(NadiasKitchenOuter.get(13).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event603 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event603);
            }
        };
        ButtonListAddtoCart.get(13).setOnAction(event503);

        EventHandler<ActionEvent> event504 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelNadiasInfo.get(14).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(NadiasKitchenOuter.get(14).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event604 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event604);
            }
        };
        ButtonListAddtoCart.get(14).setOnAction(event504);

        EventHandler<ActionEvent> event505 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelNadiasInfo.get(15).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(NadiasKitchenOuter.get(15).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event605 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event605);
            }
        };
        ButtonListAddtoCart.get(15).setOnAction(event505);

        EventHandler<ActionEvent> event506 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelNadiasInfo.get(16).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(NadiasKitchenOuter.get(16).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event606 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event606);
            }
        };
        ButtonListAddtoCart.get(16).setOnAction(event506);

        EventHandler<ActionEvent> event507 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelNadiasInfo.get(17).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(NadiasKitchenOuter.get(17).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event607 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event607);
            }
        };
        ButtonListAddtoCart.get(17).setOnAction(event507);

        EventHandler<ActionEvent> event508 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelNadiasInfo.get(18).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(NadiasKitchenOuter.get(18).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event608 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event608);
            }
        };
        ButtonListAddtoCart.get(18).setOnAction(event508);

        EventHandler<ActionEvent> event509 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelNadiasInfo.get(19).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(NadiasKitchenOuter.get(19).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event609 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();
                    }
                };
                btnClearCart.setOnAction(event609);
            }
        };
        ButtonListAddtoCart.get(19).setOnAction(event509);

        ButtonListViewMore.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = NadiasKitchenOuter.get(0).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (NadiasKitchenOuter.get(0).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + NadiasKitchenOuter.get(0).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + NadiasKitchenOuter.get(0).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + NadiasKitchenOuter.get(0).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = NadiasKitchenOuter.get(0).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = NadiasKitchenOuter.get(1).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (NadiasKitchenOuter.get(1).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + NadiasKitchenOuter.get(1).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + NadiasKitchenOuter.get(1).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + NadiasKitchenOuter.get(1).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = NadiasKitchenOuter.get(1).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = NadiasKitchenOuter.get(2).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (NadiasKitchenOuter.get(2).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + NadiasKitchenOuter.get(2).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + NadiasKitchenOuter.get(2).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + NadiasKitchenOuter.get(2).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = NadiasKitchenOuter.get(2).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(3).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = NadiasKitchenOuter.get(3).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (NadiasKitchenOuter.get(3).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + NadiasKitchenOuter.get(3).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + NadiasKitchenOuter.get(3).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + NadiasKitchenOuter.get(3).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = NadiasKitchenOuter.get(3).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(4).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = NadiasKitchenOuter.get(4).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (NadiasKitchenOuter.get(4).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + NadiasKitchenOuter.get(4).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + NadiasKitchenOuter.get(4).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + NadiasKitchenOuter.get(4).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = NadiasKitchenOuter.get(4).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(5).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = NadiasKitchenOuter.get(5).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (NadiasKitchenOuter.get(5).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + NadiasKitchenOuter.get(5).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + NadiasKitchenOuter.get(5).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + NadiasKitchenOuter.get(5).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = NadiasKitchenOuter.get(5).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(6).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = NadiasKitchenOuter.get(6).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (NadiasKitchenOuter.get(6).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + NadiasKitchenOuter.get(6).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + NadiasKitchenOuter.get(6).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + NadiasKitchenOuter.get(6).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = NadiasKitchenOuter.get(6).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(7).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = NadiasKitchenOuter.get(7).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (NadiasKitchenOuter.get(7).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + NadiasKitchenOuter.get(7).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + NadiasKitchenOuter.get(7).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + NadiasKitchenOuter.get(7).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = NadiasKitchenOuter.get(7).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(8).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = NadiasKitchenOuter.get(8).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (NadiasKitchenOuter.get(8).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + NadiasKitchenOuter.get(8).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + NadiasKitchenOuter.get(8).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + NadiasKitchenOuter.get(8).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = NadiasKitchenOuter.get(8).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(9).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = NadiasKitchenOuter.get(9).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (NadiasKitchenOuter.get(9).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + NadiasKitchenOuter.get(9).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + NadiasKitchenOuter.get(9).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + NadiasKitchenOuter.get(9).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = NadiasKitchenOuter.get(9).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(10).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = NadiasKitchenOuter.get(10).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (NadiasKitchenOuter.get(10).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + NadiasKitchenOuter.get(10).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + NadiasKitchenOuter.get(10).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + NadiasKitchenOuter.get(10).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = NadiasKitchenOuter.get(10).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(11).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = NadiasKitchenOuter.get(11).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (NadiasKitchenOuter.get(11).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + NadiasKitchenOuter.get(11).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + NadiasKitchenOuter.get(11).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + NadiasKitchenOuter.get(11).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = NadiasKitchenOuter.get(11).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(12).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = NadiasKitchenOuter.get(12).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (NadiasKitchenOuter.get(12).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + NadiasKitchenOuter.get(12).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + NadiasKitchenOuter.get(12).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + NadiasKitchenOuter.get(12).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = NadiasKitchenOuter.get(12).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(13).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = NadiasKitchenOuter.get(13).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (NadiasKitchenOuter.get(13).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + NadiasKitchenOuter.get(13).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + NadiasKitchenOuter.get(13).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + NadiasKitchenOuter.get(13).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = NadiasKitchenOuter.get(13).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(14).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = NadiasKitchenOuter.get(14).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (NadiasKitchenOuter.get(14).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + NadiasKitchenOuter.get(14).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + NadiasKitchenOuter.get(14).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + NadiasKitchenOuter.get(14).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = NadiasKitchenOuter.get(14).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(15).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = NadiasKitchenOuter.get(15).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (NadiasKitchenOuter.get(15).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + NadiasKitchenOuter.get(15).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + NadiasKitchenOuter.get(15).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + NadiasKitchenOuter.get(15).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = NadiasKitchenOuter.get(15).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(16).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = NadiasKitchenOuter.get(16).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (NadiasKitchenOuter.get(16).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + NadiasKitchenOuter.get(16).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + NadiasKitchenOuter.get(16).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + NadiasKitchenOuter.get(16).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = NadiasKitchenOuter.get(16).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(17).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = NadiasKitchenOuter.get(17).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (NadiasKitchenOuter.get(17).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + NadiasKitchenOuter.get(17).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + NadiasKitchenOuter.get(17).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + NadiasKitchenOuter.get(17).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = NadiasKitchenOuter.get(17).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(18).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = NadiasKitchenOuter.get(18).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (NadiasKitchenOuter.get(18).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + NadiasKitchenOuter.get(18).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + NadiasKitchenOuter.get(18).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + NadiasKitchenOuter.get(18).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = NadiasKitchenOuter.get(18).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(19).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = NadiasKitchenOuter.get(19).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (NadiasKitchenOuter.get(19).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + NadiasKitchenOuter.get(19).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + NadiasKitchenOuter.get(19).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + NadiasKitchenOuter.get(19).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = NadiasKitchenOuter.get(19).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });

        ButtonListRemoveItem.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelNadiasInfo.get(0).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(NadiasKitchenOuter.get(0).get(2)));
            }
        });
        ButtonListRemoveItem.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelNadiasInfo.get(1).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(NadiasKitchenOuter.get(1).get(2)));
            }
        });
        ButtonListRemoveItem.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelNadiasInfo.get(2).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(NadiasKitchenOuter.get(2).get(2)));
            }
        });
        ButtonListRemoveItem.get(3).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelNadiasInfo.get(3).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(NadiasKitchenOuter.get(3).get(2)));
            }
        });
        ButtonListRemoveItem.get(4).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelNadiasInfo.get(4).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(NadiasKitchenOuter.get(4).get(2)));
            }
        });
        ButtonListRemoveItem.get(5).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelNadiasInfo.get(5).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(NadiasKitchenOuter.get(5).get(2)));
            }
        });
        ButtonListRemoveItem.get(6).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelNadiasInfo.get(6).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(NadiasKitchenOuter.get(6).get(2)));
            }
        });
        ButtonListRemoveItem.get(7).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelNadiasInfo.get(7).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(NadiasKitchenOuter.get(7).get(2)));
            }
        });
        ButtonListRemoveItem.get(8).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelNadiasInfo.get(8).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(NadiasKitchenOuter.get(8).get(2)));
            }
        });
        ButtonListRemoveItem.get(9).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelNadiasInfo.get(9).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(NadiasKitchenOuter.get(9).get(2)));
            }
        });
        ButtonListRemoveItem.get(10).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelNadiasInfo.get(10).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(NadiasKitchenOuter.get(10).get(2)));
            }
        });
        ButtonListRemoveItem.get(11).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelNadiasInfo.get(11).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(NadiasKitchenOuter.get(11).get(2)));
            }
        });
        ButtonListRemoveItem.get(12).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelNadiasInfo.get(12).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(NadiasKitchenOuter.get(12).get(2)));
            }
        });
        ButtonListRemoveItem.get(13).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelNadiasInfo.get(13).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(NadiasKitchenOuter.get(13).get(2)));
            }
        });
        ButtonListRemoveItem.get(14).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelNadiasInfo.get(14).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(NadiasKitchenOuter.get(14).get(2)));
            }
        });
        ButtonListRemoveItem.get(15).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelNadiasInfo.get(15).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(NadiasKitchenOuter.get(15).get(2)));
            }
        });
        ButtonListRemoveItem.get(16).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelNadiasInfo.get(16).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(NadiasKitchenOuter.get(16).get(2)));
            }
        });
        ButtonListRemoveItem.get(17).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelNadiasInfo.get(17).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(NadiasKitchenOuter.get(17).get(2)));
            }
        });
        ButtonListRemoveItem.get(18).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelNadiasInfo.get(18).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(NadiasKitchenOuter.get(18).get(2)));
            }
        });
        ButtonListRemoveItem.get(19).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelNadiasInfo.get(19).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(NadiasKitchenOuter.get(19).get(2)));
            }
        });


    }

    /**
     * HyperLinkView7() method
     * this methods displays all the products that are present in the database under the store name of Choas Computers onto the grid view
     * all the button componets seen in each grid frame are initilized in this method
     **/

    public void HyperLink7(ActionEvent event) {
        ArrayList<Label> LabelChoasInfo = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            Label l = new Label();
            LabelChoasInfo.add(l);
        }
        ButtonList();
        lbProductDsiplay.setText("Choas Computers");
        gPane.getChildren().retainAll(gPane.getChildren().get(0));
        String cat = "Choas Computers";
        try {
            String q2 = "SELECT COUNT(*) AS rowcount FROM productinfo WHERE product_store = '" + cat + "'";
            Statement s = connectDB.createStatement();
            ResultSet r = s.executeQuery(q2);
            r.next();
            row = r.getInt("rowcount");
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        Label lbChoasComputers;

        try {
            String query1 = "SELECT prduct_name,  product_store, product_price, product_category,   product_linkimage, product_owner, owner_details, product_location, product_quantity FROM productinfo WHERE product_store = '" + cat + "'";
            Statement st = connectDB.createStatement();
            ResultSet rs = st.executeQuery(query1);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNo = rsmd.getColumnCount();

            while (rs.next()) {
                ChoasComputersInner = new ArrayList<String>();
                for (int i = 1; i <= columnNo; i++) {
                    ChoasComputersInner.add(rs.getString(i));
                }
                ChoasComputersOuter.add(ChoasComputersInner);
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


        int cnt = 0;


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {

                if (cnt <= (row - 1)) {

                    String text = "Product Name: " + ChoasComputersOuter.get(cnt).get(0) + "\nPrice: R" + ChoasComputersOuter.get(cnt).get(2) + "\nStore: " + ChoasComputersOuter.get(cnt).get(1);
                    LabelChoasInfo.get(cnt).setText(text);

                    LabelChoasInfo.get(cnt).setFont(new Font("Arial Bold", 15));
                    LabelChoasInfo.get(cnt).setMinWidth(50);
                    LabelChoasInfo.get(cnt).setMaxHeight(50);
                    gPane.add(LabelChoasInfo.get(cnt), i, j);
                    gPane.add(ButtonListAddtoCart.get(cnt), i, j);
                    gPane.add(ButtonListViewMore.get(cnt), i, j);
                    gPane.add(ButtonListRemoveItem.get(cnt), i, j);
                    LabelChoasInfo.get(cnt).setAlignment(Pos.TOP_RIGHT);
                    ButtonListViewMore.get(cnt).setAlignment(Pos.BOTTOM_RIGHT);
                    gPane.setValignment(LabelChoasInfo.get(cnt), VPos.TOP);
                    gPane.setHalignment(ButtonListRemoveItem.get(cnt), HPos.RIGHT);
                    gPane.setValignment(ButtonListAddtoCart.get(cnt), VPos.BOTTOM);
                    gPane.setHalignment(ButtonListViewMore.get(cnt), HPos.LEFT);
                    gPane.setVgap(5);
                    gPane.setHgap(5);
                    cnt++;

                } else if (cnt > (row - 1)) {
                    String text = "Product not available";
                    lbChoasComputers = new Label(text);
                    lbChoasComputers.setFont(new Font("Arial Italics", 13));
                    lbChoasComputers.setMinWidth(50);
                    lbChoasComputers.setMaxHeight(50);
                    gPane.add(lbChoasComputers, i, j);
                    lbChoasComputers.setAlignment(Pos.TOP_RIGHT);
                    gPane.setValignment(lbChoasComputers, VPos.TOP);
                    gPane.setVgap(5);
                    gPane.setHgap(5);


                }
            }
        }

        EventHandler<ActionEvent> event30 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");

                String text = LabelChoasInfo.get(0).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ChoasComputersOuter.get(0).get(2));
                cost.add(number);

                items++;

                EventHandler<ActionEvent> event40 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event40);
            }
        };
        ButtonListAddtoCart.get(0).setOnAction(event30);


        EventHandler<ActionEvent> event31 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelChoasInfo.get(1).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ChoasComputersOuter.get(1).get(2));
                cost.add(number);

                items++;

                EventHandler<ActionEvent> event41 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event41);
            }
        };
        ButtonListAddtoCart.get(1).setOnAction(event31);

        EventHandler<ActionEvent> event32 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelChoasInfo.get(2).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ChoasComputersOuter.get(2).get(2));
                cost.add(number);

                items++;

                EventHandler<ActionEvent> event42 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();
                    }
                };
                btnClearCart.setOnAction(event42);
            }
        };
        ButtonListAddtoCart.get(2).setOnAction(event32);

        EventHandler<ActionEvent> event33 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelChoasInfo.get(3).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ChoasComputersOuter.get(3).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event43 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event43);
            }
        };
        ButtonListAddtoCart.get(3).setOnAction(event33);

        EventHandler<ActionEvent> event34 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelChoasInfo.get(4).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ChoasComputersOuter.get(4).get(2));
                cost.add(number);

                items++;

                EventHandler<ActionEvent> event44 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event44);
            }
        };
        ButtonListAddtoCart.get(4).setOnAction(event34);

        EventHandler<ActionEvent> event35 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelChoasInfo.get(5).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ChoasComputersOuter.get(5).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event45 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event45);
            }
        };
        ButtonListAddtoCart.get(5).setOnAction(event35);

        EventHandler<ActionEvent> event36 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelChoasInfo.get(6).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ChoasComputersOuter.get(6).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event46 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event46);
            }
        };
        ButtonListAddtoCart.get(6).setOnAction(event36);

        EventHandler<ActionEvent> event37 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelChoasInfo.get(7).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ChoasComputersOuter.get(7).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event47 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event47);
            }
        };
        ButtonListAddtoCart.get(7).setOnAction(event37);

        EventHandler<ActionEvent> event38 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelChoasInfo.get(8).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ChoasComputersOuter.get(8).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event48 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event48);
            }
        };
        ButtonListAddtoCart.get(8).setOnAction(event38);

        EventHandler<ActionEvent> event39 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelChoasInfo.get(9).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ChoasComputersOuter.get(9).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event49 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event49);
            }
        };
        ButtonListAddtoCart.get(9).setOnAction(event39);


        EventHandler<ActionEvent> event300 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");

                String text = LabelChoasInfo.get(10).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ChoasComputersOuter.get(10).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event400 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event400);
            }
        };
        ButtonListAddtoCart.get(10).setOnAction(event300);


        EventHandler<ActionEvent> event301 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelChoasInfo.get(11).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ChoasComputersOuter.get(11).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event401 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event401);
            }
        };
        ButtonListAddtoCart.get(11).setOnAction(event301);

        EventHandler<ActionEvent> event302 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelChoasInfo.get(12).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ChoasComputersOuter.get(12).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event402 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event402);
            }
        };
        ButtonListAddtoCart.get(12).setOnAction(event302);

        EventHandler<ActionEvent> event303 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");

                String text = LabelChoasInfo.get(13).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ChoasComputersOuter.get(13).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event403 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event403);
            }
        };
        ButtonListAddtoCart.get(13).setOnAction(event303);

        EventHandler<ActionEvent> event304 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelChoasInfo.get(14).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ChoasComputersOuter.get(14).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event404 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event404);
            }
        };
        ButtonListAddtoCart.get(14).setOnAction(event304);

        EventHandler<ActionEvent> event305 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelChoasInfo.get(15).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ChoasComputersOuter.get(15).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event405 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event405);
            }
        };
        ButtonListAddtoCart.get(15).setOnAction(event305);

        EventHandler<ActionEvent> event306 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelChoasInfo.get(16).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ChoasComputersOuter.get(16).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event406 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event406);
            }
        };
        ButtonListAddtoCart.get(16).setOnAction(event306);

        EventHandler<ActionEvent> event307 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelChoasInfo.get(17).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ChoasComputersOuter.get(17).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event407 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event407);
            }
        };
        ButtonListAddtoCart.get(17).setOnAction(event307);

        EventHandler<ActionEvent> event308 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelChoasInfo.get(18).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ChoasComputersOuter.get(18).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event408 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event408);
            }
        };
        ButtonListAddtoCart.get(18).setOnAction(event308);

        EventHandler<ActionEvent> event309 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelChoasInfo.get(19).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(ChoasComputersOuter.get(19).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event409 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event409);
            }
        };
        ButtonListAddtoCart.get(19).setOnAction(event309);

        ButtonListViewMore.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ChoasComputersOuter.get(0).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ChoasComputersOuter.get(0).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ChoasComputersOuter.get(0).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ChoasComputersOuter.get(0).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ChoasComputersOuter.get(0).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ChoasComputersOuter.get(0).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ChoasComputersOuter.get(1).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ChoasComputersOuter.get(1).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ChoasComputersOuter.get(1).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ChoasComputersOuter.get(1).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ChoasComputersOuter.get(1).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ChoasComputersOuter.get(1).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ChoasComputersOuter.get(2).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ChoasComputersOuter.get(2).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ChoasComputersOuter.get(2).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ChoasComputersOuter.get(2).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ChoasComputersOuter.get(2).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ChoasComputersOuter.get(2).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(3).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ChoasComputersOuter.get(3).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ChoasComputersOuter.get(3).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ChoasComputersOuter.get(3).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ChoasComputersOuter.get(3).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ChoasComputersOuter.get(3).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ChoasComputersOuter.get(3).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(4).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ChoasComputersOuter.get(4).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ChoasComputersOuter.get(4).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ChoasComputersOuter.get(4).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ChoasComputersOuter.get(4).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ChoasComputersOuter.get(4).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ChoasComputersOuter.get(4).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(5).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ChoasComputersOuter.get(5).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ChoasComputersOuter.get(5).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ChoasComputersOuter.get(5).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ChoasComputersOuter.get(5).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ChoasComputersOuter.get(5).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ChoasComputersOuter.get(5).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(6).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ChoasComputersOuter.get(6).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ChoasComputersOuter.get(6).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ChoasComputersOuter.get(6).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ChoasComputersOuter.get(6).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ChoasComputersOuter.get(6).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ChoasComputersOuter.get(6).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(7).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ChoasComputersOuter.get(7).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ChoasComputersOuter.get(7).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ChoasComputersOuter.get(7).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ChoasComputersOuter.get(7).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ChoasComputersOuter.get(7).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ChoasComputersOuter.get(7).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(8).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ChoasComputersOuter.get(8).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ChoasComputersOuter.get(8).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ChoasComputersOuter.get(8).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ChoasComputersOuter.get(8).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ChoasComputersOuter.get(8).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ChoasComputersOuter.get(8).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(9).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ChoasComputersOuter.get(9).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ChoasComputersOuter.get(9).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ChoasComputersOuter.get(9).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ChoasComputersOuter.get(9).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ChoasComputersOuter.get(9).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ChoasComputersOuter.get(9).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(10).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ChoasComputersOuter.get(10).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ChoasComputersOuter.get(10).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ChoasComputersOuter.get(10).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ChoasComputersOuter.get(10).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ChoasComputersOuter.get(10).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ChoasComputersOuter.get(10).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(11).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ChoasComputersOuter.get(11).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ChoasComputersOuter.get(11).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ChoasComputersOuter.get(11).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ChoasComputersOuter.get(11).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ChoasComputersOuter.get(11).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ChoasComputersOuter.get(11).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(12).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ChoasComputersOuter.get(12).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ChoasComputersOuter.get(12).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ChoasComputersOuter.get(12).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ChoasComputersOuter.get(12).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ChoasComputersOuter.get(12).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ChoasComputersOuter.get(12).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(13).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ChoasComputersOuter.get(13).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ChoasComputersOuter.get(13).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ChoasComputersOuter.get(13).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ChoasComputersOuter.get(13).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ChoasComputersOuter.get(13).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ChoasComputersOuter.get(13).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(14).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ChoasComputersOuter.get(14).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ChoasComputersOuter.get(14).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ChoasComputersOuter.get(14).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ChoasComputersOuter.get(14).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ChoasComputersOuter.get(14).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ChoasComputersOuter.get(14).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(15).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ChoasComputersOuter.get(15).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ChoasComputersOuter.get(15).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ChoasComputersOuter.get(15).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ChoasComputersOuter.get(15).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ChoasComputersOuter.get(15).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ChoasComputersOuter.get(15).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(16).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ChoasComputersOuter.get(16).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ChoasComputersOuter.get(16).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ChoasComputersOuter.get(16).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ChoasComputersOuter.get(16).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ChoasComputersOuter.get(16).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ChoasComputersOuter.get(16).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(17).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ChoasComputersOuter.get(17).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ChoasComputersOuter.get(17).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ChoasComputersOuter.get(17).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ChoasComputersOuter.get(17).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ChoasComputersOuter.get(17).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ChoasComputersOuter.get(17).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(18).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ChoasComputersOuter.get(18).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ChoasComputersOuter.get(18).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ChoasComputersOuter.get(18).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ChoasComputersOuter.get(18).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ChoasComputersOuter.get(18).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ChoasComputersOuter.get(18).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(19).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = ChoasComputersOuter.get(19).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (ChoasComputersOuter.get(19).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + ChoasComputersOuter.get(19).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + ChoasComputersOuter.get(19).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + ChoasComputersOuter.get(19).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = ChoasComputersOuter.get(19).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });

        ButtonListRemoveItem.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelChoasInfo.get(0).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ChoasComputersOuter.get(0).get(2)));
            }
        });
        ButtonListRemoveItem.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelChoasInfo.get(1).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ChoasComputersOuter.get(1).get(2)));
            }
        });
        ButtonListRemoveItem.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelChoasInfo.get(2).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ChoasComputersOuter.get(2).get(2)));
            }
        });
        ButtonListRemoveItem.get(3).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelChoasInfo.get(3).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ChoasComputersOuter.get(3).get(2)));
            }
        });
        ButtonListRemoveItem.get(4).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelChoasInfo.get(4).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ChoasComputersOuter.get(4).get(2)));
            }
        });
        ButtonListRemoveItem.get(5).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelChoasInfo.get(5).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ChoasComputersOuter.get(5).get(2)));
            }
        });
        ButtonListRemoveItem.get(6).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelChoasInfo.get(6).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ChoasComputersOuter.get(6).get(2)));
            }
        });
        ButtonListRemoveItem.get(7).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelChoasInfo.get(7).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ChoasComputersOuter.get(7).get(2)));
            }
        });
        ButtonListRemoveItem.get(8).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelChoasInfo.get(8).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ChoasComputersOuter.get(8).get(2)));
            }
        });
        ButtonListRemoveItem.get(9).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelChoasInfo.get(9).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ChoasComputersOuter.get(9).get(2)));
            }
        });
        ButtonListRemoveItem.get(10).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelChoasInfo.get(10).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ChoasComputersOuter.get(10).get(2)));
            }
        });
        ButtonListRemoveItem.get(11).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelChoasInfo.get(11).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ChoasComputersOuter.get(11).get(2)));
            }
        });
        ButtonListRemoveItem.get(12).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelChoasInfo.get(12).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ChoasComputersOuter.get(12).get(2)));
            }
        });
        ButtonListRemoveItem.get(13).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelChoasInfo.get(13).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ChoasComputersOuter.get(13).get(2)));
            }
        });
        ButtonListRemoveItem.get(14).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelChoasInfo.get(14).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ChoasComputersOuter.get(14).get(2)));
            }
        });
        ButtonListRemoveItem.get(15).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelChoasInfo.get(15).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ChoasComputersOuter.get(15).get(2)));
            }
        });
        ButtonListRemoveItem.get(16).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelChoasInfo.get(16).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ChoasComputersOuter.get(16).get(2)));
            }
        });
        ButtonListRemoveItem.get(17).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelChoasInfo.get(17).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ChoasComputersOuter.get(17).get(2)));
            }
        });
        ButtonListRemoveItem.get(18).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelChoasInfo.get(18).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ChoasComputersOuter.get(18).get(2)));
            }
        });
        ButtonListRemoveItem.get(19).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelChoasInfo.get(19).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(ChoasComputersOuter.get(19).get(2)));
            }
        });


    }

    /**
     * linkbtn() method
     * this methods displays all the products that are present in the database under newly created store names onto the grid view
     * all the button componets seen in each grid frame are initilized in this method
     **/

    public void linkbtn(){
        try {
            GettingdbStuff();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (int i = 0; i < GettingDBStuff.size(); i++){
            Hyperlink l = new Hyperlink(GettingDBStuff.get(i));
            String name21 = GettingDBStuff.get(i);
            HyperLinkList.add(l);
            boxV.getChildren().add(l);

            HyperLinkList.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ArrayList<ArrayList<String>> Outer = new ArrayList<ArrayList<String>>();
                    ArrayList<String> Inner;
                    ButtonList();
                    ArrayList<Label> Linfo = new ArrayList<>();
                    for (int i = 0; i < 21; i++) {
                        Label l = new Label();
                        Linfo.add(l);
                    }
                    lbProductDsiplay.setText(name21);
                    gPane.getChildren().retainAll(gPane.getChildren().get(0));
                    String cat = name21;

                    try {
                        String q2 = "SELECT COUNT(*) AS rowcount FROM productinfo WHERE product_store = '" + cat + "'";
                        Statement s = connectDB.createStatement();
                        ResultSet r = s.executeQuery(q2);
                        r.next();
                        row = r.getInt("rowcount");
                    } catch (Exception e) {
                        e.printStackTrace();
                        e.getCause();
                    }
                    Label lbNoProduct;
                    try {
                        String query1 = "SELECT prduct_name,  product_store, product_price, product_category,   product_linkimage, product_owner, owner_details, product_location, product_quantity FROM productinfo WHERE product_store = '" + cat + "'";
                        Statement st = connectDB.createStatement();
                        ResultSet rs = st.executeQuery(query1);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int columnNo = rsmd.getColumnCount();

                        while (rs.next()) {
                            Inner = new ArrayList<String>();
                            for (int i = 1; i <= columnNo; i++) {
                                Inner.add(rs.getString(i));
                            }
                            Outer.add(Inner);
                        }

                        rs.close();
                        st.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                        e.getCause();
                    }
                    int cnt = 0;
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 5; j++) {

                            if (cnt <= (row - 1)) {

                                String text = "Product Name: " + Outer.get(cnt).get(0) + "\nPrice: R" + Outer.get(cnt).get(2) + "\nStore: " + Outer.get(cnt).get(1);
                                Linfo.get(cnt).setText(text);

                                Linfo.get(cnt).setFont(new Font("Arial Bold", 15));
                                Linfo.get(cnt).setMinWidth(50);
                                Linfo.get(cnt).setMaxHeight(50);
                                gPane.add(Linfo.get(cnt), i, j);
                                gPane.add(ButtonListAddtoCart.get(cnt), i, j);
                                gPane.add(ButtonListViewMore.get(cnt), i, j);
                                gPane.add(ButtonListRemoveItem.get(cnt), i, j);
                                Linfo.get(cnt).setAlignment(Pos.TOP_RIGHT);
                                ButtonListViewMore.get(cnt).setAlignment(Pos.BOTTOM_RIGHT);
                                gPane.setValignment(Linfo.get(cnt), VPos.TOP);
                                gPane.setHalignment(ButtonListRemoveItem.get(cnt), HPos.RIGHT);
                                gPane.setValignment(ButtonListAddtoCart.get(cnt), VPos.BOTTOM);
                                gPane.setHalignment(ButtonListViewMore.get(cnt), HPos.LEFT);
                                gPane.setVgap(5);
                                gPane.setHgap(5);
                                cnt++;

                            } else if (cnt > (row - 1)) {
                                String text = "Product not available";
                                lbNoProduct = new Label(text);
                                lbNoProduct.setFont(new Font("Arial Italics", 13));
                                lbNoProduct.setMinWidth(50);
                                lbNoProduct.setMaxHeight(50);
                                gPane.add(lbNoProduct, i, j);
                                lbNoProduct.setAlignment(Pos.TOP_RIGHT);
                                gPane.setValignment(lbNoProduct, VPos.TOP);
                                gPane.setVgap(5);
                                gPane.setHgap(5);


                            }
                        }
                    }


                    EventHandler<ActionEvent> event30 = new EventHandler<ActionEvent>() {


                        int items = 1;

                        @Override
                        public void handle(ActionEvent event) {
                            names.remove("empty");

                            String text = Linfo.get(0).getText();
                            names.add(text);
                            ComboBox.setItems(names);
                            txtCartCounter.setText("No. of items: " + names.size());
                            int number = Integer.parseInt(Outer.get(0).get(2));
                            cost.add(number);

                            items++;

                            EventHandler<ActionEvent> event40 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    items = 1;
                                    txtCartCounter.setText("No. of items: 0");
                                    names.clear();
                                    names.add("empty");
                                    ComboBox.setItems(names);
                                    cost.clear();

                                }
                            };
                            btnClearCart.setOnAction(event40);
                        }
                    };
                    ButtonListAddtoCart.get(0).setOnAction(event30);


                    EventHandler<ActionEvent> event31 = new EventHandler<ActionEvent>() {


                        int items = 1;

                        @Override
                        public void handle(ActionEvent event) {
                            names.remove("empty");
                            String text = Linfo.get(1).getText();
                            names.add(text);
                            ComboBox.setItems(names);
                            txtCartCounter.setText("No. of items: " + names.size());
                            int number = Integer.parseInt(Outer.get(1).get(2));
                            cost.add(number);

                            items++;

                            EventHandler<ActionEvent> event41 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    items = 1;
                                    txtCartCounter.setText("No. of items: 0");
                                    names.clear();
                                    names.add("empty");
                                    ComboBox.setItems(names);
                                    cost.clear();

                                }
                            };
                            btnClearCart.setOnAction(event41);
                        }
                    };
                    ButtonListAddtoCart.get(1).setOnAction(event31);

                    EventHandler<ActionEvent> event32 = new EventHandler<ActionEvent>() {


                        int items = 1;

                        @Override
                        public void handle(ActionEvent event) {
                            names.remove("empty");
                            String text = Linfo.get(2).getText();
                            names.add(text);
                            ComboBox.setItems(names);
                            txtCartCounter.setText("No. of items: " + names.size());
                            int number = Integer.parseInt(Outer.get(2).get(2));
                            cost.add(number);

                            items++;

                            EventHandler<ActionEvent> event42 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    items = 1;
                                    txtCartCounter.setText("No. of items: 0");
                                    names.clear();
                                    names.add("empty");
                                    ComboBox.setItems(names);
                                    cost.clear();
                                }
                            };
                            btnClearCart.setOnAction(event42);
                        }
                    };
                    ButtonListAddtoCart.get(2).setOnAction(event32);

                    EventHandler<ActionEvent> event33 = new EventHandler<ActionEvent>() {


                        int items = 1;

                        @Override
                        public void handle(ActionEvent event) {
                            names.remove("empty");
                            String text = Linfo.get(3).getText();
                            names.add(text);
                            ComboBox.setItems(names);
                            txtCartCounter.setText("No. of items: " + names.size());
                            int number = Integer.parseInt(Outer.get(3).get(2));
                            cost.add(number);
                            items++;

                            EventHandler<ActionEvent> event43 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    items = 1;
                                    txtCartCounter.setText("No. of items: 0");
                                    names.clear();
                                    names.add("empty");
                                    ComboBox.setItems(names);
                                    cost.clear();

                                }
                            };
                            btnClearCart.setOnAction(event43);
                        }
                    };
                    ButtonListAddtoCart.get(3).setOnAction(event33);

                    EventHandler<ActionEvent> event34 = new EventHandler<ActionEvent>() {


                        int items = 1;

                        @Override
                        public void handle(ActionEvent event) {
                            names.remove("empty");
                            String text = Linfo.get(4).getText();
                            names.add(text);
                            ComboBox.setItems(names);
                            txtCartCounter.setText("No. of items: " + names.size());
                            int number = Integer.parseInt(Outer.get(4).get(2));
                            cost.add(number);

                            items++;

                            EventHandler<ActionEvent> event44 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    items = 1;
                                    txtCartCounter.setText("No. of items: 0");
                                    names.clear();
                                    names.add("empty");
                                    ComboBox.setItems(names);
                                    cost.clear();

                                }
                            };
                            btnClearCart.setOnAction(event44);
                        }
                    };
                    ButtonListAddtoCart.get(4).setOnAction(event34);

                    EventHandler<ActionEvent> event35 = new EventHandler<ActionEvent>() {


                        int items = 1;

                        @Override
                        public void handle(ActionEvent event) {
                            names.remove("empty");
                            String text = Linfo.get(5).getText();
                            names.add(text);
                            ComboBox.setItems(names);
                            txtCartCounter.setText("No. of items: " + names.size());
                            int number = Integer.parseInt(Outer.get(5).get(2));
                            cost.add(number);
                            items++;

                            EventHandler<ActionEvent> event45 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    items = 1;
                                    txtCartCounter.setText("No. of items: 0");
                                    names.clear();
                                    names.add("empty");
                                    ComboBox.setItems(names);
                                    cost.clear();

                                }
                            };
                            btnClearCart.setOnAction(event45);
                        }
                    };
                    ButtonListAddtoCart.get(5).setOnAction(event35);

                    EventHandler<ActionEvent> event36 = new EventHandler<ActionEvent>() {


                        int items = 1;

                        @Override
                        public void handle(ActionEvent event) {
                            names.remove("empty");
                            String text = Linfo.get(6).getText();
                            names.add(text);
                            ComboBox.setItems(names);
                            txtCartCounter.setText("No. of items: " + names.size());
                            int number = Integer.parseInt(Outer.get(6).get(2));
                            cost.add(number);
                            items++;

                            EventHandler<ActionEvent> event46 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    items = 1;
                                    txtCartCounter.setText("No. of items: 0");
                                    names.clear();
                                    names.add("empty");
                                    ComboBox.setItems(names);
                                    cost.clear();

                                }
                            };
                            btnClearCart.setOnAction(event46);
                        }
                    };
                    ButtonListAddtoCart.get(6).setOnAction(event36);

                    EventHandler<ActionEvent> event37 = new EventHandler<ActionEvent>() {


                        int items = 1;

                        @Override
                        public void handle(ActionEvent event) {
                            names.remove("empty");
                            String text = Linfo.get(7).getText();
                            names.add(text);
                            ComboBox.setItems(names);
                            txtCartCounter.setText("No. of items: " + names.size());
                            int number = Integer.parseInt(Outer.get(7).get(2));
                            cost.add(number);
                            items++;

                            EventHandler<ActionEvent> event47 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    items = 1;
                                    txtCartCounter.setText("No. of items: 0");
                                    names.clear();
                                    names.add("empty");
                                    ComboBox.setItems(names);
                                    cost.clear();

                                }
                            };
                            btnClearCart.setOnAction(event47);
                        }
                    };
                    ButtonListAddtoCart.get(7).setOnAction(event37);

                    EventHandler<ActionEvent> event38 = new EventHandler<ActionEvent>() {


                        int items = 1;

                        @Override
                        public void handle(ActionEvent event) {
                            names.remove("empty");
                            String text = Linfo.get(8).getText();
                            names.add(text);
                            ComboBox.setItems(names);
                            txtCartCounter.setText("No. of items: " + names.size());
                            int number = Integer.parseInt(Outer.get(8).get(2));
                            cost.add(number);
                            items++;

                            EventHandler<ActionEvent> event48 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    items = 1;
                                    txtCartCounter.setText("No. of items: 0");
                                    names.clear();
                                    names.add("empty");
                                    ComboBox.setItems(names);
                                    cost.clear();

                                }
                            };
                            btnClearCart.setOnAction(event48);
                        }
                    };
                    ButtonListAddtoCart.get(8).setOnAction(event38);

                    EventHandler<ActionEvent> event39 = new EventHandler<ActionEvent>() {


                        int items = 1;

                        @Override
                        public void handle(ActionEvent event) {
                            names.remove("empty");
                            String text = Linfo.get(9).getText();
                            names.add(text);
                            ComboBox.setItems(names);
                            txtCartCounter.setText("No. of items: " + names.size());
                            int number = Integer.parseInt(Outer.get(9).get(2));
                            cost.add(number);
                            items++;

                            EventHandler<ActionEvent> event49 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    items = 1;
                                    txtCartCounter.setText("No. of items: 0");
                                    names.clear();
                                    names.add("empty");
                                    ComboBox.setItems(names);
                                    cost.clear();

                                }
                            };
                            btnClearCart.setOnAction(event49);
                        }
                    };
                    ButtonListAddtoCart.get(9).setOnAction(event39);


                    EventHandler<ActionEvent> event300 = new EventHandler<ActionEvent>() {


                        int items = 1;

                        @Override
                        public void handle(ActionEvent event) {
                            names.remove("empty");

                            String text = Linfo.get(10).getText();
                            names.add(text);
                            ComboBox.setItems(names);
                            txtCartCounter.setText("No. of items: " + names.size());
                            int number = Integer.parseInt(Outer.get(10).get(2));
                            cost.add(number);
                            items++;

                            EventHandler<ActionEvent> event400 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    items = 1;
                                    txtCartCounter.setText("No. of items: 0");
                                    names.clear();
                                    names.add("empty");
                                    ComboBox.setItems(names);
                                    cost.clear();

                                }
                            };
                            btnClearCart.setOnAction(event400);
                        }
                    };
                    ButtonListAddtoCart.get(10).setOnAction(event300);


                    EventHandler<ActionEvent> event301 = new EventHandler<ActionEvent>() {


                        int items = 1;

                        @Override
                        public void handle(ActionEvent event) {
                            names.remove("empty");
                            String text = Linfo.get(11).getText();
                            names.add(text);
                            ComboBox.setItems(names);
                            txtCartCounter.setText("No. of items: " + names.size());
                            int number = Integer.parseInt(Outer.get(11).get(2));
                            cost.add(number);
                            items++;

                            EventHandler<ActionEvent> event401 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    items = 1;
                                    txtCartCounter.setText("No. of items: 0");
                                    names.clear();
                                    names.add("empty");
                                    ComboBox.setItems(names);
                                    cost.clear();

                                }
                            };
                            btnClearCart.setOnAction(event401);
                        }
                    };
                    ButtonListAddtoCart.get(11).setOnAction(event301);

                    EventHandler<ActionEvent> event302 = new EventHandler<ActionEvent>() {


                        int items = 1;

                        @Override
                        public void handle(ActionEvent event) {
                            names.remove("empty");
                            String text = Linfo.get(12).getText();
                            names.add(text);
                            ComboBox.setItems(names);
                            txtCartCounter.setText("No. of items: " + names.size());
                            int number = Integer.parseInt(Outer.get(12).get(2));
                            cost.add(number);
                            items++;

                            EventHandler<ActionEvent> event402 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    items = 1;
                                    txtCartCounter.setText("No. of items: 0");
                                    names.clear();
                                    names.add("empty");
                                    ComboBox.setItems(names);
                                    cost.clear();

                                }
                            };
                            btnClearCart.setOnAction(event402);
                        }
                    };
                    ButtonListAddtoCart.get(12).setOnAction(event302);

                    EventHandler<ActionEvent> event303 = new EventHandler<ActionEvent>() {


                        int items = 1;

                        @Override
                        public void handle(ActionEvent event) {
                            names.remove("empty");

                            String text = Linfo.get(13).getText();
                            names.add(text);
                            ComboBox.setItems(names);
                            txtCartCounter.setText("No. of items: " + names.size());
                            int number = Integer.parseInt(Outer.get(13).get(2));
                            cost.add(number);
                            items++;

                            EventHandler<ActionEvent> event403 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    items = 1;
                                    txtCartCounter.setText("No. of items: 0");
                                    names.clear();
                                    names.add("empty");
                                    ComboBox.setItems(names);
                                    cost.clear();

                                }
                            };
                            btnClearCart.setOnAction(event403);
                        }
                    };
                    ButtonListAddtoCart.get(13).setOnAction(event303);

                    EventHandler<ActionEvent> event304 = new EventHandler<ActionEvent>() {


                        int items = 1;

                        @Override
                        public void handle(ActionEvent event) {
                            names.remove("empty");
                            String text = Linfo.get(14).getText();
                            names.add(text);
                            ComboBox.setItems(names);
                            txtCartCounter.setText("No. of items: " + names.size());
                            int number = Integer.parseInt(Outer.get(14).get(2));
                            cost.add(number);
                            items++;

                            EventHandler<ActionEvent> event404 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    items = 1;
                                    txtCartCounter.setText("No. of items: 0");
                                    names.clear();
                                    names.add("empty");
                                    ComboBox.setItems(names);
                                    cost.clear();

                                }
                            };
                            btnClearCart.setOnAction(event404);
                        }
                    };
                    ButtonListAddtoCart.get(14).setOnAction(event304);

                    EventHandler<ActionEvent> event305 = new EventHandler<ActionEvent>() {


                        int items = 1;

                        @Override
                        public void handle(ActionEvent event) {
                            names.remove("empty");
                            String text = Linfo.get(15).getText();
                            names.add(text);
                            ComboBox.setItems(names);
                            txtCartCounter.setText("No. of items: " + names.size());
                            int number = Integer.parseInt(Outer.get(15).get(2));
                            cost.add(number);
                            items++;

                            EventHandler<ActionEvent> event405 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    items = 1;
                                    txtCartCounter.setText("No. of items: 0");
                                    names.clear();
                                    names.add("empty");
                                    ComboBox.setItems(names);
                                    cost.clear();

                                }
                            };
                            btnClearCart.setOnAction(event405);
                        }
                    };
                    ButtonListAddtoCart.get(15).setOnAction(event305);

                    EventHandler<ActionEvent> event306 = new EventHandler<ActionEvent>() {


                        int items = 1;

                        @Override
                        public void handle(ActionEvent event) {
                            names.remove("empty");
                            String text = Linfo.get(16).getText();
                            names.add(text);
                            ComboBox.setItems(names);
                            txtCartCounter.setText("No. of items: " + names.size());
                            int number = Integer.parseInt(Outer.get(16).get(2));
                            cost.add(number);
                            items++;

                            EventHandler<ActionEvent> event406 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    items = 1;
                                    txtCartCounter.setText("No. of items: 0");
                                    names.clear();
                                    names.add("empty");
                                    ComboBox.setItems(names);
                                    cost.clear();

                                }
                            };
                            btnClearCart.setOnAction(event406);
                        }
                    };
                    ButtonListAddtoCart.get(16).setOnAction(event306);

                    EventHandler<ActionEvent> event307 = new EventHandler<ActionEvent>() {


                        int items = 1;

                        @Override
                        public void handle(ActionEvent event) {
                            names.remove("empty");
                            String text = Linfo.get(17).getText();
                            names.add(text);
                            ComboBox.setItems(names);
                            txtCartCounter.setText("No. of items: " + names.size());
                            int number = Integer.parseInt(Outer.get(17).get(2));
                            cost.add(number);
                            items++;

                            EventHandler<ActionEvent> event407 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    items = 1;
                                    txtCartCounter.setText("No. of items: 0");
                                    names.clear();
                                    names.add("empty");
                                    ComboBox.setItems(names);
                                    cost.clear();

                                }
                            };
                            btnClearCart.setOnAction(event407);
                        }
                    };
                    ButtonListAddtoCart.get(17).setOnAction(event307);

                    EventHandler<ActionEvent> event308 = new EventHandler<ActionEvent>() {


                        int items = 1;

                        @Override
                        public void handle(ActionEvent event) {
                            names.remove("empty");
                            String text = Linfo.get(18).getText();
                            names.add(text);
                            ComboBox.setItems(names);
                            txtCartCounter.setText("No. of items: " + names.size());
                            int number = Integer.parseInt(Outer.get(18).get(2));
                            cost.add(number);
                            items++;

                            EventHandler<ActionEvent> event408 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    items = 1;
                                    txtCartCounter.setText("No. of items: 0");
                                    names.clear();
                                    names.add("empty");
                                    ComboBox.setItems(names);
                                    cost.clear();

                                }
                            };
                            btnClearCart.setOnAction(event408);
                        }
                    };
                    ButtonListAddtoCart.get(18).setOnAction(event308);

                    EventHandler<ActionEvent> event309 = new EventHandler<ActionEvent>() {


                        int items = 1;

                        @Override
                        public void handle(ActionEvent event) {
                            names.remove("empty");
                            String text = Linfo.get(19).getText();
                            names.add(text);
                            ComboBox.setItems(names);
                            txtCartCounter.setText("No. of items: " + names.size());
                            int number = Integer.parseInt(Outer.get(19).get(2));
                            cost.add(number);
                            items++;

                            EventHandler<ActionEvent> event409 = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    items = 1;
                                    txtCartCounter.setText("No. of items: 0");
                                    names.clear();
                                    names.add("empty");
                                    ComboBox.setItems(names);
                                    cost.clear();

                                }
                            };
                            btnClearCart.setOnAction(event409);
                        }
                    };
                    ButtonListAddtoCart.get(19).setOnAction(event309);


                    ButtonListViewMore.get(0).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage popup = new Stage();
                            VBox box = new VBox();
                            String url = Outer.get(0).get(4);
                            Label txtQauntity = new Label("Product Qauntity: " + (Outer.get(0).get(8)) + "\n");
                            Label txtOwner = new Label("Store owner name: " + Outer.get(0).get(5) + "\n");
                            Label txtContact = new Label("Contact Details: " + Outer.get(0).get(6) + "\n");
                            Label txtLocation = new Label("Store Location: " + Outer.get(0).get(7) + "\n");
                            Image image = new Image(url);
                            txtQauntity.setFont(new Font("Arial Bold", 20));
                            txtOwner.setFont(new Font("Arial Bold", 20));
                            txtContact.setFont(new Font("Arial Bold", 20));
                            txtLocation.setFont(new Font("Arial Bold", 20));
                            imageview = new ImageView(image);
                            imageview.setFitHeight(300);
                            imageview.setFitWidth(300);

                            box.getChildren().add(imageview);
                            box.getChildren().add(txtQauntity);
                            box.getChildren().add(txtOwner);
                            box.getChildren().add(txtContact);
                            box.getChildren().add(txtLocation);
                            Scene stageScene = new Scene(box, 450, 450);
                            String title = Outer.get(0).get(0);
                            popup.setTitle(title);
                            popup.setScene(stageScene);
                            popup.show();


                        }
                    });
                    ButtonListViewMore.get(1).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage popup = new Stage();
                            VBox box = new VBox();
                            String url = Outer.get(1).get(4);
                            Label txtQauntity = new Label("Product Qauntity: " + (Outer.get(1).get(8)) + "\n");
                            Label txtOwner = new Label("Store owner name: " + Outer.get(1).get(5) + "\n");
                            Label txtContact = new Label("Contact Details: " + Outer.get(1).get(6) + "\n");
                            Label txtLocation = new Label("Store Location: " + Outer.get(1).get(7) + "\n");
                            Image image = new Image(url);
                            txtQauntity.setFont(new Font("Arial Bold", 20));
                            txtOwner.setFont(new Font("Arial Bold", 20));
                            txtContact.setFont(new Font("Arial Bold", 20));
                            txtLocation.setFont(new Font("Arial Bold", 20));
                            imageview = new ImageView(image);
                            imageview.setFitHeight(300);
                            imageview.setFitWidth(300);

                            box.getChildren().add(imageview);
                            box.getChildren().add(txtQauntity);
                            box.getChildren().add(txtOwner);
                            box.getChildren().add(txtContact);
                            box.getChildren().add(txtLocation);
                            Scene stageScene = new Scene(box, 450, 450);
                            String title = Outer.get(1).get(0);
                            popup.setTitle(title);
                            popup.setScene(stageScene);
                            popup.show();


                        }
                    });
                    ButtonListViewMore.get(2).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage popup = new Stage();
                            VBox box = new VBox();
                            String url = Outer.get(2).get(4);
                            Label txtQauntity = new Label("Product Qauntity: " + (Outer.get(2).get(8)) + "\n");
                            Label txtOwner = new Label("Store owner name: " + Outer.get(2).get(5) + "\n");
                            Label txtContact = new Label("Contact Details: " + Outer.get(2).get(6) + "\n");
                            Label txtLocation = new Label("Store Location: " + Outer.get(2).get(7) + "\n");
                            Image image = new Image(url);
                            txtQauntity.setFont(new Font("Arial Bold", 20));
                            txtOwner.setFont(new Font("Arial Bold", 20));
                            txtContact.setFont(new Font("Arial Bold", 20));
                            txtLocation.setFont(new Font("Arial Bold", 20));
                            imageview = new ImageView(image);
                            imageview.setFitHeight(300);
                            imageview.setFitWidth(300);

                            box.getChildren().add(imageview);
                            box.getChildren().add(txtQauntity);
                            box.getChildren().add(txtOwner);
                            box.getChildren().add(txtContact);
                            box.getChildren().add(txtLocation);
                            Scene stageScene = new Scene(box, 450, 450);
                            String title = Outer.get(2).get(0);
                            popup.setTitle(title);
                            popup.setScene(stageScene);
                            popup.show();


                        }
                    });
                    ButtonListViewMore.get(3).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage popup = new Stage();
                            VBox box = new VBox();
                            String url = Outer.get(3).get(4);
                            Label txtQauntity = new Label("Product Qauntity: " + (Outer.get(3).get(8)) + "\n");
                            Label txtOwner = new Label("Store owner name: " + Outer.get(3).get(5) + "\n");
                            Label txtContact = new Label("Contact Details: " + Outer.get(3).get(6) + "\n");
                            Label txtLocation = new Label("Store Location: " + Outer.get(3).get(7) + "\n");
                            Image image = new Image(url);
                            txtQauntity.setFont(new Font("Arial Bold", 20));
                            txtOwner.setFont(new Font("Arial Bold", 20));
                            txtContact.setFont(new Font("Arial Bold", 20));
                            txtLocation.setFont(new Font("Arial Bold", 20));
                            imageview = new ImageView(image);
                            imageview.setFitHeight(300);
                            imageview.setFitWidth(300);

                            box.getChildren().add(imageview);
                            box.getChildren().add(txtQauntity);
                            box.getChildren().add(txtOwner);
                            box.getChildren().add(txtContact);
                            box.getChildren().add(txtLocation);
                            Scene stageScene = new Scene(box, 450, 450);
                            String title = Outer.get(3).get(0);
                            popup.setTitle(title);
                            popup.setScene(stageScene);
                            popup.show();


                        }
                    });
                    ButtonListViewMore.get(4).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage popup = new Stage();
                            VBox box = new VBox();
                            String url = Outer.get(4).get(4);
                            Label txtQauntity = new Label("Product Qauntity: " + (Outer.get(4).get(8)) + "\n");
                            Label txtOwner = new Label("Store owner name: " + Outer.get(4).get(5) + "\n");
                            Label txtContact = new Label("Contact Details: " + Outer.get(4).get(6) + "\n");
                            Label txtLocation = new Label("Store Location: " + Outer.get(4).get(7) + "\n");
                            Image image = new Image(url);
                            txtQauntity.setFont(new Font("Arial Bold", 20));
                            txtOwner.setFont(new Font("Arial Bold", 20));
                            txtContact.setFont(new Font("Arial Bold", 20));
                            txtLocation.setFont(new Font("Arial Bold", 20));
                            imageview = new ImageView(image);
                            imageview.setFitHeight(300);
                            imageview.setFitWidth(300);

                            box.getChildren().add(imageview);
                            box.getChildren().add(txtQauntity);
                            box.getChildren().add(txtOwner);
                            box.getChildren().add(txtContact);
                            box.getChildren().add(txtLocation);
                            Scene stageScene = new Scene(box, 450, 450);
                            String title = Outer.get(4).get(0);
                            popup.setTitle(title);
                            popup.setScene(stageScene);
                            popup.show();


                        }
                    });
                    ButtonListViewMore.get(5).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage popup = new Stage();
                            VBox box = new VBox();
                            String url = Outer.get(5).get(4);
                            Label txtQauntity = new Label("Product Qauntity: " + (Outer.get(5).get(8)) + "\n");
                            Label txtOwner = new Label("Store owner name: " + Outer.get(5).get(5) + "\n");
                            Label txtContact = new Label("Contact Details: " + Outer.get(5).get(6) + "\n");
                            Label txtLocation = new Label("Store Location: " + Outer.get(5).get(7) + "\n");
                            Image image = new Image(url);
                            txtQauntity.setFont(new Font("Arial Bold", 20));
                            txtOwner.setFont(new Font("Arial Bold", 20));
                            txtContact.setFont(new Font("Arial Bold", 20));
                            txtLocation.setFont(new Font("Arial Bold", 20));
                            imageview = new ImageView(image);
                            imageview.setFitHeight(300);
                            imageview.setFitWidth(300);

                            box.getChildren().add(imageview);
                            box.getChildren().add(txtQauntity);
                            box.getChildren().add(txtOwner);
                            box.getChildren().add(txtContact);
                            box.getChildren().add(txtLocation);
                            Scene stageScene = new Scene(box, 450, 450);
                            String title = Outer.get(5).get(0);
                            popup.setTitle(title);
                            popup.setScene(stageScene);
                            popup.show();


                        }
                    });
                    ButtonListViewMore.get(6).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage popup = new Stage();
                            VBox box = new VBox();
                            String url = Outer.get(6).get(4);
                            Label txtQauntity = new Label("Product Qauntity: " + (Outer.get(6).get(8)) + "\n");
                            Label txtOwner = new Label("Store owner name: " + Outer.get(6).get(5) + "\n");
                            Label txtContact = new Label("Contact Details: " + Outer.get(6).get(6) + "\n");
                            Label txtLocation = new Label("Store Location: " + Outer.get(6).get(7) + "\n");
                            Image image = new Image(url);
                            txtQauntity.setFont(new Font("Arial Bold", 20));
                            txtOwner.setFont(new Font("Arial Bold", 20));
                            txtContact.setFont(new Font("Arial Bold", 20));
                            txtLocation.setFont(new Font("Arial Bold", 20));
                            imageview = new ImageView(image);
                            imageview.setFitHeight(300);
                            imageview.setFitWidth(300);

                            box.getChildren().add(imageview);
                            box.getChildren().add(txtQauntity);
                            box.getChildren().add(txtOwner);
                            box.getChildren().add(txtContact);
                            box.getChildren().add(txtLocation);
                            Scene stageScene = new Scene(box, 450, 450);
                            String title = Outer.get(6).get(0);
                            popup.setTitle(title);
                            popup.setScene(stageScene);
                            popup.show();


                        }
                    });
                    ButtonListViewMore.get(7).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage popup = new Stage();
                            VBox box = new VBox();
                            String url = Outer.get(7).get(4);
                            Label txtQauntity = new Label("Product Qauntity: " + (Outer.get(7).get(8)) + "\n");
                            Label txtOwner = new Label("Store owner name: " + Outer.get(7).get(5) + "\n");
                            Label txtContact = new Label("Contact Details: " + Outer.get(7).get(6) + "\n");
                            Label txtLocation = new Label("Store Location: " + Outer.get(7).get(7) + "\n");
                            Image image = new Image(url);
                            txtQauntity.setFont(new Font("Arial Bold", 20));
                            txtOwner.setFont(new Font("Arial Bold", 20));
                            txtContact.setFont(new Font("Arial Bold", 20));
                            txtLocation.setFont(new Font("Arial Bold", 20));
                            imageview = new ImageView(image);
                            imageview.setFitHeight(300);
                            imageview.setFitWidth(300);

                            box.getChildren().add(imageview);
                            box.getChildren().add(txtQauntity);
                            box.getChildren().add(txtOwner);
                            box.getChildren().add(txtContact);
                            box.getChildren().add(txtLocation);
                            Scene stageScene = new Scene(box, 450, 450);
                            String title = Outer.get(7).get(0);
                            popup.setTitle(title);
                            popup.setScene(stageScene);
                            popup.show();


                        }
                    });
                    ButtonListViewMore.get(8).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage popup = new Stage();
                            VBox box = new VBox();
                            String url = Outer.get(8).get(4);
                            Label txtQauntity = new Label("Product Qauntity: " + (Outer.get(8).get(8)) + "\n");
                            Label txtOwner = new Label("Store owner name: " + Outer.get(8).get(5) + "\n");
                            Label txtContact = new Label("Contact Details: " + Outer.get(8).get(6) + "\n");
                            Label txtLocation = new Label("Store Location: " + Outer.get(8).get(7) + "\n");
                            Image image = new Image(url);
                            txtQauntity.setFont(new Font("Arial Bold", 20));
                            txtOwner.setFont(new Font("Arial Bold", 20));
                            txtContact.setFont(new Font("Arial Bold", 20));
                            txtLocation.setFont(new Font("Arial Bold", 20));
                            imageview = new ImageView(image);
                            imageview.setFitHeight(300);
                            imageview.setFitWidth(300);

                            box.getChildren().add(imageview);
                            box.getChildren().add(txtQauntity);
                            box.getChildren().add(txtOwner);
                            box.getChildren().add(txtContact);
                            box.getChildren().add(txtLocation);
                            Scene stageScene = new Scene(box, 450, 450);
                            String title = Outer.get(8).get(0);
                            popup.setTitle(title);
                            popup.setScene(stageScene);
                            popup.show();


                        }
                    });
                    ButtonListViewMore.get(9).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage popup = new Stage();
                            VBox box = new VBox();
                            String url = Outer.get(9).get(4);
                            Label txtQauntity = new Label("Product Qauntity: " + (Outer.get(9).get(8)) + "\n");
                            Label txtOwner = new Label("Store owner name: " + Outer.get(9).get(5) + "\n");
                            Label txtContact = new Label("Contact Details: " + Outer.get(9).get(6) + "\n");
                            Label txtLocation = new Label("Store Location: " + Outer.get(9).get(7) + "\n");
                            Image image = new Image(url);
                            txtQauntity.setFont(new Font("Arial Bold", 20));
                            txtOwner.setFont(new Font("Arial Bold", 20));
                            txtContact.setFont(new Font("Arial Bold", 20));
                            txtLocation.setFont(new Font("Arial Bold", 20));
                            imageview = new ImageView(image);
                            imageview.setFitHeight(300);
                            imageview.setFitWidth(300);

                            box.getChildren().add(imageview);
                            box.getChildren().add(txtQauntity);
                            box.getChildren().add(txtOwner);
                            box.getChildren().add(txtContact);
                            box.getChildren().add(txtLocation);
                            Scene stageScene = new Scene(box, 450, 450);
                            String title = Outer.get(9).get(0);
                            popup.setTitle(title);
                            popup.setScene(stageScene);
                            popup.show();


                        }
                    });
                    ButtonListViewMore.get(10).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage popup = new Stage();
                            VBox box = new VBox();
                            String url = Outer.get(10).get(4);
                            Label txtQauntity = new Label("Product Qauntity: " + (Outer.get(10).get(8)) + "\n");
                            Label txtOwner = new Label("Store owner name: " + Outer.get(10).get(5) + "\n");
                            Label txtContact = new Label("Contact Details: " + Outer.get(10).get(6) + "\n");
                            Label txtLocation = new Label("Store Location: " + Outer.get(10).get(7) + "\n");
                            Image image = new Image(url);
                            txtQauntity.setFont(new Font("Arial Bold", 20));
                            txtOwner.setFont(new Font("Arial Bold", 20));
                            txtContact.setFont(new Font("Arial Bold", 20));
                            txtLocation.setFont(new Font("Arial Bold", 20));
                            imageview = new ImageView(image);
                            imageview.setFitHeight(300);
                            imageview.setFitWidth(300);

                            box.getChildren().add(imageview);
                            box.getChildren().add(txtQauntity);
                            box.getChildren().add(txtOwner);
                            box.getChildren().add(txtContact);
                            box.getChildren().add(txtLocation);
                            Scene stageScene = new Scene(box, 450, 450);
                            String title = Outer.get(10).get(0);
                            popup.setTitle(title);
                            popup.setScene(stageScene);
                            popup.show();


                        }
                    });
                    ButtonListViewMore.get(11).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage popup = new Stage();
                            VBox box = new VBox();
                            String url = Outer.get(11).get(4);
                            Label txtQauntity = new Label("Product Qauntity: " + (Outer.get(11).get(8)) + "\n");
                            Label txtOwner = new Label("Store owner name: " + Outer.get(11).get(5) + "\n");
                            Label txtContact = new Label("Contact Details: " + Outer.get(11).get(6) + "\n");
                            Label txtLocation = new Label("Store Location: " + Outer.get(11).get(7) + "\n");
                            Image image = new Image(url);
                            txtQauntity.setFont(new Font("Arial Bold", 20));
                            txtOwner.setFont(new Font("Arial Bold", 20));
                            txtContact.setFont(new Font("Arial Bold", 20));
                            txtLocation.setFont(new Font("Arial Bold", 20));
                            imageview = new ImageView(image);
                            imageview.setFitHeight(300);
                            imageview.setFitWidth(300);

                            box.getChildren().add(imageview);
                            box.getChildren().add(txtQauntity);
                            box.getChildren().add(txtOwner);
                            box.getChildren().add(txtContact);
                            box.getChildren().add(txtLocation);
                            Scene stageScene = new Scene(box, 450, 450);
                            String title = Outer.get(11).get(0);
                            popup.setTitle(title);
                            popup.setScene(stageScene);
                            popup.show();


                        }
                    });
                    ButtonListViewMore.get(12).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage popup = new Stage();
                            VBox box = new VBox();
                            String url = Outer.get(12).get(4);
                            Label txtQauntity = new Label("Product Qauntity: " + (Outer.get(12).get(8)) + "\n");
                            Label txtOwner = new Label("Store owner name: " + Outer.get(12).get(5) + "\n");
                            Label txtContact = new Label("Contact Details: " + Outer.get(12).get(6) + "\n");
                            Label txtLocation = new Label("Store Location: " + Outer.get(12).get(7) + "\n");
                            Image image = new Image(url);
                            txtQauntity.setFont(new Font("Arial Bold", 20));
                            txtOwner.setFont(new Font("Arial Bold", 20));
                            txtContact.setFont(new Font("Arial Bold", 20));
                            txtLocation.setFont(new Font("Arial Bold", 20));
                            imageview = new ImageView(image);
                            imageview.setFitHeight(300);
                            imageview.setFitWidth(300);

                            box.getChildren().add(imageview);
                            box.getChildren().add(txtQauntity);
                            box.getChildren().add(txtOwner);
                            box.getChildren().add(txtContact);
                            box.getChildren().add(txtLocation);
                            Scene stageScene = new Scene(box, 450, 450);
                            String title = Outer.get(12).get(0);
                            popup.setTitle(title);
                            popup.setScene(stageScene);
                            popup.show();


                        }
                    });
                    ButtonListViewMore.get(13).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage popup = new Stage();
                            VBox box = new VBox();
                            String url = Outer.get(13).get(4);
                            Label txtQauntity = new Label("Product Qauntity: " + (Outer.get(13).get(8)) + "\n");
                            Label txtOwner = new Label("Store owner name: " + Outer.get(13).get(5) + "\n");
                            Label txtContact = new Label("Contact Details: " + Outer.get(13).get(6) + "\n");
                            Label txtLocation = new Label("Store Location: " + Outer.get(13).get(7) + "\n");
                            Image image = new Image(url);
                            txtQauntity.setFont(new Font("Arial Bold", 20));
                            txtOwner.setFont(new Font("Arial Bold", 20));
                            txtContact.setFont(new Font("Arial Bold", 20));
                            txtLocation.setFont(new Font("Arial Bold", 20));
                            imageview = new ImageView(image);
                            imageview.setFitHeight(300);
                            imageview.setFitWidth(300);

                            box.getChildren().add(imageview);
                            box.getChildren().add(txtQauntity);
                            box.getChildren().add(txtOwner);
                            box.getChildren().add(txtContact);
                            box.getChildren().add(txtLocation);
                            Scene stageScene = new Scene(box, 450, 450);
                            String title = Outer.get(13).get(0);
                            popup.setTitle(title);
                            popup.setScene(stageScene);
                            popup.show();


                        }
                    });
                    ButtonListViewMore.get(14).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage popup = new Stage();
                            VBox box = new VBox();
                            String url = Outer.get(14).get(4);
                            Label txtQauntity = new Label("Product Qauntity: " + (Outer.get(14).get(8)) + "\n");
                            Label txtOwner = new Label("Store owner name: " + Outer.get(14).get(5) + "\n");
                            Label txtContact = new Label("Contact Details: " + Outer.get(14).get(6) + "\n");
                            Label txtLocation = new Label("Store Location: " + Outer.get(14).get(7) + "\n");
                            Image image = new Image(url);
                            txtQauntity.setFont(new Font("Arial Bold", 20));
                            txtOwner.setFont(new Font("Arial Bold", 20));
                            txtContact.setFont(new Font("Arial Bold", 20));
                            txtLocation.setFont(new Font("Arial Bold", 20));
                            imageview = new ImageView(image);
                            imageview.setFitHeight(300);
                            imageview.setFitWidth(300);

                            box.getChildren().add(imageview);
                            box.getChildren().add(txtQauntity);
                            box.getChildren().add(txtOwner);
                            box.getChildren().add(txtContact);
                            box.getChildren().add(txtLocation);
                            Scene stageScene = new Scene(box, 450, 450);
                            String title = Outer.get(14).get(0);
                            popup.setTitle(title);
                            popup.setScene(stageScene);
                            popup.show();


                        }
                    });
                    ButtonListViewMore.get(15).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage popup = new Stage();
                            VBox box = new VBox();
                            String url = Outer.get(15).get(4);
                            Label txtQauntity = new Label("Product Qauntity: " + (Outer.get(15).get(8)) + "\n");
                            Label txtOwner = new Label("Store owner name: " + Outer.get(15).get(5) + "\n");
                            Label txtContact = new Label("Contact Details: " + Outer.get(15).get(6) + "\n");
                            Label txtLocation = new Label("Store Location: " + Outer.get(15).get(7) + "\n");
                            Image image = new Image(url);
                            txtQauntity.setFont(new Font("Arial Bold", 20));
                            txtOwner.setFont(new Font("Arial Bold", 20));
                            txtContact.setFont(new Font("Arial Bold", 20));
                            txtLocation.setFont(new Font("Arial Bold", 20));
                            imageview = new ImageView(image);
                            imageview.setFitHeight(300);
                            imageview.setFitWidth(300);

                            box.getChildren().add(imageview);
                            box.getChildren().add(txtQauntity);
                            box.getChildren().add(txtOwner);
                            box.getChildren().add(txtContact);
                            box.getChildren().add(txtLocation);
                            Scene stageScene = new Scene(box, 450, 450);
                            String title = Outer.get(15).get(0);
                            popup.setTitle(title);
                            popup.setScene(stageScene);
                            popup.show();


                        }
                    });
                    ButtonListViewMore.get(16).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage popup = new Stage();
                            VBox box = new VBox();
                            String url = Outer.get(16).get(4);
                            Label txtQauntity = new Label("Product Qauntity: " + (Outer.get(16).get(8)) + "\n");
                            Label txtOwner = new Label("Store owner name: " + Outer.get(16).get(5) + "\n");
                            Label txtContact = new Label("Contact Details: " + Outer.get(16).get(6) + "\n");
                            Label txtLocation = new Label("Store Location: " + Outer.get(16).get(7) + "\n");
                            Image image = new Image(url);
                            txtQauntity.setFont(new Font("Arial Bold", 20));
                            txtOwner.setFont(new Font("Arial Bold", 20));
                            txtContact.setFont(new Font("Arial Bold", 20));
                            txtLocation.setFont(new Font("Arial Bold", 20));
                            imageview = new ImageView(image);
                            imageview.setFitHeight(300);
                            imageview.setFitWidth(300);

                            box.getChildren().add(imageview);
                            box.getChildren().add(txtQauntity);
                            box.getChildren().add(txtOwner);
                            box.getChildren().add(txtContact);
                            box.getChildren().add(txtLocation);
                            Scene stageScene = new Scene(box, 450, 450);
                            String title = Outer.get(16).get(0);
                            popup.setTitle(title);
                            popup.setScene(stageScene);
                            popup.show();


                        }
                    });
                    ButtonListViewMore.get(17).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage popup = new Stage();
                            VBox box = new VBox();
                            String url = Outer.get(17).get(4);
                            Label txtQauntity = new Label("Product Qauntity: " + (Outer.get(17).get(8)) + "\n");
                            Label txtOwner = new Label("Store owner name: " + Outer.get(17).get(5) + "\n");
                            Label txtContact = new Label("Contact Details: " + Outer.get(17).get(6) + "\n");
                            Label txtLocation = new Label("Store Location: " + Outer.get(17).get(7) + "\n");
                            Image image = new Image(url);
                            txtQauntity.setFont(new Font("Arial Bold", 20));
                            txtOwner.setFont(new Font("Arial Bold", 20));
                            txtContact.setFont(new Font("Arial Bold", 20));
                            txtLocation.setFont(new Font("Arial Bold", 20));
                            imageview = new ImageView(image);
                            imageview.setFitHeight(300);
                            imageview.setFitWidth(300);

                            box.getChildren().add(imageview);
                            box.getChildren().add(txtQauntity);
                            box.getChildren().add(txtOwner);
                            box.getChildren().add(txtContact);
                            box.getChildren().add(txtLocation);
                            Scene stageScene = new Scene(box, 450, 450);
                            String title = Outer.get(17).get(0);
                            popup.setTitle(title);
                            popup.setScene(stageScene);
                            popup.show();


                        }
                    });
                    ButtonListViewMore.get(18).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage popup = new Stage();
                            VBox box = new VBox();
                            String url = Outer.get(18).get(4);
                            Label txtQauntity = new Label("Product Qauntity: " + (Outer.get(18).get(8)) + "\n");
                            Label txtOwner = new Label("Store owner name: " + Outer.get(18).get(5) + "\n");
                            Label txtContact = new Label("Contact Details: " + Outer.get(18).get(6) + "\n");
                            Label txtLocation = new Label("Store Location: " + Outer.get(18).get(7) + "\n");
                            Image image = new Image(url);
                            txtQauntity.setFont(new Font("Arial Bold", 20));
                            txtOwner.setFont(new Font("Arial Bold", 20));
                            txtContact.setFont(new Font("Arial Bold", 20));
                            txtLocation.setFont(new Font("Arial Bold", 20));
                            imageview = new ImageView(image);
                            imageview.setFitHeight(300);
                            imageview.setFitWidth(300);

                            box.getChildren().add(imageview);
                            box.getChildren().add(txtQauntity);
                            box.getChildren().add(txtOwner);
                            box.getChildren().add(txtContact);
                            box.getChildren().add(txtLocation);
                            Scene stageScene = new Scene(box, 450, 450);
                            String title = Outer.get(18).get(0);
                            popup.setTitle(title);
                            popup.setScene(stageScene);
                            popup.show();


                        }
                    });
                    ButtonListViewMore.get(19).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage popup = new Stage();
                            VBox box = new VBox();
                            String url = Outer.get(19).get(4);
                            Label txtQauntity = new Label("Product Qauntity: " + (Outer.get(19).get(8)) + "\n");
                            Label txtOwner = new Label("Store owner name: " + Outer.get(19).get(5) + "\n");
                            Label txtContact = new Label("Contact Details: " + Outer.get(19).get(6) + "\n");
                            Label txtLocation = new Label("Store Location: " + Outer.get(19).get(7) + "\n");
                            Image image = new Image(url);
                            txtQauntity.setFont(new Font("Arial Bold", 20));
                            txtOwner.setFont(new Font("Arial Bold", 20));
                            txtContact.setFont(new Font("Arial Bold", 20));
                            txtLocation.setFont(new Font("Arial Bold", 20));
                            imageview = new ImageView(image);
                            imageview.setFitHeight(300);
                            imageview.setFitWidth(300);

                            box.getChildren().add(imageview);
                            box.getChildren().add(txtQauntity);
                            box.getChildren().add(txtOwner);
                            box.getChildren().add(txtContact);
                            box.getChildren().add(txtLocation);
                            Scene stageScene = new Scene(box, 450, 450);
                            String title = ChoasComputersOuter.get(19).get(0);
                            popup.setTitle(title);
                            popup.setScene(stageScene);
                            popup.show();


                        }
                    });

                    ButtonListRemoveItem.get(0).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            names.remove(Linfo.get(0).getText());
                            txtCartCounter.setText("No. of items: " + names.size());
                            ComboBox.setItems(names);
                            cost.remove(new Integer(Outer.get(0).get(2)));
                        }
                    });
                    ButtonListRemoveItem.get(1).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            names.remove(Linfo.get(1).getText());
                            txtCartCounter.setText("No. of items: " + names.size());
                            ComboBox.setItems(names);
                            cost.remove(new Integer(Outer.get(1).get(2)));
                        }
                    });
                    ButtonListRemoveItem.get(2).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            names.remove(Linfo.get(2).getText());
                            txtCartCounter.setText("No. of items: " + names.size());
                            ComboBox.setItems(names);
                            cost.remove(new Integer(Outer.get(2).get(2)));
                        }
                    });
                    ButtonListRemoveItem.get(3).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            names.remove(Linfo.get(3).getText());
                            txtCartCounter.setText("No. of items: " + names.size());
                            ComboBox.setItems(names);
                            cost.remove(new Integer(Outer.get(3).get(2)));
                        }
                    });
                    ButtonListRemoveItem.get(4).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            names.remove(Linfo.get(4).getText());
                            txtCartCounter.setText("No. of items: " + names.size());
                            ComboBox.setItems(names);
                            cost.remove(new Integer(Outer.get(4).get(2)));
                        }
                    });
                    ButtonListRemoveItem.get(5).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            names.remove(Linfo.get(5).getText());
                            txtCartCounter.setText("No. of items: " + names.size());
                            ComboBox.setItems(names);
                            cost.remove(new Integer(Outer.get(5).get(2)));
                        }
                    });
                    ButtonListRemoveItem.get(6).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            names.remove(Linfo.get(6).getText());
                            txtCartCounter.setText("No. of items: " + names.size());
                            ComboBox.setItems(names);
                            cost.remove(new Integer(Outer.get(6).get(2)));
                        }
                    });
                    ButtonListRemoveItem.get(7).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            names.remove(Linfo.get(7).getText());
                            txtCartCounter.setText("No. of items: " + names.size());
                            ComboBox.setItems(names);
                            cost.remove(new Integer(Outer.get(7).get(2)));
                        }
                    });
                    ButtonListRemoveItem.get(8).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            names.remove(Linfo.get(8).getText());
                            txtCartCounter.setText("No. of items: " + names.size());
                            ComboBox.setItems(names);
                            cost.remove(new Integer(Outer.get(8).get(2)));
                        }
                    });
                    ButtonListRemoveItem.get(9).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            names.remove(Linfo.get(9).getText());
                            txtCartCounter.setText("No. of items: " + names.size());
                            ComboBox.setItems(names);
                            cost.remove(new Integer(Outer.get(9).get(2)));
                        }
                    });
                    ButtonListRemoveItem.get(10).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            names.remove(Linfo.get(10).getText());
                            txtCartCounter.setText("No. of items: " + names.size());
                            ComboBox.setItems(names);
                            cost.remove(new Integer(Outer.get(10).get(2)));
                        }
                    });
                    ButtonListRemoveItem.get(11).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            names.remove(Linfo.get(11).getText());
                            txtCartCounter.setText("No. of items: " + names.size());
                            ComboBox.setItems(names);
                            cost.remove(new Integer(Outer.get(11).get(2)));
                        }
                    });
                    ButtonListRemoveItem.get(12).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            names.remove(Linfo.get(12).getText());
                            txtCartCounter.setText("No. of items: " + names.size());
                            ComboBox.setItems(names);
                            cost.remove(new Integer(Outer.get(12).get(2)));
                        }
                    });
                    ButtonListRemoveItem.get(13).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            names.remove(Linfo.get(13).getText());
                            txtCartCounter.setText("No. of items: " + names.size());
                            ComboBox.setItems(names);
                            cost.remove(new Integer(Outer.get(13).get(2)));
                        }
                    });
                    ButtonListRemoveItem.get(14).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            names.remove(Linfo.get(14).getText());
                            txtCartCounter.setText("No. of items: " + names.size());
                            ComboBox.setItems(names);
                            cost.remove(new Integer(Outer.get(14).get(2)));
                        }
                    });
                    ButtonListRemoveItem.get(15).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            names.remove(Linfo.get(15).getText());
                            txtCartCounter.setText("No. of items: " + names.size());
                            ComboBox.setItems(names);
                            cost.remove(new Integer(Outer.get(15).get(2)));
                        }
                    });
                    ButtonListRemoveItem.get(16).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            names.remove(Linfo.get(16).getText());
                            txtCartCounter.setText("No. of items: " + names.size());
                            ComboBox.setItems(names);
                            cost.remove(new Integer(Outer.get(16).get(2)));
                        }
                    });
                    ButtonListRemoveItem.get(17).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            names.remove(Linfo.get(17).getText());
                            txtCartCounter.setText("No. of items: " + names.size());
                            ComboBox.setItems(names);
                            cost.remove(new Integer(Outer.get(17).get(2)));
                        }
                    });
                    ButtonListRemoveItem.get(18).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            names.remove(Linfo.get(18).getText());
                            txtCartCounter.setText("No. of items: " + names.size());
                            ComboBox.setItems(names);
                            cost.remove(new Integer(Outer.get(18).get(2)));
                        }
                    });
                    ButtonListRemoveItem.get(19).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            names.remove(Linfo.get(19).getText());
                            txtCartCounter.setText("No. of items: " + names.size());
                            ComboBox.setItems(names);
                            cost.remove(new Integer(Outer.get(19).get(2)));
                        }
                    });




                }
            });
        }















    }

    /**
     * GettingdbStuff() method
     * method adds all the newely created stores in the database into an array
     * */

    public void GettingdbStuff() throws SQLException {
        PreparedStatement p = null;
        ResultSet s = null;
        String q = "SELECT * FROM sellerinfo WHERE seller_store <> 'Choas Computers' and seller_store <> 'Nadias Kitchen' and seller_store <> 'Sams Shoes'";
        try {
            p = connectDB.prepareStatement(q);

            s = p.executeQuery();
            while(s.next()){
                GettingDBStuff.add(s.getString("seller_store"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void Recieve(String category, String storename)  {
        catt = category;
        NameOfStore = storename;

    }

    /**
     * initialize() method
     * the initialize() method has access to the @FXML fields and is called after the @FXML members
     * in this method I am able to populate the image view as well as set the background colors for the various anchor panes and panes
     * All pre populated labels and text fields are defined in this method
     * and this methods calls other methods within the StoreFrontController class which need to be initialized on creation of the screen
     * */
    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        linkbtn();



        System.out.println(NewStores);
        System.out.println(catt);
        PaymentFrontController p = new PaymentFrontController();
        btnAdmin.setVisible(false);
        btnSeller.setVisible(false);
        ComboBox.styleProperty().set("-fx-background-color: #DCAE96");
        btnView.styleProperty().set("-fx-background-color: #DCAE96");
        btnAccount.styleProperty().set("-fx-background-color: #DCAE96");
        btnSignIn.styleProperty().set("-fx-background-color: #DCAE96");
        btnHome.styleProperty().set("-fx-background-color: #DCAE96");
        btnClearCart.styleProperty().set("-fx-background-color: #DCAE96");
        spane.styleProperty().set("-fx-background-color: #add8e6");
        gPane.styleProperty().set("-fx-background-color: #add8e6");
        ArrayList<Label> LabelInfo = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            Label lbgt = new Label();
            LabelInfo.add(lbgt);
        }


        ButtonList();
        int cnt = 0;
        try {
            String query1 = "SELECT prduct_name,  product_store, product_price, product_category, product_linkimage, product_owner, owner_details, product_location, product_quantity FROM productinfo";
            Statement st = connectDB.createStatement();
            ResultSet rs = st.executeQuery(query1);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNo = rsmd.getColumnCount();

            while (rs.next()) {
                inner = new ArrayList<String>();
                for (int i = 1; i <= columnNo; i++) {
                    inner.add(rs.getString(i));
                }
                outer.add(inner);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


        try {
            String q2 = "SELECT COUNT(*) AS rowcount FROM productinfo";
            Statement s = connectDB.createStatement();
            ResultSet r = s.executeQuery(q2);
            r.next();
            row = r.getInt("rowcount");


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {


                if (cnt <= (row - 1)) {

                    String text = "Product Name: " + outer.get(cnt).get(0) + "\nPrice: R" + outer.get(cnt).get(2) + "\nStore: " + outer.get(cnt).get(1);
                    LabelInfo.get(cnt).setText(text);


                    LabelInfo.get(cnt).setFont(new Font("Arial Bold", 15));
                    LabelInfo.get(cnt).setMinWidth(50);
                    LabelInfo.get(cnt).setMaxHeight(50);
                    gPane.add(LabelInfo.get(cnt), i, j);
                    gPane.add(ButtonListAddtoCart.get(cnt), i, j);
                    gPane.add(ButtonListViewMore.get(cnt), i, j);
                    gPane.add(ButtonListRemoveItem.get(cnt), i, j);


                    LabelInfo.get(cnt).setAlignment(Pos.TOP_CENTER);
                    ButtonListViewMore.get(cnt).setAlignment(Pos.BOTTOM_RIGHT);
                    gPane.setValignment(LabelInfo.get(cnt), VPos.TOP);
                    gPane.setHalignment(ButtonListRemoveItem.get(cnt), HPos.RIGHT);
                    gPane.setValignment(ButtonListAddtoCart.get(cnt), VPos.BOTTOM);
                    gPane.setHalignment(ButtonListViewMore.get(cnt), HPos.LEFT);
                    gPane.setVgap(5);
                    gPane.setHgap(5);


                    cnt++;
                } else if (cnt > (row - 1)) {
                    String text = "Product not available";
                    Label lbby = new Label(text);
                    lbby.setFont(new Font("Arial Italics", 13));
                    lbby.setMinWidth(50);
                    lbby.setMaxHeight(50);
                    gPane.add(lbby, i, j);
                    lbby.setAlignment(Pos.TOP_RIGHT);
                    gPane.setValignment(lbby, VPos.TOP);
                    gPane.setVgap(5);
                    gPane.setHgap(5);


                }


            }

        }

        EventHandler<ActionEvent> event10 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");

                String text = LabelInfo.get(0).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(0).get(2));
                cost.add(number);


                items++;

                EventHandler<ActionEvent> event20 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();


                    }
                };
                btnClearCart.setOnAction(event20);
            }
        };
        ButtonListAddtoCart.get(0).setOnAction(event10);

        EventHandler<ActionEvent> event11 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelInfo.get(1).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                items++;
                int number = Integer.parseInt(outer.get(1).get(2));
                cost.add(number);

                EventHandler<ActionEvent> event21 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event21);
            }
        };
        ButtonListAddtoCart.get(1).setOnAction(event11);

        EventHandler<ActionEvent> event12 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelInfo.get(2).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(2).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event22 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event22);
            }
        };
        ButtonListAddtoCart.get(2).setOnAction(event12);

        EventHandler<ActionEvent> event13 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");

                String text = LabelInfo.get(3).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(3).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event23 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event23);
            }
        };
        ButtonListAddtoCart.get(3).setOnAction(event13);

        EventHandler<ActionEvent> event14 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelInfo.get(4).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(4).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event24 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event24);
            }
        };
        ButtonListAddtoCart.get(4).setOnAction(event14);

        EventHandler<ActionEvent> event15 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelInfo.get(5).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(5).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event25 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event25);
            }
        };
        ButtonListAddtoCart.get(5).setOnAction(event15);

        EventHandler<ActionEvent> event16 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelInfo.get(6).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(6).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event26 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event26);
            }
        };
        ButtonListAddtoCart.get(6).setOnAction(event16);

        EventHandler<ActionEvent> event17 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelInfo.get(7).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(7).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event27 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event27);
            }
        };
        ButtonListAddtoCart.get(7).setOnAction(event17);

        EventHandler<ActionEvent> event18 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelInfo.get(8).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(8).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event28 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event28);
            }
        };
        ButtonListAddtoCart.get(8).setOnAction(event18);

        EventHandler<ActionEvent> event19 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelInfo.get(9).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(9).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event29 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event29);
            }
        };
        ButtonListAddtoCart.get(9).setOnAction(event19);

        EventHandler<ActionEvent> event100 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");

                String text = LabelInfo.get(10).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(10).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event200 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event200);
            }
        };
        ButtonListAddtoCart.get(10).setOnAction(event100);


        EventHandler<ActionEvent> event101 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelInfo.get(11).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(11).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event201 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event201);
            }
        };
        ButtonListAddtoCart.get(11).setOnAction(event101);

        EventHandler<ActionEvent> event102 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelInfo.get(12).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(12).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event202 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event202);
            }
        };
        ButtonListAddtoCart.get(12).setOnAction(event102);

        EventHandler<ActionEvent> event103 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");

                String text = LabelInfo.get(13).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(13).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event203 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event203);
            }
        };
        ButtonListAddtoCart.get(13).setOnAction(event103);

        EventHandler<ActionEvent> event104 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelInfo.get(14).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(14).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event204 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event204);
            }
        };
        ButtonListAddtoCart.get(14).setOnAction(event104);

        EventHandler<ActionEvent> event105 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelInfo.get(15).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(15).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event205 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event205);
            }
        };
        ButtonListAddtoCart.get(15).setOnAction(event105);

        EventHandler<ActionEvent> event106 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelInfo.get(16).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(16).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event206 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event206);
            }
        };
        ButtonListAddtoCart.get(16).setOnAction(event106);

        EventHandler<ActionEvent> event107 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelInfo.get(17).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(17).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event207 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event207);
            }
        };
        ButtonListAddtoCart.get(17).setOnAction(event107);

        EventHandler<ActionEvent> event108 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelInfo.get(18).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(18).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event208 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event208);
            }
        };
        ButtonListAddtoCart.get(18).setOnAction(event108);

        EventHandler<ActionEvent> event109 = new EventHandler<ActionEvent>() {


            int items = 1;

            @Override
            public void handle(ActionEvent event) {
                names.remove("empty");
                String text = LabelInfo.get(19).getText();
                names.add(text);
                ComboBox.setItems(names);
                txtCartCounter.setText("No. of items: " + names.size());
                int number = Integer.parseInt(outer.get(19).get(2));
                cost.add(number);
                items++;

                EventHandler<ActionEvent> event209 = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        items = 1;
                        txtCartCounter.setText("No. of items: 0");
                        names.clear();
                        names.add("empty");
                        ComboBox.setItems(names);
                        cost.clear();

                    }
                };
                btnClearCart.setOnAction(event209);
            }
        };
        ButtonListAddtoCart.get(19).setOnAction(event109);


        ButtonListRemoveItem.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelInfo.get(0).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(0).get(2)));
            }
        });
        ButtonListRemoveItem.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelInfo.get(1).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(1).get(2)));
            }
        });
        ButtonListRemoveItem.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelInfo.get(2).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(2).get(2)));
            }
        });
        ButtonListRemoveItem.get(3).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelInfo.get(3).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(3).get(2)));
            }
        });
        ButtonListRemoveItem.get(4).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelInfo.get(4).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(4).get(2)));
            }
        });
        ButtonListRemoveItem.get(5).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelInfo.get(5).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(5).get(2)));
            }
        });
        ButtonListRemoveItem.get(6).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelInfo.get(6).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(6).get(2)));
            }
        });
        ButtonListRemoveItem.get(7).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelInfo.get(7).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(7).get(2)));
            }
        });
        ButtonListRemoveItem.get(8).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelInfo.get(8).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(8).get(2)));
            }
        });
        ButtonListRemoveItem.get(9).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelInfo.get(9).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(9).get(2)));
            }
        });
        ButtonListRemoveItem.get(10).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelInfo.get(10).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(10).get(2)));
            }
        });
        ButtonListRemoveItem.get(11).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelInfo.get(11).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(11).get(2)));
            }
        });
        ButtonListRemoveItem.get(12).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelInfo.get(12).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(12).get(2)));
            }
        });
        ButtonListRemoveItem.get(13).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelInfo.get(13).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(13).get(2)));
            }
        });
        ButtonListRemoveItem.get(14).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelInfo.get(14).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(14).get(2)));
            }
        });
        ButtonListRemoveItem.get(15).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelInfo.get(15).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(15).get(2)));
            }
        });
        ButtonListRemoveItem.get(16).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelInfo.get(16).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(16).get(2)));
            }
        });
        ButtonListRemoveItem.get(17).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelInfo.get(17).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(17).get(2)));
            }
        });
        ButtonListRemoveItem.get(18).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelInfo.get(18).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(18).get(2)));
            }
        });
        ButtonListRemoveItem.get(19).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                names.remove(LabelInfo.get(19).getText());
                txtCartCounter.setText("No. of items: " + names.size());
                ComboBox.setItems(names);
                cost.remove(new Integer(outer.get(19).get(2)));
            }
        });


        ButtonListViewMore.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(0).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(0).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(0).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(0).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(0).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(0).get(0);
                popup.setTitle(title);

                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(1).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(1).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(1).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(1).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(1).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(1).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(2).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(2).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(2).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(2).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(2).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(2).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(3).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(3).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(3).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(3).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(3).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(3).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(3).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(4).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(4).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(4).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(4).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(4).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(4).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(4).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(5).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(5).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(5).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(5).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(5).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(5).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(5).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(6).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(6).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(6).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(6).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(6).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(6).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(6).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(7).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(7).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(7).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(7).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(7).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(7).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(7).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(8).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(8).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(8).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(8).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(8).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(8).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(8).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(9).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(9).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(9).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(9).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(9).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(9).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(9).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(10).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(10).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(10).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(10).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(10).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(10).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(10).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(11).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(11).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(11).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(11).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(11).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(11).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(11).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(12).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(12).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(12).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(12).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(12).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(12).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(12).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(13).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(13).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(13).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(13).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(13).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(13).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(13).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(14).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(14).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(14).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(14).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(14).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(14).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(14).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(15).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(15).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(15).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(15).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(15).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(15).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(15).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(16).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(16).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(16).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(16).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(16).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(16).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(16).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(17).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(17).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(17).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(17).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(17).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(17).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(17).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(18).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(18).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(18).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(18).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(18).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(18).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(18).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });
        ButtonListViewMore.get(19).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popup = new Stage();
                VBox box = new VBox();
                String url = outer.get(19).get(4);
                Label txtQauntity = new Label("Product Qauntity: " + (outer.get(19).get(8)) + "\n");
                Label txtOwner = new Label("Store owner name: " + outer.get(19).get(5) + "\n");
                Label txtContact = new Label("Contact Details: " + outer.get(19).get(6) + "\n");
                Label txtLocation = new Label("Store Location: " + outer.get(19).get(7) + "\n");
                Image image = new Image(url);
                txtQauntity.setFont(new Font("Arial Bold", 20));
                txtOwner.setFont(new Font("Arial Bold", 20));
                txtContact.setFont(new Font("Arial Bold", 20));
                txtLocation.setFont(new Font("Arial Bold", 20));
                imageview = new ImageView(image);
                imageview.setFitHeight(300);
                imageview.setFitWidth(300);

                box.getChildren().add(imageview);
                box.getChildren().add(txtQauntity);
                box.getChildren().add(txtOwner);
                box.getChildren().add(txtContact);
                box.getChildren().add(txtLocation);
                Scene stageScene = new Scene(box, 450, 450);
                String title = outer.get(19).get(0);
                popup.setTitle(title);
                popup.setScene(stageScene);
                popup.show();


            }
        });


    }

    /**
     * SignIn() method
     * this method contains the code that takes the user to the login FXML page
     * here the StoreFrontController FXML stage is closed and the login FXML is displayed
     * this method also contains data that gets parsed to the login() class
     * */
    @FXML
    public void SignIn(javafx.event.ActionEvent actionEvent) {
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
    }

    /**
     * getUser() method
     * this meethod gets data from another class and sets the buttons visible and changes the background color of the buttons
     * */
    public void getUser(String email, String role) {
        userEmail = email;
        userRole = role;
        email_Label.setText("Welcome, " + email);

        if (role.equalsIgnoreCase("seller")) {
            btnSeller.setVisible(true);
            btnSeller.styleProperty().set("-fx-background-color: #ADFF2F");
        } else if (role.equalsIgnoreCase("admin"))
            btnAdmin.setVisible(true);
        btnAdmin.styleProperty().set("-fx-background-color: #ADFF2F");
    }
    /**
     * getUser2() method
     * this meethod gets data from another class and sets the buttons visible and changes the background color of the buttons
     * */
    public void getUser2(String emanuel, String rolo) {
        userEmail = emanuel;
        userRole = rolo;

        if (emanuel.equals("sad")){
            email_Label.setText(" ");
        } else {
            email_Label.setText("Welcome, " + emanuel);
        }

        if (rolo.equalsIgnoreCase("seller")) {
            btnSeller.setVisible(true);
            btnSeller.styleProperty().set("-fx-background-color: #ADFF2F");
        } else if (rolo.equalsIgnoreCase("admin")) {
            btnAdmin.setVisible(true);
            btnAdmin.styleProperty().set("-fx-background-color: #ADFF2F");
        } else if (rolo.equals("sad")){
            btnSeller.setVisible(false);
            btnAdmin.setVisible(false);
        }
    }
    /**
     * getUserFromSellerPortal() method
     * this meethod gets data from the SellerPortal class and sets the buttons visible and changes the background color of the buttons
     * this method also contains the code that takes the user to the SellerPortal FXML page
     * here the StoreFrontController FXML stage is closed and the SellerPortal FXML is displayed
     *  this method also contains data that gets parsed to the SellerPortal() class
     * */
    public void getUserFromSellerPortal(String eele, String reele, String stname) throws SQLException {

        email_Label.setText("Welcome, " + eele);
        eele1 = eele;
        if (reele.equalsIgnoreCase("seller")) {
            btnSeller.setVisible(true);
            btnSeller.styleProperty().set("-fx-background-color: #ADFF2F");
            btnSeller.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(javafx.event.ActionEvent actionEvent) {

                    String pass = null;
                    PreparedStatement f1 = null;
                    ResultSet rs6 = null;
                    String q2 = "SELECT password FROM userdbinfo WHERE email = ?";
                    try {
                        f1 = connectDB.prepareStatement(q2);
                        f1.setString(1, eele);
                        rs6 = f1.executeQuery();
                        while (rs6.next()) {
                            pass = rs6.getString("password");
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    try {
                        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("SellerPortal.fxml"));
                        Parent viewParent = (Parent) loader.load();
                        SellerPortalController sp = loader.getController();
                        sp.RecieveFromStoreController(stname, pass);
                        Stage stage = new Stage();
                        stage.setScene(new Scene(viewParent));
                        stage.show();

                    } catch (IOException | SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

        } else if (reele.equalsIgnoreCase("admin")){
            btnAdmin.setVisible(true);
        btnAdmin.styleProperty().set("-fx-background-color: #ADFF2F");
    }

    }

    /**
     * changeScene() method
     * this method contains the code that takes the user to the CartFront FXML page
     * here the StoreFrontController FXML stage is closed and the CartFront FXML is displayed
     * this method also contains data that gets parsed to the CartFront() class
     * */
    @FXML
    void changeScene(javafx.event.ActionEvent actionEvent) {
        int amount = 0;
        for (int i = 0; i < cost.size(); i++) {
            amount = amount + cost.get(i);
        }

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("CartFront.fxml"));
            Parent viewParent = (Parent) loader.load();
            CartFrontController CartController = loader.getController();
            for (int i = 0; i < names.size(); i++) {
                String item = names.get(i);

                String[] k = names.get(i).split("\n");
                String storename = k[2].replaceAll(".+:", "").replaceFirst("^ *", "");
                String productname = k[0].replaceAll(".+:", "").replaceFirst("^ *", "");
                int productprice = Integer.parseInt(k[1].replaceAll(".+:", "").replaceFirst("^ *", "").replaceAll("R", ""));
                CartController.myFunction(item, amount, userEmail, userRole, storename, productname, productprice);

            }
            Stage stage = new Stage();
            stage.setScene(new Scene(viewParent));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * AcountChangeScene() method
     * this method contains the code that takes the user to the Account FXML page
     * here the StoreFrontController FXML stage is closed and the Account FXML is displayed
     * this method also contains data that gets parsed to the Account() class
     * */

    public void AcountChangeScene(javafx.event.ActionEvent actionEvent) {

        String ez = email_Label.getText().replaceAll("Welcome, ", "");
        System.out.println(userRole);
        try {
            ((Node) actionEvent.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Account.fxml"));
            Parent viewParent = (Parent) loader.load();
            AccountController ac = loader.getController();
            ac.funk(ez, userRole);
            //AccountController ac= loader.getController();
            //ac.recieveEmail("sara@gmail.com");

            Stage stage = new Stage();
            stage.setScene(new Scene(viewParent));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * SellerPortal() method
     * this method is used to allow the user to sign into the seller portal
     * a pop up window is displayed that allows the user to ener information
     * this method contains the code that takes the user to the SellerPortal FXML page
     * here the StoreFrontController FXML stage is closed and the SellerPortal FXML is displayed
     * this method also contains data that gets parsed to the SellerPortal() class
     * */
    public void SellerPortal(javafx.event.ActionEvent actionEvent) {
        Stage popup = new Stage();
        VBox box = new VBox();
        box.setSpacing(30);
        box.setPadding(new Insets(10,10,10,10));
        Label storeName  = new Label("Enter store name");
        Label enterPassword = new Label("Enter Password");
        TextField storename = new TextField();
        PasswordField pFieldOriginal = new PasswordField();
        Button enterButton = new Button("Enter");
        Button createAccount = new Button("Create store account");
        enterPassword.setFont((new Font("Arial Bold", 20)));
        storeName.setFont((new Font("Arial Bold", 20)));
        enterButton.setMaxSize(500, 500);
        createAccount.setMaxSize(500, 500);
        Label status = new Label();
        status.setFont((new Font("Arial Bold", 20)));
        box.getChildren().add(storeName);
        box.getChildren().add(storename);
        box.getChildren().add(enterPassword);
        box.getChildren().add(pFieldOriginal);
        box.getChildren().add(enterButton);
        box.getChildren().add(createAccount);
        box.getChildren().add(status);
        Scene stageScene = new Scene(box, 550,  590);
        String title = "Store sign-in";
        popup.setTitle(title);
        popup.setScene(stageScene);
        popup.show();


        enterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String nameOfStore = storename.getText();
                String password = pFieldOriginal.getText();
                String pword = "";
                String aword = "";
                String sword = "";
                String r = "seller";
                PreparedStatement ps2 = null;

                ResultSet rs2 = null;
                PreparedStatement ps3 = null;
                ResultSet rs3 = null;

                String query3 = "SELECT seller_store FROM sellerinfo WHERE seller_email = ?";

                String query2 = "SELECT password FROM userdbinfo WHERE email= ? and role = ?";
                try {
                    ps2 = connectDB.prepareStatement(query2);
                    ps2.setString(1, userEmail);
                    ps2.setString(2, r);
                    rs2 = ps2.executeQuery();

                    ps3 = connectDB.prepareStatement(query3);
                    ps3.setString(1, email_Label.getText().replaceAll("Welcome, ", ""));
                    rs3 = ps3.executeQuery();

                    while (rs2.next() && rs3.next() ) {
                        pword = rs2.getString("password");
                        sword = rs3.getString("seller_store");

                    }

                    if (sword.equals(nameOfStore)) {
                            if (pword.equals(password)) {
                                if (!nameOfStore.equals("") || !password.equals("")) {
                                    try {
                                        popup.close();
                                        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("SellerPortal.fxml"));
                                        Parent viewParent = (Parent) loader.load();
                                        SellerPortalController sp = loader.getController();
                                        sp.RecieveFromStoreController(sword, pword);
                                        Stage stage = new Stage();
                                        stage.setScene(new Scene(viewParent));
                                        stage.show();

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                } else {
                                    status.setText("Please enter information.");

                                }


                        }else {
                            status.setText("Store doesnt exists, please create one.");
                        }

                }else {
                        status.setText("Store doesnt exists, please create one.");
                }


                }catch (Exception e){
                        e.printStackTrace();
                }

            }
        });

        createAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String name =null;
                String email =null;

                String phone = null;
                String role = null;
                PreparedStatement state = null;
                ResultSet rst = null;
                String qtee = "SELECT name,email, phone, role FROM userdbinfo WHERE email = ?";
                try{
                    state = connectDB.prepareStatement(qtee);
                    state.setString(1, userEmail);
                    rst = state.executeQuery();

                    while (rst.next()){
                        name = rst.getString("name");
                        email = rst.getString("email");
                        phone = rst.getString("phone");
                        role = rst.getString("role");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


                Stage popup2 = new Stage();
                VBox box2 = new VBox();
                box2.setSpacing(15);
                box2.setPadding(new Insets(10,10,10,10));
                Label PersonalInfoTag = new Label("Personal Information: ");
                PersonalInfoTag.setFont((new Font("Arial Bold", 30)));
                Label n = new Label("Name: " + name);
                n.setFont((new Font("Arial Bold", 20)));
                Label e = new Label("Email: " + email);
                e.setFont((new Font("Arial Bold", 20)));
                Label p = new Label("Contact Details: " + phone);
                p.setFont((new Font("Arial Bold", 20)));
                Label r = new Label("Site role: " + role);
                r.setFont((new Font("Arial Bold", 20)));

                Label StoreTag = new Label("Store Information: ");
                StoreTag.setFont((new Font("Arial Bold", 30)));

                Label storename = new Label("Enter store name");
                storename.setFont((new Font("Arial Bold", 20)));
                TextField txfName = new TextField();


                Label storelocation = new Label("Enter store location");
                storelocation.setFont((new Font("Arial Bold", 20)));
                TextField txfLocation = new TextField();


                Label AccountTag = new Label("Account Information: ");
                AccountTag.setFont((new Font("Arial Bold", 30)));

                Label accountname = new Label("Enter account name");
                accountname.setFont((new Font("Arial Bold", 20)));
                TextField txfAccountName = new TextField();


                Label accountno = new Label("Enter account number");
                accountno.setFont((new Font("Arial Bold", 20)));
                TextField txfAccountNo = new TextField();


                Label accounttype = new Label("Enter account type (Business or Cheque)");
                accounttype.setFont((new Font("Arial Bold", 20)));
                TextField txfAccountType = new TextField();


                Label branch = new Label("Enter Branch name");
                branch.setFont((new Font("Arial Bold", 20)));
                TextField txfBranch = new TextField();


                Label branchNo = new Label("Enter Branch number");
                branchNo.setFont((new Font("Arial Bold", 20)));
                TextField txfBranchNo = new TextField();


                Button StoreCreated = new Button("Create store");
                StoreCreated.setMaxSize(500, 500);

                box2.getChildren().add(PersonalInfoTag);
                box2.getChildren().add(n);
                box2.getChildren().add(e);
                box2.getChildren().add(p);
                box2.getChildren().add(r);
                box2.getChildren().add(StoreTag);
                box2.getChildren().add(storename);
                box2.getChildren().add(txfName);
                box2.getChildren().add(storelocation);
                box2.getChildren().add(txfLocation);
                box2.getChildren().add(AccountTag);
                box2.getChildren().add(accountname);
                box2.getChildren().add(txfAccountName);
                box2.getChildren().add(accountno);
                box2.getChildren().add(txfAccountNo);
                box2.getChildren().add(accounttype);
                box2.getChildren().add(txfAccountType);
                box2.getChildren().add(branch);
                box2.getChildren().add(txfBranch);
                box2.getChildren().add(branchNo);
                box2.getChildren().add(txfBranchNo);
                box2.getChildren().add(StoreCreated);

                Scene stageScene1 = new Scene(box2, 550,  1080);
                String title = "Create store account";
                popup2.setTitle(title);
                popup2.setScene(stageScene1);
                popup2.show();

                StoreCreated.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String dbStoreName = null;
                        String NameOfStore = null;
                        String LocationOfStore = null;
                        String NameOfAccount = null;
                        String NumberOfAccount = null;
                        String TypeOfAccount = null;
                        String Branch = null ;
                        String BranchNo = null;
                        NameOfStore = txfName.getText();
                        LocationOfStore = txfLocation.getText();
                        NameOfAccount = txfAccountName.getText();
                        NumberOfAccount = txfAccountNo.getText();
                        TypeOfAccount = txfAccountType.getText();
                        Branch = txfBranch.getText();
                        BranchNo = txfBranchNo.getText();

                        PreparedStatement state1 = null;
                        ResultSet rst1 = null;
                        String qtee1 = "SELECT * FROM sellerinfo";
                        try{
                            state1 = connectDB.prepareStatement(qtee1);
                            rst1 = state1.executeQuery();

                        } catch (Exception c) {
                            c.printStackTrace();
                        }

                            PreparedStatement preparedStatement = null;
                            String query2 = "INSERT INTO sellerinfo(seller_name, seller_location, seller_store, seller_email, account_name, account_no, account_type, branch, branch_code) VALUES(?,?,?,?,?,?,?,?,?)";

                            try {
                                preparedStatement = connectDB.prepareStatement(query2);
                                preparedStatement.setString(1, n.getText().replaceAll("Name: ", ""));
                                preparedStatement.setString(2, LocationOfStore);
                                preparedStatement.setString(3, NameOfStore);
                                preparedStatement.setString(4, e.getText().replaceAll("Email: ", ""));
                                preparedStatement.setString(5, NameOfAccount);
                                preparedStatement.setString(6,NumberOfAccount);
                                preparedStatement.setString(7, TypeOfAccount);
                                preparedStatement.setString(8, Branch);
                                preparedStatement.setString(9, BranchNo);

                                preparedStatement.executeUpdate();


                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }


                        popup2.hide();

                    }
                });


            }
        });





    }

    /**
     * getAdminUser() method
     * sets label to users name
     * sets the admin button visible and changes background color of button
     * */
    public void getAdminUser(String email){
        email_Label.setText("Welcome, "+ email);
        btnAdmin.setVisible(true);
        btnAdmin.styleProperty().set("-fx-background-color: #ADFF2F");
    }

    /**
     * AdminPortal() method
     * this method contains the code that takes the user to the Admin FXML page
     * here the StoreFrontController FXML stage is closed and the Admin FXML is displayed
     * this method also contains data that gets parsed to the Admin() class
     * */


    public void AdminPortal(javafx.event.ActionEvent actionEvent) {
        try {

            ((Node) actionEvent.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
            Parent viewParent = (Parent) loader.load();
            Admin ad = loader.getController();
            ad.GetAdminUser(email_Label.getText().replaceAll("Welcome, ", ""));
            Stage stage = new Stage();
            stage.setScene(new Scene(viewParent));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


