package ua.konstantynov.test3.user_interface.implement;

import ua.konstantynov.test3.service.VisitService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
    public static void run() {
        loop:
        while (true) {
            System.out.println("### Main Menu ###");
            System.out.println("1: Doctor Utils");
            System.out.println("2: Patient Utils");
            System.out.println("3: Visit Utils");
            System.out.println("4: Fill DB with random values");
            System.out.println("9: Exit");
            System.out.println("Select an option:");
            int command = -1;
            try {
                command = new Scanner(System.in).nextInt();
            } catch (InputMismatchException e) {
                System.out.println("!!!!! Error: Incorrect value !!!!!");
            }
            switch (command) {
                case (1):
                    DoctorUtils.menu();
                    break;
                case (2):
                    PatientUtils.menu();
                    break;
                case (3):
                    VisitUtils.menu();
                    break;
                case (4):
                    System.out.println("Enter count:");
                    try {
                        new VisitService().saveRandom(new Scanner(System.in).nextInt());
                        System.out.println("Random values saved to DB");
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("!!!!! Error: Incorrect value !!!!!");
                        break;
                    }
                case (9):
                    break loop;
                default:
                    System.out.println("!!!!! Error: Unknown command !!!!!");
            }
        }
    }
}
