package chatserver;


import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import infopacket.InfoPacket;
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
                        /*
                        DatagramSocket server = new DatagramSocket(9092);
                        
                        byte[] data = new byte[length];
                        DatagramPacket receivedPacket = new DatagramPacket(data, length);
                        server.receive(receivedPacket);
                        data = receivedPacket.getData();
                        */
                        /*
                        int length = Integer.parseInt(inFromClient.readUTF());
                        int count;
                        byte[] data = new byte[length]; // or 4096, or more
                        int co;
                        while ((co = in.read(data)) > 0)
                        {
                            inFromClient.read(data, 0, co);
                        }
                        */
                        
                        //String saveSentFile = new File("Chats/" + lineBreakdown.get(3)).getAbsolutePath();
                        //FileOutputStream newFile = new FileOutputStream(saveSentFile);
                        //newFile.write(data);
                        
                        //server.close();
                        
                        /*
                        inFromClient.close();

                        ObjectInputStream objectInFromClient = new ObjectInputStream(client.getInputStream());
                        InfoPacket fileIn = (InfoPacket) objectInFromClient.readObject();
                        objectInFromClient.close();

                        byte [] fileData = (byte []) fileIn.GetByteData();

                        String saveSentFile = new File("res/Chats/" + lineBreakdown.get(3)).getAbsolutePath();
                        FileOutputStream newFile = new FileOutputStream(saveSentFile);
                        newFile.write(fileData);

                        inFromClient = new DataInputStream(client.getInputStream());
                        */
                        

                    }
                }
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }  
}
