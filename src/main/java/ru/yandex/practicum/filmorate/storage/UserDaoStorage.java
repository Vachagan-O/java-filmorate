package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserDaoStorage extends MainStorage<User> {
    void  addFriend(int id, int friendId);

    void  deleteFriend(int id, int friendId);

    List<User> getFriends(int id);

    List<User> getMutualFriends(int id, int friendId);

    void clearTable();
}
