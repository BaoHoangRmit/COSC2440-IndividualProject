package com.example;

/**
 * @author Hoang Quoc Bao - s3926050
 */

public abstract class Product implements Gift {
    /**
     * Product attributes
     */
    private String name;
    private String description;
    private int availableQuantity;
    private double price;
    private String message;

    // constructors

    /**
     * @param name 
     * @param description
     * @param availableQuantity non-negative
     * @param price non-negative 
     * @param message nullable
     */
    public Product(String name, String description, int availableQuantity, double price, String message) {
        this.name = name;
        this.description = description;
        this.availableQuantity = availableQuantity;
        this.price = price;
        this.message = message;
    }

    // getter
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public double getPrice() {
        return price;
    }

    // setter
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // overriden functions
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String msg) {
        this.message = msg;  
    }

    // abstract functions

    /**
     * String representation
     */
    @Override
    public abstract String toString();

    /**
     * get the product type (PHYSICAL / DIGITAL)
     */
    public abstract String getProductType();

    /**
     * get the formatted information String of a product
     */
    public abstract String getDisplayInfo();

    // functions

    /**
     * increase product quantity by an amount 
     * <p>
     * Given an amount integer -> increase the product available quanity by that amount
     * </p>
     * @param amount amount to increase
     */
    public void increaseQuantity(int amount) {
        availableQuantity += amount;
    }

    /**
     * decrease product quantity by an amount 
     * <p>
     * Given an amount integer -> decrease the product available quanity by that amount
     * </p>
     * @param amount amount to decrease
     */
    public void decreaseQuantity(int amount) {
        availableQuantity -= amount;
    }
}
