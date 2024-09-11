package main;
import text.TextTask;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int choice;
        TextTask textTask = new TextTask();
        System.out.print("Menu:\n1 - Text - Digits task\n2 - Password Checker\n3 - Exit\nEnter your choice: ");
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                int choiceLang;
                String input;
                System.out.print("Choose language:\n1 - English\n2 - Українська\nEnter your choice: ");
                choiceLang = sc.nextInt();
                sc.nextLine();
                switch (choiceLang) {
                    case 1:
                        System.out.print("Please enter your text: ");
                        input = sc.nextLine();
                        textTask.processText(input);
                        break;
                    case 2:
                        System.out.print("Введіть Ваш текст: ");
                        input = sc.nextLine();
                        textTask.processUkrText(input);
                        break;
                    case 3:
                        System.exit(0);
                        break;
                }
                break;
            case 2:
                System.out.print("Please enter your password: ");
                String password = sc.nextLine();
                if(textTask.validatePassword(password)) {
                    System.out.println("Password is valid");
                }
                else {
                    System.out.println("Password is not valid");
                }
                break;
                case 3:
                    System.exit(0);
                    break;
        }
    }
}

