package ua.konstantynov.test3.UserInterface.implement;

import ua.konstantynov.test3.UserInterface.CommonUtils;
import ua.konstantynov.test3.UserInterface.UpdatableUtils;
import ua.konstantynov.test3.entities.Doctor;
import ua.konstantynov.test3.enumerations.DoctorStatus;
import ua.konstantynov.test3.enumerations.MedicalSpeciality;
import ua.konstantynov.test3.service.DoctorService;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

class DoctorUtils implements CommonUtils, UpdatableUtils {
    private static final DoctorService DOCTOR_SERVICE = new DoctorService();

    static void menu() {
        loop:
        while (true) {
            System.out.println("### Doctor Menu ###");
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
        Doctor doctor = DOCTOR_SERVICE.get(scanner.next());
        if (doctor == null) {
            System.out.println("!!!!! Error: id not found !!!!!");
        } else {
            System.out.println("Enter first name:");
            doctor.setFirstName(scanner.next());
            System.out.println("Enter last name:");
            doctor.setLastName(scanner.next());
            System.out.println("Enter phone number:");
            doctor.setPhoneNumber(scanner.next());
            try {
                System.out.println("Enter medical speciality:");
                Arrays.stream(MedicalSpeciality.values())
                        .forEach(x -> System.out.println(x.toString()));
                doctor.setMedicalSpeciality(MedicalSpeciality.valueOf(scanner.next().toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.out.println("!!!!! Error: Incorrect value !!!!!");
                return;
            }
            try {
                System.out.println("Enter status:");
                Arrays.stream(DoctorStatus.values())
                        .forEach(x -> System.out.println(x.toString()));
                doctor.setDoctorStatus(DoctorStatus.valueOf(scanner.next().toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.out.println("!!!!! Error: Incorrect value !!!!!");
                return;
            }
            DOCTOR_SERVICE.update(doctor);
            System.out.println("Updated");
        }
    }

    private static void create() {
        Doctor doctor = new Doctor();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first name:");
        doctor.setFirstName(scanner.next());
        System.out.println("Enter last name:");
        doctor.setLastName(scanner.next());
        System.out.println("Enter phone number:");
        doctor.setPhoneNumber(scanner.next());
        try {
            System.out.println("Enter medical speciality:");
            Arrays.stream(MedicalSpeciality.values())
                    .forEach(x -> System.out.println(x.toString()));
            doctor.setMedicalSpeciality(MedicalSpeciality.valueOf(scanner.next().toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.out.println("!!!!! Error: Incorrect value !!!!!");
            return;
        }
        try {
            System.out.println("Enter status:");
            Arrays.stream(DoctorStatus.values())
                    .forEach(x -> System.out.println(x.toString()));
            doctor.setDoctorStatus(DoctorStatus.valueOf(scanner.next().toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.out.println("!!!!! Error: Incorrect value !!!!!");
            return;
        }
        DOCTOR_SERVICE.save(doctor);
        System.out.println("Saved");
    }

    private static void delete() {
        System.out.println("Enter id:");
        String id = new Scanner(System.in).next();
        Doctor doctor = DOCTOR_SERVICE.get(id);
        if (doctor == null) {
            System.out.println("!!!!! Error: id not found !!!!!");
        } else {
            DOCTOR_SERVICE.delete(id);
            System.out.println("Deleted");
        }
    }

    private static void show() {
        System.out.println("Enter id:");
        Doctor doctor = DOCTOR_SERVICE.get(new Scanner(System.in).next());
        System.out.println(doctor == null ? "!!!!! Error: id not found !!!!!" : doctor);
    }

    private static void showAll() {
        for (Doctor doctor : DOCTOR_SERVICE.getAll()) {
            System.out.println(doctor);
        }
    }
}
