package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.controller.UserController;

import java.time.LocalDate;

@SpringBootTest
public class UserTest {
    private User user1;
    private User user2;
    private UserController userController;

    @BeforeEach
    void createUsers() {
        userController = new UserController(new UserService());

        user1 = User.builder()
                .id(1)
                .login("Логин1")
                .name("Пользователь1")
                .email("email1@mail.ru")
                .birthday(LocalDate.of(2000,1,1))
                .build();

        user2 = User.builder()
                .id(5)
                .login("Логин2")
                .name("Пользователь2")
                .email("email2@mail.ru")
                .birthday(LocalDate.of(2000, 5, 11))
                .build();
    }

    @Test
    public void createTest() {
        userController.createUser(user1);
        Assertions.assertEquals(1, userController.getAllUsers().size());
    }

    @Test
    public void updateTest() {
        userController.createUser(user1);
        user1.setId(12);
        Assertions.assertEquals(12, user1.getId());
    }

    @Test
    public void getAllTest() {
        userController.createUser(user1);
        userController.createUser(user2);
        Assertions.assertEquals(2, userController.getAllUsers().size());
    }
}
