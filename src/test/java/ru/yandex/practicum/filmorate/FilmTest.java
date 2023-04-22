package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.daoStorage.FilmDbStorage;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FilmTest {
    private final FilmDbStorage filmDbStorage;
    Film film1 = Film.builder()
            .id(1)
            .name("Фильм1")
            .description("Описание1")
            .releaseDate(LocalDate.parse("2022-01-01"))
            .duration(121)
            .build();

    Film film2 = Film.builder()
            .id(2)
            .name("Фильм2")
            .description("Описание2")
            .releaseDate(LocalDate.parse("2022-01-02"))
            .duration(122)
            .build();

    Film film3 = Film.builder()
            .id(3)
            .name("Фильм3")
            .description("Описание3")
            .releaseDate(LocalDate.parse("2022-01-03"))
            .duration(123)
            .build();

    @Test
    public void createTest() {
        filmDbStorage.addFilm(film1);
        Assertions.assertEquals(1, filmDbStorage.getAllFilms().size());
    }

    @Test
    public void getAllTest() {
        filmDbStorage.addFilm(film1);
        filmDbStorage.addFilm(film2);
        Assertions.assertEquals(2, filmDbStorage.getAllFilms().size());
    }

    @Test
    public void updateTest() {
        filmDbStorage.addFilm(film1);
        film1.setId(11);
        Assertions.assertEquals(11, film1.getId());
    }

    @Test
    public void getFilmById() {
        filmDbStorage.addFilm(film1);
        Assertions.assertEquals(film1, filmDbStorage.getFilmById(1));
    }
}