package org.example.lab_11.main;

import org.example.lab_11.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.example.lab_11.repository.CustomerRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = CustomerRepository.class)
@EntityScan(basePackageClasses = Customer.class)
public class Main {

    private static CustomerRepository customerRepository = null;
    public Main(CustomerRepository customerRepository) {
        Main.customerRepository = customerRepository;
    }
    private static final int ADD_CUSTOMER = 1;
    private static final int SHOW_ALL_CUSTOMERS = 2;
    private static final int FIND_CUSTOMER_BY_ID = 3;
    private static final int UPDATE_CUSTOMER_BONUS = 4;
    private static final int DELETE_CUSTOMER = 5;
    private static final int FIND_CUSTOMER_BY_NAME = 6;
    private static final int EXIT = 0;

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            printMenu();
            option = scanner.nextInt();

            switch (option) {
                case ADD_CUSTOMER:
                    Customer customer = new Customer();
                    customer.inputData();
                    customerRepository.save(customer);
                    System.out.println("Customer added successfully!");
                    break;
                case SHOW_ALL_CUSTOMERS:
                    List<Customer> customers = customerRepository.findAll();
                    for (Customer c : customers) {
                        c.showAllData();
                    }
                    break;
                case FIND_CUSTOMER_BY_ID:
                    System.out.print("Enter customer ID: ");
                    Long id = scanner.nextLong();
                    Optional<Customer> cust = customerRepository.findById(id);
                    if(cust.isPresent()) {
                        cust.get().showAllData();
                    }
                    else{
                        System.out.println("Customer not found!");
                    }
                    break;
                case UPDATE_CUSTOMER_BONUS:
                    System.out.print("Enter customer ID: ");
                    Long updateId = scanner.nextLong();
                    System.out.print("Enter new bonus: ");
                    double bonus = scanner.nextDouble();
                    customerRepository.updateCustomerBonus(updateId, bonus);
                    break;
                case DELETE_CUSTOMER:
                    System.out.print("Enter customer ID to delete: ");
                    Long deleteId = scanner.nextLong();
                    customerRepository.deleteById(deleteId);
                    break;
                case FIND_CUSTOMER_BY_NAME:
                    scanner.nextLine();
                    System.out.print("Enter customer surname: ");
                    String name = scanner.nextLine();
                    List <Customer> customersBySurname = customerRepository.findBySurname(name);
                    for (Customer c : customersBySurname) {
                        c.showAllData();
                    }
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

//public void insert(Customer c) {
//
//}
//
//public List<Customer> select() {
//
//}
//
//
//public Customer findById(int id) {
//
//}
//
//public List<Customer> findByName(String name) {
//
//}
//
//public int update(Customer c) {
//
//
//}
//
//public int delete(int id) {
//
//}
