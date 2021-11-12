package ua.konstantynov.test3.service;

import ua.konstantynov.test3.dao.DoctorDao;
import ua.konstantynov.test3.entities.Doctor;

import java.util.List;

public class DoctorService {
    private static final DoctorDao DOCTOR_DAO = new DoctorDao();

    public void save(Doctor doctor) {
        DOCTOR_DAO.save(doctor);
    }

    public void update(Doctor doctor) {
        DOCTOR_DAO.update(doctor);
    }

    public void delete(String id) {
        DOCTOR_DAO.delete(id);
    }

    public Doctor get(String id) {
        return DOCTOR_DAO.get(id);
    }

    public List<Doctor> getAll() {
        return DOCTOR_DAO.getAll();
    }
}
