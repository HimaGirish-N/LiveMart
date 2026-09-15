// package com.livemart.service;

// import com.livemart.model.Product;
// import com.livemart.repository.ProductRepository;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class ProductService {

//     private final ProductRepository productRepo;

//     public ProductService(ProductRepository productRepo){
//         this.productRepo = productRepo;
//     }

//     public Product addProduct(Product product){
//         return productRepo.save(product);
//     }

//     public List<Product> getAllProducts(){
//         return productRepo.findAll();
//     }

//     // ⭐ ADD THIS
//     public Product getProductById(Long id){
//         return productRepo.findById(id).orElse(null);
//     }
// }
package com.livemart.service;

import com.livemart.model.Product;
import com.livemart.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo){
        this.productRepo = productRepo;
    }

    // Add Product
    public Product addProduct(Product product){
        return productRepo.save(product);
    }

    // Update Product
    public Product updateProduct(Long id, Product updated){
        Product existing = productRepo.findById(id).orElse(null);
        if (existing == null) return null;

        // -------- DO NOT REMOVE RELATIONS --------
        updated.setId(existing.getId());
        updated.setRetailer(existing.getRetailer());
        updated.setWholesaler(existing.getWholesaler());

        // -------- UPDATE BASIC FIELDS ONLY --------
        existing.setName(updated.getName());
        existing.setCategory(updated.getCategory());
        existing.setPrice(updated.getPrice());
        existing.setQuantity(updated.getQuantity());
        existing.setStockStatus(updated.getStockStatus());
        existing.setRegion(updated.getRegion());
        existing.setImage(updated.getImage());

        return productRepo.save(existing);
    }

    // Delete Product
    public boolean deleteProduct(Long id){
        if (productRepo.existsById(id)){
            productRepo.deleteById(id);
            return true;
        }
        return false;
    }

    // Get all products
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    // Get product by id
    public Product getProductById(Long id){
        return productRepo.findById(id).orElse(null);
    }

    // Retailer products
    public List<Product> getProductsByRetailer(Long retailerId){
        return productRepo.findByRetailer_Id(retailerId);
    }

    // Wholesaler products
    public List<Product> getProductsByWholesaler(Long wholesalerId){
        return productRepo.findByWholesaler_Id(wholesalerId);
    }

    // Region filter
    public List<Product> getProductsByRegion(String region){
        return productRepo.findByRegion(region);
    }

    // Search
    public List<Product> searchProducts(String text){
        return productRepo.findByNameContainingIgnoreCase(text);
    }

    // Category filter
    public List<Product> getProductsByCategory(String category){
        return productRepo.findByCategory(category);
    }
}
