package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleHibernate;

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
    private final RuleHibernate ruleRepository;

    public List<Rule> getAll() {
        return ruleRepository.getAll();
    }

    public boolean create(Rule rule) {
        return ruleRepository.create(rule);
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
