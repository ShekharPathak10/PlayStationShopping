/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.UUID;

/**
 *
 * @author shekh
 */
public class Console {
    //To store the Name of the console
    private String Name = "";
    
    //To store the console number or the unique ID for the console
    private UUID consoleid;
    
    //To store the price of the console
    private int Price = 0;
    
    //To store the color of the console
    private String Colour = "";
    
    //To store the Memory size of the console
    private int Memory = 0; 
    
    //TO store the HDD size or Storage capacity of the console
    // Had to make the Storage String to compare the length to display GB or TB accordingly
    private String Storage = "";
    
    //This is a constructor to create a new console item
    public Console(String Name, UUID consoleid, String Colour, int Price, int Memory, String Storage){
        this.Name = Name;
        this.consoleid = consoleid;
        this.Colour = Colour; 
        this.Price = Price; 
        this.Memory = Memory;
        this.Storage = Storage;
    }
    
    
    public Console() {
    }
    
    //Console Name getter
    public String getName() {
        return Name;
    }
    
    //Console Name setter
    public void setName(String Name) {
        this.Name = Name;
    }
    
    //Console Number getter
    public UUID getConsoleid() {
        return consoleid;
    }
    
    //Console Number setter
    public void setNumber(UUID id) {
        this.consoleid = id;
    }
    
    //Console price getter
    public int getPrice() {
        return Price;
    }
    
    //Console price setter
    public void setPrice(int Price) {
        this.Price = Price;
    }

    public String getColour() {
        return Colour;
    }
    
    //Console colour setter
    public void setColour(String Colour) {
        this.Colour = Colour;
    }
    
    // Console memory getter
    public int getMemory() {
        return Memory;
    }
    
    //Console memory setter
    public void setMemory(int Memory) {
        this.Memory = Memory;
    }
    
    //Console Storage capacity getter
    public String getStorage() {
        return Storage;
    }
    
    //Console Storage setter
    public void setStorage(String Storage) {
        this.Storage = Storage;
    }
    
    public int total(int a){
        int b = 0;
        b = b + a;
        return b; 
    }
    
}


