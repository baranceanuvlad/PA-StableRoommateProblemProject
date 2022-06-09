package Threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static final int PORT = 8101;
    private static boolean running = true;
    private static List<ClientThread> clients=new ArrayList<>();

    public Server(){}
    public void execute()  {
        try {
            run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeServer(){
        for(ClientThread client:clients) {
            client.sendMessage("exitServer");
            client.closeThread();
        }
        System.exit(0);
    }
    public void run() throws IOException{
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            //serverSocket.setSoTimeout(1000000);
            while (running) {
                try {
                    System.out.println("Waiting for a client ...");
                    Socket socket = serverSocket.accept();
                    // Execute the client's request in a new thread
                    ClientThread client=new ClientThread(socket,this);
                    clients.add(client);
                    client.start();
                } catch (SocketTimeoutException e) {
                    System.err.println("Ceva timout!");
                }
            }
        } catch (IOException e) {
            System.err.println("Ooops... " + e);
        } finally {
            serverSocket.close();
        }
    }
}
