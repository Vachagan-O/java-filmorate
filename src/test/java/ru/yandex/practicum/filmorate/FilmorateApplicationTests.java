package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.storage.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.LinkedHashSet;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class FilmorateApplicationTests {

    public final UserDaoStorage userStorage;
    public final FilmDaoStorage filmStorage;
    public final MpaStorage mpaStorage;
    public final GenreStorage genreStorage;
    public final LikeDaoStorage likeDaoStorage;

    @Test
    public void doesErrorAppearWhenAddUser() {
        User user = new User(null, "login", "name",
                LocalDate.of(1946, 8, 20), "chippinIn.@mail.ru");
        userStorage.addObject(user);
        User user1 = new User(null, "friendLog", "friendName",
                LocalDate.of(1958, 8, 20), "friend.@mail.ru");
        userStorage.addObject(user1);
        User user2 = new User(null, "someUsLog", "someUsName",
                LocalDate.of(1976, 8, 20), "someUs.@mail.ru");
        userStorage.addObject(user2);
        Assertions.assertEquals(userStorage.getObjectById(user.getId()),
                new User(user.getId(), "login", "name", LocalDate.of(1946, 8, 20),
                        "chippinIn.@mail.ru"));
        userStorage.clearTable();

    }

    @Test
    public void doesErrorAppearWhenUpdateUser() {

        User user = new User(null, "login", "name",
                LocalDate.of(1946, 8, 20), "chippinIn.@mail.ru");

        userStorage.addObject(user);

        userStorage.updateObject(new User(user.getId(), "updateLogin", "updateName",
                LocalDate.of(1946, 8, 20), "chippinIn.@mail.ru"));
        Assertions.assertEquals(userStorage.getObjectById(user.getId()), new User(user.getId(),
                "updateLogin", "updateName",
                LocalDate.of(1946, 8, 20), "chippinIn.@mail.ru"));
        userStorage.clearTable();

    }

    @Test
    public void shouldGetCorrectUserById() {
        User user = new User(null, "login", "name",
                LocalDate.of(1946, 8, 20), "chippinIn.@mail.ru");
        userStorage.addObject(user);
        userStorage.getObjectById(user.getId());
        Assertions.assertEquals(user, new User(user.getId(), "login", "name",
                LocalDate.of(1946, 8, 20), "chippinIn.@mail.ru"));
        userStorage.clearTable();

    }

    @Test
    public void doesErrorAppearWhenAddFriend() {
        User user = new User(null, "login", "name",
                LocalDate.of(1946, 8, 20), "chippinIn.@mail.ru");
        userStorage.addObject(user);
        User user1 = new User(null, "friendLog", "friendName",
                LocalDate.of(1958, 8, 20), "friend.@mail.ru");
        userStorage.addObject(user1);
        User user2 = new User(null, "someUsLog", "someUsName",
                LocalDate.of(1976, 8, 20), "someUs.@mail.ru");
        userStorage.addObject(user2);

        userStorage.addFriend(1, 2);
        userStorage.addFriend(1, 3);
        userStorage.addFriend(2, 1);
        userStorage.addFriend(2, 3);
        userStorage.clearTable();

    }

    @Test
    public void doesErrorAppearWhenDeleteFriend() {
        userStorage.deleteFriend(1, 2);
        userStorage.clearTable();

    }

    @Test
    public void doesErrorAppearWhenAddFilm() {
        LinkedHashSet<Genre> genres = new LinkedHashSet<>();
        genres.add(new Genre(1, "Комедия"));
        filmStorage.addObject(new Film(null, "SuperFilm", "superDescription",
                LocalDate.of(1996, 8, 20), 120, new LinkedHashSet<>(genres),
                new Mpa(1, null, null)));
        Assertions.assertEquals(filmStorage.getObjectById(1), new Film(1, "SuperFilm",
                "superDescription", LocalDate.of(1996, 8, 20), 120,
                new LinkedHashSet<>(genres), new Mpa(1, "G", "У фильма нет возрастных ограничений")));
        filmStorage.clearTable();
    }

    @Test
    public void shouldGetCorrectFilmById() {
        LinkedHashSet<Genre> genres = new LinkedHashSet<>();
        genres.add(new Genre(1, "Комедия"));

        Film film = new Film(1, "boringFilm", "boringDescription",
                LocalDate.of(1996, 8, 20), 120, new LinkedHashSet<>(genres),
                new Mpa(2, "PG", "детям рекомендуется смотреть фильм с родителями"));
        filmStorage.addObject(film);
        Assertions.assertEquals(film, new Film(film.getId(), "boringFilm", "boringDescription",
                LocalDate.of(1996, 8, 20), 120, new LinkedHashSet<>(genres),
                new Mpa(2, "PG", "детям рекомендуется смотреть фильм с родителями")));
    }

    @Test
    public void doesErrorAppearWhenRemoveLike() {
        likeDaoStorage.removeLike(1, 1);
    }

    @Test
    public void shouldGetGenres() {
        Assertions.assertEquals(genreStorage.getGenres().size(), 6);
    }

    @Test
    public void shouldGetCorrectGenreById() {
        Assertions.assertEquals(genreStorage.getGenreById(1), new Genre(1, "Комедия"));
    }

    @Test
    public void shouldGetRatings() {
        Assertions.assertEquals(mpaStorage.getAllMpa().size(), 5);
    }

    @Test
    public void shouldGetCorrectRatingById() {
        Assertions.assertEquals(mpaStorage.getMpaById(1), new Mpa(1, "G",
                "У фильма нет возрастных ограничений"));
    }
}
