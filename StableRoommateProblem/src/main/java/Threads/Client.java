package Threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client{
    public Client() {

    }

    public void run() {
        String serverAddress = "127.0.0.1"; // The server's IP address
        int PORT = 8101; // The server's port
        Scanner command = new Scanner(System.in);
        boolean running = true;
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            socket = new Socket(serverAddress, PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

        while (running) {
            String s = command.nextLine();
            // Send a request to the server
            out.println(s);
            out.flush();

            try {
                String response = in.readLine();
                if (response != null)
                    System.out.println(response);
                if (s.equals("exit")) {
                    running = false;
                    break;
                }
            } catch (IOException e) {
                System.err.println("Nothing to read");
            }
        }
        } catch (UnknownHostException e) {
            System.err.println("No server listening!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
