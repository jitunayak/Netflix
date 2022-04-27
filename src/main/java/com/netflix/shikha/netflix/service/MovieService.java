package com.netflix.shikha.netflix.service;

import com.netflix.shikha.netflix.jpaRepo.MovieRepository;
import com.netflix.shikha.netflix.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        Optional<Movie> movie = movieRepository.findMovieById(id);
        if (!movie.isPresent()) {
            throw new IllegalStateException("Movie not present");
        }
        return movie.get();
    }
}