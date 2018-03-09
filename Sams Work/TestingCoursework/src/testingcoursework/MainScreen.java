/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingcoursework;

import infopacket.InfoPacket;
import java.awt.Image;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author samal
 */
public class MainScreen extends javax.swing.JFrame {

    /**
     * Creates new form MainScreen
     */
    private String Username;
    
    public MainScreen() {
        initComponents();
        
        
    }
    
    public MainScreen(String UserName) throws IOException, ClassNotFoundException {
        initComponents();
        this.Username = UserName;
        RefreshAllFriendsList();
        RefreshFriendsRequestList();
        RefreshMySongs();
        RefreshPosts();
        RefreshActiveFriendsList();
        //Thread timer to refresh Active Friend lists and Friend Requests every 2 seconds
        TimerThread C = new TimerThread();  
        C.SetForm(this);
        Thread t1 = new Thread(C);
        t1.start(); 
        //Thread timer to refresh Posts and All Friends every 60 seconds
        TimerThread2 D = new TimerThread2();  
        D.SetForm(this);
        Thread t2 = new Thread(D);
        t2.start();
               
        //ListAllFriends.getSelectedValue() to retrieve currently selected item
    }
    
    public void RefreshAllFriendsList() throws IOException, ClassNotFoundException
    {
        Socket MainServer = new Socket("localhost", 9090);
                    
        ObjectOutputStream OutToServer = new ObjectOutputStream(MainServer.getOutputStream());
        ObjectInputStream FromServerStream = new ObjectInputStream(MainServer.getInputStream());

        InfoPacket UserFriends = new InfoPacket();
        //GET MY FRIENDS
        UserFriends.SetService("GMF");
        UserFriends.SetSingleData(Username);
        
        OutToServer.writeObject(UserFriends);

        InfoPacket ServerReply = (InfoPacket) FromServerStream.readObject();
        
        ArrayList<String> AllUsersFriends = ServerReply.GetArray();
        
        OutToServer.close();
        FromServerStream.close();
        
        DefaultListModel AllFriends = new DefaultListModel();
        
        
        for (int i = 0; i < AllUsersFriends.size(); i++)
        {
            AllFriends.addElement(AllUsersFriends.get(i));
            
        }
        ListAllFriends.setModel(AllFriends);
    }
    
    public void RefreshActiveFriendsList() throws IOException, ClassNotFoundException
    {
        Socket MainServer = new Socket("localhost", 9090);
                    
        ObjectOutputStream OutToServer = new ObjectOutputStream(MainServer.getOutputStream());
        ObjectInputStream FromServerStream = new ObjectInputStream(MainServer.getInputStream());

        InfoPacket UserFriends = new InfoPacket();
        //GET MY FRIENDS
        UserFriends.SetService("GAF");
        UserFriends.SetSingleData(Username);
        
        OutToServer.writeObject(UserFriends);

        InfoPacket ServerReply = (InfoPacket) FromServerStream.readObject();
        
        ArrayList<String> ActiveFriends = ServerReply.GetArray();
        
        OutToServer.close();
        FromServerStream.close();
        
        DefaultListModel MyActiveFriends = new DefaultListModel();
        
        for (int i = 0; i < ActiveFriends.size(); i++)
        {
            MyActiveFriends.addElement(ActiveFriends.get(i));
            
        }
        ListActiveFriends.setModel(MyActiveFriends);
    }
    
    public void RefreshFriendsRequestList() throws IOException, ClassNotFoundException
    {
        Socket MainServer = new Socket("localhost", 9090);
                    
        ObjectOutputStream OutToServer = new ObjectOutputStream(MainServer.getOutputStream());
        ObjectInputStream FromServerStream = new ObjectInputStream(MainServer.getInputStream());

        InfoPacket UserFriends = new InfoPacket();
        //GET MY FRIENDS
        UserFriends.SetService("GFR");
        UserFriends.SetSingleData(Username);
        
        OutToServer.writeObject(UserFriends);

        InfoPacket ServerReply = (InfoPacket) FromServerStream.readObject();
        
        ArrayList<String> AllUsersFriends = ServerReply.GetArray();
        
        OutToServer.close();
        FromServerStream.close();
        
        DefaultListModel AllFriendRequests = new DefaultListModel();
        
        
        for (int i = 0; i < AllUsersFriends.size(); i++)
        {
            AllFriendRequests.addElement(AllUsersFriends.get(i));
            
        }
        ListFriendRequests.setModel(AllFriendRequests);
    }

