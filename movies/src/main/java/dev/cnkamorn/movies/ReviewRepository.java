package dev.cnkamorn.movies;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//table Review
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {

    Optional<Review> deleteReviewById(ObjectId id);
}
