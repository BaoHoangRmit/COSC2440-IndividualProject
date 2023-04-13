package com.example;

/**
 * @author Hoang Quoc Bao - s3926050
 */

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * class to manage system functions
 */
public class SystemManager {

    // functions

    /**
     * Menu printing functions
     */
    public void printMainMenu() {
        System.out.println("----- WELCOME TO OUR STORE -----");
        System.out.println("1/ Product Management");
        System.out.println("2/ Cart Management");
        System.out.println("3/ Exit Applicaation");
    }

    public void printProductMenu() {
        System.out.println("----- LIST OF PRODUCT -----");
        ProductManager.getAllProducts();
        System.out.println("----- OPTION LIST -----");
        System.out.println("1/ Create new product");
        System.out.println("2/ Edit a product");
        System.out.println("3/ Back to Main Menu");
    }

    public void printCartMenu() {
        System.out.println("----- YOUR SHOPPING CART LIST -----");
        CartManager.getAllCarts();
        System.out.println("----- OPTION LIST -----");
        System.out.println("1/ Create a new empty cart");
        System.out.println("2/ Manage one cart");
        System.out.println("3/ Back to Main Menu");
    }

    public void printOneCartMenu(ShoppingCart currentCart) {
        System.out.printf("\n----- CART %s-----\n", currentCart.getCartId());
        System.out.println(currentCart);
        System.out.printf("Total weight: %,.2f\n", currentCart.getTotalWeight());
        System.out.printf("Total price: %,.2f\n", currentCart.cartAmount());
        System.out.println("----- OPTION LIST -----");
        System.out.println("1/ Add new product to cart");
        System.out.println("2/ Remove a product from cart");
        System.out.println("3/ View product list");
        System.out.println("4/ Back to Cart Menu");
    }

    /**
     * Validate input functions
     */

    /**
     * Validate Main Menu Input
     */
    public void checkMainMenuInput() {
        boolean hasRun = false;

        mainMenu: do {
            if (hasRun) {
                System.out.print("Enter your number option again: ");
            } else {
                System.out.print("Enter your number option: ");
            }

            try {
                Scanner scanner = new Scanner(System.in);
                int inputOption = Integer.parseInt(scanner.nextLine());

                switch (inputOption) {
                    // choose to Product Menu
                    case 1:
                        hasRun = false;

                        System.out.println("Going to Product Menu ...");
                        Thread.sleep(1500);
                        System.out.println();

                        printProductMenu();
                        checkProductMenuInput();
                        break;
                    
                    // choose to Cart Menu
                    case 2:
                        hasRun = false;

                        System.out.println("Going to Cart Menu ...");
                        Thread.sleep(1500);
                        System.out.println();

                        printCartMenu();
                        checkCartMenuInput();
                        break;
                    
                    // choose to exit application
                    case 3:
                        System.out.println("Exiting the application ...");
                        Thread.sleep(1500);

                        System.out.println("Exit Sucessfully!");
                        System.exit(0);
                    default:
                        throw new InputMismatchException("Please enter a valid input!");
                }

            } catch (InputMismatchException e) {
                hasRun = true;
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                hasRun = true;
                System.out.println("Please enter a valid input!");
            } catch (InterruptedException i) {
                System.out.println("Simultaneously using the system is banned!");
                System.out.println("Logging out ...");
                System.exit(0);
            }
        } while (true);
    }

