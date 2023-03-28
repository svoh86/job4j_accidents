package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class RuleService {
    private final RuleRepository ruleRepository;

    public List<Rule> getAll() {
        return (List<Rule>) ruleRepository.findAll();
    }

    public boolean create(Rule rule) {
        boolean flag;
        try {
            ruleRepository.save(rule);
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public Set<Rule> findById(String[] ids) {
        List<Integer> integerId = getIds(ids);
        return ruleRepository.findById(integerId);
    }

    private List<Integer> getIds(String[] ids) {
        List<Integer> integerId = new ArrayList<>();
        for (String s : ids) {
            integerId.add(Integer.parseInt(s));
        }
        return integerId;
    }
}
