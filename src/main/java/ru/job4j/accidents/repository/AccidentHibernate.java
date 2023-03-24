package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

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
public class AccidentHibernate {
    /**
     * @see ru.job4j.accidents.config.HbnConfig
     */
    private final SessionFactory sf;

    public Accident create(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.persist(accident);
            session.getTransaction().commit();
        }
        return accident;
    }

    public List<Accident> getAll() {
        List<Accident> accidentList;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            accidentList = session.createQuery(
                            "SELECT DISTINCT ac FROM Accident ac JOIN FETCH ac.rules ORDER BY ac.id", Accident.class)
                    .list();
            session.getTransaction().commit();
        }
        return accidentList;
    }

    public Optional<Accident> findById(int id) {
        Optional<Accident> accidentOptional;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            accidentOptional = session.createQuery(
                            "SELECT DISTINCT ac FROM Accident ac JOIN FETCH ac.rules WHERE ac.id = :fId",
                            Accident.class)
                    .setParameter("fId", id)
                    .uniqueResultOptional();
            session.getTransaction().commit();
        }
        return accidentOptional;
    }

    public boolean update(Accident accident) {
        boolean flag;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            flag = accident.equals(session.merge(accident));
            session.getTransaction().commit();
        }
        return flag;
    }
}
