package ru.job4j.accidents.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Accident;

import java.util.List;

/**
 * Репозиторий для работы с базой через spring-data
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */

public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    List<Accident> findAllByOrderById();
}
