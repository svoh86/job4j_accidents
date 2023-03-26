package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Репозиторий для работы с базой через hibernate
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Repository
@AllArgsConstructor
public class AccidentTypeHibernate {
    private final CrudRepository crudRepository;

    public List<AccidentType> getAll() {
        return crudRepository.query("FROM AccidentType", AccidentType.class);
    }

    public boolean create(AccidentType accidentType) {
        crudRepository.run(session -> session.persist(accidentType));
        return accidentType != null;
    }

    public Optional<AccidentType> findById(int id) {
        return crudRepository.optional(
                "FROM AccidentType WHERE id = :fId",
                AccidentType.class,
                Map.of("fId", id));
    }
}
