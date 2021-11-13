package ua.konstantynov.test3.service;

import ua.konstantynov.test3.dao.VisitDao;
import ua.konstantynov.test3.entities.Doctor;
import ua.konstantynov.test3.entities.Patient;
import ua.konstantynov.test3.entities.Visit;
import ua.konstantynov.test3.enumerations.DoctorStatus;
import ua.konstantynov.test3.enumerations.MedicalSpeciality;
import ua.konstantynov.test3.enumerations.PatientStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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