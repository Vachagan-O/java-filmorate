package ru.yandex.practicum.filmorate.storage;

public interface LikeDaoStorage {

    void addLike(int userId, int filmId);

    void removeLike(int userId, int filmId);
}
