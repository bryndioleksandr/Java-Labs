package main;

import customer.Customer;
import dao.CustomerDAO;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final int ADD_CUSTOMER = 1;
    private static final int SHOW_ALL_CUSTOMERS = 2;
    private static final int FIND_CUSTOMER_BY_ID = 3;
    private static final int UPDATE_CUSTOMER_BONUS = 4;
    private static final int DELETE_CUSTOMER = 5;
    private static final int FIND_CUSTOMER_BY_NAME = 6;
    private static final int EXIT = 0;

    public static void main(String[] args) {
            String propertiesFilePath = "C:\\КН-421\\РКСЗ\\lab_9\\src\\database.properties";
        CustomerDAO customerDAO = new CustomerDAO(propertiesFilePath);

        customerDAO.createTable();

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            printMenu();
            option = scanner.nextInt();

            switch (option) {
                case ADD_CUSTOMER:
                    Customer customer = new Customer();
                    customer.inputData();
                    customerDAO.insert(customer);
                    System.out.println("Customer added successfully!");
                    break;
                case SHOW_ALL_CUSTOMERS:
                    ArrayList<Customer> customers = customerDAO.select();
                    for (Customer c : customers) {
                        c.showAllData();
                        System.out.println("------------");
                    }
                    break;
                case FIND_CUSTOMER_BY_ID:
                    System.out.print("Enter customer ID: ");
                    int id = scanner.nextInt();
                    Customer foundCustomer = customerDAO.findById(id);
                    if (foundCustomer != null) {
                        foundCustomer.showAllData();
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
                case UPDATE_CUSTOMER_BONUS:
                    System.out.print("Enter customer ID: ");
                    int updateId = scanner.nextInt();
                    Customer customerToUpdate = customerDAO.findById(updateId);
                    if (customerToUpdate != null) {
                        System.out.print("Enter new bonus: ");
                        double newBonus = scanner.nextDouble();
                        customerToUpdate.setBonus(newBonus);
                        customerDAO.update(customerToUpdate);
                        System.out.println("Customer bonus updated.");
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
                case DELETE_CUSTOMER:
                    System.out.print("Enter customer ID to delete: ");
                    int deleteId = scanner.nextInt();
                    int result = customerDAO.delete(deleteId);
                    if (result > 0) {
                        System.out.println("Customer deleted successfully!");
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
                case FIND_CUSTOMER_BY_NAME:
                    scanner.nextLine();
                    System.out.print("Enter customer name: ");
                    String name = scanner.nextLine();
                    Customer customerByName = customerDAO.findByName(name);
                    customerByName.showAllData();
                    break;
                case EXIT:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (option != EXIT);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("Menu:");
        System.out.println(ADD_CUSTOMER + ". Add new customer");
        System.out.println(SHOW_ALL_CUSTOMERS + ". Show all customers");
        System.out.println(FIND_CUSTOMER_BY_ID + ". Find customer by ID");
        System.out.println(UPDATE_CUSTOMER_BONUS + ". Update customer bonus");
        System.out.println(DELETE_CUSTOMER + ". Delete customer by ID");
        System.out.println(FIND_CUSTOMER_BY_NAME + ". Find customer by surname");
        System.out.println(EXIT + ". Exit");
        System.out.print("Enter option: ");
    }
}
