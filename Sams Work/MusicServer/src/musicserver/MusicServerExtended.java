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
import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;

/**
 *
 * @author samal
 */
public class MusicServerExtended extends Thread {
    
    private Socket client;
    private MServerGUI GUI;
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
    
    public void SetGUI(MServerGUI GUI)
    {
        this.GUI = GUI;
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
            String ip = client.getInetAddress().toString().replace("/","");
            //System.out.println(InFromClient.GetService());
            if (null == InFromClient.GetService())
            {
                GUI.AddToLog(ip + " has sent an invalid command");
            }            else //Login attempted
            switch (InFromClient.GetService()) {
            //User Log in 
                case "LGN":
                    String UserName = InFromClient.GetArray().get(0);
                    String UserPassWord = InFromClient.GetArray().get(1);
                    //Get password from DB searching with username
                    String DBPassword = db.SelectLogInDetails(UserName);
                    ToClient.SetService("LGN");
                    if (UserPassWord.equals(DBPassword))
                    {
                        ToClient.SetSingleData("CORRECT");
                        
                        //Get IP Address
                        
                        db.InsertActiveMember(UserName, ip);
                        GUI.AddToLog(UserName + " Logged in using address: " + ip);
                        //Insert username and IP address into Active Members
                        
                    }
                    else
                    {
                        ToClient.SetSingleData("INCORRECT");
                        GUI.AddToLog("Incorrect Log in attempt from: " + ip);
                    }   //Send InfoPacket To user
                    ToClientStream.writeObject(ToClient);
                    break;
                    //Log Out
                case "LGO":
                    {
                        db.RemoveActiveMember(InFromClient.GetData());
                        InfoPacket Reply = new InfoPacket();
                        Reply.SetService("LGO");
                        Reply.SetSingleData("Logout");
                        ToClientStream.writeObject(Reply);
                        GUI.AddToLog(InFromClient.GetData() + " has logged out");
                        break;
                    }
                    //Create new user
                case "CNU":
                    {
                        //Retreive all information about user and add it to the DB
                        ArrayList UsersInfo = InFromClient.GetArray();
                        boolean AlreadyExists = db.DoesUsernameExist(UsersInfo.get(0).toString());
                        InfoPacket Reply = new InfoPacket();
                        Reply.SetService("CNU");
                        if (AlreadyExists == false)
                        {
                            db.InsertNewRegUser(UsersInfo);
                            byte [] Image = (byte []) InFromClient.GetByteData();
                            
                            
                            File PhotoDirectory = new File("res/Photos/" + InFromClient.GetArray().get(0) + ".png");
                            FileOutputStream FileOut = new FileOutputStream(PhotoDirectory);
                            FileOut.write(Image);
                            
                            
                            Reply.SetSingleData("Registered");
                            
                        }
                        else
                        {
                            Reply.SetSingleData("UsernameExists");
                        }       ToClientStream.writeObject(Reply);
                        GUI.AddToLog("Added new user: " + UsersInfo.get(0));
                        break;
                    }
                    //Upload new song
                case "UNS":
                    {
                        
                        ArrayList SongInformation = InFromClient.GetArray();
                        String FileName = SongInformation.get(2) + "," + SongInformation.get(3);
                        File MusicDirectory = new File("res/Music/" + FileName + ".mp3");
                        File PhotoDirectory = new File("res/Photos/" + FileName + ".png");
                        byte [] Song = (byte []) InFromClient.GetByteData();
                        FileOutputStream SongOut = new FileOutputStream(MusicDirectory);
                        SongOut.write(Song);
                        byte [] CoverPhoto = (byte []) InFromClient.GetSecondData();
                        FileOutputStream PhotoOut = new FileOutputStream(PhotoDirectory);
                        PhotoOut.write(CoverPhoto);
                        db.InsertSong(SongInformation);
                        db.InsertPost(SongInformation);
                        GUI.AddToLog(SongInformation.get(0) + " Uploaded a new song: " + FileName);
                        
                        
                        //System.out.println("Successfull");
                        break;
                    }
                    //Upload new post
                case "UNP":
                    {
                        db.InsertPost(InFromClient.GetArray());
                        InfoPacket Reply = new InfoPacket();
                        Reply.SetService("GMF");
                        Reply.SetSingleData("Added Post");
                        ToClientStream.writeObject(Reply);
                        GUI.AddToLog(InFromClient.GetArray().get(0) + " made a new post");
                        break;
                    }
                    //Get My Friends
                case "GMF":
                    {
                        ArrayList<String> UsersFriends = db.GetUsersFriends(InFromClient.GetData());
                        InfoPacket Reply = new InfoPacket();
                        Reply.SetService("GMF");
                        Reply.SetArray(UsersFriends);
                        ToClientStream.writeObject(Reply);
                        GUI.AddToLog(InFromClient.GetData() + " requested to view all friends");
                        break;
                    }
                    //Get Users based on Prefernces
                case "GUP":
                    {
                        ArrayList<String> Users = db.GetUsernamesOnPreferences(InFromClient.GetData());
                        InfoPacket Reply = new InfoPacket();
                        Reply.SetService("GUP");
                        Reply.SetArray(Users);
                        ToClientStream.writeObject(Reply);
                        GUI.AddToLog(ip + " requested to get users based on preferences");
                        break;
                    }
                    //Get Active Friends
                case "GAF":
                    {
                        ArrayList<String> Friends = db.GetUsersFriends(InFromClient.GetData());
                        ArrayList<String> ActiveFriends = db.GetActiveFriends(Friends);
                        InfoPacket Reply = new InfoPacket();
                        Reply.SetService("GAF");
                        Reply.SetArray(ActiveFriends);
                        ToClientStream.writeObject(Reply);
                        GUI.AddToLog("Getting " + InFromClient.GetData() + " active friends");
                        break;
                    }
                    //New Friend Request
                case "NFR":
                    {
                        ArrayList<String> Users = InFromClient.GetArray();
                        boolean UsernameExists = db.DoesUsernameExist(Users.get(1));
                        InfoPacket Reply = new InfoPacket();
                        Reply.SetService("NFR");
                        if (UsernameExists == true)
                        {
                            boolean AlreadyFriends = db.AlreadyFriends(Users);
                            if (AlreadyFriends == false)
                            {
                                Reply.SetSingleData("Exists");
                                db.NewFriendRequest(Users);
                                GUI.AddToLog(Users.get(0) + " sent " + Users.get(1) + " a friends request");
                            }
                            else
                            {
                                Reply.SetSingleData("AlreadyFriends");
                                GUI.AddToLog(Users.get(0) + " tried to send " + Users.get(1) + " a friend request, but they are already friends");
                            }
                        }
                        else
                        {
                            Reply.SetSingleData("Doesnt");
                            GUI.AddToLog(Users.get(0) + " tried to send a friend request to a none existing user");
                        }       ToClientStream.writeObject(Reply);
                        break;
                    }
                    //Get Friend Requests
                case "GFR":
                    {
                        ArrayList<String> UsersFriendRequests = db.GetUsersFriendRequests(InFromClient.GetData());
                        InfoPacket Reply = new InfoPacket();
                        Reply.SetService("GFR");
                        Reply.SetArray(UsersFriendRequests);
                        ToClientStream.writeObject(Reply);
                        GUI.AddToLog(InFromClient.GetData() + " reqested to see their friend requests");
                        break;
                    }
                    //Accept Friend Request
                case "AFR":
                    {
                        db.AcceptFriendRequest(InFromClient.GetArray());
                        InfoPacket Reply = new InfoPacket();
                        Reply.SetService("GFR");
                        Reply.SetSingleData("Accepted");
                        ToClientStream.writeObject(Reply);
                        GUI.AddToLog(InFromClient.GetArray().get(0) + " accepted " + InFromClient.GetArray().get(1) + " friend request");
                        break;
                    }
                    //Decline Friend Request
                case "DFR":
                    {
                        db.DeclineFriendRequest(InFromClient.GetArray());
                        InfoPacket Reply = new InfoPacket();
                        Reply.SetService("DFR");
                        Reply.SetSingleData("Declined");
                        ToClientStream.writeObject(Reply);
                        GUI.AddToLog(InFromClient.GetArray().get(0) + " declined " + InFromClient.GetArray().get(1) + " friend request");
                        break;
                    }
                    //Get My Songs
                case "GMS":
                    {
                        ArrayList<String> MySongs = db.GetUserSongFileName(InFromClient.GetData());
                        InfoPacket Reply = new InfoPacket();
                        Reply.SetService("GMS");
                        Reply.SetArray(MySongs);
                        ToClientStream.writeObject(Reply);
                        GUI.AddToLog(ip + " requests to see " + InFromClient.GetData() + " songs");
                        break;
                    }
                    //Delete FRiend
                case "DFS":
                    {
                        db.DeleteFriend(InFromClient.GetArray());
                        InfoPacket Reply = new InfoPacket();
                        Reply.SetService("DFS");
                        Reply.SetSingleData("Removed");
                        ToClientStream.writeObject(Reply);
                        GUI.AddToLog(InFromClient.GetArray().get(0) + "removed " + InFromClient.GetArray().get(1) + " as a friend");
                        break;
                    }
                    //Get user details
                case "GUD":
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
                        File PhotoDirectory = new File("res/Photos/" + Username + ".png");
                        FileInputStream UserPicture = new FileInputStream(PhotoDirectory);
                        byte [] buffer = new byte[UserPicture.available()];
                        UserPicture.read(buffer);
                        UserInformation.SetFirstByte(buffer);
                        ToClientStream.writeObject(UserInformation);
                        GUI.AddToLog("Sending " + Username + " details and songs to " + ip);
                        break;
                    }
                    //Get Friends Posts
                case "GFP":
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
                        GUI.AddToLog(Username + " requests to see their friends posts");
                        break;
                    }
                    //DoWnload Song
                case "DWS":
                    {
                        InfoPacket SongData = new InfoPacket();
                        File MusicDirectory = new File("res/Music/" + InFromClient.GetData() + ".mp3");
                        File PhotoDirectory = new File("res/Photos/" + InFromClient.GetData() + ".png");
                        FileInputStream SongFile = new FileInputStream(MusicDirectory);
                        byte [] buffer = new byte[SongFile.available()];
                        SongFile.read(buffer);
                        FileInputStream PhotoFile = new FileInputStream(PhotoDirectory);
                        byte [] buffer2 = new byte[PhotoFile.available()];
                        PhotoFile.read(buffer2);
                        SongData.SetService("DWS");
                        SongData.SetFirstByte(buffer);
                        SongData.SetSecondByte(buffer2);
                        ToClientStream.writeObject(SongData);
                        GUI.AddToLog(ip + " is downloading the song and cover photo for " + InFromClient.GetData());
                        break;
                    }
                default:
                    GUI.AddToLog(ip + " has sent an invalid command");
                    break;
            }
                
                

                //Retreiving the password from the SQL Database
                
                //outToClient.writeUTF(DBPassword);
            FromClientStream.close();
            ToClientStream.close();
                
                
        } catch (IOException | ClassNotFoundException e)
        {
            GUI.AddToLog(e.getMessage());
        }
    }
   
}

//http://www.oracle.com/technetwork/java/socket-140484.html
//To make multithreaded server

