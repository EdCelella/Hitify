package pkg;

import java.io.*;
import java.net.*;
import java.util.*;
import infopacket.InfoPacket;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.File.getAbsolutePath()

public class ServerRecieveHandler implements Runnable{

    Socket client;
	DataInputStream inFromClient;
	File chatFile;
	boolean runServer = true;

	public ServerRecieveHandler(DataInputStream _inFromClient, File _chatFile, Socket _client){

        client = _client;
		inFromClient = _inFromClient;
		chatFile = _chatFile;

	}

	public void run(){
		while(runServer){
   			try{
   				
    			String newMessage = inFromClient.readUTF();

    			List<String> lineBreakdown = Arrays.asList(newMessage.split("§"));

    			if(lineBreakdown.get(0).equals("E")){
    				runServer = false;
    			}

    			if (lineBreakdown.get(0).equals("T") || lineBreakdown.get(0).equals("F")){

    				FileWriter fileOut = new FileWriter(chatFile.getAbsolutePath(), true);
    				BufferedWriter bufferOut = new BufferedWriter(fileOut); 
                	bufferOut.write(newMessage);
                	bufferOut.newLine();
                	bufferOut.close();
                	fileOut.close();
                    
                    if (lineBreakdown.get(0).equals("F")){

                        inFromClient.close();

                        ObjectInputStream objectInFromClient = new ObjectInputStream(client.getInputStream());
                        InfoPacket fileIn = (InfoPacket) objectInFromClient.readObject();
                        objectInFromClient.close();

                        byte [] fileData = (byte []) inFromClient.GetByteData();

                        String saveSentFile = new File(("res/Chats/" + lineBreakdown.get(3)).getAbsolutePath());
                        FileOutputStream newFile = new FileOutputStream(saveSentFile);
                        newFile.write(fileData);

                        inFromClient = new DataInputStream(client.getInputStream());

                    }
                    
                    
                    
    			}


    		}catch(IOException e){
				System.out.println(e.getMessage());
			}
    	}
	}
    
}
