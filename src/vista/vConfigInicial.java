/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.awt.Toolkit;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import utils.constantes.Constantes;

/**
 *
 * @author vacev
 */
public class vConfigInicial extends javax.swing.JFrame {

    /**
     * Creates new form vConfigInicial
     */
    public vConfigInicial() {
        initComponents();
        this.iniciarVista();
    }
    
    public void iniciarVista() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/utils/icon/user.png")));
        
        ComboBoxModel<String> modeloPreguntasRecuperacion1 = new DefaultComboBoxModel<String> (Constantes.PREGUNTAS_SEGURIDAD);
        ComboBoxModel<String> modeloPreguntasRecuperacion2 = new DefaultComboBoxModel<String> (Constantes.PREGUNTAS_SEGURIDAD);
        ComboBoxModel<String> modeloPreguntasRecuperacion3 = new DefaultComboBoxModel<String> (Constantes.PREGUNTAS_SEGURIDAD);
        this.comboPreguntaRecuperacion1.setModel(modeloPreguntasRecuperacion1);
        this.comboPreguntaRecuperacion2.setModel(modeloPreguntasRecuperacion2);
        this.comboPreguntaRecuperacion3.setModel(modeloPreguntasRecuperacion3);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSPanel1 = new necesario.RSPanel();
        rSButtonShapeIcon15 = new RSMaterialComponent.RSButtonShapeIcon();
        rSButtonShapeIcon10 = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel1 = new javax.swing.JLabel();
        comboPreguntaRecuperacion2 = new RSMaterialComponent.RSComboBoxMaterial();
        txtRespuesta2 = new RSMaterialComponent.RSTextFieldMaterial();
        rSButtonShapeIcon9 = new RSMaterialComponent.RSButtonShapeIcon();
        txtCorreo = new RSMaterialComponent.RSTextFieldMaterial();
        txtClave = new RSMaterialComponent.RSPasswordMaterial();
        txtCarnet = new RSMaterialComponent.RSTextFieldMaterial();
        comboPreguntaRecuperacion1 = new RSMaterialComponent.RSComboBoxMaterial();
        txtRespuesta1 = new RSMaterialComponent.RSTextFieldMaterial();
        comboPreguntaRecuperacion3 = new RSMaterialComponent.RSComboBoxMaterial();
        txtRespuesta3 = new RSMaterialComponent.RSTextFieldMaterial();
        txtNombres = new RSMaterialComponent.RSTextFieldMaterial();
        txtApellidos = new RSMaterialComponent.RSPasswordMaterial();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        rSPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        rSPanel1.setForeground(new java.awt.Color(255, 255, 255));
        rSPanel1.setColorBackground(new java.awt.Color(255, 255, 255));

        rSButtonShapeIcon15.setBackground(new java.awt.Color(33, 58, 86));
        rSButtonShapeIcon15.setText("Crear configuración");
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

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utils/img/recuperacion.png"))); // NOI18N
        jLabel1.setToolTipText("");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setIconTextGap(1);

        comboPreguntaRecuperacion2.setBorder(null);
        comboPreguntaRecuperacion2.setColorMaterial(new java.awt.Color(102, 102, 102));

        txtRespuesta2.setForeground(new java.awt.Color(0, 0, 0));
        txtRespuesta2.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtRespuesta2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtRespuesta2.setPhColor(new java.awt.Color(0, 0, 0));
        txtRespuesta2.setPlaceholder("Digite la respuesta..");
        txtRespuesta2.setSelectionColor(new java.awt.Color(0, 0, 0));

