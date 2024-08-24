package org.example.statecontroller.admin;

import org.example.database.ProductDataBase;
import org.example.database.UserDataBase;
import org.example.reciepes.Product;
import org.example.account.Person;
import org.example.statecontroller.Context;
import org.example.statecontroller.ExitState;
import org.example.statecontroller.State;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class ManageContentState implements State {

    private Context context;
    private  Logger logger = Logger.getLogger(ManageContentState.class.getName());
    private  String command;
    private String name;
    private double price;
    private int sellCounter;
    private int id;
    private String username;
    private String post;
    public ManageContentState(Context context) {
        this.context = context;
    }

    public void setCommand(String command) {
        this.command = command;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
      this.price = price;
    }
    public void setSellCounter(int sellCounter) {
        this.sellCounter = sellCounter;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPost(String post) {
        this.post = post;
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
        if(!context.isTest())
         command = input.nextLine();
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
                if(context.isTest())
                    context.setCurrentState(new ExitState());
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
        if(context.isTest())
            setCommand("exit");
        context.handleInput();
    }

    private void addProduct(Scanner input) {
        logger.info("Enter product name:");
        if(!context.isTest())
            name = input.nextLine();
        logger.info("Enter product price:");
        if(!context.isTest())
            price = Double.parseDouble(input.nextLine());
        logger.info("Enter product sell counter:");
        if(!context.isTest())
            sellCounter = Integer.parseInt(input.nextLine());

        Product product = new Product(ProductDataBase.getProducts().size() + 1, name, price, sellCounter);
        ProductDataBase.addProduct(product);
        logger.info("Product added successfully.");
        if(context.isTest())
            setCommand("exit");
        context.handleInput();
    }

    private void editProduct(Scanner input) {
        logger.info("Enter product ID to edit:");
        if(!context.isTest())
            id  = Integer.parseInt(input.nextLine());
        Product product = ProductDataBase.getProduct(id);

        if (product != null) {
            logger.info("Enter new product name:");
            if(!context.isTest())
                name = input.nextLine();
            logger.info("Enter new product price:");
            if(!context.isTest())
              price = Double.parseDouble(input.nextLine());

            ProductDataBase.editProduct(id, name, price);
            logger.info("Product updated successfully.");
        } else {
            logger.info("Product not found.");
        }
        if(context.isTest())
            setCommand("exit");
        context.handleInput();
    }

    private void deleteProduct(Scanner input) {
        logger.info("Enter product ID to delete:");
        if(!context.isTest())
            id = Integer.parseInt(input.nextLine());
        ProductDataBase.removeProduct(id);
        logger.info("Product deleted successfully.");
        if(context.isTest())
            setCommand("exit");
        context.handleInput();
    }

    public void viewPosts() {
        List<Person> users = UserDataBase.getDb();
        for (Person user : users) {
            logger.info(user.getUsername() + "'s posts:");
            for (String p : user.getPosts()) {
                logger.info(p);
            }
        }
        if(context.isTest())
            setCommand("exit");
        context.handleInput();
    }

    private void addPost(Scanner input) {
        logger.info("Enter username to add post:");
        if(!context.isTest())
           username = input.nextLine();
        Person user = UserDataBase.getPersonByUsername(username);

        if (user != null) {
            logger.info("Enter post content:");
            if(!context.isTest())
              post = input.nextLine();
            user.addPost(post);
            logger.info("Post added successfully.");
        } else {
            logger.info("User not found.");
        }
        if (context.isTest())
            setCommand("exit");
        context.handleInput();
    }

    private void deletePost(Scanner input) {
        logger.info("Enter username to delete post:");
        if(!context.isTest())
            username = input.nextLine();
        Person user = UserDataBase.getPersonByUsername(username);

        if (user != null) {
            logger.info("Enter post content to delete:");
            if(!context.isTest())
              post = input.nextLine();
            if (user.getPosts().remove(post)) {
                logger.info("Post deleted successfully.");
            } else {
                logger.info("Post not found.");
            }
        } else {
            logger.info("User not found.");
        }
        if (context.isTest())
            setCommand("exit");
        context.handleInput();
    }
}