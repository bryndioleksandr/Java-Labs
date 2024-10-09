package main;

import customer.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    final int MAX_SIZE = 100;

    public List<Customer> createInitialCustomers() {
        return new ArrayList<>(List.of(
                new Customer(1, "Smith", "John", "Doe", "123 Street", "123-4567", "john@example.com", "111122223333"),
                new Customer(2, "Johnson", "Emily", "White", "456 Avenue", "234-5678", "emily@example.com", "444455556666"),
                new Customer(3, "Williams", "David", "Brown", "789 Boulevard", "345-6789", "david@example.com", "777788889999")
        ));
    }

    public void checkEmptyBonus(List<Customer> customers) {
        customers.stream()
                .filter(customer -> customer != null && customer.getBonus() == 0)
                .forEach(Customer::showAllData);
    }

    public void checkCreditInterval(List<Customer> customers) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter credit interval (min max): ");
        Long minNumber = scanner.nextLong();
        Long maxNumber = scanner.nextLong();

        customers.stream()
                .filter(customer -> {
                    try {
                        Long credit = Long.parseLong(customer.getCreditNumber());
                        return credit >= minNumber && credit <= maxNumber;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid credit number format for customer ID: " + customer.getId());
                        return false;
                    }
                })
                .forEach(Customer::showAllData);
    }

    public void findByName(List<Customer> customers) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer first name: ");
        String name = scanner.nextLine();

        customers.stream()
                .filter(customer -> customer != null && customer.getFirstName().equalsIgnoreCase(name))
                .forEach(Customer::showAllData);
    }

    public List<Customer> deleteUser(List<Customer> customers) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer's first name to delete: ");
        String name = scanner.nextLine();

        List<Customer> filteredCustomers = customers.stream()
                .filter(customer -> customer != null && !customer.getFirstName().equalsIgnoreCase(name))
                .collect(Collectors.toList());

        if (filteredCustomers.size() == customers.size()) {
            System.out.println("No customer found with the name: " + name);
        } else {
            System.out.println((customers.size() - filteredCustomers.size()) + " customer(s) deleted.");
        }
        return filteredCustomers;
    }

    public List<Customer> addUser(List<Customer> customers) {
        if (customers.size() >= MAX_SIZE) {
            System.out.println("The list has reached its maximum size of " + MAX_SIZE + " customers.");
            return customers;
        }

        Customer customer = new Customer();
        customer.inputData();
        customers.add(customer);
        System.out.println("Customer added successfully.");
        return customers;
    }

    public void showAllCustomers(List<Customer> customers) {
        if (customers == null || customers.isEmpty()) {
            System.out.println("No customers to display.");
            return;
        }
        customers.stream()
                .filter(Objects::nonNull)
                .forEach(Customer::showAllData);
    }

    public static void main(String[] args) {
        Main main = new Main();
        List<Customer> customers = main.createInitialCustomers();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("0 - Show all customers");
            System.out.println("1 - Find customer by name");
            System.out.println("2 - Check credit cards");
            System.out.println("3 - Check empty bonuses");
            System.out.println("4 - Delete customer");
            System.out.println("5 - Add customer");
            System.out.println("6 - Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    main.showAllCustomers(customers);
                    break;
                case 1:
                    main.findByName(customers);
                    break;
                case 2:
                    main.checkCreditInterval(customers);
                    break;
                case 3:
                    main.checkEmptyBonus(customers);
                    break;
                case 4:
                    customers = main.deleteUser(customers);
                    break;
                case 5:
                    customers = main.addUser(customers);
                    break;
                case 6:
                    System.out.println("Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 0 and 6.");
            }
        }
    }
}
