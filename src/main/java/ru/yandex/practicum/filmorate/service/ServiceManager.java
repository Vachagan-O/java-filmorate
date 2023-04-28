package ru.yandex.practicum.filmorate.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ServiceManager {
    private final UserService userService;
    private final FilmService filmService;
    private final MpaService mpaService;
    private final GenreService genreService;

    @Autowired
    public ServiceManager(UserService userService, FilmService filmService, GenreService genreService, MpaService mpaService) {
        this.userService = userService;
        this.filmService = filmService;
        this.genreService = genreService;
        this.mpaService = mpaService;
    }
}
