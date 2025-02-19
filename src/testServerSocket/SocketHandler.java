package testServerSocket;

import java.io.*;
import java.net.Socket;

//public class SocketHandler extends Thread {
public class SocketHandler implements Runnable {
    private Socket socketFromClient;
    private static int counter = 0;
    public SocketHandler(Socket clientSocket){
        this.socketFromClient =clientSocket;
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    @Override
    public void run() {
        this.handleSocket();
    }

    private void handleSocket(){
        try (
                InputStream clientInputStream = this.socketFromClient.getInputStream();
                Reader r = new InputStreamReader(clientInputStream);
                BufferedReader fromClient = new BufferedReader(r);

                OutputStream clientOutputStream = this.socketFromClient.getOutputStream();
                PrintStream toClient = new PrintStream(clientOutputStream);
        ) {
            SmartReplyEngine engine = new SmartReplyEngine();
            while (true) {
                String inputLine = fromClient.readLine();
                String serverMessage = engine.reply(inputLine);
                System.out.println("CLIENT : " + inputLine);
                Thread.sleep(1000); // delay server response
                System.out.println("SERVER : " + serverMessage);
                toClient.println(serverMessage);
                if (serverMessage.toLowerCase().trim().equals("bye")) break;
            }
        } catch (IOException ex) {
            System.err.println("IOException : " + ex.getMessage());
        } catch (InterruptedException e) {
            System.err.println("can't sleep " + e.getMessage());
        } finally {
            try {
                socketFromClient.close();
                System.out.println("Client connection closed");
            } catch (IOException e) {
                System.err.println("Client can't close" + e.getMessage());
            }
            counter--;
            System.out.println(getCounter() + " Clients connected");
        }
        System.out.println("---------------------------------------");
    }

}
