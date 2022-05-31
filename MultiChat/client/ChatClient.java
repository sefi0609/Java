package com.example.multchat;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
// class to represent the chat client
public class ChatClient {
    private BufferedWriter out;
    private BufferedReader in;
    private Socket socket;
    private boolean isConnected = false;
    // constructor
    public ChatClient(String host, int port, String username) throws IOException {
        // this socket is connected to the server's thread, the thread is communicating with the other server thread
        socket = new Socket(host, port);
        socket.setSoTimeout(30000);
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // send the username to the server
        writeToBuffer(username);
    }
    //close all the resources
    public void closeResources() {
        try {
            if (out != null)
                out.close();
            if (in != null)
                in.close();
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error closing resources");
        }
    }
    // send message to the chat buddy (server tread)
    public void writeToBuffer(String s) throws IOException {
        out.write(s);
        out.newLine();
        out.flush();
    }
    // read message from the chat buddy (server tread)
    public String readFromBuffer() {
        try {
            return in.readLine();
        } catch (IOException e) {
            closeResources();
            if (e.getMessage().equals("Read timed out"))
                return "timeout"; // if the server can't find a chat buddy
            return null;
        }
    }
    // check if the client is connected
    public boolean socketIsAlive() { return socket.isConnected() && !socket.isClosed(); }
    // listen to the chat buddy until he disconnects, it's a thread so the GUI will not freeze
    // the controller object is pass to this function to controll the GUI   
    public void listenForMessages( ChatController c) {
        new Thread(() -> {
            String msgFromChat;
            while (socketIsAlive()) {// read message from the chat buddy
                msgFromChat = readFromBuffer();
                if(msgFromChat != null) {
                    // check if it's the second client, adjust the GUI, disable socket time out and set the flag
                    if (!msgFromChat.equals("") && isConnected == false && !msgFromChat.equals("timeout")) {
                        isConnected = true;
                        c.uiStateConnected();
                        c.setTextArea(msgFromChat + "\n");
                        try {
                            // disable timeout
                            socket.setSoTimeout(0);
                        } catch (SocketException e) {
                            e.printStackTrace();
                        }
                    }
                    // if the chat buddy disconnects, close all the resources and tell the controller (client)
                    else if (msgFromChat.equals("Disconnected")) {
                        closeResources();
                        c.uiStateDisconnected();
                        c.setTextArea("The connection has ended by the other client");
                        c.initialize();
                    }// if there is timeout, close all the resources and tell the controller (client) that the server is not responding
                    else if (msgFromChat.equals("timeout")) {
                        closeResources();
                        c.uiStateDisconnected();
                        c.setTextArea("The connection has ended because the server isn't responding");
                        c.initialize();
                    }
                    // if all is ok, set the text area for the client
                    else
                        c.setTextArea(msgFromChat + "\n");
                }
                // if the message is null, close all the resources
                else {
                    c.uiStateDisconnected();
                    closeResources();
                }
            }
        }).start();
    }
}
