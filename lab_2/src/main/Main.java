package main;
import customer.Customer;

import java.util.Scanner;

public class Main {

    public Customer[] createObject() {
        Customer[] customers = new Customer[3];

        customers[0] = new Customer(1, "Smith", "John", "Doe", "123 Street", "123-4567", "john@example.com", "1111-2222-3333");
        customers[1] = new Customer(2, "Johnson", "Emily", "White", "456 Avenue", "234-5678", "emily@example.com", "4444-5555-6666");
        customers[2] = new Customer(3, "Williams", "David", "Brown", "789 Boulevard", "345-6789", "david@example.com", "7777-8888-9999");

        return customers;
    }

    public void checkEmptyBonus(Customer[] customers) {
        if (customers != null) {
            for (int i = 0; i < customers.length; i++) {
                if (customers[i] != null) {
                    customers[i].emptyBonus();
                }
            }
        } else {
            System.out.println("The customers array is null.");
        }
    }

    public void checkCreditInterval(Customer[] customers){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter credit interval(first min and then max): ");
        Long minNumber = scanner.nextLong();
        scanner.nextLine();
        Long maxNumber = scanner.nextLong();
        scanner.nextLine();
        boolean found = false;

        if (customers != null) {
            for (int i = 0; i < customers.length; i++) {
                if (customers[i] != null) {
                    customers[i].creditIn(minNumber, maxNumber);
                    found = true;
                }
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


    public static void main(String[] args) {
        Main main = new Main();
        Customer[] customers = main.createObject();
        Scanner scanner = new Scanner(System.in);
        System.out.print("1 - Find customer by name\n2 - Check credit cards\n3 - Check empty bonuses\nEnter your choice:  ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch(choice){
            case 1: main.findByName(customers); break;
            case 2: main.checkCreditInterval(customers); break;
            case 3: main.checkEmptyBonus(customers); break;
            default: System.out.println("Invalid choice");
        }
    }
}
