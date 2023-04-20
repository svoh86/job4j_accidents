package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class AccidentTypeService {
    private final AccidentTypeRepository accidentTypeRepository;

    public List<AccidentType> getAll() {
        return (List<AccidentType>) accidentTypeRepository.findAll();
    }

    @Transactional
    public boolean create(AccidentType accidentType) {
        boolean flag;
        try {
            accidentTypeRepository.save(accidentType);
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public Optional<AccidentType> findById(int id) {
        return accidentTypeRepository.findById(id);
    }
}
