package testClientSocket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class testSocketClient {

    public static void main(String[] args) {
        try(
                Socket mySocket = new Socket("192.168.1.7", 7777);

                InputStream myInputStream = mySocket.getInputStream();
                OutputStream myOutPutStream  = mySocket.getOutputStream();

                Reader r = new InputStreamReader(myInputStream);
                BufferedReader fromServerReader = new BufferedReader(r);

                PrintStream toServerPrinter= new PrintStream(myOutPutStream);
                Scanner keyboardScanner = new Scanner(System.in);
                )
        {
            while (true){
             System.out.print("MY MESSAGE : ");
             String myMessage = keyboardScanner.nextLine();
             toServerPrinter.println(myMessage);
             String serverReply = fromServerReader.readLine();
             System.out.println("SERVER : " + serverReply);
             if (serverReply.toLowerCase().trim().equals("bye")) break;
            }
        }catch (IOException ex){
            System.err.println("IOException : " + ex.getMessage());
        }
    }

}
