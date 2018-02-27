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
    
    private Connection connect() {
        Connection con = null;
        try {
            String url = "jdbc:sqlite:C:src/musicserver/Shitify.db";
            con = DriverManager.getConnection(url);
            System.out.println("Connection to Database is established");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    
        return con;
    }  
    
    public void selectAll(){
        String sql = "SELECT UserName, Password, Preferences FROM UserTable";
        
        try (Connection con = this.connect();
             Statement stmt  = con.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("UserName") +  "\t" + 
                                   rs.getString("Password") + "\t" +
                                   rs.getString("Preferences"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public String SelectLogInDetails(String UserName){
        String sql = "SELECT Password FROM UserTable WHERE UserName = '" + UserName + "';";
        String Password = "";
        try (Connection con = this.connect();
             Statement stmt  = con.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            
            Password = rs.getString("Password");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Password;
    }

    public void InsertNewRegUser(ArrayList<String> UserDetails)
    {
        int ArraySize = UserDetails.size();
        int NumberOfPrefs = ArraySize - 5;
        
        //Get all info from Array
        String UserName = UserDetails.get(0);
        System.out.println("Username: " + UserName);
        String UserPassword = UserDetails.get(1);
        System.out.println("Password: " + UserPassword);
        String FirstName = UserDetails.get(2);
        System.out.println("FirstName: " + FirstName );
        String SecondName = UserDetails.get(3);
        System.out.println("SecondName: " + SecondName);
        String UserEmail = UserDetails.get(4);
        System.out.println("UserEmail: " + UserEmail);
        String UserPreferences = "";
        //Make a string list that is seperated with commas full of preferences
        for (int i = 5; i < ArraySize; i++)
        {
            UserPreferences = UserPreferences + UserDetails.get(i) + ",";
        }
        UserPreferences = UserPreferences.substring(0, UserPreferences.length()-1);
        System.out.println("User Preferences: " + UserPreferences);
        String SQLQuery = "INSERT INTO UserTable VALUES ('" + UserName + "','" + UserPassword
                + "','" + FirstName + "','" + SecondName + "','" + UserEmail + "','" + UserPreferences + "');";
        try (Connection con = this.connect();
             PreparedStatement pstmt  = con.prepareStatement(SQLQuery)) {
                    pstmt.execute();
                    System.out.println("Execture Statement INSERT");
            
            
                } catch (SQLException e) {
            System.out.println(e.getMessage());
                }
        
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
                //Accessing the DataBase
                MusicServerExtended app = new MusicServerExtended();
                            
                InFromClient = (InfoPacket) FromClientStream.readObject();
                System.out.println(InFromClient.GetService());
                
                //Login attempted
                if ("LGN".equals(InFromClient.GetService()))
                {
                    String UserName = InFromClient.GetArray().get(0);
                    String UserPassWord = InFromClient.GetArray().get(1);
                    //Get password from DB searching with username                                
                    String DBPassword = app.SelectLogInDetails(UserName);
                    System.out.println(UserName + " " + UserPassWord + " " + DBPassword);
                    
                    if (UserPassWord.equals(DBPassword))
                    {
                        System.out.println("Correct");
                        ToClient.CreateSingleDataPacket("LGN", "CORRECT");
                    }
                    else
                    {
                        System.out.println("Incorrect");
                        ToClient.CreateSingleDataPacket("LGN", "INCORRECT");
                    }
                    //Send InfoPacket To user
                    ToClientStream.writeObject(ToClient);
                }
                //Create new user
                else if ("CNU".equals(InFromClient.GetService()))
                {
                    //Retreive all information about user and add it to the DB
                    ArrayList UsersInfo = new ArrayList();
                    UsersInfo = InFromClient.GetArray();
                    
                    byte [] Image = (byte []) InFromClient.GetByteData();
                    
                    InsertNewRegUser(UsersInfo);
                    
                    String WhereToSave = "C:/Users/samal/Documents/2nd Year/Systems Software/Shitify/Sams Work/TestingCoursework/res/Photos/" + InFromClient.GetArray().get(0) + ".png";
                    FileOutputStream FileOut = new FileOutputStream(WhereToSave);
                    FileOut.write(Image);
                    
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

