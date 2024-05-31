/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.Usuario;
import utils.constantes.CambiaPanel;

import servicios.ServicioUsuario;
import sesion.Sesion;
import utils.UtileriaVista;

/**
 *
 * @author vacev
 */
public class vPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form vPrincipal
     */
    private ServicioUsuario _usuario;
    private Sesion sesion;
    private boolean esVentanaSecundaria;
    public vPrincipal(ServicioUsuario _usuario, Sesion sesion, boolean esVentanaSecundaria) {
        initComponents();
        this._usuario = _usuario;
        this.esVentanaSecundaria = esVentanaSecundaria;
        if (sesion.usuario == null) {
            this.dispose();
            throw new IllegalAccessError("No puede iniciar sin usuario");
        }
        this.sesion = sesion;
        
        this.iniciarVista();
        UtileriaVista.actualizarPerfil(sesion);
        vPrincipal.imagenPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource(sesion.configUsuario.getAvatar())));
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
        if (sesion.configUsuario.getId_ciclo_contable() == -1) {
            this.txtConfigCicloContable.setText("NO SE HA SELECCIONADO NINGUN CICLO CONTABLE");
        } else {
            this.txtConfigCicloContable.setText(sesion.configUsuario.nombreCicloYCatalogo());
        }
        this.txtNombreUsuario.setText(sesion.usuario.getPersona().nombreCompleto());
        this.imagenPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource(sesion.configUsuario.getAvatar())));
        
        
    }

    public void iniciarVista() {
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
        this.setTitle("SISTEMA CONTABLE");
        this.setExtendedState(this.MAXIMIZED_BOTH);
        JFrame ventana = this;
        //evitar que la ventana se cierre por defecto
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                //confirmar al salir
                int opt = JOptionPane.showConfirmDialog(ventana,"¿Realmente desea salir?", "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (opt == JOptionPane.YES_OPTION) {
                    if(esVentanaSecundaria == true) {
                        ventana.dispose();
                    } else {
                        System.exit(0);
                    }
                } else {
                    ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/utils/icon/user.png")));
        if (sesion.esAlumno()) {
            btnSeleccionarArchivoAlumno.setEnabled(false);
            this.setTitle(sesion.rutaConexion + " (Alumnos) ");
        } else {
            btnSeleccionarArchivoAlumno.setEnabled(true);
            if(esVentanaSecundaria == true) {
                btnSeleccionarArchivoAlumno.setEnabled(false);
            }
            this.setTitle(sesion.rutaConexion + " (Docente) ");
        }
        
        new CambiaPanel(pnl, new vDashboard(this.sesion));
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
        jScrollPane1 = new javax.swing.JScrollPane();
        pnl = new javax.swing.JPanel();
        sidebar = new javax.swing.JPanel();
        rSLabelFecha1 = new rojeru_san.rsdate.RSLabelFecha();
        rSLabelHora1 = new rojeru_san.rsdate.RSLabelHora();
        btnEstadoResultado = new RSMaterialComponent.RSButtonShapeIcon();
        btnLibroDiario = new RSMaterialComponent.RSButtonShapeIcon();
        btnLibroMayor = new RSMaterialComponent.RSButtonShapeIcon();
        btnConfigUsuario = new RSMaterialComponent.RSButtonShapeIcon();
        btnCerrarSesion = new RSMaterialComponent.RSButtonShapeIcon();
        imagenPrincipal = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JLabel();
        btnBalanzaComprobacion = new RSMaterialComponent.RSButtonShapeIcon();
        btnBalanceGeneral = new RSMaterialComponent.RSButtonShapeIcon();
        btnCicloContable = new RSMaterialComponent.RSButtonShapeIcon();
        topbar = new javax.swing.JPanel();
        txtConfigCicloContable = new javax.swing.JLabel();
        btnGuardarCicloContable2 = new RSMaterialComponent.RSButtonShapeIcon();
        btnGuardarCicloContable3 = new RSMaterialComponent.RSButtonShapeIcon();
        btnSeleccionarArchivoAlumno = new RSMaterialComponent.RSButtonShapeIcon();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        pnlBase.setBackground(new java.awt.Color(255, 255, 255));

        pnl.setLayout(new javax.swing.BoxLayout(pnl, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane1.setViewportView(pnl);

        sidebar.setBackground(new java.awt.Color(102, 102, 102));

        rSLabelFecha1.setForeground(new java.awt.Color(0, 0, 0));
        rSLabelFecha1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        rSLabelHora1.setForeground(new java.awt.Color(0, 0, 0));
        rSLabelHora1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        btnEstadoResultado.setBackground(new java.awt.Color(73, 120, 248));
        btnEstadoResultado.setText("Estado de resultados");
        btnEstadoResultado.setBackgroundHover(new java.awt.Color(73, 65, 248));
        btnEstadoResultado.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
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
        btnLibroDiario.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LIST);
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
        btnLibroMayor.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ACCOUNT_BALANCE);
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

        btnCerrarSesion.setBackground(new java.awt.Color(213, 73, 24));
        btnCerrarSesion.setText("CERRAR SESIÓN");
        btnCerrarSesion.setBackgroundHover(new java.awt.Color(213, 12, 10));
        btnCerrarSesion.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EXIT_TO_APP);
        btnCerrarSesion.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        imagenPrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagenPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utils/avatar/avatar1.png"))); // NOI18N
        imagenPrincipal.setToolTipText("Ver Dashboard");
        imagenPrincipal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        imagenPrincipal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imagenPrincipal.setIconTextGap(1);
        imagenPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagenPrincipalMouseClicked(evt);
            }
        });

        txtNombreUsuario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtNombreUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNombreUsuario.setText("Nombre Usuario");

        btnBalanzaComprobacion.setBackground(new java.awt.Color(73, 120, 248));
        btnBalanzaComprobacion.setText("Balanza de comprobación");
        btnBalanzaComprobacion.setToolTipText("Balanza de comprobación");
        btnBalanzaComprobacion.setBackgroundHover(new java.awt.Color(73, 65, 248));
        btnBalanzaComprobacion.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LIBRARY_BOOKS);
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
        btnBalanceGeneral.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ASSESSMENT);
        btnBalanceGeneral.setSizeIcon(25.0F);
        btnBalanceGeneral.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        btnBalanceGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBalanceGeneralActionPerformed(evt);
            }
        });

        btnCicloContable.setBackground(new java.awt.Color(73, 120, 248));
        btnCicloContable.setText("Config. contabilidad");
        btnCicloContable.setToolTipText("Configuración de contabilidad (Ciclos contables, Catalogos, etc.)");
        btnCicloContable.setBackgroundHover(new java.awt.Color(73, 65, 248));
        btnCicloContable.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BUILD);
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
                    .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLibroDiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEstadoResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLibroMayor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBalanzaComprobacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBalanceGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(18, 18, 18)
                .addComponent(btnLibroDiario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLibroMayor, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBalanzaComprobacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEstadoResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBalanceGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(btnCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConfigUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        topbar.setBackground(new java.awt.Color(102, 102, 102));

        txtConfigCicloContable.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtConfigCicloContable.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtConfigCicloContable.setText("CONTABILIDAD BANCARIA");
        txtConfigCicloContable.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btnGuardarCicloContable2.setBackground(new java.awt.Color(33, 58, 86));
        btnGuardarCicloContable2.setToolTipText("OCULTAR / VER MENU LATERAL");
        btnGuardarCicloContable2.setBackgroundHover(new java.awt.Color(33, 84, 86));
        btnGuardarCicloContable2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuardarCicloContable2.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.RECT);
        btnGuardarCicloContable2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGuardarCicloContable2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardarCicloContable2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MENU);
        btnGuardarCicloContable2.setSizeIcon(25.0F);
        btnGuardarCicloContable2.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        btnGuardarCicloContable2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCicloContable2ActionPerformed(evt);
            }
        });

        btnGuardarCicloContable3.setBackground(new java.awt.Color(33, 58, 86));
        btnGuardarCicloContable3.setToolTipText("VER INICIO");
        btnGuardarCicloContable3.setBackgroundHover(new java.awt.Color(33, 84, 86));
        btnGuardarCicloContable3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuardarCicloContable3.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.RECT);
        btnGuardarCicloContable3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGuardarCicloContable3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardarCicloContable3.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.HOME);
        btnGuardarCicloContable3.setSizeIcon(25.0F);
        btnGuardarCicloContable3.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        btnGuardarCicloContable3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCicloContable3ActionPerformed(evt);
            }
        });

        btnSeleccionarArchivoAlumno.setBackground(new java.awt.Color(33, 58, 86));
        btnSeleccionarArchivoAlumno.setToolTipText("ABRIR OTRO ARCHIVO");
        btnSeleccionarArchivoAlumno.setBackgroundHover(new java.awt.Color(33, 84, 86));
        btnSeleccionarArchivoAlumno.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSeleccionarArchivoAlumno.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.RECT);
        btnSeleccionarArchivoAlumno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSeleccionarArchivoAlumno.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSeleccionarArchivoAlumno.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.FOLDER);
        btnSeleccionarArchivoAlumno.setSizeIcon(25.0F);
        btnSeleccionarArchivoAlumno.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        btnSeleccionarArchivoAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarArchivoAlumnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topbarLayout = new javax.swing.GroupLayout(topbar);
        topbar.setLayout(topbarLayout);
        topbarLayout.setHorizontalGroup(
            topbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topbarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardarCicloContable2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarCicloContable3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionarArchivoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(txtConfigCicloContable, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                .addContainerGap())
        );
        topbarLayout.setVerticalGroup(
            topbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topbarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(topbarLayout.createSequentialGroup()
                        .addComponent(btnGuardarCicloContable2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addComponent(txtConfigCicloContable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardarCicloContable3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnSeleccionarArchivoAlumno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout pnlBaseLayout = new javax.swing.GroupLayout(pnlBase);
        pnlBase.setLayout(pnlBaseLayout);
        pnlBaseLayout.setHorizontalGroup(
            pnlBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBaseLayout.createSequentialGroup()
                .addComponent(sidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)))
        );
        pnlBaseLayout.setVerticalGroup(
            pnlBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlBaseLayout.createSequentialGroup()
                .addComponent(topbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1062, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 667, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void irADashboar() {
        this.seleccionarBoton();
        new CambiaPanel(pnl, new vDashboard(this.sesion));
    }

    public void seleccionarBoton() {

        this.btnLibroDiario.setSelected(false);
        this.btnLibroMayor.setSelected(false);
        this.btnBalanzaComprobacion.setSelected(false);
        this.btnEstadoResultado.setSelected(false);
        this.btnBalanceGeneral.setSelected(false);
//        this.btnFlujoEfectivo.setSelected(false);
//        this.btnCambiosPatrimonio.setSelected(false);
        this.btnCicloContable.setSelected(false);
        this.btnConfigUsuario.setSelected(false);

    }

    private void btnEstadoResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadoResultadoActionPerformed
        this.seleccionarBoton();
        if (!this.btnEstadoResultado.isSelected()) {
            this.btnEstadoResultado.setSelected(true);
            new CambiaPanel(pnl, new vEstadoResultados(sesion));
        }
    }//GEN-LAST:event_btnEstadoResultadoActionPerformed

    private void btnLibroDiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLibroDiarioActionPerformed
        this.seleccionarBoton();
        if (!this.btnLibroDiario.isSelected()) {
            this.btnLibroDiario.setSelected(true);
            new CambiaPanel(pnl, new vLibroDiario(sesion));
        }
    }//GEN-LAST:event_btnLibroDiarioActionPerformed

    private void btnLibroMayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLibroMayorActionPerformed
        this.seleccionarBoton();
        if (!this.btnLibroMayor.isSelected()) {
            this.btnLibroMayor.setSelected(true);
            new CambiaPanel(pnl, new vLibroMayor(sesion));
        }
    }//GEN-LAST:event_btnLibroMayorActionPerformed

    private void btnConfigUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigUsuarioActionPerformed
        this.seleccionarBoton();
        if (!this.btnConfigUsuario.isSelected()) {
            this.btnConfigUsuario.setSelected(true);
            new CambiaPanel(pnl, new vConfiguracion(this.sesion, _usuario));
        }
    }//GEN-LAST:event_btnConfigUsuarioActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        //new utils.alertas.AlertError(this, true, "OCURRIO UN PROBLEM").setVisible(true);
        int salida = JOptionPane.showConfirmDialog(this, "¿Esta seguro de continuar?", "¡CERRAR SESIÓN!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        //System.out.println(salida);
        if (salida == 0) {

            vLogin login = new vLogin(new Sesion(null, null, sesion.rutaConexion));
            login.setVisible(true);
            this.dispose();
        }
        /**/
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnBalanzaComprobacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBalanzaComprobacionActionPerformed
        this.seleccionarBoton();
        if (!this.btnBalanzaComprobacion.isSelected()) {
            this.btnBalanzaComprobacion.setSelected(true);
            new CambiaPanel(pnl, new vBalanzaComprobacion(sesion));
        }
    }//GEN-LAST:event_btnBalanzaComprobacionActionPerformed

    private void btnBalanceGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBalanceGeneralActionPerformed
        this.seleccionarBoton();
        if (!this.btnBalanceGeneral.isSelected()) {
            this.btnBalanceGeneral.setSelected(true);
            new CambiaPanel(pnl, new vBalanceGeneral(sesion));
        }
    }//GEN-LAST:event_btnBalanceGeneralActionPerformed

    private void btnCicloContableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCicloContableActionPerformed
        this.seleccionarBoton();
        if (!this.btnCicloContable.isSelected()) {
            this.btnCicloContable.setSelected(true);
            new CambiaPanel(pnl, new vConfigContabilidad(sesion));
        }
    }//GEN-LAST:event_btnCicloContableActionPerformed

    private void btnGuardarCicloContable2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCicloContable2ActionPerformed
        boolean visible = this.sidebar.isVisible();
        if (visible) {
            this.sidebar.setVisible(false);
        } else {
            this.sidebar.setVisible(true);
        }
    }//GEN-LAST:event_btnGuardarCicloContable2ActionPerformed

    private void imagenPrincipalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagenPrincipalMouseClicked
        this.irADashboar();
    }//GEN-LAST:event_imagenPrincipalMouseClicked

    private void btnGuardarCicloContable3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCicloContable3ActionPerformed
        this.irADashboar();
    }//GEN-LAST:event_btnGuardarCicloContable3ActionPerformed

    private void btnSeleccionarArchivoAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarArchivoAlumnoActionPerformed
        // TODO add your handling code here:
        //JFileChooser selectorArchivos = new JFileChooser("./");
        if (sesion.esAlumno()) {
            return;
        }

        JFileChooser selector = new JFileChooser();
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("sqlite", "sqlite", "db");
        //selectorArchivos.setFileFilter(fnef);
        //selectorArchivos.setMultiSelectionEnabled(false);
        selector.setFileFilter(fnef);
        selector.setCurrentDirectory(new File("./database"));
        selector.setMultiSelectionEnabled(false);
        if (selector.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(this, "¡No seleccionó ningún archivo: ", "Mensaje", JOptionPane.WARNING_MESSAGE);
        }
        File archivo = selector.getSelectedFile();
        try {
            if (archivo != null) {
                String ruta = archivo.getCanonicalPath();
                System.out.println(ruta);

                Usuario usuarioInvitado = sesion.usuario;// (Usuario) sesion.usuario.clone();
                Sesion sesionInvitado = new Sesion(usuarioInvitado, null, ruta);

                if (ruta.contains(".sqlite") || ruta.contains(".db")) {
                    ServicioUsuario _usuarioInvitado = new ServicioUsuario(sesionInvitado.rutaConexion);
                    boolean esSecundaria = true;
                    vPrincipal vInvitado = new vPrincipal(_usuarioInvitado, sesionInvitado, esSecundaria);
                    vInvitado.setExtendedState(this.NORMAL);
                    final int WIDTH = 500;
                    final int HEIGHT = 600;
                    Dimension dimension = new Dimension(WIDTH, HEIGHT);
                    //vInvitado.setSize(dimension);
                    vInvitado.setVisible(true);
                    vInvitado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                } else {
                    JOptionPane.showMessageDialog(this, "¡Archivo no valido: ", "Mensaje", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(vInicio.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "¡Ocurrió un error: " + ex.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSeleccionarArchivoAlumnoActionPerformed

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
    public static RSMaterialComponent.RSButtonShapeIcon btnBalanceGeneral;
    public static RSMaterialComponent.RSButtonShapeIcon btnBalanzaComprobacion;
    private RSMaterialComponent.RSButtonShapeIcon btnCerrarSesion;
    private RSMaterialComponent.RSButtonShapeIcon btnCicloContable;
    private RSMaterialComponent.RSButtonShapeIcon btnConfigUsuario;
    public static RSMaterialComponent.RSButtonShapeIcon btnEstadoResultado;
    private RSMaterialComponent.RSButtonShapeIcon btnGuardarCicloContable2;
    private RSMaterialComponent.RSButtonShapeIcon btnGuardarCicloContable3;
    public static RSMaterialComponent.RSButtonShapeIcon btnLibroDiario;
    public static RSMaterialComponent.RSButtonShapeIcon btnLibroMayor;
    private RSMaterialComponent.RSButtonShapeIcon btnSeleccionarArchivoAlumno;
    public static javax.swing.JLabel imagenPrincipal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnl;
    private javax.swing.JPanel pnlBase;
    private rojeru_san.rsdate.RSLabelFecha rSLabelFecha1;
    private rojeru_san.rsdate.RSLabelHora rSLabelHora1;
    private javax.swing.JPanel sidebar;
    private javax.swing.JPanel topbar;
    public static javax.swing.JLabel txtConfigCicloContable;
    public static javax.swing.JLabel txtNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
