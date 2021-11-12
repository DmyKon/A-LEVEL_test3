package ua.konstantynov.test3.service;

import ua.konstantynov.test3.dao.VisitDao;
import ua.konstantynov.test3.entities.Visit;

import java.util.List;

public class VisitService {
    private static final VisitDao PATIENT_VISIT_DAO = new VisitDao();

    public void save(Visit visit) {
        PATIENT_VISIT_DAO.save(visit);
    }

    public void update(Visit visit) {
        PATIENT_VISIT_DAO.update(visit);
    }

    public void delete(String id) {
        PATIENT_VISIT_DAO.delete(id);
    }

    public Visit get(String id) {
        return PATIENT_VISIT_DAO.get(id);
    }

    public List<Visit> getAll() {
        return PATIENT_VISIT_DAO.getAll();
    }

    public void saveRandom(int count) {
        PATIENT_VISIT_DAO.saveRandom(count);
    }
}