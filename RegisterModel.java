package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * RegisterModel() class
 * This class  consists of application specific domain objects
 * this model represents objects carrying data
 * */
import org.apache.commons.validator.routines.EmailValidator;

public class RegisterModel {
    DatabaseConnection connectionNow = new DatabaseConnection();
    Connection connectDB = connectionNow.getConnection();

    /**
     * PasswordMatch() method
     * This boolean method is used to determine whether the two
     * passwords entered in by the user at the registration match with each other
     * returns true to they do match, false otherwise
     * */
    public boolean passwordMatch(String password1,String password2){
        if (password1.equals(password2)) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * validPhoneNumber() method
     * This boolean method is used to determine whether the
     * phone number entered in by the user at the registration is a valid phone number
     * returns true if valid, false otherwise
     * */
    public boolean validPhoneNumber(String phoneNumber){
        String regex = "\\d+";
        if (phoneNumber.matches(regex)) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * validEmail() method
     * This boolean method is used to determine whether the email
     * entered in by the user at the registration is valid and contains all the relevant string items
     * to valid the email the apache commons validator library was used
     * */

    public boolean validEmail(String email){
        boolean allowLocal;
        allowLocal = true;
        boolean valid = EmailValidator.getInstance(allowLocal).isValid(email);
        return valid;

    }

    /**
     * validPassword() method
     * This boolean method is used to determine whether the
     * passwords entered in by the user at the registration is valid and contains sufficients characters to be classified
     * as a strong enough password
     * */
    public boolean validPassword(String password){
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        if (password.matches(regex)) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * isRegistered() method
     * This boolean method is used to add the user to the database
     * here a sql query is executed which inserts data into the userdbinfo table in the database
     * this method is called in the RegisterController class when the user selects the create account button
     * */

    public boolean isRegistered(String name,String email,String phoneNumber,String password, String role) throws SQLException {
        PreparedStatement preparedStatement = null;
        String query2 = "INSERT INTO userdbinfo(name, password, email, phone, role) VALUES(?,?,?,?,?)";
        try{
            preparedStatement = connectDB.prepareStatement(query2);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5,  role);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }finally {
            preparedStatement.close();
        }
    }








}
