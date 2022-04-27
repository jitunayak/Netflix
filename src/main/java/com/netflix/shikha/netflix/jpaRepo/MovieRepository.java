package com.netflix.shikha.netflix.jpaRepo;

import com.netflix.shikha.netflix.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m from Movie m where m.id=?1")
    Optional<Movie> findMovieById(Long id);


}
