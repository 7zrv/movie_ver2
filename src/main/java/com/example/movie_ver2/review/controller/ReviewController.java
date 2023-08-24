package com.example.movie_ver2.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewController {

    @GetMapping("/create/review")
    public String createReview() {
        return "create-review";
    }

    @GetMapping("/modify/review/{reviewId}")
    public String modifyReview() {
        return "modify-review";
    }

    @GetMapping("/my/review")
    public String myReviewList() {
        return "my-review";
    }

    @GetMapping("/movie/review")
    public String reviewList() {
        return "movie-review";
    }
}
