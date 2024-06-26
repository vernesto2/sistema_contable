/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.awt.Toolkit;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.Usuario;
import servicios.ServicioUsuario;
import utils.UtileriaVista;
import utils.constantes.Constantes;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class vRecuperacion extends javax.swing.JFrame {

    /**
     * Creates new form vRecuperacion
     */
    ServicioUsuario _usuario;
    
    public vRecuperacion() {
        initComponents();
        this._usuario = new ServicioUsuario(Constantes.rutaConexion);
        this.iniciarVista();
    }
    
    public void iniciarVista() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/utils/icon/user.png")));
        ComboBoxModel<String> modeloPreguntasRecuperacion1 = new DefaultComboBoxModel<String>(Constantes.PREGUNTAS_SEGURIDAD);
        this.cmbPreguntas.setModel(modeloPreguntasRecuperacion1);
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
        txtUsuario = new RSMaterialComponent.RSTextFieldMaterial();
        rSButtonShapeIcon15 = new RSMaterialComponent.RSButtonShapeIcon();
        rSButtonShapeIcon10 = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel1 = new javax.swing.JLabel();
        cmbPreguntas = new RSMaterialComponent.RSComboBoxMaterial();
        txtRespuesta = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel3 = new javax.swing.JLabel();
        rSButtonShapeIcon9 = new RSMaterialComponent.RSButtonShapeIcon();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        rSPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        rSPanel1.setForeground(new java.awt.Color(255, 255, 255));
        rSPanel1.setColorBackground(new java.awt.Color(255, 255, 255));

        txtUsuario.setForeground(new java.awt.Color(0, 0, 0));
        txtUsuario.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtUsuario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtUsuario.setPhColor(new java.awt.Color(0, 0, 0));
        txtUsuario.setPlaceholder("Digite el usuario..");
        txtUsuario.setSelectionColor(new java.awt.Color(0, 0, 0));

        rSButtonShapeIcon15.setBackground(new java.awt.Color(33, 58, 86));
        rSButtonShapeIcon15.setText("Recuperar");
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
        rSButtonShapeIcon10.setText("Cancelar");
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

        cmbPreguntas.setBorder(null);
        cmbPreguntas.setColorMaterial(new java.awt.Color(102, 102, 102));

        txtRespuesta.setForeground(new java.awt.Color(0, 0, 0));
        txtRespuesta.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtRespuesta.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtRespuesta.setPhColor(new java.awt.Color(0, 0, 0));
        txtRespuesta.setPlaceholder("Digite la respuesta..");
        txtRespuesta.setSelectionColor(new java.awt.Color(0, 0, 0));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("RECUPERACIÓN");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        rSButtonShapeIcon9.setBackground(new java.awt.Color(33, 58, 86));
        rSButtonShapeIcon9.setToolTipText("SALIR DE LA APLICACIÓN");
        rSButtonShapeIcon9.setBackgroundHover(new java.awt.Color(33, 68, 86));
        rSButtonShapeIcon9.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.RECT);
        rSButtonShapeIcon9.setHideActionText(true);
        rSButtonShapeIcon9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonShapeIcon9.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLOSE);
        rSButtonShapeIcon9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rSButtonShapeIcon9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShapeIcon9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rSPanel1Layout = new javax.swing.GroupLayout(rSPanel1);
        rSPanel1.setLayout(rSPanel1Layout);
        rSPanel1Layout.setHorizontalGroup(
            rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanel1Layout.createSequentialGroup()
                        .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rSPanel1Layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(jLabel1))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(rSButtonShapeIcon9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanel1Layout.createSequentialGroup()
                        .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbPreguntas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtRespuesta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(72, 72, 72))))
            .addGroup(rSPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(rSButtonShapeIcon10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rSButtonShapeIcon15, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        rSPanel1Layout.setVerticalGroup(
            rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanel1Layout.createSequentialGroup()
                .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addComponent(rSButtonShapeIcon9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbPreguntas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rSButtonShapeIcon10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonShapeIcon15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonShapeIcon15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShapeIcon15ActionPerformed
        // ir a db a consultar si el usuario existe y si la pregunta hace mach con alguna respuesta configurada.
        if (txtUsuario.getText().isEmpty() || txtRespuesta.getText().isEmpty() || cmbPreguntas.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Complete toda la información", "¡Alerta!", JOptionPane.WARNING_MESSAGE);
        }
        else {
            RespuestaGeneral rg = this._usuario.recuperarUsuario(txtUsuario.getText().trim().toUpperCase(), String.valueOf(cmbPreguntas.getSelectedIndex()), txtRespuesta.getText().trim().toUpperCase());
            if (rg.esExitosa()) {
                //JOptionPane.showMessageDialog(this, "Por favor resetee la clave", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                vReestablecer reestablecer = new vReestablecer((Usuario)rg.getDatos());
                reestablecer.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, rg.getMensaje(), "Mensaje", UtileriaVista.devolverCodigoMensaje(rg));
            }
        }
        
        
    }//GEN-LAST:event_rSButtonShapeIcon15ActionPerformed

    private void rSButtonShapeIcon10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShapeIcon10ActionPerformed
        vInicio inicio = new vInicio();
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSButtonShapeIcon10ActionPerformed

    private void rSButtonShapeIcon9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShapeIcon9ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_rSButtonShapeIcon9ActionPerformed

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
            java.util.logging.Logger.getLogger(vRecuperacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vRecuperacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vRecuperacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vRecuperacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vRecuperacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSComboBoxMaterial cmbPreguntas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private RSMaterialComponent.RSButtonShapeIcon rSButtonShapeIcon10;
    private RSMaterialComponent.RSButtonShapeIcon rSButtonShapeIcon15;
    private RSMaterialComponent.RSButtonShapeIcon rSButtonShapeIcon9;
    private necesario.RSPanel rSPanel1;
    private RSMaterialComponent.RSTextFieldMaterial txtRespuesta;
    private RSMaterialComponent.RSTextFieldMaterial txtUsuario;
    // End of variables declaration//GEN-END:variables
}
