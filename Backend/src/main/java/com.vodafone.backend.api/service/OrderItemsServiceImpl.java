package com.vodafone.backend.api.service;

import com.vodafone.backend.api.domain.OrderItems;
import com.vodafone.backend.api.domain.Product;
import com.vodafone.backend.api.repo.OrderItemsRepository;
import com.vodafone.backend.api.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemsServiceImpl implements OrderItemsService{
    private final OrderItemsRepository orderItemsRepository;

    private final ProductRepository productRepository;

    @Autowired
    public OrderItemsServiceImpl(OrderItemsRepository orderItemsRepository, ProductRepository productRepository) {
        this.orderItemsRepository = orderItemsRepository;
        this.productRepository = productRepository;
    }

    public List<OrderItems> getAllOrderItems() {
        return orderItemsRepository.findAll();
    }

    public OrderItems getOrderItemsById(int id) {
        return orderItemsRepository.findById(id).orElse(null);
    }

    public List<OrderItems> getOrderItemsByCustomerId(int customerId) {
        List<OrderItems> orderItems = orderItemsRepository.findByCustomerId(customerId);

        for (OrderItems orderItem : orderItems) {
            int productId = orderItem.getProductId();
            Product product = productRepository.findById(productId).orElse(null);

            if (product != null) {
                String productName = product.getName();
                orderItem.setName(productName);
            }
        }

        return orderItems;
    }

    public OrderItems addOrderItems(OrderItems orderItems) {
        orderItems.setName(productRepository.getById(orderItems.getProductId()).getName());
        return orderItemsRepository.save(orderItems);
    }

    public OrderItems updateOrderItems(int id, OrderItems updatedOrderItems) {
        OrderItems existingOrderItems = orderItemsRepository.findById(id).orElse(null);

        if (existingOrderItems != null) {
            existingOrderItems.setProductId(updatedOrderItems.getProductId());
            existingOrderItems.setQuantity(updatedOrderItems.getQuantity());
            existingOrderItems.setPrice(updatedOrderItems.getPrice());
            existingOrderItems.setName(updatedOrderItems.getName());
            return orderItemsRepository.save(existingOrderItems);
        } else {
            return null;
        }
    }

    public boolean deleteOrderItems(int id) {
        OrderItems orderItems = orderItemsRepository.findById(id).orElse(null);
        if (orderItems != null) {
            orderItemsRepository.delete(orderItems);
            return true;
        } else {
            return false;
        }
    }

    // Calculating the total cost for an order
    public double total(int customerId) {
        List<OrderItems> orderItemsList = orderItemsRepository.findByCustomerId(customerId);
        double total = 0.0;

        for (OrderItems orderItems : orderItemsList) {
            int quantity = orderItems.getQuantity();
            double price = orderItems.getPrice();
            total += price * quantity;
        }

        return total;
    }
}
