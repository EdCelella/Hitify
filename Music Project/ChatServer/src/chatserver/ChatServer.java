package chatserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    public static void main(String[] args) {
        
        try{
            // Sets a port number for the server socket
            ServerSocket server = new ServerSocket(9091);
            while(true){
                System.out.println("Waiting for client connection...");
                // Creats socket to listen for requests
                Socket client = server.accept();
                System.out.println("Connected to: " + client.getInetAddress());
                // Creates and runs new thread
                Thread th = new Thread(new ServerSendHandler(client));
                th.start();
            }	
        }
        catch(IOException e){
            // Prints error message
            System.out.println(e.getMessage());
        }
        
    }
    
}
