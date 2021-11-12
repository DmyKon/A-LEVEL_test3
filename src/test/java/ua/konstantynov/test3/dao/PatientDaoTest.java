package ua.konstantynov.test3.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.konstantynov.test3.entities.Patient;

import java.util.ArrayList;
import java.util.List;

class PatientDaoTest {
    private static PatientDao PATIENT_DAO;
    Patient patient1;
    Patient patient2;
    Patient patient3;


    @BeforeAll
    static void beforeAll() {
        PATIENT_DAO = new PatientDao();
    }

    @BeforeEach
    void setUp() {
        patient1 = new Patient();
        patient2 = new Patient();
        patient3 = new Patient();
    }

    @Test
    void save() {
        PATIENT_DAO.save(patient1);
        Assertions.assertEquals(patient1, PATIENT_DAO.get(patient1.getId()));
    }

    @Test
    void update() {
        PATIENT_DAO.save(patient1);
        patient1.setFirstName("TEST");
        PATIENT_DAO.update(patient1);
        Assertions.assertEquals(patient1, PATIENT_DAO.get(patient1.getId()));
    }

    @Test
    void delete() {
        PATIENT_DAO.save(patient1);
        PATIENT_DAO.delete(patient1.getId());
        Assertions.assertEquals(0, PATIENT_DAO.getAll().size());
    }

    @Test
    void get() {
        PATIENT_DAO.save(patient1);
        Assertions.assertEquals(patient1, PATIENT_DAO.get(patient1.getId()));
    }

    @Test
    void getAll() {
        List<Patient> customerList = new ArrayList<>();
        customerList.add(patient1);
        customerList.add(patient2);
        customerList.add(patient3);
        PATIENT_DAO.save(patient1);
        PATIENT_DAO.save(patient2);
        PATIENT_DAO.save(patient3);
        Assertions.assertEquals(customerList, PATIENT_DAO.getAll());
    }
}