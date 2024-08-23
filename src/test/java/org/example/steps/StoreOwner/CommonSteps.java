package org.example.steps.StoreOwner;

import io.cucumber.java.en.Given;
import org.example.database.ProductDataBase;
import org.example.reciepes.Product;


public class CommonSteps {



    @Given("the following products exist:")
    public void the_following_products_exist(io.cucumber.datatable.DataTable dataTable) {
        ProductDataBase.getProducts().clear();
        dataTable.asMaps().forEach(row -> {
            int id = Integer.parseInt(row.get("id"));
            String name = row.get("name");
            double price = Double.parseDouble(row.get("price"));
            int sellCounter = Integer.parseInt(row.get("sellCounter"));
            ProductDataBase.addProduct(new Product(id, name, price, sellCounter));
        });
    }
}