/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.sql.*;
import java.util.*;
/**
 *
 * @author shekh
 */
public class ConsoleList {
    // Creating a consolelist called consolelist
    private ArrayList<Console> consolelist;
    
    //Definig a function which creates a new consolelist Array upon called
    public ConsoleList(){
        consolelist = new ArrayList();
    }
    
    //Function which returns the the list of courses 
    public ArrayList getConsoles(){
        return consolelist;
    }
    
    //Function to add a console to the console list
    public void add(Console c){
        consolelist.add(c);
    }
    
    //Method to return the size of the console list
    public int getSize(){
        return consolelist.size();
    }
    
    // A search method to search for a specific console from the console list
    public Console getConsole(String Name, String consoleid){
        for(int i = 0; i < getSize(); i++){
            Console c = consolelist.get(i);
            if (Name.equals(c.getName()) && consoleid.equals(c.getConsoleid().toString()))
                return c;
        }
        return null;
    }
    
    //Remove the console by searching based on its name and consoleid
    public void remove(String Name, String consoleid){
        Console c = getConsole(Name, consoleid);
        consolelist.remove(c);
    }
    
    //Get the existing consoles from the database and add them to the list
    public void loadConsoles() {
        Connection connection = null;
        Statement statement = null;
        ResultSet results = null;
        try {
            // Loading the database driver
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
                consolelist.add(c);
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
