package com.example.fashionblog.Model;

import com.example.fashionblog.Enum.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String productName;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "TEXT", nullable = false)
    private ProductCategory category;
    @Column(nullable = false)
    private BigInteger price;
    @Column(nullable = false)
    private String imgLink;


}
