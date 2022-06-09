package Threads;

import UI.MainFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client{
    public String s=null;
    private MainFrame mainFrame=new MainFrame();
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
        mainFrame.draw();
        try {
            socket = new Socket(serverAddress, PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

        while (running) {
            s=mainFrame.getMessage();
            if(s==null)
                continue;
            System.out.println("Citesc comanda...");
            //String s = command.nextLine();
            // Send a request to the server
            out.println(s);
            out.flush();
            if (s.equals("exit") || s.equals("exitServer")) {
                System.out.println("Se inchide clientul");
                System.exit(0);
            }
            try {
                String response = in.readLine();
                if (response != null)
                    System.out.println(response);
                if (s.equals("exit")) {
                    running = false;
                    break;
                }
                else if(response.equals("exitServer")){
                    System.exit(0);
                }
            } catch (IOException e) {
                System.out.println("Serverul s-a inchis!");
                System.exit(0);
                //System.err.println("Nothing to read");
            }
            mainFrame.setMessage(null);
        }
        } catch (UnknownHostException e) {
            System.err.println("No server listening!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
