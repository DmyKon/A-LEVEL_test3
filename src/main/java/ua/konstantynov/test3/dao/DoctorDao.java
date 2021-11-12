package ua.konstantynov.test3.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.konstantynov.test3.entities.Doctor;
import ua.konstantynov.test3.utils.HibernateUtils;

import java.util.List;

public class DoctorDao {
    public void save(Doctor doctor) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(doctor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("!!!!! save doctor: Invalid transaction !!!!!");
        }
    }

    public void update(Doctor doctor) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(doctor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("!!!!! Transaction error: update doctor !!!!!");
        }
    }

    public void delete(String id) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, id);
            if (doctor != null) {
                session.delete(doctor);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("!!!!! Transaction error: delete doctor !!!!!");
        }
    }

    public Doctor get(String id) {
        Transaction transaction = null;
        Doctor doctor = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            doctor = session.get(Doctor.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("!!!!! Transaction error: get doctor !!!!!");
        }
        return doctor;
    }

    public List<Doctor> getAll() {
        Transaction transaction = null;
        List<Doctor> doctors = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            doctors = session.createQuery(
                    "SELECT d FROM Doctor d", Doctor.class)
                    .list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("!!!!! Transaction error: getAll doctors !!!!!");
        }
        return doctors;
    }
}
