package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 * Products() class
 * This class  consists of application specific domain objects
 * this model represents objects carrying data
 * */
public class Products {
    private SimpleStringProperty ProductName;
    private SimpleIntegerProperty Price;
    private SimpleStringProperty StoreName;
    /**
     * Products() method
     * This is the constructor that validates the data
     * it initializes an instance of the newly created objects before they are used
     *
     * */
    public Products(String storename, String productName, int price) {
        this.ProductName = new SimpleStringProperty(productName);
        this.Price = new SimpleIntegerProperty(price);
        this.StoreName = new SimpleStringProperty(storename);
    }
    /**
     * Getters() and Setter() Methods for the above created objects
     * these getters and setters allow me to control how variables are accessed and updated in the various controller classes
     * the getter() method returns data
     * the setter() method sets or updates its value
     * */

    public String getProductName() {
        return ProductName.get();
    }

    public void setProductName(String productName) {
        ProductName = new SimpleStringProperty(productName);
    }

    public int getPrice() {
        return Price.get();
    }

    public void setPrice(int price) {
        Price = new SimpleIntegerProperty(price);
    }

    public String getStoreName() {
        return StoreName.get();
    }

    public void setStoreName(String storeName) {
        StoreName = new SimpleStringProperty(storeName);
    }
}