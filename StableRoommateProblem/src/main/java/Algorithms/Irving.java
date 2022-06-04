package Algorithms;

import Repositories.RoommateRepository;
import Roommate.Roommates;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Irving {
    public List<Roommates> participants=new ArrayList<>();
    public List<Integer> propolsals; /// valoarea ii propune indexului sa fie prieteni
    public List<Integer> accepted; ///indexul accepta valoarea ca prieten

    public Irving() {
        this.participants = RoommateRepository.findAll();
        this.propolsals=new ArrayList<>(participants.size());
        this.accepted=new ArrayList<>(participants.size());
    }
    public void resetPrtitcipants(){
        this.participants=RoommateRepository.findAll();
    }
    public void solve(){
        resetPropolsals();
        //System.out.println("Hello");
        for(Roommates roommates:participants){
            roommates.setPreferences();
            roommates.setIndex(participants.indexOf(roommates)+1);
        }
        System.out.println(participants);
        for(Roommates roommates:participants){
            for(Iterator<Roommates> iterator= roommates.preferences.listIterator();iterator.hasNext();){
                Roommates proposed=iterator.next();
                if(propolsals.get(proposed.getIndex())==0){
                    propolsals.set(proposed.getIndex(), roommates.getIndex());
                    accepted.set(proposed.getIndex(), roommates.getIndex());
                    break;
                }
                else{
                    Roommates conflict=proposed;
                    int ok=0;
                    for(Roommates preferences:conflict.preferences){
                        if(preferences.getIndex()==propolsals.get(conflict.getIndex())){
                            ok=1;
                            break;
                        }
                        if(preferences.getIndex()== roommates.getIndex()){
                            ok=2;
                            break;
                        }
                    }
                    if(ok==1){
                        iterator.remove();
                        conflict.preferences.remove(roommates);
                    }
                    if(ok==2){
                        Roommates mate=participants.get(propolsals.get(conflict.getIndex())-1);
                        propolsals.set(conflict.getIndex(), roommates.getIndex());
                        accepted.set(proposed.getIndex(), roommates.getIndex());
                        int contin=1;
                        while(contin!=0) {
                            conflict.preferences.remove(mate);
                            mate.preferences.remove(conflict);
                            for(Iterator<Roommates> iterator1=mate.preferences.listIterator();iterator1.hasNext();){
                                Roommates preferences=iterator1.next();
                                if(propolsals.get(preferences.getIndex())==0){
                                    propolsals.set(preferences.getIndex(), mate.getIndex());
                                    accepted.set(preferences.getIndex(), mate.getIndex());
                                    contin=0;
                                    break;
                                }
                                else{
                                    conflict=preferences;
                                    ok=0;
                                    for(Roommates preferences2:conflict.preferences){
                                        if(preferences2.getIndex()==propolsals.get(conflict.getIndex())){
                                            ok=1;
                                            break;
                                        }
                                        if(preferences2.getIndex()== proposed.getIndex()){
                                            ok=2;
                                            break;
                                        }
                                    }
                                    if(ok==1){
                                        iterator1.remove();
                                        conflict.preferences.remove(mate);
                                    }
                                    else{
                                         Roommates aux=participants.get(propolsals.get(conflict.getIndex())-1);
                                         propolsals.set(conflict.getIndex(), mate.getIndex());
                                         accepted.set(conflict.getIndex(), mate.getIndex());
                                         mate=aux;
                                         break;
                                    }

                                }
                            }

                        }
                        break;
                    }
                }

            }
            System.out.println("Salut");


        }
        removePreferences();
    }

    private void removePreferences() {
        for(Roommates mate:participants){
            int ok=0;
            for(Iterator<Roommates> iterator=mate.preferences.listIterator();iterator.hasNext();){
                Roommates preferences=iterator.next();
                if(preferences.getIndex()==accepted.get(mate.getIndex())){
                    ok=1;
                }
                else
                    if(ok==1){
                        iterator.remove();
                    }
            }
        }
    }

    private void resetPropolsals() {
        propolsals.clear();
        for(int nr=0;nr<=participants.size();nr++){
            propolsals.add(0);
            accepted.add(0);
        }
    }
}
