package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserTest {
    private final UserStorage userStorage;

    private User user1 = User.builder()
            .id(1)
            .name("Имя1")
            .login("Логин1")
            .email("imya1@ya.ru")
            .birthday(LocalDate.parse("1984-05-01"))
            .build();

    private User user2 = User.builder()
            .id(2)
            .name("Имя2")
            .login("Логин2")
            .email("imya2@ya.ru")
            .birthday(LocalDate.parse("1984-05-02"))
            .build();

    private User user3 = User.builder()
            .id(3)
            .name("Имя3")
            .login("Логин3")
            .email("imya3@ya.ru")
            .birthday(LocalDate.parse("1984-05-03"))
            .build();

    @Test
    public void createTest() {
        userStorage.createUser(user1);
        Assertions.assertEquals(1, userStorage.getAllUsers().size());
    }

    @Test
    public void updateTest() {
        userStorage.createUser(user1);
        user1.setId(11);
        Assertions.assertEquals(11, user1.getId());
    }

    @Test
    public void getAllTest() {
        userStorage.createUser(user1);
        userStorage.createUser(user2);
        Assertions.assertEquals(2, userStorage.getAllUsers().size());
    }

    @Test
    public void getByIdTest() {
        userStorage.createUser(user1);
        userStorage.createUser(user2);
        Assertions.assertEquals(user2, userStorage.getUserById(2));
    }
}