package org.example.statecontroller.admin;

import org.example.database.ProductDataBase;
import org.example.database.UserDataBase;
import org.example.reciepes.Product;
import org.example.account.Person;
import org.example.statecontroller.Context;
import org.example.statecontroller.State;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class ManageContentState implements State {

    private Context context;
    private  Logger logger = Logger.getLogger(ManageContentState.class.getName());

    public ManageContentState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner input = new Scanner(System.in);
        String textBlock = """
                     <html>
                          <body>
                                <tag>
                                  Manage Content<br>
                                  1. View Products   2. Add Product<br>
                                  3. Edit Product    4. Delete Product<br>
                                  5. View Posts      6. Add Post<br>
                                  7. Delete Post     8.Back
                                  </tag>
                          </body>
                    </html>""";
        logger.info(textBlock);
        String command = input.nextLine();
        context.filterState(command);

        switch (command) {
            case "1":
                viewProducts();
                break;
            case "2":
                addProduct(input);
                break;
            case "3":
                editProduct(input);
                break;
            case "4":
                deleteProduct(input);
                break;
            case "5":
                viewPosts();
                break;
            case "6":
                addPost(input);
                break;
            case "7":
                deletePost(input);
                break;
            case "8":
                context.setCurrentState(new AdminState(context));
                context.handleInput();
                break;
            default:
                logger.info("Invalid command");
                context.handleInput();
                break;
        }
    }



    public void viewProducts() {
        List<Product> products = ProductDataBase.getProducts();
        for (Product product : products) {
            logger.info(product.getId() + ": " + product.getName() + " - $" + product.getPrice());
        }
        context.handleInput();
    }

    private void addProduct(Scanner input) {
        logger.info("Enter product name:");
        String name = input.nextLine();
        logger.info("Enter product price:");
        double price = Double.parseDouble(input.nextLine());
        logger.info("Enter product sell counter:");
        int sellCounter = Integer.parseInt(input.nextLine());

        Product product = new Product(ProductDataBase.getProducts().size() + 1, name, price, sellCounter);
        ProductDataBase.addProduct(product);
        logger.info("Product added successfully.");
        context.handleInput();
    }

    private void editProduct(Scanner input) {
        logger.info("Enter product ID to edit:");
        int id = Integer.parseInt(input.nextLine());
        Product product = ProductDataBase.getProduct(id);

        if (product != null) {
            logger.info("Enter new product name:");
            String name = input.nextLine();
            logger.info("Enter new product price:");
            double price = Double.parseDouble(input.nextLine());

            ProductDataBase.editProduct(id, name, price);
            logger.info("Product updated successfully.");
        } else {
            logger.info("Product not found.");
        }
        context.handleInput();
    }

    private void deleteProduct(Scanner input) {
        logger.info("Enter product ID to delete:");
        int id = Integer.parseInt(input.nextLine());
        ProductDataBase.removeProduct(id);
        logger.info("Product deleted successfully.");
        context.handleInput();
    }

    public void viewPosts() {
        List<Person> users = UserDataBase.getDb();
        for (Person user : users) {
            logger.info(user.getUsername() + "'s posts:");
            for (String post : user.getPosts()) {
                logger.info(post);
            }
        }
        context.handleInput();
    }

    private void addPost(Scanner input) {
        logger.info("Enter username to add post:");
        String username = input.nextLine();
        Person user = UserDataBase.getPersonByUsername(username);

        if (user != null) {
            logger.info("Enter post content:");
            String post = input.nextLine();
            user.addPost(post);
            logger.info("Post added successfully.");
        } else {
            logger.info("User not found.");
        }
        context.handleInput();
    }

    private void deletePost(Scanner input) {
        logger.info("Enter username to delete post:");
        String username = input.nextLine();
        Person user = UserDataBase.getPersonByUsername(username);

        if (user != null) {
            logger.info("Enter post content to delete:");
            String post = input.nextLine();
            if (user.getPosts().remove(post)) {
                logger.info("Post deleted successfully.");
            } else {
                logger.info("Post not found.");
            }
        } else {
            logger.info("User not found.");
        }
        context.handleInput();
    }
}