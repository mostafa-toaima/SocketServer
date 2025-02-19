package testServerSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class testSocketServer {
    public static void main(String[] args) {
        try (ServerSocket srSocket = new ServerSocket(7777);) {
            System.out.println("Server started");
            HashMap<String , Thread> runningThreads
                    = new HashMap<String ,Thread>();
            while (true) {
                System.out.println("Server is waiting for new client");
                Socket scFromClient = srSocket.accept();
                SocketHandler socketHandler = new SocketHandler(scFromClient);
                /*
                * if socket handler class doesn't extend thread but implement Runnable
                * Start not exist in Runnable
                * in this case u need take object from thread  like second comment
                * if u choose between implement Runnable and extend thread ?
                * extend thread close for u any extend because class extend one class
                * but implement runnable take u free to extend any class with ur tree
                * if you have same thing extend and can u implement make implement
                */
                //socketHandler.start(); // use this if extend thread in socket handler because u have start method
                // start  => start thread

                Thread t = new Thread(socketHandler);
                runningThreads.put(
                        String.valueOf(
                                scFromClient.getInetAddress().getHostName()),t);
                t.start();
                System.out.println(SocketHandler.getCounter() +" Clients connected");
            }
        }catch (IOException ex ){
            System.err.println("Server Can't started : " + ex.getMessage());
        }
    }
}