package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public User createUser(@Valid @RequestBody User user) {
        log.info("Добавлен новый пользователь");
        return userService.createUser(user);
    }

    @GetMapping()
    public Collection<User> getAllUsers() {
        log.info("Список пользователей");
        return userService.getAllUsers();
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        log.info("Пользователь обновлен");
        return userService.updateUser(user);
    }
}
