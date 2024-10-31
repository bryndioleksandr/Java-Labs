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

            while (true) {
                System.out.println("1. Send new Customer");
                System.out.println("2. Get list of Customers");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    Customer customer = new Customer();
                    customer.inputData();
                    oos.writeObject(customer);
                    oos.flush();

                    String response = (String) ois.readObject();
                    System.out.println("Server response: " + response);

                } else if (choice == 2) {
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

                } else if (choice == 3) {
                    oos.writeObject("exit");
                    oos.flush();
                    break;
                }
            }
            System.out.println("Disconnected from server.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
