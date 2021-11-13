package ua.konstantynov.test3.UserInterface.implement;

import ua.konstantynov.test3.UserInterface.CommonUtils;
import ua.konstantynov.test3.entities.Doctor;
import ua.konstantynov.test3.entities.Patient;
import ua.konstantynov.test3.entities.Visit;
import ua.konstantynov.test3.service.DoctorService;
import ua.konstantynov.test3.service.PatientService;
import ua.konstantynov.test3.service.VisitService;

import java.time.LocalDateTime;
import java.util.*;

class VisitUtils implements CommonUtils {
    private static final VisitService VISIT_SERVICE = new VisitService();

    static void menu() {
        loop:
        while (true) {
            System.out.println("### Visit Menu ###");
            System.out.println("1: Create new");
            System.out.println("2: Delete");
            System.out.println("3: Show");
            System.out.println("4: Show All");
            System.out.println("8: Back");
            System.out.println("Select an option:");
            int command = -1;
            try {
                command = new Scanner(System.in).nextInt();
            } catch (InputMismatchException e) {
                System.out.println("!!!!! Error: Incorrect value !!!!!");
            }
            switch (command) {
                case (1):
                    create();
                    break;
                case (2):
                    delete();
                    break;
                case (3):
                    show();
                    break;
                case (4):
                    showAll();
                    break;
                case (8):
                    break loop;
                default:
                    System.out.println("!!!!! Error: Unknown command !!!!!");
            }
        }
    }

    private static void create() {
        Visit visit = new Visit();
        Scanner scanner = new Scanner(System.in);
        visit.setVisitDateTime(LocalDateTime.now());
        System.out.println("Enter diagnosis:");
        visit.setDiagnosis(scanner.nextLine());
        System.out.println("Enter recipe:");
        visit.setRecipe(scanner.nextLine());
        System.out.println("Enter medicals through a space:");
        String medicals = scanner.nextLine();
        List<String> medicalsList = new ArrayList<>();
        Scanner medicalsInput = new Scanner(medicals);
        while (medicalsInput.hasNext()) {
            medicalsList.add(medicals);
            medicalsInput.next();
        }
        medicalsInput.close();
        visit.setMedicalsList(medicalsList);
        System.out.println("Enter doctor id:");
        Doctor doctor = new DoctorService().get(scanner.next());
        if (doctor == null) {
            System.out.println("!!!!! Error: id not found !!!!!");
            return;
        }
        visit.setDoctor(doctor);
        System.out.println("Enter patient id:");
        Patient patient = new PatientService().get(scanner.next());
        visit.setPatient(patient);
        if (patient == null) {
            System.out.println("!!!!! Error: id not found !!!!!");
            return;
        }
        VISIT_SERVICE.save(visit);
        System.out.println("Saved: id = ");
        System.out.println(visit.getId());
    }

    private static void delete() {
        System.out.println("Enter id:");
        String id = new Scanner(System.in).next();
        Visit visit = VISIT_SERVICE.get(id);
        if (visit == null) {
            System.out.println("!!!!! Error: id not found !!!!!");
        } else {
            VISIT_SERVICE.delete(id);
            System.out.println("Deleted");
        }
    }

    private static void show() {
        System.out.println("Enter id:");
        Visit visit = VISIT_SERVICE.get(new Scanner(System.in).next());
        System.out.println(visit == null ? "!!!!! Error: id not found !!!!!" : visit);
    }

    private static void showAll() {
        for (Visit visit : VISIT_SERVICE.getAll()) {
            System.out.println(visit);
        }
    }
}