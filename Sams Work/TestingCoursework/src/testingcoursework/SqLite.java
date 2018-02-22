/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingcoursework;

import java.sql.*;

/**
 *
 * @author samal
 */
public class SqLite {
    
    private static Connection con;
    private static boolean hasData = false;
    
    
    public ResultSet displayUsers() throws SQLException, ClassNotFoundException {
        if(con == null) {
            getConnection();
        }
        
        Statement state = con.createStatement();
        ResultSet res = state.executeQuery("SELECT fname, lname FROM USER");
        return res;
        
    }

    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:SQLiteTest1.db");
        initialise();
    }

    private void initialise() throws SQLException {
         if (!hasData) {
             hasData = true;
             
             
             Statement state = con.createStatement();
             ResultSet res = state.executeQuery("SELECT name FROM sqlite master WHERE type = 'table' AND name = 'user'");
             if (!res.next()) {
                 System.out.println("Building the User table with prepopulated values");
                 
                 //Build a table
                 Statement state2 = con.createStatement();
                 state2.execute("CREATE TABLE user(id integer,"
                         + "fName varchar(20), lName varchar(60),"
                         + "primary key(id));");
             
                 
                 //Insert some data
                 PreparedStatement prep = con.prepareStatement("INSERT INTO user values(?,?,?);");
                 prep.setString(2, "John");
                 prep.setString(3, "Sam");
                 prep.execute();
             }
                 
         }
    }
    
    public void addUser (String firstName, String lastName) throws ClassNotFoundException, SQLException {
        if (con == null){
            getConnection();
            
        }
        
        PreparedStatement prep = con.prepareStatement("INSERT INTO user values(?,?,?);");
        prep.setString(2,firstName);
        prep.setString(3, lastName);
        prep.execute();
        
    }
    
}
