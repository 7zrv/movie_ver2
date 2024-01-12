package com.example.movie_ver2.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewController {

    @GetMapping("/create/review")
    public String createReview() {
        return "reviewHtml/create-review";
    }

    @GetMapping("/modify/review/{reviewId}")
    public String modifyReview() {
        return "reviewHtml/modify-review";
    }

    @GetMapping("/my/review")
    public String myReviewList() {
        return "reviewHtml/my-review";
    }

    @GetMapping("/movie/review")
    public String reviewList() {
        return "reviewHtml/movie-review";
    }
}
