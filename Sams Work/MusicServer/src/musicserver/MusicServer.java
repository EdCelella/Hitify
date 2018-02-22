//Java Shitify Server
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import infopacket.InfoPacket;

/**
 *
 * @author samal
 */
public class MusicServer {

        public static void main(String[] args) {
        
        
        try
        {
            ServerSocket server = new ServerSocket(9090);


            
            while (true) 
                    {  
                        //Always waiting for a client. 
                        //When a client connects create a new thread to handle the service
                        //Then start the thread. And continue to wait for more clients;
                        System.out.println("Waiting for client...");
                        //New Thread
                        MusicServerExtended C = new MusicServerExtended();  
                        C.SetSocket(server.accept());
                        Thread t1 = new Thread(C);
                        t1.start();                       
                    }            
        }
        //Catch any IOExceptions and print the error
        catch (IOException e)
        {
            System.err.println("Error - " + e.getMessage());
        }
         
    }
    }


