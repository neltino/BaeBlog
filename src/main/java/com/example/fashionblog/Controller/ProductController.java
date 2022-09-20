package com.example.fashionblog.Controller;

import com.example.fashionblog.DTO.ProductDTO;
import com.example.fashionblog.Model.Product;
import com.example.fashionblog.Service.ServiceImpl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @PostMapping("/create-product")
    @Operation(
            tags = {"Product"},
            summary = "view all products",
            description = "This endpoint creates new products",
            responses = {
                    @ApiResponse(responseCode = "200", description = "product has been added successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "406", description = "user error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "403", description = "only Admin can add product", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))

            }
    )
    public String createProduct(@RequestBody ProductDTO product){
        return productService.createProduct(product);
    }
    @GetMapping("/view-all-product")
    @Operation(
            tags = {"Product"},
            summary = "view all products",
            description = "This endpoint creates new products",
            responses = {
                    @ApiResponse(responseCode = "200", description = "list of product", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "403", description = "only Admin can view product", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))

            }
    )
    public List<Product> viewAllProduct(){
        return productService.viewAllProduct();
    }
    @GetMapping("/view-product-by-id")
    @Operation(
            tags = {"Product"},
            summary = "view all products by Id",
            description = "This endpoint returns a product by Id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "returns a product", content = @Content(schema = @Schema(implementation = Product.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "406", description = "user error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "403", description = "only Admin can add product", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),

            }
    )
    public Product viewProductById(@RequestParam Long id){
        return productService.viewProductById(id);
    }
}
