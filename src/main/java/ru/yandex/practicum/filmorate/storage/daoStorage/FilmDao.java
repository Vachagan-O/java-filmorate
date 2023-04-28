package ru.yandex.practicum.filmorate.storage.daoStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.FilmDaoStorage;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;

@Component
public class FilmDao implements FilmDaoStorage {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FilmDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Film addObject(Film film) {
        String insertMessageSql = "INSERT INTO film (NAME, DESCRIPTION, REALISE_DATE, DURATION, RATING_ID) VALUES(?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertMessageSql, new String[]{"id"});
            //ps.setInt(1, film.getId());
            ps.setString(1, film.getName());
            ps.setString(2, film.getDescription());
            ps.setDate(3, Date.valueOf(film.getReleaseDate()));
            ps.setString(4, String.valueOf(film.getDuration()));
            ps.setInt(5, film.getMpa().getId());

            return ps;
        }, keyHolder);
        film.setId(keyHolder.getKey().intValue());

        saveGenre(film);
        return film;
    }


    @Override
    public void updateObject(Film film) {
        getObjectById(film.getId());
        jdbcTemplate.update("UPDATE film SET name = ?, description = ?, realise_date = ?, duration = ?," + " rating_id = ? WHERE id = ?", film.getName(), film.getDescription(), film.getReleaseDate().toString(), film.getDuration(), film.getMpa().getId(), film.getId());

        if (film.getGenres() != null) {
            jdbcTemplate.update("DELETE FROM film_genres WHERE film_id = ?", film.getId());
            saveGenre(film);
        }
    }

    private void saveGenre(Film film) {
        for (Genre genre : film.getGenres()) {
            jdbcTemplate.update("INSERT INTO film_genres VALUES(?,?)", film.getId(), genre.getId());
        }
    }

    @Override
    public Film getObjectById(int id) {
        return jdbcTemplate.query("SELECT f.*, m.ID mpa_id, m.NAME mpa_name, m.DESCRIPTION mpa_desc " + "FROM film f LEFT JOIN MPA m ON f.rating_id = m.id WHERE f.id = ? ", (rs, rowNum) -> makeFilm(rs), id).stream().findAny().orElseThrow(() -> new NotFoundException("Фильм с указанным id " + id + " не найден"));
    }

    private Integer getTableId() {
        return jdbcTemplate.query("SELECT * FROM film ORDER BY id DESC LIMIT 1 ", (rs, rowNum) -> rs.getInt("id")).stream().findAny().orElse(0);
    }

    @Override
    public List<Film> getObjects() {
        return jdbcTemplate.query("SELECT f.*, m.ID mpa_id, m.NAME mpa_name, m.DESCRIPTION mpa_desc " + "FROM film f LEFT JOIN MPA m ON f.rating_id = m.id", (rs, rowNum) -> makeFilm(rs));
    }

    private Film makeFilm(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        LocalDate realiseDate = rs.getDate("realise_date").toLocalDate();
        int duration = rs.getInt("duration");
        Integer idMpa = rs.getInt("mpa_id");
        String nameMpa = rs.getString("mpa_name");
        String descriptionMpa = rs.getString("mpa_desc");

        Film film = new Film(id, name, description, realiseDate, duration, null, new Mpa(idMpa, nameMpa, descriptionMpa));
        film.setGenres(getFilmGenres(id));

        return film;
    }

    @Override
    public List<Film> mostPopularFilm(int count) {
        List<Film> popular = jdbcTemplate.query("SELECT f.*, m.ID mpa_id, m.NAME mpa_name, m.DESCRIPTION mpa_desc " + "FROM film f LEFT JOIN MPA m ON f.rating_id = m.id WHERE f.id IN (SELECT film_id AS id FROM likes " + "GROUP BY id ORDER BY COUNT(user_like_id) DESC LIMIT ?)", (rs, rowNum) -> makeFilm(rs), count);

        if (popular.isEmpty()) {
            return getObjects();
        }
        return popular;
    }

    private LinkedHashSet<Genre> getFilmGenres(int id) {
        List<Genre> genres = jdbcTemplate.query("SELECT * FROM genre WHERE id IN (SELECT genre_id AS id FROM " + "film_genres WHERE film_id = ?) ORDER BY id", (rs, rowNum) -> makeGenre(rs), id);
        return new LinkedHashSet<>(genres);
    }

    private Genre makeGenre(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");

        return new Genre(id, name);
    }
}
