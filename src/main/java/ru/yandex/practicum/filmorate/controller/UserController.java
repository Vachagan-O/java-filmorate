package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.ServiceManager;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.service.ValidateService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final ValidateService validateService;
    private final UserService userService;

    @Autowired
    public UserController(ServiceManager serviceManager, ValidateService validateService) {
        this.validateService = validateService;
        this.userService = serviceManager.getUserService();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Integer userId) {
        log.info("Получение пользователя с id {}", userId);
        return userService.getUserById(userId);
    }

    @GetMapping
    public List<User> getUsers() {
        log.info("Получение списка всех пользователей");
        return userService.getUsers();
    }

    @PostMapping
    public User addUser(@RequestBody @Valid User user) {
        validateService.validateUser(user);
        log.info("Пользователь добавлен: {}", user);
        return userService.addUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody @Valid User user) {
        validateService.validateUser(user);
        userService.updateUser(user);
        log.info("Пользователь обновлен: {}", user);
        return user;
    }

    @PutMapping("/{id}/friends/{friendId}")
    public void addFriend(@PathVariable Integer id, @PathVariable Integer friendId) throws NotFoundException {
        userService.addFriend(id, friendId);
        log.info("Пользователи с id {} и id {} добавлены в друзья", id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void deleteFriend(@PathVariable Integer id, @PathVariable Integer friendId) throws NotFoundException {
        userService.deleteFriend(id, friendId);
        log.info("Пользователи с id {} и id {} удалены из друзей", id, friendId);
    }

    @GetMapping("/{id}/friends")
    public List<User> getFriends(@PathVariable Integer id) {
        log.info("Получение списка друзей пользователя {}", id);
        return userService.getFriends(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> getCommonFriends(@PathVariable Integer id, @PathVariable Integer otherId) {
        log.info("Получение списка общих друзей у пользователей с id {} и id {}", id, otherId);
        return userService.getMutualFriends(id, otherId);
    }
}
