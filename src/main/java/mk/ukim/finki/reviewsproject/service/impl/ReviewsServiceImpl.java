package mk.ukim.finki.reviewsproject.service.impl;

import mk.ukim.finki.reviewsproject.model.Review;
import mk.ukim.finki.reviewsproject.repository.ReviewsRepository;
import mk.ukim.finki.reviewsproject.service.ReviewsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsServiceImpl implements ReviewsService {

    private final ReviewsRepository reviewsRepository;


    public ReviewsServiceImpl(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }


    @Override
    public List<Review> findAll() {
        return this.reviewsRepository.findAll();
    }

    @Override
    public List<Review> filter(String orderRating, int rating, String orderDate, String text) {
        return this.reviewsRepository.filter(orderRating, rating, orderDate, text);
    }
}
