
package com.Reto2.repository.crud;

import com.Reto2.model.Product;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductCrudRepository extends MongoRepository<Product, String>{
    
    public List<Product> findByPriceLessThanEqual(double price);

    public List<Product> findByCategory(String category);
    
}
