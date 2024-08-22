package org.example.StateController.Admin;

import org.example.Database.ProductDataBase;
import org.example.Database.UserDataBase;
import org.example.Reciepes.Product;
import org.example.Account.Person;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.State;

import java.util.List;
import java.util.Scanner;

public class ManageContentState implements State {

    private Context context;

    public ManageContentState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("Manage Content\n" +
                "1. View Products   2. Add Product\n"+
                "3. Edit Product    4. Delete Product\n"+
                "5. View Posts      6. Add Post\n"+
                "7. Delete Post     8.Back\n");

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
                System.out.println("Invalid command");
                context.handleInput();
                break;
        }
    }



    public void viewProducts() {
        List<Product> products = ProductDataBase.getProducts();
        for (Product product : products) {
            System.out.println(product.getId() + ": " + product.getName() + " - $" + product.getPrice());
        }
        context.handleInput();
    }

    private void addProduct(Scanner input) {
        System.out.println("Enter product name:");
        String name = input.nextLine();
        System.out.println("Enter product price:");
        double price = Double.parseDouble(input.nextLine());
        System.out.println("Enter product sell counter:");
        int sellCounter = Integer.parseInt(input.nextLine());

        Product product = new Product(ProductDataBase.getProducts().size() + 1, name, price, sellCounter);
        ProductDataBase.addProduct(product);
        System.out.println("Product added successfully.");
        context.handleInput();
    }

    private void editProduct(Scanner input) {
        System.out.println("Enter product ID to edit:");
        int id = Integer.parseInt(input.nextLine());
        Product product = ProductDataBase.getProduct(id);

        if (product != null) {
            System.out.println("Enter new product name:");
            String name = input.nextLine();
            System.out.println("Enter new product price:");
            double price = Double.parseDouble(input.nextLine());

            ProductDataBase.editProduct(id, name, price);
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
        context.handleInput();
    }

    private void deleteProduct(Scanner input) {
        System.out.println("Enter product ID to delete:");
        int id = Integer.parseInt(input.nextLine());
        ProductDataBase.removeProduct(id);
        System.out.println("Product deleted successfully.");
        context.handleInput();
    }

    public void viewPosts() {
        List<Person> users = UserDataBase.getDb();
        for (Person user : users) {
            System.out.println(user.getUsername() + "'s posts:");
            for (String post : user.getPosts()) {
                System.out.println(post);
            }
        }
        context.handleInput();
    }

    private void addPost(Scanner input) {
        System.out.println("Enter username to add post:");
        String username = input.nextLine();
        Person user = UserDataBase.getPersonByUsername(username);

        if (user != null) {
            System.out.println("Enter post content:");
            String post = input.nextLine();
            user.addPost(post);
            System.out.println("Post added successfully.");
        } else {
            System.out.println("User not found.");
        }
        context.handleInput();
    }

    private void deletePost(Scanner input) {
        System.out.println("Enter username to delete post:");
        String username = input.nextLine();
        Person user = UserDataBase.getPersonByUsername(username);

        if (user != null) {
            System.out.println("Enter post content to delete:");
            String post = input.nextLine();
            if (user.getPosts().remove(post)) {
                System.out.println("Post deleted successfully.");
            } else {
                System.out.println("Post not found.");
            }
        } else {
            System.out.println("User not found.");
        }
        context.handleInput();
    }
}