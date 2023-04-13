package com.example;

/**
 * @author Hoang Quoc Bao - s3926050
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

public class ShoppingCart implements Comparable<ShoppingCart> {
    /**
     * List of Shopping Cart
     */
    private String cartId;

    // list of product name inside the cart
    private HashSet<String> productListCart;
    private double totalWeight;

    // constructors

    /**
     * auto generate cart id when create new shopping cart
     */
    public ShoppingCart() {
        LocalDateTime createId = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyHHmmss");
        this.cartId = "C" + createId.format(myFormatObj);

        this.productListCart = new HashSet<>();
        this.totalWeight = 0;
    }

    // getter
    public String getCartId() {
        return cartId;
    }

    public HashSet<String> getProductListCart() {
        return productListCart;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    // setter
    public void setCartId(String cartId) {
        this.cartId = cartId;
    } 

    public void setProductListCart(HashSet<String> productListCart) {
        this.productListCart = productListCart;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    // functions

    /**
     * add new product to this cart
     * <p>
     * Given a product name 
     * add the product with the given name to this cart
     * change necessary attributes of this cart according to the product
     * </p>
     * @param productName the product name
     * @return true if added successfully, false if failed
     */
    public boolean addItem(String productName) {
        // cannot add product to this cart if the product is already in it
        if (productListCart.contains(productName)) {
            return false;
        }

        Product selectedProduct = ProductManager.getProductByName(productName);
        if (selectedProduct == null) return false;
        
        // cannot add the product with available quantity = 0
        if (selectedProduct.getAvailableQuantity() == 0) {
            return false;
        }

        selectedProduct.decreaseQuantity(1);
        productListCart.add(productName);

        if (selectedProduct.getProductType().equals("PHYSICAL")) {
            addWeight((PhysicalProduct) selectedProduct);
        }

        return true;
    }

    /**
     * remove a product out of this cart
     * <p>
     * Given a product name 
     * remove the product with the given name out of this cart
     * change necessary attributes of this cart according to the product
     * </p>
     * @param productName the product name
     * @return true if removed successfully, false if failed
     */
    public boolean removeItem(String productName) {
        // cannot add product to this cart if the product is not in it
        if (!(productListCart.contains(productName))) {
            return false;
        }

        Product selectedProduct = ProductManager.getProductByName(productName);
        if (selectedProduct == null) return false;

        selectedProduct.increaseQuantity(1);
        productListCart.remove(productName);

        if (selectedProduct.getProductType().equals("PHYSICAL")) {
            minusWeight((PhysicalProduct) selectedProduct);
        }

        return true;
    }

    /**
     * calculate the cart price according to price and shipping fee
     * <p>
     * cart price = product price + shipping fee
     * shipping fee = total weight of all physical products * base fee
     * base fee = 0.1
     * </p>
     * @return total price of the cart
     */
    public double cartAmount() {
        double totalPrice = 0;

        for (String productName : productListCart) {
            Product selectedProduct = ProductManager.getProductByName(productName);
            totalPrice += selectedProduct.getPrice();
        }

        totalPrice += (totalWeight * 0.1);

        return totalPrice;
    }

    /**
     * update the weight of this cart
     * <p>
     * reset the weight to 0
     * calculate the weight of this cart again
     * </p>
     */
    public void updateTotalWeight() {
        setTotalWeight(0);

        for (String productName : productListCart) {
            Product selectedProduct = ProductManager.getProductByName(productName);
            if (selectedProduct.getProductType().equals("PHYSICAL")) {
                PhysicalProduct physicalProduct = (PhysicalProduct) selectedProduct;
                addWeight(physicalProduct);
            }
        }
    }

    /**
     * add the weight of the given product to total weight of this cart
     * @param physicalProduct the given product
     */
    private void addWeight(PhysicalProduct physicalProduct) {
        totalWeight += physicalProduct.getWeight();
    }

    /**
     * minus the weight of the given product to total weight of this cart
     * @param physicalProduct the given product
     */
    private void minusWeight(PhysicalProduct physicalProduct) {
        totalWeight -= physicalProduct.getWeight();
    }

    // overriden methods

    /**
     * methods to compare shopping carts according to weight
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ShoppingCart)) return false;
        if (obj == this) return true;
        return this.totalWeight == ((ShoppingCart) obj).getTotalWeight();
    }

    @Override
    public int hashCode() {
        return ((Double) totalWeight).hashCode();
    }

    @Override
    public int compareTo(ShoppingCart o) {
        return ((Double) this.totalWeight).compareTo((Double) o.getTotalWeight());
    }

    /**
     * String representation
     */
    @Override
    public String toString() {
        if (getProductListCart().isEmpty()) {
            return String.format("Cart %s: There are no products in this cart!", cartId);
        }
        return String.format("Cart %s: items= [%s], weight= %,.2f, price= %,.2f", 
        cartId, getProductListCart().toString(), getTotalWeight(), cartAmount());
    }

    
}
