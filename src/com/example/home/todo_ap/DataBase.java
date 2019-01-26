package com.example.home.todo_ap;
import javafx.concurrent.Task;

import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class DataBase implements Serializable {

    private HashSet<User> users;


    //singleton
    private static DataBase instance;

    static {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("database.ser"))) {
            instance = (DataBase) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            instance = new DataBase();
        }
    }


    public void serialize() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("database.ser"))) {
            outputStream.writeObject(instance);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DataBase getInstance() {
        return instance;
    }


    private DataBase() {
        this.users = new HashSet<>();
    }


    public User findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }

    public User findByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) return user;
        }
        return null;
    }

    public boolean addUser(User user) {
        if (findByEmail(user.getEmail()) != null || findByUsername(user.getUsername()) != null)
            return false;
        users.add(user);
        return true;
    }


    public void addGoldenForAllTask(User user, TaskInfo task) {
        if (user.getType() == Type.golden) {
            for (User u : users) {
                u.addTask(task);
            }
        }
    }

    public void deleteGoldenForAllTask(User user, TaskInfo task) throws DeletException {
        if (user.getType() == Type.golden) {
            for (User u : users) {
                u.deleteTask(task);
            }
        }
    }


    public void addTask(User user, TaskInfo taskToAdd) {
        if (users.contains(user))
            user.addTask(taskToAdd);

    }

    public void deleteTask(User user, TaskInfo taskToDelete) throws DeletException {
        deleteTask(user, taskToDelete);

    }
}