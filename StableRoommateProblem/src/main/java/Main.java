import Algorithms.Irving;
import Repositories.PreferencesRepository;
import Repositories.RoommateRepository;
import Roommate.Preferences;
import Roommate.Roommates;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String args[]){

        Irving problema=new Irving();
        problema.solve();
        System.out.println(problema.propolsals);
        System.out.println(problema.accepted);
        //creareTest();

    }

    static void creareTest(){
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
}