    /**
     * Validate Product Menu Input
     */
    public void checkProductMenuInput() {
        boolean hasRun = false;

        productMenu: do {
            if (hasRun) {
                System.out.print("Enter your number option again: ");
            } else {
                System.out.print("Enter your number option: ");
            }

            try {
                Scanner scanner = new Scanner(System.in);
                int inputOption = Integer.parseInt(scanner.nextLine());

                switch (inputOption) {
                    // choose create new product
                    case 1:
                        hasRun = false;
                        try {
                            ProductManager.createnewProduct();
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        } catch (InterruptedException i) {
                            System.out.println("Simultaneously using the system is banned!");
                            System.out.println("Logging out ...");
                            System.exit(0);
                        }

                        System.out.println();
                        printProductMenu();
                        break;

                    // choose edit a product
                    case 2:
                        hasRun = false;

                        if (ProductManager.getProductList() == null || ProductManager.getProductList().isEmpty()) {
                            System.out.println("There are no products in the store!");
                            System.out.println("Returning to Product Menu ...");
                            Thread.sleep(1500);
                        } else {
                            System.out.print("Please enter product name: ");
                            String inputProductName = scanner.nextLine();
                            Product editedProduct = ProductManager.getProductByName(inputProductName);

                            // if the input product name is not found
                            if (editedProduct == null) {
                                System.out.println("Please enter the right product name!");
                                System.out.println("Returning to Product Menu ...");
                                Thread.sleep(1500);
                            } else {
                                try {
                                    ProductManager.editProduct(inputProductName);

                                    // update total weight of all cart after editing any product
                                    for (ShoppingCart shoppingCart : CartManager.getCartList()) {
                                        shoppingCart.updateTotalWeight();
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println(e.getMessage());
                                } catch (InterruptedException i) {
                                    System.out.println("Simultaneously using the system is banned!");
                                    System.out.println("Logging out ...");
                                    System.exit(0);
                                }
                            }
                        } 
                        
                        System.out.println();
                        printProductMenu();
                        break;

                    // choose back to Main Menu
                    case 3:
                        hasRun = false;
                        System.out.println("Returning to Main Menu ...");
                        Thread.sleep(1500);
                        System.out.println();
                        printMainMenu();
                        break productMenu;
                    default:
                        throw new InputMismatchException("Please enter a valid input!");
                }

            } catch (InputMismatchException e) {
                hasRun = true;
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                hasRun = true;
                System.out.println("Please enter a valid input!");
            } catch (InterruptedException i) {
                System.out.println("Simultaneously using the system is banned!");
                System.out.println("Logging out ...");
                System.exit(0);
            }
        } while (true);
    }

    /**
     * Validate Cart Menu Input
     */
    public void checkCartMenuInput() {
        boolean hasRun = false;
        
        cartMenu: do {
            if (hasRun) {
                System.out.print("Enter your number option again: ");
            } else {
                System.out.print("Enter your number option: ");
            }

            try {
                Scanner scanner = new Scanner(System.in);
                int inputOption = Integer.parseInt(scanner.nextLine());

                switch (inputOption) {
                    case 1:
                        hasRun = false;

                        System.out.println("Creating a new empty cart ...");
                        Thread.sleep(1500);

                        CartManager.createNewCart();

                        System.out.println();
                        printCartMenu();
                        break;
                    case 2:
                        hasRun = false;

                        System.out.print("Please enter cart ID: ");
                        String inputCartId = scanner.nextLine();

                        ShoppingCart currentCart = CartManager.getCartById(inputCartId);

                        // if there are no cart with the given id
                        if (currentCart == null) {
                            System.out.println("Please enter the right cart ID!");
                            System.out.println("Returning to Cart Menu ...");
                            Thread.sleep(1500);
                            
                            System.out.println();
                            printCartMenu();
                            break;
                        } else {
                            System.out.printf("Directing to Cart %s management screen ...\n", inputCartId);
                            Thread.sleep(1500);

                            System.out.println();
                            printOneCartMenu(currentCart);
                            checkOneCartMenuInput(currentCart);
                        }  
                        break;
                    case 3:
                        hasRun = false;
                        System.out.println("Returning to Main Menu ...");
                        Thread.sleep(1500);
                        System.out.println();
                        printMainMenu();
                        break cartMenu;
                    default:
                        throw new InputMismatchException("Please enter a valid input!");
                }

            } catch (InputMismatchException e) {
                hasRun = true;
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                hasRun = true;
                System.out.println("Please enter a valid input!");
            } catch (InterruptedException i) {
                System.out.println("Simultaneously using the system is banned!");
                System.out.println("Logging out ...");
                System.exit(0);
            }
        } while (true);
    }

    /**
     * Validate One Cart Menu Input
     * @param currentCart the current viewing cart
     */
    public void checkOneCartMenuInput(ShoppingCart currentCart) {
        boolean hasRun = false;

        oneCartMenu: do {
            if (hasRun) {
                System.out.print("Enter your number option again: ");
            } else {
                System.out.print("Enter your number option: ");
            }

            try {
                Scanner scanner = new Scanner(System.in);
                int inputOption = Integer.parseInt(scanner.nextLine());

                switch (inputOption) {
                    case 1:
                        hasRun = false;
                        System.out.print("Enter the product's name: ");
                        String inputProductName = scanner.nextLine();

                        if (inputProductName == null) {
                            System.out.println("Please enter a product name!");
                        } else if (currentCart.getProductListCart().contains(inputProductName)) {
                            System.out.println("Cannot add two similar product to the same cart!");
                        } else {
                            Product inputProduct = ProductManager.getProductByName(inputProductName);

                            if (inputProduct == null) {
                                System.out.printf("There are no product with the name: %s\n", inputProductName);
                            } else if (inputProduct.getAvailableQuantity() == 0) {
                                System.out.printf("The product %s cannot be added to this cart as it is out of stock\n", 
                                inputProductName);   
                            } else {
                                System.out.println(inputProduct.getDisplayInfo());

                                addItemToCart: do {
                                    System.out.print("Confirm adding this product to this cart? (y/n): ");
                                    String inputConfirm = scanner.nextLine();
    
                                    if (inputConfirm.equals("y")) {
                                        System.out.printf("Adding %s to Cart %s ...\n", 
                                        inputProductName, currentCart.getCartId());
                                        Thread.sleep(1500);

                                        if (currentCart.addItem(inputProductName)) {
                                            System.out.println("Add item to cart successfully!");
                                        } else {
                                            System.out.println("Adding item to cart failed!");
                                        }

                                        break addItemToCart;
                                    } else if (inputConfirm.equals("n")) {         
                                        break addItemToCart;
                                    } else {
                                        // if enter invalid option -> loop
                                        System.out.println("Please enter the right option!");
                                    }
                                } while (true);
                            }
                        }

                        System.out.println();
                        System.out.printf("Returning to Cart %s Menu ...\n", currentCart.getCartId());
                        Thread.sleep(1500);

                        System.out.println();
                        printOneCartMenu(currentCart);
                        break;
                    case 2:
                        hasRun = false;

                        System.out.print("Enter the product's name: ");
                        String inputProductNameRemove = scanner.nextLine();

                        if (inputProductNameRemove == null) {
                            System.out.println("Please enter a product name!");
                        } else if (!(currentCart.getProductListCart().contains(inputProductNameRemove))) {
                            System.out.println("This product cannot be removed as it was not contained in the cart");
                        } else {
                            Product inputProductRemove = ProductManager.getProductByName(inputProductNameRemove);

                            if (inputProductRemove == null) {
                                System.out.printf("There are no product with the name: %s\n", inputProductNameRemove);
                            } else {
                                System.out.println(inputProductRemove.getDisplayInfo());

                                removeItemOutOfCart: do {
                                    System.out.print("Confirm removing this product out of this cart? (y/n): ");
                                    String inputConfirm = scanner.nextLine();

                                    if (inputConfirm.equals("y")) {
                                        System.out.printf("Removing %s out of Cart %s ...\n", 
                                        inputProductNameRemove, currentCart.getCartId());
                                        Thread.sleep(1500);

                                        if (currentCart.removeItem(inputProductNameRemove)) {
                                            System.out.println("Remove item out of cart successfully!");
                                        } else {
                                            System.out.println("Remove item out of cart failed!");
                                        }

                                        break removeItemOutOfCart;
                                    } else if (inputConfirm.equals("n")) {         
                                        break removeItemOutOfCart;
                                    } else {
                                        System.out.println("Please enter the right option!");
                                    }
                                } while (true);
                            }
                        }

                        System.out.println();
                        System.out.printf("Returning to Cart %s Menu ...\n", currentCart.getCartId());
                        Thread.sleep(1500);

                        System.out.println();
                        printOneCartMenu(currentCart);
                        break;
                    case 3:
                        hasRun = false;

                        System.out.println("Loading Product list ...");
                        Thread.sleep(1500);

                        System.out.println();
                        System.out.println("----- LIST OF PRODUCT -----");
                        ProductManager.getAllProducts();
                        System.out.println("----- LIST OF PRODUCT -----");
                        System.out.println();
                        
                        printOneCartMenu(currentCart);
                        break;
                    case 4:
                        hasRun = false;

                        System.out.println("Returning to Cart Menu ...");
                        Thread.sleep(1500);

                        System.out.println();
                        printCartMenu();

                        break oneCartMenu;
                    default:
                        throw new InputMismatchException("Please enter a valid input!");
                }
            } catch (InputMismatchException e) {
                hasRun = true;
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                hasRun = true;
                System.out.println("Please enter a valid input!");
            } catch (InterruptedException i) {
                System.out.println("Simultaneously using the system is banned!");
                System.out.println("Logging out ...");
                System.exit(0);
            }
        } while (true);
    }
}
