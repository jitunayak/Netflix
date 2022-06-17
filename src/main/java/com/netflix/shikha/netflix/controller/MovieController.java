package com.netflix.shikha.netflix.controller;

import com.netflix.shikha.netflix.models.Genres;
import com.netflix.shikha.netflix.models.Movie;
import com.netflix.shikha.netflix.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/movie")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PutMapping
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) {
        try {
            return new ResponseEntity(movieService.updateMovie(movie), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("Please send correct request", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(Movie movie) {

        try {
            return new ResponseEntity(movieService.getAllMovies(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("Server is facing issues", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "{movie_id}")
    public ResponseEntity<Movie> getMovie(@PathVariable("movie_id") Long id) {
        try {
            return new ResponseEntity(movieService.getMovieById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Id is not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        try {
            movieService.addNewMovie(movie);
            return new ResponseEntity("New movie has been created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("Please send correct request", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "{movie_id}")
    public ResponseEntity deleteMovie(@PathVariable("movie_id") Long id) {
        try {
            movieService.deleteMovie(id);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("movie not found for specified Id", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/query")
    public ResponseEntity<List<Movie>> getByReleaseYear(@RequestParam(required = false) Year releaseYear, @RequestParam(required = false) Genres genre) {
        try {
            return new ResponseEntity(movieService.getMoviesByYearOrGenre(releaseYear, genre), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("please send genre or releaseyear", HttpStatus.BAD_REQUEST);
        }
    }

}
