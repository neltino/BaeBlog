package com.example.fashionblog.Controller;

import com.example.fashionblog.DTO.ProductDTO;
import com.example.fashionblog.Model.Product;
import com.example.fashionblog.Service.ServiceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @PostMapping("/create-product")
    public String createProduct(@RequestBody ProductDTO product){
        return productService.createProduct(product);
    }
    @GetMapping("/view-all-product")
    public List<Product> viewAllProduct(){
        return productService.viewAllProduct();
    }
    @GetMapping("/view-product-by-id")
    public Product viewProductById(@RequestParam Long id){
        return productService.viewProductById(id);
    }
}
