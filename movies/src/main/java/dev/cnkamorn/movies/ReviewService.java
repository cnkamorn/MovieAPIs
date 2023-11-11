package dev.cnkamorn.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate; //to map between tables

    public Review singleReview(String reviewBody, String imdbId){
        Review rv = reviewRepository.insert(new Review(reviewBody)); //return rv
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(rv.getId()))
                .first();
        return rv;
    }

    public Optional<Review> deleteReview(ObjectId id){
        return reviewRepository.deleteReviewById(id);
    }

    public List<Review> allReviews() {
        return reviewRepository.findAll();
    }


}
