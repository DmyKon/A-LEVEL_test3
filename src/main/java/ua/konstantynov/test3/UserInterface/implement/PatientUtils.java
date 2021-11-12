package ua.konstantynov.test3.UserInterface.implement;

import ua.konstantynov.test3.UserInterface.CommonUtils;
import ua.konstantynov.test3.UserInterface.UpdatableUtils;
import ua.konstantynov.test3.entities.Patient;
import ua.konstantynov.test3.service.PatientService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

class PatientUtils implements CommonUtils, UpdatableUtils {
    private static final PatientService PATIENT_SERVICE = new PatientService();

    static void menu() {
        loop:
        while (true) {
            System.out.println("### Patient Menu ###");
            System.out.println("1: Create new");
            System.out.println("2: Delete");
            System.out.println("3: Update");
            System.out.println("4: Show");
            System.out.println("5: Show All");
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
                    update();
                    break;
                case (4):
                    show();
                    break;
                case (5):
                    showAll();
                    break;
                case (8):
                    break loop;
                default:
                    System.out.println("!!!!! Error: Unknown command !!!!!");
            }
        }
    }

    private static void update() {
        System.out.println("Enter id");
        Scanner scanner = new Scanner(System.in);
        Patient patient = PATIENT_SERVICE.get(scanner.next());
        if (patient == null) {
            System.out.println("!!!!! Error: id not found !!!!!");
        } else {
            System.out.println("Enter first name:");
            patient.setFirstName(scanner.next());
            System.out.println("Enter last name:");
            patient.setLastName(scanner.next());
            System.out.println("Enter phone number:");
            patient.setPhoneNumber(scanner.next());
            System.out.println("Enter year of birth:");
            int year = scanner.nextInt();
            System.out.println("Enter month of birth:");
            int month = scanner.nextInt();
            System.out.println("Enter day of birth:");
            int day = scanner.nextInt();
            try {
                patient.setBirthDate(LocalDate.of(year, month, day));
            } catch (DateTimeException e) {
                System.out.println("!!!!! Error: Incorrect date !!!!!");
                return;
            }
            PATIENT_SERVICE.update(patient);
            System.out.println("Updated");
        }
    }

    private static void create() {
        Patient patient = new Patient();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first name:");
        patient.setFirstName(scanner.next());
        System.out.println("Enter last name:");
        patient.setLastName(scanner.next());
        System.out.println("Enter phone number:");
        patient.setPhoneNumber(scanner.next());
        try {
            System.out.println("Enter year of birth:");
            int year = scanner.nextInt();
            System.out.println("Enter month of birth:");
            int month = scanner.nextInt();
            System.out.println("Enter day of birth:");
            int day = scanner.nextInt();
            patient.setBirthDate(LocalDate.of(year, month, day));
        } catch (DateTimeException | InputMismatchException e) {
            System.out.println("!!!!! Error: Incorrect date !!!!!");
            return;
        }
        PATIENT_SERVICE.save(patient);
        System.out.println("Saved");
    }

    private static void delete() {
        System.out.println("Enter id:");
        String id = new Scanner(System.in).next();
        Patient patient = PATIENT_SERVICE.get(id);
        if (patient == null) {
            System.out.println("!!!!! Error: id not found !!!!!");
        } else {
            PATIENT_SERVICE.delete(id);
            System.out.println("Deleted");
        }
    }

    private static void show() {
        System.out.println("Enter id:");
        Patient patient = PATIENT_SERVICE.get(new Scanner(System.in).next());
        System.out.println(patient == null ? "!!!!! Error: id not found !!!!!" : patient);
    }

    private static void showAll() {
        for (Patient patient : PATIENT_SERVICE.getAll()) {
            System.out.println(patient);
        }
    }
}
