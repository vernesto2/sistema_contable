/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import dto.dtoLista;
import formularios.dCrearUsuario;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.ConfiguracionUsuario;
import modelo.Persona;
import modelo.Usuario;
import servicios.ServicioConfigUsuario;
import servicios.ServicioUsuario;
import sesion.Sesion;
import utils.UtileriaVista;
import utils.constantes.CharArrayUtils;
import utils.constantes.Constantes;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class vConfiguracion extends javax.swing.JPanel {

    /**
     * Creates new form vConfiguracion
     */
    ServicioUsuario _usuario;
    Sesion sesion;
    public char pass;
    public char pass1;
    public char pass2;
    ServicioConfigUsuario _configUsuario;
    ArrayList<dtoLista> listaAvatars = new ArrayList<>();
    ConfiguracionUsuario configUsuarioModel;
    int i = 0;
    
    public vConfiguracion(Sesion sesion, ServicioUsuario _usuario) {
        initComponents();
        imgAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource(sesion.configUsuario.getAvatar())));
        this.sesion = sesion;
        this._usuario = _usuario;
        _configUsuario = new ServicioConfigUsuario(sesion.rutaConexion);
        this.configUsuarioModel = sesion.configUsuario;
        this.iniciarVista();
        this.setearCombos();
        this.rellenarDatosUsuario(sesion.usuario);
    }
    


    public void iniciarVista() {
        if(sesion.esDocente()) {
            btnNuevoDocente.setVisible(true);
        } else {
            btnNuevoDocente.setVisible(false);
        }
        ComboBoxModel<String> modeloPreguntasRecuperacion1 = new DefaultComboBoxModel<String>(Constantes.PREGUNTAS_SEGURIDAD);
        ComboBoxModel<String> modeloPreguntasRecuperacion2 = new DefaultComboBoxModel<String>(Constantes.PREGUNTAS_SEGURIDAD);
        ComboBoxModel<String> modeloPreguntasRecuperacion3 = new DefaultComboBoxModel<String>(Constantes.PREGUNTAS_SEGURIDAD);
        this.comboPreguntaRecuperacion1.setModel(modeloPreguntasRecuperacion1);
        this.comboPreguntaRecuperacion2.setModel(modeloPreguntasRecuperacion2);
        this.comboPreguntaRecuperacion3.setModel(modeloPreguntasRecuperacion3);
        this.swOculto.setActivado(false);
        this.mostrarInfoOculta();
        
    }
    
    public void setearCombos() {
        this.listaAvatars = Constantes.listaAvatars();
        cmbAvatar.removeAllItems();
        for (dtoLista item : this.listaAvatars) {
            cmbAvatar.addItem(item.getLabel());
        }
        
        // setear opcion por defecto
        this.i = 0;
        this.listaAvatars.forEach((t) -> {
            if (this.configUsuarioModel.getAvatar().equals(t.getValue())) {
                this.cmbAvatar.setSelectedIndex(i);
            }
            this.i++;
        });
        
        
    }
    
    public void mostrarInfoOculta() {
        if (this.swOculto.isActivado()) {
            this.pnlSeguridad.setVisible(true);
//            this.comboPreguntaRecuperacion1.setVisible(true);
//            this.comboPreguntaRecuperacion2.setVisible(true);
//            this.comboPreguntaRecuperacion3.setVisible(true);
//            this.txtRespuesta1.setVisible(true);
//            this.txtRespuesta2.setVisible(true);
//            this.txtRespuesta3.setVisible(true);

        } else {
            this.pnlSeguridad.setVisible(false);
//            this.comboPreguntaRecuperacion1.setVisible(false);
//            this.comboPreguntaRecuperacion2.setVisible(false);
//            this.comboPreguntaRecuperacion3.setVisible(false);
//            this.txtRespuesta1.setVisible(false);
//            this.txtRespuesta2.setVisible(false);
//            this.txtRespuesta3.setVisible(false);
        }
    }
    
    public void rellenarDatosUsuario(Usuario usuario) {
        Persona persona = sesion.usuario.getPersona();
        
        txtNombres.setText(persona.getNombres());
        txtApellidos.setText(persona.getApellidos());
        txtCarnet.setText(persona.getCarnet());
        txtCorreo.setText(usuario.getCorreo());
        
        comboPreguntaRecuperacion1.setSelectedIndex(usuario.getPregunta1());
        txtRespuesta1.setText(usuario.getRespuesta1());
        comboPreguntaRecuperacion2.setSelectedIndex(usuario.getPregunta2());
        txtRespuesta2.setText(usuario.getRespuesta2());
        comboPreguntaRecuperacion3.setSelectedIndex(usuario.getPregunta3());
        txtRespuesta3.setText(usuario.getRespuesta3());


    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        pnlSeguridad = new javax.swing.JPanel();
        comboPreguntaRecuperacion1 = new RSMaterialComponent.RSComboBoxMaterial();
        txtRespuesta1 = new RSMaterialComponent.RSTextFieldMaterial();
        comboPreguntaRecuperacion2 = new RSMaterialComponent.RSComboBoxMaterial();
        txtRespuesta2 = new RSMaterialComponent.RSTextFieldMaterial();
        comboPreguntaRecuperacion3 = new RSMaterialComponent.RSComboBoxMaterial();
        txtRespuesta3 = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txtNombres = new RSMaterialComponent.RSTextFieldMaterial();
        txtApellidos = new RSMaterialComponent.RSTextFieldMaterial();
        txtCarnet = new RSMaterialComponent.RSTextFieldMaterial();
        txtCorreo = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel19 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblDialog = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        btnNuevoDocente = new RSMaterialComponent.RSButtonShapeIcon();
        btnActualizar = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel2 = new javax.swing.JLabel();
        swOculto = new rojerusan.RSSwitch();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtClaveActual = new RSMaterialComponent.RSPasswordMaterial();
        jLabel11 = new javax.swing.JLabel();
        txtClaveNueva = new RSMaterialComponent.RSPasswordMaterial();
        jLabel12 = new javax.swing.JLabel();
        txtRepetirClaveNueva = new RSMaterialComponent.RSPasswordMaterial();
        checkMostrarClave = new rojerusan.RSCheckBox();
        jPanel10 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblDialog2 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        btnActualizarClave = new RSMaterialComponent.RSButtonShapeIcon();
        jPanel9 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        imgAvatar = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbAvatar = new RSMaterialComponent.RSComboBoxMaterial();
        jPanel14 = new javax.swing.JPanel();
        lblAvatar = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        btnActualizarClave1 = new RSMaterialComponent.RSButtonShapeIcon();

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        pnlSeguridad.setBackground(new java.awt.Color(255, 255, 255));
        pnlSeguridad.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Preguntas de Seguridad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        comboPreguntaRecuperacion1.setColorMaterial(new java.awt.Color(102, 102, 102));

        txtRespuesta1.setForeground(new java.awt.Color(0, 0, 0));
        txtRespuesta1.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtRespuesta1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtRespuesta1.setPhColor(new java.awt.Color(0, 0, 0));
        txtRespuesta1.setPlaceholder("Digite la respuesta..");
        txtRespuesta1.setSelectionColor(new java.awt.Color(0, 0, 0));

        comboPreguntaRecuperacion2.setColorMaterial(new java.awt.Color(102, 102, 102));

        txtRespuesta2.setForeground(new java.awt.Color(0, 0, 0));
        txtRespuesta2.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtRespuesta2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtRespuesta2.setPhColor(new java.awt.Color(0, 0, 0));
        txtRespuesta2.setPlaceholder("Digite la respuesta..");
        txtRespuesta2.setSelectionColor(new java.awt.Color(0, 0, 0));

        comboPreguntaRecuperacion3.setColorMaterial(new java.awt.Color(102, 102, 102));
        comboPreguntaRecuperacion3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPreguntaRecuperacion3ActionPerformed(evt);
            }
        });

        txtRespuesta3.setForeground(new java.awt.Color(0, 0, 0));
        txtRespuesta3.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtRespuesta3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtRespuesta3.setPhColor(new java.awt.Color(0, 0, 0));
        txtRespuesta3.setPlaceholder("Digite la respuesta..");
        txtRespuesta3.setSelectionColor(new java.awt.Color(0, 0, 0));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Pregunta 1:");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Respuesta 1:");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Pregunta 2:");
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Respuesta 2:");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Pregunta 3:");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Respuesta 3:");
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout pnlSeguridadLayout = new javax.swing.GroupLayout(pnlSeguridad);
        pnlSeguridad.setLayout(pnlSeguridadLayout);
        pnlSeguridadLayout.setHorizontalGroup(
            pnlSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSeguridadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboPreguntaRecuperacion3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtRespuesta2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtRespuesta3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboPreguntaRecuperacion1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtRespuesta1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboPreguntaRecuperacion2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );
        pnlSeguridadLayout.setVerticalGroup(
            pnlSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSeguridadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comboPreguntaRecuperacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnlSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtRespuesta1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSeguridadLayout.createSequentialGroup()
                        .addComponent(comboPreguntaRecuperacion2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRespuesta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSeguridadLayout.createSequentialGroup()
                        .addComponent(comboPreguntaRecuperacion3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRespuesta3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos Personales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        txtNombres.setForeground(new java.awt.Color(0, 0, 0));
        txtNombres.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtNombres.setPhColor(new java.awt.Color(0, 0, 0));
        txtNombres.setPlaceholder("Nombres");
        txtNombres.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtNombres.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        txtNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresActionPerformed(evt);
            }
        });

        txtApellidos.setForeground(new java.awt.Color(0, 0, 0));
        txtApellidos.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtApellidos.setPhColor(new java.awt.Color(0, 0, 0));
        txtApellidos.setPlaceholder("Apellidos");
        txtApellidos.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtApellidos.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        txtApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosActionPerformed(evt);
            }
        });

        txtCarnet.setForeground(new java.awt.Color(0, 0, 0));
        txtCarnet.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtCarnet.setEnabled(false);
        txtCarnet.setPhColor(new java.awt.Color(0, 0, 0));
        txtCarnet.setPlaceholder("Usuario/NIE");
        txtCarnet.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtCarnet.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        txtCarnet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCarnetActionPerformed(evt);
            }
        });

        txtCorreo.setForeground(new java.awt.Color(0, 0, 0));
        txtCorreo.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtCorreo.setPhColor(new java.awt.Color(0, 0, 0));
        txtCorreo.setPlaceholder("Correo");
        txtCorreo.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtCorreo.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Usuario/NIE:");
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Apellidos:");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Nombres:");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Correo:");
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombres, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCarnet, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCarnet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utils/img/userNew.png"))); // NOI18N
        jLabel1.setToolTipText("");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setIconTextGap(1);

        lblDialog.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDialog.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDialog.setText("EDICIÓN DE PERFIL DE USUARIO");
        lblDialog.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblDialog, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                .addGap(128, 128, 128))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnNuevoDocente.setBackground(new java.awt.Color(33, 58, 86));
        btnNuevoDocente.setText("Nuevo docente");
        btnNuevoDocente.setBackgroundHover(new java.awt.Color(33, 68, 86));
        btnNuevoDocente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnNuevoDocente.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnNuevoDocente.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btnNuevoDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoDocenteActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(33, 58, 86));
        btnActualizar.setText("Actualizar");
        btnActualizar.setBackgroundHover(new java.awt.Color(33, 68, 86));
        btnActualizar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnActualizar.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnActualizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnActualizar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EDIT);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevoDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevoDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Ver preguntas de seguridad");

        swOculto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                swOcultoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(swOculto, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSeguridad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(swOculto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSeguridad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane1.setViewportView(jPanel1);

        jTabbedPane1.addTab("Datos de usuario", jScrollPane1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Clave actual:");

        txtClaveActual.setForeground(new java.awt.Color(0, 0, 0));
        txtClaveActual.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtClaveActual.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtClaveActual.setPhColor(new java.awt.Color(0, 0, 0));
        txtClaveActual.setPlaceholder("Clave actual");
        txtClaveActual.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtClaveActual.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Nueva clave de acceso:");

        txtClaveNueva.setForeground(new java.awt.Color(0, 0, 0));
        txtClaveNueva.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtClaveNueva.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtClaveNueva.setPhColor(new java.awt.Color(0, 0, 0));
        txtClaveNueva.setPlaceholder("Nueva clave de acceso");
        txtClaveNueva.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtClaveNueva.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Repetir nueva clave de acceso:");

        txtRepetirClaveNueva.setForeground(new java.awt.Color(0, 0, 0));
        txtRepetirClaveNueva.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtRepetirClaveNueva.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtRepetirClaveNueva.setPhColor(new java.awt.Color(0, 0, 0));
        txtRepetirClaveNueva.setPlaceholder("Repetir nueva clave de acceso");
        txtRepetirClaveNueva.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtRepetirClaveNueva.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);

        checkMostrarClave.setForeground(new java.awt.Color(0, 0, 0));
        checkMostrarClave.setText("Mostrar Contraseñas");
        checkMostrarClave.setColorCheck(new java.awt.Color(0, 0, 0));
        checkMostrarClave.setColorUnCheck(new java.awt.Color(0, 0, 0));
        checkMostrarClave.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        checkMostrarClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkMostrarClaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(457, 457, 457)
                        .addComponent(checkMostrarClave, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtClaveNueva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtClaveActual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtRepetirClaveNueva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(192, 192, 192))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtClaveActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtClaveNueva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRepetirClaveNueva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkMostrarClave, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utils/img/recuperacion.png"))); // NOI18N
        jLabel3.setToolTipText("");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setIconTextGap(1);

        lblDialog2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDialog2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDialog2.setText("EDICIÓN DE CLAVE DE USUARIO");
        lblDialog2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblDialog2, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                .addGap(125, 125, 125))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lblDialog2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnActualizarClave.setBackground(new java.awt.Color(33, 58, 86));
        btnActualizarClave.setText("Actualizar clave de acceso");
        btnActualizarClave.setBackgroundHover(new java.awt.Color(33, 68, 86));
        btnActualizarClave.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnActualizarClave.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnActualizarClave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnActualizarClave.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CACHED);
        btnActualizarClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarClaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(658, 658, 658)
                .addComponent(btnActualizarClave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(btnActualizarClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Clave de acceso", jPanel2);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        imgAvatar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utils/avatar/avatar1.png"))); // NOI18N
        imgAvatar.setToolTipText("Ver Dashboard");
        imgAvatar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        imgAvatar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imgAvatar.setIconTextGap(1);
        imgAvatar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgAvatarMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Avatar:");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        cmbAvatar.setColorMaterial(new java.awt.Color(102, 102, 102));
        cmbAvatar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAvatarItemStateChanged(evt);
            }
        });

        lblAvatar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblAvatar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAvatar.setText("EDICIÓN DE AVATAR DE USUARIO");
        lblAvatar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(216, 216, 216)
                    .addComponent(lblAvatar, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                    .addGap(217, 217, 217)))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE)))
        );

        btnActualizarClave1.setBackground(new java.awt.Color(33, 58, 86));
        btnActualizarClave1.setText("Actualizar Avatar");
        btnActualizarClave1.setBackgroundHover(new java.awt.Color(33, 68, 86));
        btnActualizarClave1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnActualizarClave1.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnActualizarClave1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnActualizarClave1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CACHED);
        btnActualizarClave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarClave1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnActualizarClave1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(btnActualizarClave1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(imgAvatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(224, 224, 224))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbAvatar, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)))
                .addGap(190, 190, 190))
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(imgAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Cambiar avatar", jPanel9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarClaveActionPerformed
        char[] claveSinCifrarActual = txtClaveActual.getPassword();
        char[] claveSinCifrarNueva = txtClaveNueva.getPassword();
        char[] repetirClaveSinCifrarNueva = txtRepetirClaveNueva.getPassword();
        //la clave se cifra en el servicio
        //usuario.setClave(null);
        if (!Arrays.equals(claveSinCifrarNueva, repetirClaveSinCifrarNueva)) {
            JOptionPane.showMessageDialog(this, "Las claves no coinciden", "Mensaje", JOptionPane.ERROR_MESSAGE);
            return;
        }
        RespuestaGeneral resp = _usuario.actualizarClave(sesion.usuario, claveSinCifrarActual, claveSinCifrarNueva);
        if (resp.esExitosa()) {
            JOptionPane.showMessageDialog(this, resp.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(resp));
        } else {
            JOptionPane.showMessageDialog(this, resp.getMensaje(), "Mensaje", UtileriaVista.devolverCodigoMensaje(resp));
        }
        //limpiar el array de char, en caso de volcado de memoria, no esté la clave
        CharArrayUtils.limpiar(claveSinCifrarActual);
        CharArrayUtils.limpiar(claveSinCifrarNueva);
        CharArrayUtils.limpiar(repetirClaveSinCifrarNueva);
        
        //limpiar formulario
        txtClaveNueva.setText("");
        txtClaveActual.setText("");
        txtRepetirClaveNueva.setText("");
    }//GEN-LAST:event_btnActualizarClaveActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        

        Persona persona = sesion.usuario.getPersona();
        persona.setNombres(txtNombres.getText().trim());
        persona.setApellidos(txtApellidos.getText().trim());
        
        persona.setTipo(sesion.usuario.getPersona().getTipo());
        persona.setCarnet(txtCarnet.getText().trim());

        sesion.usuario.setNombre(txtCarnet.getText().trim());
        sesion.usuario.setCorreo(txtCorreo.getText().trim());

        sesion.usuario.setResetear_clave(Constantes.NO_RESETEAR_CLAVE);

        sesion.usuario.setPregunta1(comboPreguntaRecuperacion1.getSelectedIndex());
        sesion.usuario.setRespuesta1(txtRespuesta1.getText().trim());
        sesion.usuario.setPregunta2(comboPreguntaRecuperacion2.getSelectedIndex());
        sesion.usuario.setRespuesta2(txtRespuesta2.getText().trim());
        sesion.usuario.setPregunta3(comboPreguntaRecuperacion3.getSelectedIndex());
        sesion.usuario.setRespuesta3(txtRespuesta3.getText().trim());

        sesion.usuario.setPersona(persona);
        RespuestaGeneral resp = this._usuario.actualizar(sesion.usuario, sesion.usuario.getId());
        if (resp.esExitosa()) {
            JOptionPane.showMessageDialog(this, resp.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(resp));
            // si actualizo correctamente se debe actualizar el sidebar
            RespuestaGeneral rg1 = _configUsuario.obtenerPorIdUsuario(sesion.usuario.getId());
            if (rg1.esExitosa()) {
                ArrayList<ConfiguracionUsuario> listaConfigUsuario = (ArrayList<ConfiguracionUsuario>)rg1.getDatos();
                sesion.configUsuario = listaConfigUsuario.get(0);
                UtileriaVista.actualizarPerfil(sesion);
            }
        
        } else {
            JOptionPane.showMessageDialog(this, resp.getMensaje(), "Mensaje", UtileriaVista.devolverCodigoMensaje(resp));
        }
        
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void txtApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosActionPerformed

    private void txtNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresActionPerformed

    private void comboPreguntaRecuperacion3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPreguntaRecuperacion3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboPreguntaRecuperacion3ActionPerformed

    private void txtCarnetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarnetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarnetActionPerformed

    private void btnNuevoDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoDocenteActionPerformed
        // TODO add your handling code here:
        dCrearUsuario dialogo = new dCrearUsuario(null, true, sesion, Constantes.TIPO_DOCENTE);
        dialogo.setVisible(true);
    }//GEN-LAST:event_btnNuevoDocenteActionPerformed

    private void checkMostrarClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkMostrarClaveActionPerformed
        char character = this.txtClaveActual.getEchoChar();
        char character1 = this.txtClaveNueva.getEchoChar();
        char character2 = this.txtRepetirClaveNueva.getEchoChar();
        if (this.checkMostrarClave.isSelected()) {
            this.pass = character;
            this.txtClaveActual.setEchoChar((char) 0);
            this.txtClaveActual.requestFocus();
            
            this.pass1 = character1;
            this.txtClaveNueva.setEchoChar((char) 0);
            this.txtClaveNueva.requestFocus();
            
            this.pass2 = character2;
            this.txtRepetirClaveNueva.setEchoChar((char) 0);
            this.txtRepetirClaveNueva.requestFocus();
            
        } else {
            this.txtClaveActual.setEchoChar((char) this.pass);
            this.txtClaveActual.requestFocus();
            
            this.txtClaveNueva.setEchoChar((char) this.pass1);
            this.txtClaveNueva.requestFocus();
            
            this.txtRepetirClaveNueva.setEchoChar((char) this.pass2);
            this.txtRepetirClaveNueva.requestFocus();
        }
    }//GEN-LAST:event_checkMostrarClaveActionPerformed

    private void imgAvatarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgAvatarMouseClicked
        
    }//GEN-LAST:event_imgAvatarMouseClicked

    private void cmbAvatarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAvatarItemStateChanged
        int row = this.cmbAvatar.getSelectedIndex();
        if (row > -1 && !this.listaAvatars.isEmpty()) {
            Icon img = new ImageIcon(getClass().getResource(this.listaAvatars.get(row).getValue()));
            this.imgAvatar.setIcon(img);
        }
    }//GEN-LAST:event_cmbAvatarItemStateChanged

    private void btnActualizarClave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarClave1ActionPerformed
        int row = this.cmbAvatar.getSelectedIndex();
        this.configUsuarioModel.setAvatar(this.listaAvatars.get(row).getValue());
        RespuestaGeneral rg = _configUsuario.editar(this.configUsuarioModel);
        if (rg.esExitosa()) {
            this.sesion.configUsuario.setAvatar(this.configUsuarioModel.getAvatar());
            JOptionPane.showMessageDialog(this, rg.getMensaje(), "MENSAJE", UtileriaVista.devolverCodigoMensaje(rg));
            Icon img = new ImageIcon(getClass().getResource(sesion.configUsuario.getAvatar()));
            vPrincipal.imagenPrincipal.setIcon(img);
        }
        
    }//GEN-LAST:event_btnActualizarClave1ActionPerformed

    private void swOcultoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_swOcultoMouseClicked
        this.mostrarInfoOculta();
    }//GEN-LAST:event_swOcultoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShapeIcon btnActualizar;
    private RSMaterialComponent.RSButtonShapeIcon btnActualizarClave;
    private RSMaterialComponent.RSButtonShapeIcon btnActualizarClave1;
    private RSMaterialComponent.RSButtonShapeIcon btnNuevoDocente;
    private rojerusan.RSCheckBox checkMostrarClave;
    private RSMaterialComponent.RSComboBoxMaterial cmbAvatar;
    private RSMaterialComponent.RSComboBoxMaterial comboPreguntaRecuperacion1;
    private RSMaterialComponent.RSComboBoxMaterial comboPreguntaRecuperacion2;
    private RSMaterialComponent.RSComboBoxMaterial comboPreguntaRecuperacion3;
    public static javax.swing.JLabel imgAvatar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblDialog;
    private javax.swing.JLabel lblDialog2;
    private javax.swing.JPanel pnlSeguridad;
    private rojerusan.RSSwitch swOculto;
    private RSMaterialComponent.RSTextFieldMaterial txtApellidos;
    private RSMaterialComponent.RSTextFieldMaterial txtCarnet;
    private RSMaterialComponent.RSPasswordMaterial txtClaveActual;
    private RSMaterialComponent.RSPasswordMaterial txtClaveNueva;
    private RSMaterialComponent.RSTextFieldMaterial txtCorreo;
    private RSMaterialComponent.RSTextFieldMaterial txtNombres;
    private RSMaterialComponent.RSPasswordMaterial txtRepetirClaveNueva;
    private RSMaterialComponent.RSTextFieldMaterial txtRespuesta1;
    private RSMaterialComponent.RSTextFieldMaterial txtRespuesta2;
    private RSMaterialComponent.RSTextFieldMaterial txtRespuesta3;
    // End of variables declaration//GEN-END:variables
}

