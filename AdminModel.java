package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 * AdminModel() class
 * This class  consists of application specific domain objects
 * this model represents objects carrying data
 * */
public class AdminModel {
    private SimpleStringProperty Name;
    private SimpleStringProperty Email;
    private SimpleStringProperty Request;
    /**
     * AdminModel() method
     * This is the constructor that validates the data
     * it initializes an instance of the newly created objects before they are used
     *
     * */
    public AdminModel(String name, String email, String request) {
        this.Name = new SimpleStringProperty(name);
        this.Email = new SimpleStringProperty(email);
        this.Request = new SimpleStringProperty(request);
    }
    /**
     * Getters() and Setter() Methods for the above created objects
     * these getters and setters allow me to control how variables are accessed and updated in the various controller classes
     * the getter() method returns data
     * the setter() method sets or updates its value
     * */

    public String getName() {
        return Name.get();
    }

    public SimpleStringProperty nameProperty() {
        return Name;
    }

    public void setName(String name) {
        this.Name.set(name);
    }

    public String getEmail() {
        return Email.get();
    }

    public SimpleStringProperty emailProperty() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email.set(email);
    }

    public String getRequest() {
        return Request.get();
    }

    public SimpleStringProperty requestProperty() {
        return Request;
    }

    public void setRequest(String request) {
        this.Request.set(request);
    }
}
