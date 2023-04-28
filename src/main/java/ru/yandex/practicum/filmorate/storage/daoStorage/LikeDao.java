package ru.yandex.practicum.filmorate.storage.daoStorage;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.storage.LikeDaoStorage;

@Component
@AllArgsConstructor
public class LikeDao implements LikeDaoStorage {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public void addLike(int userId, int filmId) {
        jdbcTemplate.update("INSERT INTO likes VALUES(?,?)", userId, filmId);
    }

    @Override
    public void removeLike(int userId, int filmId) {
        jdbcTemplate.update("DELETE FROM likes WHERE user_like_id = ? AND film_id = ?", userId, filmId);
    }
}
