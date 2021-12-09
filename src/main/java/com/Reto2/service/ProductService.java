package com.Reto2.service;

import com.Reto2.model.Product;
import com.Reto2.repository.ProductRepository;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Product createProduct(Product product) {
        return productRepository.createProduct(product);
    }

    public Product updateProduct(Product product) {

        if (product.getReference() != null) {
            Optional<Product> productoDb = productRepository.getProductById(product.getReference());
            if (!productoDb.isEmpty()) {
                if (product.getBrand()!= null) {
                    productoDb.get().setBrand(product.getBrand());
                }
                if (product.getCategory() != null) {
                    productoDb.get().setCategory(product.getCategory());
                }
                if (product.getDescription() != null) {
                    productoDb.get().setDescription(product.getDescription());
                }
                
                if (product.getPrice() != 0.0) {
                    productoDb.get().setPrice(product.getPrice());
                }
                productRepository.updateProduct(productoDb.get());
                return productoDb.get();
            } else {
                return product;
            }
        } else {
            return product;
        }
    }

    public List< Product> getAllProduct() {
        return this.productRepository.getAllProduct();
    }

    public Optional<Product> getProductById(String productId) {

        return this.productRepository.getProductById(productId);
    }

    public List<Product> productosxPrecio(double price) {
        return productRepository.productosxPrecio(price);
    }

    public List<Product> productosxCategoria(String categoria) {
        return productRepository.productosxCategoria(categoria);
    }

    public List<String> getAllCategories() {
        List<String> categoryList = new ArrayList<>();
        MongoCollection mongoCollection = mongoTemplate.getCollection("productos");
        DistinctIterable distinctIterable = mongoCollection.distinct("categoria", String.class);
        MongoCursor cursor = distinctIterable.iterator();
        while (cursor.hasNext()) {
            String category = (String) cursor.next();
            categoryList.add(category);
        }
        
        return categoryList;
    }
    
    public boolean deleteProduct(String productId){
        Boolean d=getProductById(productId).map(product -> {
            productRepository.deleteProduct(product);
            return true;
        }).orElse(false);
        return d;
     }
}
