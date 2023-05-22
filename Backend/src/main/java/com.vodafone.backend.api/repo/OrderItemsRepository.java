package com.vodafone.backend.api.repo;

import com.vodafone.backend.api.domain.OrderItems;
import com.vodafone.backend.api.domain.Review;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {
    List<OrderItems> findByCustomerId(int id);
}
