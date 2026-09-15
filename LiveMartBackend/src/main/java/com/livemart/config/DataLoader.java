package com.livemart.config;

import com.livemart.model.Product;
import com.livemart.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductRepository repo;

    public DataLoader(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) throws Exception {

        if (repo.count() == 0) {

            Product p1 = new Product();
            p1.setName("Apple");
            p1.setCategory("Fruits");
            p1.setPrice(120);
            p1.setQuantity(50);
            p1.setStockStatus("In Stock");
            p1.setRegion("Local");
            p1.setImage("");

            Product p2 = new Product();
            p2.setName("Milk 1L");
            p2.setCategory("Grocery");
            p2.setPrice(60);
            p2.setQuantity(30);
            p2.setStockStatus("In Stock");
            p2.setRegion("Local");
            p2.setImage("");

            Product p3 = new Product();
            p3.setName("Laptop Bag");
            p3.setCategory("Electronics");
            p3.setPrice(999);
            p3.setQuantity(20);
            p3.setStockStatus("In Stock");
            p3.setRegion("India");
            p3.setImage("");

            Product p4 = new Product();
            p4.setName("T-Shirt");
            p4.setCategory("Clothes");
            p4.setPrice(299);
            p4.setQuantity(100);
            p4.setStockStatus("In Stock");
            p4.setRegion("India");
            p4.setImage("");

            Product p5 = new Product();
            p5.setName("Banana");
            p5.setCategory("Fruits");
            p5.setPrice(40);
            p5.setQuantity(80);
            p5.setStockStatus("In Stock");
            p5.setRegion("Local");
            p5.setImage("");

            repo.save(p1);
            repo.save(p2);
            repo.save(p3);
            repo.save(p4);
            repo.save(p5);

            System.out.println("✔ Dummy product data loaded!");
        }
    }
}
