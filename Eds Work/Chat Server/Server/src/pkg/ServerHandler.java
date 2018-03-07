package pkg;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerHandler implements Runnable{

	// Declares global variables
	Socket client;
	DataOutputStream outToClient;
	DataInputStream inFromClient;

	public ServerHandler(Socket _client){

		try{
			// Gets Client socket data
			client = _client;
			// Input stream to recieve data from client
			inFromClient = new DataInputStream(client.getInputStream());
			// Output stream to send data from server
			outToClient = new DataOutputStream(client.getOutputStream());
		}
		catch(IOException e){
			// Prints error message
			System.out.println(e.getMessage());
		}
	}

	public void run(){

		try{
			
			// Takes the username trying to connect and the username they want to 
			// connect to seperated by a comma
			String chatName = inFromClient.readUTF();

			// Seperates the two usernames by the comma 
			List<String> splitMessage = Arrays.asList(chatName.split(","));

			// Compars the two usernames alphabetically
			int compare = splitMessage.get(0).compareTo(splitMessage.get(1));

			// Joins the usernames back together in alphabaetical order which gives
			// the name of the chat history file
			if(compare < 0){
				chatName = splitMessage.get(0) + splitMessage.get(1);
			}else{
				chatName = splitMessage.get(1) + splitMessage.get(0);
			}

			// Gets the location of the file
			String fileName = "/Users/edwardcelella/Documents/University/Systems Software/Shitify/Eds Work/Chat Server/Chats/" + chatName + ".txt";

			// Checks if file exists (previous chat history)
			File checkFile = new File(fileName);

			if(!(checkFile.exists())){
				checkFile.createNewFile();
				outToClient.writeUTF("File created");

			}

			//Checks if message needs appending to file
			if( splitMessage.get(3).equals("Y") ){

				FileWriter fw = new FileWriter(checkFile.getAbsoluteFile(), true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.newLine();
				bw.write(splitMessage.get(4));
				bw.close();

			}
			
			// Created a buffered stream for the file
			FileReader fin = new FileReader(fileName); 
			BufferedReader din = new BufferedReader(fin);
			String line = null;

			// Outputs line by line to the user contents of the file
			while ((line = din.readLine()) != null) {
				outToClient.writeUTF(line);
			}

			//Closes file
			din.close();

		}
		catch(IOException e){
			// Prints error message
			System.out.println(e.getMessage());
		}

    }
}				