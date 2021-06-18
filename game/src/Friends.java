/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author rafael
 */
public class Friends extends javax.swing.JFrame {
    ButtonGroup friendActive = new ButtonGroup();
    Game game;
    private ArrayList<JButton> friends = new ArrayList<JButton>();
    private ArrayList<String> friendsInfo = new ArrayList<String>();
    private int remaining;


    /**
     * Creates new form Friends
     */
    public Friends(Game game) throws RemoteException {
        this.game = game;
        this.createAllButtons();
        System.out.println("[ADDRESS] Friends");
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        LabelLogin = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        Friend1Choose = new javax.swing.JButton();
        quit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(25, 25, 25));

        LabelLogin.setBackground(new java.awt.Color(0, 0, 0));
        LabelLogin.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        LabelLogin.setForeground(new java.awt.Color(255, 10, 10));
        LabelLogin.setText("Tic-Tac-Toe");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(121, Short.MAX_VALUE)
                                .addComponent(LabelLogin)
                                .addGap(110, 110, 110))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(16, Short.MAX_VALUE)
                                .addComponent(LabelLogin)
                                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(43, 43, 43));

        jPanel13.setBackground(new java.awt.Color(25, 25, 25));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 43, Short.MAX_VALUE)
        );
        int i=0;
        for( JButton button: this.friends){
            button.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
            button.setText(this.friendsInfo.get(i).split(" ")[0]);
            //System.out.println(this.friendsInfo.get(i));
            int finalI = i;
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    try {
                        Friend1ChooseMouseClicked(evt, finalI);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
            button.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Friend1ChooseActionPerformed(evt);
                }
            });

            i++;
        }

        quit.setBackground(new java.awt.Color(255, 10, 10));
        quit.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        quit.setForeground(new java.awt.Color(0, 0, 0));
        quit.setText("QUIT");
        quit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quitMouseClicked(evt);
            }
        });
        quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(5, 90, 255));
        jLabel1.setText("Available Friends");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);

        jPanel12Layout.setHorizontalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(127, 127, 127))
                        .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(this.friends.get(0), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(this.friends.get(1), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(this.friends.get(2), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(this.friends.get(3), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(this.friends.get(4), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(this.friends.get(5), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(quit, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE))
                                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(this.friends.get(0), javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(this.friends.get(1), javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(this.friends.get(2), javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(this.friends.get(3), javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(this.friends.get(4), javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(this.friends.get(5), javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addComponent(quit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>

    // method to make all the buttons
    private void createAllButtons() throws RuntimeException, RemoteException {
        String[] users = this.game.getAllActiveUser();
        String userInfo[] = {"", ""};
        for (String user: users){
            userInfo = user.split(" ");
            Friend1Choose = new JButton();
            this.friends.add(Friend1Choose);
            this.friendsInfo.add(userInfo[1]+" "+userInfo[0]);
        }
        this.remaining = 6-this.friends.toArray().length;
        for (int i=0; i<this.remaining; i++){
            Friend1Choose = new JButton();
            this.friends.add(Friend1Choose);
            this.friendsInfo.add("");
        }

    }

    private void Friend1ChooseMouseClicked(java.awt.event.MouseEvent evt, int i) throws RemoteException {
        if (i < 6-this.remaining) {
            // TODO add your handling code here:
            String[] enemyInfo = {this.friendsInfo.get(i).split(" ")[1],this.friendsInfo.get(i).split(" ")[0]};
            System.out.println("User " + enemyInfo[0]);
            this.game.setEnimyInfo(enemyInfo);
            this.game.sendMatchRequest();
            WaitingOpponnent wot = new WaitingOpponnent(this.game);
            wot.setVisible(true);
            wot.pack();
            wot.setLocationRelativeTo(null);
            wot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.dispose();
            //wot.waitOpponent();
        }
    }

    private void Friend1ChooseActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void quitMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void quitActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        PlayGame qg = new PlayGame(this.game);
        qg.setVisible(true);
        qg.pack();
        qg.setLocationRelativeTo(null);
        qg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }

    public  void playWhitFriend(Game game) {
        this.game = game;
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
            java.util.logging.Logger.getLogger(Friends.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Friends.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Friends.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Friends.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Friends(game).setVisible(true);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton CardCross;
    private javax.swing.JButton CardCross1;
    private javax.swing.JButton CardCross2;
    private javax.swing.JButton Friend1Choose;
    private javax.swing.JLabel LabelLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton quit;
    // End of variables declaration
}
