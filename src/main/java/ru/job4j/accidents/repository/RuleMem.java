package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Repository
public class RuleMem {
    private final Set<Rule> rules = new HashSet<>();

    public RuleMem() {
        rules.add(new Rule(1, "Статья. 10"));
        rules.add(new Rule(2, "Статья. 11"));
        rules.add(new Rule(3, "Статья. 12"));
    }

    public List<Rule> getAll() {
        return List.copyOf(rules);
    }

    public boolean create(Rule rule) {
        return rules.add(rule);
    }

    public Set<Rule> findById(String[] ids) {
        Set<Rule> ruleSet = new HashSet<>();
        for (Rule rule : rules) {
            for (String s : ids) {
                if (rule.getId() == Integer.parseInt(s)) {
                    ruleSet.add(rule);
                }
            }
        }
        return ruleSet;
    }
}
