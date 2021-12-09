package com.Reto2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cleaningproducts")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    @Id
    private String reference;
    private String brand;
    private String category;
    private String material;
    private String presentacion;
    private String description;
    private double price;
    private boolean availability = true;
    private int quantity;
    private String photography;
    
}
