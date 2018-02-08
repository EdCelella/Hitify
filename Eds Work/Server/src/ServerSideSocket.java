import java.io.*;
import java.net.*;
import java.util.*;

public class ServerSideSocket {
	public static void main(String[] args){

		try{

			//Sets a port number for the server socket
			ServerSocket server = new ServerSocket(9090);

			while (true) {

				//Creats socket to listen for requests
				Socket client = server.accept();

				//Input stream to recieve data from client
				DataInputStream inFromClient = new DataInputStream(client.getInputStream());
				//Output strean to send data from server
			    DataOutputStream outToClient = new DataOutputStream(client.getOutputStream());

			    InetAddress obj = client.getInetAddress();
			    System.out.println("Request from: " + obj);

			    //Reads data, converts to uppercase, and sends data back
		   		String message = inFromClient.readUTF();
		   		
		   		System.out.println("Recieved from client: " + message);

		   		//message = "Message recieved.";
			    //outToClient.writeUTF(message);
			    
			}
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}

	}  
}
