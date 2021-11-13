package ua.konstantynov.test3.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.konstantynov.test3.entities.Visit;
import ua.konstantynov.test3.utils.HibernateUtils;

import java.util.List;

public class VisitDao {
    public void save(Visit visit) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(visit);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("!!!!! Transaction error: save visit !!!!!");
            e.printStackTrace();
        }
    }

    public void update(Visit visit) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(visit);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("!!!!! Transaction error: update visit !!!!!");
        }
    }

    public void delete(String id) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Visit visit = session.get(Visit.class, id);
            if (visit != null) {
                session.delete(visit);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("!!!!! Transaction error: delete visit !!!!!");
        }
    }

    public Visit get(String id) {
        Transaction transaction = null;
        Visit visit = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            visit = session.get(Visit.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("!!!!! Transaction error: get visit !!!!!");
        }
        return visit;
    }

    public List<Visit> getAll() {
        Transaction transaction = null;
        List<Visit> visits = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            visits = session.createQuery(
                    "SELECT v FROM Visit v", Visit.class)
                    .list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("!!!!! Transaction error: getAll visits !!!!!");
        }
        return visits;
    }
}
