package dev.cnkamorn.movies;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;

@Service
public class MovieService {
    @Autowired //spring boot will initialize this field without creating the constructor
    private MovieRepository movieRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }

    /**
     * Get a single Movie from the database by imdbID
     * @param imdbId
     * @return
     */
    public Optional<Movie> singleMovie(String imdbId){

        return movieRepository.getMovieByImdbId(imdbId);
    }

    /**
     * Get a single Movie from the database by title
     */
    public Optional<Movie> movieByTitle(String title) {
        return movieRepository.getMovieByTitle(title);
    }

    public Optional<Movie> deleteAmovie(String imdbId) {
        return movieRepository.deleteMovieByImdbId(imdbId);
    }

    public Movie updateMovieTitle(String title,String imdbId){
       return mongoTemplate.update(Movie.class)
                .matching(Criteria.where("ImdbId").is(imdbId))
                .apply(new Update().set("title",title)).withOptions(FindAndModifyOptions.options().returnNew(true))
               .findAndModifyValue();
    }
}
