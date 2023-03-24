package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.config.JdbcConfig;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Репозиторий для работы с базой через JdbcTemplate
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Repository
@AllArgsConstructor
public class AccidentJDBCTemplate {
    /**
     * @see JdbcConfig
     */
    private final JdbcTemplate jdbc;
    private final AccidentTypeJDBCTemplate accidentTypeJDBCTemplate;
    private final RuleJDBCTemplate ruleJDBCTemplate;
    private final RowMapper<Accident> accidentRowMapper = (resultSet, rowNum) -> {
        Accident accident = new Accident();
        accident.setId(resultSet.getInt("id"));
        accident.setName(resultSet.getString("name"));
        accident.setText(resultSet.getString("text"));
        accident.setAddress(resultSet.getString("address"));
        accident.setType(getAccidentType(resultSet));
        accident.setRules(getRules(accident.getId()));
        return accident;
    };

    /**
     * Метод добавляет в БД инцидент.
     * keyHolder - содержит сгенерированный ключ при успешном update.
     *
     * @param accident инцидент
     * @return инцидент
     */
    public Accident create(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO accident (name, text, address, type_id) VALUES (?,?,?,?)",
                    new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.getRules().forEach(
                rule -> jdbc.update(
                        "INSERT INTO accident_rule (accident_id, rule_id) VALUES (?, ?)",
                        keyHolder.getKey(),
                        rule.getId()));
        return accident;
    }

    public List<Accident> getAll() {
        return jdbc.query("SELECT id, name, text, address, type_id FROM accident",
                accidentRowMapper);
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(jdbc.queryForObject(
                "SELECT id, name, text, address, type_id FROM accident WHERE id = ?",
                accidentRowMapper,
                id));
    }

    public boolean update(int id, Accident accident) {
        int update = jdbc.update(
                "UPDATE accident SET name = ?, text = ?, address = ?, type_id = ? WHERE id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                id);
        jdbc.update("DELETE FROM accident_rule WHERE accident_id = ?",
                id);
        accident.getRules().forEach(
                rule -> jdbc.update(
                        "INSERT INTO accident_rule (accident_id, rule_id) VALUES (?, ?)",
                        id,
                        rule.getId()));
        return update != 0;
    }

    private AccidentType getAccidentType(ResultSet resultSet) throws SQLException {
       return accidentTypeJDBCTemplate.findById(resultSet.getInt("type_id")).orElseThrow();
    }

    private Set<Rule> getRules(int accidentId) {
        return new HashSet<>(ruleJDBCTemplate.getRulesByAccidentId(accidentId));
    }
}
