package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        // write your code here
        try (ServerSocket server = new ServerSocket(1234)) {
            Socket socket=server.accept();
            ClientHandler client=new ClientHandler(socket);
            client.start();
        } catch (IOException e) {
            System.out.println("Server Failed");
        }
    }
}

class ClientHandler extends Thread{
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        out=new ObjectOutputStream(socket.getOutputStream());
        in=new ObjectInputStream(socket.getInputStream());
    }

    private void send(Request request) throws IOException {
        out.writeObject(request);
    }

    private Request recieve() throws IOException, ClassNotFoundException {
        return (Request) in.readObject();
    }

    @Override
    public void run(){
        DataBase data=DataBase.getInstance();
        try {
        while (true) {
            Request request=recieve();
            String typeOfRequest=request.getType();
            switch (typeOfRequest) {
                case "Regester":


                //todo tregester o singupp o task o tarof mikonm

            }
        }} catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
