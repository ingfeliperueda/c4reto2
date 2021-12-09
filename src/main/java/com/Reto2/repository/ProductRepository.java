package com.Reto2.repository;

import com.Reto2.model.Product;
import com.Reto2.repository.crud.ProductCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    @Autowired
    private ProductCrudRepository productCrudRepository;

    public Product createProduct(Product product) {
        return productCrudRepository.save(product);
    }

    public void updateProduct(Product product) {
        productCrudRepository.save(product);
    }

    public List< Product> getAllProduct() {
        return productCrudRepository.findAll();
    }

    public Optional<Product> getProductById(String productId) {
        return productCrudRepository.findById(productId);
    }

    public void deleteProduct(Product product) {
        productCrudRepository.delete(product);
    }  
    
    public List<Product> productosxPrecio(double price){
        return productCrudRepository.findByPriceLessThanEqual(price);
    }
    
    public List<Product> productosxCategoria(String category){
        return productCrudRepository.findByCategory(category);
    }
}
