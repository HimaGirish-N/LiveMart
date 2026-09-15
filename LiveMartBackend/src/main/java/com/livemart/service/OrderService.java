// package com.livemart.service;

// import com.livemart.model.Order;
// import com.livemart.model.OrderItem;
// import com.livemart.repository.OrderRepository;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class OrderService {
//     private final OrderRepository orderRepository;

//     public OrderService(OrderRepository orderRepository){
//         this.orderRepository = orderRepository;
//     }

//     public Order placeOrder(Order order){
//         return orderRepository.save(order);
//     }

//     public List<Order> getOrdersByCustomerId(Long customerId){
//         return orderRepository.findByCustomerId(customerId);
//     }

//     public Optional<Order> getOrderById(Long id){
//         return orderRepository.findById(id);
//     }
// }
package com.livemart.service;

import com.livemart.model.Order;
import com.livemart.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Order placeOrder(Order order){
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByCustomerId(Long customerId){
        return orderRepository.findByCustomer_Id(customerId);
    }

    public Optional<Order> getOrderById(Long id){
        return orderRepository.findById(id);
    }

    // ⭐ NEW — Cancel order logic
    public boolean cancelOrder(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (!optionalOrder.isPresent()) {
            return false; // order not found
        }

        Order order = optionalOrder.get();

        // Already cancelled check
        if ("Cancelled".equalsIgnoreCase(order.getStatus())) {
            return false;
        }

        order.setStatus("Cancelled");
        orderRepository.save(order);

        return true;
    }
}
