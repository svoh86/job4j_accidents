package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с базой через spring-data
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */

public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    List<Accident> findAllByOrderById();

    @Query("SELECT DISTINCT ac FROM Accident ac JOIN FETCH ac.rules WHERE ac.id = ?1")
    Optional<Accident> findById(Integer id);
}
