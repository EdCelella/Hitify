package chatserver;


import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import static java.lang.System.in;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

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
                
                // Reads and splits message
                String newMessage = inFromClient.readUTF();
                List<String> lineBreakdown = Arrays.asList(newMessage.split("§"));
                
                // Closes thread if chat window is closed
                if(lineBreakdown.get(0).equals("E")){
                        runServer = false;
                }
                
                // Runs if message or file is sent
                if (lineBreakdown.get(0).equals("T") || lineBreakdown.get(0).equals("F")){
                    
                    // Writes new message to text file
                    FileWriter fileOut = new FileWriter(chatFile.getAbsolutePath(), true);
                    BufferedWriter bufferOut = new BufferedWriter(fileOut); 
                    bufferOut.write(newMessage);
                    bufferOut.newLine();
                    bufferOut.close();
                    fileOut.close();
                    
                    // Runs if file is sent
                    if (lineBreakdown.get(0).equals("F")){
                        
                        // Creates new buffer for file
                        byte[] fileBtyes = new byte[Integer.parseInt(lineBreakdown.get(3))];
                        
                        // Reads byte array in from client
                        inFromClient.readFully(fileBtyes, 0, fileBtyes.length);
                        
                        // Saves file
                        File fileSavePath = new File("Chats/Files/" + lineBreakdown.get(4));
                        FileOutputStream FileOut = new FileOutputStream(fileSavePath);
                        FileOut.write(fileBtyes);
                        FileOut.close();

                    }
                }
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }  
}
