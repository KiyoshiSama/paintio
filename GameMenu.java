
package paintio.paintio;


public class GameMenu extends javax.swing.JFrame {

    public int speedN;
    public int enemyNum;
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
        enemyCount = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 102));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 153, 51));
        jTextField1.setFont(new java.awt.Font("Tempus Sans ITC", 0, 24)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Welcome to Paint.io game!");
        jTextField1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
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
        GameDiff.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DIFFICULITY:", "EASY", "NORMAL", "HARD" }));
        GameDiff.setToolTipText("");
        GameDiff.setBorder(null);
        GameDiff.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        GameDiff.setDoubleBuffered(true);
        GameDiff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GameDiffActionPerformed(evt);
            }
        });

        enemyCount.setMaximum(10);
        enemyCount.setMinimum(1);
        enemyCount.setPaintLabels(true);
        enemyCount.setPaintTicks(true);
        enemyCount.setSnapToTicks(true);
        enemyCount.setToolTipText("");
        enemyCount.setOpaque(false);
        enemyCount.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                enemyCountStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("TNC_F1", 0, 21)); // NOI18N
        jLabel1.setText(" 1 ,  2  , 3 , 4 , 5 ,  6 , 7 , 8 , 9 , 10 ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(GameSpeed, 0, 435, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                            .addComponent(StartBut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(GameDiff, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(enemyCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(229, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126)
                .addComponent(StartBut, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(GameDiff, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(GameSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(enemyCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
      /*   String diffS = GameDiff.getSelectedItem().toString();
        switch (diffS) {
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
        }     */ 
    }//GEN-LAST:event_GameDiffActionPerformed

    private void StartButMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StartButMouseClicked
        String speedS = GameSpeed.getSelectedItem().toString();
        if ("SPEED:".equals(speedS))speedN = 10;
        setVisible(false);
       InputSelect inputFrame = new InputSelect(this);
       inputFrame.setVisible(true);
       
       
    }//GEN-LAST:event_StartButMouseClicked

    private void GameSpeedItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_GameSpeedItemStateChanged



        
    }//GEN-LAST:event_GameSpeedItemStateChanged

    private void enemyCountStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_enemyCountStateChanged
        enemyNum = enemyCount.getValue();
    }//GEN-LAST:event_enemyCountStateChanged
    
    public int getEnemyCount(){
    return enemyNum;
    }
    
    public int getSpeed(){
    return speedN;
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
    private javax.swing.JComboBox<String> GameDiff;
    private javax.swing.JComboBox<String> GameSpeed;
    private javax.swing.JButton StartBut;
    private javax.swing.JSlider enemyCount;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