    public void RefreshMySongs() throws IOException, ClassNotFoundException
    {
        Socket MainServer = new Socket("localhost", 9090);
                    
        ObjectOutputStream OutToServer = new ObjectOutputStream(MainServer.getOutputStream());
        ObjectInputStream FromServerStream = new ObjectInputStream(MainServer.getInputStream());

        InfoPacket UserFriends = new InfoPacket();
        //GET MY FRIENDS
        UserFriends.SetService("GMS");
        UserFriends.SetSingleData(Username);
        
        OutToServer.writeObject(UserFriends);

        InfoPacket ServerReply = (InfoPacket) FromServerStream.readObject();
        
        ArrayList<String> MySongs = ServerReply.GetArray();
        
        OutToServer.close();
        FromServerStream.close();
        
        DefaultListModel MyUserSongs = new DefaultListModel();
        
        
        for (int i = 0; i < MySongs.size(); i++)
        {
            MyUserSongs.addElement(MySongs.get(i));
            
        }
        ListMySongs.setModel(MyUserSongs);
    }
    
    
    public void RefreshPosts() throws IOException, ClassNotFoundException
    {
        
        Socket MainServer = new Socket("localhost", 9090);
                    
        ObjectOutputStream OutToServer = new ObjectOutputStream(MainServer.getOutputStream());
        ObjectInputStream FromServerStream = new ObjectInputStream(MainServer.getInputStream());

        InfoPacket GetFriendsPosts = new InfoPacket();
        //GET MY FRIENDS
        GetFriendsPosts.SetService("GFP");
        GetFriendsPosts.SetSingleData(Username);
        
        OutToServer.writeObject(GetFriendsPosts);

        InfoPacket ServerReply = (InfoPacket) FromServerStream.readObject();
        
        ArrayList<ArrayList<String>> FriendsPosts = ServerReply.GetMultipleArray();
        txtPostArea.setText("");
        for (int i = 0 ; i < FriendsPosts.size(); i++)
        {
            String PostFormat = "";
            if ("Select Mood:".equals(FriendsPosts.get(i).get(3))) {
                PostFormat = FriendsPosts.get(i).get(0) + " - " + FriendsPosts.get(i).get(1) + ": " + FriendsPosts.get(i).get(2) +"\n";
            }
            else {
                PostFormat = FriendsPosts.get(i).get(0) + " - " + FriendsPosts.get(i).get(1) + ": " + FriendsPosts.get(i).get(2) + " - Feeling " + FriendsPosts.get(i).get(3)+"\n";
            }
            txtPostArea.append(PostFormat);

        }
        OutToServer.close();
        FromServerStream.close();
    }
    /**
     * This method is called from within the constructor to  the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdLogOut = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListAllFriends = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ListActiveFriends = new javax.swing.JList<>();
        lblSendRequest = new javax.swing.JLabel();
        txtNewFriendRequest = new javax.swing.JTextField();
        cmdSendFreindRequest = new javax.swing.JButton();
        cmdUploadSong = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ListFriendRequests = new javax.swing.JList<>();
        cmdAcceptFriendRequest = new javax.swing.JButton();
        cmdDeclineFriendRequest = new javax.swing.JButton();
        lblProfilePicture = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblFirstName = new javax.swing.JLabel();
        lblSecondName = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblPreferences = new javax.swing.JLabel();
        txtNewPost = new javax.swing.JTextField();
        cmdPost = new javax.swing.JButton();
        lblPosts = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        listSelectedUsersSongs = new javax.swing.JList<>();
        cmdChat = new javax.swing.JButton();
        cmdDeleteFriend = new javax.swing.JButton();
        cmdPlayUsersSong = new javax.swing.JButton();
        lblOwnSongs = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        ListMySongs = new javax.swing.JList<>();
        cbUserMood = new javax.swing.JComboBox<>();
        cmdClearPost = new javax.swing.JButton();
        cmdFindUser = new javax.swing.JButton();
        txtPosts = new javax.swing.JScrollPane();
        txtPostArea = new javax.swing.JTextArea();
        cmdPlayYourSong = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cmdLogOut.setText("Log Out");
        cmdLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLogOutActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitle.setText("Main Screen");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Friends List");

        ListAllFriends.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(ListAllFriends);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Active Friends");

        ListActiveFriends.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(ListActiveFriends);

        lblSendRequest.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSendRequest.setText("Add a new friend");

        cmdSendFreindRequest.setText("Send Request");
        cmdSendFreindRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSendFreindRequestActionPerformed(evt);
            }
        });

        cmdUploadSong.setText("Upload New Song");
        cmdUploadSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUploadSongActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Friend Requests");

        ListFriendRequests.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(ListFriendRequests);

        cmdAcceptFriendRequest.setText("Accept");
        cmdAcceptFriendRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAcceptFriendRequestActionPerformed(evt);
            }
        });

        cmdDeclineFriendRequest.setText("Decline");
        cmdDeclineFriendRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeclineFriendRequestActionPerformed(evt);
            }
        });

        lblUsername.setText("Username:");

        lblFirstName.setText("First Name:");

        lblSecondName.setText("Second Name:");

        lblEmail.setText("Email: ");

        lblPreferences.setText("Prefences: ");

        cmdPost.setText("Post");
        cmdPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPostActionPerformed(evt);
            }
        });

        lblPosts.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPosts.setText("Friends Posts");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Users Songs");

        listSelectedUsersSongs.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(listSelectedUsersSongs);

        cmdChat.setText("Chat");
        cmdChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdChatActionPerformed(evt);
            }
        });

        cmdDeleteFriend.setText("Delete Friend");
        cmdDeleteFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteFriendActionPerformed(evt);
            }
        });

        cmdPlayUsersSong.setText("Play Users Song");
        cmdPlayUsersSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPlayUsersSongActionPerformed(evt);
            }
        });

        lblOwnSongs.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblOwnSongs.setText("Your Songs");

        ListMySongs.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane6.setViewportView(ListMySongs);

        cbUserMood.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Mood:", "Happy", "Sad", "Miserable", "Stressed", "Relieved", "Excited", "Angry" }));

        cmdClearPost.setText("Clear Post");
        cmdClearPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdClearPostActionPerformed(evt);
            }
        });

        cmdFindUser.setText("Find User");
        cmdFindUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdFindUserActionPerformed(evt);
            }
        });

        txtPostArea.setColumns(20);
        txtPostArea.setRows(5);
        txtPosts.setViewportView(txtPostArea);

        cmdPlayYourSong.setText("Play Your Song");
        cmdPlayYourSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPlayYourSongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(286, 286, 286)
                        .addComponent(lblTitle)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSendRequest)
                            .addComponent(txtNewFriendRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdSendFreindRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(jLabel1))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 21, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPosts)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblProfilePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblSecondName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblPreferences, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cmdFindUser, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(cmdChat, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmdDeleteFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(cbUserMood, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(cmdClearPost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(cmdAcceptFriendRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cmdDeclineFriendRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cmdUploadSong, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(cmdLogOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(txtNewPost, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(cmdPost, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtPosts, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane5)
                            .addComponent(lblOwnSongs)
                            .addComponent(jScrollPane6)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmdPlayUsersSong, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmdPlayYourSong, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 11, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblSecondName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblPreferences, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmdDeleteFriend)
                                    .addComponent(cmdPlayUsersSong)
                                    .addComponent(cmdChat)
                                    .addComponent(cmdFindUser)
                                    .addComponent(cmdPlayYourSong)))
                            .addComponent(lblProfilePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(30, 30, 30)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPosts)
                        .addComponent(lblOwnSongs)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(txtPosts))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNewPost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdPost, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbUserMood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdClearPost, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSendRequest)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmdAcceptFriendRequest)
                            .addComponent(cmdUploadSong))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmdDeclineFriendRequest)
                            .addComponent(cmdLogOut)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNewFriendRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdSendFreindRequest)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLogOutActionPerformed
        
        //message to server to say disconnected user
        InfoPacket LoggingOff = new InfoPacket();
        
        LoggingOff.SetService("LGO");
        LoggingOff.SetSingleData(Username);
        
            
        try {
            //Send the NamePass infopacket
            Socket MainServer = new Socket("localhost", 9090);
            ObjectOutputStream ToServerStream = new ObjectOutputStream(MainServer.getOutputStream());
            System.out.println("Made Output Stream");
            ToServerStream.writeObject(LoggingOff);
            
            ObjectInputStream FromServerStream = new ObjectInputStream(MainServer.getInputStream());
            
            //Get reply 
            InfoPacket ServerReply = (InfoPacket) FromServerStream.readObject();
            
            
            MainServer.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new LogInScreen().setVisible(true);
        this.dispose();        
    }//GEN-LAST:event_cmdLogOutActionPerformed

    private void cmdUploadSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUploadSongActionPerformed
        new UploadSong(Username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cmdUploadSongActionPerformed

    private void cmdSendFreindRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSendFreindRequestActionPerformed
        try {
            
            InfoPacket FriendRequest = new InfoPacket();
            //GET MY FRIENDS
            FriendRequest.SetService("NFR");
            ArrayList<String> Users = new ArrayList();
            Users.add(Username);
            Users.add(txtNewFriendRequest.getText());
            FriendRequest.SetSingleData(Username);
            FriendRequest.SetArray(Users);
        
            Socket MainServer = new Socket("localhost", 9090);

            ObjectOutputStream OutToServer = new ObjectOutputStream(MainServer.getOutputStream());
            ObjectInputStream FromServerStream = new ObjectInputStream(MainServer.getInputStream());

            

            OutToServer.writeObject(FriendRequest);

            InfoPacket ServerReply = (InfoPacket) FromServerStream.readObject();

            ArrayList<String> AllUsersFriends = ServerReply.GetArray();

            OutToServer.close();
            FromServerStream.close();

            txtNewFriendRequest.setText("");
            
            JOptionPane.showMessageDialog(this,
            "Friend Request Sent",
            "Success",
            JOptionPane.PLAIN_MESSAGE);
            
            OutToServer.close();
            FromServerStream.close();
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_cmdSendFreindRequestActionPerformed

    private void cmdAcceptFriendRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAcceptFriendRequestActionPerformed
        String SecondUser = ListFriendRequests.getSelectedValue();
        ArrayList<String> AcceptRequest = new ArrayList();
        AcceptRequest.add(Username);
        AcceptRequest.add(SecondUser);
        
        InfoPacket AcceptFriendRequest = new InfoPacket();
        AcceptFriendRequest.SetService("AFR");
        AcceptFriendRequest.SetArray(AcceptRequest);
        try {
            Socket MainServer = new Socket("localhost", 9090);

            ObjectOutputStream OutToServer = new ObjectOutputStream(MainServer.getOutputStream());
            ObjectInputStream FromServerStream = new ObjectInputStream(MainServer.getInputStream());



            OutToServer.writeObject(AcceptFriendRequest);

            InfoPacket ServerReply = (InfoPacket) FromServerStream.readObject();

            OutToServer.close();
            FromServerStream.close();
            RefreshAllFriendsList();
            RefreshFriendsRequestList();
        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        
    }//GEN-LAST:event_cmdAcceptFriendRequestActionPerformed

    private void cmdDeclineFriendRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeclineFriendRequestActionPerformed
        String SecondUser = ListFriendRequests.getSelectedValue();
        ArrayList<String> DeclineRequest = new ArrayList();
        DeclineRequest.add(Username);
        DeclineRequest.add(SecondUser);
        
        InfoPacket DeclineFriendRequest = new InfoPacket();
        DeclineFriendRequest.SetService("DFR");
        DeclineFriendRequest.SetArray(DeclineRequest);
        try {
            Socket MainServer = new Socket("localhost", 9090);

            ObjectOutputStream OutToServer = new ObjectOutputStream(MainServer.getOutputStream());
            ObjectInputStream FromServerStream = new ObjectInputStream(MainServer.getInputStream());



            OutToServer.writeObject(DeclineFriendRequest);

            InfoPacket ServerReply = (InfoPacket) FromServerStream.readObject();

            OutToServer.close();
            FromServerStream.close();
            RefreshAllFriendsList();
            RefreshFriendsRequestList();
        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_cmdDeclineFriendRequestActionPerformed

    private void cmdClearPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdClearPostActionPerformed
        ClearPost();
    }//GEN-LAST:event_cmdClearPostActionPerformed

    private void cmdPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPostActionPerformed
        
        String PostMessage = txtNewPost.getText();
        String UserMood = cbUserMood.getSelectedItem().toString();
        ArrayList<String> PostDetails = new ArrayList();
        PostDetails.add(Username);
        PostDetails.add("TextPost");
        PostDetails.add(PostMessage);
        PostDetails.add(UserMood);
        
        InfoPacket NewPost = new InfoPacket();
        
        NewPost.SetService("UNP");
        NewPost.SetArray(PostDetails);
        
        try {
            Socket MainServer = new Socket("localhost", 9090);

            ObjectOutputStream OutToServer = new ObjectOutputStream(MainServer.getOutputStream());
            ObjectInputStream FromServerStream = new ObjectInputStream(MainServer.getInputStream());



            OutToServer.writeObject(NewPost);

            InfoPacket ServerReply = (InfoPacket) FromServerStream.readObject();

            OutToServer.close();
            FromServerStream.close();
            //Update posts
            ClearPost();
            RefreshPosts();
        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        
        
    }//GEN-LAST:event_cmdPostActionPerformed

    private void cmdFindUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdFindUserActionPerformed
        String UsernameToFind = ListAllFriends.getSelectedValue();
        InfoPacket FindUserDetails = new InfoPacket();
        FindUserDetails.SetService("GUD");
        FindUserDetails.SetSingleData(UsernameToFind);
        
        try {
            Socket MainServer = new Socket("localhost", 9090);

            ObjectOutputStream OutToServer = new ObjectOutputStream(MainServer.getOutputStream());
            ObjectInputStream FromServerStream = new ObjectInputStream(MainServer.getInputStream());

            OutToServer.writeObject(FindUserDetails);

            InfoPacket ServerReply = (InfoPacket) FromServerStream.readObject();

            OutToServer.close();
            FromServerStream.close();
            
            ArrayList<ArrayList<String>> UserInformation = ServerReply.GetMultipleArray();
            
            byte [] ProfileImage = (byte []) ServerReply.GetByteData();
            String WhereToSave = "C:/Users/samal/Documents/2nd Year/Systems Software/Shitify/Sams Work/TestingCoursework/res/Photos/" + UsernameToFind + ".png";
            FileOutputStream FileOut = new FileOutputStream(WhereToSave);
            FileOut.write(ProfileImage);
            
            lblProfilePicture.setIcon(ResizeImage(WhereToSave));
            lblUsername.setText("Username: " + UsernameToFind);     
            lblFirstName.setText("First Name: " + UserInformation.get(0).get(0));
            lblSecondName.setText("Second Name: " + UserInformation.get(0).get(1));
            lblEmail.setText("Email: " + UserInformation.get(0).get(2));
            lblPreferences.setText("Preferences: " + UserInformation.get(0).get(3));
            
            
            RefreshUserSongs(UserInformation.get(1));
            
            
        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_cmdFindUserActionPerformed

    private void cmdChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdChatActionPerformed
        String UsernameToChat = ListAllFriends.getSelectedValue();
        new ChatWindow(UsernameToChat).setVisible(true);
        
    }//GEN-LAST:event_cmdChatActionPerformed

    private void cmdDeleteFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteFriendActionPerformed
        String usernameToDelete = ListAllFriends.getSelectedValue();
        InfoPacket DeleteUser = new InfoPacket();
        DeleteUser.SetService("DFS");
        ArrayList<String> UsersToRemove = new ArrayList();
        UsersToRemove.add(Username);
        UsersToRemove.add(usernameToDelete);
        DeleteUser.SetArray(UsersToRemove);
        
        try {
            Socket MainServer = new Socket("localhost", 9090);

            ObjectOutputStream OutToServer = new ObjectOutputStream(MainServer.getOutputStream());
            ObjectInputStream FromServerStream = new ObjectInputStream(MainServer.getInputStream());

            OutToServer.writeObject(DeleteUser);

            InfoPacket ServerReply = (InfoPacket) FromServerStream.readObject();

            OutToServer.close();
            FromServerStream.close();
            
            
            RefreshAllFriendsList();
            
            
        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        
    }//GEN-LAST:event_cmdDeleteFriendActionPerformed

    private void cmdPlayUsersSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPlayUsersSongActionPerformed
        String FileName = listSelectedUsersSongs.getSelectedValue();
        InfoPacket SelectedSong = new InfoPacket();
        SelectedSong.SetService("DWS");
        SelectedSong.SetSingleData(FileName);
        
        try {
            Socket MainServer = new Socket("localhost", 9090);

            ObjectOutputStream OutToServer = new ObjectOutputStream(MainServer.getOutputStream());
            ObjectInputStream FromServerStream = new ObjectInputStream(MainServer.getInputStream());

            OutToServer.writeObject(SelectedSong);

            InfoPacket ServerReply = (InfoPacket) FromServerStream.readObject();
            
            byte [] Song = (byte []) ServerReply.GetByteData();
            String WhereToSaveSong = "C:/Users/samal/Documents/2nd Year/Systems Software/Shitify/Sams Work/TestingCoursework/res/Music/" + FileName + ".mp3";
            FileOutputStream SongOut = new FileOutputStream(WhereToSaveSong);
            SongOut.write(Song);

            byte [] CoverPhoto = (byte []) ServerReply.GetSecondData();
            String WhereToSavePhoto= "C:/Users/samal/Documents/2nd Year/Systems Software/Shitify/Sams Work/TestingCoursework/res/Photos/" + FileName + ".png";
            FileOutputStream PhotoOut = new FileOutputStream(WhereToSavePhoto);
            PhotoOut.write(CoverPhoto);
            
            OutToServer.close();
            FromServerStream.close();
            
            //Pass song name to music player form    
        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        
    }//GEN-LAST:event_cmdPlayUsersSongActionPerformed

    private void cmdPlayYourSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPlayYourSongActionPerformed
        
        String FileName = ListMySongs.getSelectedValue();
        InfoPacket SelectedSong = new InfoPacket();
        SelectedSong.SetService("DWS");
        SelectedSong.SetSingleData(FileName);
        
        try {
            Socket MainServer = new Socket("localhost", 9090);

            ObjectOutputStream OutToServer = new ObjectOutputStream(MainServer.getOutputStream());
            ObjectInputStream FromServerStream = new ObjectInputStream(MainServer.getInputStream());

            OutToServer.writeObject(SelectedSong);

            InfoPacket ServerReply = (InfoPacket) FromServerStream.readObject();
            
            byte [] Song = (byte []) ServerReply.GetByteData();
            String WhereToSaveSong = "C:/Users/samal/Documents/2nd Year/Systems Software/Shitify/Sams Work/TestingCoursework/res/Music/" + FileName + ".mp3";
            FileOutputStream SongOut = new FileOutputStream(WhereToSaveSong);
            SongOut.write(Song);

            byte [] CoverPhoto = (byte []) ServerReply.GetSecondData();
            String WhereToSavePhoto= "C:/Users/samal/Documents/2nd Year/Systems Software/Shitify/Sams Work/TestingCoursework/res/Photos/" + FileName + ".png";
            FileOutputStream PhotoOut = new FileOutputStream(WhereToSavePhoto);
            PhotoOut.write(CoverPhoto);
            
            OutToServer.close();
            FromServerStream.close();
            //Pass song name to music player form
            
        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        
    }//GEN-LAST:event_cmdPlayYourSongActionPerformed

    public void RefreshUserSongs(ArrayList<String> Songs) throws IOException, ClassNotFoundException
    {              
        DefaultListModel UserSongs = new DefaultListModel();
        
        
        for (int i = 0; i < Songs.size(); i++)
        {
            UserSongs.addElement(Songs.get(i));
            
        }
        listSelectedUsersSongs.setModel(UserSongs);
    }
    
    public void ClearPost()
    {
        txtNewPost.setText("");
        cbUserMood.setSelectedIndex(0);
    }
    
    public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lblProfilePicture.getWidth(), lblProfilePicture.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> ListActiveFriends;
    private javax.swing.JList<String> ListAllFriends;
    private javax.swing.JList<String> ListFriendRequests;
    private javax.swing.JList<String> ListMySongs;
    private javax.swing.JComboBox<String> cbUserMood;
    private javax.swing.JButton cmdAcceptFriendRequest;
    private javax.swing.JButton cmdChat;
    private javax.swing.JButton cmdClearPost;
    private javax.swing.JButton cmdDeclineFriendRequest;
    private javax.swing.JButton cmdDeleteFriend;
    private javax.swing.JButton cmdFindUser;
    private javax.swing.JButton cmdLogOut;
    private javax.swing.JButton cmdPlayUsersSong;
    private javax.swing.JButton cmdPlayYourSong;
    private javax.swing.JButton cmdPost;
    private javax.swing.JButton cmdSendFreindRequest;
    private javax.swing.JButton cmdUploadSong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblOwnSongs;
    private javax.swing.JLabel lblPosts;
    private javax.swing.JLabel lblPreferences;
    private javax.swing.JLabel lblProfilePicture;
    private javax.swing.JLabel lblSecondName;
    private javax.swing.JLabel lblSendRequest;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JList<String> listSelectedUsersSongs;
    private javax.swing.JTextField txtNewFriendRequest;
    private javax.swing.JTextField txtNewPost;
    private javax.swing.JTextArea txtPostArea;
    private javax.swing.JScrollPane txtPosts;
    // End of variables declaration//GEN-END:variables
}
