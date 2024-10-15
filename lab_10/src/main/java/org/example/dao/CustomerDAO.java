package org.example.dao;

import org.example.customer.Customer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class CustomerDAO {
    public static final String DELETE_BY_ID = "DELETE FROM customers WHERE id = ?";
    public static final String UPDATE_QUERY = "UPDATE customers SET surname = ?, firstname = ?, lastname = ?, birthyear = ?, address = ?, phone = ?, email = ?, creditnumber = ?, bonus = ? WHERE id = ?";
    public static final String SELECT_BY_NAME = "SELECT * FROM customers WHERE name = ?";
    public static final String SELECT_BY_ID = "SELECT * FROM customers WHERE id = ?";
    public static final String SELECT_ALL = "SELECT * FROM customers";
    public static final String INSERT_QUERY = "INSERT INTO customers(surname, firstname, lastname, birthyear, address, phone, email, creditnumber, bonus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String url;
    private String username;
    private String password;

    private static final String createTable = "CREATE TABLE IF NOT EXISTS customers ("
            + "id SERIAL PRIMARY KEY, "
            + "surname VARCHAR(100), "
            + "firstname VARCHAR(100), "
            + "lastname VARCHAR(100), "
            + "birthyear INT, "
            + "address VARCHAR(255), "
            + "phone VARCHAR(50), "
            + "email VARCHAR(100), "
            + "creditnumber VARCHAR(16), "
            + "bonus DOUBLE PRECISION"
            + ")";


    public CustomerDAO(String propertiesFilePath) {
        getProps(propertiesFilePath);
    }

    private void getProps(String propertiesFilePath) {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get(propertiesFilePath))) {
            props.load(in);
            this.url = props.getProperty("url");
            this.username = props.getProperty("username");
            this.password = props.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException("Error loading database properties", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public int createTable() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            return stmt.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int insert(Customer c) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(    INSERT_QUERY)) {
            stmt.setString(1, c.getSurname());
            stmt.setString(2, c.getFirstName());
            stmt.setString(3, c.getLastName());
            stmt.setInt(4, c.getBirthYear());
            stmt.setString(5, c.getAddress());
            stmt.setString(6, c.getPhone());
            stmt.setString(7, c.getEmail());
            stmt.setString(8, c.getCreditNumber());
            stmt.setDouble(9, c.getBonus());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<Customer> select() {
        ArrayList<Customer> customers = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL)) {
            while (rs.next()) {
                Customer c = new Customer(
                        rs.getInt("id"),
                        rs.getString("surname"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getInt("birthyear"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("creditnumber"),
                        rs.getDouble("bonus")
                );
                customers.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public Customer findById(int id) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("id"),
                        rs.getString("surname"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getInt("birthyear"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("creditnumber"),
                        rs.getDouble("bonus")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Customer findByName(String name) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_NAME)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("id"),
                        rs.getString("surname"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getInt("birthyear"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("creditnumber"),
                        rs.getDouble("bonus")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int update(Customer c) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY)) {
            stmt.setString(1, c.getSurname());
            stmt.setString(2, c.getFirstName());
            stmt.setString(3, c.getLastName());
            stmt.setInt(4, c.getBirthYear());
            stmt.setString(5, c.getAddress());
            stmt.setString(6, c.getPhone());
            stmt.setString(7, c.getEmail());
            stmt.setString(8, c.getCreditNumber());
            stmt.setDouble(9, c.getBonus());
            stmt.setInt(10, c.getId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int id) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_BY_ID)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
