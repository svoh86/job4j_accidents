package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с базой через hibernate
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Repository
@AllArgsConstructor
public class AccidentTypeHibernate {
    /**
     * @see ru.job4j.accidents.config.HbnConfig
     */
    private final SessionFactory sf;

    public List<AccidentType> getAll() {
        List<AccidentType> accidentTypeList;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            accidentTypeList = session.createQuery("FROM AccidentType", AccidentType.class).list();
            session.getTransaction().commit();
        }
        return accidentTypeList;
    }

    public boolean create(AccidentType accidentType) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.persist(accidentType);
            session.getTransaction().commit();
        }
        return accidentType != null;
    }

    public Optional<AccidentType> findById(int id) {
        Optional<AccidentType> accidentTypeOptional;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            accidentTypeOptional = session.createQuery("FROM AccidentType WHERE id = :fId", AccidentType.class)
                    .setParameter("fId", id).uniqueResultOptional();
            session.getTransaction().commit();
        }
        return accidentTypeOptional;
    }
}
