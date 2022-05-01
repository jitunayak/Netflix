package com.netflix.shikha.netflix.jpaRepo;

import com.netflix.shikha.netflix.models.Genres;
import com.netflix.shikha.netflix.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m from Movie m where m.title=?1 and m.releaseYear=?2")
    Optional<Movie> findMovieByTitleAndReleaseYear(String title, Year releaseYear);

    @Query("select m from Movie m where m.releaseYear=?1")
    Optional<List<Movie>> findMovieByReleaseYear(Year releaseYear);

    @Query("select m from Movie m where m.genre=?1")
    Optional<List<Movie>> findMovieByGenre(Genres genre);

    @Query("select m from Movie m where m.genre=?1 and m.releaseYear=?2")
    Optional<List<Movie>> findMovieByGenreAndReleaseYear(Genres genre, Year releaseYear);

}
