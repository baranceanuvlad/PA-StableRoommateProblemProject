package Threads;

import Algorithms.Irving;
import Repositories.PreferencesRepository;
import Repositories.RoommateRepository;
import Roommate.Preferences;
import Roommate.Roommates;
import UI.MainFrame;
import Utilities.PersistanceManager;
import com.github.javafaker.Faker;

import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;

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
    /*public void creareTest(){
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
    }*/
    public void closeThread(){
        System.exit(0);
    }
    public void sendMessage(String message){
        out.println(message);
        out.flush();
    }
    public void run() {

        try {
            while (running) {
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
                else if (command[0].equals("solve")) {
                    Irving problema=new Irving();
                    Map<Roommates,Roommates> map=problema.solve();
                    if(map==null)
                    {
                        out.println("Nu este configuratie stabila!");
                        out.flush();
                    }
                    else {
                        StringBuilder ans = new StringBuilder();
                        ans.append('#').append('\n');
                        int nr=0;
                        for (Map.Entry<Roommates, Roommates> entry : map.entrySet()) {
                            nr++;
                            ans.append(nr).append(". ");
                            ans.append(entry.getKey().getFirstname()).append(" ").append(entry.getKey().getLastname()).append(" -> ")
                                    .append(entry.getValue().getFirstname()).append(" ").append(entry.getValue().getLastname()).append('\n');
                        }
                        out.println(ans.toString());
                        out.flush();
                    }
                } else if(command[0].equals("add")){
                    String firstName=command[1];
                    String lastName=command[2];
                    List<String> pref=new ArrayList<>();
                    for(int i=3;i<command.length;i++) {
                        pref.add(command[i]);
                    }
                    ///////////////////////////////////////////////////////////////////////////////////////////////////
                    RoommateRepository.create(new Roommates(firstName,lastName));
                    Roommates roommates=RoommateRepository.findByFirstNameLastName(firstName,lastName);
                    for(int i=0;i<pref.size();i++)
                        PreferencesRepository.create(new Preferences(roommates.getId(),RoommateRepository.findByFirstNameLastName(pref.get(i),pref.get(i+1)).getId(),i/2+1));

                    out.println("Am adaugat student!");
                    out.flush();
                } else if (command[0].equals("modify")){ ////////
                    String firstName=command[1];
                    String lastName=command[2];
                    List<String> pref=new ArrayList<>();
                    for(int i=3;i<command.length;i++)
                        pref.add(command[i]);
                    Roommates roommates=RoommateRepository.findByFirstNameLastName(firstName,lastName);
                    PreferencesRepository.deletePreferences(roommates.getId());
                    for(int i=0;i<pref.size();i+=2)
                        PreferencesRepository.create(new Preferences(roommates.getId(),RoommateRepository.findByFirstNameLastName(pref.get(i),pref.get(i+1)).getId(),i/2+1));
                    out.println("Am modificat student!");
                    out.flush();
                }
                else if (command[0].equals("addp")){
                    String firstName1=command[1];
                    String lastName1=command[2];
                    String firstName2=command[3];
                    String lastName2=command[4];
                    Integer poz=Integer.valueOf(command[5]);
                    Roommates roommates1=RoommateRepository.findByFirstNameLastName(firstName1,lastName1);
                    Roommates roommates2=RoommateRepository.findByFirstNameLastName(firstName2,lastName2);
                    PreferencesRepository.updatePreferences(roommates1.getId(),poz);
                    PreferencesRepository.create(new Preferences((roommates1.getId()),roommates2.getId(),poz));
                    out.println("Am adaugat preferinta!");
                    out.flush();
                }
                else if (command[0].equals("show")){
                    String firstName=command[1];
                    String lastName=command[2];
                    Roommates roommates=RoommateRepository.findByFirstNameLastName(firstName,lastName);
                    List<Roommates> lista=RoommateRepository.findPreferences(roommates.getId());

                    int nr=0;
                    StringBuilder ans=new StringBuilder();
                    ans.append('#').append('\n');
                    for(int i=0;i<lista.size();i++)
                    {
                        nr++;
                        ans.append(nr).append(". ");
                        ans.append(lista.get(i).getFirstname()).append(" ").append(lista.get(i).getLastname()).append('\n');
                    }
                    out.println(ans.toString());
                    out.flush();
                }
                else if (command[0].equals("delete")){
                    String firstName=command[1];
                    String lastName=command[2];
                    Roommates roommates=RoommateRepository.findByFirstNameLastName(firstName,lastName);
                    PreferencesRepository.deleteSPreferences(roommates.getId());
                    PersistanceManager.getEm().getTransaction().begin();
                    PersistanceManager.getEm().remove(roommates);
                    PersistanceManager.getEm().getTransaction().commit();
                    out.println("Am sters studentul!");
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
