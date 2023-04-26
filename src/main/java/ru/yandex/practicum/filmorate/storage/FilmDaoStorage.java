package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.List;

public interface FilmDaoStorage extends MainStorage<Film> {

    void addLike(int userId, int filmId);

    void removeLike(int userId, int filmId);

    List<Film> mostPopularFilm(int count);

    List<Genre> getGenres();

    List<Mpa> getRatings();

    Genre getGenreById(int id);

    Mpa getRatingById(int id);
}
