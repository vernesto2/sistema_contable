/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.Usuario;
import utils.constantes.CambiaPanel;

import rojerusan.RSPanelsSlider;
import servicios.ServicioUsuario;

import utils.constantes.Constantes;

/**
 *
 * @author vacev
 */
public class vPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form vPrincipal
     */

    private Usuario usuario;
    private ServicioUsuario _usuario;
    
    public vPrincipal(Usuario usuario, ServicioUsuario _usuario) {
        this._usuario = _usuario;
        if(usuario == null) {
            this.dispose();
            throw new IllegalAccessError("No puede iniciar sin usuario");
        }
        this.usuario = usuario;
        initComponents();
        this.iniciarVista();
        if (Constantes.configUsuario.getId_ciclo_contable() == -1) {
            this.txtConfigCicloContable.setText("NO SE HA SELECCIONADO NINGUN CICLO CONTABLE");
        } else {
            this.txtConfigCicloContable.setText(Constantes.configUsuario.nombreCicloYCatalogo());
        }
        this.txtNombreUsuario.setText(Constantes.usuario.getPersona().nombreCompleto());
        //this.txtNombreUsuario.setText(usuario.getPersona().nombreCompleto());
        this.imagenPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource(Constantes.configUsuario.getAvatar())));
    }

    
