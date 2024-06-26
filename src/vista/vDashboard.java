/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import formularios.dMostrarInfo;
import sesion.Sesion;

/**
 *
 * @author vacev
 */
public class vDashboard extends javax.swing.JPanel {

    /**
     * Creates new form vDashboard
     */
    public vDashboard(Sesion sesion) {
        initComponents();
        this.txtBienvenida.setText("Bienvenido/a de nuevo " + sesion.usuario.getPersona().getNombres() + " " + sesion.usuario.getPersona().getApellidos());
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
        txtBienvenida = new javax.swing.JLabel();
        imagenPrincipal = new javax.swing.JLabel();
        btnBuscarCicloContable = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel2 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtBienvenida.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        txtBienvenida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtBienvenida.setText("BIENVENIDA");

        imagenPrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagenPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utils/img/portada.jpg"))); // NOI18N
        imagenPrincipal.setToolTipText("Ver Dashboard");
        imagenPrincipal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        imagenPrincipal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imagenPrincipal.setIconTextGap(1);

        btnBuscarCicloContable.setBackground(new java.awt.Color(33, 58, 86));
        btnBuscarCicloContable.setText("ACERCA DE");
        btnBuscarCicloContable.setToolTipText("MAS INFORMACIÓN");
        btnBuscarCicloContable.setBackgroundHover(new java.awt.Color(33, 68, 86));
        btnBuscarCicloContable.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBuscarCicloContable.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnBuscarCicloContable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBuscarCicloContable.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.HELP);
        btnBuscarCicloContable.setSizeIcon(18.0F);
        btnBuscarCicloContable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCicloContableActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SISTEMA CONTABLE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagenPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBienvenida, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBuscarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(imagenPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(44, 44, 44))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBuscarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addComponent(txtBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(399, 399, 399))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarCicloContableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCicloContableActionPerformed
        dMostrarInfo d = new dMostrarInfo(null, true, "", "INFORMACIÓN");
        d.setVisible(true);
    }//GEN-LAST:event_btnBuscarCicloContableActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShapeIcon btnBuscarCicloContable;
    public static javax.swing.JLabel imagenPrincipal;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txtBienvenida;
    // End of variables declaration//GEN-END:variables
}
