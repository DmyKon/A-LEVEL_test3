package ua.konstantynov.test3.service;

import ua.konstantynov.test3.dao.PatientDao;
import ua.konstantynov.test3.entities.Patient;

import java.util.List;

public class PatientService {
    private static final PatientDao PATIENT_DAO = new PatientDao();

    public void save(Patient patient) {
        PATIENT_DAO.save(patient);
    }

    public void update(Patient patient) {
        PATIENT_DAO.update(patient);
    }

    public void delete(String id) {
        PATIENT_DAO.delete(id);
    }

    public Patient get(String id) {
        return PATIENT_DAO.get(id);
    }

    public List<Patient> getAll() {
        return PATIENT_DAO.getAll();
    }
}