        rSButtonShapeIcon9.setBackground(new java.awt.Color(33, 58, 86));
        rSButtonShapeIcon9.setToolTipText("SALIR DE LA APLICACIÓN");
        rSButtonShapeIcon9.setBackgroundHover(new java.awt.Color(33, 68, 86));
        rSButtonShapeIcon9.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.RECT);
        rSButtonShapeIcon9.setHideActionText(true);
        rSButtonShapeIcon9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonShapeIcon9.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLOSE);
        rSButtonShapeIcon9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShapeIcon9ActionPerformed(evt);
            }
        });

        txtCorreo.setPlaceholder("Correo");
        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });

        txtClave.setPlaceholder("Clave de acceso");

        txtCarnet.setPlaceholder("Carnét");
        txtCarnet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCarnetActionPerformed(evt);
            }
        });

        comboPreguntaRecuperacion1.setColorMaterial(new java.awt.Color(102, 102, 102));

        txtRespuesta1.setForeground(new java.awt.Color(0, 0, 0));
        txtRespuesta1.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtRespuesta1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtRespuesta1.setPhColor(new java.awt.Color(0, 0, 0));
        txtRespuesta1.setPlaceholder("Digite la respuesta..");
        txtRespuesta1.setSelectionColor(new java.awt.Color(0, 0, 0));

        comboPreguntaRecuperacion3.setColorMaterial(new java.awt.Color(102, 102, 102));

        txtRespuesta3.setForeground(new java.awt.Color(0, 0, 0));
        txtRespuesta3.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtRespuesta3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtRespuesta3.setPhColor(new java.awt.Color(0, 0, 0));
        txtRespuesta3.setPlaceholder("Digite la respuesta..");
        txtRespuesta3.setSelectionColor(new java.awt.Color(0, 0, 0));

        txtNombres.setPlaceholder("Nombres");
        txtNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresActionPerformed(evt);
            }
        });

        txtApellidos.setPlaceholder("Apellidos");

        javax.swing.GroupLayout rSPanel1Layout = new javax.swing.GroupLayout(rSPanel1);
        rSPanel1.setLayout(rSPanel1Layout);
        rSPanel1Layout.setHorizontalGroup(
            rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSButtonShapeIcon9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(rSPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanel1Layout.createSequentialGroup()
                        .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rSPanel1Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel1))
                            .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRespuesta1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboPreguntaRecuperacion2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtRespuesta2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboPreguntaRecuperacion1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(rSPanel1Layout.createSequentialGroup()
                        .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCarnet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboPreguntaRecuperacion3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtRespuesta3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(rSPanel1Layout.createSequentialGroup()
                                .addComponent(rSButtonShapeIcon10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rSButtonShapeIcon15, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(102, 102, 102))
        );
        rSPanel1Layout.setVerticalGroup(
            rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanel1Layout.createSequentialGroup()
                .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rSPanel1Layout.createSequentialGroup()
                        .addComponent(rSButtonShapeIcon9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rSPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(comboPreguntaRecuperacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRespuesta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboPreguntaRecuperacion2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRespuesta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(rSPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtCarnet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))
                    .addGroup(rSPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboPreguntaRecuperacion3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRespuesta3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rSButtonShapeIcon10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSButtonShapeIcon15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(rSPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonShapeIcon10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShapeIcon10ActionPerformed
        vInicio inicio = new vInicio();
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSButtonShapeIcon10ActionPerformed

    private void rSButtonShapeIcon15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShapeIcon15ActionPerformed
        
    }//GEN-LAST:event_rSButtonShapeIcon15ActionPerformed

    private void rSButtonShapeIcon9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShapeIcon9ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_rSButtonShapeIcon9ActionPerformed

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void txtCarnetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarnetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarnetActionPerformed

    private void txtNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresActionPerformed

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
            java.util.logging.Logger.getLogger(vConfigInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vConfigInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vConfigInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vConfigInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vConfigInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSComboBoxMaterial comboPreguntaRecuperacion1;
    private RSMaterialComponent.RSComboBoxMaterial comboPreguntaRecuperacion2;
    private RSMaterialComponent.RSComboBoxMaterial comboPreguntaRecuperacion3;
    private javax.swing.JLabel jLabel1;
    private RSMaterialComponent.RSButtonShapeIcon rSButtonShapeIcon10;
    private RSMaterialComponent.RSButtonShapeIcon rSButtonShapeIcon15;
    private RSMaterialComponent.RSButtonShapeIcon rSButtonShapeIcon9;
    private necesario.RSPanel rSPanel1;
    private RSMaterialComponent.RSPasswordMaterial txtApellidos;
    private RSMaterialComponent.RSTextFieldMaterial txtCarnet;
    private RSMaterialComponent.RSPasswordMaterial txtClave;
    private RSMaterialComponent.RSTextFieldMaterial txtCorreo;
    private RSMaterialComponent.RSTextFieldMaterial txtNombres;
    private RSMaterialComponent.RSTextFieldMaterial txtRespuesta1;
    private RSMaterialComponent.RSTextFieldMaterial txtRespuesta2;
    private RSMaterialComponent.RSTextFieldMaterial txtRespuesta3;
    // End of variables declaration//GEN-END:variables
}
