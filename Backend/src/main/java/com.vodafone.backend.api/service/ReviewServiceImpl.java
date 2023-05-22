package com.vodafone.backend.api.service;

import com.vodafone.backend.api.domain.Review;
import com.vodafone.backend.api.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * Retrieves all reviews from the database.
     *
     * @return List of reviews
     */
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    /**
     * Retrieves a review by its ID from the database.
     *
     * @param id ID of the review
     * @return Review object if found, null otherwise
     */
    public Review getReviewById(int id) {
        return reviewRepository.findById(id).orElse(null);
    }

    /**
     * Adds a new review to the database.
     *
     * @param review Review object to be added
     * @return Saved review object
     */
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    /**
     * Updates an existing review in the database.
     *
     * @param id      ID of the review to be updated
     * @param review  Updated review object
     * @return Updated review object if found, null otherwise
     */
    public Review updateReview(int id, Review review) {
        Review existingReview = reviewRepository.findById(id).orElse(null);
        if (existingReview != null) {
            // Update the properties of the existing review
            existingReview.setProductId(review.getProductId());
            existingReview.setTitle(review.getTitle());
            existingReview.setCustomerId(review.getCustomerId());
            existingReview.setRating(review.getRating());
            existingReview.setDescription(review.getDescription());

            return reviewRepository.save(existingReview);
        } else {
            return null;
        }
    }

    /**
     * Deletes a review from the database.
     *
     * @param id ID of the review to be deleted
     * @return True if the review was deleted successfully, false otherwise
     */
    public boolean deleteReview(int id) {
        Review review = reviewRepository.findById(id).orElse(null);
        if (review != null) {
            reviewRepository.delete(review);
            return true;
        } else {
            return false;
        }
    }
}
