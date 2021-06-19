/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import java.rmi.RemoteException;

/**
 *
 * @author rafael
 */
public class Match extends javax.swing.JFrame {
    private Game game;
    private int winner;
    private boolean isMe;
    private boolean[] buttonPressed = {false, false, false, false, false, false, false, false, false};
    private String card;


    public class WaitPlay extends Thread {
        Game game;
        int play;
        public WaitPlay(Game game){
            this.game = game;
        }
        public void runWait() throws RemoteException, InterruptedException {
            do{
                this.play = this.game.getOpponentPlay();
            }while(this.play < 1);
            opponentPlay(play);
        }
        @Override
        public void run() {
            try {
                this.runWait();
            } catch (RemoteException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Creates new form Match
     */
    public Match(Game game) throws RemoteException{
        this.game = game;
        this.setCardImageName();
        initComponents();
        this.verifyNext();
        this.setLocationRelativeTo(null);
    }
    public void setIsMe() throws RemoteException{
        if(this.game.getMyCard()==this.game.getPlayer())
            this.isMe = true;
        else
            this.isMe = false;
    }

    public void setCardImageName() throws RemoteException {
        int player = this.game.getPlayer();
        if(player==1)
            this.card = "cross.png";
        else
            this.card = "circle.png";
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        LabelLogin2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        Button1 = new javax.swing.JButton();
        Button2 = new javax.swing.JButton();
        Button3 = new javax.swing.JButton();
        Button4 = new javax.swing.JButton();
        Button5 = new javax.swing.JButton();
        Button6 = new javax.swing.JButton();
        Button7 = new javax.swing.JButton();
        Button8 = new javax.swing.JButton();
        Button9 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        YourTurn = new javax.swing.JLabel();
        player1 = new javax.swing.JLabel();
        Player2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(25, 25, 25));

        LabelLogin2.setBackground(new java.awt.Color(0, 0, 0));
        LabelLogin2.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        LabelLogin2.setForeground(new java.awt.Color(255, 10, 10));
        LabelLogin2.setText("Tic-Tac-Toe");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(121, Short.MAX_VALUE)
                                .addComponent(LabelLogin2)
                                .addGap(103, 103, 103))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(LabelLogin2)
                                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(43, 43, 43));

