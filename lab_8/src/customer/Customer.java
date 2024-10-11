package customer;

import java.util.Scanner;

public class Customer {
    private int id;



    private String surname;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private String creditNumber;



    private int birthYear;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getCreditNumber() {
        return creditNumber;
    }

    public double getBonus() {
        return bonus;
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