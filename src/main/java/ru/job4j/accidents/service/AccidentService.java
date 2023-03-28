package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class AccidentService {
    private final AccidentRepository accidentRepository;

    public List<Accident> getAll() {
        return accidentRepository.findAllByOrderById();
    }

    public boolean create(Accident accident) {
        boolean flag;
        try {
            accidentRepository.save(accident);
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    public boolean update(Accident accident) {
        return create(accident);
    }
}
