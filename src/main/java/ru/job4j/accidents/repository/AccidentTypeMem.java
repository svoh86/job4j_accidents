package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Repository
public class AccidentTypeMem {
    private final Map<Integer, AccidentType> accidentTypes = new ConcurrentHashMap<>();

    public AccidentTypeMem() {
        accidentTypes.putIfAbsent(1, new AccidentType(1, "Две машины"));
        accidentTypes.putIfAbsent(2, new AccidentType(2, "Машина и человек"));
        accidentTypes.putIfAbsent(3, new AccidentType(3, "Машина и велосипед"));
    }

    public List<AccidentType> getAll() {
        Collection<AccidentType> values = accidentTypes.values();
        return List.copyOf(values);
    }

    public boolean create(AccidentType accidentType) {
        return accidentTypes.putIfAbsent(accidentType.getId(), accidentType) == null;

    }

    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(accidentTypes.get(id));
    }
}
