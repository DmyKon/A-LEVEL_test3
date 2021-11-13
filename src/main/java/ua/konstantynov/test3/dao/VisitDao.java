package ua.konstantynov.test3.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.konstantynov.test3.entities.Doctor;
import ua.konstantynov.test3.entities.Patient;
import ua.konstantynov.test3.entities.Visit;
import ua.konstantynov.test3.enumerations.DoctorStatus;
import ua.konstantynov.test3.enumerations.MedicalSpeciality;
import ua.konstantynov.test3.enumerations.PatientStatus;
import ua.konstantynov.test3.utils.HibernateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

    public void saveRandom(int count) {
        String[] names = ("Ernest Sharipova Sabina Yakovenko Arbi Savina Elenochka Ivanov Gilyana Sedov Rina " +
                "Kucherenko Evdokia Pavlenko Gulia Yudina Ramil Panchenko Aleksa Islamova Irik Kovtun Dilya " +
                "Serov Aleftina Li Khazan Lyubimov Nazir Orlova Lyubanya Suslova Feliks Kharitonov Luiza " +
                "Trofimov Rim Karpenko Rauf Subbotin Alishka Kondratenko Savva Kornilov Natalia Mironov " +
                "Albinochka Vetrova Alfina Terekhov Uma Akimov Khanifa Akopyan Eseniya Salnikova Adam " +
                "Khasanov Ayana Novikova Chermen Savchenko Rusya Sitnikova Artysh Kulikov Timofey Sizov " +
                "Vera Polyanskaya Armine Panova Ketrin Musatov Rustem Shcherbakova Sofia Demchenko Leon " +
                "Kopylova Semyon Sukhanov Gulnara Galiev Lia Koroleva Super Kononova Lara Krasnova Nikolai " +
                "Ilchenko Lyana Shcherbakov Elka Savin Linara Bychkov Bogdan Volodin").split(" ");
        for (int i = 0; i < count; i++) {
            Patient patient = new Patient();
            patient.setFirstName(names[ThreadLocalRandom.current().nextInt(names.length)]);
            patient.setLastName(names[ThreadLocalRandom.current().nextInt(names.length)]);
            patient.setPhoneNumber(ThreadLocalRandom.current().nextInt(1000000000) + "");
            long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
            long maxDay = LocalDate.of(2021, 12, 31).toEpochDay();
            long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
            LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
            patient.setBirthDate(randomDate);
            patient.setPatientStatus(PatientStatus
                    .values()[ThreadLocalRandom.current().nextInt(PatientStatus.values().length)]);
            Doctor doctor = new Doctor();
            doctor.setFirstName(names[ThreadLocalRandom.current().nextInt(names.length)]);
            doctor.setLastName(names[ThreadLocalRandom.current().nextInt(names.length)]);
            doctor.setPhoneNumber("+" + ThreadLocalRandom.current().nextInt(1000000000));
            doctor.setMedicalSpeciality(MedicalSpeciality
                    .values()[ThreadLocalRandom.current().nextInt(MedicalSpeciality.values().length)]);
            doctor.setDoctorStatus(DoctorStatus
                    .values()[ThreadLocalRandom.current().nextInt(DoctorStatus.values().length)]);
            Visit visit = new Visit();
            visit.setVisitDateTime(LocalDateTime.now());
            String[] randomWords = ("Bubble Grave On Peace Knot Jelly Attack Document Dedicate Moment Station " +
                    "Bar Rank Greeting Sunshine Deliver Lease Fitness Hit Reflect Healthy Ample War Stay " +
                    "Cane Prescription Pen Cooperative Dive Fax Portion Merchant Plastic Committee Protest " +
                    "Weapon Eternal Past Distributor Snarl Donor Casualty Brink Worm Barrier Shot Cover " +
                    "Building Breast Fascinate").split(" ");
            visit.setDiagnosis(randomWords[ThreadLocalRandom.current().nextInt(randomWords.length)]);
            visit.setRecipe(randomWords[ThreadLocalRandom.current().nextInt(randomWords.length)]);
            List<String> medicalList = new ArrayList<>();
            for (int j = 1; j <= ThreadLocalRandom.current().nextInt(randomWords.length) / 3; j++) {
                medicalList.add(randomWords[ThreadLocalRandom.current().nextInt(randomWords.length)]);
            }
            visit.setMedicalsList(medicalList);
            visit.setPatient(patient);
            visit.setDoctor(doctor);
            save(visit);
        }
    }
}
