package Algorithms;

import Repositories.RoommateRepository;
import Roommate.Roommates;

import java.util.ArrayList;
import java.util.List;

public class Irving {
    public List<Roommates> participants=new ArrayList<>();

    public Irving() {
        this.participants = RoommateRepository.findAll();
    }
    public void resetPrtitcipants(){
        this.participants=RoommateRepository.findAll();
    }
    public void solve(){
        System.out.println("Hello");
        System.out.println(participants);
    }
}
