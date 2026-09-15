package com.livemart.repository;

import com.livemart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByRetailer_Id(Long retailerId);

    List<Product> findByWholesaler_Id(Long wholesalerId);

    List<Product> findByRegion(String region);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByCategory(String category);
}
