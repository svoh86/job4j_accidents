package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentHibernate;

import java.util.List;
import java.util.Optional;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class AccidentService {
    private final AccidentHibernate accidentRepository;

    public List<Accident> getAll() {
        return accidentRepository.getAll();
    }

    public boolean create(Accident accident) {
        return accidentRepository.create(accident) != null;
    }

    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    public boolean update(Accident accident) {
        return accidentRepository.update(accident);
    }
}
