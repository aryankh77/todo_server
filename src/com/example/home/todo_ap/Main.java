package com.example.home.todo_ap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(7878)) {
            while (true) {
                Socket socket = server.accept();
                System.out.println("salam");
                ClientHandler client = new ClientHandler(socket);
                client.start();
            }
        } catch (IOException e) {
            System.out.println("Server Failed");
        }
    }
}

class ClientHandler extends Thread {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
    }

    private void send(Request request) throws IOException {
        out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
    }

    private Request receive() throws IOException, ClassNotFoundException {
        in = new ObjectInputStream(socket.getInputStream());
        Request request= (Request) in.readObject();
        System.out.println(request.getMessage() + " " + request.getType());
        return request;
    }

    @Override
    public void run() {
        DataBase data = DataBase.getInstance();
        try {
            while (true) {
                Request request = receive();
                String typeOfRequest = request.getType();
                System.out.println(request.getMessage());
                String[] message = request.getMessage().split("\\s");
                switch (typeOfRequest) {
                    case "register":
                        String username = message[0];
                        String password = message[1];
                        String name = message[2];
                        String familyName = message[3];
                        String email = message[4];
                        String type = message[5];
                        Type type1 = null;
                        switch (type) {
                            case "Gold":
                                type1 = Type.golden;
                                break;
                            case "Silver":
                                type1 = Type.silver;
                                break;
                            case "Normal":
                                type1 = Type.normal;
                                break;
                        }
                        if (data.findByEmail(email) != null)
                            send(new Request("email exist", "exist", null));
                        else if (data.findByUsername(username) != null)
                            send(new Request("username exist", "exist", null));
                        else {
                            User userToAdd = new User(username, password, name, familyName, email, type1);;
                            data.addUser(userToAdd);
                            send(new Request("register success", "success", userToAdd));
                        }
                        break;
                    case "sign in":

                        boolean byEmail = message[0].matches("^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$");
                        username = message[0];
                        email = message[0];
                        password = message[1];
                        User user = byEmail ? data.findByEmail(email) : data.findByUsername(username);
                        if (user != null) {
                            if (user.getPassword().equals(password)) {
                                user.setLogin(true);
                                send(new Request("sign in success", "success", user));
                            } else
                                send(new Request("password not exist", "not exist", null));
                        } else
                            send(new Request("email or username not exist", "not exist", null));
                        break;
                    case "add task":
                        Serializable[] serializable = (Serializable[]) request.getSerializable();
                        TaskInfo taskToAdd= (TaskInfo) serializable[0];
                        user= (User) serializable[1];
                        //data.addTask(data.findByUsername(user.getUsername()),taskToAdd);
                        data.findByUsername(user.getUsername()).addTask(taskToAdd);
                        send(new Request("add task success","success",null));
                        break;
                    case "add task to all":
                        serializable= (Serializable[]) request.getSerializable();
                        taskToAdd= (TaskInfo) serializable[0];
                        user= (User) serializable[1];
                        data.addGoldenForAllTask(user,taskToAdd);
                        send(new Request("add task to all success","success",null));

                        break;
                    case "get tasks":
                        user= (User) request.getSerializable();
                        ArrayList<TaskInfo> arrayList=data.findByUsername(user.getUsername()).getTasks();
                        Collections.sort(arrayList);
                        send(new Request(null,"sucess",arrayList));
                        break;
                    case "delete task":
                        serializable = (Serializable[]) request.getSerializable();
                        TaskInfo taskToDelete= (TaskInfo) serializable[0];
                        user= (User) serializable[1];
                        try {
                            data.deleteTask(user, taskToDelete);
                        } catch (DeletException e) {
                            e.printStackTrace();
                        }
                        send(new Request("delete task success","success",null));
                        break;
                    case "delete task from all":
                        serializable= (Serializable[]) request.getSerializable();
                        taskToDelete = (TaskInfo) serializable[0];
                        user= (User) serializable[1];
                        try {
                            data.deleteGoldenForAllTask(user,taskToDelete);
                            send(new Request("delete task from all success","success",null));
                        } catch (DeletException e) {
                            send(new Request("user is not golden","not golden",null));
                        }
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            data.serialize();
            e.printStackTrace();
        }

    }
}
