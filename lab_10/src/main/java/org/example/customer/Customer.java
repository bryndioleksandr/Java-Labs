package org.example.customer;

import jakarta.persistence.*;
import lombok.*;

import java.util.Scanner;


@Entity
@Table(name = "customers")
@Setter
@Getter
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "credit_number", unique = true)
    private String creditNumber;

    @Column(name = "birth_year")
    private int birthYear;

    @Column(name = "bonus")
    private double bonus;


    public Customer(int id, String surname, String firstName, String lastName, int birthYear, String address, String phone, String email, String creditNumber, double bonus) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.creditNumber = creditNumber;
        this.bonus = bonus;
    }

    public Customer() {
        this.id = 0;
        this.surname = "";
        this.firstName = "";
        this.lastName = "";
        this.birthYear = 0;
        this.address = "";
        this.phone = "";
        this.email = "";
        this.creditNumber = "";
        this.bonus = 0;
    }


    public void inputData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ID: ");
        this.id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Surname: ");
        this.surname = scanner.nextLine();
        System.out.print("Enter First Name: ");
        this.firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        this.lastName = scanner.nextLine();
        System.out.print("Enter birth year: ");
        this.birthYear = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Address: ");
        this.address = scanner.nextLine();
        System.out.print("Enter Phone: ");
        this.phone = scanner.nextLine();
        System.out.print("Enter Email: ");
        this.email = scanner.nextLine();
        System.out.print("Enter Credit Number: ");
        this.creditNumber = scanner.nextLine();
    }

    public void showAllData() {
        System.out.println("ID: " + id);
        System.out.println("Surname: " + surname);
        System.out.println("First name: " + firstName);
        System.out.println("Last name: " + lastName);
        System.out.println("Birth year: " + birthYear);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Credit Number: " + creditNumber);
        System.out.println("Bonus: " + bonus);
    }
}
