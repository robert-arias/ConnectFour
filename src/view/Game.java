package view;

import controller.Controller;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author rober
 */
public class Game extends javax.swing.JFrame {

    Controller controller;
    
    public Game() {
        initComponents();
        board.setGame(this);
        controller = new Controller(preferences, board);
        preferences.addListeners(controller);
        board.addListeners(controller);
        jm_file.requestFocusInWindow();
    }
    
    public int showWinner(char turn) {
        String message = "Congratulations, " +
                    ((turn == 'x') ? preferences.getPlayer2() : preferences.getPlayer1()) +
                    " has won the match!\nWould you like to play again?";

        return JOptionPane.showConfirmDialog(rootPane, message, "Game Over", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/assets/trophy.png")));
    }
    
    public void enableOptions() {
        preferences.enableOptions();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        preferences = new view.Preferences();
        board = new view.Board();
        jmb_menu = new javax.swing.JMenuBar();
        jm_file = new javax.swing.JMenu();
        jmi_instructions = new javax.swing.JMenuItem();
        jmi_about = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusCycleRoot(false);

        preferences.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jm_file.setText("File");

        jmi_instructions.setText("Instructions");
        jm_file.add(jmi_instructions);

        jmi_about.setText("About");
        jm_file.add(jmi_about);

        jmb_menu.add(jm_file);

        setJMenuBar(jmb_menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(preferences, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(board, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(board, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(preferences, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Game().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.Board board;
    private javax.swing.JMenu jm_file;
    private javax.swing.JMenuBar jmb_menu;
    private javax.swing.JMenuItem jmi_about;
    private javax.swing.JMenuItem jmi_instructions;
    private view.Preferences preferences;
    // End of variables declaration//GEN-END:variables
}