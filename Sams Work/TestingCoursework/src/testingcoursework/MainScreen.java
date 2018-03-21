/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingcoursework;

import infopacket.InfoPacket;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
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
import javax.swing.border.LineBorder;
import java.io.FileInputStream;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.util.concurrent.TimeUnit;
import javazoom.jl.converter.Converter;
import javazoom.jl.decoder.JavaLayerException;

public class MainScreen extends javax.swing.JFrame {

    /**
     * Creates new form MainScreen
     */
    private String Username;
    
    // Sets colours to be used in design
    Color foreground = Color.decode("#FDFFFC");
    Color background = Color.decode("#2E2F2F");
    Color highlight = Color.decode("#5DFDCB");
    Color warning = Color.decode("#FF3366");
    
    // Used for border thickness
    int buttonBorder = 4;
    int listBorder = 4;
    
    //Used for music player
    boolean musicPlaying = false;
    String previousSongChoice = "";
    AudioStream audioStream = null;
    
    public MainScreen() {
        initComponents();
    }
    
    public MainScreen(String UserName) throws IOException, ClassNotFoundException {
        
        initComponents();
        
        //Sets frame background colour
        getContentPane().setBackground(background);
        
        //Sets background, text and border colours for buttons
        cmdAcceptFriendRequest.setContentAreaFilled(false);
        cmdAcceptFriendRequest.setBackground(background);
        cmdAcceptFriendRequest.setBorder(new LineBorder(foreground, buttonBorder));
	cmdAcceptFriendRequest.setForeground(foreground);
        
        cmdChat.setContentAreaFilled(false);
        cmdChat.setBackground(background);
        cmdChat.setBorder(new LineBorder(foreground, buttonBorder));
	cmdChat.setForeground(foreground);
        
        cmdClearPost.setContentAreaFilled(false);
        cmdClearPost.setBackground(background);
        cmdClearPost.setBorder(new LineBorder(foreground, buttonBorder));
	cmdClearPost.setForeground(foreground);
        
        cmdDeclineFriendRequest.setContentAreaFilled(false);
        cmdDeclineFriendRequest.setBackground(background);
        cmdDeclineFriendRequest.setBorder(new LineBorder(foreground, buttonBorder));
	cmdDeclineFriendRequest.setForeground(foreground);
        
        cmdDeleteFriend.setContentAreaFilled(false);
        cmdDeleteFriend.setBackground(background);
        cmdDeleteFriend.setBorder(new LineBorder(foreground, buttonBorder));
	cmdDeleteFriend.setForeground(foreground);
        
        cmdFindUser.setContentAreaFilled(false);
        cmdFindUser.setBackground(background);
        cmdFindUser.setBorder(new LineBorder(foreground, buttonBorder));
	cmdFindUser.setForeground(foreground);
        
        cmdLogOut.setContentAreaFilled(false);
        cmdLogOut.setBackground(background);
        cmdLogOut.setBorder(new LineBorder(foreground, buttonBorder));
	cmdLogOut.setForeground(foreground);
        
        cmdPost.setContentAreaFilled(false);
        cmdPost.setBackground(background);
        cmdPost.setBorder(new LineBorder(foreground, buttonBorder));
	cmdPost.setForeground(foreground);
        
        cmdSendFreindRequest.setContentAreaFilled(false);
        cmdSendFreindRequest.setBackground(background);
        cmdSendFreindRequest.setBorder(new LineBorder(foreground, buttonBorder));
	cmdSendFreindRequest.setForeground(foreground);
        
        cmdUploadSong.setContentAreaFilled(false);
        cmdUploadSong.setBackground(background);
        cmdUploadSong.setBorder(new LineBorder(foreground, buttonBorder));
	cmdUploadSong.setForeground(foreground);
        
        cmdPlayPause.setContentAreaFilled(false);
        cmdPlayPause.setBackground(background);
	cmdPlayPause.setForeground(foreground);
        cmdPlayPause.setIcon(ResizeImage("./res/Photos/SystemPics/PlayPause.png"));
        
        cmdFindOtherUsers.setContentAreaFilled(false);
        cmdFindOtherUsers.setBackground(background);
        cmdFindOtherUsers.setBorder(new LineBorder(foreground, buttonBorder));
	cmdFindOtherUsers.setForeground(foreground);
        
        // Sets background and foreground colours for lists and removes the 
        // scroll pane border
        ListMySongs.setBackground(background);
        ListMySongs.setForeground(foreground);
        jScrollPane6.setBorder(null);
        
        ListActiveFriends.setBackground(background);
        ListActiveFriends.setForeground(foreground);
        jScrollPane2.setBorder(null);
        
        
        ListAllFriends.setBackground(background);
        ListAllFriends.setForeground(foreground);
        jScrollPane1.setBorder(null);
        
        ListFriendRequests.setBackground(background);
        ListFriendRequests.setForeground(foreground);
        jScrollPane3.setBorder(null);
        
        listSelectedUsersSongs.setBackground(background);
        listSelectedUsersSongs.setForeground(foreground);
        jScrollPane5.setBorder(null);
        
        txtPostArea.setBackground(background);
        txtPostArea.setForeground(foreground);
        txtPosts.setBorder(null);
        
        // Adds border for profile picture label
        lblProfilePicture.setBorder(new LineBorder(foreground, 2));
        
        // Uses Label borders as white lines
        jLabel4.setBorder(new LineBorder(foreground, 5));
        jLabel7.setBorder(new LineBorder(foreground, 4));
        jLabel8.setBorder(new LineBorder(foreground, 4));
        jLabel9.setBorder(new LineBorder(foreground, 4));
        jLabel10.setBorder(new LineBorder(foreground, 4));
        jLabel11.setBorder(new LineBorder(foreground, 5));
        jLabel12.setBorder(new LineBorder(foreground, 4));
        
        // Sets Logo
        
        jLabel13.setIcon(ResizeImage("./res/Photos/SystemPics/logo.png"));
        
        // Styles drop down menu
        cbUserMood.setBackground(foreground);
        cbUserMood.setUI(new CustomComboBoxUI());
        
        // Styles textboxs on pages
        txtNewPost.setBorder(new LineBorder(foreground, 4));
        txtNewFriendRequest.setBorder(new LineBorder(foreground, 4));
        
        // Styles progress bar
        songProgress.setBackground(foreground);
        songProgress.setForeground(highlight);
        songProgress.setBorder(null);
        
        // Turns vertical scroll bars on permenatly and sets the style
        txtPosts.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        jScrollPane2.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        jScrollPane3.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        jScrollPane5.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        jScrollPane6.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        
        this.Username = UserName;
        RefreshAllFriendsList();
        RefreshFriendsRequestList();
        RefreshMySongs();
        RefreshPosts();
        RefreshActiveFriendsList();
        //Thread timer to refresh
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
            if ("TextPost".equals(FriendsPosts.get(i).get(4)))
            {
                if ("Select Mood:".equals(FriendsPosts.get(i).get(3))) {
                    PostFormat = FriendsPosts.get(i).get(0) + " - " + FriendsPosts.get(i).get(1) + ": " + FriendsPosts.get(i).get(2) +"\n";
                }
                else {
                    PostFormat = FriendsPosts.get(i).get(0) + " - " + FriendsPosts.get(i).get(1) + ": " + FriendsPosts.get(i).get(2) + " - Feeling " + FriendsPosts.get(i).get(3)+"\n";
                }
            }
            else if ("SongUpload".equals(FriendsPosts.get(i).get(4)))
            {
                PostFormat = FriendsPosts.get(i).get(0) + " - " + FriendsPosts.get(i).get(1) + " uploaded a new song: " + FriendsPosts.get(i).get(2) +"\n";
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
        jScrollPane5 = new javax.swing.JScrollPane();
        listSelectedUsersSongs = new javax.swing.JList<>();
        cmdChat = new javax.swing.JButton();
        cmdDeleteFriend = new javax.swing.JButton();
        lblOwnSongs = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        ListMySongs = new javax.swing.JList<>();
        cbUserMood = new javax.swing.JComboBox<>();
        cmdClearPost = new javax.swing.JButton();
        cmdFindUser = new javax.swing.JButton();
        txtPosts = new javax.swing.JScrollPane();
        txtPostArea = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmdPlayPause = new javax.swing.JButton();
        songProgress = new javax.swing.JProgressBar();
        cmdFindOtherUsers = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(43, 45, 66));

        cmdLogOut.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        cmdLogOut.setText("LOG OUT");
        cmdLogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdLogOutMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdLogOutMouseEntered(evt);
            }
        });
        cmdLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLogOutActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Futura", 1, 70)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(93, 253, 203));
        lblTitle.setText("HITIFY");

        jLabel1.setFont(new java.awt.Font("Futura", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(93, 253, 203));
        jLabel1.setText("FRIENDS LIST");

        ListAllFriends.setFont(new java.awt.Font("Futura", 0, 14)); // NOI18N
        ListAllFriends.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ListAllFriends.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListAllFriendsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ListAllFriends);

        jLabel2.setFont(new java.awt.Font("Futura", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(93, 253, 203));
        jLabel2.setText("ACTIVE FRIENDS");

        ListActiveFriends.setFont(new java.awt.Font("Futura", 0, 14)); // NOI18N
        ListActiveFriends.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ListActiveFriends.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListActiveFriendsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(ListActiveFriends);

        txtNewFriendRequest.setFont(new java.awt.Font("Futura", 0, 13)); // NOI18N
        txtNewFriendRequest.setForeground(new java.awt.Color(46, 47, 47));

        cmdSendFreindRequest.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        cmdSendFreindRequest.setText("SEND REQUEST");
        cmdSendFreindRequest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdSendFreindRequestMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdSendFreindRequestMouseEntered(evt);
            }
        });
        cmdSendFreindRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSendFreindRequestActionPerformed(evt);
            }
        });

        cmdUploadSong.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        cmdUploadSong.setText("UPLOAD SONG");
        cmdUploadSong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdUploadSongMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdUploadSongMouseEntered(evt);
            }
        });
        cmdUploadSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUploadSongActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Futura", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(93, 253, 203));
        jLabel3.setText("FRIEND REQUESTS");

        ListFriendRequests.setFont(new java.awt.Font("Futura", 0, 14)); // NOI18N
        ListFriendRequests.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ListFriendRequests.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListFriendRequestsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(ListFriendRequests);

        cmdAcceptFriendRequest.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        cmdAcceptFriendRequest.setText("ACCEPT");
        cmdAcceptFriendRequest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdAcceptFriendRequestMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdAcceptFriendRequestMouseEntered(evt);
            }
        });
        cmdAcceptFriendRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAcceptFriendRequestActionPerformed(evt);
            }
        });

        cmdDeclineFriendRequest.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        cmdDeclineFriendRequest.setText("DECLINE");
        cmdDeclineFriendRequest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdDeclineFriendRequestMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdDeclineFriendRequestMouseEntered(evt);
            }
        });
        cmdDeclineFriendRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeclineFriendRequestActionPerformed(evt);
            }
        });

        lblUsername.setFont(new java.awt.Font("Futura", 0, 13)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(253, 255, 252));
        lblUsername.setText("Username:");

        lblFirstName.setFont(new java.awt.Font("Futura", 0, 13)); // NOI18N
        lblFirstName.setForeground(new java.awt.Color(253, 255, 252));
        lblFirstName.setText("First Name:");

        lblSecondName.setFont(new java.awt.Font("Futura", 0, 13)); // NOI18N
        lblSecondName.setForeground(new java.awt.Color(253, 255, 252));
        lblSecondName.setText("Second Name:");

        lblEmail.setFont(new java.awt.Font("Futura", 0, 13)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(253, 255, 252));
        lblEmail.setText("Email: ");

        lblPreferences.setFont(new java.awt.Font("Futura", 0, 13)); // NOI18N
        lblPreferences.setForeground(new java.awt.Color(253, 255, 252));
        lblPreferences.setText("Prefences: ");

        txtNewPost.setFont(new java.awt.Font("Futura", 0, 13)); // NOI18N
        txtNewPost.setForeground(new java.awt.Color(46, 47, 47));
        txtNewPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNewPostActionPerformed(evt);
            }
        });

        cmdPost.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        cmdPost.setText("POST");
        cmdPost.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdPostMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdPostMouseEntered(evt);
            }
        });
        cmdPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPostActionPerformed(evt);
            }
        });

        listSelectedUsersSongs.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listSelectedUsersSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listSelectedUsersSongsMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(listSelectedUsersSongs);

        cmdChat.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        cmdChat.setText("CHAT");
        cmdChat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdChatMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdChatMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdChatMouseEntered(evt);
            }
        });
        cmdChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdChatActionPerformed(evt);
            }
        });

        cmdDeleteFriend.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        cmdDeleteFriend.setText("REMOVE");
        cmdDeleteFriend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdDeleteFriendMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdDeleteFriendMouseEntered(evt);
            }
        });
        cmdDeleteFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteFriendActionPerformed(evt);
            }
        });

        lblOwnSongs.setFont(new java.awt.Font("Futura", 0, 24)); // NOI18N
        lblOwnSongs.setForeground(new java.awt.Color(93, 253, 203));
        lblOwnSongs.setText("YOUR MUSIC");

        ListMySongs.setFont(new java.awt.Font("Futura", 0, 14)); // NOI18N
        ListMySongs.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ListMySongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListMySongsMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(ListMySongs);

        cbUserMood.setFont(new java.awt.Font("Futura", 0, 13)); // NOI18N
        cbUserMood.setForeground(new java.awt.Color(46, 47, 47));
        cbUserMood.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Mood:", "Happy", "Sad", "Miserable", "Stressed", "Relieved", "Excited", "Angry" }));

        cmdClearPost.setFont(new java.awt.Font("Futura", 0, 13)); // NOI18N
        cmdClearPost.setText("CLEAR");
        cmdClearPost.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdClearPostMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdClearPostMouseEntered(evt);
            }
        });
        cmdClearPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdClearPostActionPerformed(evt);
            }
        });

        cmdFindUser.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        cmdFindUser.setText("PROFILE");
        cmdFindUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdFindUserMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdFindUserMouseEntered(evt);
            }
        });
        cmdFindUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdFindUserActionPerformed(evt);
            }
        });

        txtPosts.setBackground(new java.awt.Color(46, 47, 47));
        txtPosts.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtPostArea.setColumns(20);
        txtPostArea.setFont(new java.awt.Font("Futura", 0, 14)); // NOI18N
        txtPostArea.setRows(5);
        txtPostArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtPosts.setViewportView(txtPostArea);

        jLabel5.setFont(new java.awt.Font("Futura", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(93, 253, 203));
        jLabel5.setText("NEWS FEED");

        jLabel6.setFont(new java.awt.Font("Futura", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(93, 253, 203));
        jLabel6.setText("FRIENDS PROFILE");

        cmdPlayPause.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdPlayPauseMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdPlayPauseMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdPlayPauseMouseEntered(evt);
            }
        });
        cmdPlayPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPlayPauseActionPerformed(evt);
            }
        });

        cmdFindOtherUsers.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        cmdFindOtherUsers.setText("FIND USERS");
        cmdFindOtherUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdFindOtherUsersMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdFindOtherUsersMouseEntered(evt);
            }
        });
        cmdFindOtherUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdFindOtherUsersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmdUploadSong, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblOwnSongs, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(51, 51, 51)
                                                .addComponent(cmdPlayPause, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmdLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(cbUserMood, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(cmdClearPost, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(txtNewPost))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cmdPost, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel5)
                                                    .addComponent(txtPosts, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel2)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addComponent(cmdChat, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel1)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(cmdFindUser, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cmdDeleteFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(1, 1, 1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(lblProfilePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(lblSecondName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(lblFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addComponent(lblPreferences, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jLabel6))
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                                                    .addComponent(txtNewFriendRequest))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(cmdSendFreindRequest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addComponent(cmdAcceptFriendRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(cmdDeclineFriendRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                    .addComponent(cmdFindOtherUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addComponent(jLabel3)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(songProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 1062, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmdLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(7, 7, 7)
                                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel5)
                                                .addComponent(lblOwnSongs))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(txtNewPost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(cmdClearPost)
                                                        .addComponent(cbUserMood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addComponent(cmdPost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtPosts, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(jScrollPane1)
                                                    .addGap(42, 42, 42))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(cmdChat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cmdFindUser, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cmdDeleteFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel6)
                                                .addComponent(jLabel3))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(7, 7, 7)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(lblUsername)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(lblFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(lblSecondName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(lblProfilePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(10, 10, 10)
                                                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(lblPreferences, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(4, 4, 4)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addComponent(cmdAcceptFriendRequest, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                                            .addComponent(cmdDeclineFriendRequest, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(cmdFindOtherUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtNewFriendRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cmdSendFreindRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmdUploadSong, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addComponent(jLabel12)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdPlayPause, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(songProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLogOutActionPerformed
        
        //message to server to say disconnected user
        InfoPacket LoggingOff = new InfoPacket();
        
        LoggingOff.SetService("LGO");
        LoggingOff.SetSingleData(Username);
        
        File Pdir = new File("res/Photos"); 
        File Mdir = new File("res/Music"); 
            
        try {
            //Send the NamePass infopacket
            //Remove Files in Music and Photo Folders
            for(File file: Pdir.listFiles()) 
                if (!file.isDirectory()) 
                    file.delete();
            for(File file: Mdir.listFiles()) 
                if (!file.isDirectory()) 
                    file.delete();
            
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
            //FriendRequest.SetSingleData(Username);
            FriendRequest.SetArray(Users);
        
            Socket MainServer = new Socket("localhost", 9090);

            ObjectOutputStream OutToServer = new ObjectOutputStream(MainServer.getOutputStream());
            ObjectInputStream FromServerStream = new ObjectInputStream(MainServer.getInputStream());

            

            OutToServer.writeObject(FriendRequest);

            InfoPacket ServerReply = (InfoPacket) FromServerStream.readObject();

            String State = ServerReply.GetData();

            OutToServer.close();
            FromServerStream.close();

            
            
            if ("Exists".equals(State))
            {
                JOptionPane.showMessageDialog(this,
                "Request has been sent to " + txtNewFriendRequest.getText(),
                "Friend Request",
                 JOptionPane.PLAIN_MESSAGE);
            }
            else if ("Doesnt".equals(State))
            {
                JOptionPane.showMessageDialog(this,
                "Declined, this username does not exist on Hitify",
                "Friend Request",
                 JOptionPane.PLAIN_MESSAGE);
            }
            else if ("AlreadyFriends".equals(State))
            {
                JOptionPane.showMessageDialog(this,
                "You are already friends with this user, or they have sent you a friend request",
                "Friend Request",
                 JOptionPane.PLAIN_MESSAGE);
            }
            
            txtNewFriendRequest.setText("");
            
            OutToServer.close();
            FromServerStream.close();
            
        } catch (IOException | ClassNotFoundException e) {
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
            
            
            File PhotoDirectory = new File("res/Photos/" + UsernameToFind + ".png");
            
            byte [] ProfileImage = (byte []) ServerReply.GetByteData();
            FileOutputStream FileOut = new FileOutputStream(PhotoDirectory);
            FileOut.write(ProfileImage);
            
            
            
            lblProfilePicture.setIcon(ResizeImage(PhotoDirectory.getPath()));
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
        if(ListAllFriends.getSelectedValue() != null){
            new ChatWindow(Username, ListAllFriends.getSelectedValue()).setVisible(true);
        }else if(ListActiveFriends.getSelectedValue() != null){
            new ChatWindow(Username, ListActiveFriends.getSelectedValue()).setVisible(true);
        }
        
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

    
    
    
    private void txtNewPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNewPostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNewPostActionPerformed

    private void cmdUploadSongMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdUploadSongMouseEntered
        // Changes colour of button when hovered over
        cmdUploadSong.setContentAreaFilled(true);
        cmdUploadSong.setBackground(foreground);
        cmdUploadSong.setForeground(highlight);
    }//GEN-LAST:event_cmdUploadSongMouseEntered

    private void cmdUploadSongMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdUploadSongMouseExited
         // Changes colour of button when mouse isn't hovering
        cmdUploadSong.setContentAreaFilled(false);
        cmdUploadSong.setForeground(foreground);
    }//GEN-LAST:event_cmdUploadSongMouseExited

    private void cmdLogOutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdLogOutMouseEntered
        // Changes colour of button when hovered over
        cmdLogOut.setContentAreaFilled(true);
        cmdLogOut.setBackground(foreground);
        cmdLogOut.setForeground(warning);
    }//GEN-LAST:event_cmdLogOutMouseEntered

    private void cmdLogOutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdLogOutMouseExited
        // Changes colour of button when mouse isn't hovering
        cmdLogOut.setContentAreaFilled(false);
        cmdLogOut.setForeground(foreground);
    }//GEN-LAST:event_cmdLogOutMouseExited

    private void cmdPostMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdPostMouseEntered
        // Changes colour of button when hovered over
        cmdPost.setContentAreaFilled(true);
        cmdPost.setBackground(foreground);
        cmdPost.setForeground(highlight);
    }//GEN-LAST:event_cmdPostMouseEntered

    private void cmdPostMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdPostMouseExited
        // Changes colour of button when mouse isn't hovering
        cmdPost.setContentAreaFilled(false);
        cmdPost.setForeground(foreground);
    }//GEN-LAST:event_cmdPostMouseExited

    private void cmdClearPostMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdClearPostMouseEntered
        // Changes colour of button when hovered over
        cmdClearPost.setContentAreaFilled(true);
        cmdClearPost.setBackground(foreground);
        cmdClearPost.setForeground(warning);
    }//GEN-LAST:event_cmdClearPostMouseEntered

    private void cmdClearPostMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdClearPostMouseExited
        // Changes colour of button when mouse isn't hovering
        cmdClearPost.setContentAreaFilled(false);
        cmdClearPost.setForeground(foreground);
    }//GEN-LAST:event_cmdClearPostMouseExited

    private void cmdChatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdChatMouseEntered
        // Changes colour of button when hovered over
        cmdChat.setContentAreaFilled(true);
        cmdChat.setBackground(foreground);
        cmdChat.setForeground(highlight);
    }//GEN-LAST:event_cmdChatMouseEntered

    private void cmdChatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdChatMouseExited
        // Changes colour of button when mouse isn't hovering
        cmdChat.setContentAreaFilled(false);
        cmdChat.setForeground(foreground);
    }//GEN-LAST:event_cmdChatMouseExited

    private void cmdFindUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdFindUserMouseEntered
        // Changes colour of button when hovered over
        cmdFindUser.setContentAreaFilled(true);
        cmdFindUser.setBackground(foreground);
        cmdFindUser.setForeground(highlight);
    }//GEN-LAST:event_cmdFindUserMouseEntered

    private void cmdFindUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdFindUserMouseExited
        // Changes colour of button when mouse isn't hovering
        cmdFindUser.setContentAreaFilled(false);
        cmdFindUser.setForeground(foreground);
    }//GEN-LAST:event_cmdFindUserMouseExited

    private void cmdDeleteFriendMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdDeleteFriendMouseEntered
        // Changes colour of button when hovered over
        cmdDeleteFriend.setContentAreaFilled(true);
        cmdDeleteFriend.setBackground(foreground);
        cmdDeleteFriend.setForeground(warning);
    }//GEN-LAST:event_cmdDeleteFriendMouseEntered

    private void cmdDeleteFriendMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdDeleteFriendMouseExited
        // Changes colour of button when mouse isn't hovering
        cmdDeleteFriend.setContentAreaFilled(false);
        cmdDeleteFriend.setForeground(foreground);
    }//GEN-LAST:event_cmdDeleteFriendMouseExited

    private void cmdAcceptFriendRequestMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdAcceptFriendRequestMouseEntered
        // Changes colour of button when hovered over
        cmdAcceptFriendRequest.setContentAreaFilled(true);
        cmdAcceptFriendRequest.setBackground(foreground);
        cmdAcceptFriendRequest.setForeground(highlight);
    }//GEN-LAST:event_cmdAcceptFriendRequestMouseEntered

    private void cmdAcceptFriendRequestMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdAcceptFriendRequestMouseExited
        // Changes colour of button when mouse isn't hovering
        cmdAcceptFriendRequest.setContentAreaFilled(false);
        cmdAcceptFriendRequest.setForeground(foreground);
    }//GEN-LAST:event_cmdAcceptFriendRequestMouseExited

    private void cmdDeclineFriendRequestMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdDeclineFriendRequestMouseEntered
        // Changes colour of button when hovered over
        cmdDeclineFriendRequest.setContentAreaFilled(true);
        cmdDeclineFriendRequest.setBackground(foreground);
        cmdDeclineFriendRequest.setForeground(warning);
    }//GEN-LAST:event_cmdDeclineFriendRequestMouseEntered

    private void cmdDeclineFriendRequestMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdDeclineFriendRequestMouseExited
        // Changes colour of button when mouse isn't hovering
        cmdDeclineFriendRequest.setContentAreaFilled(false);
        cmdDeclineFriendRequest.setForeground(foreground);
    }//GEN-LAST:event_cmdDeclineFriendRequestMouseExited

    private void cmdSendFreindRequestMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdSendFreindRequestMouseEntered
        // Changes colour of button when hovered over
        cmdSendFreindRequest.setContentAreaFilled(true);
        cmdSendFreindRequest.setBackground(foreground);
        cmdSendFreindRequest.setForeground(highlight);
    }//GEN-LAST:event_cmdSendFreindRequestMouseEntered

    private void cmdSendFreindRequestMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdSendFreindRequestMouseExited
        // Changes colour of button when mouse isn't hovering
        cmdSendFreindRequest.setContentAreaFilled(false);
        cmdSendFreindRequest.setForeground(foreground);
    }//GEN-LAST:event_cmdSendFreindRequestMouseExited

    private void ListMySongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListMySongsMouseClicked
        // Clears selections on other lists when mouse is clicked in list area
        ListActiveFriends.clearSelection();
        ListAllFriends.clearSelection();
        listSelectedUsersSongs.clearSelection();
        ListFriendRequests.clearSelection();
    }//GEN-LAST:event_ListMySongsMouseClicked

    private void ListActiveFriendsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListActiveFriendsMouseClicked
       // Clears selections on other lists when mouse is clicked in list area
        ListMySongs.clearSelection();
        ListAllFriends.clearSelection();
        listSelectedUsersSongs.clearSelection();
        ListFriendRequests.clearSelection();
    }//GEN-LAST:event_ListActiveFriendsMouseClicked

    private void ListAllFriendsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListAllFriendsMouseClicked
        // Clears selections on other lists when mouse is clicked in list area
        ListMySongs.clearSelection();
        ListActiveFriends.clearSelection();
        listSelectedUsersSongs.clearSelection();
        ListFriendRequests.clearSelection();
    }//GEN-LAST:event_ListAllFriendsMouseClicked

    private void listSelectedUsersSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listSelectedUsersSongsMouseClicked
        // Clears selections on other lists when mouse is clicked in list area
        ListMySongs.clearSelection();
        ListActiveFriends.clearSelection();
        ListAllFriends.clearSelection();
        ListFriendRequests.clearSelection();
    }//GEN-LAST:event_listSelectedUsersSongsMouseClicked

    private void ListFriendRequestsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListFriendRequestsMouseClicked
        // Clears selections on other lists when mouse is clicked in list area
        ListMySongs.clearSelection();
        ListActiveFriends.clearSelection();
        ListAllFriends.clearSelection();
        listSelectedUsersSongs.clearSelection();
    }//GEN-LAST:event_ListFriendRequestsMouseClicked

    private void cmdChatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdChatMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdChatMouseClicked

    private void cmdPlayPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPlayPauseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdPlayPauseActionPerformed

    private void cmdPlayPauseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdPlayPauseMouseEntered
        // Changes icon colour when mouse hovers
        cmdPlayPause.setIcon(ResizeImage("./res/Photos/SystemPics/PlayPauseHover.png"));
    }//GEN-LAST:event_cmdPlayPauseMouseEntered

    private void cmdPlayPauseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdPlayPauseMouseExited
        // Changes icon colour when mouse leaves
        cmdPlayPause.setIcon(ResizeImage("./res/Photos/SystemPics/PlayPause.png"));
    }//GEN-LAST:event_cmdPlayPauseMouseExited

    private void cmdPlayPauseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdPlayPauseMouseClicked
        
        String songChoice = "";
        
        if(!(ListMySongs.isSelectionEmpty())){
            songChoice = ListMySongs.getSelectedValue();
        }else if(!(listSelectedUsersSongs.isSelectionEmpty())){
            songChoice = listSelectedUsersSongs.getSelectedValue();
        }
        //If song not same as previous song or empty choice 
        if( !(songChoice.equals(previousSongChoice)) && (songChoice != "") ){
            //if music already playing
            if(musicPlaying == true){
                AudioPlayer.player.stop(audioStream);
            }
            downloadSong(songChoice);
            convertFile(songChoice);
            playSong();
            previousSongChoice = songChoice;
            musicPlaying = true;
        }else{
            if(musicPlaying == true){
                AudioPlayer.player.stop(audioStream);
                musicPlaying = false;
            }else{
                AudioPlayer.player.start(audioStream);
                musicPlaying = true;
            }
        }
        
    }//GEN-LAST:event_cmdPlayPauseMouseClicked

    private void cmdFindOtherUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdFindOtherUsersActionPerformed
        // TODO add your handling code here:
        new FindUsers(Username).setVisible(true);
    }//GEN-LAST:event_cmdFindOtherUsersActionPerformed

    private void cmdFindOtherUsersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdFindOtherUsersMouseEntered
        // Changes colour of button when hovered over
        cmdFindOtherUsers.setContentAreaFilled(true);
        cmdFindOtherUsers.setBackground(foreground);
        cmdFindOtherUsers.setForeground(highlight);
    }//GEN-LAST:event_cmdFindOtherUsersMouseEntered

    private void cmdFindOtherUsersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdFindOtherUsersMouseExited
        // Changes colour of button when mouse isn't hovering
        cmdFindOtherUsers.setContentAreaFilled(false);
        cmdFindOtherUsers.setForeground(foreground);
    }//GEN-LAST:event_cmdFindOtherUsersMouseExited
    
    public void playSong(){
        try {
            
            // open the sound file as a Java input stream
            InputStream in = new FileInputStream("./res/Music/Conv/playSong.wav");
            // create an audiostream from the inputstream
            audioStream = new AudioStream(in);
            // play the audio clip with the audioplayer class
            AudioPlayer.player.start(audioStream);
            
        }catch(IOException e){}  
    }
    public void convertFile(String songName){
        
        String songPath = "./res/Music/" + songName + ".mp3";
        //String songPath = "./res/Music/James Bay,Wild Love.mp3";
        System.out.println(songPath);
        Converter converter = new Converter();
        try {
            converter.convert(songPath, "./res/Music/Conv/playSong.wav");
        } catch (JavaLayerException ex) {}
        //new File("./src/res/Music/Conv/playSong.wav");
       
    }
    
    public void downloadSong(String FileName)
    {
        InfoPacket SelectedSong = new InfoPacket();
        SelectedSong.SetService("DWS");
        SelectedSong.SetSingleData(FileName);
        
        try {
            Socket MainServer = new Socket("localhost", 9090);

            ObjectOutputStream OutToServer = new ObjectOutputStream(MainServer.getOutputStream());
            ObjectInputStream FromServerStream = new ObjectInputStream(MainServer.getInputStream());

            OutToServer.writeObject(SelectedSong);

            InfoPacket ServerReply = (InfoPacket) FromServerStream.readObject();
            
            File MusicDirectory = new File("res/Music/" + FileName + ".mp3");
            File PhotoDirectory = new File("res/Photos/" + FileName + ".png");
            
            byte [] Song = (byte []) ServerReply.GetByteData();
            FileOutputStream SongOut = new FileOutputStream(MusicDirectory);
            SongOut.write(Song);

            byte [] CoverPhoto = (byte []) ServerReply.GetSecondData();
            FileOutputStream PhotoOut = new FileOutputStream(PhotoDirectory);
            PhotoOut.write(CoverPhoto);
            
            OutToServer.close();
            FromServerStream.close();
            
            //Pass song name to music player form    
        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
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
    private javax.swing.JButton cmdFindOtherUsers;
    private javax.swing.JButton cmdFindUser;
    private javax.swing.JButton cmdLogOut;
    private javax.swing.JButton cmdPlayPause;
    private javax.swing.JButton cmdPost;
    private javax.swing.JButton cmdSendFreindRequest;
    private javax.swing.JButton cmdUploadSong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblOwnSongs;
    private javax.swing.JLabel lblPreferences;
    private javax.swing.JLabel lblProfilePicture;
    private javax.swing.JLabel lblSecondName;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JList<String> listSelectedUsersSongs;
    private javax.swing.JProgressBar songProgress;
    private javax.swing.JTextField txtNewFriendRequest;
    private javax.swing.JTextField txtNewPost;
    private javax.swing.JTextArea txtPostArea;
    private javax.swing.JScrollPane txtPosts;
    // End of variables declaration//GEN-END:variables
}
