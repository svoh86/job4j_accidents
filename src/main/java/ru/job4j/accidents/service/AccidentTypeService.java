package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeMem;

import java.util.List;
import java.util.Optional;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Service
public class AccidentTypeService {
    private final AccidentTypeMem accidentTypeMem;

    public AccidentTypeService(AccidentTypeMem accidentTypeMem) {
        this.accidentTypeMem = accidentTypeMem;
    }

    public List<AccidentType> getAll() {
        return accidentTypeMem.getAll();
    }

    public boolean create(AccidentType accidentType) {
        return accidentTypeMem.create(accidentType);
    }

    public Optional<AccidentType> findById(int id) {
        return accidentTypeMem.findById(id);
    }
}
