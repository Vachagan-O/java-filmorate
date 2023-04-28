package ru.yandex.practicum.filmorate.storage.daoStorage;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.MpaStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@AllArgsConstructor
public class MpaDao implements MpaStorage {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Mpa getMpaById(Integer id) {
        return jdbcTemplate.query("SELECT * FROM mpa WHERE id = ?", (rs, rowNum) -> makeRating(rs), id).stream()
                .findAny().orElseThrow(() -> new NotFoundException("Рейтинг с указанным id " + id + " не найден"));
    }

    @Override
    public List<Mpa> getAllMpa() {
        return jdbcTemplate.query("SELECT * FROM mpa", (rs, rowNum) -> makeRating(rs));
    }

    private Mpa makeRating(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        String description = rs.getString("description");

        return new Mpa(id, name, description);
    }
}
