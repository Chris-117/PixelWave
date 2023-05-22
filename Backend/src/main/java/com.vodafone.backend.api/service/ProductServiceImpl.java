package com.vodafone.backend.api.service;

import com.vodafone.backend.api.domain.Product;
import com.vodafone.backend.api.domain.ProductType;
import com.vodafone.backend.api.repo.ProductRepository;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> searchProducts(String keyword) {
        keyword = keyword.toLowerCase();
        return productRepository.searchProducts(keyword);
    }

    public List<Product> getProductsCategory(List<ProductType> keywords) {
        List<Product> products = new ArrayList<>();
        for (ProductType keyword : keywords) {
            List<Product> matchedProducts = productRepository.getAllByProductType(keyword);
            System.out.println("here");
            products.addAll(matchedProducts);
        }
        return products;
    }
    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }


    public Product addProduct(Product product) throws IOException {
        return productRepository.save(product);
    }

    public Product updateProduct(int id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setImage(updatedProduct.getImage());
            existingProduct.setStock(updatedProduct.getStock());
            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }

    public boolean deleteProduct(int id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            productRepository.delete(product);
            return true;
        } else {
            return false;
        }
    }
}

