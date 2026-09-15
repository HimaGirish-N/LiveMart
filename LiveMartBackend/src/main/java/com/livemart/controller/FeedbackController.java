package com.livemart.controller;

import com.livemart.model.Feedback;
import com.livemart.model.Product;
import com.livemart.model.User;
import com.livemart.service.FeedbackService;
import com.livemart.repository.ProductRepository;
import com.livemart.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "*")
public class FeedbackController {

    private final FeedbackService service;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    public FeedbackController(
            FeedbackService service,
            UserRepository userRepo,
            ProductRepository productRepo
    ) {
        this.service = service;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    @PostMapping("/add")
    public ResponseEntity<Feedback> addFeedback(@RequestBody Map<String, Object> body) {

        int rating = Integer.parseInt(body.get("rating").toString());
        String comment = body.get("comment").toString();

        // Nested customer object → extract ID
        Map<String, Object> customerObj = (Map<String, Object>) body.get("customer");
        Long customerId = Long.valueOf(customerObj.get("id").toString());

        // Nested product object → extract ID
        Map<String, Object> productObj = (Map<String, Object>) body.get("product");
        Long productId = Long.valueOf(productObj.get("id").toString());

        User customer = userRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Feedback fb = new Feedback();
        fb.setRating(rating);
        fb.setComment(comment);
        fb.setCustomer(customer);
        fb.setProduct(product);

        return ResponseEntity.ok(service.addFeedback(fb));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<Feedback>> getFeedback(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFeedbackByProduct(id));
    }
}
