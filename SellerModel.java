package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * SellerModel() class
 * This class  consists of application specific domain objects
 * this model represents objects carrying data
 * */

public class SellerModel {
    private SimpleStringProperty ProductName;
    private SimpleIntegerProperty ProductPrice;
    private SimpleStringProperty ProdcutCategory;
    private SimpleIntegerProperty ProductQuantity;
    private SimpleStringProperty ProductStore;
    private SimpleStringProperty ProductLinkImage;
    private SimpleStringProperty ProductOwner;
    private SimpleStringProperty ProductOwnerDetails;
    private SimpleStringProperty ProductLocation;

    /**
     * SellerModel() method
     * This is the constructor that validates the data
     * it initializes an instance of the newly created objects before they are used
     *
     * */
    public SellerModel(String productname, int productprice, String productcategory, int productquantity, String productstore, String productlinkimage, String productowner, String ownerdetails, String location) {
        this.ProductName = new SimpleStringProperty(productname);
        this.ProductPrice = new SimpleIntegerProperty(productprice);
        this.ProdcutCategory = new SimpleStringProperty(productcategory);
        this.ProductQuantity = new SimpleIntegerProperty(productquantity);
        this.ProductStore = new SimpleStringProperty(productstore);
        this.ProductLinkImage = new SimpleStringProperty(productlinkimage);
        this.ProductOwner = new SimpleStringProperty(productowner);
        this.ProductOwnerDetails = new SimpleStringProperty(ownerdetails);
        this.ProductLocation =  new SimpleStringProperty(location);
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

    public SimpleStringProperty productNameProperty() {
        return ProductName;
    }

    public void setProductName(String productName) {
        this.ProductName.set(productName);
    }

    public int getProductPrice() {
        return ProductPrice.get();
    }

    public SimpleIntegerProperty productPriceProperty() {
        return ProductPrice;
    }

    public void setProductPrice(int productPrice) {
        this.ProductPrice.set(productPrice);
    }

    public String getProdcutCategory() {
        return ProdcutCategory.get();
    }

    public SimpleStringProperty prodcutCategoryProperty() {
        return ProdcutCategory;
    }

    public void setProdcutCategory(String prodcutCategory) {
        this.ProdcutCategory.set(prodcutCategory);
    }

    public int getProductQuantity() {
        return ProductQuantity.get();
    }

    public SimpleIntegerProperty productQuantityProperty() {
        return ProductQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.ProductQuantity.set(productQuantity);
    }

    public String getProductStore() {
        return ProductStore.get();
    }

    public SimpleStringProperty productStoreProperty() {
        return ProductStore;
    }

    public void setProductStore(String productStore) {
        this.ProductStore.set(productStore);
    }

    public String getProductLinkImage() {
        return ProductLinkImage.get();
    }

    public SimpleStringProperty productLinkImageProperty() {
        return ProductLinkImage;
    }

    public void setProductLinkImage(String productLinkImage) {
        this.ProductLinkImage.set(productLinkImage);
    }

    public String getProductOwner() {
        return ProductOwner.get();
    }

    public SimpleStringProperty productOwnerProperty() {
        return ProductOwner;
    }

    public void setProductOwner(String productOwner) {
        this.ProductOwner.set(productOwner);
    }

    public String getProductOwnerDetails() {
        return ProductOwnerDetails.get();
    }

    public SimpleStringProperty productOwnerDetailsProperty() {
        return ProductOwnerDetails;
    }

    public void setProductOwnerDetails(String productOwnerDetails) {
        this.ProductOwnerDetails.set(productOwnerDetails);
    }

    public String getProductLocation() {
        return ProductLocation.get();
    }

    public SimpleStringProperty productLocationProperty() {
        return ProductLocation;
    }

    public void setProductLocation(String productLocation) {
        this.ProductLocation.set(productLocation);
    }
}
