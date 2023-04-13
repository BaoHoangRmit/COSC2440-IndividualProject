package com.example;

/**
 * @author Hoang Quoc Bao - s3926050
 */

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * class to manage product functions
 */
public class ProductManager {
    /**
     * product manager attributes
     */

     // list of product
    private static HashSet<Product> productList = new HashSet<>();

    // list of product name
    private static HashSet<String> productNameList = new HashSet<>();  

    // getter
    public static HashSet<Product> getProductList() {
        return productList;
    }

    public static HashSet<String> getProductNameList() {
        return productNameList;
    }

    // functions

    /**
     * print all products
     * <p>
     * If the list of product is empty -> print message
     * else  print all product
     * </p>
     */
    public static void getAllProducts() {
        if (productList.isEmpty()) {
            System.out.println("There are no products in the store!");
        } else {
            for (Product product : productList) {
                System.out.printf("%s\n", product.getDisplayInfo());
            }
        }
    }

    /**
     * get product by name
     * <p>
     * Given a name String
     * Return the sproduct with the given name
     * If no results were found -> return null
     * </p>
     * @param searchName the search product name
     * @return product if found / null if not found
     */
    public static Product getProductByName(String searchName) {
        if (productList != null) {
            for (Product product : productList) {
                if (searchName.equals(product.getName())) return product;
            }
        }
        return null;
    }

    /**
     * create a new product
     * <p>
     * Print and checking the input in creating product process
     * If any exception -> throw, catch, and process expection
     * If success -> print success message
     * </p>
     * @throws InterruptedException if the Thread is interrupted
     */
    public static void createnewProduct() throws InterruptedException {

        System.out.println("Select product type you want to create: ");
        System.out.println("1/ Physical Product");
        System.out.println("2/ Digital Product");

        boolean hasRun = false;

        createProduct: do {
            
            // print another message if this loop has been run
            if (hasRun) {
                System.out.print("Enter your number option again: ");
            } else {
                System.out.print("Enter your number option: ");
            }

            try {
                Scanner scanner = new Scanner(System.in);

                // get the choice for product type
                int inputChoice = Integer.parseInt(scanner.nextLine());

                if (inputChoice == 1) {
                    System.out.println("----- CREATE NEW PHYSICAL PRODUCT -----");
                } else if (inputChoice == 2) {
                    System.out.println("----- CREATE NEW DIGITAL PRODUCT -----");
                } else {
                    hasRun = true;
                    throw new NumberFormatException("Please enter a valid input!");
                }
        
                System.out.println("Enter product name: ");
                String inputName = scanner.nextLine();
                if (productNameList.contains(inputName)) {
                    throw new InputMismatchException("Products in the store cannot have the same name!");
                }
        
                System.out.println("Enter product description: ");
                String inputDesc = scanner.nextLine();
        
                System.out.println("Enter product quantity: ");
                int inputQuantity = Integer.parseInt(scanner.nextLine());
                if (inputQuantity < 0) {
                    throw new InputMismatchException("Products in the store cannot have negative quantity!");
                }
        
                System.out.println("Enter product price: ");
                double inputPrice = Double.parseDouble(scanner.nextLine());
                if (inputPrice < 0) {
                    throw new InputMismatchException("Products in the store cannot have negative price!");
                }

                System.out.println("Enter product message (can be skipped by press Enter): ");
                String inputMessage = scanner.nextLine();
        
                // if the product type choice is physical -> allow weight creation
                if (inputChoice == 1) {
                    System.out.println("Enter product weight: ");
                    double inputWeight = Double.parseDouble(scanner.nextLine());
                    if (inputWeight <= 0) {
                        throw new InputMismatchException("Physical products must have a positive amount of weight!");
                    }
        
                    // create a new physcial product and update product list
                    PhysicalProduct newProduct = new PhysicalProduct(inputName, inputDesc, inputQuantity, inputPrice, inputWeight, inputMessage);
                    productList.add(newProduct);

                    // update product name list
                    productNameList.add(inputName);
                    // physicalProductList.add(newProduct);
                } else {
                    DigitalProduct newProduct = new DigitalProduct(inputName, inputDesc, inputQuantity, inputPrice, inputMessage);
                    productList.add(newProduct);
                    productNameList.add(inputName);
                    // digitalProductList.add(newProduct);
                }
        
                System.out.println("Creating product ...");
                Thread.sleep(1500);
                System.out.println("Create new product successfully!");
                System.out.println();
                break;
            } catch (InputMismatchException e) {
                // if this expection is catched -> print error message, terminate the process, and return to previous screen
                hasRun = true;
                System.out.println(e.getMessage());

                System.out.println("Create new product failed!");
                System.out.println("Ending create product process ...");
                Thread.sleep(1500);

                System.out.println("Returning to Product Menu ...");
                Thread.sleep(1500);

                break createProduct;
            } catch (NumberFormatException e) {
                hasRun = true;
                System.out.println("Please enter a valid input!");
            }           
        } while (true);
    }

    /**
     * edit an existing product
     * <p>
     * Given the product name
     * edit the product with the given name
     * Print and checking the input in edit product process
     * If any exception -> throw, catch, and process expection
     * If success -> print success message
     * 
     * not allow to edit name & product type
     * </p>
     * @param editedProductName 
     * @throws InterruptedException if the Thread is interrupted
     */
    public static void editProduct(String editedProductName) throws InterruptedException {
        Product editedProduct = getProductByName(editedProductName);

        updateProduct: do {
            try {
                Scanner scanner = new Scanner(System.in);
        
                System.out.println("Enter new product description: ");
                String inputDesc = scanner.nextLine();
        
                System.out.println("Enter new product quantity: ");
                int inputQuantity = Integer.parseInt(scanner.nextLine());
                if (inputQuantity < 0) {
                    throw new InputMismatchException("Products in the store cannot have negative quantity!");
                }
        
                System.out.println("Enter product price: ");
                double inputPrice = Double.parseDouble(scanner.nextLine());
                if (inputPrice < 0) {
                    throw new InputMismatchException("Products in the store cannot have negative price!");
                }

                System.out.println("Enter new product message (can be skipped by press Enter): ");
                String inputMessage = scanner.nextLine();
        
                if (editedProduct.getProductType().equals("PHYSICAL")) {
                    System.out.println("Enter new product weight: ");
                    double inputWeight = Double.parseDouble(scanner.nextLine());

                    if (inputWeight <= 0) {
                        throw new InputMismatchException("Physical products must have a positive amount of weight!");
                    }

                    editedProduct.setDescription(inputDesc);
                    editedProduct.setAvailableQuantity(inputQuantity);
                    editedProduct.setPrice(inputPrice);
                    editedProduct.setMessage(inputMessage);
                    PhysicalProduct physicalProduct = (PhysicalProduct) editedProduct;
                    physicalProduct.setWeight(inputWeight);
                } else {
                    editedProduct.setDescription(inputDesc);
                    editedProduct.setAvailableQuantity(inputQuantity);
                    editedProduct.setPrice(inputPrice);
                    editedProduct.setMessage(inputMessage);
                }        
        
                System.out.println("Editing product ...");
                Thread.sleep(1500);
                System.out.println("Edit product successfully!");
                System.out.println();
                break;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                System.out.println("Edit product failed!");
                System.out.println("Ending edit product process ...");
                Thread.sleep(1500);
                System.out.println("Returning to Product Menu ...");
                Thread.sleep(1500);
                break updateProduct;
            }  
        } while (true);
    }
}
