package com.example.multchat;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
// thread to handle each client connection
public class ServerThread extends Thread{
    private BufferedWriter out;
    private BufferedReader in;
    private WaitingRoom wr;
    private ServerThread target;
    private Socket socket;
    private String username;
    private String message;

    public ServerThread(Socket s, WaitingRoom wr) {
        try {
            socket = s;
            setTimeOut(1000);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.wr = wr;
            username = in.readLine();
        } catch (IOException e) { e.printStackTrace(); }
    }
    public void run(){
        while(true) {
            if (!socketIsAlive()) {
                closeResources();
                break;
            }
            else {
                sendMassageTo(this, "Waiting for someone to chat with...\n");
                // sending the client to the waiting room, waiting for another client (two client per chat)
                target = wr.checkNumOfClient(this);
                // the thread checks if the message is null, if it is, the client has closed the socket
                // if it's timeout the target is waiting (*)
                message = readFromBuffer();
                //
                if (message != null && (!message.equals("Disconnected") || message.equals("timeout"))) {
                    sendMassageTo(this, "Connection established, you are now chatting with " + target.getUsername());
                    if(wr.getNumberOfClients() == 2)
                        wr.setNumberOfClients(0); // the second client clean the waiting room
                    else
                        wr.notifyClient(); // the first thread wakes up the second client (thread) after checking (*)
                    // reset the time out to infinity 
                    setTimeOut(0);
                    // listen to the client send to target
                    while (socketIsAlive() && target.socketIsAlive()) {
                        sendMassageTo(target, readFromBuffer());
                    }
                }
                else{
                    // if disconnected or a thread without a client
                    wr.setFirstClient(false);
                    wr.notifyClient();
                }
                closeResources();
            }
            break;
        }
    }
    // send a message to the target
    public void sendMassageTo(ServerThread to,String message){
        // message equals null if the buffered reader is closed. in this case we will terminate
        if (message == null){
            closeResources();
            return;
        }
        to.writeToBuffer(message);
    }
    // close all the resources
    public void closeResources(){
        try {
            if(out != null)
                out.close();
            if(in != null)
                in.close();
            if(socket != null)
                socket.close();
        } catch (IOException e) { e.printStackTrace(); }
    }
    // returns true if the socket is connected, and still hasn't closed else returns false
    public boolean socketIsAlive() { return socket.isConnected() && !socket.isClosed(); }
    // send s to the client (not target)
    public void writeToBuffer(String s){
        try {
            out.write(s);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            System.out.println("Error writing to buffer");
        }
    }
    // read from the client (not target)
    public String readFromBuffer() {
        try {
            return in.readLine();
        } catch (IOException e) {
            if (e.getMessage().equals("Read timed out"))
                return "timeout";
            return null;
        }
    }
    // return the username of the client
    public String getUsername(){ return username; }
    // set time out for socket
    public void setTimeOut(int timeOut) {
        try {
            socket.setSoTimeout(timeOut);
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("Error setting timeout");
        }
    }
}
