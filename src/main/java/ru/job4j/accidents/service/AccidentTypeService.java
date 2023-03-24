package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeJDBCTemplate;

import java.util.List;
import java.util.Optional;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class AccidentTypeService {
    private final AccidentTypeJDBCTemplate accidentTypeRepository;

    public List<AccidentType> getAll() {
        return accidentTypeRepository.getAll();
    }

    public boolean create(AccidentType accidentType) {
        return accidentTypeRepository.create(accidentType);
    }

    public Optional<AccidentType> findById(int id) {
        return accidentTypeRepository.findById(id);
    }
}
