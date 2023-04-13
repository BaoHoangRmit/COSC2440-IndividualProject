package com.example;

/**
 * @author Hoang Quoc Bao - s3926050
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * class to manage shopping cart functions
 */
public class CartManager {
    /**
     * List of Shopping Cart
     */
    private static List<ShoppingCart> cartList = new ArrayList<>();

    // getter
    public static List<ShoppingCart> getCartList() {
        return cartList;
    }

    // setter
    public static void setCartList(List<ShoppingCart> cartList) {
        CartManager.cartList = cartList;
    }

    // functions

    /**
     * print all shopping carts 
     * <p>
     * If the list of cart is empty -> print message
     * else sort the cart ascending weight and print all shopping cart
     * </p>
     */
    public static void getAllCarts() {
        if (cartList.isEmpty()) {
            System.out.println("You currently have no shopping cart!!");
        } else {
            Collections.sort(cartList);
            for (ShoppingCart cart : cartList) {
                System.out.printf("%s\n", cart);
            }
        }
    }

    /**
     * create new empty shopping cart
     * <p>
     * Create a new empty shopping cart with no product inside
     * Print success/fail message
     * </p>
     */
    public static void createNewCart() {
        if (cartList.add(new ShoppingCart())) {
            System.out.println("Create new cart successfully!");
        } else System.out.println("Create new cart failed!");
    }

    /**
     * get shopping cart by cart id
     * <p>
     * Given an id String
     * Return the shopping cart with the given id String
     * If no results were found -> return null
     * </p>
     * @param searchCartId the search cart id
     * @return shopping cart if found / null if not found
     */
    public static ShoppingCart getCartById(String searchCartId) {
        if (cartList != null) {
            for (ShoppingCart shoppingCart : cartList) {
                if (searchCartId.equals(shoppingCart.getCartId())) return shoppingCart;
            }
            return null;
        }
        return null;
    }

}
