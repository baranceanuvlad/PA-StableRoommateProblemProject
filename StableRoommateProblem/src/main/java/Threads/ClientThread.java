package Threads;

import Algorithms.Irving;
import Repositories.PreferencesRepository;
import Repositories.RoommateRepository;
import Roommate.Preferences;
import Roommate.Roommates;
import UI.MainFrame;
import com.github.javafaker.Faker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.System.out;

public class ClientThread extends Thread {
    private Socket socket = null;
    protected boolean running = true;
    private Server server;
    public BufferedReader in;
    public PrintWriter out;

    public ClientThread(Socket socket,Server server) throws IOException {
        this.socket = socket;
        this.server=server;
        in=new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());
    }
    public void creareTest(){
        Faker faker=new Faker();

        for(int i=0;i<6;i++){
            Roommates roommate=new Roommates(faker.name().firstName(),faker.name().lastName());
            RoommateRepository.create(roommate);
        }
        List<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        for(int i=1;i<=6;i++){
            Collections.shuffle(list);
            for(Integer id:list){
                if(id==i){
                    Preferences prefer=new Preferences(i,6,list.indexOf(id));
                    PreferencesRepository.create(prefer);
                }
                else {
                    Preferences prefer=new Preferences(i,id,list.indexOf(id));
                    PreferencesRepository.create(prefer);
                }
            }
        }
    }
    public void closeThread(){
        System.exit(0);
    }
    public void sendMessage(String message){
        out.println(message);
        out.flush();
    }
    public void run() {

        try {
            //socket.setSoTimeout(600000);

            while (running) {
                // Get the request from the input stream: client → server
                System.out.println("Primesc comanda...");

                String request = in.readLine();
                System.out.println(request);
                String[] command = request.split(" ");
                // Send the response to the output stream: server → client

                if (command[0].equals("exit")) {
                    running = false;
                    out.println("Closing client!");
                    out.flush();
                    System.out.println("Closing client!");
                    break;
                    //socket.close();
                }else if(command[0].equals("exitServer")){
                    System.out.println("Closing server!");
                    server.closeServer();
                }
                else if (command[0].equals("solve")) { //////////////////////////////////////////////////////////////////////////////////////////////////////////
                    out.println("Problema rezolvata!");
                    out.flush();
                    Irving problema=new Irving();
                    System.out.println(problema.solve());
                    //System.out.println(problema.propolsals);
                    //System.out.println(problema.accepted);
                    //creareTest();
                } else if(command[0].equals("add")){
                    out.println("Adaugam student!");
                    out.flush();
                } else if (command[0].equals("modify")){
                    out.println("Modificam student!");
                    out.flush();
                }
                else if (command[0].equals("addp")){
                    out.println("Adaugam preferinta!");
                    out.flush();
                }
                else if (command[0].equals("delete")){
                    out.println("Stergem student!");
                    out.flush();
                }
                else {
                    out.println("Unknown command!");
                    out.flush();
                }

            }
        } catch (SocketException e) {
            running = false;
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close(); // or use try-with-resources
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
