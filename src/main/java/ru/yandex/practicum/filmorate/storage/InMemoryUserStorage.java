package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;

@Slf4j
@Component
@Qualifier("inMemoryUserStorage")
public class InMemoryUserStorage implements UserStorage {

    private final Map<Integer, User> users = new HashMap<>();
    private int newId = 1;

    // Создание
    @Override
    public User createUser(User user) {
        if (!users.containsKey(user.getId())) {
            user.setId(newId);
            users.put(user.getId(), user);
            newId++;
            log.debug("Пользователь с Id " + user.getId() + " успешно создан");
            return user;
        } else {
            throw new NotFoundException("Пользователь с Id " + user.getId() + " уже добавлен");
        }
    }

    // Обновление
    @Override
    public User updateUser(User user) {
        if (!users.containsKey(user.getId())) {
            throw new NotFoundException("Пользователь с Id " + user.getId() + " не найден");
        }
        users.replace(user.getId(), user);
        log.debug("Пользователь успешно обновлён");
        return user;
    }

    // Список
    @Override
    public Collection<User> getAllUsers() {
        log.debug("Запрошен список всех пользователей");
        return new ArrayList<>(users.values());
    }

    //Получение по ID
    @Override
    public User getUserById(Integer id) {
        if (!users.containsKey(id)) {
            throw new NotFoundException("пользователя не найден");
        }
        log.debug("Запрошен пользователя по Id");
        return users.get(id);
    }

}
