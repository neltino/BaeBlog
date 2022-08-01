package com.example.fashionblog.DTO;

import com.example.fashionblog.Enum.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @NotBlank
    private String productName;
    @NotBlank
    private ProductCategory category;
    @NotBlank
    private BigInteger price;


}
