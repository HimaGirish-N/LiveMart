// package com.livemart.repository;

// import com.livemart.model.Order;
// import org.springframework.data.jpa.repository.JpaRepository;
// import java.util.List;

// public interface OrderRepository extends JpaRepository<Order, Long> {
//     List<Order> findByCustomerId(Long customerId);
// }


package com.livemart.repository;

import com.livemart.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // FIX: correct field name customer.id
    List<Order> findByCustomer_Id(Long customerId);
}
