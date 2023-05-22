package com.vodafone.backend.api.domain;

import com.vodafone.backend.api.service.ProductServiceImpl;
import javax.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_items_id")
    private int orderItemsId;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_id", nullable = false)
    private int productId;

    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false, length = 45)
    private double price;


    // Default constructor
    public OrderItems() {
    }

    // Parameterised constructor
    public OrderItems(int orderItemsId, int productId, int customerId, int quantity, double price) {
        this.orderItemsId = orderItemsId;
        this.productId = productId;
        this.customerId = customerId;
        this.quantity = quantity;
        this.price = price;

    }

    // Getters and Setters

    public int getOrderItemsId() {
        return orderItemsId;
    }

    public void setOrderItemsId(int orderItemsId) {
        this.orderItemsId = orderItemsId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
