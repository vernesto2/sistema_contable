/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author vacev
 */
public class vLogin extends javax.swing.JFrame {

    /**
     * Creates new form vLogin
     */
    public vLogin() {
        initComponents();
        this.iniciarVista();
    }
    
    public void iniciarVista() {
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("LOGIN");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/utils/icon/login.png")));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSPassView1 = new rojeru_san.rsfield.RSPassView();
        rSPanel1 = new necesario.RSPanel();
        jLabel1 = new javax.swing.JLabel();
        rSPasswordMaterial1 = new RSMaterialComponent.RSPasswordMaterial();
        rSTextFieldMaterial1 = new RSMaterialComponent.RSTextFieldMaterial();
        rSCheckBox1 = new rojerusan.RSCheckBox();
        rSButtonShapeIcon15 = new RSMaterialComponent.RSButtonShapeIcon();
        rSButtonShapeIcon10 = new RSMaterialComponent.RSButtonShapeIcon();
        flatButton1 = new rojeru_san.extras.FlatButton();

        rSPassView1.setText("rSPassView1");
        rSPassView1.setModoMaterial(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        rSPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        rSPanel1.setForeground(new java.awt.Color(255, 255, 255));
        rSPanel1.setColorBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utils/img/login.png"))); // NOI18N
        jLabel1.setToolTipText("");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setIconTextGap(1);

        rSPasswordMaterial1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        rSPasswordMaterial1.setPlaceholder("Digite la contraseña..");
        rSPasswordMaterial1.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);

        rSTextFieldMaterial1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        rSTextFieldMaterial1.setPlaceholder("Digite el usuario..");

        rSCheckBox1.setText("Mostrar Contraseña");
        rSCheckBox1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        rSButtonShapeIcon15.setBackground(new java.awt.Color(33, 58, 86));
        rSButtonShapeIcon15.setText("INGRESAR");
        rSButtonShapeIcon15.setBackgroundHover(new java.awt.Color(33, 68, 86));
        rSButtonShapeIcon15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        rSButtonShapeIcon15.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        rSButtonShapeIcon15.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CACHED);
        rSButtonShapeIcon15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShapeIcon15ActionPerformed(evt);
            }
        });

        rSButtonShapeIcon10.setBackground(new java.awt.Color(251, 205, 6));
        rSButtonShapeIcon10.setText("CANCELAR");
        rSButtonShapeIcon10.setBackgroundHover(new java.awt.Color(251, 174, 6));
        rSButtonShapeIcon10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        rSButtonShapeIcon10.setForegroundHover(new java.awt.Color(0, 0, 0));
        rSButtonShapeIcon10.setForegroundIcon(new java.awt.Color(0, 0, 0));
        rSButtonShapeIcon10.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        rSButtonShapeIcon10.setForegroundText(new java.awt.Color(0, 0, 0));
        rSButtonShapeIcon10.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        rSButtonShapeIcon10.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        rSButtonShapeIcon10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShapeIcon10ActionPerformed(evt);
            }
        });

        flatButton1.setBackground(new java.awt.Color(255, 255, 255));
        flatButton1.setForeground(new java.awt.Color(0, 0, 0));
        flatButton1.setText("¿Olvido su contraseña?");
        flatButton1.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N

        javax.swing.GroupLayout rSPanel1Layout = new javax.swing.GroupLayout(rSPanel1);
        rSPanel1.setLayout(rSPanel1Layout);
        rSPanel1Layout.setHorizontalGroup(
            rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanel1Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addComponent(rSButtonShapeIcon10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(rSButtonShapeIcon15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
            .addGroup(rSPanel1Layout.createSequentialGroup()
                .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanel1Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(flatButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanel1Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rSPasswordMaterial1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSCheckBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(rSTextFieldMaterial1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        rSPanel1Layout.setVerticalGroup(
            rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rSTextFieldMaterial1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(rSPasswordMaterial1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(rSCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSButtonShapeIcon10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonShapeIcon15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(flatButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonShapeIcon15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShapeIcon15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonShapeIcon15ActionPerformed

    private void rSButtonShapeIcon10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShapeIcon10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonShapeIcon10ActionPerformed

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
            java.util.logging.Logger.getLogger(vLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.extras.FlatButton flatButton1;
    private javax.swing.JLabel jLabel1;
    private RSMaterialComponent.RSButtonShapeIcon rSButtonShapeIcon10;
    private RSMaterialComponent.RSButtonShapeIcon rSButtonShapeIcon15;
    private rojerusan.RSCheckBox rSCheckBox1;
    private necesario.RSPanel rSPanel1;
    private rojeru_san.rsfield.RSPassView rSPassView1;
    private RSMaterialComponent.RSPasswordMaterial rSPasswordMaterial1;
    private RSMaterialComponent.RSTextFieldMaterial rSTextFieldMaterial1;
    // End of variables declaration//GEN-END:variables
}
