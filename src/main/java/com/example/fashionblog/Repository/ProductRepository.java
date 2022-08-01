package com.example.fashionblog.Repository;

import com.example.fashionblog.Enum.ProductCategory;
import com.example.fashionblog.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductName(String productName);

}
