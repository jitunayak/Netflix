package com.netflix.shikha.netflix.controller;

import com.netflix.shikha.netflix.models.Movie;
import com.netflix.shikha.netflix.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/movie")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getAllMovies(Movie movie) {
        return movieService.getAllMovies();
    }

    @GetMapping(path = "{movie_id}")
    public Movie getMovie(@PathVariable("movie_id") Long id) {
        return movieService.getMovieById(id);
    }
}
