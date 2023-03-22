package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger ids = new AtomicInteger(3);

    public AccidentMem() {
        accidents.put(1, new Accident(1, "Ivanov", "Speed over 40", "Lenin street",
                new AccidentType(2, "Машина и человек"),
                Set.of(new Rule(1, "Статья. 1"))));
        accidents.put(2, new Accident(2, "Petrov", "Wrong parking", "Nevskiy avenue",
                new AccidentType(1, "Две машины"),
                Set.of(new Rule(2, "Статья. 2"))));
        accidents.put(3, new Accident(3, "Sidorov", "Didn't yield to a pedestrian", "Pushkin street",
                new AccidentType(2, "Машина и человек"),
                Set.of(new Rule(1, "Статья. 1"), new Rule(3, "Статья. 3"))));
    }

    public List<Accident> getAll() {
        Collection<Accident> values = accidents.values();
        return List.copyOf(values);
    }

    public boolean create(Accident accident) {
        accident.setId(ids.incrementAndGet());
        return accidents.put(accident.getId(), accident) == null;
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    public boolean update(int id, Accident accident) {
        return accidents.replace(id, accident) != null;
    }
}
