import java.io.*;
import java.net.*;
import java.util.*;

public class ClientSocket {
	public static void main(String[] args){

		try{

			//Takes user input for message
			System.out.println("Enter a message to send to the server: ");
            String message;
            Scanner Reader = new Scanner(System.in);
            message = Reader.nextLine();
            Reader.close();

			//Specifies the address of the server is of a InetAddress structure
		    InetAddress address = InetAddress.getByName("localhost");

		    //Sets the address of the server
		    Socket server = new Socket(address, 9090);

		    //Creates input stream from server
		    DataInputStream inFromServer = new DataInputStream(server.getInputStream());
		    //Creates output stream to server
		    DataOutputStream outToServer = new DataOutputStream(server.getOutputStream()); 

		    //Sends args[0] string to server
		    outToServer.writeUTF(message);

		    while(true){
			    //Recieves string from server
			    String text = inFromServer.readUTF();

			    //Prints message from server
			    System.out.println(text);
			}

		    //Closes server socket.
		    //server.close();
		}
		catch(IOException e){
			//lol
		}

	}

}
        