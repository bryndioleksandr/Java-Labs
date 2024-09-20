package main;
import customer.Customer;

import java.util.Scanner;
import java.io.*;

public class Main {
    final int MAX_SIZE = 100; // Максимальний розмір масиву

    public Customer[] createInitialCustomers() {
        Customer[] customers = new Customer[3]; // Створюємо початковий масив із 3 елементів

        customers[0] = new Customer(1, "Smith", "John", "Doe", "123 Street", "123-4567", "john@example.com", "1111-2222-3333");
        customers[1] = new Customer(2, "Johnson", "Emily", "White", "456 Avenue", "234-5678", "emily@example.com", "4444-5555-6666");
        customers[2] = new Customer(3, "Williams", "David", "Brown", "789 Boulevard", "345-6789", "david@example.com", "7777-8888-9999");

        return customers;
    }

    public void checkEmptyBonus(Customer[] customers) {
        if (customers != null) {
            for (Customer customer : customers) {
                if (customer != null) {
                    customer.emptyBonus();
                }
            }
        } else {
            System.out.println("The customers array is null.");
        }
    }

    public void checkCreditInterval(Customer[] customers) {
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
            System.out.println("The customers array is null.");
        }
    }

    public void findByName(Customer[] customers) {
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
            System.out.println("The customers array is null.");
        }
    }

    public Customer[] deleteUser(Customer[] customers) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer's first name to delete: ");

        String name = scanner.nextLine();
        int count = 0;

        // Підраховуємо кількість клієнтів для видалення
        for (Customer customer : customers) {
            if (customer != null && name.equalsIgnoreCase(customer.getFirstName())) {
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No customer found with the name: " + name);
            return customers;
        }

        // Створюємо новий масив з меншою кількістю елементів
        Customer[] newCustomers = new Customer[customers.length - count];
        for (int i = 0, k = 0; i < customers.length; i++) {
            if (customers[i] != null && name.equalsIgnoreCase(customers[i].getFirstName())) {
                continue;
            }
            newCustomers[k++] = customers[i];
        }

        System.out.println(count + " customer(s) deleted.");
        return newCustomers;
    }

    public Customer[] addUser(Customer[] customers) {
        if (customers.length >= MAX_SIZE) {
            System.out.println("The array has reached its maximum size of " + MAX_SIZE + " customers.");
            return customers;
        }

        Customer[] newCustomers = new Customer[customers.length + 1];
        Customer customer = new Customer();
        customer.inputData();

        for (int i = 0; i < customers.length; i++) {
            newCustomers[i] = customers[i];
        }
        newCustomers[newCustomers.length - 1] = customer;
        System.out.println("Customer added successfully.");
        return newCustomers;
    }

    public void saveCustomersToFile(Customer[] customers) {
        String filename = "customers.dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(customers);
            System.out.println("Customers data has been updated in the file.");
        } catch (Exception ex) {
            System.out.println("Got exception during file writing");
            ex.printStackTrace();
        }
    }

    public Customer[] loadCustomersFromFile() {
        String filename = "customers.dat";
        File file = new File(filename);
        if (!file.exists()) {
            // Файл не існує, створюємо початковий масив клієнтів
            Customer[] initialCustomers = createInitialCustomers();
            saveCustomersToFile(initialCustomers);
            return initialCustomers;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Customer[] customers = (Customer[]) ois.readObject();
            return customers;
        } catch (Exception ex) {
            System.out.println("Got exception during file reading");
            ex.printStackTrace();
            // У разі помилки, створюємо початковий масив клієнтів
            Customer[] initialCustomers = createInitialCustomers();
            saveCustomersToFile(initialCustomers);
            return initialCustomers;
        }
    }

    public void showAllCustomers(Customer[] customers) {
        if (customers == null || customers.length == 0) {
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
        Customer[] customers = main.loadCustomersFromFile();

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
                scanner.nextLine(); // Очистка буфера
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 0 and 6.");
                scanner.nextLine(); // Очистка буфера
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
