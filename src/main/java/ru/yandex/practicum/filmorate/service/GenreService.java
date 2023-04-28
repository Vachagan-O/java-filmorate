package ru.yandex.practicum.filmorate.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.GenreStorage;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreService {
    private final GenreStorage storage;

    public Genre getGenreById(Integer id) {
        return storage.getGenreById(id);
    }

    public List<Genre> getGenres() {
        return storage.getGenres();
    }
}
