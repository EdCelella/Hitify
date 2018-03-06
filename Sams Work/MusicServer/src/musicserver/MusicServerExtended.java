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
import java.io.FileInputStream;
import java.net.InetSocketAddress;

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
            //System.out.println("Connected to " + client.getInetAddress());
            
            //Create an Input stream to send to user        
            ObjectInputStream FromClientStream = new ObjectInputStream(client.getInputStream());
            
            
            ObjectOutputStream ToClientStream = new ObjectOutputStream(client.getOutputStream());
            
            
            //Loop
            
            Database db = new Database();
            //Accessing the DataBase

            InFromClient = (InfoPacket) FromClientStream.readObject();
            //System.out.println(InFromClient.GetService());

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

                    //Get IP Address
                    String ip = client.getInetAddress().toString().replace("/","");
                    System.out.println(ip);
                    db.InsertActiveMember(UserName, ip);
                    //Insert username and IP address into Active Members

                }
                else
                {
                    ToClient.SetSingleData("INCORRECT");
                }
                //Send InfoPacket To user
                ToClientStream.writeObject(ToClient);

            }
            //When user logs out
            else if ("LGO".equals(InFromClient.GetService()))
            {
                System.out.println("Log Out Command");
                db.RemoveActiveMember(InFromClient.GetData());

                InfoPacket Reply = new InfoPacket();
                Reply.SetService("LGO");
                Reply.SetSingleData("Logout");
                ToClientStream.writeObject(Reply);
            }
            //Create new user
            else if ("CNU".equals(InFromClient.GetService()))
            {
                System.out.println("Creating New User");
                //Retreive all information about user and add it to the DB
                ArrayList UsersInfo = InFromClient.GetArray();
                db.InsertNewRegUser(UsersInfo);
                byte [] Image = (byte []) InFromClient.GetByteData();

                String WhereToSave = "C:/Users/samal/Documents/2nd Year/Systems Software/Shitify/Sams Work/MusicServer/res/Photos/" + InFromClient.GetArray().get(0) + ".png";
                FileOutputStream FileOut = new FileOutputStream(WhereToSave);
                FileOut.write(Image);
                
                InfoPacket Reply = new InfoPacket();
                Reply.SetService("LGO");
                Reply.SetSingleData("Logout");
                ToClientStream.writeObject(Reply);
                
                System.out.println("Sucessfull");
            }

            //Upload new song
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

            

                //System.out.println("Successfull");
            } 
            //Upload new post
            else if ("UNP".equals(InFromClient.GetService()))
            {
                db.InsertPost(InFromClient.GetArray());
                InfoPacket Reply = new InfoPacket();
                Reply.SetService("GMF");
                Reply.SetSingleData("Added Post");
                ToClientStream.writeObject(Reply);
            }
            //Get My Friends
            else if ("GMF".equals(InFromClient.GetService()))
            {
                System.out.println("MusicServer GMF Username: " + InFromClient.GetData());
                ArrayList<String> UsersFriends = db.GetUsersFriends(InFromClient.GetData());
                InfoPacket Reply = new InfoPacket();
                Reply.SetService("GMF");
                Reply.SetArray(UsersFriends);
                ToClientStream.writeObject(Reply);
                
            }
            //Get Active Friends
            else if ("GAF".equals(InFromClient.GetService()))
            {
                
            }
            //New Friend Request
            else if ("NFR".equals(InFromClient.GetService()))
            {
                ArrayList<String> Users = InFromClient.GetArray();
                db.NewFriendRequest(Users);
                InfoPacket Reply = new InfoPacket();
                Reply.SetService("NFR");
                Reply.SetSingleData("Send Request");
                ToClientStream.writeObject(Reply);
            }
            //Get Friend Requests
            else if ("GFR".equals(InFromClient.GetService()))
            {
                ArrayList<String> UsersFriendRequests = db.GetUsersFriendRequests(InFromClient.GetData());
                InfoPacket Reply = new InfoPacket();
                Reply.SetService("GFR");
                Reply.SetArray(UsersFriendRequests);
                ToClientStream.writeObject(Reply);
            }
            //Accept Friend Request
            else if ("AFR".equals(InFromClient.GetService()))
            {
                db.AcceptFriendRequest(InFromClient.GetArray());
                InfoPacket Reply = new InfoPacket();
                Reply.SetService("GFR");
                Reply.SetSingleData("Accepted");
                ToClientStream.writeObject(Reply);
            }
            //Decline Friend Request
            else if ("DFR".equals(InFromClient.GetService()))
            {
                db.DeclineFriendRequest(InFromClient.GetArray());
                InfoPacket Reply = new InfoPacket();
                Reply.SetService("DFR");
                Reply.SetSingleData("Declined");
                ToClientStream.writeObject(Reply);
            }
            //Get My Songs
            else if ("GMS".equals(InFromClient.GetService()))
            {
                ArrayList<String> MySongs = db.GetUserSongFileName(InFromClient.GetData());
                InfoPacket Reply = new InfoPacket();
                Reply.SetService("GMS");
                Reply.SetArray(MySongs);
                ToClientStream.writeObject(Reply);
            }
            //Get user details
            else if ("GUD".equals(InFromClient.GetService()))
            {
                String Username = InFromClient.GetData();
                InfoPacket UserInformation = new InfoPacket();
                
                ArrayList<String> UsersDetails = db.GetUsersDetails(Username);
                ArrayList<String> UserSongs = db.GetUserSongFileName(Username);
               
                ArrayList<ArrayList<String>> UsersInfo = new ArrayList();
                
                UsersInfo.add(UsersDetails);
                UsersInfo.add(UserSongs);
                
                UserInformation.SetService("GUD");
                UserInformation.SetMultipleArray(UsersInfo);
                
                String PhotoFilePath = "C:/Users/samal/Documents/2nd Year/Systems Software/Shitify/Sams Work/MusicServer/res/Photos/" + Username + ".png";
                FileInputStream UserPicture = new FileInputStream(PhotoFilePath);
                byte [] buffer = new byte[UserPicture.available()];
                UserPicture.read(buffer);
                
                UserInformation.SetFirstByte(buffer);
                
                
                
                ToClientStream.writeObject(UserInformation);
            }
            else if ("GFP".equals(InFromClient.GetService()))
            {
                String Username = InFromClient.GetData();
                ArrayList<String> Friends = db.GetUsersFriends(Username);
                //Add own username to retrieve own posts
                Friends.add(InFromClient.GetData());
                ArrayList<ArrayList<String>> UserPosts = db.GetFriendsPosts(Friends);
                InfoPacket FriendsPosts = new InfoPacket();
                FriendsPosts.SetService("GFP");
                FriendsPosts.SetMultipleArray(UserPosts);
                ToClientStream.writeObject(FriendsPosts);
            }
            else
            {
                System.out.println("Not a valid command");
            }
                
                

                //Retreiving the password from the SQL Database
                
                //outToClient.writeUTF(DBPassword);
            FromClientStream.close();
            ToClientStream.close();
                
                
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

