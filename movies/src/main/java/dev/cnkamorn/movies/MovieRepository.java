package dev.cnkamorn.movies;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie,ObjectId> {//What type of data? 1.Movie what type of ID? 2.ObjectId
    Optional<Movie> getMovieByImdbId(String ImdbId); //getMovieByImdbId

    Optional<Movie> getMovieByTitle(String title);
}
