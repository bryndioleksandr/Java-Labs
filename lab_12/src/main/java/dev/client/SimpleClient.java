package dev.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import dev.entity.Customer;

public class SimpleClient {

    public static void main(String[] args) throws InterruptedException {

        try (Socket socket = new Socket("localhost", 1234);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {
            System.out.println("Client connected to socket.");
            System.out.println();
            System.out.println("Client writing channel = oos & reading channel = ois initialized.");

            Thread readThread = new Thread(() -> {
                try {
                    while (!socket.isClosed()) {
                        Object in = ois.readObject();
                        System.out.println("Server response: " + in);
                    }
                } catch (IOException e) {
                    System.err.println("Connection closed or server not responding.");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            readThread.start();


            while (!socket.isOutputShutdown()) {

                if (br.ready()) {

                    System.out.println("Client start writing in channel...");
                    Thread.sleep(1000);
                    Customer customer = new Customer();
                    customer.inputData();

                    oos.writeObject(customer);
                    oos.flush();
                    System.out.println("Client sent object " + customer + " to server.");
                    Thread.sleep(1000);

                    if (customer.getId() == 0) {

                        System.out.println("Client kill connections");
                        Thread.sleep(2000);

                        System.out.println("reading...");
                        Object in = ois.readObject();
                        System.out.println(in);

                        break;
                    }

                    System.out.println("Client sent message & start waiting for data from server...");

                }
            }

            System.out.println("Closing connections & channels on client side - DONE.");
        } catch (UnknownHostException e) {
            System.err.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}