package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.config.JdbcConfig;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с базой через JdbcTemplate
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Repository
@AllArgsConstructor
public class AccidentTypeJDBCTemplate {
    /**
     * @see JdbcConfig
     */
    private final JdbcTemplate jdbc;
    private final RowMapper<AccidentType> accidentTypeRowMapper = (resultSet, rowNum) -> {
        AccidentType accidentType = new AccidentType();
        accidentType.setId(resultSet.getInt("id"));
        accidentType.setName(resultSet.getString("name"));
        return accidentType;
    };

    public List<AccidentType> getAll() {
        return jdbc.query("SELECT id, name FROM accident_type",
                accidentTypeRowMapper);
    }

    public boolean create(AccidentType accidentType) {
        return jdbc.update("INSERT INTO accident_type(name) VALUES (?)",
                accidentType.getName()) != 0;
    }

    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(jdbc.queryForObject("SELECT id, name FROM accident_type WHERE id = ?",
                accidentTypeRowMapper,
                id));
    }
}
