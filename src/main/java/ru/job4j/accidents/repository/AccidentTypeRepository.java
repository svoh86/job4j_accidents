package ru.job4j.accidents.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.AccidentType;

/**
 * Репозиторий для работы с базой через spring-data
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */

public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {
}
