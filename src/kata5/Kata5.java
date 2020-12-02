package kata5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Kata5 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        Class.forName("org.sqlite.JDBC");
        
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:data/us500.db");
                
                Statement statement = connection.createStatement()) {
        
                ResultSet result = statement.executeQuery("SELECT * FROM people");
                
                while (result.next()) {
                    String email = result.getString("email");
                    System.out.println(email);
                }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
         
    }
    
}
