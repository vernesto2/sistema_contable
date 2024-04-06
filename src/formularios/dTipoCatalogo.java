/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package formularios;

import dto.dtoLista;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.TipoCatalogo;
import servicios.ServicioTipoCatalogo;
import sesion.Sesion;
import utils.UtileriaVista;
import utils.constantes.Constantes;
import utils.constantes.RespuestaGeneral;
import vista.vConfigContabilidad;

/**
 *
 * @author vacev
 */
public class dTipoCatalogo extends javax.swing.JDialog {

    TipoCatalogo tipoCatalogoModel = new TipoCatalogo();
    Sesion sesion;
    ServicioTipoCatalogo _tipoCatalogo;
    RespuestaGeneral rg = new RespuestaGeneral();
    ArrayList<dtoLista> listaColor = new ArrayList<>();
    
    boolean realizoAccion = false;
    /**
     * Creates new form dTipoCatalogo
     */
    public dTipoCatalogo(java.awt.Frame parent, boolean modal, TipoCatalogo tipoCatalogo, Sesion sesion) {
        super(parent, modal);
        this.sesion = sesion;
        _tipoCatalogo = new ServicioTipoCatalogo(sesion.rutaConexion);
        initComponents();
        this.obtenerListaCmbColores();
        this.tipoCatalogoModel = (TipoCatalogo)tipoCatalogo;
        this.iniciarVistaDialog();
        this.setearData();
    }
    
    public void setearData() {
        txtTipoCatalogo.setText(this.tipoCatalogoModel.getTipo());
        chLibroDiario.setSelected((this.tipoCatalogoModel.getLibro_diario() == 1));
        chLibroMayor.setSelected((this.tipoCatalogoModel.getLibro_mayor()== 1));
        chBalanzaComprobacion.setSelected((this.tipoCatalogoModel.getBalanza_comprobacion()== 1));
        chEstadoResultado.setSelected((this.tipoCatalogoModel.getEstado_resultado()== 1));
        chBalanceGeneral.setSelected((this.tipoCatalogoModel.getBalance_general()== 1));
        chFlujoEfectivo.setSelected((this.tipoCatalogoModel.getFlujo_efectivo()== 1));
        chCambiosPatromonio.setSelected((this.tipoCatalogoModel.getCambios_patrimonio()== 1));
        txtNivelMayorizar.setText(String.valueOf(this.tipoCatalogoModel.getNivel_mayorizar()));
        
        int iCmb = 0, i = 0;
        for (dtoLista item : listaColor) {
            if (Integer.parseInt(item.getValue()) == this.tipoCatalogoModel.getColor()) {
                iCmb = i;
            }
            i++;
        }
        cmbColor.setSelectedIndex(iCmb);
        
    }
    
    public void obtenerListaCmbColores() {
        this.listaColor = Constantes.listaColores();
        cmbColor.removeAllItems();
        for (dtoLista item : this.listaColor) {
            cmbColor.addItem(item.getLabel());
        }
    }
    
    public void iniciarVistaDialog() {
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle((this.tipoCatalogoModel.getId() > 0) ? "MODIFICACIÓN DE TIPO CATALOGO": "NUEVO REGISTRO DE TIPO CATALOGO");
        this.realizoAccion = false;
    }
    
    public boolean getRealizoAccion() {
        return realizoAccion;
    }
    
    public RespuestaGeneral getRG() {
        return rg;
    }

    public TipoCatalogo getTipoCatalogoModel() {
        return tipoCatalogoModel;
    }

