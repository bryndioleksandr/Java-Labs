package main;

import customer.Customer;
import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public Set<Customer> createInitialCustomers() {
        Set<Customer> customers = new HashSet<>();
        customers.add(new Customer(1, "Smith", "John", "Doe", "123 Street", "123-4567", "john@example.com", "111122223333"));
        customers.add(new Customer(2, "Johnson", "Emily", "White", "456 Avenue", "234-5678", "emily@example.com", "444455556666"));
        customers.add(new Customer(3, "Williams", "David", "Brown", "789 Boulevard", "345-6789", "david@example.com", "777788889999"));
        return customers;
    }

    public void checkEmptyBonus(Set<Customer> customers) {
        if (customers != null) {
            for (Customer customer : customers) {
                customer.emptyBonus();
            }
        } else {
            System.out.println("The customers set is null.");
        }
    }

    public void checkCreditInterval(Set<Customer> customers) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter credit interval (first min and then max): ");
        Long minNumber = scanner.nextLong();
        Long maxNumber = scanner.nextLong();
        boolean found = false;

        if (customers != null) {
            for (Customer customer : customers) {
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
            if (!found) {
                System.out.println("No customer found within the credit interval.");
            }
        } else {
            System.out.println("The customers set is null.");
        }
    }

    public void findByName(Set<Customer> customers) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer first name: ");
        String name = scanner.nextLine();
        boolean found = false;

        if (customers != null) {
            for (Customer customer : customers) {
                if (name.equalsIgnoreCase(customer.getFirstName())) {
                    customer.showAllData();
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No customer found with the name: " + name);
            }
        } else {
            System.out.println("The customers set is null.");
        }
    }

    public Set<Customer> deleteUser(Set<Customer> customers) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer's first name to delete: ");
        String name = scanner.nextLine();
        Set<Customer> toRemove = new HashSet<>();
        int count = 0;

        if (customers != null) {
            for (Customer customer : customers) {
                if (name.equalsIgnoreCase(customer.getFirstName())) {
                    toRemove.add(customer);
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("No customer found with the name: " + name);
            } else {
                customers.removeAll(toRemove);
                System.out.println(count + " customer(s) deleted.");
            }
        }
        return customers;
    }

    public Set<Customer> addUser(Set<Customer> customers) {
        if (customers.size() >= 100) {
            System.out.println("The set has reached its maximum size.");
            return customers;
        }

        Customer customer = new Customer();
        customer.inputData();
        customers.add(customer);
        System.out.println("Customer added successfully.");
        return customers;
    }

    public void saveCustomersToFile(Set<Customer> customers) {
        String filename = "customers_hashset.dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(customers);
            System.out.println("Customers data has been updated in the file.");
        } catch (Exception ex) {
            System.out.println("Got exception during file writing");
            ex.printStackTrace();
        }
    }

    public Set<Customer> loadCustomersFromFile() {
        String filename = "customers_hashset.dat";
        File file = new File(filename);
        if (!file.exists()) {
            Set<Customer> initialCustomers = createInitialCustomers();
            saveCustomersToFile(initialCustomers);
            return initialCustomers;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Set<Customer>) ois.readObject();
        } catch (Exception ex) {
            System.out.println("Got exception during file reading");
            ex.printStackTrace();
            Set<Customer> initialCustomers = createInitialCustomers();
            saveCustomersToFile(initialCustomers);
            return initialCustomers;
        }
    }

    public void showAllCustomers(Set<Customer> customers) {
        if (customers == null || customers.isEmpty()) {
            System.out.println("No customers to display.");
            return;
        }
        for (Customer customer : customers) {
            customer.showAllData();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        Set<Customer> customers = main.loadCustomersFromFile();

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
                    System.out.println("Invalid choice.");
            }
        }
    }
}
