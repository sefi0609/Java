package com.example.multchat;
// class to represent a waiting room
public class WaitingRoom {
    private ServerThread firstClient = null;
    private ServerThread secondClient = null;
    private ServerThread tempClient = null;
    private boolean firstClientReady = true;
    private int numberOfClients;
    // main entry point, start the waiting room, return handleFirst() or handleSecond()
    public synchronized ServerThread checkNumOfClient(ServerThread c) {
        if (firstClient == null) {
            firstClient = c;
            return handleFirst(c);
        }
        else {
            secondClient = c;
            return handleSecond(c);
        }
    }
    // handle the first client
   public synchronized ServerThread handleFirst(ServerThread c){
       numberOfClients = 0;
        try {
            // wait for the second client to connect
            wait();
            numberOfClients++;
            // check if the client is disconnected
            return secondClient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // handle the second client
    public synchronized ServerThread handleSecond(ServerThread c){
        notifyAll();
        try {
            wait();
        } catch (InterruptedException e) {
            System.out.println("error in waiting room handleSecond");
        }
        if (firstClientReady) {
            // clear the waiting room for the next clients
            tempClient = firstClient;
            closeWaitingRoom();
            numberOfClients++;
            return tempClient;
        }
        else{
            firstClient = c;
            setFirstClient(true);
            return handleFirst(c);
            }
        }
    // set the first client to true or false
    public synchronized void setFirstClient(boolean b){ firstClientReady = b; }
    // close the waiting room, set the clients to null, wait for new clients
    public synchronized void closeWaitingRoom(){
        firstClient = null;
        secondClient = null;
    }
    // notify the waiting room that the client needs to wake up
    public synchronized void notifyClient(){ notifyAll(); }
    // return the number of clients
    public synchronized int getNumberOfClients(){ return numberOfClients; }
    // set the number of clients
    public synchronized void setNumberOfClients(int n){ numberOfClients = n; }
}