    public void setTipoCatalogoModel(TipoCatalogo tipoCatalogoModel) {
        this.tipoCatalogoModel = tipoCatalogoModel;
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
        jPanel2 = new javax.swing.JPanel();
        btnCancelarTipoCatalogo = new RSMaterialComponent.RSButtonShapeIcon();
        btnGuardarTipoCatalogo = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel5 = new javax.swing.JLabel();
        txtTipoCatalogo = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel6 = new javax.swing.JLabel();
        cmbColor = new RSMaterialComponent.RSComboBoxMaterial();
        jPanel3 = new javax.swing.JPanel();
        chLibroDiario = new rojerusan.RSCheckBox();
        chLibroMayor = new rojerusan.RSCheckBox();
        chBalanzaComprobacion = new rojerusan.RSCheckBox();
        chEstadoResultado = new rojerusan.RSCheckBox();
        chBalanceGeneral = new rojerusan.RSCheckBox();
        chFlujoEfectivo = new rojerusan.RSCheckBox();
        chCambiosPatromonio = new rojerusan.RSCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNivelMayorizar = new RSMaterialComponent.RSTextFieldMaterial();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        btnCancelarTipoCatalogo.setBackground(new java.awt.Color(251, 205, 6));
        btnCancelarTipoCatalogo.setText("CERRAR");
        btnCancelarTipoCatalogo.setToolTipText("LIMPIAR EL FORMULARIO Y SELECCIÓN");
        btnCancelarTipoCatalogo.setBackgroundHover(new java.awt.Color(251, 174, 6));
        btnCancelarTipoCatalogo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCancelarTipoCatalogo.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnCancelarTipoCatalogo.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnCancelarTipoCatalogo.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnCancelarTipoCatalogo.setForegroundText(new java.awt.Color(0, 0, 0));
        btnCancelarTipoCatalogo.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnCancelarTipoCatalogo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        btnCancelarTipoCatalogo.setSizeIcon(18.0F);
        btnCancelarTipoCatalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarTipoCatalogoActionPerformed(evt);
            }
        });

        btnGuardarTipoCatalogo.setBackground(new java.awt.Color(33, 58, 86));
        btnGuardarTipoCatalogo.setText("GUARDAR");
        btnGuardarTipoCatalogo.setToolTipText("GUARDAR O ACTUALIZAR EL TIPO DE CATALOGO");
        btnGuardarTipoCatalogo.setBackgroundHover(new java.awt.Color(33, 68, 86));
        btnGuardarTipoCatalogo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuardarTipoCatalogo.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnGuardarTipoCatalogo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btnGuardarTipoCatalogo.setSizeIcon(18.0F);
        btnGuardarTipoCatalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarTipoCatalogoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Tipo: *");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtTipoCatalogo.setForeground(new java.awt.Color(0, 0, 0));
        txtTipoCatalogo.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtTipoCatalogo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtTipoCatalogo.setPhColor(new java.awt.Color(0, 0, 0));
        txtTipoCatalogo.setPlaceholder("Digite el tipo de catalogo");
        txtTipoCatalogo.setSelectionColor(new java.awt.Color(0, 0, 0));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Color: *");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        cmbColor.setColorMaterial(new java.awt.Color(102, 102, 102));
        cmbColor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbColorItemStateChanged(evt);
            }
        });

        chLibroDiario.setForeground(new java.awt.Color(0, 0, 0));
        chLibroDiario.setText("Libro diario");
        chLibroDiario.setColorCheck(new java.awt.Color(33, 58, 86));
        chLibroDiario.setColorUnCheck(new java.awt.Color(0, 0, 0));
        chLibroDiario.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        chLibroMayor.setForeground(new java.awt.Color(0, 0, 0));
        chLibroMayor.setText("Libro mayor");
        chLibroMayor.setColorCheck(new java.awt.Color(33, 58, 86));
        chLibroMayor.setColorUnCheck(new java.awt.Color(0, 0, 0));
        chLibroMayor.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        chBalanzaComprobacion.setForeground(new java.awt.Color(0, 0, 0));
        chBalanzaComprobacion.setText("Balanza de comprobación");
        chBalanzaComprobacion.setColorCheck(new java.awt.Color(33, 58, 86));
        chBalanzaComprobacion.setColorUnCheck(new java.awt.Color(0, 0, 0));
        chBalanzaComprobacion.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        chEstadoResultado.setForeground(new java.awt.Color(0, 0, 0));
        chEstadoResultado.setText("Estado de resultados");
        chEstadoResultado.setColorCheck(new java.awt.Color(33, 58, 86));
        chEstadoResultado.setColorUnCheck(new java.awt.Color(0, 0, 0));
        chEstadoResultado.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        chBalanceGeneral.setForeground(new java.awt.Color(0, 0, 0));
        chBalanceGeneral.setText("Balance general");
        chBalanceGeneral.setColorCheck(new java.awt.Color(33, 58, 86));
        chBalanceGeneral.setColorUnCheck(new java.awt.Color(0, 0, 0));
        chBalanceGeneral.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        chFlujoEfectivo.setForeground(new java.awt.Color(0, 0, 0));
        chFlujoEfectivo.setText("Flujo de efectivo");
        chFlujoEfectivo.setColorCheck(new java.awt.Color(33, 58, 86));
        chFlujoEfectivo.setColorUnCheck(new java.awt.Color(0, 0, 0));
        chFlujoEfectivo.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        chCambiosPatromonio.setForeground(new java.awt.Color(0, 0, 0));
        chCambiosPatromonio.setText("Cambios en el patrimonio");
        chCambiosPatromonio.setColorCheck(new java.awt.Color(33, 58, 86));
        chCambiosPatromonio.setColorUnCheck(new java.awt.Color(0, 0, 0));
        chCambiosPatromonio.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(chLibroDiario, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                            .addComponent(chBalanzaComprobacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chEstadoResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chLibroMayor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(chBalanceGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chFlujoEfectivo, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                    .addComponent(chCambiosPatromonio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chLibroDiario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chLibroMayor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chBalanzaComprobacion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chEstadoResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chBalanceGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chFlujoEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chCambiosPatromonio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Estados financieros:");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Nivel a mayorizar: *");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtNivelMayorizar.setForeground(new java.awt.Color(0, 0, 0));
        txtNivelMayorizar.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtNivelMayorizar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtNivelMayorizar.setPhColor(new java.awt.Color(0, 0, 0));
        txtNivelMayorizar.setPlaceholder("Digite el nivel a mayorizar");
        txtNivelMayorizar.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtNivelMayorizar.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        txtNivelMayorizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNivelMayorizarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmbColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTipoCatalogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNivelMayorizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNivelMayorizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnCancelarTipoCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarTipoCatalogoActionPerformed
        this.cerrar();
    }//GEN-LAST:event_btnCancelarTipoCatalogoActionPerformed

    public void cerrar() {
        this.dispose();
    }
    
    public void setearModelTipoCatalogo() {
        this.tipoCatalogoModel.setTipo(this.txtTipoCatalogo.getText());
        this.tipoCatalogoModel.setColor(this.cmbColor.getSelectedIndex());
        this.tipoCatalogoModel.setLibro_diario(this.chLibroDiario.isSelected() ? 1 : 0);
        this.tipoCatalogoModel.setLibro_mayor(this.chLibroMayor.isSelected() ? 1 : 0);
        this.tipoCatalogoModel.setBalanza_comprobacion(this.chBalanzaComprobacion.isSelected() ? 1 : 0);
        this.tipoCatalogoModel.setEstado_resultado(this.chEstadoResultado.isSelected() ? 1 : 0);
        this.tipoCatalogoModel.setBalance_general(this.chBalanceGeneral.isSelected() ? 1 : 0);
        this.tipoCatalogoModel.setFlujo_efectivo(this.chFlujoEfectivo.isSelected() ? 1 : 0);
        this.tipoCatalogoModel.setCambios_patrimonio(this.chCambiosPatromonio.isSelected() ? 1 : 0);
        this.tipoCatalogoModel.setNivel_mayorizar(Integer.parseInt(txtNivelMayorizar.getText()));
    }
    
    private void btnGuardarTipoCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarTipoCatalogoActionPerformed
        if (!this.validar()) {
            JOptionPane.showMessageDialog(this, "Complete la información obligatoria", "¡ALERTA!", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        // setear info al modelo
        this.setearModelTipoCatalogo();
        // guardamos la info
        // verificamos si es NUEVO
        if (this.tipoCatalogoModel.getId() < 0) {
            this.rg = _tipoCatalogo.insertar(this.tipoCatalogoModel);
            if (rg.esExitosa()) {
                //JOptionPane.showMessageDialog(this, rs.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rs));
                this.realizoAccion = true;
                this.cerrar();
            } else {
                JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
            }

            // verificamos si es NUEVO
        } else {
            rg = _tipoCatalogo.editar(this.tipoCatalogoModel);
            if (rg.esExitosa()) {
                //JOptionPane.showMessageDialog(this, rs.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rs));
                this.realizoAccion = true;
                this.cerrar();
            } else {
                JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
            }
        }
    }//GEN-LAST:event_btnGuardarTipoCatalogoActionPerformed

    public boolean validar() {
        boolean valido = true;
        if (txtTipoCatalogo.getText().isEmpty() || txtNivelMayorizar.getText().isEmpty()) {
            valido = false;
        }
        return valido;
    }
    
    private void cmbColorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbColorItemStateChanged
        //this.seleccionarOpcionCmbTipoCatalogo();
    }//GEN-LAST:event_cmbColorItemStateChanged

    private void txtNivelMayorizarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNivelMayorizarKeyTyped
        if (!Constantes.validarNumeros(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNivelMayorizarKeyTyped

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(dTipoCatalogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(dTipoCatalogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(dTipoCatalogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(dTipoCatalogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                dTipoCatalogo dialog = new dTipoCatalogo(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShapeIcon btnCancelarTipoCatalogo;
    private RSMaterialComponent.RSButtonShapeIcon btnGuardarTipoCatalogo;
    private rojerusan.RSCheckBox chBalanceGeneral;
    private rojerusan.RSCheckBox chBalanzaComprobacion;
    private rojerusan.RSCheckBox chCambiosPatromonio;
    private rojerusan.RSCheckBox chEstadoResultado;
    private rojerusan.RSCheckBox chFlujoEfectivo;
    private rojerusan.RSCheckBox chLibroDiario;
    private rojerusan.RSCheckBox chLibroMayor;
    private RSMaterialComponent.RSComboBoxMaterial cmbColor;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private RSMaterialComponent.RSTextFieldMaterial txtNivelMayorizar;
    private RSMaterialComponent.RSTextFieldMaterial txtTipoCatalogo;
    // End of variables declaration//GEN-END:variables
}
