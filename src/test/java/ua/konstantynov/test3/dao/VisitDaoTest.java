package ua.konstantynov.test3.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.konstantynov.test3.entities.Doctor;
import ua.konstantynov.test3.entities.Patient;
import ua.konstantynov.test3.entities.Visit;

import java.util.ArrayList;
import java.util.List;

class VisitDaoTest {
    private static VisitDao VISIT_DAO;
    Visit visit1;
    Visit visit2;
    Visit visit3;
    Doctor doctor;
    Patient patient;

    @BeforeAll
    static void beforeAll() {
        VISIT_DAO = new VisitDao();
    }

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
        patient = new Patient();
        visit1 = new Visit();
        visit2 = new Visit();
        visit3 = new Visit();
        visit1.setPatient(patient);
        visit1.setDoctor(doctor);
        visit1.setMedicalsList(new ArrayList<>());
        visit2.setPatient(patient);
        visit2.setDoctor(doctor);
        visit3.setMedicalsList(new ArrayList<>());
        visit3.setPatient(patient);
        visit3.setDoctor(doctor);
        visit3.setMedicalsList(new ArrayList<>());
    }

    @Test
    void save() {
        VISIT_DAO.save(visit1);
        Assertions.assertEquals(visit1, VISIT_DAO.get(visit1.getId()));
    }

    @Test
    void update() {
        VISIT_DAO.save(visit1);
        visit1.setRecipe("TEST TEST TEST");
        VISIT_DAO.update(visit1);
        Assertions.assertEquals(visit1, VISIT_DAO.get(visit1.getId()));
    }

    @Test
    void delete() {
        VISIT_DAO.save(visit1);
        VISIT_DAO.delete(visit1.getId());
        Assertions.assertEquals(0, VISIT_DAO.getAll().size());
    }

    @Test
    void get() {
        VISIT_DAO.save(visit1);
        Assertions.assertEquals(visit1, VISIT_DAO.get(visit1.getId()));
    }

    @Test
    void getAll() {
        List<Visit> customerList = new ArrayList<>();
        customerList.add(visit1);
        customerList.add(visit2);
        customerList.add(visit3);
        VISIT_DAO.save(visit1);
        VISIT_DAO.save(visit2);
        VISIT_DAO.save(visit3);
        Assertions.assertEquals(customerList, VISIT_DAO.getAll());
    }

    @Test
    void saveRandom() {
        int countBeforeSave = VISIT_DAO.getAll().size();
        VISIT_DAO.saveRandom(10);
        Assertions.assertEquals(countBeforeSave + 10, VISIT_DAO.getAll().size());

    }
}