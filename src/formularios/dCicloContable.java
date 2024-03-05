/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package formularios;

import dto.dtoLista;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.text.Document;
import modelo.CicloContable;
import modelo.TipoCatalogo;
import servicios.ServicioCicloContable;
import servicios.ServicioTipoCatalogo;
import sesion.Sesion;
import utils.UtileriaVista;
import utils.constantes.Constantes;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class dCicloContable extends javax.swing.JDialog {

    CicloContable cicloContableModel = new CicloContable();
    RespuestaGeneral rg = new RespuestaGeneral();
    ServicioCicloContable _cicloContable;
    ServicioTipoCatalogo _tipoCatalogo;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    ArrayList<TipoCatalogo> listaCmbTipoCatalogo = new ArrayList<>();
    ArrayList<dtoLista> listaCmbTipoSociedad = new ArrayList<>();
    Sesion sesion;
    boolean realizoAccion = false;
    /**
     * Creates new form dTipoCatalogo
     */
    public dCicloContable(java.awt.Frame parent, boolean modal, CicloContable cicloContable, Sesion sesion) {
        super(parent, modal);
        
        _cicloContable = new ServicioCicloContable(sesion.rutaConexion);
        _tipoCatalogo = new ServicioTipoCatalogo(sesion.rutaConexion);
        initComponents();
        this.cicloContableModel = (CicloContable)cicloContable;
        this.sesion = sesion;
        this.iniciarVistaDialog();
    }
    
    public void iniciarVistaDialog() {
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        // agregar formato a los campos
        this.setTitle((this.cicloContableModel.getId() > 0) ? "MODIFICACIÓN DE CICLO CONTABLE": "NUEVO REGISTRO DE CICLO CONTABLE");
        this.realizoAccion = false;
        // obtenemos los catalogos
        this.obtenerListaCmbTipoSociedad();
        this.obtenerListaCmbTipoCatalogo();
        // seteamos la informacion
        this.setearData();
    }
    
    public void setearData() {
        //txtTipoCatalogo.setText(this.tipoCatalogoModel.getTipo());
        txtTitulo.setText(this.cicloContableModel.getTitulo());
        txtDesde.setDate(this.cicloContableModel.getDesde());
        txtDesde.setFormatDate("dd-MM-yyyy");
        txtHasta.setDate(this.cicloContableModel.getHasta());
        txtHasta.setFormatDate("dd-MM-yyyy");
        txtPorcentajeReserva.setText(String.valueOf(this.cicloContableModel.getPorcentaje_reserva_legal()));
        
        // PROCESO PARA SELECCION DE COMBOBOX
        int iCmb = 0, i = 0;
        for (TipoCatalogo tipoCatalogo : listaCmbTipoCatalogo) {
            if (tipoCatalogo.getId() == this.cicloContableModel.getId_catalogo()) {
                iCmb = i;
            }
            i++;
        }
        cmbTipoCatalogo.setSelectedIndex(iCmb);
        
        // PROCESO PARA SELECCION DE COMBOBOX DE TIPO SOCIEDAD
        int iCmb1 = 0, i1 = 0;
        for (dtoLista item : listaCmbTipoSociedad) {
            if (Integer.parseInt(item.getValue()) == this.cicloContableModel.getTipo_sociedad()) {
                iCmb1 = i1;
            }
            i1++;
        }
        cmbTipoSociedad.setSelectedIndex(iCmb1);
        
    }
    
    public void obtenerListaCmbTipoCatalogo() {
        // obtenemos el listado de tipos de catalogo
        this.listaCmbTipoCatalogo = new ArrayList<>();
        RespuestaGeneral rg = _tipoCatalogo.obtenerLista("");
        if (rg.esExitosa()) {
            this.listaCmbTipoCatalogo = (ArrayList<TipoCatalogo>)rg.getDatos();
            cmbTipoCatalogo.removeAllItems();
            for (TipoCatalogo itemTipoCatalogo : listaCmbTipoCatalogo) {
                cmbTipoCatalogo.addItem(itemTipoCatalogo.getTipo());
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo obtener el listado", "ALERTA", UtileriaVista.devolverCodigoMensaje(rg));
        }
        
        //cmbTipoCatalogo.addItem();
    }
    
    public void obtenerListaCmbTipoSociedad() {
        this.listaCmbTipoSociedad = Constantes.listaTipoSociedad();
        cmbTipoSociedad.removeAllItems();
        for (dtoLista item : this.listaCmbTipoSociedad) {
            cmbTipoSociedad.addItem(item.getLabel());
        }
    }
    
    public void seleccionarOpcionCmbTipoCatalogo() {
        int i = cmbTipoCatalogo.getSelectedIndex();
        if (i >= 0) {
            this.cicloContableModel.setId_catalogo(this.listaCmbTipoCatalogo.get(i).getId());
        }
    }
    
    public void seleccionarOpcionCmbTipoSociedad() {
        int i = cmbTipoSociedad.getSelectedIndex();
        if (i >= 0) {
            this.cicloContableModel.setTipo_sociedad(Integer.parseInt(this.listaCmbTipoSociedad.get(i).getValue()));
        }
    }
    
    public boolean getRealizoAccion() {
        return realizoAccion;
    }
    
    public RespuestaGeneral getRG() {
        return rg;
    }

    public CicloContable getTipoCatalogoModel() {
        return cicloContableModel;
    }

    public void setTipoCatalogoModel(CicloContable cicloContable) {
        this.cicloContableModel = cicloContable;
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
        jLabel4 = new javax.swing.JLabel();
        cmbTipoCatalogo = new RSMaterialComponent.RSComboBoxMaterial();
        jLabel1 = new javax.swing.JLabel();
        txtTitulo = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel2 = new javax.swing.JLabel();
        txtDesde = new newscomponents.RSDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtHasta = new newscomponents.RSDateChooser();
        jLabel5 = new javax.swing.JLabel();
        cmbTipoSociedad = new RSMaterialComponent.RSComboBoxMaterial();
        jLabel6 = new javax.swing.JLabel();
        txtPorcentajeReserva = new RSMaterialComponent.RSTextFieldMaterial();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setModal(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        btnCancelarTipoCatalogo.setBackground(new java.awt.Color(251, 205, 6));
        btnCancelarTipoCatalogo.setText("CERRAR");
        btnCancelarTipoCatalogo.setToolTipText("CERRAR FORMULARIO");
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
        btnGuardarTipoCatalogo.setToolTipText("GUARDAR / ACTUALIZAR EL CICLO CONTABLE");
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

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Tipo de catalogo:");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        cmbTipoCatalogo.setColorMaterial(new java.awt.Color(102, 102, 102));
        cmbTipoCatalogo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoCatalogoItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Titulo:");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtTitulo.setForeground(new java.awt.Color(0, 0, 0));
        txtTitulo.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtTitulo.setPhColor(new java.awt.Color(0, 0, 0));
        txtTitulo.setPlaceholder("Digite el titulo del ciclo contable");
        txtTitulo.setSelectionColor(new java.awt.Color(0, 0, 0));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Fecha desde:");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtDesde.setBackground(new java.awt.Color(153, 153, 153));
        txtDesde.setBgColor(new java.awt.Color(153, 153, 153));
        txtDesde.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtDesde.setFormatDate("dd-MM-yyyy");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Fecha hasta:");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtHasta.setBackground(new java.awt.Color(153, 153, 153));
        txtHasta.setBgColor(new java.awt.Color(153, 153, 153));
        txtHasta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtHasta.setFormatDate("dd-MM-yyyy");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Tipo de sociedad:");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        cmbTipoSociedad.setColorMaterial(new java.awt.Color(102, 102, 102));
        cmbTipoSociedad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoSociedadItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("% Reserva legal:");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtPorcentajeReserva.setForeground(new java.awt.Color(0, 0, 0));
        txtPorcentajeReserva.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtPorcentajeReserva.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtPorcentajeReserva.setPhColor(new java.awt.Color(0, 0, 0));
        txtPorcentajeReserva.setPlaceholder("Digite el porcentaje");
        txtPorcentajeReserva.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtPorcentajeReserva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcentajeReservaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                    .addComponent(cmbTipoCatalogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbTipoSociedad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPorcentajeReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPorcentajeReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        this.seleccionarOpcionCmbTipoCatalogo();
        this.seleccionarOpcionCmbTipoSociedad();
        this.cicloContableModel.setTitulo(this.txtTitulo.getText());
        this.cicloContableModel.setDesde(txtDesde.getDate());
        this.cicloContableModel.setHasta(txtHasta.getDate());
        double porcentajeReserva = Double.parseDouble(txtPorcentajeReserva.getText());
        this.cicloContableModel.setPorcentaje_reserva_legal(BigDecimal.valueOf(porcentajeReserva));
    }
    
    private void btnGuardarTipoCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarTipoCatalogoActionPerformed
        // setear info al modelo
        this.setearModelTipoCatalogo();
        // guardamos la info
        if (this.cicloContableModel.getId() < 0) {
            this.rg = _cicloContable.insertar(this.cicloContableModel);
            if (rg.esExitosa()) {
                //JOptionPane.showMessageDialog(this, rs.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rs));
                this.realizoAccion = true;
                this.cerrar();
            } else {
                JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
            }

            // verificamos si es NUEVO
        } else {
            rg = _cicloContable.editar(this.cicloContableModel);
            if (rg.esExitosa()) {
                //JOptionPane.showMessageDialog(this, rs.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rs));
                this.realizoAccion = true;
                this.cerrar();
            } else {
                JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
            }
        }
    }//GEN-LAST:event_btnGuardarTipoCatalogoActionPerformed

    private void cmbTipoCatalogoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoCatalogoItemStateChanged
        //this.seleccionarOpcionCmbTipoCatalogo();
    }//GEN-LAST:event_cmbTipoCatalogoItemStateChanged

    private void cmbTipoSociedadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoSociedadItemStateChanged
        //this.seleccionarOpcionCmbTipoSociedad();
        int i = cmbTipoSociedad.getSelectedIndex();
        if (i >= 0) {
            if (i == 0) {
                txtPorcentajeReserva.setText("7");
            } else {
                txtPorcentajeReserva.setText("5");
            }
        }
    }//GEN-LAST:event_cmbTipoSociedadItemStateChanged

    private void txtPorcentajeReservaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeReservaKeyTyped
        if (!Constantes.validarPorcentaje(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPorcentajeReservaKeyTyped

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
    private RSMaterialComponent.RSComboBoxMaterial cmbTipoCatalogo;
    private RSMaterialComponent.RSComboBoxMaterial cmbTipoSociedad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private newscomponents.RSDateChooser txtDesde;
    private newscomponents.RSDateChooser txtHasta;
    private RSMaterialComponent.RSTextFieldMaterial txtPorcentajeReserva;
    private RSMaterialComponent.RSTextFieldMaterial txtTitulo;
    // End of variables declaration//GEN-END:variables
}
