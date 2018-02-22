/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingcoursework;

import java.awt.Color;
import java.sql.ResultSet;
import javax.swing.ImageIcon;

/**
 *
 * @author samal
 */
public class RegisterUser extends javax.swing.JFrame {

    /**
     * Creates new form RegisterUser
     */
    public RegisterUser() {
        initComponents();
        this.setTitle("Register User");
        this.getContentPane().setBackground(Color.decode("#2B2D42"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        lblMusicChoice1 = new javax.swing.JLabel();
        lblRegisterNewUser = new javax.swing.JLabel();
        lblFirstName = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        lblSecondName = new javax.swing.JLabel();
        txtSecondName = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblUserName = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        lblPassword2 = new javax.swing.JLabel();
        txtPassword2 = new javax.swing.JTextField();
        lblMusicChoice = new javax.swing.JLabel();
        rbFolk = new javax.swing.JRadioButton();
        rbRock = new javax.swing.JRadioButton();
        rbTechno = new javax.swing.JRadioButton();
        rbSoul = new javax.swing.JRadioButton();
        rbPop = new javax.swing.JRadioButton();
        rbBlues = new javax.swing.JRadioButton();
        rbJazz = new javax.swing.JRadioButton();
        rbReggae = new javax.swing.JRadioButton();
        rbEDM = new javax.swing.JRadioButton();
        rbRapping = new javax.swing.JRadioButton();
        rbIndie = new javax.swing.JRadioButton();
        rbClassical = new javax.swing.JRadioButton();
        lblUploadPicture = new javax.swing.JLabel();
        cmdPhoto = new javax.swing.JButton();
        cmdCreate = new javax.swing.JButton();
        cmdLogIn = new javax.swing.JButton();
        lblPhoto = new javax.swing.JLabel();
        cmdTestChat = new javax.swing.JButton();

        lblMusicChoice1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMusicChoice1.setText("Select Music Interests:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(43, 45, 66));

        lblRegisterNewUser.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblRegisterNewUser.setText("Register New User");

        lblFirstName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblFirstName.setText("First Name:");

        lblSecondName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSecondName.setText("Second Name:");

        lblEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblEmail.setText("Email Address:");

        lblUserName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblUserName.setText("Username:");

        lblPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPassword.setText("Password:");

        lblPassword2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPassword2.setText("Confirm Password:");

        lblMusicChoice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMusicChoice.setText("Select Music Interests:");

        rbFolk.setText("Folk");

        rbRock.setText("Rock");

        rbTechno.setText("Techno");

        rbSoul.setText("Soul");

        rbPop.setText("Pop");

        rbBlues.setText("Blues");
        rbBlues.setToolTipText("");

        rbJazz.setText("Jazz");

        rbReggae.setText("Reggae");

        rbEDM.setText("EDM");

        rbRapping.setText("Rapping");

        rbIndie.setText("Indie");

        rbClassical.setText("Classical");

        lblUploadPicture.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblUploadPicture.setText("Upload Profile Picture");

        cmdPhoto.setText("Upload Photo");
        cmdPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPhotoActionPerformed(evt);
            }
        });

        cmdCreate.setText("Create New Profile");
        cmdCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCreateActionPerformed(evt);
            }
        });

        cmdLogIn.setText("Back To Log In Screen");
        cmdLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLogInActionPerformed(evt);
            }
        });

        cmdTestChat.setText("Test Chat");
        cmdTestChat.setToolTipText("");
        cmdTestChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTestChatActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUserName)
                            .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPassword2)
                            .addComponent(lblPassword))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                                .addComponent(lblRegisterNewUser)
                                .addGap(63, 63, 63)
                                .addComponent(cmdTestChat))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFirstName)
                                    .addComponent(lblSecondName)
                                    .addComponent(txtSecondName, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEmail)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(75, 75, 75)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMusicChoice)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(rbFolk)
                                                    .addComponent(rbPop)
                                                    .addComponent(rbEDM))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(rbRock)
                                                    .addComponent(rbBlues)
                                                    .addComponent(rbRapping)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(rbSoul)
                                                .addGap(18, 18, 18)
                                                .addComponent(rbReggae)))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rbClassical)
                                            .addComponent(rbJazz)
                                            .addComponent(rbTechno)
                                            .addComponent(rbIndie)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(cmdCreate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cmdLogIn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblUploadPicture, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmdPhoto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRegisterNewUser)
                    .addComponent(cmdTestChat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFirstName)
                    .addComponent(lblMusicChoice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbFolk)
                    .addComponent(rbRock)
                    .addComponent(rbTechno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSecondName)
                    .addComponent(rbPop)
                    .addComponent(rbBlues)
                    .addComponent(rbJazz))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSecondName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbEDM)
                    .addComponent(rbRapping)
                    .addComponent(rbIndie))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(rbSoul)
                    .addComponent(rbReggae)
                    .addComponent(rbClassical))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblUserName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdCreate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPassword2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdLogIn)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblUploadPicture)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmdPhoto)
                            .addComponent(lblPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblMusicChoice.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLogInActionPerformed
        new LogInScreen().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cmdLogInActionPerformed

    private void cmdPhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPhotoActionPerformed
        new UploadProfilePicture().setVisible(true);
        
    }//GEN-LAST:event_cmdPhotoActionPerformed

    public void SetPhoto(ImageIcon Image)
    {
        lblPhoto.setIcon(Image);
    }
    
    private void cmdCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCreateActionPerformed
        Boolean isValid;
        isValid = ValidChecks();
        
        SqLite test = new SqLite();
        ResultSet set;
        
