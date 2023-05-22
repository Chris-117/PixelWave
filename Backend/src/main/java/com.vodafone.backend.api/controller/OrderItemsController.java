package com.vodafone.backend.api.controller;
import com.vodafone.backend.api.domain.OrderItems;
import com.vodafone.backend.api.service.OrderItemsServiceImpl;
import com.vodafone.backend.api.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/orderItems")
public class OrderItemsController {
    private final OrderItemsServiceImpl orderItemsService;

    @Autowired
    public OrderItemsController(OrderItemsServiceImpl orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    @GetMapping
    public ResponseEntity<List<OrderItems>> getAllOrderItems() {
        List<OrderItems> orderItems = orderItemsService.getAllOrderItems();
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItems> getOrderItemsById(@PathVariable int id) {
        OrderItems orderItems = orderItemsService.getOrderItemsById(id);
        if (orderItems != null) {
            return new ResponseEntity<>(orderItems, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("total/{id}")
    public ResponseEntity<Double> totalPrice(@PathVariable int id) {
        double total = orderItemsService.total(id);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderItems>> getOrderItemsByCustomerId(@PathVariable int id) {
        List<OrderItems> orderItemsList = orderItemsService.getOrderItemsByCustomerId(id);
        if (!orderItemsList.isEmpty()) {
            return new ResponseEntity<>(orderItemsList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<OrderItems> addOrderItems(@RequestBody OrderItems orderItems) {
        OrderItems savedOrderItems = orderItemsService.addOrderItems(orderItems);
        return new ResponseEntity<>(savedOrderItems, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItems> updateOrderItems(@PathVariable int id, @RequestBody OrderItems orderItems) {
        OrderItems updatedOrderItems = orderItemsService.updateOrderItems(id, orderItems);
        if (updatedOrderItems != null) {
            return new ResponseEntity<>(updatedOrderItems, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItems(@PathVariable int id) {
        boolean deleted = orderItemsService.deleteOrderItems(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
