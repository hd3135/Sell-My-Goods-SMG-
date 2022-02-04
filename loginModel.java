package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * loginModel() class
 * This class  consists of application specific domain objects
 * this model represents objects carrying data
 * */
public class loginModel {
    DatabaseConnection connectionNow = new DatabaseConnection();
    Connection connectDB = connectionNow.getConnection();

    /**
     * The loginModel() method is used to check if a user already exists on the system or not
     * it checks information entered from the user against information in the database
     * returns true if the information entered by the user exists in the database already
     * returns false if otherwise
     * */

    public boolean isUser(String user, String phone) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query1 = "SELECT * FROM userdbinfo WHERE email = ?";
        String query2 = "SELECT * FROM userdbinfo WHERE phone = ?";
        try {
            preparedStatement = connectDB.prepareStatement(query1);
            preparedStatement.setString(1,user);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }else {
                try {
                    preparedStatement = connectDB.prepareStatement(query2);
                    preparedStatement.setString(1,phone);
                    resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()){
                        return true;
                    }else {
                        return false;
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    return false;
                }finally {
                    preparedStatement.close();
                    resultSet.close();
                }
            }
        }catch (Exception e){
            return false;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
}
