package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
@Slf4j

public class FilmController {

    private final FilmService films;

    @PostMapping()
    public Film addFilm(@Valid @RequestBody Film film) {
        log.info("Добавлен новый фильм");
        return films.addFilm(film);
    }

    @GetMapping()
    public Collection<Film> getAllFilms() {
        log.info("Список фильмов");
        return films.getAllFilms();
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        log.info("Фильм обновлен");
        return films.updateFilm(film);
    }
}
