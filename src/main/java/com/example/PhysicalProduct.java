package com.example;

/**
 * @author Hoang Quoc Bao - s3926050
 */

public class PhysicalProduct extends Product {
    /**
     * Physical product attributes
     */
    private double weight;

    // constructors
    public PhysicalProduct(String name, String description, int availableQuantity, double price, double weight, String message) {
        super(name, description, availableQuantity, price, message);
        this.weight = weight;
    }

    // getter
    public double getWeight() {
        return weight;
    }

    // setter
    public void setWeight(double weight) {
        this.weight = weight;
    }

    // overriden functions
    @Override
    public String toString() {
        return String.format("PHYSICAL - %s", getName());
    }

    @Override
    public String getProductType() {
        return "PHYSICAL";
    }

    @Override
    public String getDisplayInfo() {
        return String.format("%s  [type= %s, description= %s, quantity= %d, price= %,.2f, weight= %,.2f, message= %s]", 
        getName(), getProductType(), getDescription(), getAvailableQuantity(), getPrice(), getWeight(), getMessage());
    }
    
}
