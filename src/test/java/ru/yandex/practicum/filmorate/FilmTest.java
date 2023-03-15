package ru.yandex.practicum.filmorate;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.controller.FilmController;

import java.time.LocalDate;

@SpringBootTest
public class FilmTest {

    private Film film1;
    private Film film2;
    private FilmController filmController;

    @BeforeEach
    void createFilms() {
        filmController = new FilmController(new FilmService());
        film1 = Film.builder()
                .id(1)
                .name("Фильм1")
                .description("Описание1")
                .releaseDate(LocalDate.of(2023, 3, 13))
                .duration(90)
                .build();

        film2 = Film.builder()
                .id(2)
                .name("Фильм2")
                .description("Описание2" + "2".repeat(200))
                .releaseDate(LocalDate.of(1985, 12, 29))
                .duration(180)
                .build();
    }

    @Test
    public void createTest() {
        filmController.addFilm(film1);
        Assertions.assertEquals(1, filmController.getAllFilms().size());
    }

    @Test
    public void getAllTest() {
        filmController.addFilm(film1);
        filmController.addFilm(film2);
        Assertions.assertEquals(2, filmController.getAllFilms().size());
    }

    @Test
    public void updateTest() {
        filmController.addFilm(film1);
        film1.setId(12);
        Assertions.assertEquals(12, film1.getId());
    }
}
