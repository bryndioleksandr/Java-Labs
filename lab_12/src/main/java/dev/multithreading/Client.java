package dev.multithreading;

import dev.entity.Customer;
import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to server.");
            boolean running = true;

            while (running) {
                int choice = getUserChoice(scanner);
                switch (choice) {
                    case 1:
                        sendNewCustomer(oos, ois, scanner);
                        break;
                    case 2:
                        getListOfCustomers(oos, ois);
                        break;
                    case 3:
                        exitClient(oos);
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            System.out.println("Disconnected from server.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int getUserChoice(Scanner scanner) {
        System.out.println("1. Send new Customer");
        System.out.println("2. Get list of Customers");
        System.out.println("3. Exit");
        System.out.print("Enter choice: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter choice: ");
            scanner.next();
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return choice;
    }

    private static void sendNewCustomer(ObjectOutputStream oos, ObjectInputStream ois, Scanner scanner) throws IOException, ClassNotFoundException {
        Customer customer = new Customer();
        customer.inputData();
        oos.writeObject(customer);
        oos.flush();

        String response = (String) ois.readObject();
        System.out.println("Server response: " + response);
    }

    private static void getListOfCustomers(ObjectOutputStream oos, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        oos.writeObject("get_list");
        oos.flush();

        List<Customer> customers = (List<Customer>) ois.readObject();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer c : customers) {
                System.out.println(c);
            }
        }
    }

    private static void exitClient(ObjectOutputStream oos) throws IOException {
        oos.writeObject("exit");
        oos.flush();
    }
}