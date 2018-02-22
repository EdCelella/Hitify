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
            String url = "jdbc:sqlite:C:\\Users\\samal\\Documents\\2nd Year\\Systems Software\\Shitify.db";
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

    @Override
    public void run()
    {
        try {
            
            //Stating which client connected to
            System.out.println("Connected to " + client.getInetAddress());
            //Data from client
            //DataInputStream FromClientStream = new DataInputStream(client.getInputStream());
            //Data to send to client
            //DataOutputStream ToClientStream = new DataOutputStream(client.getOutputStream());
            System.out.println("Making Streams");
            ObjectOutputStream ToClientStream = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
            System.out.println("Made Output Stream");
            ObjectInputStream FromClientStream = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
            System.out.println("Made Input Stream");
            
            
            
            
            while(true) {
                //Accessing the DataBase
                MusicServerExtended app = new MusicServerExtended();
                
                //When LGN
                            
                //String UserName = inFromClient.readUTF();
                //System.out.println("User name: " + UserName);
                System.out.println("In while");
                InFromClient = (InfoPacket) FromClientStream.readObject();
                System.out.println("Got Data From Client");
                String UserName = InFromClient.GetArray().get(1);
                
                
                
                String DBPassword = app.SelectLogInDetails(UserName);
                System.out.println("Got Password from DB");
                System.out.println(DBPassword);
                
                //ToClient.CreateSingleDataPacket("Reply", DBPassword);
                
                //output.writeObject(outToClient);

                //Retreiving the password from the SQL Database
                
                //outToClient.writeUTF(DBPassword);
            }
            
        } catch (IOException e)
        {
            System.err.println("Error - " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MusicServerExtended.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
}

//http://www.oracle.com/technetwork/java/socket-140484.html
//To make multithreaded server

