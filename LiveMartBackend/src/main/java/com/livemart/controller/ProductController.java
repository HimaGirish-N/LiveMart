// package com.livemart.controller;

// import com.livemart.model.Product;
// import com.livemart.service.ProductService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/products")

// public class ProductController {

//     private final ProductService productService;

//     public ProductController(ProductService productService){
//         this.productService = productService;
//     }

//     // ADD PRODUCT
//     @PostMapping
//     public ResponseEntity<Product> addProduct(@RequestBody Product product){
//         return ResponseEntity.ok(productService.addProduct(product));
//     }

//     // GET ALL PRODUCTS
//     @GetMapping
//     public ResponseEntity<List<Product>> getAllProducts(){
//         return ResponseEntity.ok(productService.getAllProducts());
//     }

//     // ⭐ GET PRODUCT BY ID (IMPORTANT)
//     @GetMapping("/{id}")
//     public ResponseEntity<Product> getProductById(@PathVariable Long id){
//         Product p = productService.getProductById(id);
//         return ResponseEntity.ok(p);
//     }
// }
    
package com.livemart.controller;

import com.livemart.model.Product;
import com.livemart.model.User;
import com.livemart.service.ProductService;
import com.livemart.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")   // IMPORTANT
public class ProductController {

    private final ProductService productService;
    private final UserRepository userRepository;

    public ProductController(ProductService productService, UserRepository userRepository){
        this.productService = productService;
        this.userRepository = userRepository;
    }

    // ----------------------------------------------------
    // ADD PRODUCT (Retailer OR Wholesaler)
    // ----------------------------------------------------
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(
            @RequestBody Product product,
            @RequestParam(required = false) Long retailerId,
            @RequestParam(required = false) Long wholesalerId
    ) {

        // Retailer adding product
        if (retailerId != null) {
            User retailer = userRepository.findById(retailerId)
                    .orElseThrow(() -> new RuntimeException("Retailer not found"));
            product.setRetailer(retailer);
        }

        // Wholesaler adding product
        if (wholesalerId != null) {
            User wholesaler = userRepository.findById(wholesalerId)
                    .orElseThrow(() -> new RuntimeException("Wholesaler not found"));
            product.setWholesaler(wholesaler);
        }

        // Safety check: ensure at least one ID present
        if (retailerId == null && wholesalerId == null) {
            throw new RuntimeException("retailerId or wholesalerId must be provided.");
        }

        return ResponseEntity.ok(productService.addProduct(product));
    }

    // ----------------------------------------------------
    // COMMON ENDPOINTS (Customer/Retailer/Wholesaler)
    // ----------------------------------------------------
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // ----------------------------------------------------
    // RETAILER FEATURES
    // ----------------------------------------------------
    @GetMapping("/retailer/{retailerId}")
    public ResponseEntity<List<Product>> getProductsByRetailer(@PathVariable Long retailerId){
        return ResponseEntity.ok(productService.getProductsByRetailer(retailerId));
    }

    // ----------------------------------------------------
    // WHOLESALER FEATURES
    // ----------------------------------------------------
    @GetMapping("/wholesaler/{wholesalerId}")
    public ResponseEntity<List<Product>> getProductsByWholesaler(@PathVariable Long wholesalerId){
        return ResponseEntity.ok(productService.getProductsByWholesaler(wholesalerId));
    }

    // ----------------------------------------------------
    // UPDATE / DELETE
    // ----------------------------------------------------
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product updatedProduct) {

        Product updated = productService.updateProduct(id, updatedProduct);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        boolean deleted = productService.deleteProduct(id);
        return deleted
                ? ResponseEntity.ok("Product deleted successfully.")
                : ResponseEntity.badRequest().body("Product not found.");
    }

    // ----------------------------------------------------
    // SEARCH + FILTER FEATURES
    // ----------------------------------------------------
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String text){
        return ResponseEntity.ok(productService.searchProducts(text));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable String category){
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @GetMapping("/region/{region}")
    public ResponseEntity<List<Product>> getByRegion(@PathVariable String region){
        return ResponseEntity.ok(productService.getProductsByRegion(region));
    }
}
