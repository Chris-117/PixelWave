package com.vodafone.backend.api.service;

import com.vodafone.backend.api.domain.Customer;
import com.vodafone.backend.api.domain.Order;
import com.vodafone.backend.api.repo.CustomerRepository;
import com.vodafone.backend.api.repo.OrderRepository;
import com.vodafone.backend.api.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    private final EmailService emailService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository,
                            ProductRepository productRepository, EmailService emailService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.emailService = emailService;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order createOrder(Order order) {
        // Get the customer ID from the order
        int customerId = order.getCustomerId();
        orderRepository.save(order);
        // Retrieve the customer details from the customer repository
        Customer customer = customerRepository.findById(customerId)
          .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        String subject = "Order Confirmation";
        String text = "Dear customer, your order has been confirmed. Order ID: " + order.getOrderId()
          + "\nDate: " + order.getDate()
          + "\nPayment Status: " + order.getPaymentStatus()
          + "\nDelivery Address: " + order.getDeliveryAddress()
          + "\nTotal Price: " + order.getTotalPrice();
        emailService.sendEmail(customer.getEmail(), subject, text);
        return order;
    }

    public Order updateOrder(int id, Order updatedOrder) {
        Order existingOrder = orderRepository.findById(id).orElse(null);
        if (existingOrder != null) {
            existingOrder.setOrderItemsId(updatedOrder.getOrderItemsId());
            existingOrder.setDate(updatedOrder.getDate());
            existingOrder.setPaymentStatus(updatedOrder.getPaymentStatus());
            existingOrder.setCustomerId(updatedOrder.getCustomerId());
            existingOrder.setDeliveryAddress(updatedOrder.getDeliveryAddress());
            existingOrder.setTotalPrice(updatedOrder.getTotalPrice());
            return orderRepository.save(existingOrder);
        } else {
            return null;
        }
    }

    public boolean deleteOrder(int id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            orderRepository.delete(order);
            return true;
        } else {
            return false;
        }
    }
}
