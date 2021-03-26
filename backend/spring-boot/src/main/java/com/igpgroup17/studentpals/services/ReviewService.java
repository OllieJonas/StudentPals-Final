package com.igpgroup17.studentpals.services;


import com.igpgroup17.studentpals.models.Review;

import java.util.List;

public interface ReviewService {

    Review createReview(Review review);

    Review readReview(String id);

    Review updateReview(Review review);

    String deleteReview(String id);

    List<Review> getReviewsFor(String id);
}
