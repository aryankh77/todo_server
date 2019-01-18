package com.company;

import java.util.ArrayList;
import java.util.HashSet;

public class DataBase {

    private HashSet<User> users;


    //singleton
    private static final DataBase instance = new DataBase();

    public static DataBase getInstance() {
        return instance;
    }


    private DataBase() {
        this.users = new HashSet<>();
    }


    public User findByUsername(String username){
        for (User user : users) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }
    public User findByEmail(String email){
        for (User user : users) {
            if (user.getEmail().equals(email)) return user;
        }
        return null;
    }

    private boolean addUser(User user) {
        if(findByEmail(user.getEmail())!=null||findByUsername(user.getUsername())!=null)
            return false;
        users.add(user);
        return true;
    }

    public void addGoldenForAllTask(User user,TaskInfo task){
        if (user.getAnva()==anva.golden){
        for(User u :users) {
            u.addTask(task);
        }
        }
    }

}
