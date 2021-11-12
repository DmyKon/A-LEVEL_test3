package ua.konstantynov.test3.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.konstantynov.test3.entities.Patient;
import ua.konstantynov.test3.utils.HibernateUtils;

import java.util.List;

public class PatientDao {
    public void save(Patient patient) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(patient);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("!!!!! Transaction error: save patient !!!!!");
        }
    }

    public void update(Patient patient) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(patient);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("!!!!! Transaction error: update patient !!!!!");
        }
    }

    public void delete(String id) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Patient patient = session.get(Patient.class, id);
            if (patient != null) {
                session.delete(patient);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("!!!!! Transaction error: delete patient !!!!!");
        }
    }

    public Patient get(String id) {
        Transaction transaction = null;
        Patient patient = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            patient = session.get(Patient.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("!!!!! Transaction error: get patient !!!!!");
        }
        return patient;
    }

    public List<Patient> getAll() {
        Transaction transaction = null;
        List<Patient> patients = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            patients = session.createQuery(
                    "SELECT p FROM Patient p", Patient.class)
                    .list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("!!!!! Transaction error: getAll patients !!!!!");
        }
        return patients;
    }
}
