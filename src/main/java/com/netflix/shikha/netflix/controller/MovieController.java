package com.netflix.shikha.netflix.controller;

import com.netflix.shikha.netflix.models.Genres;
import com.netflix.shikha.netflix.models.Movie;
import com.netflix.shikha.netflix.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Movie updateMovie(@RequestBody Movie movie) {
        return movieService.updateMovie(movie);
    }

    @GetMapping
    public List<Movie> getAllMovies(Movie movie) {
        return movieService.getAllMovies();
    }

    @GetMapping(path = "{movie_id}")
    public Movie getMovie(@PathVariable("movie_id") Long id) {
        return movieService.getMovieById(id);
    }

    @PostMapping
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieService.addNewMovie(movie);
    }

    @DeleteMapping(path = "{movie_id}")
    public void deleteMovie(@PathVariable("movie_id") Long id) {
        movieService.deleteMovie(id);
    }

    @GetMapping(path = "/query")
    public List<Movie> getByReleaseYear(@RequestParam(required = false) Year releaseYear, @RequestParam(required = false) Genres genre) {
        return movieService.getMoviesByYearOrGenre(releaseYear, genre);
    }

}
