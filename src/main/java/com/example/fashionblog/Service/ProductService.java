package com.example.fashionblog.Service;

import com.example.fashionblog.DTO.ProductDTO;
import com.example.fashionblog.Model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    String createProduct(ProductDTO product);
    List<Product> viewAllProduct();
    Product viewProductById(Long id);
}
