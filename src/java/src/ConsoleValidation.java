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

/**
 *
 * @author shekh
 */
public class ConsoleValidation {
    //Checking to see if the user has entered the name of the course
    public static boolean validateName(String name, ConsoleErrorList errors){
        if("".equals(name.trim())){
            errors.setNameMissing(true);
            return false;
        }
        else {
            return true;
        }
    }
    
    
    //Checking to see if the user has selected a color for the console from the dropdown
    public static boolean validateColor(String color, ConsoleErrorList errors) {
        if (color == null || color.equals("choose")) {
            errors.setColorMissing(true);
            return false;
        }
        else {
            return true;
        }
    }
    
    //Checking to see if the user has enter price of the Console. 
    //If yes then validating if the price is in legal format. 
    public static boolean validatePrice(String price, ConsoleErrorList errors){
        //Fist checking if any price is entered
        price = price.trim();
        
        if ("".equals(price)) {
            errors.setPriceMissing(true);
            return false;
        }
        
        //If the price is entered check to see if the value entered is a non-negative integer
        else{
            try{
                int Price = Integer.parseInt(price);
                
                //Check to see if the price is negetive or not
                if(Price < 0){
                    errors.setPriceTooSmall(true);
                    return false;
                }
            }
            
            //If a NumberFormatException error occurs, the user has put non-integer values, so create the priceNotNumeric error
            catch(NumberFormatException e){
                errors.setPriceNotNumeric(true);
                return false;
            }
        }
        //If everyting is satisfied then return true
        return true;
    }
    
    public static boolean validateMemory(String memory, ConsoleErrorList errors) {
        if (memory == null || memory.equals("choose")) {
            errors.setMemoryMissing(true);
            return false;
        }
        else {
            return true;
        }
    }
    
    public static boolean validateStorage(String storage, ConsoleErrorList errors){
        //First checking if the user has entered storage capacity
        
        if ("".equals(storage.trim())) {
            errors.setStorageMissing(true);
            return false;
        }
        
        //A storage for PS consoles range from 256 GB to 2 TB 
        //In detail the storage can be 256 GB, 500 GB, 1 TB or 2 TB
        String RegEx = "((256)|(500)|(1)|(2))";
        storage = storage.trim();
        if(!storage.matches(RegEx)){
            errors.setStorageIllegal(true);
            return false;
        }
        else{
            return true;
        }
    }
    
    public static boolean validateConsoleUnique(String Name, String ConsoleID ) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet results = null;
        boolean alreadyExists = false;
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
            // Ceate parameterized query for existing course
            String template = "select * from Consoles "
                    + "where Name = ? and ConsoleID = ?";
            statement = connection.prepareStatement(template);
            
            // Insert prefix and number of proposed course into query and perform
            statement.setString(1, Name);
            statement.setString(2, ConsoleID);           
            results = statement.executeQuery();
            
            // If next returns true, some existing record has same prefix and number
            alreadyExists = results.next();
        }     
        catch (SQLException ex) {
            System.out.println("Query failed!");
        }
        if (alreadyExists) {
            // Since the console exists in the databse and is not unique the function will return false
            return false;        
        }
        else {
            return true;
        }
    } 
}
