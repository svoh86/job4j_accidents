package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.config.JdbcConfig;
import ru.job4j.accidents.model.Rule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Repository
@AllArgsConstructor
public class RuleJDBCTemplate {
    /**
     * @see JdbcConfig
     */
    private final JdbcTemplate jdbc;
    private final RowMapper<Rule> ruleRowMapper = (resultSet, rowNum) -> {
        Rule rule = new Rule();
        rule.setId(resultSet.getInt("id"));
        rule.setName((resultSet.getString("name")));
        return rule;
    };

    public List<Rule> getRulesByAccidentId(int accidentId) {
        return jdbc.query(
                "SELECT id, name FROM rule WHERE id IN (SELECT rule_id FROM accident_rule WHERE accident_id = ?)",
                ruleRowMapper,
                accidentId);
    }

    public List<Rule> getAll() {
        return jdbc.query("SELECT id, name FROM rule",
                ruleRowMapper);
    }

    public boolean create(Rule rule) {
        return jdbc.update("INSERT INTO rule(name) VALUES (?)",
                rule.getName()) != 0;
    }

    public Set<Rule> findById(String[] ids) {
        Set<Rule> rules = new HashSet<>();
        int[] integerIds = getIds(ids);
        for (int id : integerIds) {
            Rule ruleDB = jdbc.queryForObject("SELECT id, name FROM rule WHERE id = ?",
                    ruleRowMapper,
                    id);
            if (ruleDB != null) {
                rules.add(ruleDB);
            }
        }
        return rules;
    }

    private int[] getIds(String[] ids) {
        int[] integerId = new int[ids.length];
        for (int i = 0; i < ids.length; i++) {
            integerId[i] = Integer.parseInt(ids[i]);
        }
        return integerId;
    }
}
