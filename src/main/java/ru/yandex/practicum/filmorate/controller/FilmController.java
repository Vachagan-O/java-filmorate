package ru.yandex.practicum.filmorate.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/films")
@Slf4j
@AllArgsConstructor
@Validated
public class FilmController {
    private final ValidateService validateService;
    private final FilmService filmService;
    private final UserService userService;
    private final LikeService likeService;

    @GetMapping("/{filmId}")
    public Film getFilmById(@PathVariable Integer filmId) {
        log.info("Получение фильма с id {}", filmId);
        return filmService.getFilmById(filmId);
    }

    @GetMapping
    public List<Film> getFilms() {
        log.info("Получение списка всех фильмов");
        return filmService.getFilms();
    }

    @PostMapping
    public Film addFilm(@RequestBody @Valid Film film)  {
        validateService.validateFilm(film);
        filmService.addFilm(film);
        log.info("Добавлен фильм {}", film);
        return film;
    }

    @PutMapping
    public Film updateFilm(@RequestBody @Valid Film film) {
        validateService.validateFilm(film);
        filmService.updateFilm(film);
        log.info("Обновлен фильм: {}", film);
        return film;
    }

    @PutMapping("/{id}/like/{userId}")
    public void putLike(@PathVariable Integer id, @PathVariable Integer userId) throws NotFoundException {
        likeService.addLike(userId, id);
        log.info("Пользователь {} поставил лайк фильму {}", userId, id);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void deleteLike(@PathVariable Integer id, @PathVariable Integer userId) throws NotFoundException {
        userService.getUserById(userId);
        likeService.removeLike(userId, id);
        log.info("Пользователь {} убрал лайк у фильма {}", userId, id);
    }

    @GetMapping("/popular")
    public List<Film> getMostPopularFilm(@Positive @RequestParam(defaultValue = "10") Integer count) {
        log.info("Получение {} самых популярных фильмов", count);
        return filmService.mostPopularFilm(count);
    }
}
