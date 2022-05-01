package com.netflix.shikha.netflix.service;

import com.netflix.shikha.netflix.jpaRepo.MovieRepository;
import com.netflix.shikha.netflix.models.Genres;
import com.netflix.shikha.netflix.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
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
        Optional<Movie> movie = movieRepository.findById(id);
        if (!movie.isPresent()) {
            throw new IllegalStateException("Movie not present");
        }
        return movie.get();
    }

    public Movie addNewMovie(Movie movie) {
        Optional<Movie> existingMovie = movieRepository.findMovieByTitleAndReleaseYear(movie.getTitle(), movie.getReleaseYear());
        if (existingMovie.isPresent()) {
            throw new IllegalStateException("Movie is present.Enter a new movie");
        }
        return movieRepository.save(movie);
    }


    public Movie updateMovie(Movie movie) {
        if (movie.getId() == null) { // checking whether client/user has sent id in the payload
            throw new IllegalStateException("Id is required to update");
        }
        Optional<Movie> existingMovie = movieRepository.findById(movie.getId()); // if id is passed, we are confirming with the database;
        if (existingMovie.isPresent()) {
            return movieRepository.save(movie); // if movie is present in db already, we are using save(), which will update the record in db ( as id is passed, it won't create record for us)
        }
        return null;
    }

    public void deleteMovie(Long id) {
        Optional<Movie> checkPresence = movieRepository.findById(id);
        if (!checkPresence.isPresent()) {
            throw new IllegalStateException("Movie not found");
        }
        movieRepository.deleteById(id);
    }

    public List<Movie> getMovieByReleaseYear(Year releaseYear) {
        Optional<List<Movie>> checkMovie = movieRepository.findMovieByReleaseYear(releaseYear);
        if (!checkMovie.isPresent()) {
            throw new IllegalStateException("Movie not found for the year defined");
        }
        return checkMovie.get();
    }

    public List<Movie> getMoviesByYearOrGenre(Year releaseYear, Genres genre) {
        if (releaseYear == null && genre == null) {
            throw new IllegalStateException("releaseYear n Genre not found for the year defined");
        }
        if (releaseYear != null && genre == null) {
            Optional<List<Movie>> checkMovieByYear = movieRepository.findMovieByReleaseYear(releaseYear);
            return checkMovieByYear.get();
        }
        if (releaseYear == null && genre != null) {
            Optional<List<Movie>> checkMovieByGenre = movieRepository.findMovieByGenre(genre);
            return checkMovieByGenre.get();
        }
        Optional<List<Movie>> checkMovieByGenreAndYear = movieRepository.findMovieByGenreAndReleaseYear(genre, releaseYear);
        return checkMovieByGenreAndYear.get();
    }
}