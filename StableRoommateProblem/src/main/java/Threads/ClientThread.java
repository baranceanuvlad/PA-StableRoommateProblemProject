package Threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket = null;
    private static boolean running = true;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            //socket.setSoTimeout(600000);

            while (running) {
                // Get the request from the input stream: client → server
                String request = in.readLine();
                System.out.println(request);
                String[] command = request.split(" ");
                // Send the response to the output stream: server → client

                if(command[0].equals("exit")){
                    running=false;
                    out.println("Closing client!");
                    out.flush();
                    System.out.println("Closing client!");
                    //socket.close();
                }
                else {
                    out.println("Unknown command!");
                    out.flush();
                }
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            //socket.close(); // or use try-with-resources
        }
    }
}
