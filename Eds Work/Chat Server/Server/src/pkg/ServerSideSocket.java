package pkg;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerSideSocket{

	public static void main(String[] args){

		try{

			//Sets a port number for the server socket
			ServerSocket server = new ServerSocket(9090);

			while(true){

				System.out.println("Waiting for client connection...");

				//Creats socket to listen for requests
				Socket client = server.accept();

				System.out.println("Connected to: " + client.getInetAddress());

				//Creates and runs new thread
		   		Thread th = new Thread(new ServerHandler(client));
		   		th.start();

			}	
		}
		catch(IOException e){
			//Prints error message
			System.out.println(e.getMessage());
		}
	}  
}