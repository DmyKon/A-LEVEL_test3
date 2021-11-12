package ua.konstantynov.test3.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.konstantynov.test3.entities.Doctor;

import java.util.ArrayList;
import java.util.List;

class DoctorDaoTest {
    private static DoctorDao DOCTOR_DAO;
    Doctor doctor1;
    Doctor doctor2;
    Doctor doctor3;

    @BeforeAll
    static void beforeAll() {
        DOCTOR_DAO = new DoctorDao();
    }

    @BeforeEach
    void setUp() {
        doctor1 = new Doctor();
        doctor2 = new Doctor();
        doctor3 = new Doctor();
    }

    @Test
    void save() {
        DOCTOR_DAO.save(doctor1);
        Assertions.assertEquals(doctor1, DOCTOR_DAO.get(doctor1.getId()));
    }

    @Test
    void update() {
        DOCTOR_DAO.save(doctor1);
        doctor1.setFirstName("TEST");
        DOCTOR_DAO.update(doctor1);
        Assertions.assertEquals(doctor1, DOCTOR_DAO.get(doctor1.getId()));
    }

    @Test
    void delete() {
        DOCTOR_DAO.save(doctor1);
        DOCTOR_DAO.delete(doctor1.getId());
        Assertions.assertEquals(0, DOCTOR_DAO.getAll().size());
    }

    @Test
    void get() {
        DOCTOR_DAO.save(doctor1);
        Assertions.assertEquals(doctor1, DOCTOR_DAO.get(doctor1.getId()));
    }

    @Test
    void getAll() {
        List<Doctor> customerList = new ArrayList<>();
        customerList.add(doctor1);
        customerList.add(doctor2);
        customerList.add(doctor3);
        DOCTOR_DAO.save(doctor1);
        DOCTOR_DAO.save(doctor2);
        DOCTOR_DAO.save(doctor3);
        Assertions.assertEquals(customerList, DOCTOR_DAO.getAll());
    }
}