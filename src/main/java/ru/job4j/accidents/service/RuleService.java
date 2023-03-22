package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleMem;

import java.util.List;
import java.util.Set;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Service
public class RuleService {
    private final RuleMem ruleMem;

    public RuleService(RuleMem ruleMem) {
        this.ruleMem = ruleMem;
    }

    public List<Rule> getAll() {
        return ruleMem.getAll();
    }

    public boolean create(Rule rule) {
        return ruleMem.create(rule);
    }

    public Set<Rule> findById(String[] ids) {
        return ruleMem.findById(ids);
    }
}
