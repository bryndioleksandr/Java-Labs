package dev.server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleServer {
    private static final int PORT = 1234;
    public static void main(String[] args) throws IOException {
        try(ServerSocket server = new ServerSocket(PORT)){
            InetAddress serverIP = InetAddress.getLocalHost();
            String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            System.out.println("Server Information:");
            System.out.println("  IP Address   : " + serverIP.getHostAddress());
            System.out.println("  Hostname     : " + serverIP.getHostName());
            System.out.println("  Port         : " + server.getLocalPort());
            System.out.println("  Real hostname         : " + InetAddress.getLocalHost().getCanonicalHostName());
            System.out.println("  Socket address         : " + server.getLocalSocketAddress());
            System.out.println("  Status       : Running");
            System.out.println("  Start Time   : " + startTime);
            System.out.println("Waiting for client connection...");

            Socket client = server.accept();
            System.out.println("Connection succeeded: " + client.getInetAddress().getHostName());
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            System.out.println("Output created");
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            System.out.println("Input created");

            while(!client.isClosed()){
                System.out.println("Server now reading from channel");

                Object entry = in.readObject();
                System.out.println("Here is a message:\n" + entry.toString());
                if(entry.equals("exit")){
                    System.out.println("Client initialize connections suicide ...");
                    out.writeObject(entry);
                    out.flush();
                    Thread.sleep(3000);
                    break;
                }
                out.writeObject(entry);
                System.out.println("Server Wrote message to client.");

                out.flush();
            }
            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");
            in.close();
            out.close();

            client.close();

            System.out.println("Closing connections & channels - DONE.");

        } catch (InterruptedException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
