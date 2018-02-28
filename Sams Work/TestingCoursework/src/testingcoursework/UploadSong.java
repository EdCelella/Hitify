/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingcoursework;

import java.io.File;
import javax.swing.JFileChooser;
import infopacket.InfoPacket;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author samal
 */
public class UploadSong extends javax.swing.JFrame {

    private String Filepath;
    private String UserName;
    /**
     * Creates new form UploadSong
     */
    public UploadSong() {
        initComponents();
    }
    
    public UploadSong(String Username) {
        
        this.UserName = Username;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblArtistName = new javax.swing.JLabel();
        lblSongName = new javax.swing.JLabel();
        txtArtistName = new javax.swing.JTextField();
        txtSongName = new javax.swing.JTextField();
        cmdSelectFile = new javax.swing.JButton();
        lblChosen = new javax.swing.JLabel();
        cmdUploadSong = new javax.swing.JButton();
        lblSongName1 = new javax.swing.JLabel();
        cbMood = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTitle.setText("Upload A Song");

        lblArtistName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblArtistName.setText("Artist Name");

        lblSongName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSongName.setText("Song Name");

        cmdSelectFile.setText("Select Music File");
        cmdSelectFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSelectFileActionPerformed(evt);
            }
        });

        lblChosen.setText("You chose:");

        cmdUploadSong.setText("Upload Song");
        cmdUploadSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUploadSongActionPerformed(evt);
            }
        });

        lblSongName1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSongName1.setText("Mood");

        cbMood.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Mood", "Happy", "Sad", "Peaceful", "Loving", "Amused", "Gloomy", "Stressed" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbMood, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdUploadSong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblChosen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdSelectFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtArtistName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSongName))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblArtistName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblSongName, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(117, 117, 117))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblSongName1)
                        .addGap(140, 140, 140))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addComponent(lblTitle)
                .addGap(66, 66, 66))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblArtistName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtArtistName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSongName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSongName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSongName1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbMood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(cmdSelectFile)
                .addGap(18, 18, 18)
                .addComponent(lblChosen, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdUploadSong)
                .addGap(13, 13, 13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdSelectFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSelectFileActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        Filepath = f.getAbsolutePath();
        lblChosen.setText("You chose: " + Filepath);
        
    }//GEN-LAST:event_cmdSelectFileActionPerformed

    private void cmdUploadSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUploadSongActionPerformed
        InfoPacket UserSongDetails = new InfoPacket();
        ArrayList UserSongArray = new ArrayList();
        String UserMood = cbMood.getSelectedItem().toString();
        System.out.println(UserMood);
        UserSongArray.add(UserName);
        //Date and time added by default SQL Table
        UserSongArray.add("SongUpload");
        UserSongArray.add(txtArtistName.getText());
        UserSongArray.add(txtSongName.getText());
        UserSongArray.add(cbMood.getSelectedItem().toString());
        
        //Convert song file to byte
        FileInputStream SongFile;
        try {
            SongFile = new FileInputStream(Filepath);
            byte [] buffer = new byte[SongFile.available()];
            SongFile.read(buffer);
            
            //UNS - Upload New Song
            UserSongDetails.CreateArrayBytePacket("UNS", UserSongArray, buffer);
            
            Socket MainServer = new Socket("localhost", 9090);
                    
            ObjectOutputStream OutToServer = new ObjectOutputStream(MainServer.getOutputStream());
            
            OutToServer.writeObject(UserSongDetails);

            OutToServer.close();

            MainServer.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Error - " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error - " + ex.getMessage());
        }
        
        
        
        
        
    }//GEN-LAST:event_cmdUploadSongActionPerformed

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
            java.util.logging.Logger.getLogger(UploadSong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UploadSong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UploadSong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UploadSong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UploadSong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbMood;
    private javax.swing.JButton cmdSelectFile;
    private javax.swing.JButton cmdUploadSong;
    private javax.swing.JLabel lblArtistName;
    private javax.swing.JLabel lblChosen;
    private javax.swing.JLabel lblSongName;
    private javax.swing.JLabel lblSongName1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField txtArtistName;
    private javax.swing.JTextField txtSongName;
    // End of variables declaration//GEN-END:variables
}
