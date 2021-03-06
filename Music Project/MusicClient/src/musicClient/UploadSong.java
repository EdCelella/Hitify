/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicClient;

import java.io.File;
import javax.swing.JFileChooser;
import infopacket.InfoPacket;
import java.awt.Color;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;



/**
 *
 * @author samal
 */
public class UploadSong extends javax.swing.JFrame {

    private String MusicFilePath;
    private String PhotoFilePath;
    private String UserName;
    
    Color foreground = Color.decode("#FDFFFC");
    Color background = Color.decode("#2E2F2F");
    Color highlight = Color.decode("#5DFDCB");
    Color warning = Color.decode("#FF3366");
    /**
     * Creates new form UploadSong
     */
    public UploadSong() {
        initComponents();
    }
    
    public UploadSong(String Username) {
        
        this.UserName = Username;
        initComponents();
        
        int buttonBorder = 4;
        
        this.getContentPane().setBackground(background);
        
        cmdSelectFile.setContentAreaFilled(false);
        cmdSelectFile.setBackground(background);
        cmdSelectFile.setBorder(new LineBorder(foreground, buttonBorder));
	cmdSelectFile.setForeground(foreground);
        
        cmdUploadCoverPhoto.setContentAreaFilled(false);
        cmdUploadCoverPhoto.setBackground(background);
        cmdUploadCoverPhoto.setBorder(new LineBorder(foreground, buttonBorder));
	cmdUploadCoverPhoto.setForeground(foreground);
        
        cmdHome.setContentAreaFilled(false);
        cmdHome.setBackground(background);
        cmdHome.setBorder(new LineBorder(foreground, buttonBorder));
	cmdHome.setForeground(foreground);
        
        cmdClear.setContentAreaFilled(false);
        cmdClear.setBackground(background);
        cmdClear.setBorder(new LineBorder(foreground, buttonBorder));
	cmdClear.setForeground(foreground);
        
        cmdUploadSong.setContentAreaFilled(false);
        cmdUploadSong.setBackground(background);
        cmdUploadSong.setBorder(new LineBorder(foreground, buttonBorder));
	cmdUploadSong.setForeground(foreground);
        
        txtArtistName.setBorder(new LineBorder(foreground, 4));
        txtSongName.setBorder(new LineBorder(foreground, 4));
        
        cbGenre.setBackground(foreground);
        cbGenre.setUI(new CustomComboBoxUI());
        
        
        
        
        lblCoverPhoto.setBorder(new LineBorder(foreground, 2));
        
        taFilePath.setBackground(background);
        taFilePath.setLineWrap(true);
        taFilePath.setBorder(null);
        taFilePath.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblPhoto = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblArtistName = new javax.swing.JLabel();
        lblSongName = new javax.swing.JLabel();
        txtArtistName = new javax.swing.JTextField();
        txtSongName = new javax.swing.JTextField();
        cmdSelectFile = new javax.swing.JButton();
        lblChosen = new javax.swing.JLabel();
        cmdUploadSong = new javax.swing.JButton();
        lblSongName1 = new javax.swing.JLabel();
        cbGenre = new javax.swing.JComboBox<>();
        lblCoverPhoto = new javax.swing.JLabel();
        cmdUploadCoverPhoto = new javax.swing.JButton();
        cmdClear = new javax.swing.JButton();
        cmdHome = new javax.swing.JButton();
        taFilePath = new javax.swing.JTextArea();

        lblPhoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("Futura", 1, 48)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(93, 253, 203));
        lblTitle.setText("UPLOAD SONG");

        lblArtistName.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        lblArtistName.setForeground(new java.awt.Color(253, 255, 252));
        lblArtistName.setText("Artist Name:");

        lblSongName.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        lblSongName.setForeground(new java.awt.Color(253, 255, 252));
        lblSongName.setText("Song Name:");

        txtArtistName.setFont(new java.awt.Font("Futura", 0, 13)); // NOI18N

        txtSongName.setFont(new java.awt.Font("Futura", 0, 13)); // NOI18N

