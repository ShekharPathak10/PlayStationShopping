/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author shekh
 */
public class ConsoleErrorList {
    private boolean nameMissing = false;
    //private boolean nameAlphanumeric = false; 
    
    private boolean colorMissing = false;
    
    private boolean priceMissing = false;
    private boolean priceNotNumeric = false;
    private boolean priceTooSmall = false;
    
    private boolean memoryMissing = false;
    
    private boolean storageMissing = false;
    private boolean storageIllegal = false;

    public boolean isNameMissing() {
        return nameMissing;
    }

    public void setNameMissing(boolean nameMissing) {
        this.nameMissing = nameMissing;
    }

    public boolean isColorMissing() {
        return colorMissing;
    }

    public void setColorMissing(boolean colorMissing) {
        this.colorMissing = colorMissing;
    }

    public boolean isPriceMissing() {
        return priceMissing;
    }

    public void setPriceMissing(boolean priceMissing) {
        this.priceMissing = priceMissing;
    }

    public boolean isPriceNotNumeric() {
        return priceNotNumeric;
    }

    public void setPriceNotNumeric(boolean priceNotNumeric) {
        this.priceNotNumeric = priceNotNumeric;
    }

    public boolean isPriceTooSmall() {
        return priceTooSmall;
    }

    public void setPriceTooSmall(boolean priceTooSmall) {
        this.priceTooSmall = priceTooSmall;
    }

    public boolean isMemoryMissing() {
        return memoryMissing;
    }

    public void setMemoryMissing(boolean memoryMissing) {
        this.memoryMissing = memoryMissing;
    }

    public boolean isStorageMissing() {
        return storageMissing;
    }

    public void setStorageMissing(boolean storageMissing) {
        this.storageMissing = storageMissing;
    }

    public boolean isStorageIllegal() {
        return storageIllegal;
    }

    public void setStorageIllegal(boolean storageIllegal) {
        this.storageIllegal = storageIllegal;
    }
    
}
