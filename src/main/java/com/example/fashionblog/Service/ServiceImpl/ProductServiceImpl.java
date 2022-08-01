package com.example.fashionblog.Service.ServiceImpl;

import com.example.fashionblog.CustomException.BadRequestException;
import com.example.fashionblog.CustomException.ForbiddenException;
import com.example.fashionblog.CustomException.NotAcceptableException;
import com.example.fashionblog.CustomException.NotFoundException;
import com.example.fashionblog.DTO.ProductDTO;
import com.example.fashionblog.Model.Product;
import com.example.fashionblog.Repository.ProductRepository;
import com.example.fashionblog.Service.ProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Data
public class ProductServiceImpl implements ProductService {
   private final HttpSession httpSession;
    @Autowired
    ProductRepository productRepository;
    @Override
    public String createProduct(ProductDTO product) {
        System.out.println(httpSession.getAttribute("user_Id"));

        if(httpSession.getAttribute("user_Id") == null){
            throw new BadRequestException("Please log in first");
        }
        if(!httpSession.getAttribute("user_Role").toString().equals("ADMIN")){
            throw new ForbiddenException("Sorry, only ADMIN can create product!");
        }
        if(productRepository.findByProductName(product.getProductName()) != null){
            throw new NotAcceptableException("Sorry, this product already exists!");
        }
        Product prod = new Product();
        prod.setProductName(product.getProductName());
        prod.setCategory(product.getCategory());
        prod.setPrice(product.getPrice());
        productRepository.save(prod);
        return "Product '" + product.getProductName() + "', has been added successfully!";
    }

    @Override
    public List<Product> viewAllProduct() {
        if(!httpSession.getAttribute("user_Role").toString().equals("ADMIN")){
            throw new ForbiddenException("Only ADMIN can view product catalog!");
        }
        List<Product> list = productRepository.findAll();
        if(list.size() == 0){
            throw new NotFoundException("Sorry, no available product to view!");
        }
        return list;
    }

    @Override
    public Product viewProductById(Long id) {
        if(!httpSession.getAttribute("user_Role").toString().equals("ADMIN")){
            throw new ForbiddenException("Only Admin can view products by ID");
        }
        return productRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Product with Id '" + id + "' not found!"));
    }


}
