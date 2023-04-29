package ru.yandex.practicum.filmorate.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.storage.LikeDaoStorage;

@Service
@AllArgsConstructor
public class LikeService {
    private final LikeDaoStorage storage;

    public void addLike(int userId, int filmId) {
        storage.addLike(userId, filmId);
    }

    public void removeLike(int userId, int filmId) {
        storage.removeLike(userId, filmId);
    }
}
