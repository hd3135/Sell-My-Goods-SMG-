package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 * SalesPersonModel() class
 * This class  consists of application specific domain objects
 * this model represents objects carrying data
 * */
public class SalesPersonModel {
    private SimpleStringProperty SellerName;
    private SimpleStringProperty SellerLocation;
    private SimpleStringProperty SellerStore;
    private SimpleStringProperty SellerEmail;
    private SimpleStringProperty AccountName;
    private SimpleStringProperty AccountNo;
    private SimpleStringProperty AccountType;
    private SimpleStringProperty Branch;
    private SimpleStringProperty BranchCode;

    /**
     * SalesPersonModel() method
     * This is the constructor that validates the data
     * it initializes an instance of the newly created objects before they are used
     *
     * */

    public SalesPersonModel(String sellername, String sellerlocation, String sellerstore, String selleremail, String accountname, String accountno, String accounttype, String branch, String branchcode) {
        this.SellerName = new SimpleStringProperty(sellername);
        this.SellerLocation = new SimpleStringProperty(sellerlocation);
        this.SellerStore = new SimpleStringProperty(sellerstore);
        this.SellerEmail = new SimpleStringProperty(selleremail);
        this.AccountName = new SimpleStringProperty(accountname);
        this.AccountNo = new SimpleStringProperty(accountno);
        this.AccountType = new SimpleStringProperty(accounttype);
        this.Branch = new SimpleStringProperty(branch);
        this.BranchCode =  new SimpleStringProperty(branchcode);
    }
    /**
     * Getters() and Setter() Methods for the above created objects
     * these getters and setters allow me to control how variables are accessed and updated in the various controller classes
     * the getter() method returns data
     * the setter() method sets or updates its value
     * */
    public String getSellerName() {
        return SellerName.get();
    }

    public SimpleStringProperty sellerNameProperty() {
        return SellerName;
    }

    public void setSellerName(String sellerName) {
        this.SellerName.set(sellerName);
    }

    public String getSellerLocation() {
        return SellerLocation.get();
    }

    public SimpleStringProperty sellerLocationProperty() {
        return SellerLocation;
    }

    public void setSellerLocation(String sellerLocation) {
        this.SellerLocation.set(sellerLocation);
    }

    public String getSellerStore() {
        return SellerStore.get();
    }

    public SimpleStringProperty sellerStoreProperty() {
        return SellerStore;
    }

    public void setSellerStore(String sellerStore) {
        this.SellerStore.set(sellerStore);
    }

    public String getSellerEmail() {
        return SellerEmail.get();
    }

    public SimpleStringProperty sellerEmailProperty() {
        return SellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.SellerEmail.set(sellerEmail);
    }

    public String getAccountName() {
        return AccountName.get();
    }

    public SimpleStringProperty accountNameProperty() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        this.AccountName.set(accountName);
    }

    public String getAccountNo() {
        return AccountNo.get();
    }

    public SimpleStringProperty accountNoProperty() {
        return AccountNo;
    }

    public void setAccountNo(String accountNo) {
        this.AccountNo.set(accountNo);
    }

    public String getAccountType() {
        return AccountType.get();
    }

    public SimpleStringProperty accountTypeProperty() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        this.AccountType.set(accountType);
    }

    public String getBranch() {
        return Branch.get();
    }

    public SimpleStringProperty branchProperty() {
        return Branch;
    }

    public void setBranch(String branch) {
        this.Branch.set(branch);
    }

    public String getBranchCode() {
        return BranchCode.get();
    }

    public SimpleStringProperty branchCodeProperty() {
        return BranchCode;
    }

    public void setBranchCode(String branchCode) {
        this.BranchCode.set(branchCode);
    }
}

