// package com.livemart.model;

// import javax.persistence.*;
// import javax.persistence.Entity;
// import javax.persistence.Id;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Column;
// import javax.persistence.Table;
// import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;
// import javax.persistence.JoinColumn;

// import lombok.Data;

// import java.util.List;

// @Data
// @Entity
// @Table(name = "orders")
// public class Order {
//     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     private User customer;

//     @OneToMany(cascade = CascadeType.ALL)
//     private List<OrderItem> products;

//     private double totalPrice;

//     private String status; // Pending / Processing / Delivered
//     private String paymentMode; // Online / Offline
// }

package com.livemart.model;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders") // Avoid reserved keyword "order"
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Customer who placed the order
    @ManyToOne
    @JoinColumn(name = "customer_id") // FK column in orders table
    private User customer;

    // List of products included in the order
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id") // FK in order_items table
    private List<OrderItem> products;

    private double totalPrice;

    private String status; // Pending / Processing / Delivered

    private String paymentMode; // Online / Offline

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<OrderItem> getProducts() {
        return products;
    }

    public void setProducts(List<OrderItem> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
}
