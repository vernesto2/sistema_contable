/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import dto.dtoCuenta;
import dto.dtoLista;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.CicloContable;
import modelo.ConfiguracionUsuario;
import modelo.Cuenta;
import modelo.TipoCatalogo;
import modelo.dtoCicloContable;
import rojerusan.RSTableMetro;
import servicios.ServicioCicloContable;
import servicios.ServicioConfigUsuario;
import servicios.ServicioCuenta;
import servicios.ServicioTipoCatalogo;
import utils.UtileriaVista;
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
    CicloContable cicloContableModel = new CicloContable();
    ServicioConfigUsuario _configUsuario = new ServicioConfigUsuario();
    
    // VARIABLES DE TIPO DE CATALOGO
    ServicioTipoCatalogo _tipoCatalogo = new ServicioTipoCatalogo();
    ArrayList<TipoCatalogo> listaTiposCatalogos = new ArrayList<>();
    TipoCatalogo tipoCatalogoModel = new TipoCatalogo();
    
    // VARIABLES DE CUENTAS
    ServicioCuenta _cuenta = new ServicioCuenta();
    ArrayList<dtoCuenta> listaCuentas = new ArrayList<>();
    ArrayList<dtoLista> listaCmbTipoSaldo = new ArrayList<>();
    Cuenta cuentaModel = new Cuenta();
    
    // VARIABLES EN COMUN
    DefaultTableModel dtm = new DefaultTableModel();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    ArrayList<TipoCatalogo> listaCmbTipoCatalogo = new ArrayList<>();
    
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
            JOptionPane.showMessageDialog(this, "No se pudo obtener el listado", "ALERTA", UtileriaVista.devolverCodigoMensaje(rg));
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
        tblCicloContable.setAutoResizeMode(tblCicloContable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tblCicloContable.getColumnModel().getColumn(0).setPreferredWidth(220);
        tblCicloContable.getColumnModel().getColumn(1).setPreferredWidth(20);
        tblCicloContable.getColumnModel().getColumn(2).setPreferredWidth(20);
        tblCicloContable.getColumnModel().getColumn(3).setPreferredWidth(100);
    }
    
    public void obtenerListadoCiclosContables() {
        this.listaCiclosContables = new ArrayList<>();
        tblCicloContable.clearSelection();
        this.limiparTablaCicloContable();
        RespuestaGeneral rs = _cicloContable.obtenerLista();
        if (rs.esExitosa()) {
            this.listaCiclosContables = (ArrayList<dtoCicloContable>)rs.getDatos();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo obtener el listado", "ALERTA", UtileriaVista.devolverCodigoMensaje(rs));
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
        tblCuentas.setAutoResizeMode(tblCuentas.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
    }
    
    public void obtenerListadoTipoCatalogo() {
        this.listaTiposCatalogos = new ArrayList<>();
        tblTipoCatalogo.clearSelection();
        this.limiparTablaTipoCatalogo();
        RespuestaGeneral rs = _tipoCatalogo.obtenerLista();
        if (rs.esExitosa()) {
            this.listaTiposCatalogos = (ArrayList<TipoCatalogo>)rs.getDatos();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo obtener el listado", "ALERTA", UtileriaVista.devolverCodigoMensaje(rs));
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
    
    /* <------------------------------------------------------------------------------->
                                   INICIO SECCION DE CUENTAS
        <------------------------------------------------------------------------------->  */  
    public void setModelCuentas() {
        String[] cabecera = {"Nivel", "Codigo", "Concepto", "Tipo de Saldo", "Ingresos", "Egresos"};
        dtm.setColumnIdentifiers(cabecera);
        tblCuentas.setModel(dtm);
    }
    
    public void obtenerListaCmbTipoCatalogo2() {
        // obtenemos el listado de tipos de catalogo
        this.listaCmbTipoCatalogo = new ArrayList<>();
        RespuestaGeneral rg = _tipoCatalogo.obtenerLista();
        if (rg.esExitosa()) {
            this.listaCmbTipoCatalogo = (ArrayList<TipoCatalogo>)rg.getDatos();
            cmbTipoCatalogo2.removeAllItems();
            for (TipoCatalogo itemTipoCatalogo : listaCmbTipoCatalogo) {
                cmbTipoCatalogo2.addItem(itemTipoCatalogo.getTipo());
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo obtener el listado", "ALERTA", UtileriaVista.devolverCodigoMensaje(rg));
        }
    }
    
    public void obtenerListaCmbTipoSaldo() {
        this.listaCmbTipoSaldo = Constantes.listaTiposSaldoCuentas();
        cmbTipoSaldo.removeAllItems();
        for (dtoLista item : listaCmbTipoSaldo) {
            cmbTipoSaldo.addItem(item.getLabel());
        }
    }
    
    public void setDatosCuentas() {
        Object[] datos = new Object[dtm.getColumnCount()];
        for (dtoCuenta cuenta : listaCuentas) {
            datos[0] = cuenta.getNivel();
            datos[1] = cuenta.getCodigo();
            datos[2] = cuenta.getNombre();
            datos[3] = cuenta.getTipo_saldo();
            datos[4] = cuenta.getIngresos();
            datos[5] = cuenta.getEgresos();
            dtm.addRow(datos);
        }
        tblCuentas.setModel(dtm);
        tblCuentas.setAutoResizeMode(tblCuentas.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tblCuentas.getColumnModel().getColumn(0).setPreferredWidth(150);
        tblCuentas.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblCuentas.getColumnModel().getColumn(2).setPreferredWidth(485);
        tblCuentas.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblCuentas.getColumnModel().getColumn(4).setPreferredWidth(150);
        tblCuentas.getColumnModel().getColumn(5).setPreferredWidth(150);
        //tblCuentas.setModel(dtm);
       
    }
    
    public void obtenerListadoCuentasPorTipoCatalogo() {
        this.listaCuentas = new ArrayList<>();
        tblCuentas.clearSelection();
        this.limiparTablaCuentas();
        this.seleccionarOpcionCmbTipoCatalogo2();
        RespuestaGeneral rg = _cuenta.obtenerListaPorIdTipoCatalogo(this.cuentaModel.getId_tipo_catalogo(), this.txtQueryBusqueda.getText());
        if (rg.esExitosa()) {
            this.listaCuentas = (ArrayList<dtoCuenta>)rg.getDatos();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo obtener el listado", "ALERTA", UtileriaVista.devolverCodigoMensaje(rg));
        }
        this.setDatosCuentas();
    }

    public void limiparTablaCuentas() {
        for (int i = 0; i < tblCuentas.getRowCount(); i++) {
            dtm.removeRow(i);
            i-=1;
        }
    }
    
    public void limpiarFormCuentas() {
        cuentaModel = new Cuenta();
        txtCodigo.setText("");
        txtConcepto.setText("");
        txtNivel.setText("");
        txtIngresos.setText("");
        txtEgresos.setText("");
        cmbTipoSaldo.setSelectedIndex(0);
        btnEliminarCuenta.setEnabled(false);
        tblCuentas.clearSelection();
    }
    
    /* <------------------------------------------------------------------------------->
                                   FIN SECCION DE CUENTAS
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
        btnCancelarCicloContable = new RSMaterialComponent.RSButtonShapeIcon();
        btnGuardarCicloContable = new RSMaterialComponent.RSButtonShapeIcon();
        cmbTipoCatalogo = new RSMaterialComponent.RSComboBoxMaterial();
        txtHasta = new newscomponents.RSDateChooser();
        txtDesde = new newscomponents.RSDateChooser();
        btnEstablecerCicloContable = new RSMaterialComponent.RSButtonShapeIcon();
        btnEliminarCicloContable = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTitulo = new RSMaterialComponent.RSTextFieldMaterial();
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
        jPanel8 = new javax.swing.JPanel();
        txtCodigo = new RSMaterialComponent.RSTextFieldMaterial();
        btnCancelarCuenta = new RSMaterialComponent.RSButtonShapeIcon();
        btnGuardarCuenta = new RSMaterialComponent.RSButtonShapeIcon();
        cmbTipoCatalogo2 = new RSMaterialComponent.RSComboBoxMaterial();
        btnEliminarCuenta = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtConcepto = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel11 = new javax.swing.JLabel();
        txtNivel = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel12 = new javax.swing.JLabel();
        cmbTipoSaldo = new RSMaterialComponent.RSComboBoxMaterial();
        txtIngresos = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtEgresos = new RSMaterialComponent.RSTextFieldMaterial();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCuentas = new rojerusan.RSTableMetro();
        txtQueryBusqueda = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel15 = new javax.swing.JLabel();
        btnLimpiarBusquedaCuenta = new RSMaterialComponent.RSButtonShapeIcon();
        btnBuscarCuenta = new RSMaterialComponent.RSButtonShapeIcon();

        tabPanelContabilidad.setBackground(new java.awt.Color(255, 255, 255));
        tabPanelContabilidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabPanelContabilidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabPanelContabilidadStateChanged(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Registro y edición de ciclo contable   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        btnCancelarCicloContable.setBackground(new java.awt.Color(251, 205, 6));
        btnCancelarCicloContable.setText("CANCELAR");
        btnCancelarCicloContable.setToolTipText("LIMPIAR EL FORMULARIO Y SELECCIÓN");
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
        btnGuardarCicloContable.setToolTipText("GUARDAR O ACTUALIZAR EL CICLO CONTABLE");
        btnGuardarCicloContable.setBackgroundHover(new java.awt.Color(33, 84, 86));
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
        btnEstablecerCicloContable.setToolTipText("ESTABLECER POR DEFECTO EL CICLO CONTABLE SELECCIONADO");
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
        btnEliminarCicloContable.setToolTipText("ELIMINAR EL REGISTRO SELECCIONADO");
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(btnGuardarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnEstablecerCicloContable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEstablecerCicloContable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
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
        tblCicloContable.setPositionText(rojerusan.RSTableMetro.POSITION_TEXT.LEFT);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabPanelContabilidad.addTab("Ciclo Contable", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

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

        btnEliminarTipoCatalogo.setBackground(new java.awt.Color(197, 0, 0));
        btnEliminarTipoCatalogo.setText("ELIMINAR");
        btnEliminarTipoCatalogo.setToolTipText("ELIMINAR EL REGISTRO SELECCIONADO");
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
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTipoCatalogo, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addGap(43, 43, 43)
                .addComponent(btnGuardarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
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
        tblTipoCatalogo.setPositionText(rojerusan.RSTableMetro.POSITION_TEXT.LEFT);
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabPanelContabilidad.addTab("Tipos de Catálogos", jPanel2);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Registro y edición de cuentas de catalogos    ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        txtCodigo.setForeground(new java.awt.Color(0, 0, 0));
        txtCodigo.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtCodigo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtCodigo.setPhColor(new java.awt.Color(0, 0, 0));
        txtCodigo.setPlaceholder("Digite el codigo de la cuenta");
        txtCodigo.setSelectionColor(new java.awt.Color(0, 0, 0));

        btnCancelarCuenta.setBackground(new java.awt.Color(251, 205, 6));
        btnCancelarCuenta.setText("CANCELAR");
        btnCancelarCuenta.setToolTipText("LIMPIAR EL FORMULARIO Y SELECCIÓN");
        btnCancelarCuenta.setBackgroundHover(new java.awt.Color(251, 174, 6));
        btnCancelarCuenta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCancelarCuenta.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnCancelarCuenta.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnCancelarCuenta.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnCancelarCuenta.setForegroundText(new java.awt.Color(0, 0, 0));
        btnCancelarCuenta.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnCancelarCuenta.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        btnCancelarCuenta.setSizeIcon(18.0F);
        btnCancelarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCuentaActionPerformed(evt);
            }
        });

        btnGuardarCuenta.setBackground(new java.awt.Color(33, 58, 86));
        btnGuardarCuenta.setText("GUARDAR");
        btnGuardarCuenta.setToolTipText("GUARDAR O ACTUALIZAR LA CUENTA");
        btnGuardarCuenta.setBackgroundHover(new java.awt.Color(33, 93, 86));
        btnGuardarCuenta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuardarCuenta.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnGuardarCuenta.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btnGuardarCuenta.setSizeIcon(18.0F);
        btnGuardarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCuentaActionPerformed(evt);
            }
        });

        cmbTipoCatalogo2.setColorMaterial(new java.awt.Color(102, 102, 102));
        cmbTipoCatalogo2.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        cmbTipoCatalogo2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoCatalogo2ItemStateChanged(evt);
            }
        });

        btnEliminarCuenta.setBackground(new java.awt.Color(197, 0, 0));
        btnEliminarCuenta.setText("ELIMINAR");
        btnEliminarCuenta.setToolTipText("ELIMINAR EL REGISTRO SELECCIONADO");
        btnEliminarCuenta.setBackgroundHover(new java.awt.Color(242, 0, 0));
        btnEliminarCuenta.setEnabled(false);
        btnEliminarCuenta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEliminarCuenta.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnEliminarCuenta.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnEliminarCuenta.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnEliminarCuenta.setForegroundText(new java.awt.Color(0, 0, 0));
        btnEliminarCuenta.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnEliminarCuenta.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ERROR);
        btnEliminarCuenta.setSizeIcon(18.0F);
        btnEliminarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCuentaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Codigo:");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Tipo de catalogo:");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Concepto:");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtConcepto.setForeground(new java.awt.Color(0, 0, 0));
        txtConcepto.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtConcepto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtConcepto.setPhColor(new java.awt.Color(0, 0, 0));
        txtConcepto.setPlaceholder("Digite el concepto de la cuenta");
        txtConcepto.setSelectionColor(new java.awt.Color(0, 0, 0));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Nivel:");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtNivel.setForeground(new java.awt.Color(0, 0, 0));
        txtNivel.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtNivel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtNivel.setPhColor(new java.awt.Color(0, 0, 0));
        txtNivel.setPlaceholder("Digite el nivel de la cuenta");
        txtNivel.setSelectionColor(new java.awt.Color(0, 0, 0));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Tipo de saldo:");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        cmbTipoSaldo.setColorMaterial(new java.awt.Color(102, 102, 102));
        cmbTipoSaldo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoSaldoItemStateChanged(evt);
            }
        });

        txtIngresos.setForeground(new java.awt.Color(0, 0, 0));
        txtIngresos.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtIngresos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtIngresos.setPhColor(new java.awt.Color(0, 0, 0));
        txtIngresos.setPlaceholder("Digite el ingreso");
        txtIngresos.setSelectionColor(new java.awt.Color(0, 0, 0));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Ingresos:");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Egresos:");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtEgresos.setForeground(new java.awt.Color(0, 0, 0));
        txtEgresos.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtEgresos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtEgresos.setPhColor(new java.awt.Color(0, 0, 0));
        txtEgresos.setPlaceholder("Digite el egreso");
        txtEgresos.setSelectionColor(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtConcepto, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(txtIngresos, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(txtEgresos, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(cmbTipoCatalogo2, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(cmbTipoSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(txtNivel, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnGuardarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbTipoSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(9, 9, 9)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(cmbTipoCatalogo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIngresos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEgresos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Listado de catalogo de cuentas   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        tblCuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblCuentas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblCuentas.setBackgoundHead(new java.awt.Color(153, 153, 153));
        tblCuentas.setBackgoundHover(new java.awt.Color(204, 204, 204));
        tblCuentas.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        tblCuentas.setColorSecundaryText(new java.awt.Color(51, 51, 51));
        tblCuentas.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        tblCuentas.setFontHead(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblCuentas.setFontRowHover(new java.awt.Font("Arial", 1, 11)); // NOI18N
        tblCuentas.setFontRowSelect(new java.awt.Font("Arial", 1, 11)); // NOI18N
        tblCuentas.setPositionText(rojerusan.RSTableMetro.POSITION_TEXT.LEFT);
        tblCuentas.setSelectionBackground(new java.awt.Color(153, 153, 153));
        tblCuentas.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblCuentas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCuentas.setShowGrid(true);
        tblCuentas.setShowVerticalLines(false);
        tblCuentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCuentasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblCuentas);

        txtQueryBusqueda.setForeground(new java.awt.Color(0, 0, 0));
        txtQueryBusqueda.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtQueryBusqueda.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtQueryBusqueda.setPhColor(new java.awt.Color(0, 0, 0));
        txtQueryBusqueda.setPlaceholder("Busqueda por codigo o concepto");
        txtQueryBusqueda.setSelectionColor(new java.awt.Color(0, 0, 0));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Busqueda:");
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        btnLimpiarBusquedaCuenta.setBackground(new java.awt.Color(102, 102, 102));
        btnLimpiarBusquedaCuenta.setText("LIMPIAR");
        btnLimpiarBusquedaCuenta.setToolTipText("LIMPIAR LA BUSQUEDA");
        btnLimpiarBusquedaCuenta.setBackgroundHover(new java.awt.Color(112, 113, 116));
        btnLimpiarBusquedaCuenta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLimpiarBusquedaCuenta.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnLimpiarBusquedaCuenta.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btnLimpiarBusquedaCuenta.setSizeIcon(18.0F);
        btnLimpiarBusquedaCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarBusquedaCuentaActionPerformed(evt);
            }
        });

        btnBuscarCuenta.setBackground(new java.awt.Color(33, 58, 86));
        btnBuscarCuenta.setText("BUSCAR");
        btnBuscarCuenta.setToolTipText("BUSCAR POR CODIGO O CONCEPTO DE CUENTA");
        btnBuscarCuenta.setBackgroundHover(new java.awt.Color(33, 93, 86));
        btnBuscarCuenta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBuscarCuenta.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnBuscarCuenta.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btnBuscarCuenta.setSizeIcon(18.0F);
        btnBuscarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCuentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtQueryBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiarBusquedaCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtQueryBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLimpiarBusquedaCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                JOptionPane.showMessageDialog(this, rs.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rs));
                this.obtenerListadoCiclosContables();
            } else {
                JOptionPane.showMessageDialog(this, rs.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rs));
            }

            // verificamos si es NUEVO
        } else {
            RespuestaGeneral rs = _cicloContable.editar(this.cicloContableModel);
            if (rs.esExitosa()) {
                JOptionPane.showMessageDialog(this, rs.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rs));
                this.obtenerListadoCiclosContables();
            } else {
                JOptionPane.showMessageDialog(this, rs.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rs));
            }
        }
        btnGuardarCicloContable.setEnabled(true);
        this.limpiarFormCicloContable();
    }//GEN-LAST:event_btnGuardarCicloContableActionPerformed

    private void btnEstablecerCicloContableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstablecerCicloContableActionPerformed
        Constantes.configUsuario.setId_ciclo_contable(this.cicloContableModel.getId());
        ArrayList<ConfiguracionUsuario> listaConfigUsuario = new ArrayList<>();
        RespuestaGeneral rg = _configUsuario.editar(Constantes.configUsuario);
        if (rg.esExitosa()) {
            RespuestaGeneral rg1 = _configUsuario.obtenerPorIdUsuario(Constantes.usuario.getId());
            if (rg1.esExitosa()) {
                listaConfigUsuario = (ArrayList<ConfiguracionUsuario>)rg1.getDatos();
                Constantes.configUsuario = listaConfigUsuario.get(0);
                btnEstablecerCicloContable.setEnabled(false);
                tblCicloContable.clearSelection();
                this.limpiarFormCicloContable();
                JOptionPane.showMessageDialog(this, "Se establecio el ciclo contable por defecto", "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rg));
                vPrincipal.txtConfigCicloContable.setText(Constantes.configUsuario.nombreCicloYCatalogo());
            } else {
                JOptionPane.showMessageDialog(this, rg1.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg1));
            }
        } else {
            JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
        }
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
                JOptionPane.showMessageDialog(this, rg.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rg));
                btnEliminarCicloContable.setEnabled(false);
                btnEstablecerCicloContable.setEnabled(false);
                this.limpiarFormCicloContable();
                this.obtenerListadoCiclosContables();
            } else {
                JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
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
                JOptionPane.showMessageDialog(this, rs.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rs));
                this.obtenerListadoTipoCatalogo();
            } else {
                JOptionPane.showMessageDialog(this, rs.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rs));
            }

            // verificamos si es NUEVO
        } else {
            RespuestaGeneral rs = _tipoCatalogo.editar(this.tipoCatalogoModel);
            if (rs.esExitosa()) {
                JOptionPane.showMessageDialog(this, rs.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rs));
                this.obtenerListadoTipoCatalogo();
            } else {
                JOptionPane.showMessageDialog(this, rs.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rs));
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
                JOptionPane.showMessageDialog(this, rg.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rg));
                btnEliminarTipoCatalogo.setEnabled(false);
                this.limpiarFormTipoCatalogo();
                this.obtenerListadoTipoCatalogo();
            } else {
                JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
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

    private void btnCancelarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCuentaActionPerformed
        this.limpiarFormCuentas();
    }//GEN-LAST:event_btnCancelarCuentaActionPerformed

    private void btnGuardarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCuentaActionPerformed
        // setear info al modelo
        this.cuentaModel.setNombre(this.txtConcepto.getText());
        this.cuentaModel.setCodigo(this.txtCodigo.getText());
        this.cuentaModel.setEgresos(this.txtEgresos.getText());
        this.cuentaModel.setIngresos(this.txtIngresos.getText());
        // validamos si el nivel ha sido ingresado
        if (this.txtNivel.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se ha ingresado el nivel", "INFORMACIÓN", JOptionPane.WARNING_MESSAGE);
        } else {
            this.cuentaModel.setNivel(Integer.parseInt(this.txtNivel.getText()));
            this.seleccionarOpcionCmbTipoCatalogo2();
            this.seleccionarOpcionCmbTipoSaldo();
            // guardamos la info
            // verificamos si es NUEVO
            btnGuardarCuenta.setEnabled(false);
            if (this.cuentaModel.getId() < 0) {
                RespuestaGeneral rs = _cuenta.insertar(this.cuentaModel);
                if (rs.esExitosa()) {
                    JOptionPane.showMessageDialog(this, rs.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rs));
                    this.limpiarFormCuentas();
                    this.obtenerListadoCuentasPorTipoCatalogo();
                } else {
                    JOptionPane.showMessageDialog(this, rs.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rs));
                }
                
                // verificamos si es NUEVO
            } else {
                RespuestaGeneral rs = _cuenta.editar(this.cuentaModel);
                if (rs.esExitosa()) {
                    JOptionPane.showMessageDialog(this, rs.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rs));
                    this.limpiarFormCuentas();
                    this.obtenerListadoCuentasPorTipoCatalogo();
                } else {
                    JOptionPane.showMessageDialog(this, rs.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rs));
                }
            }
            btnGuardarCuenta.setEnabled(true);
        }
        
    }//GEN-LAST:event_btnGuardarCuentaActionPerformed

    private void cmbTipoCatalogo2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoCatalogo2ItemStateChanged
        this.seleccionarOpcionCmbTipoCatalogo2();
        this.limiparTablaCuentas();
        this.obtenerListadoCuentasPorTipoCatalogo();
    }//GEN-LAST:event_cmbTipoCatalogo2ItemStateChanged

    private void btnEliminarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCuentaActionPerformed
        String texto = "¿Esta seguro de continuar?, Se eliminará el registro: \n" + this.txtConcepto.getText();
        int opc = JOptionPane.showConfirmDialog(null, texto, "¡ALERTA!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (opc == 0) {
            RespuestaGeneral rg = _cuenta.eliminar(this.cuentaModel.getId());
            if (rg.esExitosa()) {
                JOptionPane.showMessageDialog(this, rg.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rg));
                btnEliminarCuenta.setEnabled(false);
                this.limpiarFormCuentas();
                this.obtenerListadoCuentasPorTipoCatalogo();
            } else {
                JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
            }
        }
    }//GEN-LAST:event_btnEliminarCuentaActionPerformed

    private void tblCuentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCuentasMouseClicked
        int row = tblCuentas.getSelectedRow();
        // seteamos el ID para editar
        cuentaModel.setId(this.listaCuentas.get(row).getId());
        txtConcepto.setText(this.listaCuentas.get(row).getNombre());
        txtCodigo.setText(this.listaCuentas.get(row).getCodigo());
        txtNivel.setText(String.valueOf(this.listaCuentas.get(row).getNivel()));
        txtIngresos.setText(this.listaCuentas.get(row).getIngresos());
        txtEgresos.setText(this.listaCuentas.get(row).getEgresos());
        int iCmb = 0, i = 0;
        for (dtoLista item : listaCmbTipoSaldo) {
            if (item.getValue().equals(this.listaCuentas.get(row).getTipo_saldo())) {
                iCmb = i;
            }
            i++;
        }
        cmbTipoSaldo.setSelectedIndex(iCmb);
        btnEliminarCuenta.setEnabled(true);
    }//GEN-LAST:event_tblCuentasMouseClicked

    private void cmbTipoSaldoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoSaldoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoSaldoItemStateChanged

    private void btnLimpiarBusquedaCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarBusquedaCuentaActionPerformed
        this.txtQueryBusqueda.setText("");
        this.obtenerListadoCuentasPorTipoCatalogo();
    }//GEN-LAST:event_btnLimpiarBusquedaCuentaActionPerformed

    private void btnBuscarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCuentaActionPerformed
        this.obtenerListadoCuentasPorTipoCatalogo();
    }//GEN-LAST:event_btnBuscarCuentaActionPerformed

    public void seleccionarOpcionCmbTipoCatalogo() {
        int i = cmbTipoCatalogo.getSelectedIndex();
        if (i >= 0) {
            this.cicloContableModel.setId_catalogo(this.listaCmbTipoCatalogo.get(i).getId());
        }
    }
    
    public void seleccionarOpcionCmbTipoCatalogo2() {
        int i = cmbTipoCatalogo2.getSelectedIndex();
        if (i >= 0) {
            this.cuentaModel.setId_tipo_catalogo(this.listaCmbTipoCatalogo.get(i).getId());
        }
    }
    
    public void seleccionarOpcionCmbTipoSaldo() {
        int i = cmbTipoSaldo.getSelectedIndex();
        if (i >= 0) {
            this.cuentaModel.setTipo_saldo(this.listaCmbTipoSaldo.get(i).getValue());
        }
    }
    
    public void cargarInfoSegunTab() {
        this.limiparTablaTipoCatalogo();
        this.limpiarFormTipoCatalogo();
        this.limpiarFormCicloContable();
        this.limiparTablaCicloContable();
        this.limiparTablaCuentas();
        this.limpiarFormCuentas();
        switch (this.tabPanelContabilidad.getSelectedIndex()) {
            case 0 -> this.iniciarCicloContable();
            case 1 -> this.iniciarTiposDeCatalogos();
            case 2 -> this.iniciarCatalogos();
            default -> this.iniciarCatalogos();
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
        this.setModelCuentas();
        this.obtenerListaCmbTipoSaldo();
        this.obtenerListaCmbTipoCatalogo2();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShapeIcon btnBuscarCuenta;
    private RSMaterialComponent.RSButtonShapeIcon btnCancelarCicloContable;
    private RSMaterialComponent.RSButtonShapeIcon btnCancelarCuenta;
    private RSMaterialComponent.RSButtonShapeIcon btnCancelarTipoCatalogo;
    private RSMaterialComponent.RSButtonShapeIcon btnEliminarCicloContable;
    private RSMaterialComponent.RSButtonShapeIcon btnEliminarCuenta;
    private RSMaterialComponent.RSButtonShapeIcon btnEliminarTipoCatalogo;
    private RSMaterialComponent.RSButtonShapeIcon btnEstablecerCicloContable;
    private RSMaterialComponent.RSButtonShapeIcon btnGuardarCicloContable;
    private RSMaterialComponent.RSButtonShapeIcon btnGuardarCuenta;
    private RSMaterialComponent.RSButtonShapeIcon btnGuardarTipoCatalogo;
    private RSMaterialComponent.RSButtonShapeIcon btnLimpiarBusquedaCuenta;
    private RSMaterialComponent.RSComboBoxMaterial cmbTipoCatalogo;
    private RSMaterialComponent.RSComboBoxMaterial cmbTipoCatalogo2;
    private RSMaterialComponent.RSComboBoxMaterial cmbTipoSaldo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane tabPanelContabilidad;
    private rojerusan.RSTableMetro tblCicloContable;
    private rojerusan.RSTableMetro tblCuentas;
    private rojerusan.RSTableMetro tblTipoCatalogo;
    private RSMaterialComponent.RSTextFieldMaterial txtCodigo;
    private RSMaterialComponent.RSTextFieldMaterial txtConcepto;
    private newscomponents.RSDateChooser txtDesde;
    private RSMaterialComponent.RSTextFieldMaterial txtEgresos;
    private newscomponents.RSDateChooser txtHasta;
    private RSMaterialComponent.RSTextFieldMaterial txtIngresos;
    private RSMaterialComponent.RSTextFieldMaterial txtNivel;
    private RSMaterialComponent.RSTextFieldMaterial txtQueryBusqueda;
    private RSMaterialComponent.RSTextFieldMaterial txtTipoCatalogo;
    private RSMaterialComponent.RSTextFieldMaterial txtTitulo;
    // End of variables declaration//GEN-END:variables
}
