package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Репозиторий для работы с базой через spring-data
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */

public interface RuleRepository extends CrudRepository<Rule, Integer> {
    @Query("FROM Rule WHERE id IN (?1)")
    Set<Rule> findById(List<Integer> ids);
}
