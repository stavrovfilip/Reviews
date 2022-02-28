package mk.ukim.finki.reviewsproject.service;

import mk.ukim.finki.reviewsproject.model.Review;

import java.util.List;

public interface ReviewsService {

    //List<Review> findAll();

    List<Review> filter(String orderRating, int rating, String orderDate, String text);
}
