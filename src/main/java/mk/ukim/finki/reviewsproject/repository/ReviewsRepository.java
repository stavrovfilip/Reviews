package mk.ukim.finki.reviewsproject.repository;

import mk.ukim.finki.reviewsproject.model.DataHolder;
import mk.ukim.finki.reviewsproject.model.Review;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ReviewsRepository {

    /*public List<Review> findAll() {
        return DataHolder.reviewlist;
    }*/

    public List<Review> filter(String orderRating, int rating, String orderDate, String text) {
        List<Review> reviews = DataHolder.reviewlist.stream().filter(r -> r.getRating() >= rating).collect(Collectors.toList());

        //See if reviewText matters
        if (text.equals("yes")) {
            if (orderRating.equals("lowest") && orderDate.equals("oldest")) {
                //First the reviews that have reviewText, from lowest rating to highest, and from oldest to newest according to date
                List<Review> reviews1 = reviews.stream().filter(r -> r.getReviewText().equals("")).collect(Collectors.toList());
                List<Review> reviews2 = reviews.stream().filter(r -> !r.getReviewText().equals("")).collect(Collectors.toList());
                Comparator<Review> orderByAllThree = Comparator.comparing(Review::getReviewText)
                        .thenComparing(Review::getRating)
                        .thenComparing(Review::getReviewCreatedOnDate);
                reviews2 = reviews2.stream().sorted(orderByAllThree).collect(Collectors.toList());
                reviews1 = reviews1.stream().sorted(orderByAllThree).collect(Collectors.toList());
                reviews2.addAll(reviews1);
                return reviews2;
            } else if (orderRating.equals("lowest") && orderDate.equals("newest")) {
                //First the reviews that have reviewText, from lowest rating to highest, and from newest to oldest according to date
                List<Review> reviews1 = reviews.stream().filter(r -> r.getReviewText().equals("")).collect(Collectors.toList());
                List<Review> reviews2 = reviews.stream().filter(r -> !r.getReviewText().equals("")).collect(Collectors.toList());
                Comparator<Review> orderByAllThree = (Comparator.comparing(Review::getReviewText)
                        .thenComparing(Review::getRating))
                        .thenComparing(Comparator.comparing(Review::getReviewCreatedOnDate).reversed());
                reviews2 = reviews2.stream().sorted(orderByAllThree).collect(Collectors.toList());
                reviews1 = reviews1.stream().sorted(orderByAllThree).collect(Collectors.toList());
                reviews2.addAll(reviews1);
                return reviews2;
            } else if (orderRating.equals("highest") && orderDate.equals("oldest")) {
                //First the reviews that have reviewText, from highest rating to lowest, and from oldest to newest according to date
                Comparator<Review> orderByAllThree = Comparator.comparing(Review::getReviewText)
                        .thenComparing(Review::getRating).reversed()
                        .thenComparing(Review::getReviewCreatedOnDate);
                return reviews.stream().sorted(orderByAllThree).collect(Collectors.toList());
            } else {
                //First the reviews that have reviewText, from highest rating to lowest, and from newest to oldest according to date
                Comparator<Review> orderByAllThree = Comparator.comparing(Review::getReviewText)
                        .thenComparing(Review::getRating)
                        .thenComparing(Review::getReviewCreatedOnDate).reversed();
                return reviews.stream().sorted(orderByAllThree).collect(Collectors.toList());
            }
        }
        else {
            if (orderRating.equals("lowest") && orderDate.equals("oldest")){
                //Doesn't matter if reviews have reviewText or not; from lowest to highest rating and then from oldest to newest
                Comparator<Review> orderByRatingAndDate = Comparator.comparing(Review::getRating)
                        .thenComparing(Review::getReviewCreatedOnDate);
                return reviews.stream().sorted(orderByRatingAndDate).collect(Collectors.toList());
            }
            else if (orderRating.equals("lowest") && orderDate.equals("newest")){
                //Doesn't matter if reviews have reviewText or not; from lowest to highest rating and then from newest to oldest
                Comparator<Review> orderByRatingAndDate = Comparator.comparing(Review::getRating).reversed()
                        .thenComparing(Review::getReviewCreatedOnDate).reversed();
                return reviews.stream().sorted(orderByRatingAndDate).collect(Collectors.toList());
            }
            else if (orderRating.equals("highest") && orderDate.equals("oldest")){
                //Doesn't matter if reviews have reviewText or not; from highest to lowest rating and then from oldest to newest
                Comparator<Review> orderByRatingAndDate = Comparator.comparing(Review::getRating).reversed()
                        .thenComparing(Review::getReviewCreatedOnDate);
                return reviews.stream().sorted(orderByRatingAndDate).collect(Collectors.toList());
            } else{
                //Doesn't matter if reviews have reviewText or not; from highest to lowest rating and from newest to oldest
                Comparator<Review> orderByRatingAndDate = Comparator.comparing(Review::getRating)
                        .thenComparing(Review::getReviewCreatedOnDate).reversed();
                return reviews.stream().sorted(orderByRatingAndDate).collect(Collectors.toList());
            }
        }
    }

}
