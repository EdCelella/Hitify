/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author samal
 */
public class Database {
 
    private Connection connect() {
        Connection con = null;
        try {
            String url = "jdbc:sqlite:DataBase/Shitify.db";
            con = DriverManager.getConnection(url);
            System.out.println("Connection to Database is established");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    
        return con;
    }  
    
        
    public String SelectLogInDetails(String UserName){
        String sql = "SELECT Password FROM UserTable WHERE UserName = '" + UserName + "';";
        String Password = "";
        try (Connection con = this.connect();
             Statement stmt  = con.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            
            Password = rs.getString("Password");
            con.close();
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
            
                    con.close();
                } catch (SQLException e) {
            System.out.println(e.getMessage());
                }
        
    }
    
    public void InsertPost (ArrayList<String> PostDetails)
    {
        String UserName = PostDetails.get(0);
        //Date is automatically inserted by SQL database
        String TypeOfPost = PostDetails.get(1);
        String Message = "", UserMood = "", SQLQuery = "";
        if ("SongUpload".equals(TypeOfPost))
        {
            Message = PostDetails.get(2) + "," + PostDetails.get(3);
            SQLQuery = "INSERT INTO Posts (UserName,TypeOfPost,MessageOrFilename) VALUES ('" + UserName + "','" + TypeOfPost
            + "','" + Message + "');";
        }
        else if ("TextUpload".equals(TypeOfPost))
        {
            Message = PostDetails.get(2);
            UserMood = PostDetails.get(3);
            SQLQuery = "INSERT INTO Posts (UserName,TypeOfPost,MessageOrFilename,UserMood) VALUES ('" + UserName + "','" + TypeOfPost
                + "','" + Message + "','" + UserMood + "');";
        }
        
        try (Connection con = this.connect();
             PreparedStatement pstmt  = con.prepareStatement(SQLQuery)) {
                    pstmt.execute();
                    System.out.println("Execture Statement INSERT Song");
            
                    con.close();
                } catch (SQLException e) {
            System.out.println(e.getMessage());
                }
        
    }
    
    public void InsertSong (ArrayList<String> SongDetails)
    {
        String Username = SongDetails.get(0);
        String ArtistName = SongDetails.get(2);
        String SongName = SongDetails.get(3);
        String Genre = SongDetails.get(4);
        String FileName = ArtistName + "," + SongName;
        
        String SQLQuery = "INSERT INTO Songs VALUES ('" + FileName + "','" + Username
                + "','" + ArtistName + "','" + SongName + "','" + Genre + "');";
        try (Connection con = this.connect();
             PreparedStatement pstmt  = con.prepareStatement(SQLQuery)) {
                    pstmt.execute();
                    System.out.println("Execture Statement INSERT");
            
                    con.close();
                } catch (SQLException e) {
            System.out.println(e.getMessage());
                }
        
    }
    
    public void InsertActiveMember (String Username, String IP)
    {
        String SQLQuery = "INSERT INTO ActiveMembers VALUES ('" + Username + "','" + IP + "');";
        try (Connection con = this.connect();
             PreparedStatement pstmt  = con.prepareStatement(SQLQuery)) {
                    pstmt.execute();
                    System.out.println("Execture Statement INSERT Active Member");
            
                    con.close();
                } catch (SQLException e) {
            System.out.println(e.getMessage());
                }
        
    }
    
    public void RemoveActiveMember (String Username)
    {
        String SQLQuery = "DELETE FROM ActiveMembers WHERE Username = '" + Username + "';";
        try (Connection con = this.connect();
             PreparedStatement pstmt  = con.prepareStatement(SQLQuery)) {
                    pstmt.execute();
                    System.out.println("Execture Statement INSERT Active Member");
            
                    con.close();
                } catch (SQLException e) {
            System.out.println(e.getMessage());
                }
        
    }
    
    public ArrayList<String> GetUserSongFileName(String Username)
    {
        String SQLQuery = "SELECT FROM Songs WHERE Username = '" + Username + "';";
        ArrayList<String> UserSongs = null;
        try (Connection con = this.connect();
             Statement stmt  = con.createStatement()) {
                    ResultSet rs = stmt.executeQuery(SQLQuery);
                    System.out.println("Execture Statement INSERT Active Member");
                    
                    while (rs.next())
                    {
                        UserSongs.add(rs.getString("FileName"));
                    }
                    
                    con.close();
                } catch (SQLException e) {
            System.out.println(e.getMessage());
                }
        return UserSongs;
    }
    
    public ArrayList<String> GetUsersFriends(String Username)
    {
        System.out.println("GetUserFriends: " + Username);
        ArrayList<String> FriendsList = new ArrayList();
        
        String SQLQuery = "SELECT SecondUserName FROM Friends WHERE FirstUserName = '" + Username + "'"
                + " AND Status = 'Accepted';";
        
        try (Connection con = this.connect();
             Statement stmt  = con.createStatement()) 
                {
                    ResultSet rs = stmt.executeQuery(SQLQuery);
                    
                    System.out.println("Statement has been executed GetUserFriends");
                    while (rs.next())
                    {
                        FriendsList.add(rs.getString("SecondUserName"));
                    }
                    
                    con.close();
                } catch (SQLException e) {
            System.out.println(e.getMessage());
                }
        
        String SQLQuery2 = "SELECT FirstUserName FROM Friends WHERE SecondUserName = '" + Username + "'"
                + " AND Status = 'Accepted';";
        
        try (Connection con = this.connect();
             Statement stmt  = con.createStatement()) 
                {
                    ResultSet rs = stmt.executeQuery(SQLQuery2);
                    
                    
                    while (rs.next())
                    {
                        FriendsList.add(rs.getString("FirstUserName"));
                    }
                    
                    con.close();
                } catch (SQLException e) {
            System.out.println(e.getMessage());
                }
        
        
        return FriendsList;
    }
    
}
