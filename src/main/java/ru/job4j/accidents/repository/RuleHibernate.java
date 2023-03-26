package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Repository
@AllArgsConstructor
public class RuleHibernate {
    private final CrudRepository crudRepository;

    public List<Rule> getAll() {
        return crudRepository.query("FROM Rule", Rule.class);
    }

    public boolean create(Rule rule) {
        crudRepository.run(session -> session.persist(rule));
        return rule != null;
    }

    public Set<Rule> findById(List<Integer> ids) {
        return new HashSet<>(
                crudRepository.query("FROM Rule WHERE id IN (:fIds)",
                        Rule.class,
                        Map.of("fIds", ids)));
    }
}
