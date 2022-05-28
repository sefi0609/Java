package com.example.multchat;

import java.net.ServerSocket;
import java.net.Socket;
// listen for incoming connections, and spawn a new thread for each one
// multiple clients can connect at the same time
public class ChatServer {
    public static void main(String[] args){
        // create a waiting room for the clients
        WaitingRoom wr = new WaitingRoom();
        try {
            ServerSocket srv = new ServerSocket(8080);
            System.out.println("Server is ready");
            while(!srv.isClosed()){
                Socket socket = srv.accept();
                // create a new thread for each client
                new ServerThread(socket,wr).start();
            }
            srv.close();
        } catch (Exception e){ e.printStackTrace(); }
    }
}
