package ru.yandex.practicum.filmorate.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.storage.FilmDaoStorage;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.List;

@Service
@Slf4j
@Getter
public class FilmService {
    private final FilmDaoStorage storage;

    @Autowired
    public FilmService(FilmDaoStorage storage) {
        this.storage = storage;
    }

    public void addFilm(Film film) {
        storage.addObject(film);
    }

    public void updateFilm(Film film) {
        storage.updateObject(film);
    }

    public Film getFilmById(int id) throws NotFoundException {
        return storage.getObjectById(id);
    }

    public List<Film> getFilms() {
        return storage.getObjects();
    }

    public void addLike(int userId, int filmId) {
        storage.addLike(userId, filmId);
    }

    public void removeLike(int userId, int filmId) throws NotFoundException {
        storage.removeLike(userId, filmId);
    }

    public List<Film> mostPopularFilm(int count) {
        return storage.mostPopularFilm(count);
    }

    public List<Genre> getGenres() {
        return storage.getGenres();
    }

    public Genre getGenreById(int id) {
        return storage.getGenreById(id);
    }

    public List<Mpa> getRatings() {
        return storage.getRatings();
    }

    public Mpa getRatingById(int id) {
        return storage.getRatingById(id);
    }
}
