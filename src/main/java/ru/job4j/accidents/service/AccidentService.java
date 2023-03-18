package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.List;
import java.util.Optional;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Service
public class AccidentService {
    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public List<Accident> getAll() {
        return accidentMem.getAll();
    }

    public boolean create(Accident accident) {
        return accidentMem.create(accident);
    }

    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }

    public boolean update(int id, Accident accident) {
        return accidentMem.update(id, accident);
    }
}
