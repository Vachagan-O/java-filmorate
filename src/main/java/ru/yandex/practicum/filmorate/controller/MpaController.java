package ru.yandex.practicum.filmorate.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.service.MpaService;

import java.util.List;

@RestController
@RequestMapping("/mpa")
@Slf4j
@AllArgsConstructor
public class MpaController {
    private final MpaService service;

    @GetMapping
    public List<Mpa> getRatings() {
        log.info("Получение видов рейтинга");
        return service.getAllMpa();
    }

    @GetMapping("{id}")
    public Mpa getRatingById(@PathVariable Integer id) {
        log.info("Получение рейтинга с id {}", id);
        return service.getMpaById(id);
    }
}
