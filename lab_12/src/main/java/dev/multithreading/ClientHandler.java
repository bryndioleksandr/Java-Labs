package dev.multithreading;

import dev.entity.Customer;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private static final List<Customer> customerList = new CopyOnWriteArrayList<>();

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {

            Object request;
            while ((request = in.readObject()) != null) {
                if ("get_list".equals(request)) {
                    out.writeObject(customerList);
                    out.flush();
                } else if (request instanceof Customer) {
                    Customer customer = (Customer) request;
                    customerList.add(customer);
                    out.writeObject("Customer saved: " + customer);
                    out.flush();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
