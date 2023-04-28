package ru.yandex.practicum.filmorate.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.storage.FilmDaoStorage;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

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

    public List<Film> mostPopularFilm(int count) {
        return storage.mostPopularFilm(count);
    }
}
