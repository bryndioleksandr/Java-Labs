package dev.mono;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        try(ServerSocket server = new ServerSocket(1234)){
            InetAddress serverIP = InetAddress.getLocalHost();
            String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            System.out.println("Server Information:");
            System.out.println("  IP Address   : " + serverIP.getHostAddress());
            System.out.println("  Hostname     : " + serverIP.getHostName());
            System.out.println("  Port         : " + 1234);
            System.out.println("  Status       : Running");
            System.out.println("  Start Time   : " + startTime);
            System.out.println("Waiting for client connection...");

            Socket client = server.accept();
            System.out.println("Connection succeeded: " + client.getInetAddress().getHostName());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            System.out.println("Output created");
            DataInputStream in = new DataInputStream(client.getInputStream());
            System.out.println("Input created");

            while(!client.isClosed()){
                System.out.println("Server now reading from channel");

                String entry = in.readUTF();
                System.out.println("Here is a message" + entry);
                if(entry.equals("exit")){
                    System.out.println("Client initialize connections suicide ...");
                    out.writeUTF("Server reply - "+entry + " - OK");
                    out.flush();
                    Thread.sleep(3000);
                    break;
                }
                out.writeUTF("Server reply - "+entry + " - OK");
                System.out.println("Server Wrote message to client.");

                out.flush();
            }
            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");
            in.close();
            out.close();

            client.close();

            System.out.println("Closing connections & channels - DONE.");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
