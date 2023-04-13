package com.example;

/**
 * @author Hoang Quoc Bao - s3926050
 */

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;

/**
 * Unit test for Cart.
 */
public class CartTest
{
    /**
     * Test for getCartList()
     */
    @Test
    public void canGetCartList()
    {
        ShoppingCart shoppingCart1 = new ShoppingCart();
        ShoppingCart shoppingCart2 = new ShoppingCart();

        CartManager.getCartList().add(shoppingCart1);
        CartManager.getCartList().add(shoppingCart2);

        List<ShoppingCart> carts = new ArrayList<>();
        carts.add(shoppingCart1);
        carts.add(shoppingCart2);

        assertEquals(CartManager.getCartList(), carts);
    }

    /**
     * Test for getCartById()
     */
    @Test
    public void canGetCartById()
    {
        ShoppingCart shoppingCart1 = new ShoppingCart();
        ShoppingCart shoppingCart2 = new ShoppingCart();

        CartManager.getCartList().add(shoppingCart1);
        CartManager.getCartList().add(shoppingCart2);

        assertEquals(CartManager.getCartById(shoppingCart1.getCartId()), shoppingCart1);
    }

    /**
     * Test for addItem()
     */
    @Test
    public void canAddItemToCart()
    {
        PhysicalProduct physicalProduct1 = new PhysicalProduct("orange", "this is an orange", 10, 
        10, 10, "gift for teachers");
        PhysicalProduct physicalProduct2 = new PhysicalProduct("apple", "this is an apple", 20, 
        20, 20, null);
        DigitalProduct digitalProduct1 = new DigitalProduct("eBook", "this is an online book", 30, 
        30, "gift for students");
        DigitalProduct digitalProduct2 = new DigitalProduct("eVoucher", "this is an eVoucher", 40, 
        40, null);

        ProductManager.getProductList().add(physicalProduct1);
        ProductManager.getProductList().add(physicalProduct2);
        ProductManager.getProductList().add(digitalProduct1);
        ProductManager.getProductList().add(digitalProduct2);

        ShoppingCart shoppingCart1 = new ShoppingCart();

        CartManager.getCartList().add(shoppingCart1);

        assertTrue(shoppingCart1.addItem("orange"));
        assertFalse(shoppingCart1.addItem("orange"));
    }

    /**
     * Test for removeItem()
     */
    @Test
    public void canRemoveItemFromCart()
    {
        PhysicalProduct physicalProduct1 = new PhysicalProduct("orange", "this is an orange", 10, 
        10, 10, "gift for teachers");
        PhysicalProduct physicalProduct2 = new PhysicalProduct("apple", "this is an apple", 20, 
        20, 20, null);
        DigitalProduct digitalProduct1 = new DigitalProduct("eBook", "this is an online book", 30, 
        30, "gift for students");
        DigitalProduct digitalProduct2 = new DigitalProduct("eVoucher", "this is an eVoucher", 40, 
        40, null);

        ProductManager.getProductList().add(physicalProduct1);
        ProductManager.getProductList().add(physicalProduct2);
        ProductManager.getProductList().add(digitalProduct1);
        ProductManager.getProductList().add(digitalProduct2);

        ShoppingCart shoppingCart1 = new ShoppingCart();

        CartManager.getCartList().add(shoppingCart1);
        shoppingCart1.addItem("orange");
        shoppingCart1.addItem("apple");

        assertTrue(shoppingCart1.removeItem("orange"));
        assertFalse(shoppingCart1.removeItem("eBook"));
    }

    /**
     * Test for cartAmount()
     */
    @Test
    public void canCalculatePrice()
    {
        PhysicalProduct physicalProduct1 = new PhysicalProduct("orange", "this is an orange", 10, 
        10, 10, "gift for teachers");
        PhysicalProduct physicalProduct2 = new PhysicalProduct("apple", "this is an apple", 20, 
        20, 20, null);
        DigitalProduct digitalProduct1 = new DigitalProduct("eBook", "this is an online book", 30, 
        30, "gift for students");
        DigitalProduct digitalProduct2 = new DigitalProduct("eVoucher", "this is an eVoucher", 40, 
        40, null);

        ProductManager.getProductList().add(physicalProduct1);
        ProductManager.getProductList().add(physicalProduct2);
        ProductManager.getProductList().add(digitalProduct1);
        ProductManager.getProductList().add(digitalProduct2);

        ShoppingCart shoppingCart1 = new ShoppingCart();

        CartManager.getCartList().add(shoppingCart1);
        shoppingCart1.addItem("orange");
        shoppingCart1.addItem("apple");

        assertEquals(shoppingCart1.cartAmount(), 33.00);
    }

    /**
     * Test for getTotalWeight()
     */
    @Test
    public void canUpdateWeight()
    {
        PhysicalProduct physicalProduct1 = new PhysicalProduct("orange", "this is an orange", 10, 
        10, 10, "gift for teachers");
        PhysicalProduct physicalProduct2 = new PhysicalProduct("apple", "this is an apple", 20, 
        20, 20, null);
        DigitalProduct digitalProduct1 = new DigitalProduct("eBook", "this is an online book", 30, 
        30, "gift for students");
        DigitalProduct digitalProduct2 = new DigitalProduct("eVoucher", "this is an eVoucher", 40, 
        40, null);

        ProductManager.getProductList().add(physicalProduct1);
        ProductManager.getProductList().add(physicalProduct2);
        ProductManager.getProductList().add(digitalProduct1);
        ProductManager.getProductList().add(digitalProduct2);

        ShoppingCart shoppingCart1 = new ShoppingCart();

        CartManager.getCartList().add(shoppingCart1);
        shoppingCart1.addItem("orange");
        shoppingCart1.addItem("apple");

        assertEquals(shoppingCart1.getTotalWeight(), 30.00);
    }
}