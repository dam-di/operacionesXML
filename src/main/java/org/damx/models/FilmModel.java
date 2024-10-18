package org.damx.models;

import java.time.LocalDate;

public class FilmModel {
    private String id;
    private String name;
    private LocalDate date;
    private boolean adultMovie;
    private GenreModel genre;

    public FilmModel(String id, String name, LocalDate date, boolean adultMovie, GenreModel genre) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.adultMovie = adultMovie;
        this.genre = genre;
    }

    public FilmModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isAdultMovie() {
        return adultMovie;
    }

    public void setAdultMovie(boolean adultMovie) {
        this.adultMovie = adultMovie;
    }

    public GenreModel getGenre() {
        return genre;
    }

    public void setGenre(GenreModel genre) {
        this.genre = genre;
    }
}