//    public vPrincipal(Usuario usuario) {
//        if(usuario == null) {
//            this.dispose();
//            throw new IllegalAccessError("No puede iniciar sin usuario");
//        }
//        initComponents();
//        this.iniciarVista();
//        if (Constantes.configUsuario.getId_ciclo_contable() == -1) {
//            this.txtConfigCicloContable.setText("NO SE HA SELECCIONADO NINGUN CICLO CONTABLE");
//        } else {
//            this.txtConfigCicloContable.setText(Constantes.configUsuario.nombreCicloYCatalogo());
//        }
//        this.txtNombreUsuario.setText(usuario.getPersona().nombreCompleto());
//        this.imagenPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource(Constantes.configUsuario.getAvatar())));
//    }
//    

    public vPrincipal() {
        initComponents();
        this.iniciarVista();
        if (Constantes.configUsuario.getId_ciclo_contable() == -1) {
            this.txtConfigCicloContable.setText("NO SE HA SELECCIONADO NINGUN CICLO CONTABLE");
        } else {
            this.txtConfigCicloContable.setText(Constantes.configUsuario.nombreCicloYCatalogo());
        }
        this.txtNombreUsuario.setText(Constantes.usuario.getPersona().nombreCompleto());
        this.imagenPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource(Constantes.configUsuario.getAvatar())));
    }
    
    public void iniciarVista() {
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/utils/icon/user.png")));
        
        this.btnLibroDiario.setSelected(true);
        new CambiaPanel(pnl, new vLibroDiario());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBase = new javax.swing.JPanel();
        pnl = new javax.swing.JPanel();
        sidebar = new javax.swing.JPanel();
        rSLabelFecha1 = new rojeru_san.rsdate.RSLabelFecha();
        rSLabelHora1 = new rojeru_san.rsdate.RSLabelHora();
        btnEstadoResultado = new RSMaterialComponent.RSButtonShapeIcon();
        btnLibroDiario = new RSMaterialComponent.RSButtonShapeIcon();
        btnLibroMayor = new RSMaterialComponent.RSButtonShapeIcon();
        btnConfigUsuario = new RSMaterialComponent.RSButtonShapeIcon();
        rSButtonShapeIcon1 = new RSMaterialComponent.RSButtonShapeIcon();
        imagenPrincipal = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JLabel();
        btnBalanzaComprobacion = new RSMaterialComponent.RSButtonShapeIcon();
        btnBalanceGeneral = new RSMaterialComponent.RSButtonShapeIcon();
        btnFlujoEfectivo = new RSMaterialComponent.RSButtonShapeIcon();
        btnCambiosPatrimonio = new RSMaterialComponent.RSButtonShapeIcon();
        btnCicloContable = new RSMaterialComponent.RSButtonShapeIcon();
        topbar = new javax.swing.JPanel();
        txtConfigCicloContable = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        pnlBase.setBackground(new java.awt.Color(255, 255, 255));

        pnl.setLayout(new javax.swing.BoxLayout(pnl, javax.swing.BoxLayout.LINE_AXIS));

        sidebar.setBackground(new java.awt.Color(102, 102, 102));

        rSLabelFecha1.setForeground(new java.awt.Color(0, 0, 0));
        rSLabelFecha1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        rSLabelHora1.setForeground(new java.awt.Color(0, 0, 0));
        rSLabelHora1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        btnEstadoResultado.setBackground(new java.awt.Color(73, 120, 248));
        btnEstadoResultado.setText("Estado de resultados");
        btnEstadoResultado.setBackgroundHover(new java.awt.Color(73, 65, 248));
        btnEstadoResultado.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.TURNED_IN_NOT);
        btnEstadoResultado.setSizeIcon(25.0F);
        btnEstadoResultado.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        btnEstadoResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadoResultadoActionPerformed(evt);
            }
        });

        btnLibroDiario.setBackground(new java.awt.Color(73, 120, 248));
        btnLibroDiario.setText("Libro diario");
        btnLibroDiario.setBackgroundHover(new java.awt.Color(73, 65, 248));
        btnLibroDiario.setSizeIcon(25.0F);
        btnLibroDiario.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        btnLibroDiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLibroDiarioActionPerformed(evt);
            }
        });

        btnLibroMayor.setBackground(new java.awt.Color(73, 120, 248));
        btnLibroMayor.setText("Libro mayor");
        btnLibroMayor.setBackgroundHover(new java.awt.Color(73, 65, 248));
        btnLibroMayor.setSizeIcon(25.0F);
        btnLibroMayor.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        btnLibroMayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLibroMayorActionPerformed(evt);
            }
        });

        btnConfigUsuario.setBackground(new java.awt.Color(73, 120, 248));
        btnConfigUsuario.setText("Config. usuario");
        btnConfigUsuario.setToolTipText("Configuración del usuario");
        btnConfigUsuario.setBackgroundHover(new java.awt.Color(73, 65, 248));
        btnConfigUsuario.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.WIDGETS);
        btnConfigUsuario.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        btnConfigUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigUsuarioActionPerformed(evt);
            }
        });

        rSButtonShapeIcon1.setBackground(new java.awt.Color(213, 73, 24));
        rSButtonShapeIcon1.setText("CERRAR SESIÓN");
        rSButtonShapeIcon1.setBackgroundHover(new java.awt.Color(213, 12, 10));
        rSButtonShapeIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EXIT_TO_APP);
        rSButtonShapeIcon1.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        rSButtonShapeIcon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShapeIcon1ActionPerformed(evt);
            }
        });

        imagenPrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagenPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utils/avatar/avatar1.png"))); // NOI18N
        imagenPrincipal.setToolTipText("");
        imagenPrincipal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imagenPrincipal.setIconTextGap(1);

        txtNombreUsuario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtNombreUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNombreUsuario.setText("Nombre Usuario");

        btnBalanzaComprobacion.setBackground(new java.awt.Color(73, 120, 248));
        btnBalanzaComprobacion.setText("Balanza de comprobación");
        btnBalanzaComprobacion.setToolTipText("Balanza de comprobación");
        btnBalanzaComprobacion.setBackgroundHover(new java.awt.Color(73, 65, 248));
        btnBalanzaComprobacion.setSizeIcon(25.0F);
        btnBalanzaComprobacion.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        btnBalanzaComprobacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBalanzaComprobacionActionPerformed(evt);
            }
        });

        btnBalanceGeneral.setBackground(new java.awt.Color(73, 120, 248));
        btnBalanceGeneral.setText("Balance general");
        btnBalanceGeneral.setBackgroundHover(new java.awt.Color(73, 65, 248));
        btnBalanceGeneral.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.TURNED_IN_NOT);
        btnBalanceGeneral.setSizeIcon(25.0F);
        btnBalanceGeneral.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        btnBalanceGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBalanceGeneralActionPerformed(evt);
            }
        });

        btnFlujoEfectivo.setBackground(new java.awt.Color(73, 120, 248));
        btnFlujoEfectivo.setText("Estado de flujo de efectivo");
        btnFlujoEfectivo.setToolTipText("Estado de flujo de efectivo");
        btnFlujoEfectivo.setBackgroundHover(new java.awt.Color(73, 65, 248));
        btnFlujoEfectivo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.TURNED_IN_NOT);
        btnFlujoEfectivo.setSizeIcon(25.0F);
        btnFlujoEfectivo.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        btnFlujoEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFlujoEfectivoActionPerformed(evt);
            }
        });

        btnCambiosPatrimonio.setBackground(new java.awt.Color(73, 120, 248));
        btnCambiosPatrimonio.setText("Estado de cambios en el patrimonio");
        btnCambiosPatrimonio.setToolTipText("Estado de cambios en el patrimonio");
        btnCambiosPatrimonio.setBackgroundHover(new java.awt.Color(73, 65, 248));
        btnCambiosPatrimonio.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.TURNED_IN_NOT);
        btnCambiosPatrimonio.setSizeIcon(25.0F);
        btnCambiosPatrimonio.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        btnCambiosPatrimonio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiosPatrimonioActionPerformed(evt);
            }
        });

        btnCicloContable.setBackground(new java.awt.Color(73, 120, 248));
        btnCicloContable.setText("Config. contabilidad");
        btnCicloContable.setToolTipText("Configuración de contabilidad (Ciclos contables, Catalogos, etc.)");
        btnCicloContable.setBackgroundHover(new java.awt.Color(73, 65, 248));
        btnCicloContable.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.TURNED_IN_NOT);
        btnCicloContable.setSizeIcon(25.0F);
        btnCicloContable.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        btnCicloContable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCicloContableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSLabelHora1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rSLabelFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombreUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidebarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(imagenPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)))
                .addContainerGap())
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConfigUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonShapeIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLibroDiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEstadoResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLibroMayor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBalanzaComprobacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBalanceGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFlujoEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCambiosPatrimonio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        sidebarLayout.setVerticalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidebarLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(imagenPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreUsuario)
                .addGap(14, 14, 14)
                .addComponent(rSLabelFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnLibroDiario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLibroMayor, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBalanzaComprobacion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEstadoResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBalanceGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFlujoEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCambiosPatrimonio, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConfigUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButtonShapeIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        topbar.setBackground(new java.awt.Color(204, 204, 204));

        txtConfigCicloContable.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtConfigCicloContable.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtConfigCicloContable.setText("CONTABILIDAD BANCARIA");
        txtConfigCicloContable.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout topbarLayout = new javax.swing.GroupLayout(topbar);
        topbar.setLayout(topbarLayout);
        topbarLayout.setHorizontalGroup(
            topbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topbarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtConfigCicloContable, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
                .addContainerGap())
        );
        topbarLayout.setVerticalGroup(
            topbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topbarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtConfigCicloContable, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlBaseLayout = new javax.swing.GroupLayout(pnlBase);
        pnlBase.setLayout(pnlBaseLayout);
        pnlBaseLayout.setHorizontalGroup(
            pnlBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBaseLayout.createSequentialGroup()
                .addComponent(sidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(pnlBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(topbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlBaseLayout.setVerticalGroup(
            pnlBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlBaseLayout.createSequentialGroup()
                .addComponent(topbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1079, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void seleccionarBoton() {
        
        this.btnLibroDiario.setSelected(false);
        this.btnLibroMayor.setSelected(false);
        this.btnBalanzaComprobacion.setSelected(false);
        this.btnEstadoResultado.setSelected(false);
        this.btnBalanceGeneral.setSelected(false);
        this.btnFlujoEfectivo.setSelected(false);
        this.btnCambiosPatrimonio.setSelected(false);
        this.btnCicloContable.setSelected(false);
        this.btnConfigUsuario.setSelected(false);
        
    }
    
    private void btnEstadoResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadoResultadoActionPerformed
        this.seleccionarBoton();
        if (!this.btnEstadoResultado.isSelected()) {
            this.btnEstadoResultado.setSelected(true);
            new CambiaPanel(pnl, new vLibroDiario());
        }
    }//GEN-LAST:event_btnEstadoResultadoActionPerformed

    private void btnLibroDiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLibroDiarioActionPerformed
        this.seleccionarBoton();
        if (!this.btnLibroDiario.isSelected()) {
            this.btnLibroDiario.setSelected(true);
            new CambiaPanel(pnl, new vLibroDiario());
        }
    }//GEN-LAST:event_btnLibroDiarioActionPerformed

    private void btnLibroMayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLibroMayorActionPerformed
        this.seleccionarBoton();
        if (!this.btnLibroMayor.isSelected()) {
            this.btnLibroMayor.setSelected(true);
        }
    }//GEN-LAST:event_btnLibroMayorActionPerformed

    private void btnConfigUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigUsuarioActionPerformed
        this.seleccionarBoton();
        if (!this.btnConfigUsuario.isSelected()) {
            this.btnConfigUsuario.setSelected(true);
            new CambiaPanel(pnl, new vConfiguracion(usuario, _usuario));
        }
    }//GEN-LAST:event_btnConfigUsuarioActionPerformed

    private void rSButtonShapeIcon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShapeIcon1ActionPerformed
        //new utils.alertas.AlertError(this, true, "OCURRIO UN PROBLEM").setVisible(true);
        int salida = JOptionPane.showConfirmDialog(null, "¿Esta seguro de continuar?", "¡CERRAR SESIÓN!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        //System.out.println(salida);
        if (salida == 0) {
            vLogin login = new vLogin();
            login.setVisible(true);
            this.dispose();
        }
        /**/
    }//GEN-LAST:event_rSButtonShapeIcon1ActionPerformed

    private void btnBalanzaComprobacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBalanzaComprobacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBalanzaComprobacionActionPerformed

    private void btnBalanceGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBalanceGeneralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBalanceGeneralActionPerformed

    private void btnFlujoEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFlujoEfectivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFlujoEfectivoActionPerformed

    private void btnCambiosPatrimonioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiosPatrimonioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCambiosPatrimonioActionPerformed

    private void btnCicloContableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCicloContableActionPerformed
        this.seleccionarBoton();
        if (!this.btnCicloContable.isSelected()) {
            this.btnCicloContable.setSelected(true);
            new CambiaPanel(pnl, new vConfigContabilidad());
        }
    }//GEN-LAST:event_btnCicloContableActionPerformed

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
            java.util.logging.Logger.getLogger(vPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShapeIcon btnBalanceGeneral;
    private RSMaterialComponent.RSButtonShapeIcon btnBalanzaComprobacion;
    private RSMaterialComponent.RSButtonShapeIcon btnCambiosPatrimonio;
    private RSMaterialComponent.RSButtonShapeIcon btnCicloContable;
    private RSMaterialComponent.RSButtonShapeIcon btnConfigUsuario;
    private RSMaterialComponent.RSButtonShapeIcon btnEstadoResultado;
    private RSMaterialComponent.RSButtonShapeIcon btnFlujoEfectivo;
    private RSMaterialComponent.RSButtonShapeIcon btnLibroDiario;
    private RSMaterialComponent.RSButtonShapeIcon btnLibroMayor;
    private javax.swing.JLabel imagenPrincipal;
    private javax.swing.JPanel pnl;
    private javax.swing.JPanel pnlBase;
    private RSMaterialComponent.RSButtonShapeIcon rSButtonShapeIcon1;
    private rojeru_san.rsdate.RSLabelFecha rSLabelFecha1;
    private rojeru_san.rsdate.RSLabelHora rSLabelHora1;
    private javax.swing.JPanel sidebar;
    private javax.swing.JPanel topbar;
    public static javax.swing.JLabel txtConfigCicloContable;
    private javax.swing.JLabel txtNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
