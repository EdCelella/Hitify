package chatserver;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class ServerSendHandler implements Runnable{

    // Declares global variables
    Socket client;
    DataOutputStream outToClient;
    DataInputStream inFromClient;

    String chatFilePath;
    File chatFile;

    public ServerSendHandler(Socket _client){

        try{
            // Creates output and input stream to the client
            client = _client;
            inFromClient = new DataInputStream(client.getInputStream());
            outToClient = new DataOutputStream(client.getOutputStream());
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void run(){

        try{

            // Gets chat file name and size of file from client
            String chatName = inFromClient.readUTF();
            int clientLineCount = Integer.parseInt(inFromClient.readUTF());

            // Opens file
            chatFilePath = new File("Chats/" + chatName + ".txt").getAbsolutePath();
            chatFile = new File(chatFilePath);

            // Checks if file exists
            if(!(chatFile.exists())){
                    // Creates new file if it doesn't exist
                    chatFile.createNewFile();
            }

            //Stores the date of the last modification to the file
            long fileModTime = chatFile.lastModified();


            // Outputs new lines to the client 
            clientLineCount = sendMessages(clientLineCount, chatFile);


            // Runs thread which continuously updates message table
            Thread th2 = new Thread(new ServerRecieveHandler(inFromClient, chatFile, client));
            th2.start();

            while(th2.getState()!=Thread.State.TERMINATED){
                if(fileModTime != chatFile.lastModified()){

                    clientLineCount = sendMessages(clientLineCount, chatFile);
                    fileModTime = chatFile.lastModified();

                }
            }
        }
        catch(IOException e){
                System.out.println(e.getMessage());
        }
}


public int sendMessages(int clientLineCount, File chatFile){

    int currentLine = 0;

    try{

        // Creates a file reader 
        FileReader fileIn = new FileReader(chatFile); 
        BufferedReader bufferIn = new BufferedReader(fileIn);
        String line = null;

        // Iterates through each line of the file sending to the client all lines which
        // dont have locally
        while ((line = bufferIn.readLine()) != null) {
            
            if (currentLine >= clientLineCount){
               
                outToClient.writeUTF(line);
                
                List<String> lineBreakdown = Arrays.asList(line.split("§"));
                if(lineBreakdown.get(0).equals("F")){
                    String filePath = "Chats/Files/" + lineBreakdown.get(4);
                    FileInputStream sendFile;
                    sendFile = new FileInputStream(filePath);
                    byte [] fileBytes = new byte[sendFile.available()];
                    sendFile.read(fileBytes);
                    outToClient.write(fileBytes, 0, fileBytes.length);
                    sendFile.close();
                    File deleteFile = new File(filePath);
                    deleteFile.delete();
                }
                
            }
            currentLine += 1;
        }

                    // Closes file reader 
        bufferIn.close();
        fileIn.close();

    }catch(IOException e){
        System.out.println(e.getMessage());
    }

    return currentLine;

}

}
