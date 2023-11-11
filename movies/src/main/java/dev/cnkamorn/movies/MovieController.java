package dev.cnkamorn.movies;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        return new ResponseEntity<List<Movie>>(movieService.allMovies(),HttpStatus.OK);
    }

    @GetMapping("/{imdbId}") //convert to the path variable
    public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable String imdbId ){
        return new ResponseEntity<Optional<Movie>>(movieService.singleMovie(imdbId),HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Optional<Movie>> getMovieTitle(@PathVariable String title) {
        return new ResponseEntity<Optional<Movie>>(movieService.movieByTitle(title),HttpStatus.OK);
    }

    @DeleteMapping("/{imdbId}")
    public ResponseEntity<Optional<Movie>> deleteMovie(@PathVariable String imdbId){
        return new ResponseEntity<Optional<Movie>>(movieService.deleteAmovie(imdbId),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Optional<Movie>> updateMovieTitle(@RequestBody Map<String,String> payload) {
        return new ResponseEntity<Optional<Movie>>(Optional.ofNullable(movieService.updateMovieTitle(payload.get("title"), payload.get("imdbId"))),HttpStatus.CREATED);
    }
}
