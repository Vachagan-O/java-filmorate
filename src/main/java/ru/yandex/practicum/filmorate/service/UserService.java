package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserService {

    private final Map<Integer, User> users = new HashMap<>();
    private int id = 1;

    public User createUser(User user) {
            user.setId(id);
            users.put(user.getId(), user);
            ++id;
            log.info("Пользователь с Id " + user.getId() + " успешно создан");
        return user;
    }

    public User updateUser(User user) {
        if (!users.containsKey(user.getId())) {
            throw new ValidationException("Пользователь с Id " + user.getId() + " не найден");
        }
        users.replace(user.getId(), user);
        log.info("Пользователь обновлён");
        return user;
    }

    public Collection<User> getAllUsers() {
        log.info("Список всех пользователей");
        return new ArrayList<>(users.values());
    }

}