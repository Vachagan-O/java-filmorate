package ru.yandex.practicum.filmorate.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.storage.UserDaoStorage;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

@Service
@Slf4j
@Getter
public class UserService {

    private final UserDaoStorage userStorage;

    @Autowired
    public UserService(UserDaoStorage userStorage) {
        this.userStorage = userStorage;
    }

    public User addUser(User user) {
        return userStorage.addObject(user);
    }

    public void updateUser(User user) {
        userStorage.updateObject(user);
    }

    public User getUserById(int id) throws NotFoundException {
        return userStorage.getObjectById(id);
    }

    public List<User> getUsers() {
        return userStorage.getObjects();
    }

    public void addFriend(int id, int friendId) {
        userStorage.addFriend(id, friendId);
    }

    public void deleteFriend(int id, int friendId) {
        userStorage.deleteFriend(id, friendId);
    }

    public List<User> getFriends(int id) {
        return userStorage.getFriends(id);
    }

    public List<User> getMutualFriends(int id, int friendId) {
        return userStorage.getMutualFriends(id, friendId);
    }

}
