package ru.yandex.practicum.filmorate.storage;

import java.util.List;

public interface MainStorage<T> {
    T addObject(T object);

    void updateObject(T object);

    T getObjectById(int id);

    List<T> getObjects();
}
