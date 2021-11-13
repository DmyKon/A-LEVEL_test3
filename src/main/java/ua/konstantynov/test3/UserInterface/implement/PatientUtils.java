package ua.konstantynov.test3.UserInterface.implement;

import ua.konstantynov.test3.UserInterface.CommonUtils;
import ua.konstantynov.test3.UserInterface.UpdatableUtils;
import ua.konstantynov.test3.entities.Patient;
import ua.konstantynov.test3.enumerations.PatientStatus;
import ua.konstantynov.test3.service.PatientService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
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
            loop:
            while (true) {
                System.out.println("### Update ###");
                System.out.println("1: First name");
                System.out.println("2: Last name");
                System.out.println("3: Pone number");
                System.out.println("4: Birth date");
                System.out.println("5: Status");
                System.out.println("7: Cancel");
                System.out.println("8: Update and return");
                System.out.println("Select an option:");
                int command = -1;
                try {
                    command = new Scanner(System.in).nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("!!!!! Error: Incorrect value !!!!!");
                }
                switch (command) {
                    case (1):
                        System.out.println("Enter first name:");
                        patient.setFirstName(scanner.next());
                        break;
                    case (2):
                        System.out.println("Enter last name:");
                        patient.setLastName(scanner.next());
                        break;
                    case (3):
                        System.out.println("Enter phone number:");
                        patient.setPhoneNumber(scanner.next());
                        break;
                    case (4):
                        try {
                            System.out.println("Enter year of birth:");
                            int year = new Scanner(System.in).nextInt();
                            System.out.println("Enter month of birth:");
                            int month = new Scanner(System.in).nextInt();
                            System.out.println("Enter day of birth:");
                            int day = new Scanner(System.in).nextInt();
                            patient.setBirthDate(LocalDate.of(year, month, day));
                        } catch (DateTimeException | InputMismatchException e) {
                            System.out.println("!!!!! Error: Incorrect date !!!!!");
                            break;
                        }
                        break;
                    case (5):
                        try {
                            System.out.println("Enter status:");
                            Arrays.stream(PatientStatus.values())
                                    .forEach(x -> System.out.println(x.getStatus() + " " + x.name()));
                            patient.setPatientStatus(PatientStatus.values()[scanner.nextInt()]);
                        } catch (IllegalArgumentException | InputMismatchException |
                                ArrayIndexOutOfBoundsException e) {
                            System.out.println("!!!!! Error: Incorrect value !!!!!");
                            break;
                        }
                        break;
                    case (7):
                        System.out.println("Cancelled");
                        break loop;
                    case (8):
                        PATIENT_SERVICE.update(patient);
                        System.out.println("Updated");
                        break loop;
                    default:
                        System.out.println("!!!!! Error: Unknown command !!!!!");
                }
            }
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
        try {
            System.out.println("Enter status:");
            Arrays.stream(PatientStatus.values())
                    .forEach(x -> System.out.println(x.getStatus() + " " + x.name()));
            patient.setPatientStatus(PatientStatus.values()[scanner.nextInt()]);
        } catch (IllegalArgumentException | InputMismatchException | ArrayIndexOutOfBoundsException e) {
            System.out.println("!!!!! Error: Incorrect value !!!!!");
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
