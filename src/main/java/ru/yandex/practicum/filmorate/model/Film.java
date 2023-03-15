package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import ru.yandex.practicum.filmorate.validation.MoviesDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Slf4j
@Data
@Builder
public class Film {


    private int id;

    @NotBlank(message = "Название не может быть пустым")
    private String name;

    @Size(max = 200, message = "максимальная длина описания — 200 символов")
    private String description;

    @Positive(message = "Продолжительность фильма должна быть положительной")
    private Integer duration;

    @MoviesDate(message = "дата релиза — не раньше 28 декабря 1895 года;")
    private LocalDate releaseDate;
}

