// New Thread from server to handle services

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicserver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.*;
import java.sql.DriverManager;
import infopacket.InfoPacket;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import database.Database;

/**
 *
 * @author samal
 */
public class MusicServerExtended extends Thread {
    
    private Socket client;
    public InfoPacket InFromClient = new InfoPacket();
    public InfoPacket ToClient = new InfoPacket();
    

//    //Constructor
//    MusicServerExtended()
//    {
//        
//    }
    
    public MusicServerExtended SetSocket(Socket client)
    {
        this.client = client;
        MusicServerExtended PassBack = new MusicServerExtended();
        return PassBack;
    }
    @Override
    public void run()
    {
        try {
            
            //Stating which client connected to
            System.out.println("Connected to " + client.getInetAddress());
            
            //Create an Input stream to send to user        
            ObjectInputStream FromClientStream = new ObjectInputStream(client.getInputStream());
            System.out.println("Made Input Stream");
            
            ObjectOutputStream ToClientStream = new ObjectOutputStream(client.getOutputStream());
            System.out.println("Made Output Stream");
            
            //Loop
            while(true) {
                Database db = new Database();
                //Accessing the DataBase
                        
                InFromClient = (InfoPacket) FromClientStream.readObject();
                System.out.println(InFromClient.GetService());
                
                //Login attempted
                if ("LGN".equals(InFromClient.GetService()))
                {
                    String UserName = InFromClient.GetArray().get(0);
                    String UserPassWord = InFromClient.GetArray().get(1);
                    //Get password from DB searching with username                                
                    String DBPassword = db.SelectLogInDetails(UserName);
                    System.out.println(UserName + " " + UserPassWord + " " + DBPassword);
                    
                    ToClient.SetService("LGN");
                    if (UserPassWord.equals(DBPassword))
                    {   
                        ToClient.SetSingleData("CORRECT");
                    }
                    else
                    {
                        ToClient.SetSingleData("INCORRECT");
                    }
                    //Send InfoPacket To user
                    ToClientStream.writeObject(ToClient);
                }
                //Create new user
                else if ("CNU".equals(InFromClient.GetService()))
                {
                    System.out.println("Creating New User");
                    //Retreive all information about user and add it to the DB
                    ArrayList UsersInfo = new ArrayList();
                    UsersInfo = InFromClient.GetArray();
                    
                    byte [] Image = (byte []) InFromClient.GetByteData();
                    
                    db.InsertNewRegUser(UsersInfo);
                    
                    String WhereToSave = "C:/Users/samal/Documents/2nd Year/Systems Software/Shitify/Sams Work/MusicServer/res/Photos/" + InFromClient.GetArray().get(0) + ".png";
                    FileOutputStream FileOut = new FileOutputStream(WhereToSave);
                    FileOut.write(Image);
                    System.out.println("Sucessfull");
                }
                
                //Update new song
                else if ("UNS".equals(InFromClient.GetService()))
                {
                    System.out.println("Uploading New Song");
                    
                    ArrayList SongInformation = InFromClient.GetArray();
                    String FileName = SongInformation.get(2) + "," + SongInformation.get(3);
                    
                    byte [] Song = (byte []) InFromClient.GetByteData();
                    String WhereToSaveSong = "C:/Users/samal/Documents/2nd Year/Systems Software/Shitify/Sams Work/MusicServer/res/Music/" + FileName + ".mp3";
                    FileOutputStream SongOut = new FileOutputStream(WhereToSaveSong);
                    SongOut.write(Song);
                    
                    byte [] CoverPhoto = (byte []) InFromClient.GetSecondData();
                    String WhereToSavePhoto= "C:/Users/samal/Documents/2nd Year/Systems Software/Shitify/Sams Work/MusicServer/res/Photos/" + FileName + ".png";
                    FileOutputStream PhotoOut = new FileOutputStream(WhereToSavePhoto);
                    PhotoOut.write(CoverPhoto);
                    
                    db.InsertSong(SongInformation);
                    db.InsertPost(SongInformation);
                    
                    
                    
                    System.out.println("Successfull");
                }
                
                else
                {
                    System.out.println("Not a valid command");
                }
                
                

                //Retreiving the password from the SQL Database
                
                //outToClient.writeUTF(DBPassword);
            }
            
        } catch (IOException e)
        {
            System.err.println("Error - " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MusicServerExtended.class.getName()).log(Level.SEVERE, null, ex);
        }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(MusicServerExtended.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }
   
}

//http://www.oracle.com/technetwork/java/socket-140484.html
//To make multithreaded server

