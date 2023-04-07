package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class FilmService {
    private final Map<Integer, Film> films = new HashMap<>();
    private int id = 1;

    public Film addFilm(Film film) {
        film.setId(id);
        films.put(film.getId(), film);
        ++id;

        log.info("Фильм добавлен");
        return film;
    }

    public Film updateFilm(Film film) {
        if (!films.containsKey(film.getId())) {
            throw new ValidationException("Фильм не найден");
        }
        films.replace(film.getId(), film);
        log.info("Фильм обновлён");
        return film;
    }

    public Collection<Film> getAllFilms() {
        log.info("Список всех фильмов");
        return new ArrayList<>(films.values());
    }

}
