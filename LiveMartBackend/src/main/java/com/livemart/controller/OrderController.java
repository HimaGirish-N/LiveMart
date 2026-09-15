// package com.livemart.controller;

// import com.livemart.model.Order;
// import com.livemart.service.OrderService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/orders")
// public class OrderController {

//     private final OrderService orderService;

//     public OrderController(OrderService orderService){
//         this.orderService = orderService;
//     }

//     @PostMapping("/place")
//     public ResponseEntity<Order> placeOrder(@RequestBody Order order){
//         return ResponseEntity.ok(orderService.placeOrder(order));
//     }

//     @GetMapping("/customer/{customerId}")
//     public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable Long customerId){
//         return ResponseEntity.ok(orderService.getOrdersByCustomerId(customerId));
//     }
    
// }
package com.livemart.controller;

import com.livemart.model.Order;
import com.livemart.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    // Place new order
    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order){
        return ResponseEntity.ok(orderService.placeOrder(order));
    }

    // Get all orders by customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable Long customerId){
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(customerId));
    }

    // ⭐ NEW — Cancel order
    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        boolean cancelled = orderService.cancelOrder(orderId);

        if (cancelled) {
            return ResponseEntity.ok("Order cancelled successfully");
        } else {
            return ResponseEntity.badRequest().body("Order not found or already cancelled");
        }
    }
}
