package Threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server {
    public static final int PORT = 8101;
    private static boolean running = true;

    public Server(){

    }
    public static void main(String args[])  {
        try {
            run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void run() throws IOException{
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            //serverSocket.setSoTimeout(1000000);
            while (running) {
                try {
                    System.out.println("Waiting for a client ...");
                    Socket socket = serverSocket.accept();
                    // Execute the client's request in a new thread
                    new ClientThread(socket).start();
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
