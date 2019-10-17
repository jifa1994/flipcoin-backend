package com.yaogo.flipcoin.controllers.yelp;

import com.yaogo.flipcoin.clients.ReviewClient;
import com.yaogo.flipcoin.models.Review;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/businesses")
@CrossOrigin
public class ReviewController {

    private ReviewClient reviewClient;

    public ReviewController(ReviewClient reviewClient) {
        this.reviewClient = reviewClient;
    }

    @GetMapping("/{businessId}/reviews")
    public Iterable<Review> getBusinessReviews(@PathVariable String businessId) throws Exception{
        return reviewClient.sendGet(businessId);
    }
}
