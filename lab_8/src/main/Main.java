package main;

import customer.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    final int MAX_SIZE = 100;

    public List<Customer> createInitialCustomers() {
        List<Customer> customers = new ArrayList<>();

        customers.add(new Customer(1, "Smith", "John", "Doe", "123 Street", "123-4567", "john@example.com", "1111-2222-3333"));
        customers.add(new Customer(2, "Johnson", "Emily", "White", "456 Avenue", "234-5678", "emily@example.com", "4444-5555-6666"));
        customers.add(new Customer(3, "Williams", "David", "Brown", "789 Boulevard", "345-6789", "david@example.com", "7777-8888-9999"));

        return customers;
    }

    public void checkEmptyBonus(List<Customer> customers) {
        if (customers != null) {
            for (Customer customer : customers) {
                if (customer != null) {
                    customer.emptyBonus();
                }
            }
        } else {
            System.out.println("The customers list is null.");
        }
    }

    public void checkCreditInterval(List<Customer> customers) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter credit interval (first min and then max): ");
        Long minNumber = scanner.nextLong();
        scanner.nextLine();
        Long maxNumber = scanner.nextLong();
        scanner.nextLine();
        boolean found = false;

        if (customers != null) {
            for (Customer customer : customers) {
                if (customer != null) {
                    try {
                        Long credit = Long.parseLong(customer.getCreditNumber());
                        if (minNumber <= credit && maxNumber >= credit) {
                            customer.showAllData();
                            found = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid credit number format for customer ID: " + customer.getId());
                    }
                }
            }
            if (!found) {
                System.out.println("No customer found within the credit interval.");
            }
        } else {
            System.out.println("The customers list is null.");
        }
    }

    public void findByName(List<Customer> customers) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer first name: ");

        String name = scanner.nextLine();
        boolean found = false;

        if (customers != null) {
            for (Customer customer : customers) {
                if (customer != null && name.equalsIgnoreCase(customer.getFirstName())) {
                    customer.showAllData();
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No customer found with the name: " + name);
            }
        } else {
            System.out.println("The customers list is null.");
        }
    }

    public List<Customer> deleteUser(List<Customer> customers) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer's first name to delete: ");

        String name = scanner.nextLine();
        int count = 0;

        if (customers != null) {
            for (Customer customer : customers) {
                if (customer != null && name.equalsIgnoreCase(customer.getFirstName())) {
                    count++;
                }
            }

            if (count == 0) {
                System.out.println("No customer found with the name: " + name);
                return customers;
            }

            customers.removeIf(customer -> customer != null && name.equalsIgnoreCase(customer.getFirstName()));
            System.out.println(count + " customer(s) deleted.");
        }
        return customers;
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

    public void saveCustomersToFile(List<Customer> customers) {
        String filename = "customers.dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(customers);
            System.out.println("Customers data has been updated in the file.");
        } catch (Exception ex) {
            System.out.println("Got exception during file writing");
            ex.printStackTrace();
        }
    }

    public List<Customer> loadCustomersFromFile() {
        String filename = "customers.dat";
        File file = new File(filename);
        if (!file.exists()) {
            List<Customer> initialCustomers = createInitialCustomers();
            saveCustomersToFile(initialCustomers);
            return initialCustomers;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Customer>) ois.readObject();
        } catch (Exception ex) {
            System.out.println("Got exception during file reading");
            ex.printStackTrace();
            List<Customer> initialCustomers = createInitialCustomers();
            saveCustomersToFile(initialCustomers);
            return initialCustomers;
        }
    }

    public void showAllCustomers(List<Customer> customers) {
        if (customers == null || customers.isEmpty()) {
            System.out.println("No customers to display.");
            return;
        }
        for (Customer customer : customers) {
            if (customer != null) {
                customer.showAllData();
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        List<Customer> customers = main.loadCustomersFromFile();

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
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 0 and 6.");
                scanner.nextLine();
                continue;
            }

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
                    main.saveCustomersToFile(customers);
                    break;
                case 5:
                    customers = main.addUser(customers);
                    main.saveCustomersToFile(customers);
                    break;
                case 6:
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 0 and 6.");
            }
        }
    }
}
