package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

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
public class AccidentHibernate {
    private final CrudRepository crudRepository;

    public Accident create(Accident accident) {
        crudRepository.run(session -> session.persist(accident));
        return accident;
    }

    public List<Accident> getAll() {
        return crudRepository.query(
                "SELECT DISTINCT ac FROM Accident ac JOIN FETCH ac.rules ORDER BY ac.id",
                Accident.class);
    }

    public Optional<Accident> findById(int id) {
        return crudRepository.optional(
                "SELECT DISTINCT ac FROM Accident ac JOIN FETCH ac.rules WHERE ac.id = :fId",
                Accident.class,
                Map.of("fId", id));
    }

    public boolean update(Accident accident) {
        return crudRepository.condition(session -> accident.equals(session.merge(accident)));
    }
}