//        try (
//            set = test.displayUsers();
//            while(set.next()) {
//                lblFirstName.setText(set.getString("fname"));
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//        }
        if (isValid == false)
        {
            //Output Error message/
        }
        
        String Password = txtPassword.getText();
        String PasswordConfirm = txtPassword2.getText();
        //If password is entered correctly twice
        if (Password == PasswordConfirm)
        {
            //Write a function that determines wheether the txt box is empty
            //Check if valid email
            //Check no fields are empty
            
            //Create new user profile.
        }
        
    }//GEN-LAST:event_cmdCreateActionPerformed

    private void cmdTestChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdTestChatActionPerformed
        String UserName = lblUserName.getText();
        String UserName2 = lblSecondName.getText();
        
        new Chatwindow().setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_cmdTestChatActionPerformed

    private Boolean ValidChecks() {
        
        if (txtFirstName.getText().length() < 2 || txtSecondName.getText().length() < 2)
        {
            return false;
        }
        else if(txtEmail.getText().length() < 5 || txtUserName.getText().length() < 5)
        {
            return false;
        }
        else if (txtPassword.getText().length() < 3)
        {
            return false;
        }
        //if email does not contain a "@" or "."
        else if (txtEmail.getText().contains("@") == false || txtEmail.getText().contains(".") == false)
        {
            return false;
        }
        return false;
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
            java.util.logging.Logger.getLogger(RegisterUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JButton cmdCreate;
    private javax.swing.JButton cmdLogIn;
    private javax.swing.JButton cmdPhoto;
    private javax.swing.JButton cmdTestChat;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblMusicChoice;
    private javax.swing.JLabel lblMusicChoice1;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPassword2;
    private javax.swing.JLabel lblPhoto;
    private javax.swing.JLabel lblRegisterNewUser;
    private javax.swing.JLabel lblSecondName;
    private javax.swing.JLabel lblUploadPicture;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JRadioButton rbBlues;
    private javax.swing.JRadioButton rbClassical;
    private javax.swing.JRadioButton rbEDM;
    private javax.swing.JRadioButton rbFolk;
    private javax.swing.JRadioButton rbIndie;
    private javax.swing.JRadioButton rbJazz;
    private javax.swing.JRadioButton rbPop;
    private javax.swing.JRadioButton rbRapping;
    private javax.swing.JRadioButton rbReggae;
    private javax.swing.JRadioButton rbRock;
    private javax.swing.JRadioButton rbSoul;
    private javax.swing.JRadioButton rbTechno;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPassword2;
    private javax.swing.JTextField txtSecondName;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}