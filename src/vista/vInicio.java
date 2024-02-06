/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import conexion.Conexion;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import utils.constantes.CambiaPanel;
import necesario.RSFileChooser;
import utils.constantes.Constantes;

/**
 *
 * @author vacev
 */
public class vInicio extends javax.swing.JFrame {

    /**
     * Creates new form vInicio
     */
    boolean archivoCorrecto = true;
    
    public vInicio() {
        initComponents();
        this.iniciarVista();
        this.lblRutaGlobal.setText(Constantes.rutaConexion);
    }
    
    public void iniciarVista() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/utils/icon/user.png")));
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
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rSButtonShapeIcon16 = new RSMaterialComponent.RSButtonShapeIcon();
        lblRutaGlobal = new javax.swing.JLabel();
        btnIniciarSesion = new RSMaterialComponent.RSButtonShapeIcon();
        btnCrearConfig = new RSMaterialComponent.RSButtonShapeIcon();
        rSButtonShapeIcon9 = new RSMaterialComponent.RSButtonShapeIcon();
        rSPanelGradiente1 = new rspanelgradiente.RSPanelGradiente();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("¡BIENVENIDO/A!");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("¿Que desea hacer hoy?");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        rSButtonShapeIcon16.setBackground(new java.awt.Color(33, 58, 86));
        rSButtonShapeIcon16.setToolTipText("SELECCIONAR RUTA DE CONFIGURACIÓN");
        rSButtonShapeIcon16.setBackgroundHover(new java.awt.Color(33, 68, 86));
        rSButtonShapeIcon16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        rSButtonShapeIcon16.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        rSButtonShapeIcon16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSButtonShapeIcon16.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.STORAGE);
        rSButtonShapeIcon16.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        rSButtonShapeIcon16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShapeIcon16ActionPerformed(evt);
            }
        });

        lblRutaGlobal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblRutaGlobal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRutaGlobal.setText("Prueba Ruta");
        lblRutaGlobal.setToolTipText("Origen de datos");
        lblRutaGlobal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblRutaGlobal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblRutaGlobalMouseEntered(evt);
            }
        });

        btnIniciarSesion.setBackground(new java.awt.Color(73, 120, 248));
        btnIniciarSesion.setText("INICIAR SESION");
        btnIniciarSesion.setToolTipText("");
        btnIniciarSesion.setBackgroundHover(new java.awt.Color(73, 65, 248));
        btnIniciarSesion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnIniciarSesion.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnIniciarSesion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnIniciarSesion.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PEOPLE);
        btnIniciarSesion.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        btnCrearConfig.setBackground(new java.awt.Color(251, 205, 6));
        btnCrearConfig.setText("CREAR CONFIGURACIÓN");
        btnCrearConfig.setToolTipText("");
        btnCrearConfig.setBackgroundHover(new java.awt.Color(251, 174, 6));
        btnCrearConfig.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCrearConfig.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnCrearConfig.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnCrearConfig.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnCrearConfig.setForegroundText(new java.awt.Color(0, 0, 0));
        btnCrearConfig.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnCrearConfig.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.NEW_RELEASES);
        btnCrearConfig.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        btnCrearConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearConfigActionPerformed(evt);
            }
        });

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

        rSPanelGradiente1.setColorPrimario(new java.awt.Color(102, 102, 102));
        rSPanelGradiente1.setColorSecundario(new java.awt.Color(73, 120, 248));
        rSPanelGradiente1.setGradiente(rspanelgradiente.RSPanelGradiente.Gradiente.VERTICAL);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utils/img/logo.png"))); // NOI18N
        jLabel1.setToolTipText("");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setIconTextGap(1);

        jLabel4.setFont(new java.awt.Font("Arial", 3, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("SISTEMA CONTABLE");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout rSPanelGradiente1Layout = new javax.swing.GroupLayout(rSPanelGradiente1);
        rSPanelGradiente1.setLayout(rSPanelGradiente1Layout);
        rSPanelGradiente1Layout.setHorizontalGroup(
            rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addContainerGap(64, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelGradiente1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        rSPanelGradiente1Layout.setVerticalGroup(
            rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(152, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(rSPanelGradiente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 94, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rSButtonShapeIcon9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnCrearConfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblRutaGlobal, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rSButtonShapeIcon16, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(85, 85, 85))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(rSButtonShapeIcon9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblRutaGlobal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rSButtonShapeIcon16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCrearConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(rSPanelGradiente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        vLogin login = new vLogin();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void btnCrearConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearConfigActionPerformed
        vConfigInicial config = new vConfigInicial();
        config.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCrearConfigActionPerformed

    private void rSButtonShapeIcon9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShapeIcon9ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_rSButtonShapeIcon9ActionPerformed

    private void rSButtonShapeIcon16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShapeIcon16ActionPerformed
        //JFileChooser selectorArchivos = new JFileChooser("./");
        RSFileChooser selector = new RSFileChooser();
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("sqlite", "sqlite", "db");
        //selectorArchivos.setFileFilter(fnef);
        //selectorArchivos.setMultiSelectionEnabled(false);
        selector.setFileFilter(fnef);
        selector.setCurrentDirectory(new File("./database"));
        selector.setMultiSelectionEnabled(false);
        if (selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File archivo = selector.getSelectedFile();
            try {
                String ruta = archivo.getCanonicalPath();
                this.lblRutaGlobal.setText(ruta);
                System.out.println(ruta);
                Constantes.rutaConexion = ruta;
                if (ruta.contains(".sqlite") || ruta.contains(".db")) {
                    this.archivoCorrecto = true;
                } else {
                    this.archivoCorrecto = false;
                }
            } catch (IOException ex) {
                this.archivoCorrecto = false;
                Logger.getLogger(vInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.probarConexion();
    }//GEN-LAST:event_rSButtonShapeIcon16ActionPerformed

    private void lblRutaGlobalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRutaGlobalMouseEntered

    }//GEN-LAST:event_lblRutaGlobalMouseEntered

    public void probarConexion() {
        if (this.archivoCorrecto) {
            JOptionPane.showMessageDialog(null, "¡Conexión correcta!", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "¡Conexión incorrecta!", "Alerta", JOptionPane.ERROR_MESSAGE);
        }
        this.btnIniciarSesion.setEnabled(this.archivoCorrecto);
        this.lblRutaGlobal.setText(Constantes.rutaConexion);
        this.lblRutaGlobal.updateUI();
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
            java.util.logging.Logger.getLogger(vInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShapeIcon btnCrearConfig;
    private RSMaterialComponent.RSButtonShapeIcon btnIniciarSesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblRutaGlobal;
    private RSMaterialComponent.RSButtonShapeIcon rSButtonShapeIcon16;
    private RSMaterialComponent.RSButtonShapeIcon rSButtonShapeIcon9;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente1;
    // End of variables declaration//GEN-END:variables
}