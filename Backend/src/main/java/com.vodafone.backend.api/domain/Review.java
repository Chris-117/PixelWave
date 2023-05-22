package com.vodafone.backend.api.domain;
import javax.persistence.*;

@Entity
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int reviewId;

    @Column(name = "product_id", nullable = false)
    private int productId;

    @Column(name = "title")
    private String title;

    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    // Default constructor
    public Review() {
    }

    // Parameterised constructor
    public Review(int productId, String title, String customerId, int rating, String description) {
        this.productId = productId;
        this.title = title;
        this.customerId = customerId;
        this.rating = rating;
        this.description = description;
    }

    // Getters and Setters

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}