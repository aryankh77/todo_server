package com.company;

import java.util.ArrayList;
import java.util.Objects;

class User {
    private String username;
    private String password;
    private String name;
    private String familyName;
    private String email;
    private boolean isLogin;
    private anva anva;
    private  ArrayList<TaskInfo> tasks;


    public User(String username, String password, String name, String familyName, String email, com.company.anva anva) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.familyName = familyName;
        this.email = email;
        this.anva = anva;
        tasks = new ArrayList<>();
    }


    public void deleteTask(TaskInfo task){
        tasks.remove(task);
    }
    public void addTask(TaskInfo task){
        tasks.add(task);
    }

    public boolean getIsLogin() {
        return isLogin;
    }

    public void setAnva(com.company.anva anva) {
        this.anva = anva;
    }

    public com.company.anva getAnva() {
        return anva;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public boolean isLogin() {
        return isLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }
}