package pkg;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerHandler implements Runnable{

	//Declares global variables
	Socket client;
	DataOutputStream outToClient;
	DataInputStream inFromClient;

	public ServerHandler(Socket _client){

		try{
			//Gets Client socket data
			client = _client;
			//Input stream to recieve data from client
			inFromClient = new DataInputStream(client.getInputStream());
			//Output stream to send data from server
			outToClient = new DataOutputStream(client.getOutputStream());
		}
		catch(IOException e){
			//Prints error message
			System.out.println(e.getMessage());
		}
	}

	public void run(){

		try{
			//Reads data, converts message to uppercase and sends back
			String message = inFromClient.readUTF();
			message = message.toUpperCase();
			outToClient.writeUTF(message);
		}
		catch(IOException e){
			//Prints error message
			System.out.println(e.getMessage());
		}

    }
}				