package com.vodafone.backend.api.domain;

import java.sql.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    private int orderItemsId;

    @Column(name = "date")

    private Date date;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "total_price")
    private String totalPrice;

    // Default constructor
    public Order() {
    }

    // Parameterised constructor
    public Order(int orderItemsId, Date date, String paymentStatus, int customerId, String deliveryAddress, String totalPrice) {
        this.orderItemsId = orderItemsId;
        this.date = date;
        this.paymentStatus = paymentStatus;
        this.customerId = customerId;
        this.deliveryAddress = deliveryAddress;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderItemsId() {
        return orderItemsId;
    }

    public void setOrderItemsId(int orderItemsId) {
        this.orderItemsId = orderItemsId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
