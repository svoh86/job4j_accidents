package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Repository
@AllArgsConstructor
public class RuleHibernate {
    /**
     * @see ru.job4j.accidents.config.HbnConfig
     */
    private final SessionFactory sf;

    public List<Rule> getAll() {
        List<Rule> ruleList;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            ruleList = session.createQuery("FROM Rule", Rule.class).list();
            session.getTransaction().commit();
        }
        return ruleList;
    }

    public boolean create(Rule rule) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.persist(rule);
            session.getTransaction().commit();
        }
        return rule != null;
    }

    public Set<Rule> findById(String[] ids) {
        Set<Rule> ruleSet = new HashSet<>();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            List<Rule> ruleList = session.createQuery("FROM Rule WHERE id IN (:fIds)", Rule.class)
                    .setParameter("fIds", getIds(ids)).list();
            ruleSet.addAll(ruleList);
            session.getTransaction().commit();
        }
        return ruleSet;
    }

    private List<Integer> getIds(String[] ids) {
        List<Integer> integerId = new ArrayList<>();
        for (String s : ids) {
            integerId.add(Integer.parseInt(s));
        }
        return integerId;
    }
}
