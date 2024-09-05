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
    private double bonus;
    public Customer(int id, String surname, String firstName, String lastName, String address, String phone, String email, String creditNumber) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.creditNumber = creditNumber;
        this.bonus = 0;
    }

    public Customer() {
        this.id = 0;
        this.surname = "";
        this.firstName = "";
        this.lastName = "";
        this.address = "";
        this.phone = "";
        this.email = "";
        this.creditNumber = "";
        this.bonus = 0;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCreditNumber() {
        return creditNumber;
    }
    public void setCreditNumber(String creditNumber) {
        this.creditNumber = creditNumber;
    }
    public double getBonus() {
        return bonus;
    }
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public void inputData(){
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

        System.out.print("Enter Address: ");
        this.address = scanner.nextLine();

        System.out.print("Enter Phone: ");
        this.phone = scanner.nextLine();

        System.out.print("Enter Email: ");
        this.email = scanner.nextLine();

        System.out.print("Enter Credit Number: ");
        this.creditNumber = scanner.nextLine();
    }

    public void showAllData(){
        System.out.println("\nID: " + id);
        System.out.println("\nSurname: " + surname);
        System.out.println("\nFirst Name: " + firstName);
        System.out.println("\nLast Name: " + lastName);
        System.out.println("\nAddress: " + address);
        System.out.println("\nPhone: " + phone);
        System.out.println("\nEmail: " + email);
        System.out.println("\nCredit Number: " + creditNumber);
        System.out.println("\nBonus: " + bonus);
    }

    public void exactName(String name){
        if(name.equals(this.firstName)){
            this.showAllData();
        }
    }

    public void creditIn(Long min, Long max){
        if(min <= Long.parseLong(this.creditNumber) && max >= Long.parseLong(this.creditNumber)){
            this.showAllData();
        }
    }
    public void emptyBonus(){
        if(this.bonus == 0){
            this.showAllData();
        }
    }
}


