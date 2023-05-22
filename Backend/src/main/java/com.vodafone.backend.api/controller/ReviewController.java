package com.vodafone.backend.api.controller;

import com.vodafone.backend.api.domain.Review;
import com.vodafone.backend.api.service.ReviewService;
import com.vodafone.backend.api.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewServiceImpl reviewService;

    @Autowired
    public ReviewController(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable int id) {
        Review review = reviewService.getReviewById(id);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        Review savedReview = reviewService.addReview(review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable int id, @RequestBody Review review) {
        Review updatedReview = reviewService.updateReview(id, review);
        if (updatedReview != null) {
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable int id) {
        boolean deleted = reviewService.deleteReview(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
