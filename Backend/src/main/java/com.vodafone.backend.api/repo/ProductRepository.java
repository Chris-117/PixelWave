package com.vodafone.backend.api.repo;

import com.vodafone.backend.api.domain.Product;
import com.vodafone.backend.api.domain.ProductType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT P FROM Product P WHERE P.name LIKE %:keyword%")

    List<Product> searchProducts(@Param("keyword") String keyword);

    List<Product> getAllByProductType(ProductType productType);
}
