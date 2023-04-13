package com.example;

/**
 * @author Hoang Quoc Bao - s3926050
 */

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import org.junit.jupiter.api.*;

/**
 * Unit test for Product.
 */
public class ProductTest
{
    /**
     * Test for getProductList()
     */
    @Test
    public void canGetProductList()
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

        HashSet<Product> products = new HashSet<>();
        products.add(physicalProduct1);
        products.add(physicalProduct2);
        products.add(digitalProduct1);
        products.add(digitalProduct2);

        assertEquals(ProductManager.getProductList(), products);
    }

    /**
     * Test for getProductByName function
     */
    @Test
    public void canGetProductByName()
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

        assertEquals(ProductManager.getProductByName("orange"), physicalProduct1);
    }

    /**
     * Test for getProductByName function - null return expected
     */
    @Test
    public void canGetProductByNameNull()
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

        assertEquals(ProductManager.getProductByName("avocado"), null);
    }

    /**
     * Test for toString() of Product
     */
    @Test
    public void canGetProductToString()
    {
        PhysicalProduct physicalProduct1 = new PhysicalProduct("orange", "this is an orange", 10, 
        10, 10, "gift for teachers");

        assertEquals(physicalProduct1.toString(), "PHYSICAL - orange");
    }

    /**
     * Test for getProductType()
     */
    @Test
    public void canGetProductType()
    {
        PhysicalProduct physicalProduct1 = new PhysicalProduct("orange", "this is an orange", 10, 
        10, 10, "gift for teachers");

        assertEquals(physicalProduct1.getProductType(), "PHYSICAL");
    }

    /**
     * Test for getDisplayInfo()
     */
    @Test
    public void canGetProductDisplayInfo()
    {
        PhysicalProduct physicalProduct1 = new PhysicalProduct("orange", "this is an orange", 10, 
        10, 10, "gift for teachers");

        assertEquals(physicalProduct1.getDisplayInfo(), 
        "orange  [type= PHYSICAL, description= this is an orange, quantity= 10, price= 10.00, weight= 10.00, message= gift for teachers]");
    }

    /**
     * Test for getWeight()
     */
    @Test
    public void canGetPhysicalProductWeight()
    {
        PhysicalProduct physicalProduct1 = new PhysicalProduct("orange", "this is an orange", 10, 
        10, 10, "gift for teachers");

        assertEquals(physicalProduct1.getWeight(), 10.00);
    }

    /**
     * Test for increaseQuantity()
     */
    @Test
    public void canIncreaseQuantity()
    {
        PhysicalProduct physicalProduct1 = new PhysicalProduct("orange", "this is an orange", 10, 
        10, 10, "gift for teachers");

        physicalProduct1.increaseQuantity(10);

        assertTrue(physicalProduct1.getAvailableQuantity() == 20);
    }

    /**
     * Test for decreaseQuantity()
     */
    @Test
    public void canDecreaseQuantity()
    {
        PhysicalProduct physicalProduct1 = new PhysicalProduct("orange", "this is an orange", 10, 
        10, 10, "gift for teachers");

        physicalProduct1.decreaseQuantity(10);

        assertTrue(physicalProduct1.getAvailableQuantity() == 0);
    }
}
