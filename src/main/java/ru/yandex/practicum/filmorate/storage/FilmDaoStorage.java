package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmDaoStorage {
    Film addObject(Film object);

    void updateObject(Film object);

    Film getObjectById(int id);

    List<Film> getObjects();

    List<Film> mostPopularFilm(int count);

    void clearTable();
}