        Button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    Button1MouseClicked(evt);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        Button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button1ActionPerformed(evt);
            }
        });

        Button2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    Button2MouseClicked(evt);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        Button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button2ActionPerformed(evt);
            }
        });

        Button3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    Button3MouseClicked(evt);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        Button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button3ActionPerformed(evt);
            }
        });

        Button4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    Button4MouseClicked(evt);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        Button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button4ActionPerformed(evt);
            }
        });

        Button5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    Button5MouseClicked(evt);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        Button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button5ActionPerformed(evt);
            }
        });

        Button6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    Button6MouseClicked(evt);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        Button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button6ActionPerformed(evt);
            }
        });

        Button7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    Button7MouseClicked(evt);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        Button8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    Button8MouseClicked(evt);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        Button9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    Button9MouseClicked(evt);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        Button9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button9ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(37, 132, 222));
        jLabel1.setFont(new java.awt.Font("Ubuntu", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Vs");

        YourTurn.setBackground(new java.awt.Color(37, 132, 222));
        YourTurn.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        YourTurn.setForeground(new java.awt.Color(5, 90, 255));
        YourTurn.setText("it's your turn");
        YourTurn.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                YourTurnAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        player1.setBackground(new java.awt.Color(255, 255, 255));
        player1.setFont(new java.awt.Font("Ubuntu", 1, 26)); // NOI18N
        player1.setForeground(new java.awt.Color(255, 255, 255));
        player1.setText("Player1");
        player1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                player1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        Player2.setBackground(new java.awt.Color(255, 255, 255));
        Player2.setFont(new java.awt.Font("Ubuntu", 1, 26)); // NOI18N
        Player2.setForeground(new java.awt.Color(255, 255, 255));
        Player2.setText("Player2");
        Player2.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                Player2AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(25, 25, 25));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(150, 150, 150)
                                                .addComponent(YourTurn))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(63, 63, 63)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                .addComponent(player1)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel1)
                                                                .addGap(40, 40, 40)
                                                                .addComponent(Player2))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                                .addComponent(Button4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(Button5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                                .addComponent(Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(Button2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                                                .addComponent(Button7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(Button8, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(Button6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(Button3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(Button9, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(player1)
                                        .addComponent(Player2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Button2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Button3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Button4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Button5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Button6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Button7, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Button8, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Button9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(YourTurn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 1, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>

    private void verifyNext() throws  RemoteException{
        this.game.setPlayer();
        this.setIsMe();
        if((!this.isMe && !this.weHaveAWinner() && this.game.getMultiPlayer()==1) ||
                (!this.weHaveAWinner() && this.game.getMultiPlayer()==2))
            matchControl();
        else if (this.weHaveAWinner()){
            this.game.restartBoard();
            if(this.winner == 2){
                this.game.setCounterTie();
                Tie tie = new Tie(this.game);
                tie.setVisible(true);
                tie.pack();
                tie.setLocationRelativeTo(null);
                tie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.dispose();
            }
            else if(this.winner == this.game.getMyCard()){
                this.game.setCounterWin();
                Winner win = new Winner(this.game);
                win.setVisible(true);
                win.pack();
                win.setLocationRelativeTo(null);
                win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.dispose();
            }
            else{
                this.game.setCounterLose();
                Lose ls = new Lose(this.game);
                ls.setVisible(true);
                ls.pack();
                ls.setLocationRelativeTo(null);
                ls.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.dispose();
            }
        }
    }
    private boolean weHaveAWinner() throws RemoteException{
        this.winner = this.game.getWinner();
        if (this.winner==-1)
            return false;
        return true;
    }
    private void Button1MouseClicked(java.awt.event.MouseEvent evt) throws RemoteException{
        // TODO add your handling code here:
        boolean playAccepted;
        if((!this.buttonPressed[0] && this.game.getMultiPlayer()==1) || (!this.buttonPressed[0] && this.game.getMultiPlayer()==2 && this.isMe)){
            playAccepted = this.game.play(1);
            if (playAccepted){
                this.setCardImageName();
                Button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cross/"+this.card)));
                System.out.println("Button: 1");
                this.buttonPressed[0]=true;
                this.verifyNext();
            }
        }
    }

    private void Button5ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void Button2MouseClicked(java.awt.event.MouseEvent evt) throws RemoteException{
        // TODO add your handling code here:
        boolean playAccepted;
        if((!this.buttonPressed[1] && this.game.getMultiPlayer()==1) || (!this.buttonPressed[1] && this.game.getMultiPlayer()==2 && this.isMe)){
            playAccepted = this.game.play(2);
            if (playAccepted){
                this.setCardImageName();
                Button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cross/"+this.card)));
                System.out.println("Button: 2");
                this.buttonPressed[1]=true;
                this.verifyNext();
            }
        }
    }

    private void Button3MouseClicked(java.awt.event.MouseEvent evt) throws  RemoteException{
        // TODO add your handling code here:
        boolean playAccepted;
        if((!this.buttonPressed[2] && this.game.getMultiPlayer()==1) || (!this.buttonPressed[2] && this.game.getMultiPlayer()==2 && this.isMe)){
            playAccepted = this.game.play(3);
            if (playAccepted){
                this.setCardImageName();
                Button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cross/"+this.card)));
                System.out.println("Button: 3");
                this.buttonPressed[2]=true;
                this.verifyNext();
            }
        }
    }

    private void Button4MouseClicked(java.awt.event.MouseEvent evt) throws RemoteException{
        // TODO add your handling code here:
        boolean playAccepted;
        if((!this.buttonPressed[3] && this.game.getMultiPlayer()==1) || (!this.buttonPressed[3] && this.game.getMultiPlayer()==2 && this.isMe)){
            playAccepted = this.game.play(4);
            if (playAccepted){
                this.setCardImageName();
                Button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cross/"+this.card)));
                System.out.println("Button: 4");
                this.buttonPressed[3]=true;
                this.verifyNext();
            }
        }
    }

    private void Button5MouseClicked(java.awt.event.MouseEvent evt) throws RemoteException{
        // TODO add your handling code here:
        boolean playAccepted;
        if((!this.buttonPressed[4] && this.game.getMultiPlayer()==1) || (!this.buttonPressed[4] && this.game.getMultiPlayer()==2 && this.isMe)){
            playAccepted = this.game.play(5);
            if(playAccepted){
                this.setCardImageName();
                Button5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cross/"+this.card)));
                System.out.println("Button: 5");
                this.buttonPressed[4]=true;
                this.verifyNext();
            }
        }
    }

    private void Button6MouseClicked(java.awt.event.MouseEvent evt) throws RemoteException{
        // TODO add your handling code here:
        boolean playAccepted;
        if((!this.buttonPressed[5] && this.game.getMultiPlayer()==1) || (!this.buttonPressed[5] && this.game.getMultiPlayer()==2 && this.isMe)){
            playAccepted = this.game.play(6);
            if(playAccepted){
                this.setCardImageName();
                Button6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cross/"+this.card)));
                System.out.println("Button: 6");
                this.buttonPressed[5]=true;
                this.verifyNext();
            }
        }
    }

    private void Button7MouseClicked(java.awt.event.MouseEvent evt) throws RemoteException{
        // TODO add your handling code here:
        boolean playAccepted;
        if((!this.buttonPressed[6] && this.game.getMultiPlayer()==1) || (!this.buttonPressed[6] && this.game.getMultiPlayer()==2 && this.isMe)){
            playAccepted = this.game.play(7);
            if (playAccepted){
                this.setCardImageName();
                Button7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cross/"+this.card)));
                System.out.println("Button: 7");
                this.buttonPressed[6]=true;
                this.verifyNext();
            }
        }
    }

    private void Button8MouseClicked(java.awt.event.MouseEvent evt) throws RemoteException{
        // TODO add your handling code here:
        boolean playAccepted;
        if((!this.buttonPressed[7] && this.game.getMultiPlayer()==1) || (!this.buttonPressed[7] && this.game.getMultiPlayer()==2 && this.isMe)){
            playAccepted = this.game.play(8);
            if (playAccepted){
                this.setCardImageName();
                Button8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cross/"+this.card)));
                System.out.println("Button: 8");
                this.buttonPressed[7]=true;
                this.verifyNext();
            }
        }
    }

    private void Button9MouseClicked(java.awt.event.MouseEvent evt) throws  RemoteException{
        // TODO add your handling code here:
        boolean playAccepted;
        if((!this.buttonPressed[8] && this.game.getMultiPlayer()==1) || (!this.buttonPressed[8] && this.game.getMultiPlayer()==2 && this.isMe)){
            playAccepted = this.game.play(9);
            if(playAccepted){
                this.setCardImageName();
                Button9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cross/"+this.card)));
                System.out.println("Button: 9");
                this.buttonPressed[8]=true;
                this.verifyNext();
            }
        }
    }

    private void YourTurnAncestorAdded(javax.swing.event.AncestorEvent evt) {
        // TODO add your handling code here:
    }

    private void Player2AncestorAdded(javax.swing.event.AncestorEvent evt) {
        // TODO add your handling code here:
    }

    private void player1AncestorAdded(javax.swing.event.AncestorEvent evt) {
        // TODO add your handling code here:
    }

    private void Button1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void Button2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void Button6ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void Button4ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void Button3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void Button9ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    public void opponentPlay(int play) throws RemoteException {
        switch(play) {
            case 1:
                this.Button1MouseClicked(null);
                break;
            case 2:
                this.Button2MouseClicked(null);
                break;
            case 3:
                this.Button3MouseClicked(null);
                break;
            case 4:
                this.Button4MouseClicked(null);
                break;
            case 5:
                this.Button5MouseClicked(null);
                break;
            case 6:
                this.Button6MouseClicked(null);
                break;
            case 7:
                this.Button7MouseClicked(null);
                break;
            case 8:
                this.Button8MouseClicked(null);
                break;
            case 9:
                this.Button9MouseClicked(null);
                break;
        }
        System.out.println("Opponent Play: "+play);
        this.game.setOpponentPlayToDefault();
    }

    public void matchControl() throws RemoteException {
        int computerPlay=0,opponentPlay=0;boolean valid;
        if(this.game.getMultiPlayer()==1){
            do{
                valid=true;
                computerPlay = this.game.getComputerPlay();
                if(this.buttonPressed[computerPlay-1])
                    valid = false;
                if(computerPlay>9)
                    valid = false;
                if(computerPlay<1)
                    valid = false;
                System.out.println("Computer: "+computerPlay);
            }while(!valid);
            System.out.println("Computer: "+computerPlay);
            this.opponentPlay(computerPlay);
        }
        else if (this.game.getMultiPlayer()==2){
            WaitPlay wait = new WaitPlay(this.game);
            Thread t1 = new Thread(wait);
            t1.start();
        }
        System.out.println(this.isMe);

    }

    public void startMatch(Game game)  {
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
            java.util.logging.Logger.getLogger(Match.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Match.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Match.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Match.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Match(game).setVisible(true);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton Button1;
    private javax.swing.JButton Button2;
    private javax.swing.JButton Button3;
    private javax.swing.JButton Button4;
    private javax.swing.JButton Button5;
    private javax.swing.JButton Button6;
    private javax.swing.JButton Button7;
    private javax.swing.JButton Button8;
    private javax.swing.JButton Button9;
    private javax.swing.JLabel LabelLogin2;
    private javax.swing.JLabel Player2;
    private javax.swing.JLabel YourTurn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel player1;
    // End of variables declaration
}
