package com.example;

/**
 * @author Hoang Quoc Bao - s3926050
 */

public class DigitalProduct extends Product {

    // constructors
    public DigitalProduct(String name, String description, int availableQuantity, double price, String message) {
        super(name, description, availableQuantity, price, message);
    }

    // overidden functions
    @Override
    public String toString() {
        return String.format("DIGITAL - %s", getName());
    }

    @Override
    public String getProductType() {
        return "DIGITAL";
    }

    @Override
    public String getDisplayInfo() {
        return String.format("%s   [type= %s, description= %s, quantity= %d, price= %,.2f, message= %s]", 
        getName(), getProductType(), getDescription(), getAvailableQuantity(), getPrice(), getMessage());
    }
}
