package kata5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import kata5.persistance.MailListReader;
import java.sql.PreparedStatement;
import kata5.model.Mail;

public class Kata5 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        Class.forName("org.sqlite.JDBC");
        
        String ruta = "emails.txt";
        List<Mail> emails = MailListReader.read(ruta);
       
        
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:data/us500.db");
                
                Statement statement = connection.createStatement()) {
            
                    String createTable = "CREATE TABLE IF NOT EXISTS emails (\n"
                            + "id integer PRIMARY KEY AUTOINCREMENT,\n"
                            + "email text NOT NULL);";
                    
                    //Se crea la nueva tabla
                    statement.execute(createTable);
                    System.out.println("Tabla creada");
                    
                    String insertMail = "INSERT INTO emails(email) VALUES(?)";
                    
                    for (Mail email :  emails) {
                        String direccion = email.getMail();
                        
                        try (PreparedStatement preparedStatement = connection.prepareStatement(insertMail)) {
                            preparedStatement.setString(1, direccion);
                            preparedStatement.executeUpdate();
                        }
                        
                    }
                    
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
         
    }
    
}
