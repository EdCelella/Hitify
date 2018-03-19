package testingcoursework;

import infopacket.InfoPacket;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.System.in;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class ChatWindow extends javax.swing.JFrame {
    
    // Global variables to store both usernames
    public String username;
    public String recieveUsername;
    private String filePath;
    private boolean fileAttached;  
    
    // Sets colours to be used in design
    Color foreground = Color.decode("#FDFFFC");
    Color background = Color.decode("#2E2F2F");
    Color highlight = Color.decode("#5DFDCB");
    Color warning = Color.decode("#FF3366");
    
    // Sets width of button borders
    int buttonBorder = 4;
    
    // Used for sending and recieving chat data
    InetAddress address;
    Socket msgServer;
    DataOutputStream outToMsgServer;
    DataInputStream inFromMsgServer;
    ObjectOutputStream fileOutToMsgServer;
    
    // Stores file information
    String chatName;
    File chatFile;
    
    // Used to run while loops
    boolean cont = true;
    
    public ChatWindow(String _username, String _recieveUsername) {
        
        initComponents();
        
        // Adds window listner
        closeWindow();
        
        // Gets usernames
        username = _username;
        recieveUsername = _recieveUsername;
        
        // Sets background
        getContentPane().setBackground(background);
        
        //Outputs name of user they're messaging 
        lblUsername.setText(recieveUsername);
        
        // Creates white lines
        lblTopLine.setBorder(new LineBorder(foreground, 4));
        lblBottomLine.setBorder(new LineBorder(foreground, 4));
        
        // Styles Scroll panes
        spMessages.getViewport().setBackground(background);
        spMessages.setForeground(foreground);
        spMessages.setBorder(null);
        spMessages.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        spMessages.setHorizontalScrollBarPolicy(spMessages.HORIZONTAL_SCROLLBAR_NEVER);  
        
        spNewMessage.setBackground(background);
        spNewMessage.setForeground(foreground);
        spNewMessage.setBorder(null);
        spNewMessage.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        spNewMessage.setHorizontalScrollBarPolicy(spNewMessage.HORIZONTAL_SCROLLBAR_NEVER);     
        
        // Styles text fields
        taFilePath.setBackground(background);
        taFilePath.setBorder(null);
        taFilePath.setEditable(false);
        taFilePath.setLineWrap(true);
        
        taNewMessage.setLineWrap(true);
        
        // Styles buttons
        cmdSend.setContentAreaFilled(false);
        cmdSend.setBackground(background);
        cmdSend.setBorder(new LineBorder(foreground, buttonBorder));
	cmdSend.setForeground(foreground);
        
        cmdAttach.setContentAreaFilled(false);
        cmdAttach.setBackground(background);
        cmdAttach.setBorder(new LineBorder(foreground, buttonBorder));
	cmdAttach.setForeground(foreground);
        
        cmdClear.setContentAreaFilled(false);
        cmdClear.setBackground(background);
        cmdClear.setBorder(new LineBorder(foreground, buttonBorder));
	cmdClear.setForeground(foreground);
        
        // Sets profile picture of user you're talking to
        lblProfilePicture.setBorder(new LineBorder(foreground, 2));
        
        // Styles message tabel
        tabelMessages.setShowVerticalLines(false);
        tabelMessages.setShowHorizontalLines(true);
        tabelMessages.setBackground(background);
        tabelMessages.setForeground(foreground);
        tabelMessages.setGridColor(highlight);
        tabelMessages.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabelMessages.getColumnModel().getColumn(0).setPreferredWidth(150);
        tabelMessages.getColumnModel().getColumn(1).setPreferredWidth(568);
        
        
        
        // Creates an info packet to send the username of the person you're talking to
        InfoPacket FindUserDetails = new InfoPacket();
        FindUserDetails.SetService("GUD");
        FindUserDetails.SetSingleData(recieveUsername);
        
        try {
            
            // Creates connection with music server
            Socket MainServer = new Socket("localhost", 9090);
            ObjectOutputStream OutToServer = new ObjectOutputStream(MainServer.getOutputStream());
            ObjectInputStream FromServerStream = new ObjectInputStream(MainServer.getInputStream());
            
            // Sends username to server
            OutToServer.writeObject(FindUserDetails);
            
            // Recieves users profile picture from server
            InfoPacket ServerReply = (InfoPacket) FromServerStream.readObject();
            
            //Closes connection to music server
            OutToServer.close();
            FromServerStream.close();
            
            // Gets a byte array of the profile pictuew
            byte [] ProfileImage = (byte []) ServerReply.GetByteData();
            
            // Saves the profile picture locally
            //String profilePicture = "/Users/edwardcelella/Documents/University/Systems Software/Shitify/Sams Work/MusicServer/res/Photos" + recieveUsername + ".png";
            String profilePicture = new File("res/Photos/" + recieveUsername + ".png").getAbsolutePath();
            FileOutputStream FileOut = new FileOutputStream(profilePicture);
            FileOut.write(ProfileImage);
            
            // Displays profile picture in label
            lblProfilePicture.setIcon(ResizeImage(profilePicture));
            
            // Creates connection with messaging server
            address = InetAddress.getByName("localhost");
            msgServer = new Socket(address, 9091);
            inFromMsgServer = new DataInputStream(msgServer.getInputStream());
            outToMsgServer = new DataOutputStream(msgServer.getOutputStream());
            
        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        
        // Runs thread which continuously updates message table
        Thread th = new Thread(new Runnable()
        {
            public void run(){
                SyncChatMessages();
            }
        });
        th.start();
        
    }
    
    public void closeWindow(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cont = false;
                try{
                    outToMsgServer.writeUTF("E" + "§" + "E");
                }
                catch(IOException e2){
                    // Prints error message
                    System.out.println(e2.getMessage());
                }
                e.getWindow().dispose();
            }

        });
    }
    
    public ChatWindow() {
        initComponents();
    }
    
    public void SyncChatMessages(){
        
        // Joins both usernames in alphabetical order and joins them to get chat file name
        
        int compare = username.compareTo(recieveUsername);
        if(compare < 0){
            chatName = username + recieveUsername;
        }else{
            chatName = recieveUsername + username;
        }
        
        // Gets filepath and file
        String chatFilePath = new File("res/Chats/" + chatName + ".txt").getAbsolutePath();                                               
        chatFile = new File(chatFilePath);
        
        // Used to add rows to tabel
        DefaultTableModel tabelModel = (DefaultTableModel) tabelMessages.getModel();
        
        int lineCount = 0;
        int rowNum = 0;
        int height, newHeight, expansion, length;
        
        try{
            // Checks if file exists
            if(!(chatFile.exists())){
                // Creates file if one isn't present
                chatFile.createNewFile();
            }else{
            
                // Outputs all local stored messages
                FileReader fileIn = new FileReader(chatFilePath); 
                BufferedReader bufferIn = new BufferedReader(fileIn);
                String line = null;
                
                // Outputs locally stored messages
                while ((line = bufferIn.readLine()) != null) {

                    // Seperates username from message
                    List<String> lineBreakdown = Arrays.asList(line.split("§"));
                    // Stores message length
                    length = lineBreakdown.get(1).length();
                    // Adds html tags so text wraps
                    line = "<html>" + lineBreakdown.get(1) + "</html>";

                    // Adds username and message to tabel
                    Object[] row = {lineBreakdown.get(0), line};
                    tabelModel.addRow(row);

                    // Calculates requires row height to display message
                    height = tabelMessages.getRowHeight(rowNum);
                    newHeight = tabelMessages.prepareRenderer(tabelMessages.getCellRenderer(rowNum, 1), rowNum, 1).getPreferredSize().height;
                    expansion = (length/50);
                    if(expansion > 1){
                        newHeight = newHeight * expansion;
                    }

                    // Alters row height
                    tabelMessages.setRowHeight(rowNum, newHeight);

                    rowNum += 1;
                    lineCount += 1;
                    
                    
                }
                bufferIn.close();
                fileIn.close();
            }
            
            //Sends chatname and amount of lines saved on the client file to the server
            outToMsgServer.writeUTF(chatName);
            outToMsgServer.writeUTF(Integer.toString(lineCount));
            
            // Loop to catch all incoming messages 
            String recieveLine = null;
            
            while(cont){
                
                // Gets new message from server
                recieveLine = inFromMsgServer.readUTF();
                
                // Seperates username from message
                List<String> lineBreakdown = Arrays.asList(recieveLine.split("§"));
                // Stores message length
                length = lineBreakdown.get(2).length();
                // Adds html tags so text wraps
                recieveLine = "<html>" + lineBreakdown.get(2) + "</html>";
                
                // Outputs line to GUI
                Object[] rowData = {lineBreakdown.get(1), recieveLine};
                tabelModel.addRow(rowData);
                
                // Calculates requires row height to display message
                height = tabelMessages.getRowHeight(rowNum);
                newHeight = tabelMessages.prepareRenderer(tabelMessages.getCellRenderer(rowNum, 1), rowNum, 1).getPreferredSize().height;
                expansion = (length/50);
                if(expansion > 1){
                    newHeight = newHeight * expansion;
                }

                // Alters row height
                tabelMessages.setRowHeight(rowNum, newHeight);
                
                //Writes new line to local text file
                FileWriter fileOut = new FileWriter(chatFile.getAbsolutePath(), true);
                BufferedWriter bufferOut = new BufferedWriter(fileOut); 
                bufferOut.write(lineBreakdown.get(1) + "§" + lineBreakdown.get(2));
                bufferOut.newLine();
                bufferOut.close();
                fileOut.close();
                
                rowNum += 1;
                
                if(lineBreakdown.get(0) == "F"){
                    
                }
                
            }
            
            
        }catch(IOException e){
            // Prints error message
            System.out.println(e.getMessage());
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        spMessages = new javax.swing.JScrollPane();
        tabelMessages = new javax.swing.JTable();
        lblUsername = new javax.swing.JLabel();
        lblTopLine = new javax.swing.JLabel();
        lblBottomLine = new javax.swing.JLabel();
        spNewMessage = new javax.swing.JScrollPane();
        taNewMessage = new javax.swing.JTextArea();
        cmdSend = new javax.swing.JButton();
        cmdAttach = new javax.swing.JButton();
        cmdClear = new javax.swing.JButton();
        lblProfilePicture = new javax.swing.JLabel();
        taFilePath = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("Futura", 1, 48)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(93, 253, 203));
        lblTitle.setText("CHAT WINDOW ");

        spMessages.setBackground(new java.awt.Color(46, 47, 47));

        tabelMessages.setBackground(new java.awt.Color(46, 47, 47));
        tabelMessages.setFont(new java.awt.Font("Futura", 0, 14)); // NOI18N
        tabelMessages.setForeground(new java.awt.Color(253, 255, 252));
        tabelMessages.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabelMessages.setTableHeader(null);
        spMessages.setViewportView(tabelMessages);

        lblUsername.setFont(new java.awt.Font("Futura", 0, 36)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(253, 255, 252));

        taNewMessage.setColumns(20);
        taNewMessage.setFont(new java.awt.Font("Futura", 0, 14)); // NOI18N
        taNewMessage.setRows(5);
        taNewMessage.setWrapStyleWord(true);
        spNewMessage.setViewportView(taNewMessage);

        cmdSend.setFont(new java.awt.Font("Futura", 1, 22)); // NOI18N
        cmdSend.setText("SEND");
        cmdSend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdSendMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdSendMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdSendMouseEntered(evt);
            }
        });

        cmdAttach.setFont(new java.awt.Font("Futura", 1, 14)); // NOI18N
        cmdAttach.setText("ATTACH FILE");
        cmdAttach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdAttachMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdAttachMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdAttachMouseEntered(evt);
            }
        });

        cmdClear.setFont(new java.awt.Font("Futura", 1, 14)); // NOI18N
        cmdClear.setText("CLEAR");
        cmdClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdClearMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdClearMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdClearMouseEntered(evt);
            }
        });

        taFilePath.setColumns(20);
        taFilePath.setFont(new java.awt.Font("Futura", 0, 12)); // NOI18N
        taFilePath.setForeground(new java.awt.Color(253, 255, 252));
        taFilePath.setRows(5);
        taFilePath.setText("Attached File:");
        taFilePath.setWrapStyleWord(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTopLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBottomLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(spMessages, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(151, 151, 151)
                                .addComponent(lblProfilePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)))
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spNewMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                    .addComponent(taFilePath))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdAttach, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdSend, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmdClear, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblProfilePicture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTopLine, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(spMessages, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblBottomLine)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmdSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdAttach, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdClear, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(spNewMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(taFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdSendMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdSendMouseEntered
        // Changes colour of button when hovered over
        cmdSend.setContentAreaFilled(true);
        cmdSend.setBackground(foreground);
        cmdSend.setForeground(highlight);
    }//GEN-LAST:event_cmdSendMouseEntered

    private void cmdSendMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdSendMouseExited
        // Changes colour of button when mouse isn't hovering
        cmdSend.setContentAreaFilled(false);
        cmdSend.setForeground(foreground);
    }//GEN-LAST:event_cmdSendMouseExited

    private void cmdAttachMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdAttachMouseEntered
        // Changes colour of button when hovered over
        cmdAttach.setContentAreaFilled(true);
        cmdAttach.setBackground(foreground);
        cmdAttach.setForeground(highlight);
    }//GEN-LAST:event_cmdAttachMouseEntered

    private void cmdAttachMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdAttachMouseExited
        // Changes colour of button when mouse isn't hovering
        cmdAttach.setContentAreaFilled(false);
        cmdAttach.setForeground(foreground);
    }//GEN-LAST:event_cmdAttachMouseExited

    private void cmdClearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdClearMouseEntered
        // Changes colour of button when hovered over
        cmdClear.setContentAreaFilled(true);
        cmdClear.setBackground(foreground);
        cmdClear.setForeground(warning);
    }//GEN-LAST:event_cmdClearMouseEntered

    private void cmdClearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdClearMouseExited
        // Changes colour of button when mouse isn't hovering
        cmdClear.setContentAreaFilled(false);
        cmdClear.setForeground(foreground);
    }//GEN-LAST:event_cmdClearMouseExited

    private void cmdClearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdClearMouseClicked
        // Clears new message and attachments 
        taNewMessage.setText("");
        taFilePath.setText("Attached File: ");
        fileAttached = false;
        filePath = "";
    }//GEN-LAST:event_cmdClearMouseClicked

    private void cmdAttachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdAttachMouseClicked
        // Attaches file to send
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        filePath = f.getAbsolutePath();
        taFilePath.setText("Attached File: " + filePath);
        fileAttached = true;
    }//GEN-LAST:event_cmdAttachMouseClicked

    private void cmdSendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdSendMouseClicked
        
        try{
            
            // Checks if text message has been written to be sent
            if (!(taNewMessage.getText().equals(""))){
                String message = "T§" + username + "§" + taNewMessage.getText();
                outToMsgServer.writeUTF(message);
            }
            
            
            // Checks if attachment needs to be sent
            if(fileAttached == true){
                
                String message = "F§" + username + "§File sent$" + chatName + chatFile.lastModified();
                outToMsgServer.writeUTF(message);
                
                //outToMsgServer.close();
                
                //InfoPacket sendAttachedFile = new InfoPacket();
                //fileOutToMsgServer = new ObjectOutputStream(msgServer.getOutputStream());
                
                /*
                FileInputStream attachedFile = new FileInputStream(filePath);
                byte [] fileByteArray = new byte[attachedFile.available()];
                outToMsgServer.writeUTF(Integer.toString(fileByteArray.length));
                int count;
                while ((count = in.read(fileByteArray)) > 0)
                {
                    outToMsgServer.write(fileByteArray, 0, count);
                }
                */
                //outToMsgServer.writeUTF(Integer.toString(fileByteArray.length));
                //DatagramSocket client = new DatagramSocket();
                //DatagramPacket sendPacket = new DatagramPacket(fileByteArray, fileByteArray.length, address, 9092);
                //client.send(sendPacket);
                //client.close();
                
                //attachedFile.read(buffer);
                //sendAttachedFile.SetFirstByte(buffer);
                //fileOutToMsgServer.writeObject(sendAttachedFile);
                
                //fileOutToMsgServer.close();
                //outToMsgServer = new DataOutputStream(msgServer.getOutputStream());
                
            }
            
            
            
        }catch(IOException e){
            // Prints error message
            System.out.println(e.getMessage());
        }
        
        taNewMessage.setText("");
        taFilePath.setText("Attached File: ");
        fileAttached = false;
        filePath = "";
    }//GEN-LAST:event_cmdSendMouseClicked

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(ChatWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAttach;
    private javax.swing.JButton cmdClear;
    private javax.swing.JButton cmdSend;
    private javax.swing.JLabel lblBottomLine;
    private javax.swing.JLabel lblProfilePicture;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTopLine;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JScrollPane spMessages;
    private javax.swing.JScrollPane spNewMessage;
    private javax.swing.JTextArea taFilePath;
    private javax.swing.JTextArea taNewMessage;
    private javax.swing.JTable tabelMessages;
    // End of variables declaration//GEN-END:variables
    
    // Function to scale image
    public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lblProfilePicture.getWidth(), lblProfilePicture.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
}
