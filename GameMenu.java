
package paintio.paintio;

import javax.swing.JOptionPane;


public class GameMenu extends javax.swing.JFrame {

    public int speedN;
    public int enemyNum;
    public int weaponAammo;
    public int weaponBrecharge;
    public int enemiesSpeed;
    public String characterN = null;
    public GameMenu() {
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

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        StartBut = new javax.swing.JButton();
        GameSpeed = new javax.swing.JComboBox<>();
        GameDiff = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        Batman = new javax.swing.JLabel();
        Penny = new javax.swing.JLabel();
        Walter = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Rick = new javax.swing.JLabel();
        enemyCount = new javax.swing.JSlider();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 102));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 153, 51));
        jTextField1.setFont(new java.awt.Font("Algerian", 1, 36)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Welcome to Paint.io game!");
        jTextField1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jTextField1.setOpaque(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        StartBut.setBackground(new java.awt.Color(255, 153, 102));
        StartBut.setFont(new java.awt.Font("Bernard MT Condensed", 0, 24)); // NOI18N
        StartBut.setText("Start the Game");
        StartBut.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(), null));
        StartBut.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        StartBut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StartButMouseClicked(evt);
            }
        });

        GameSpeed.setBackground(new java.awt.Color(255, 153, 102));
        GameSpeed.setFont(new java.awt.Font("Bernard MT Condensed", 0, 24)); // NOI18N
        GameSpeed.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SPEED:", "LOW", "MEDIUM", "HIGH" }));
        GameSpeed.setToolTipText("SPEED:");
        GameSpeed.setBorder(null);
        GameSpeed.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        GameSpeed.setDoubleBuffered(true);
        GameSpeed.setName("SPEED:"); // NOI18N
        GameSpeed.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                GameSpeedItemStateChanged(evt);
            }
        });
        GameSpeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GameSpeedActionPerformed(evt);
            }
        });

        GameDiff.setBackground(new java.awt.Color(255, 153, 102));
        GameDiff.setFont(new java.awt.Font("Bernard MT Condensed", 0, 24)); // NOI18N
        GameDiff.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DIFFICULITY:(Makes ammo use and movement harder)", "EASY", "NORMAL", "HARD" }));
        GameDiff.setToolTipText("");
        GameDiff.setBorder(null);
        GameDiff.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        GameDiff.setDoubleBuffered(true);
        GameDiff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GameDiffActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("TNC_F1", 0, 24)); // NOI18N
        jLabel1.setText(" 1 , 2  , 3 , 4 ,  5 , 6 , 7 ,  8 , 9 , 10");

        Batman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paintio/paintio/resources/player/BatmanMenu.png"))); // NOI18N
        Batman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BatmanMouseClicked(evt);
            }
        });

        Penny.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paintio/paintio/resources/player/PennyMenu.png"))); // NOI18N
        Penny.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PennyMouseClicked(evt);
            }
        });

        Walter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paintio/paintio/resources/player/WalterMenu.png"))); // NOI18N
        Walter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                WalterMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Algerian", 0, 30)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Choose your character:");
        jLabel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Rick.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paintio/paintio/resources/player/RickMenu.png"))); // NOI18N
        Rick.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RickMouseClicked(evt);
            }
        });

        enemyCount.setMaximum(10);
        enemyCount.setMinimum(1);
        enemyCount.setPaintLabels(true);
        enemyCount.setPaintTicks(true);
        enemyCount.setSnapToTicks(true);
        enemyCount.setValue(1);
        enemyCount.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        enemyCount.setOpaque(false);
        enemyCount.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                enemyCountStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(855, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(enemyCount, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(StartBut, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(105, 105, 105))
                            .addComponent(GameSpeed, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(GameDiff, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(Batman)
                                .addGap(41, 41, 41)
                                .addComponent(Penny)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Rick)
                                .addGap(53, 53, 53)
                                .addComponent(Walter))
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(672, 672, 672))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(723, 723, 723))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Batman)
                    .addComponent(Walter)
                    .addComponent(Penny)
                    .addComponent(Rick))
                .addGap(38, 38, 38)
                .addComponent(GameDiff, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(GameSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(enemyCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(StartBut, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(301, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void GameSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GameSpeedActionPerformed
       String speedS = GameSpeed.getSelectedItem().toString();
        switch (speedS) {
            case "LOW":
                speedN = 5;
                break;
            case "MEDIUM":
                speedN = 10;
                break;
            case "HIGH":
                speedN = 16;
                break;
            default:
                break;
        }      
    }//GEN-LAST:event_GameSpeedActionPerformed

    private void GameDiffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GameDiffActionPerformed
        String diffS = GameDiff.getSelectedItem().toString();
        switch (diffS) {
            case "EASY":
                weaponAammo = 5;
                weaponBrecharge = 3000;
                enemiesSpeed = 110;
                break;
            case "NORMAL":
                weaponAammo = 3;
                weaponBrecharge = 5000;
                enemiesSpeed = 50;
                break;
            case "HARD":
                weaponAammo = 1;
                weaponBrecharge =7000;
                enemiesSpeed =20;
                break;
            default:
                break;
        }     
    }//GEN-LAST:event_GameDiffActionPerformed

    private void StartButMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StartButMouseClicked
        String speedS = GameSpeed.getSelectedItem().toString();
        if ("SPEED:".equals(speedS))speedN = 10;
        String Diff = GameDiff.getSelectedItem().toString();
        if ("DIFFICULITY:(Makes ammo use and movement harder)".equals(Diff)){
        weaponAammo = 5;
        weaponBrecharge = 3000;
        enemiesSpeed = 300;
        }
        if (characterN == null){
                characterN = "BATMAN";          
        }
        if (enemyCount.getValue() ==1)enemyNum = 1;
        setVisible(false);
       InputSelect inputFrame = new InputSelect(this);
       inputFrame.setVisible(true);
       
       
    }//GEN-LAST:event_StartButMouseClicked

    private void GameSpeedItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_GameSpeedItemStateChanged



        
    }//GEN-LAST:event_GameSpeedItemStateChanged

    private void BatmanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BatmanMouseClicked
        JOptionPane.showMessageDialog(this, "Character selected! ");
        characterN = "BATMAN";
    }//GEN-LAST:event_BatmanMouseClicked

    private void PennyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PennyMouseClicked
        JOptionPane.showMessageDialog(this, "Character selected! ");
        characterN = "PENNY";
    }//GEN-LAST:event_PennyMouseClicked

    private void WalterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WalterMouseClicked
        JOptionPane.showMessageDialog(this, "Character selected! ");
        characterN = "WALTER";
    }//GEN-LAST:event_WalterMouseClicked

    private void RickMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RickMouseClicked
        JOptionPane.showMessageDialog(this, "Character selected! ");
        characterN = "RICK";
    }//GEN-LAST:event_RickMouseClicked

    private void enemyCountStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_enemyCountStateChanged
        enemyNum = enemyCount.getValue();
    }//GEN-LAST:event_enemyCountStateChanged
    
    public int getEnemyCount(){
    return enemyNum;
    }
    
    public int getSpeed(){
    return speedN;
    }
   
    public int getWeaponAammo(){
    return weaponAammo;
    
    }
    
    public int getWeaponBrecharge(){
    return weaponBrecharge;
    
    }
    public int getEnemiesSpeed(){
    return enemiesSpeed;
    
    }
    public String getCharacterName(){
    return characterN;
    
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
            java.util.logging.Logger.getLogger(GameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Batman;
    private javax.swing.JComboBox<String> GameDiff;
    private javax.swing.JComboBox<String> GameSpeed;
    private javax.swing.JLabel Penny;
    private javax.swing.JLabel Rick;
    private javax.swing.JButton StartBut;
    private javax.swing.JLabel Walter;
    private javax.swing.JSlider enemyCount;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