        cmdSelectFile.setFont(new java.awt.Font("Futura", 1, 18)); // NOI18N
        cmdSelectFile.setText("SELECT MP3 FILE");
        cmdSelectFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdSelectFileMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdSelectFileMouseEntered(evt);
            }
        });
        cmdSelectFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSelectFileActionPerformed(evt);
            }
        });

        lblChosen.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        lblChosen.setForeground(new java.awt.Color(253, 255, 252));
        lblChosen.setText("Selected File:");

        cmdUploadSong.setFont(new java.awt.Font("Futura", 1, 24)); // NOI18N
        cmdUploadSong.setText("UPLOAD");
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

        lblSongName1.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        lblSongName1.setForeground(new java.awt.Color(253, 255, 252));
        lblSongName1.setText("Genre:");

        cbGenre.setFont(new java.awt.Font("Futura", 0, 13)); // NOI18N
        cbGenre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Genre", "Folk", "Rock", "Techno", "Pop", "Blues", "Jazz", "EDM", "Rapping", "Indie", "Sould", "Reggae", "Classical" }));

        lblCoverPhoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cmdUploadCoverPhoto.setFont(new java.awt.Font("Futura", 1, 18)); // NOI18N
        cmdUploadCoverPhoto.setText("SELECT PHOTO");
        cmdUploadCoverPhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdUploadCoverPhotoMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdUploadCoverPhotoMouseEntered(evt);
            }
        });
        cmdUploadCoverPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUploadCoverPhotoActionPerformed(evt);
            }
        });

        cmdClear.setFont(new java.awt.Font("Futura", 1, 24)); // NOI18N
        cmdClear.setText("CLEAR");
        cmdClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdClearMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdClearMouseEntered(evt);
            }
        });
        cmdClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdClearActionPerformed(evt);
            }
        });

        cmdHome.setFont(new java.awt.Font("Futura", 1, 24)); // NOI18N
        cmdHome.setText("HOME");
        cmdHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdHomeMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdHomeMouseEntered(evt);
            }
        });
        cmdHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdHomeActionPerformed(evt);
            }
        });

        taFilePath.setBackground(new java.awt.Color(253, 255, 252));
        taFilePath.setColumns(20);
        taFilePath.setFont(new java.awt.Font("Futura", 0, 13)); // NOI18N
        taFilePath.setForeground(new java.awt.Color(253, 255, 252));
        taFilePath.setRows(5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmdHome, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmdClear, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbGenre, javax.swing.GroupLayout.Alignment.TRAILING, 0, 382, Short.MAX_VALUE)
                                .addComponent(txtArtistName, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtSongName, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdUploadSong, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(cmdSelectFile, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 333, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(184, 184, 184)
                                    .addComponent(lblChosen, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblSongName1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblArtistName, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSongName))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCoverPhoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdUploadCoverPhoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 32, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblTitle)
                .addGap(15, 15, 15))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(223, Short.MAX_VALUE)
                    .addComponent(taFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(298, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblTitle)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtArtistName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblArtistName))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSongName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSongName))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSongName1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmdSelectFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblChosen)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCoverPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(cmdUploadCoverPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmdUploadSong, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdClear, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8))
                    .addComponent(cmdHome, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(310, Short.MAX_VALUE)
                    .addComponent(taFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(165, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdSelectFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSelectFileActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        MusicFilePath = f.getAbsolutePath();
        taFilePath.setText(MusicFilePath);
    }//GEN-LAST:event_cmdSelectFileActionPerformed

    private void cmdUploadSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUploadSongActionPerformed
        InfoPacket UserSongDetails = new InfoPacket();
        ArrayList UserSongArray = new ArrayList();
        UserSongArray.add(UserName);
        //Date and time added by default SQL Table
        UserSongArray.add("SongUpload");
        UserSongArray.add(txtArtistName.getText());
        UserSongArray.add(txtSongName.getText());
        UserSongArray.add(cbGenre.getSelectedItem().toString());
        
        //Convert song file to byte
        FileInputStream SongFile;
        FileInputStream CoverPhotoFile;
        try {
            SongFile = new FileInputStream(MusicFilePath);
            byte [] buffer = new byte[SongFile.available()];
            SongFile.read(buffer);
            
            CoverPhotoFile = new FileInputStream(PhotoFilePath);
            byte [] buffer2 = new byte[CoverPhotoFile.available()];
            CoverPhotoFile.read(buffer2);
            
            //UNS - Upload New Song
            UserSongDetails.SetService("UNS");
            UserSongDetails.SetArray(UserSongArray);
            UserSongDetails.SetFirstByte(buffer);
            UserSongDetails.SetSecondByte(buffer2);
            
            Socket MainServer = new Socket("localhost", 9090);
                    
            ObjectOutputStream OutToServer = new ObjectOutputStream(MainServer.getOutputStream());
            
            OutToServer.writeObject(UserSongDetails);

            OutToServer.close();

            MainServer.close();
            ClearForm();
        } catch (FileNotFoundException ex) {
            System.out.println("Error - " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error - " + ex.getMessage());
        }
        
    }//GEN-LAST:event_cmdUploadSongActionPerformed

    private void cmdUploadCoverPhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUploadCoverPhotoActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        PhotoFilePath = f.getAbsolutePath();
        lblCoverPhoto.setIcon(ResizeImage(PhotoFilePath));
    }//GEN-LAST:event_cmdUploadCoverPhotoActionPerformed

    private void cmdClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdClearActionPerformed
        ClearForm();
        lblChosen.setText("Selected File: ");
        taFilePath.setText("");
    }//GEN-LAST:event_cmdClearActionPerformed

    private void cmdHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHomeActionPerformed
        try {
            new MainScreen(UserName).setVisible(true);
            this.dispose();    
        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        
        
    }//GEN-LAST:event_cmdHomeActionPerformed

    private void cmdSelectFileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdSelectFileMouseEntered
        // Changes colour of button when hovered over
        cmdSelectFile.setContentAreaFilled(true);
        cmdSelectFile.setBackground(foreground);
        cmdSelectFile.setForeground(highlight);
    }//GEN-LAST:event_cmdSelectFileMouseEntered

    private void cmdSelectFileMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdSelectFileMouseExited
        // Changes colour of button when mouse isn't hovering
        cmdSelectFile.setContentAreaFilled(false);
        cmdSelectFile.setForeground(foreground);
    }//GEN-LAST:event_cmdSelectFileMouseExited

    private void cmdUploadCoverPhotoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdUploadCoverPhotoMouseEntered
        // Changes colour of button when hovered over
        cmdUploadCoverPhoto.setContentAreaFilled(true);
        cmdUploadCoverPhoto.setBackground(foreground);
        cmdUploadCoverPhoto.setForeground(highlight);
    }//GEN-LAST:event_cmdUploadCoverPhotoMouseEntered

    private void cmdUploadCoverPhotoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdUploadCoverPhotoMouseExited
        // Changes colour of button when mouse isn't hovering
        cmdUploadCoverPhoto.setContentAreaFilled(false);
        cmdUploadCoverPhoto.setForeground(foreground);
    }//GEN-LAST:event_cmdUploadCoverPhotoMouseExited

    private void cmdHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdHomeMouseEntered
        // Changes colour of button when hovered over
        cmdHome.setContentAreaFilled(true);
        cmdHome.setBackground(foreground);
        cmdHome.setForeground(highlight);
    }//GEN-LAST:event_cmdHomeMouseEntered

    private void cmdHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdHomeMouseExited
        // Changes colour of button when mouse isn't hovering
        cmdHome.setContentAreaFilled(false);
        cmdHome.setForeground(foreground);
    }//GEN-LAST:event_cmdHomeMouseExited

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

    public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lblCoverPhoto.getWidth(), lblCoverPhoto.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    
    /**
     * @param args the command line arguments
     */
    
    public void ClearForm()
    {
        txtArtistName.setText("");
        txtSongName.setText("");
        cbGenre.setSelectedIndex(0);
        lblCoverPhoto.setIcon(ResizeImage(""));
        lblChosen.setText("");
    }
    
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
    private javax.swing.JComboBox<String> cbGenre;
    private javax.swing.JButton cmdClear;
    private javax.swing.JButton cmdHome;
    private javax.swing.JButton cmdSelectFile;
    private javax.swing.JButton cmdUploadCoverPhoto;
    private javax.swing.JButton cmdUploadSong;
    private javax.swing.JLabel lblArtistName;
    private javax.swing.JLabel lblChosen;
    private javax.swing.JLabel lblCoverPhoto;
    private javax.swing.JLabel lblPhoto;
    private javax.swing.JLabel lblSongName;
    private javax.swing.JLabel lblSongName1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextArea taFilePath;
    private javax.swing.JTextField txtArtistName;
    private javax.swing.JTextField txtSongName;
    // End of variables declaration//GEN-END:variables
}
