/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author shekh
 */
public class ConsoleArray {
    // An array to store the current consoles. 
    private ArrayList<Console> consoles = new ArrayList();

    //A method to set reset or create a new list of array named consoles.
    public ConsoleArray() {
        consoles = new ArrayList(3);
    }
    
    // The getter method to get the list and for display purposes
    public ArrayList<Console> getConsoles() {
        return consoles;
    }    
    
    //A method to add the new console object to the consoles ArrayList
    public void add(Console console){
        consoles.add(console);
    }
    
    // A temporary method to populate the ArrayList consoles which later will be 
    // done by reading the items in from a database.
    /*public void add(){
            //Creating the entity objects 
            Console C1 = new Console("PlayStation3", UUID.randomUUID(), "Black", 199, 4, "500");
            Console C2 = new Console("PlayStation4", UUID.randomUUID(), "White", 299, 8, "500");
            Console C3 = new Console("PlayStationPro", UUID.randomUUID(), "Blue", 399, 16, "1");
            consoles.add(C1);
            consoles.add(C2);
            consoles.add(C3);
    }*/
    
    
    
    
    // A search method which takes in the name and colour of the console and 
    // returns any mathing console objects
    public Console getConsoles(String Name, String consoleid){
        for(int i = 0; i < consoles.size(); i++){
            Console c = consoles.get(i);
            if (Name.equals(c.getName()) && consoleid.equals(c.getConsoleid().toString()))
                return c;
        }
        return null;
    }
    
    //Get the existing consoles from the database and add them to the list
    public void loadConsoles() {
        Connection connection = null;
        Statement statement = null;
        ResultSet results = null;
        try {
            // Loading the database driver
            
            System.out.println("You here man");
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            
            // Connecting to the database
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/Consoles ", "app", "app");
        }
        catch (SQLException ex) {
            System.out.println("Connection failed!");
        }
        catch (Exception ex) {
            System.out.println("No driver!");
        }
        
        try {
            statement = connection.createStatement();
            results = statement.executeQuery("select * from Consoles ");
            
            // Using while loop to extract the values
            while (results.next()) {
                // Extracting each values
                String Name = results.getString("Name");
                String Console_ID = results.getString("Console_ID");
                UUID consoleid = UUID.fromString(Console_ID);
                String Color = results.getString("Colour");
                int Price = results.getInt("Price");
                int Memory = results.getInt("Memory");
                String Storage = results.getString("Storage");
                
                // Construct a new console object from that data, and add to list
                Console c = new Console(Name, consoleid, Color, Price, Memory, Storage); 
                consoles.add(c);
            }
        }        
        catch (SQLException ex) {
            System.out.println("Query failed!");
        }
    }
    
     // Save new console to the database
    public void saveConsole(String Name, UUID consoleid, String color, int Price, int Memory, String Storage) {
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet results = null;
        try {
            // Load the appropriate database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            
            // Connect to the database through that driver, using the 
            // database url and the username and password
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/Consoles ", "app", "app");
        }
        catch (SQLException ex) {
            System.out.println("Connection failed!");
        }
        catch (Exception ex) {
            System.out.println("No driver!");
        }
        
        try {
            String template = "insert into Consoles"
                    + "(Name, Console_ID, Colour, Price, Memory, Storage)"
                    + "values (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(template);
            statement.setString(1, Name);
            String Console_ID = consoleid.toString();
            statement.setString(2, Console_ID);
            statement.setString(3, color);
            statement.setInt(4, Price);
            statement.setInt(5, Memory);
            statement.setString(6, Storage);
            int changed = statement.executeUpdate();
            System.out.println(changed+" records added");
        }      
        catch (SQLException ex) {
            System.out.println("Query failed!");
        }
        
    }
}
