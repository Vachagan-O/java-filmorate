package ru.yandex.practicum.filmorate.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.MpaStorage;

import java.util.List;

@Service
@AllArgsConstructor
public class MpaService {
    private final MpaStorage storage;

    public Mpa getMpaById(Integer id) {
        return storage.getMpaById(id);
    }

    public List<Mpa> getAllMpa() {
        return storage.getAllMpa();
    }
}
