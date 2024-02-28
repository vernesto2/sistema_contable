/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.awt.Color;
import java.awt.Toolkit;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.ConfiguracionUsuario;
import modelo.Usuario;
import servicios.ServicioConfigUsuario;
import servicios.ServicioUsuario;
import sesion.Sesion;
import utils.UtileriaVista;
import utils.constantes.Constantes;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class vLogin extends javax.swing.JFrame {

    /**
     * Creates new form vLogin
     */
    public char pass;
    Sesion sesion;
    ServicioUsuario _usuario;
    public vLogin(Sesion sesion) {
        this.sesion = sesion;
        _usuario = new ServicioUsuario(sesion.rutaConexion);
        initComponents();
        this.iniciarVista();
    }

    public void iniciarVista() {
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("LOGIN");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/utils/icon/login.png")));
        this.txtUsuario.setText("ac14072");
        this.txtClave.setText("1234");
        
        RespuestaGeneral resp = _usuario.sePuedeCrearAlumno();
        if(resp.esExitosa()) {
            lblSePuedeCrearAlumno.setText("Este archivo soporta la creación de un alumno");
            btnCrearAlumno.setVisible(true);
        } else {
            lblSePuedeCrearAlumno.setText("Este archivo ya pertenece a un alumno");
            btnCrearAlumno.setVisible(false);
        }
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
        txtClave = new RSMaterialComponent.RSPasswordMaterial();
        checkMostrarClave = new rojerusan.RSCheckBox();
        btnIngresar = new RSMaterialComponent.RSButtonShapeIcon();
        btnCancelar = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        rSButtonShapeIcon9 = new RSMaterialComponent.RSButtonShapeIcon();
        btnOlvidoContraseña = new rojeru_san.RSButton();
        btnCrearAlumno = new RSMaterialComponent.RSButtonShapeIcon();
        lblSePuedeCrearAlumno = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        rSPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        rSPanel1.setForeground(new java.awt.Color(255, 255, 255));
        rSPanel1.setColorBackground(new java.awt.Color(255, 255, 255));

        txtUsuario.setForeground(new java.awt.Color(0, 0, 0));
        txtUsuario.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtUsuario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtUsuario.setPhColor(new java.awt.Color(0, 0, 0));
        txtUsuario.setPlaceholder("Digite el usuario..");
        txtUsuario.setSelectionColor(new java.awt.Color(0, 0, 0));

        txtClave.setForeground(new java.awt.Color(0, 0, 0));
        txtClave.setActionCommand("<Not Set>");
        txtClave.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtClave.setDropMode(javax.swing.DropMode.INSERT);
        txtClave.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtClave.setPhColor(new java.awt.Color(0, 0, 0));
        txtClave.setPlaceholder("Digite la contraseña..");
        txtClave.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtClave.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);

        checkMostrarClave.setForeground(new java.awt.Color(0, 0, 0));
        checkMostrarClave.setText("Mostrar Contraseña");
        checkMostrarClave.setColorCheck(new java.awt.Color(0, 0, 0));
        checkMostrarClave.setColorUnCheck(new java.awt.Color(0, 0, 0));
        checkMostrarClave.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        checkMostrarClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkMostrarClaveActionPerformed(evt);
            }
        });

        btnIngresar.setBackground(new java.awt.Color(33, 58, 86));
        btnIngresar.setText("INGRESAR");
        btnIngresar.setBackgroundHover(new java.awt.Color(33, 68, 86));
        btnIngresar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnIngresar.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnIngresar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CACHED);
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(251, 205, 6));
        btnCancelar.setText("CANCELAR");
        btnCancelar.setBackgroundHover(new java.awt.Color(251, 174, 6));
        btnCancelar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCancelar.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnCancelar.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnCancelar.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnCancelar.setForegroundText(new java.awt.Color(0, 0, 0));
        btnCancelar.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnCancelar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utils/img/login.png"))); // NOI18N
        jLabel1.setToolTipText("");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setIconTextGap(1);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("INICIAR SESIÓN");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        rSButtonShapeIcon9.setBackground(new java.awt.Color(33, 58, 86));
        rSButtonShapeIcon9.setToolTipText("SALIR DE LA APLICACIÓN");
        rSButtonShapeIcon9.setBackgroundHover(new java.awt.Color(33, 68, 86));
        rSButtonShapeIcon9.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.RECT);
        rSButtonShapeIcon9.setHideActionText(true);
        rSButtonShapeIcon9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSButtonShapeIcon9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonShapeIcon9.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLOSE);
        rSButtonShapeIcon9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShapeIcon9ActionPerformed(evt);
            }
        });

        btnOlvidoContraseña.setBackground(new java.awt.Color(255, 255, 255));
        btnOlvidoContraseña.setText("¿Olvido su contraseña?");
        btnOlvidoContraseña.setColorHover(new java.awt.Color(243, 240, 238));
        btnOlvidoContraseña.setColorText(new java.awt.Color(51, 51, 51));
        btnOlvidoContraseña.setColorTextHover(new java.awt.Color(0, 0, 0));
        btnOlvidoContraseña.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        btnOlvidoContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOlvidoContraseñaActionPerformed(evt);
            }
        });

        btnCrearAlumno.setBackground(new java.awt.Color(251, 205, 6));
        btnCrearAlumno.setText("Crear alumno");
        btnCrearAlumno.setToolTipText("");
        btnCrearAlumno.setBackgroundHover(new java.awt.Color(251, 174, 6));
        btnCrearAlumno.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCrearAlumno.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnCrearAlumno.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnCrearAlumno.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnCrearAlumno.setForegroundText(new java.awt.Color(0, 0, 0));
        btnCrearAlumno.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnCrearAlumno.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.NEW_RELEASES);
        btnCrearAlumno.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        btnCrearAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearAlumnoActionPerformed(evt);
            }
        });

        lblSePuedeCrearAlumno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSePuedeCrearAlumno.setText("jLabel2");

        javax.swing.GroupLayout rSPanel1Layout = new javax.swing.GroupLayout(rSPanel1);
        rSPanel1.setLayout(rSPanel1Layout);
        rSPanel1Layout.setHorizontalGroup(
            rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkMostrarClave, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71))
            .addGroup(rSPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(93, 93, 93)
                        .addComponent(rSButtonShapeIcon9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rSPanel1Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(btnOlvidoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rSPanel1Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(btnCrearAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rSPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblSePuedeCrearAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(rSPanel1Layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(checkMostrarClave, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnOlvidoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSePuedeCrearAlumno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(btnCrearAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
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

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed

        String carnet = txtUsuario.getText();
        char[] clave = txtClave.getPassword();
        
        RespuestaGeneral rgUsuario = _usuario.obtenerPorCarnet(carnet);
        if (rgUsuario.esFallida()) {
            JOptionPane.showMessageDialog(this, rgUsuario.getMensaje(), "Mensaje", UtileriaVista.devolverCodigoMensaje(rgUsuario));
            return;
        }
        
        Usuario usuario = (Usuario) rgUsuario.getDatos();
        RespuestaGeneral rgValidar = _usuario.coinciden(clave, usuario.getClave(), usuario.getSalt());
        
        if( rgValidar.esFallida() ) {
            JOptionPane.showMessageDialog(this, rgUsuario.getMensaje(), "Mensaje", UtileriaVista.devolverCodigoMensaje(rgValidar));
            return;
        }
        
        Boolean coinciden = (Boolean) rgValidar.getDatos();
        
        if(coinciden == false) {
            JOptionPane.showMessageDialog(this, "Las claves no coinciden", "Mensaje", UtileriaVista.devolverCodigoMensaje(rgValidar));
            return;
        }
        
        // buscamos si se encuentra creada una configuracion de usuario
        // de lo contrario procederemos a crear una con el usuario logeado
        ConfiguracionUsuario cConfigUsuario = this.setearInfoConfiguracionGeneralUsuario(usuario);
        if(cConfigUsuario != null) {
            Sesion sesion = new Sesion(usuario, cConfigUsuario, this.sesion.rutaConexion);
            this.sesion = sesion;
            vPrincipal principal = new vPrincipal(_usuario, sesion);
            principal.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnIngresarActionPerformed

    public ConfiguracionUsuario setearInfoConfiguracionGeneralUsuario(Usuario usuario) {
        
        ServicioConfigUsuario _configUsuario = new ServicioConfigUsuario(sesion.rutaConexion);
        
        ArrayList<ConfiguracionUsuario> listaConfigUsuario = new ArrayList<>();
        RespuestaGeneral rg = _configUsuario.obtenerPorIdUsuario(usuario.getId());
        if (rg.esExitosa()) {
            listaConfigUsuario = (ArrayList<ConfiguracionUsuario>)rg.getDatos();
            ConfiguracionUsuario cUsuario = new ConfiguracionUsuario();
            cUsuario.setId_usuario(usuario.getId());
            if (listaConfigUsuario.isEmpty()) {
                // creamos y guardamos una configuracion
                RespuestaGeneral rg1 = _configUsuario.insertar(cUsuario);
                if (rg1.esExitosa()) {
                    cUsuario.setId(Integer.parseInt(rg1.getDatos().toString()));
                } else {
                    String mensaje = !rg1.getMensaje().equals("") ? rg1.getMensaje() : "No se pudo guardar la configuración de usuario";
                    JOptionPane.showMessageDialog(this, mensaje, "Mensaje", UtileriaVista.devolverCodigoMensaje(rg1));
                    return null;
                }
                listaConfigUsuario.add(cUsuario);
            }
            cUsuario = listaConfigUsuario.get(0);
            
            // seteamos la informacion obtenida a la constante
            return cUsuario;
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo recuperar la configuración de usuario", "Mensaje", UtileriaVista.devolverCodigoMensaje(rg));
            return null;
        }
    }
    
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        vInicio inicio = new vInicio();
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void checkMostrarClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkMostrarClaveActionPerformed
        char character = this.txtClave.getEchoChar();
        if (this.checkMostrarClave.isSelected()) {
            this.pass = character;
            this.txtClave.setEchoChar((char) 0);
            this.txtClave.requestFocus();
        } else {
            this.txtClave.setEchoChar((char) this.pass);
            this.txtClave.requestFocus();
        }
    }//GEN-LAST:event_checkMostrarClaveActionPerformed

    private void rSButtonShapeIcon9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShapeIcon9ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_rSButtonShapeIcon9ActionPerformed

    private void btnOlvidoContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOlvidoContraseñaActionPerformed
        this.btnOlvidoContraseña.disable();
        vRecuperacion recup = new vRecuperacion();
        recup.setVisible(true);
        this.dispose();
        this.btnOlvidoContraseña.enable();
    }//GEN-LAST:event_btnOlvidoContraseñaActionPerformed

    private void btnCrearAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearAlumnoActionPerformed
        //false hará que la vista intente crear alumno en vez de restaurar a la configuración inicial
        final boolean esRestaurar = false;
        vRestaurarConfiguracion config = new vRestaurarConfiguracion(sesion, esRestaurar);
        config.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCrearAlumnoActionPerformed

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
                Sesion sesion = new Sesion(null, null, Constantes.rutaConexion);
                new vLogin(sesion).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShapeIcon btnCancelar;
    private RSMaterialComponent.RSButtonShapeIcon btnCrearAlumno;
    private RSMaterialComponent.RSButtonShapeIcon btnIngresar;
    private rojeru_san.RSButton btnOlvidoContraseña;
    private rojerusan.RSCheckBox checkMostrarClave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblSePuedeCrearAlumno;
    private RSMaterialComponent.RSButtonShapeIcon rSButtonShapeIcon9;
    private necesario.RSPanel rSPanel1;
    private RSMaterialComponent.RSPasswordMaterial txtClave;
    private RSMaterialComponent.RSTextFieldMaterial txtUsuario;
    // End of variables declaration//GEN-END:variables
}
