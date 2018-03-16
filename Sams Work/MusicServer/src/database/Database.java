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

    public ArrayList<ArrayList<String>> GetFriendsPosts(ArrayList<String> Friends)
    {
        String SQLCreateView = "CREATE TABLE TempPosts (DateTimePosted VARCHAR(30), Username VARCHAR(20), MessageOrFilename VARCHAR(256), UserMood VARCHAR(20), TypeOfPost VARCHAR(20));";
        
        
        //"CREATE TABLE TempPosts (DateTimePosted, Username, MessageOrFilename, UserMood) AS SELECT DateTimePosted,Username,"
        //        + "MessageOrFilename, UserMood FROM Posts WHERE Username = '" + Friends.get(0) + "' AND TypeOfPost = 'TextPost';";
        
        try (Connection con = this.connect();
                PreparedStatement pstmt = con.prepareStatement(SQLCreateView)) {
                pstmt.execute();                              
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        
        
        
        for (int i = 0; i < Friends.size(); i++)
        {
            String SQLInsertView = "INSERT INTO TempPosts SELECT DateTimePosted,Username,MessageOrFilename,UserMood,TypeOfPost FROM Posts WHERE Username = '"+Friends.get(i)+"';";
            try (Connection con = this.connect();
                PreparedStatement pstmt = con.prepareStatement(SQLInsertView)) {
                pstmt.execute();                              
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        String sqlSelectFromView = "SELECT DateTimePosted, Username, MessageOrFilename, UserMood, TypeOfPost FROM TempPosts ORDER BY DateTimePosted;";
        
        ArrayList<ArrayList<String>> FriendsPosts = new ArrayList();
        
        try (Connection con = this.connect();
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sqlSelectFromView)){
            
            while (rs.next())
            {
                ArrayList<String> Posts = new ArrayList();
                Posts.add(rs.getString("DateTimePosted"));
                Posts.add(rs.getString("Username"));
                Posts.add(rs.getString("MessageOrFilename"));
                Posts.add(rs.getString("UserMood"));
                Posts.add(rs.getString("TypeOfPost"));
                FriendsPosts.add(Posts);
            } 
            
            // rs.getString("DateTimePosted");
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        String SQLDropView = "DROP TABLE TempPosts";
        
                
        try (Connection con = this.connect();
                PreparedStatement pstmt = con.prepareStatement(SQLDropView)) {
                pstmt.execute();                              
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        
        return FriendsPosts;
    }
    
    public void InsertNewRegUser(ArrayList<String> UserDetails)
    {
        
        
        
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
        for (int i = 5; i < UserDetails.size(); i++)
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
    
    public ArrayList<String> GetUsersDetails(String Username)
    {
        ArrayList<String> UserDetails = new ArrayList();
        
        String SQLQuery = "SELECT FirstName, SecondName, Email, Preferences FROM UserTable WHERE Username = '" + Username + "';";
        try (Connection con = this.connect();
             Statement stmt  = con.createStatement()) {
                    ResultSet rs = stmt.executeQuery(SQLQuery);
                    System.out.println("Execture Statement INSERT Active Member");
                    
                                      
                    UserDetails.add(rs.getString("FirstName"));
                    UserDetails.add(rs.getString("SecondName"));
                    UserDetails.add(rs.getString("Email"));
                    UserDetails.add(rs.getString("Preferences"));
                    
                    
                    con.close();
                } catch (SQLException e) {
            System.out.println(e.getMessage());
                }
                
        return UserDetails;
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
        else if ("TextPost".equals(TypeOfPost))
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
    
    public void DeleteFriend(ArrayList<String> Users)
    {
        String Username = Users.get(0);
        String Username2 = Users.get(1);
        String SQLQuery = "DELETE FROM Friends WHERE FirstUserName = '" + Username + "' AND SecondUserName = '" + Username2 + "';";
        try (Connection con = this.connect();
             PreparedStatement pstmt  = con.prepareStatement(SQLQuery)) {
                    pstmt.execute();
            
                    con.close();
                } catch (SQLException e) {
            System.out.println(e.getMessage());
                }
        
        String SQLQuery2 = "DELETE FROM Friends WHERE FirstUserName = '" + Username2 + "' AND SecondUserName = '" + Username + "';";
        try (Connection con = this.connect();
             PreparedStatement pstmt  = con.prepareStatement(SQLQuery2)) {
                    pstmt.execute();
            
                    con.close();
                } catch (SQLException e) {
            System.out.println(e.getMessage());
                }
    }
    
    public ArrayList<String> GetUserSongFileName(String Username)
    {
        String SQLQuery = "SELECT FileName FROM Songs WHERE Username = '" + Username + "';";
        ArrayList<String> UserSongs = new ArrayList();
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
        
        ArrayList<String> FriendsList = new ArrayList();
        
        String SQLQuery = "SELECT SecondUserName FROM Friends WHERE FirstUserName = '" + Username + "'"
                + " AND Status = 'Accepted';";
        
        try (Connection con = this.connect();
             Statement stmt  = con.createStatement()) 
                {
                    ResultSet rs = stmt.executeQuery(SQLQuery);
                    
                    
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
    
    public ArrayList<String> GetActiveFriends(ArrayList<String> Friends)
    {
        
        ArrayList<String> ActiveFriends = new ArrayList();
        String CurrentFriend = "";
        
        for (int i = 0; i < Friends.size(); i++)
        {
            CurrentFriend = Friends.get(i);
            String SQLQuery = "SELECT Username FROM ActiveMembers WHERE Username = '" + CurrentFriend + "';";

            try (Connection con = this.connect();
                 Statement stmt  = con.createStatement()) 
                    {
                        ResultSet rs = stmt.executeQuery(SQLQuery);


                        while (rs.next())
                        {
                            ActiveFriends.add(rs.getString("Username"));
                        }

                        con.close();
                    } catch (SQLException e) {
                System.out.println(e.getMessage());
                    }
        }
                
        return ActiveFriends;
    }
    
    public ArrayList<String> GetUsersFriendRequests(String Username)
    {
        
        ArrayList<String> FriendsRequestList = new ArrayList();
        
        String SQLQuery = "SELECT FirstUserName FROM Friends WHERE SecondUserName = '" + Username + "'"
                + " AND Status = 'Pending';";
        
        try (Connection con = this.connect();
             Statement stmt  = con.createStatement()) 
                {
                    ResultSet rs = stmt.executeQuery(SQLQuery);
                    
                    
                    while (rs.next())
                    {
                        FriendsRequestList.add(rs.getString("FirstUserName"));
                    }
                    
                    con.close();
                } catch (SQLException e) {
            System.out.println(e.getMessage());
                }
        
        
        return FriendsRequestList;
    }
    
    public void NewFriendRequest (ArrayList<String> Users)
    {
        String FirstUser = Users.get(0);
        String SecondUser = Users.get(1);
        
        String SQLQuery = "INSERT INTO Friends VALUES ('" + FirstUser + "','" + SecondUser + "','Pending');";
        
        try (Connection con = this.connect();
             PreparedStatement pstmt  = con.prepareStatement(SQLQuery)) {
                    pstmt.execute();
                    System.out.println("INSERTED NEW FRIENDSHIP PENDING");
            
                    con.close();
                } catch (SQLException e) {
            System.out.println("SQLite - " + e.getMessage());
                }
        
    }
    
    public void AcceptFriendRequest (ArrayList<String> Users)
    {
        String FirstUser = Users.get(0);
        String SecondUser = Users.get(1);
        
        String SQLQuery = "UPDATE Friends SET Status = 'Accepted' WHERE FirstUserName = '" + SecondUser + "' AND SecondUserName = '" + FirstUser + "';";
        
        
        try (Connection con = this.connect();
             PreparedStatement pstmt  = con.prepareStatement(SQLQuery)) {
                    pstmt.execute();
                    System.out.println("INSERTED NEW FRIENDSHIP PENDING");
            
                    con.close();
                } catch (SQLException e) {
            System.out.println(e.getMessage());
                }
        
//        String SQLQuery2 = "UPDATE Friends SET Status = 'Accepted' WHERE FirstUserName = '" + SecondUser + "' AND SecondUserName = '" + FirstUser + "';";
//        
//        try (Connection con = this.connect();
//             PreparedStatement pstmt  = con.prepareStatement(SQLQuery2)) {
//                    pstmt.execute();
//                    System.out.println("INSERTED NEW FRIENDSHIP PENDING");
//            
//                    con.close();
//                } catch (SQLException e) {
//            System.out.println(e.getMessage());
//                }
    }
    
    
    public void DeclineFriendRequest (ArrayList<String> Users)
    {
        String FirstUser = Users.get(0);
        String SecondUser = Users.get(1);
        
        //String SQLQuery = "UPDATE Friends SET Status = 'Declined' WHERE FirstUserName = '" + FirstUser + "' AND SecondUserName = '" + SecondUser + "';";
        String SQLQuery3 = "DELETE FROM Friends WHERE FirstUserName = '" + SecondUser + "' AND SecondUserName = '" + FirstUser + "';";
        
        
        try (Connection con = this.connect();
             PreparedStatement pstmt  = con.prepareStatement(SQLQuery3)) {
                    pstmt.execute();
                    System.out.println("INSERTED NEW FRIENDSHIP Declined");
            
                    con.close();
                } catch (SQLException e) {
            System.out.println(e.getMessage());
                }
        
//        String SQLQuery2 = "UPDATE Friends SET Status = 'Declined' WHERE FirstUserName = '" + SecondUser + "' AND SecondUserName = '" + FirstUser + "';";
//        
//        try (Connection con = this.connect();
//             PreparedStatement pstmt  = con.prepareStatement(SQLQuery2)) {
//                    pstmt.execute();
//                    System.out.println("INSERTED NEW FRIENDSHIP Declined");
//            
//                    con.close();
//                } catch (SQLException e) {
//            System.out.println(e.getMessage());
//                }
    }
    
    public ArrayList<String> GetUsernamesOnPreferences(String MusicPref)
    {
        ArrayList<String> AllUsers = new ArrayList();
        
        String SQLQuery = "SELECT UserName, Preferences FROM UserTable";
        
        
        try (Connection con = this.connect();
             Statement stmt  = con.createStatement()) 
                {
                    ResultSet rs = stmt.executeQuery(SQLQuery);
                    
                    
                    while (rs.next())
                    {
                        String Username = rs.getString("UserName");
                        String Preferences = rs.getString("Preferences");
                        
                        if ("All".equals(MusicPref))
                            {
                                AllUsers.add(Username);
                            }
                        else {
                            if (Preferences.contains(MusicPref))
                            {
                                AllUsers.add(Username);
                            }
                        }
                    }
                    
                    con.close();
                } catch (SQLException e) {
            System.out.println(e.getMessage());
                }
        
        return AllUsers;
    }
    
    public boolean DoesUsernameExist(String Username)
    {
        boolean Exists = false;
        ArrayList<String> Users = GetUsernamesOnPreferences("All");

        for (int i = 0; i < Users.size(); i++)
        {
            if (Username.equals(Users.get(i)))
            {
                Exists = true;
            }
        }
        return Exists;
    }
    
    public boolean AlreadyFriends (ArrayList<String> Users)
    {
        boolean AlreadyFriends = false;
        //User[0] to retrieve friends for
        //if User[1] is in the ArrayList then they are already friends
        ArrayList<String> AllFriends = GetUsersFriends(Users.get(0));
        
        
        for (int i = 0; i < AllFriends.size(); i++)
        {
           if (Users.get(1).equals(AllFriends.get(i)))
           {
               AlreadyFriends = true;
           }
        }
        
        ArrayList<String> FriendRequests = GetUsersFriendRequests(Users.get(0));
        
        for (int i = 0; i < FriendRequests.size(); i++)
        {
           if (Users.get(1).equals(FriendRequests.get(i)))
           {
               AlreadyFriends = true;
           }
        }
        
        return AlreadyFriends;
    }
    
    
    
}
