package com.vodafone.backend.api.service;

import com.vodafone.backend.api.domain.Product;
import com.vodafone.backend.api.domain.ProductType;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> searchProducts(String keyword);

    List<Product> getProductsCategory(List<ProductType> keyword);

    Product getProductById(int id);

    Product addProduct(Product product) throws IOException;

    Product updateProduct(int id, Product product);

    boolean deleteProduct(int id);
}
