package com.netflix.shikha.netflix.models;

import javax.persistence.*;
import java.time.Year;

@Entity
@Table
public class Movie {
    @Id
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movie_sequence"
    )
    private Long id;
    private String title;
    private Genres genre;
    private Year releaseYear;
    private String thumbnail;
    private String duration;
    private float rating;

    private Languages language;

    public Movie(Long id, String title, Genres genre, Year releaseYear, String thumbnail, String duration, float rating, Languages language) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.thumbnail = thumbnail;
        this.duration = duration;
        this.rating = rating;
        this.language = language;
    }

    public Movie(String title, Genres genre, Year releaseYear, String thumbnail, String duration, float rating, Languages language) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.thumbnail = thumbnail;
        this.duration = duration;
        this.rating = rating;
        this.language = language;
    }

    public Movie() {
    }

    public Languages getLanguage() {
        return language;
    }

    public void setLanguage(Languages language) {
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public Genres getGenre() {
        return genre;
    }

    public void setGenre(Genres genre) {
        this.genre = genre;
    }

    public Year getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Year releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseYear=" + releaseYear +
                ", thumbnail='" + thumbnail + '\'' +
                ", duration='" + duration + '\'' +
                ", rating=" + rating +
                '}';
    }
}
