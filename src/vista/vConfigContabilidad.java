/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.CicloContable;
import modelo.TipoCatalogo;
import modelo.dtoCicloContable;
import servicios.ServicioCicloContable;
import servicios.ServicioTipoCatalogo;
import utils.constantes.Constantes;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class vConfigContabilidad extends javax.swing.JPanel {

    /**
     * Creates new form vConfigContabilidad
     */
    // VARIABLES DE CICLO CONTABLE
    ServicioCicloContable _cicloContable = new ServicioCicloContable();
    ArrayList<dtoCicloContable> listaCiclosContables = new ArrayList<>();
    ArrayList<TipoCatalogo> listaCmbTipoCatalogo = new ArrayList<>();
    CicloContable cicloContableModel = new CicloContable();
    
    // VARIABLES DE TIPO DE CATALOGO
    ServicioTipoCatalogo _tipoCatalogo = new ServicioTipoCatalogo();
    ArrayList<TipoCatalogo> listaTiposCatalogos = new ArrayList<>();
    TipoCatalogo tipoCatalogoModel = new TipoCatalogo();
    
    
    // VARIABLES EN COMUN
    DefaultTableModel dtm = new DefaultTableModel();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    
    public vConfigContabilidad() {
        initComponents();
        this.cargarInfoSegunTab();
    }

    /* <------------------------------------------------------------------------------->
                                   INICIO SECCION DE CICLO CONTABLE
        <------------------------------------------------------------------------------->  */  
    public void setModelCicloContable() {
        String[] cabecera = {"Titulo","Desde","Hasta","Catalogo"};
        dtm.setColumnIdentifiers(cabecera);
        tblCicloContable.setModel(dtm);
    }
    
    public void obtenerListaCmbTipoCatalogo() {
        // obtenemos el listado de tipos de catalogo
        this.listaCmbTipoCatalogo = new ArrayList<>();
        RespuestaGeneral rg = _tipoCatalogo.obtenerLista();
        if (rg.esExitosa()) {
            this.listaCmbTipoCatalogo = (ArrayList<TipoCatalogo>)rg.getDatos();
            cmbTipoCatalogo.removeAllItems();
            for (TipoCatalogo itemTipoCatalogo : listaCmbTipoCatalogo) {
                cmbTipoCatalogo.addItem(itemTipoCatalogo.getTipo());
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo obtener el listado", "ALERTA", Constantes.devolverCodigoMensaje(rg));
        }
        
        //cmbTipoCatalogo.addItem();
    }
    
    public void setDatosCicloContable() {
        Object[] datos = new Object[dtm.getColumnCount()];
        for (dtoCicloContable ciclo : listaCiclosContables) {
            datos[0] = ciclo.getTitulo();
            datos[1] = sdf.format(ciclo.getDesde());
            datos[2] = sdf.format(ciclo.getHasta());
            datos[3] = ciclo.getCatalogo();
            dtm.addRow(datos);
        }
        tblCicloContable.setModel(dtm);
    }
    
    public void obtenerListadoCiclosContables() {
        this.listaCiclosContables = new ArrayList<>();
        tblCicloContable.clearSelection();
        this.limiparTablaCicloContable();
        RespuestaGeneral rs = _cicloContable.obtenerLista();
        if (rs.esExitosa()) {
            this.listaCiclosContables = (ArrayList<dtoCicloContable>)rs.getDatos();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo obtener el listado", "ALERTA", Constantes.devolverCodigoMensaje(rs));
        }
        this.setDatosCicloContable();
    }

    public void limiparTablaCicloContable() {
        for (int i = 0; i < tblCicloContable.getRowCount(); i++) {
            dtm.removeRow(i);
            i-=1;
        }
    }
    
    public void limpiarFormCicloContable() {
        cicloContableModel = new CicloContable();
        txtDesde.setDate(new Date());
        txtHasta.setDate(new Date());
        txtDesde.setFormatDate("dd-MM-yyyy");
        txtHasta.setFormatDate("dd-MM-yyyy");
        txtTitulo.setText("");
        btnEliminarCicloContable.setEnabled(false);
        btnEstablecerCicloContable.setEnabled(false);
        cmbTipoCatalogo.setSelectedIndex(0);
        tblCicloContable.clearSelection();
    }
    
    /* <------------------------------------------------------------------------------->
                                   FIN SECCION DE CICLO CONTABLE
        <------------------------------------------------------------------------------->  */  
    
    /* <------------------------------------------------------------------------------->
                                   INICIO SECCION DE TIPOS DE CATALOGO
        <------------------------------------------------------------------------------->  */  
    public void setModelTipoCatalogo() {
        String[] cabecera = {"Tipo"};
        dtm.setColumnIdentifiers(cabecera);
        tblCicloContable.setModel(dtm);
    }
    
    public void setDatosTipoCatalogo() {
        Object[] datos = new Object[dtm.getColumnCount()];
        for (TipoCatalogo tipo : listaTiposCatalogos) {
            datos[0] = tipo.getTipo();
            dtm.addRow(datos);
        }
        tblTipoCatalogo.setModel(dtm);
    }
    
    public void obtenerListadoTipoCatalogo() {
        this.listaTiposCatalogos = new ArrayList<>();
        tblTipoCatalogo.clearSelection();
        this.limiparTablaTipoCatalogo();
        RespuestaGeneral rs = _tipoCatalogo.obtenerLista();
        if (rs.esExitosa()) {
            this.listaTiposCatalogos = (ArrayList<TipoCatalogo>)rs.getDatos();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo obtener el listado", "ALERTA", Constantes.devolverCodigoMensaje(rs));
        }
        this.setDatosTipoCatalogo();
    }

    public void limiparTablaTipoCatalogo() {
        for (int i = 0; i < tblTipoCatalogo.getRowCount(); i++) {
            dtm.removeRow(i);
            i-=1;
        }
    }
    
    public void limpiarFormTipoCatalogo() {
        tipoCatalogoModel = new TipoCatalogo();
        txtTipoCatalogo.setText("");
        btnEliminarTipoCatalogo.setEnabled(false);
        tblTipoCatalogo.clearSelection();
    }
    
    /* <------------------------------------------------------------------------------->
                                   FIN SECCION DE TIPOS DE CATALOGO
        <------------------------------------------------------------------------------->  */  
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabPanelContabilidad = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtTitulo = new RSMaterialComponent.RSTextFieldMaterial();
        btnCancelarCicloContable = new RSMaterialComponent.RSButtonShapeIcon();
        btnGuardarCicloContable = new RSMaterialComponent.RSButtonShapeIcon();
        cmbTipoCatalogo = new RSMaterialComponent.RSComboBoxMaterial();
        txtHasta = new newscomponents.RSDateChooser();
        txtDesde = new newscomponents.RSDateChooser();
        btnEstablecerCicloContable = new RSMaterialComponent.RSButtonShapeIcon();
        btnEliminarCicloContable = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCicloContable = new rojerusan.RSTableMetro();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtTipoCatalogo = new RSMaterialComponent.RSTextFieldMaterial();
        btnCancelarTipoCatalogo = new RSMaterialComponent.RSButtonShapeIcon();
        btnGuardarTipoCatalogo = new RSMaterialComponent.RSButtonShapeIcon();
        btnEliminarTipoCatalogo = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTipoCatalogo = new rojerusan.RSTableMetro();
        jPanel5 = new javax.swing.JPanel();

        tabPanelContabilidad.setBackground(new java.awt.Color(255, 255, 255));
        tabPanelContabilidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabPanelContabilidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabPanelContabilidadStateChanged(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Registro y edición de ciclo contable  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        txtTitulo.setForeground(new java.awt.Color(0, 0, 0));
        txtTitulo.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtTitulo.setPhColor(new java.awt.Color(0, 0, 0));
        txtTitulo.setPlaceholder("Digite el titulo del ciclo contable");
        txtTitulo.setSelectionColor(new java.awt.Color(0, 0, 0));

        btnCancelarCicloContable.setBackground(new java.awt.Color(251, 205, 6));
        btnCancelarCicloContable.setText("CANCELAR");
        btnCancelarCicloContable.setBackgroundHover(new java.awt.Color(251, 174, 6));
        btnCancelarCicloContable.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCancelarCicloContable.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnCancelarCicloContable.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnCancelarCicloContable.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnCancelarCicloContable.setForegroundText(new java.awt.Color(0, 0, 0));
        btnCancelarCicloContable.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnCancelarCicloContable.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        btnCancelarCicloContable.setSizeIcon(18.0F);
        btnCancelarCicloContable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCicloContableActionPerformed(evt);
            }
        });

        btnGuardarCicloContable.setBackground(new java.awt.Color(33, 58, 86));
        btnGuardarCicloContable.setText("GUARDAR");
        btnGuardarCicloContable.setBackgroundHover(new java.awt.Color(33, 68, 86));
        btnGuardarCicloContable.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuardarCicloContable.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnGuardarCicloContable.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btnGuardarCicloContable.setSizeIcon(18.0F);
        btnGuardarCicloContable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCicloContableActionPerformed(evt);
            }
        });

        cmbTipoCatalogo.setBorder(null);
        cmbTipoCatalogo.setColorMaterial(new java.awt.Color(102, 102, 102));
        cmbTipoCatalogo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoCatalogoItemStateChanged(evt);
            }
        });

        txtHasta.setBackground(new java.awt.Color(153, 153, 153));
        txtHasta.setBgColor(new java.awt.Color(153, 153, 153));
        txtHasta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtHasta.setFormatDate("dd-MM-yyyy");

        txtDesde.setBackground(new java.awt.Color(153, 153, 153));
        txtDesde.setBgColor(new java.awt.Color(153, 153, 153));
        txtDesde.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtDesde.setFormatDate("dd-MM-yyyy");

        btnEstablecerCicloContable.setBackground(new java.awt.Color(0, 153, 0));
        btnEstablecerCicloContable.setText("ESTABLECER CICLO POR DEFECTO");
        btnEstablecerCicloContable.setBackgroundHover(new java.awt.Color(0, 167, 0));
        btnEstablecerCicloContable.setEnabled(false);
        btnEstablecerCicloContable.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEstablecerCicloContable.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnEstablecerCicloContable.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnEstablecerCicloContable.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnEstablecerCicloContable.setForegroundText(new java.awt.Color(0, 0, 0));
        btnEstablecerCicloContable.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnEstablecerCicloContable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEstablecerCicloContable.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CHECK);
        btnEstablecerCicloContable.setSizeIcon(18.0F);
        btnEstablecerCicloContable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstablecerCicloContableActionPerformed(evt);
            }
        });

        btnEliminarCicloContable.setBackground(new java.awt.Color(197, 0, 0));
        btnEliminarCicloContable.setText("ELIMINAR");
        btnEliminarCicloContable.setBackgroundHover(new java.awt.Color(242, 0, 0));
        btnEliminarCicloContable.setEnabled(false);
        btnEliminarCicloContable.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEliminarCicloContable.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnEliminarCicloContable.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnEliminarCicloContable.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnEliminarCicloContable.setForegroundText(new java.awt.Color(0, 0, 0));
        btnEliminarCicloContable.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnEliminarCicloContable.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ERROR);
        btnEliminarCicloContable.setSizeIcon(18.0F);
        btnEliminarCicloContable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCicloContableActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Titulo:");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Fecha desde:");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Fecha hasta:");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Tipo de catalogo:");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnGuardarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnEstablecerCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(cmbTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnEstablecerCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Listado de ciclos contables  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        tblCicloContable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblCicloContable.setBackgoundHead(new java.awt.Color(153, 153, 153));
        tblCicloContable.setBackgoundHover(new java.awt.Color(204, 204, 204));
        tblCicloContable.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        tblCicloContable.setColorSecundaryText(new java.awt.Color(51, 51, 51));
        tblCicloContable.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        tblCicloContable.setFontHead(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblCicloContable.setFontRowHover(new java.awt.Font("Arial", 1, 11)); // NOI18N
        tblCicloContable.setFontRowSelect(new java.awt.Font("Arial", 1, 11)); // NOI18N
        tblCicloContable.setSelectionBackground(new java.awt.Color(153, 153, 153));
        tblCicloContable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblCicloContable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCicloContable.setShowGrid(true);
        tblCicloContable.setShowVerticalLines(false);
        tblCicloContable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCicloContableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCicloContable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabPanelContabilidad.addTab("Ciclo Contable", jPanel1);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Registro y edición de tipos de catalogo    ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        txtTipoCatalogo.setForeground(new java.awt.Color(0, 0, 0));
        txtTipoCatalogo.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtTipoCatalogo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtTipoCatalogo.setPhColor(new java.awt.Color(0, 0, 0));
        txtTipoCatalogo.setPlaceholder("Digite el tipo de catalogo");
        txtTipoCatalogo.setSelectionColor(new java.awt.Color(0, 0, 0));

        btnCancelarTipoCatalogo.setBackground(new java.awt.Color(251, 205, 6));
        btnCancelarTipoCatalogo.setText("CANCELAR");
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

        btnEliminarTipoCatalogo.setBackground(new java.awt.Color(197, 0, 0));
        btnEliminarTipoCatalogo.setText("ELIMINAR");
        btnEliminarTipoCatalogo.setBackgroundHover(new java.awt.Color(242, 0, 0));
        btnEliminarTipoCatalogo.setEnabled(false);
        btnEliminarTipoCatalogo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEliminarTipoCatalogo.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnEliminarTipoCatalogo.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnEliminarTipoCatalogo.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnEliminarTipoCatalogo.setForegroundText(new java.awt.Color(0, 0, 0));
        btnEliminarTipoCatalogo.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnEliminarTipoCatalogo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ERROR);
        btnEliminarTipoCatalogo.setSizeIcon(18.0F);
        btnEliminarTipoCatalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTipoCatalogoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Tipo:");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(btnGuardarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(183, 183, 183))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGuardarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancelarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Listado de tipos de catalogo   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        tblTipoCatalogo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTipoCatalogo.setBackgoundHead(new java.awt.Color(153, 153, 153));
        tblTipoCatalogo.setBackgoundHover(new java.awt.Color(204, 204, 204));
        tblTipoCatalogo.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        tblTipoCatalogo.setColorSecundaryText(new java.awt.Color(51, 51, 51));
        tblTipoCatalogo.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        tblTipoCatalogo.setFontHead(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblTipoCatalogo.setFontRowHover(new java.awt.Font("Arial", 1, 11)); // NOI18N
        tblTipoCatalogo.setFontRowSelect(new java.awt.Font("Arial", 1, 11)); // NOI18N
        tblTipoCatalogo.setSelectionBackground(new java.awt.Color(153, 153, 153));
        tblTipoCatalogo.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblTipoCatalogo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblTipoCatalogo.setShowGrid(true);
        tblTipoCatalogo.setShowVerticalLines(false);
        tblTipoCatalogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTipoCatalogoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTipoCatalogo);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabPanelContabilidad.addTab("Tipos de Catálogos", jPanel2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1311, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 685, Short.MAX_VALUE)
        );

        tabPanelContabilidad.addTab("Catálogos", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanelContabilidad)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanelContabilidad)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarCicloContableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCicloContableActionPerformed
        this.limpiarFormCicloContable();
    }//GEN-LAST:event_btnCancelarCicloContableActionPerformed

    private void btnGuardarCicloContableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCicloContableActionPerformed
        // setear info al modelo
        this.cicloContableModel.setTitulo(this.txtTitulo.getText());
        this.cicloContableModel.setDesde(this.txtDesde.getDate());
        this.cicloContableModel.setHasta(this.txtHasta.getDate());
        this.seleccionarOpcionCmbTipoCatalogo();
        // guardamos la info
        // verificamos si es NUEVO
        btnGuardarCicloContable.setEnabled(false);
        if (this.cicloContableModel.getId() < 0) {
            RespuestaGeneral rs = _cicloContable.insertar(this.cicloContableModel);
            if (rs.esExitosa()) {
                JOptionPane.showMessageDialog(this, rs.getMensaje(), "INFORMACIÓN", Constantes.devolverCodigoMensaje(rs));
                this.obtenerListadoCiclosContables();
            } else {
                JOptionPane.showMessageDialog(this, rs.getMensaje(), "¡ALERTA!", Constantes.devolverCodigoMensaje(rs));
            }

            // verificamos si es NUEVO
        } else {
            RespuestaGeneral rs = _cicloContable.editar(this.cicloContableModel);
            if (rs.esExitosa()) {
                JOptionPane.showMessageDialog(this, rs.getMensaje(), "INFORMACIÓN", Constantes.devolverCodigoMensaje(rs));
                this.obtenerListadoCiclosContables();
            } else {
                JOptionPane.showMessageDialog(this, rs.getMensaje(), "¡ALERTA!", Constantes.devolverCodigoMensaje(rs));
            }
        }
        btnGuardarCicloContable.setEnabled(true);
        this.limpiarFormCicloContable();
    }//GEN-LAST:event_btnGuardarCicloContableActionPerformed

    private void btnEstablecerCicloContableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstablecerCicloContableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEstablecerCicloContableActionPerformed

    private void tblCicloContableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCicloContableMouseClicked
        int row = tblCicloContable.getSelectedRow();
        // seteamos el ID para editar
        cicloContableModel.setId(this.listaCiclosContables.get(row).getId());
        txtTitulo.setText(this.listaCiclosContables.get(row).getTitulo());
        txtDesde.setDate(this.listaCiclosContables.get(row).getDesde());
        txtHasta.setDate(this.listaCiclosContables.get(row).getHasta());
        txtDesde.setFormatDate("dd-MM-yyyy");
        txtHasta.setFormatDate("dd-MM-yyyy");
        int iCmb = 0, i = 0;
        for (TipoCatalogo tipoCatalogo : listaCmbTipoCatalogo) {
            if (tipoCatalogo.getId() == this.listaCiclosContables.get(row).getId_catalogo()) {
                iCmb = i;
            }
            i++;
        }
        cmbTipoCatalogo.setSelectedIndex(iCmb);
        btnEliminarCicloContable.setEnabled(true);
        btnEstablecerCicloContable.setEnabled(true);
    }//GEN-LAST:event_tblCicloContableMouseClicked

    private void tabPanelContabilidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabPanelContabilidadStateChanged
        this.cargarInfoSegunTab();
    }//GEN-LAST:event_tabPanelContabilidadStateChanged

    private void btnEliminarCicloContableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCicloContableActionPerformed
        String texto = "¿Esta seguro de continuar?, Se eliminará el registro: " + this.txtTitulo.getText();
        int opc = JOptionPane.showConfirmDialog(null, texto, "¡ALERTA!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        //System.out.println(salida);
        if (opc == 0) {
            RespuestaGeneral rg = _cicloContable.eliminar(this.cicloContableModel.getId());
            if (rg.esExitosa()) {
                JOptionPane.showMessageDialog(this, rg.getMensaje(), "INFORMACIÓN", Constantes.devolverCodigoMensaje(rg));
                btnEliminarCicloContable.setEnabled(false);
                btnEstablecerCicloContable.setEnabled(false);
                this.limpiarFormCicloContable();
                this.obtenerListadoCiclosContables();
            } else {
                JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", Constantes.devolverCodigoMensaje(rg));
            }
        }
    }//GEN-LAST:event_btnEliminarCicloContableActionPerformed

    private void btnCancelarTipoCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarTipoCatalogoActionPerformed
        this.limpiarFormTipoCatalogo();
    }//GEN-LAST:event_btnCancelarTipoCatalogoActionPerformed

    private void btnGuardarTipoCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarTipoCatalogoActionPerformed
        // setear info al modelo
        this.tipoCatalogoModel.setTipo(this.txtTipoCatalogo.getText());
        // guardamos la info
        // verificamos si es NUEVO
        btnGuardarTipoCatalogo.setEnabled(false);
        if (this.tipoCatalogoModel.getId() < 0) {
            RespuestaGeneral rs = _tipoCatalogo.insertar(this.tipoCatalogoModel);
            if (rs.esExitosa()) {
                JOptionPane.showMessageDialog(this, rs.getMensaje(), "INFORMACIÓN", Constantes.devolverCodigoMensaje(rs));
                this.obtenerListadoTipoCatalogo();
            } else {
                JOptionPane.showMessageDialog(this, rs.getMensaje(), "¡ALERTA!", Constantes.devolverCodigoMensaje(rs));
            }

            // verificamos si es NUEVO
        } else {
            RespuestaGeneral rs = _tipoCatalogo.editar(this.tipoCatalogoModel);
            if (rs.esExitosa()) {
                JOptionPane.showMessageDialog(this, rs.getMensaje(), "INFORMACIÓN", Constantes.devolverCodigoMensaje(rs));
                this.obtenerListadoTipoCatalogo();
            } else {
                JOptionPane.showMessageDialog(this, rs.getMensaje(), "¡ALERTA!", Constantes.devolverCodigoMensaje(rs));
            }
        }
        btnGuardarTipoCatalogo.setEnabled(true);
        this.limpiarFormTipoCatalogo();
    }//GEN-LAST:event_btnGuardarTipoCatalogoActionPerformed

    private void btnEliminarTipoCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTipoCatalogoActionPerformed
        String texto = "¿Esta seguro de continuar?, Se eliminará el registro: " + this.txtTipoCatalogo.getText();
        int opc = JOptionPane.showConfirmDialog(null, texto, "¡ALERTA!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        //System.out.println(salida);
        if (opc == 0) {
            RespuestaGeneral rg = _tipoCatalogo.eliminar(this.tipoCatalogoModel.getId());
            if (rg.esExitosa()) {
                JOptionPane.showMessageDialog(this, rg.getMensaje(), "INFORMACIÓN", Constantes.devolverCodigoMensaje(rg));
                btnEliminarTipoCatalogo.setEnabled(false);
                this.limpiarFormTipoCatalogo();
                this.obtenerListadoTipoCatalogo();
            } else {
                JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", Constantes.devolverCodigoMensaje(rg));
            }
        }
    }//GEN-LAST:event_btnEliminarTipoCatalogoActionPerformed
    
    private void tblTipoCatalogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTipoCatalogoMouseClicked
        int row = tblTipoCatalogo.getSelectedRow();
        // seteamos el ID para editar
        tipoCatalogoModel.setId(this.listaTiposCatalogos.get(row).getId());
        txtTipoCatalogo.setText(this.listaTiposCatalogos.get(row).getTipo());
        btnEliminarTipoCatalogo.setEnabled(true);
    }//GEN-LAST:event_tblTipoCatalogoMouseClicked

    private void cmbTipoCatalogoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoCatalogoItemStateChanged
        //this.seleccionarOpcionCmbTipoCatalogo();
    }//GEN-LAST:event_cmbTipoCatalogoItemStateChanged

    public void seleccionarOpcionCmbTipoCatalogo() {
        int i = cmbTipoCatalogo.getSelectedIndex();
        if (i >= 0) {
            this.cicloContableModel.setId_catalogo(this.listaCmbTipoCatalogo.get(i).getId());
        }
    }
    
    public void cargarInfoSegunTab() {
        this.limiparTablaTipoCatalogo();
        this.limpiarFormTipoCatalogo();
        this.limpiarFormCicloContable();
        this.limiparTablaCicloContable();
        switch (this.tabPanelContabilidad.getSelectedIndex()) {
            case 0 -> this.iniciarCicloContable();
            case 1 -> this.iniciarTiposDeCatalogos();
            case 2 -> this.iniciarCatalogos();
            default -> this.iniciarCicloContable();
        }
    }
    
    public void iniciarCicloContable() {
        this.setModelCicloContable();
        this.obtenerListaCmbTipoCatalogo();
        this.obtenerListadoCiclosContables();
    }
    
    public void iniciarTiposDeCatalogos() {
        this.setModelTipoCatalogo();
        this.obtenerListadoTipoCatalogo();
    }
    
    public void iniciarCatalogos() {
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShapeIcon btnCancelarCicloContable;
    private RSMaterialComponent.RSButtonShapeIcon btnCancelarTipoCatalogo;
    private RSMaterialComponent.RSButtonShapeIcon btnEliminarCicloContable;
    private RSMaterialComponent.RSButtonShapeIcon btnEliminarTipoCatalogo;
    private RSMaterialComponent.RSButtonShapeIcon btnEstablecerCicloContable;
    private RSMaterialComponent.RSButtonShapeIcon btnGuardarCicloContable;
    private RSMaterialComponent.RSButtonShapeIcon btnGuardarTipoCatalogo;
    private RSMaterialComponent.RSComboBoxMaterial cmbTipoCatalogo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane tabPanelContabilidad;
    private rojerusan.RSTableMetro tblCicloContable;
    private rojerusan.RSTableMetro tblTipoCatalogo;
    private newscomponents.RSDateChooser txtDesde;
    private newscomponents.RSDateChooser txtHasta;
    private RSMaterialComponent.RSTextFieldMaterial txtTipoCatalogo;
    private RSMaterialComponent.RSTextFieldMaterial txtTitulo;
    // End of variables declaration//GEN-END:variables
}
