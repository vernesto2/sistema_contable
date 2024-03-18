/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import dto.dtoCuenta;
import dto.dtoLista;
import formularios.dCicloContable;
import formularios.dCuentas;
import formularios.dTipoCatalogo;
import java.awt.Color;
import java.awt.Cursor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.CicloContable;
import modelo.ConfiguracionUsuario;
import modelo.Cuenta;
import modelo.TipoCatalogo;
import rojeru_san.efectos.ValoresEnum;
import servicios.ServicioCicloContable;
import servicios.ServicioConfigUsuario;
import servicios.ServicioCuenta;
import servicios.ServicioTipoCatalogo;
import sesion.Sesion;
import utils.Render;
import utils.UtileriaVista;
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
    ServicioCicloContable _cicloContable;
    
    ArrayList<CicloContable> listaCiclosContables = new ArrayList<>();
    CicloContable cicloContableModel = new CicloContable();
    ServicioConfigUsuario _configUsuario;
    
    // VARIABLES DE TIPO DE CATALOGO
    ServicioTipoCatalogo _tipoCatalogo;
    ArrayList<TipoCatalogo> listaTiposCatalogos = new ArrayList<>();
    TipoCatalogo tipoCatalogoModel = new TipoCatalogo();
    
    // VARIABLES DE CUENTAS
    ServicioCuenta _cuenta;
    ArrayList<dtoCuenta> listaCuentas = new ArrayList<>();
    ArrayList<dtoLista> listaCmbTipoSaldo = new ArrayList<>();
    Cuenta cuentaModel = new Cuenta();
    
    Sesion sesion;
    // VARIABLES EN COMUN
    DefaultTableModel dtm = new DefaultTableModel() {
        @Override 
        public boolean isCellEditable(int row, int column) { 
            return false;
        }
    };
            
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    ArrayList<TipoCatalogo> listaCmbTipoCatalogo = new ArrayList<>();
    
    public vConfigContabilidad(Sesion sesion) {
        this.sesion = sesion;
        _cicloContable = new ServicioCicloContable(sesion.rutaConexion);
        _configUsuario = new ServicioConfigUsuario(sesion.rutaConexion);
        _tipoCatalogo = new ServicioTipoCatalogo(sesion.rutaConexion);
        _cuenta = new ServicioCuenta(sesion.rutaConexion);
        initComponents();
        this.cargarInfoSegunTab();
    }

    /* <------------------------------------------------------------------------------->
                                   INICIO SECCION DE CICLO CONTABLE
        <------------------------------------------------------------------------------->  */  
    public void setModelCicloContable() {
        String[] cabecera = {"Seleccioado","Titulo","Desde","Hasta","Catalogo", "Editar", "Eliminar", "Seleccionar"};
        dtm.setColumnIdentifiers(cabecera);
        tblCicloContable.setModel(dtm);
        tblCicloContable.setDefaultRenderer(Object.class, new Render());
    }
    
    public void setDatosCicloContable() {
        // definimos los botones a crear
        RSMaterialComponent.RSButtonCustomIcon btn1 = new RSMaterialComponent.RSButtonCustomIcon();
        RSMaterialComponent.RSButtonCustomIcon btn2 = new RSMaterialComponent.RSButtonCustomIcon();
        RSMaterialComponent.RSButtonCustomIcon btn3 = new RSMaterialComponent.RSButtonCustomIcon();
        btn1.setIcons(ValoresEnum.ICONS.EDIT);
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);
        btn1.setCursor(cur);
        btn2.setIcons(ValoresEnum.ICONS.DELETE);
        btn2.setColorIcon(Color.RED);
        btn2.setCursor(cur);
        btn3.setIcons(ValoresEnum.ICONS.CHECK);
        btn3.setColorIcon(Color.GREEN);
        
        Object[] datos = new Object[dtm.getColumnCount()];
        for (CicloContable ciclo : listaCiclosContables) {
            datos[0] = (ciclo.getId() == this.sesion.configUsuario.getCicloContable().getId() ? "Por defecto" : "-");
            datos[1] = ciclo.getTitulo();
            datos[2] = sdf.format(ciclo.getDesde());
            datos[3] = sdf.format(ciclo.getHasta());
            datos[4] = ciclo.getTipoCatalogo().getTipo();
            datos[5] = btn1;
            datos[6] = btn2;
            datos[7] = btn3;
            dtm.addRow(datos);
        }
        tblCicloContable.setModel(dtm);
        tblCicloContable.setAutoResizeMode(tblCicloContable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tblCicloContable.getColumnModel().getColumn(0).setPreferredWidth(60);
        tblCicloContable.getColumnModel().getColumn(1).setPreferredWidth(320);
        tblCicloContable.getColumnModel().getColumn(2).setPreferredWidth(20);
        tblCicloContable.getColumnModel().getColumn(3).setPreferredWidth(20);
        tblCicloContable.getColumnModel().getColumn(4).setPreferredWidth(200);
        tblCicloContable.getColumnModel().getColumn(5).setPreferredWidth(10);
        tblCicloContable.getColumnModel().getColumn(6).setPreferredWidth(10);
        tblCicloContable.getColumnModel().getColumn(7).setPreferredWidth(10);
    }
    
    public void obtenerListadoCiclosContables() {
        this.listaCiclosContables = new ArrayList<>();
        tblCicloContable.clearSelection();
        this.limiparTablaCicloContable();
        RespuestaGeneral rs = _cicloContable.obtenerLista(this.txtBusquedaCicloContable.getText());
        this.totalCicloContable.setText("0");
        if (rs.esExitosa()) {
            this.listaCiclosContables = (ArrayList<CicloContable>)rs.getDatos();
            this.totalCicloContable.setText(String.valueOf(this.listaCiclosContables.size()));
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
        tblCicloContable.clearSelection();
    }
    
    /* <------------------------------------------------------------------------------->
                                   FIN SECCION DE CICLO CONTABLE
        <------------------------------------------------------------------------------->  */  
    
    /* <------------------------------------------------------------------------------->
                                   INICIO SECCION DE TIPOS DE CATALOGO
        <------------------------------------------------------------------------------->  */  
    public void setModelTipoCatalogo() {
//        String[] cabecera = {"Tipo", "Color", "Libro diario", "Libro mayor", "Balanza comprobacion", "Estado resultado", "Balance general", "Flujo de efectivo", "Cambios en patrimonio", "Editar", "Eliminar"};
        String[] cabecera = {"Tipo", "Editar", "Eliminar"};
        dtm.setColumnIdentifiers(cabecera);
        tblTipoCatalogo.setModel(dtm);
        tblTipoCatalogo.setDefaultRenderer(Object.class, new Render());
    }
    
    public void setDatosTipoCatalogo() {
        Object[] datos = new Object[dtm.getColumnCount()];
        // definimos los botones a crear
        RSMaterialComponent.RSButtonCustomIcon btn1 = new RSMaterialComponent.RSButtonCustomIcon();
        RSMaterialComponent.RSButtonCustomIcon btn2 = new RSMaterialComponent.RSButtonCustomIcon();
        btn1.setIcons(ValoresEnum.ICONS.EDIT);
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);
        btn1.setCursor(cur);
        btn2.setIcons(ValoresEnum.ICONS.DELETE);
        btn2.setColorIcon(Color.RED);
        btn2.setCursor(cur);
        for (TipoCatalogo tipo : listaTiposCatalogos) {
            datos[0] = tipo.getTipo();
//            datos[1] = "";
//            datos[2] = tipo.getLibro_diario() == 1 ? "SI" : "NO";
//            datos[3] = tipo.getLibro_mayor()== 1 ? "SI" : "NO";
//            datos[4] = tipo.getBalanza_comprobacion()== 1 ? "SI" : "NO";
//            datos[5] = tipo.getEstado_resultado()== 1 ? "SI" : "NO";
//            datos[6] = tipo.getBalance_general()== 1 ? "SI" : "NO";
//            datos[7] = tipo.getFlujo_efectivo()== 1 ? "SI" : "NO";
//            datos[8] = tipo.getCambios_patrimonio()== 1 ? "SI" : "NO";
            datos[1] = btn1;
            datos[2] = btn2;
            dtm.addRow(datos);
        }
        tblTipoCatalogo.setModel(dtm);
        tblTipoCatalogo.setAutoResizeMode(tblTipoCatalogo.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tblTipoCatalogo.getColumnModel().getColumn(0).setPreferredWidth(420);
//        tblTipoCatalogo.getColumnModel().getColumn(1).setPreferredWidth(10);
//        tblTipoCatalogo.getColumnModel().getColumn(2).setPreferredWidth(10);
//        tblTipoCatalogo.getColumnModel().getColumn(3).setPreferredWidth(10);
//        tblTipoCatalogo.getColumnModel().getColumn(4).setPreferredWidth(10);
//        tblTipoCatalogo.getColumnModel().getColumn(5).setPreferredWidth(10);
//        tblTipoCatalogo.getColumnModel().getColumn(6).setPreferredWidth(10);
//        tblTipoCatalogo.getColumnModel().getColumn(7).setPreferredWidth(10);
//        tblTipoCatalogo.getColumnModel().getColumn(8).setPreferredWidth(10);
        tblTipoCatalogo.getColumnModel().getColumn(1).setPreferredWidth(10);
        tblTipoCatalogo.getColumnModel().getColumn(2).setPreferredWidth(10);
    }
    
    public void obtenerListadoTipoCatalogo() {
        this.listaTiposCatalogos = new ArrayList<>();
        tblTipoCatalogo.clearSelection();
        this.limiparTablaTipoCatalogo();
        this.totalCicloContable.setText("0");
        RespuestaGeneral rs = _tipoCatalogo.obtenerLista(this.txtBusquedaTipoCatalogo.getText());
        if (rs.esExitosa()) {
            this.listaTiposCatalogos = (ArrayList<TipoCatalogo>)rs.getDatos();
            this.totalTipoCatalogo.setText(String.valueOf(this.listaTiposCatalogos.size()));
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

    /* <------------------------------------------------------------------------------->
                                   FIN SECCION DE TIPOS DE CATALOGO
        <------------------------------------------------------------------------------->  */  
    
    /* <------------------------------------------------------------------------------->
                                   INICIO SECCION DE CUENTAS
        <------------------------------------------------------------------------------->  */  
    public void setModelCuentas() {
        String[] cabecera = {"Nivel", "Codigo", "Concepto", "Tipo de Saldo", "Ingresos", "Egresos", "Editar", "Eliminar"};
        dtm.setColumnIdentifiers(cabecera);
        tblCuentas.setModel(dtm);
        tblCuentas.setDefaultRenderer(Object.class, new Render());
    }
    
    public void obtenerListaCmbTipoCatalogo2() {
        // obtenemos el listado de tipos de catalogo
        this.listaCmbTipoCatalogo = new ArrayList<>();
        RespuestaGeneral rg = _tipoCatalogo.obtenerLista("");
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
    
    public void setDatosCuentas() {
        // definimos los botones a crear
        RSMaterialComponent.RSButtonCustomIcon btn1 = new RSMaterialComponent.RSButtonCustomIcon();
        RSMaterialComponent.RSButtonCustomIcon btn2 = new RSMaterialComponent.RSButtonCustomIcon();
        btn1.setIcons(ValoresEnum.ICONS.EDIT);
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);
        btn1.setCursor(cur);
        btn2.setIcons(ValoresEnum.ICONS.DELETE);
        btn2.setColorIcon(Color.RED);
        btn2.setCursor(cur);
        
        Object[] datos = new Object[dtm.getColumnCount()];
        for (dtoCuenta cuenta : listaCuentas) {
            datos[0] = cuenta.getNivel();
            datos[1] = cuenta.getCodigo();
            datos[2] = cuenta.getNombre();
            datos[3] = cuenta.getTipo_saldo();
            datos[4] = cuenta.getIngresos();
            datos[5] = cuenta.getEgresos();
            datos[6] = btn1;
            datos[7] = btn2;
            dtm.addRow(datos);
        }
        tblCuentas.setModel(dtm);
        tblCuentas.setAutoResizeMode(tblCuentas.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tblCuentas.getColumnModel().getColumn(0).setPreferredWidth(60);
        tblCuentas.getColumnModel().getColumn(1).setPreferredWidth(120);
        tblCuentas.getColumnModel().getColumn(2).setPreferredWidth(425);
        tblCuentas.getColumnModel().getColumn(3).setPreferredWidth(140);
        tblCuentas.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblCuentas.getColumnModel().getColumn(5).setPreferredWidth(100);
        tblCuentas.getColumnModel().getColumn(6).setPreferredWidth(20);
        tblCuentas.getColumnModel().getColumn(7).setPreferredWidth(20);
       
    }
    
    public void obtenerListadoCuentasPorTipoCatalogo() {
        this.listaCuentas = new ArrayList<>();
        tblCuentas.clearSelection();
        this.limiparTablaCuentas();
        this.seleccionarOpcionCmbTipoCatalogo2();
        RespuestaGeneral rg = _cuenta.obtenerListaPorIdTipoCatalogo(this.cuentaModel.getId_tipo_catalogo(), this.txtQueryBusqueda.getText());
        this.totalCicloContable.setText("0");
        if (rg.esExitosa()) {
            this.listaCuentas = (ArrayList<dtoCuenta>)rg.getDatos();
            this.totalCuentas.setText(String.valueOf(this.listaCuentas.size()));
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
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCicloContable = new rojerusan.RSTableMetro();
        jLabel8 = new javax.swing.JLabel();
        txtBusquedaCicloContable = new RSMaterialComponent.RSTextFieldMaterial();
        btnBuscarCicloContable = new RSMaterialComponent.RSButtonShapeIcon();
        btnLimpiarCicloContable = new RSMaterialComponent.RSButtonShapeIcon();
        btnNuevoCicloContable = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel2 = new javax.swing.JLabel();
        totalCicloContable = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTipoCatalogo = new rojerusan.RSTableMetro();
        btnGuardarTipoCatalogo1 = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel7 = new javax.swing.JLabel();
        txtBusquedaTipoCatalogo = new RSMaterialComponent.RSTextFieldMaterial();
        btnGuardarTipoCatalogo2 = new RSMaterialComponent.RSButtonShapeIcon();
        btnGuardarTipoCatalogo3 = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel3 = new javax.swing.JLabel();
        totalTipoCatalogo = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCuentas = new rojerusan.RSTableMetro();
        txtQueryBusqueda = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel15 = new javax.swing.JLabel();
        btnLimpiarBusquedaCuenta = new RSMaterialComponent.RSButtonShapeIcon();
        btnBuscarCuenta = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel9 = new javax.swing.JLabel();
        cmbTipoCatalogo2 = new RSMaterialComponent.RSComboBoxMaterial();
        btnGuardarTipoCatalogo4 = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel1 = new javax.swing.JLabel();
        totalCuentas = new javax.swing.JLabel();

        tabPanelContabilidad.setBackground(new java.awt.Color(255, 255, 255));
        tabPanelContabilidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabPanelContabilidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabPanelContabilidadStateChanged(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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
        tblCicloContable.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblCicloContable.setFontHead(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblCicloContable.setFontRowHover(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblCicloContable.setFontRowSelect(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblCicloContable.setPositionText(rojerusan.RSTableMetro.POSITION_TEXT.LEFT);
        tblCicloContable.setRowHeight(30);
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

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Busqueda:");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtBusquedaCicloContable.setForeground(new java.awt.Color(0, 0, 0));
        txtBusquedaCicloContable.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtBusquedaCicloContable.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtBusquedaCicloContable.setPhColor(new java.awt.Color(0, 0, 0));
        txtBusquedaCicloContable.setPlaceholder("Buscar ciclo contable");
        txtBusquedaCicloContable.setSelectionColor(new java.awt.Color(0, 0, 0));

        btnBuscarCicloContable.setBackground(new java.awt.Color(33, 58, 86));
        btnBuscarCicloContable.setText("BUSCAR");
        btnBuscarCicloContable.setToolTipText("BUSCAR");
        btnBuscarCicloContable.setBackgroundHover(new java.awt.Color(33, 68, 86));
        btnBuscarCicloContable.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBuscarCicloContable.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnBuscarCicloContable.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btnBuscarCicloContable.setSizeIcon(18.0F);
        btnBuscarCicloContable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCicloContableActionPerformed(evt);
            }
        });

        btnLimpiarCicloContable.setBackground(new java.awt.Color(102, 102, 102));
        btnLimpiarCicloContable.setText("LIMPIAR");
        btnLimpiarCicloContable.setToolTipText("LIMPIAR BUSQUEDA");
        btnLimpiarCicloContable.setBackgroundHover(new java.awt.Color(112, 113, 116));
        btnLimpiarCicloContable.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLimpiarCicloContable.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnLimpiarCicloContable.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLEAR);
        btnLimpiarCicloContable.setSizeIcon(18.0F);
        btnLimpiarCicloContable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarCicloContableActionPerformed(evt);
            }
        });

        btnNuevoCicloContable.setBackground(new java.awt.Color(0, 153, 0));
        btnNuevoCicloContable.setText("NUEVO");
        btnNuevoCicloContable.setToolTipText("NUEVO REGISTRO");
        btnNuevoCicloContable.setBackgroundHover(new java.awt.Color(0, 178, 0));
        btnNuevoCicloContable.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnNuevoCicloContable.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnNuevoCicloContable.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btnNuevoCicloContable.setSizeIcon(18.0F);
        btnNuevoCicloContable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoCicloContableActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Total de registros: ");

        totalCicloContable.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        totalCicloContable.setText("100");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBusquedaCicloContable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnLimpiarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnNuevoCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusquedaCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevoCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(totalCicloContable))
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabPanelContabilidad.addTab("Ciclo Contable", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

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
        tblTipoCatalogo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblTipoCatalogo.setFontHead(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblTipoCatalogo.setFontRowHover(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblTipoCatalogo.setFontRowSelect(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblTipoCatalogo.setPositionText(rojerusan.RSTableMetro.POSITION_TEXT.LEFT);
        tblTipoCatalogo.setRowHeight(30);
        tblTipoCatalogo.setSelectionBackground(new java.awt.Color(153, 153, 153));
        tblTipoCatalogo.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblTipoCatalogo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblTipoCatalogo.setShowGrid(true);
        tblTipoCatalogo.setShowVerticalLines(false);
        tblTipoCatalogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTipoCatalogoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblTipoCatalogoMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tblTipoCatalogo);

        btnGuardarTipoCatalogo1.setBackground(new java.awt.Color(33, 58, 86));
        btnGuardarTipoCatalogo1.setText("BUSCAR");
        btnGuardarTipoCatalogo1.setToolTipText("BUSCAR");
        btnGuardarTipoCatalogo1.setBackgroundHover(new java.awt.Color(33, 68, 86));
        btnGuardarTipoCatalogo1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuardarTipoCatalogo1.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnGuardarTipoCatalogo1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btnGuardarTipoCatalogo1.setSizeIcon(18.0F);
        btnGuardarTipoCatalogo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarTipoCatalogo1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Busqueda:");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtBusquedaTipoCatalogo.setForeground(new java.awt.Color(0, 0, 0));
        txtBusquedaTipoCatalogo.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtBusquedaTipoCatalogo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtBusquedaTipoCatalogo.setPhColor(new java.awt.Color(0, 0, 0));
        txtBusquedaTipoCatalogo.setPlaceholder("Buscar por el tipo");
        txtBusquedaTipoCatalogo.setSelectionColor(new java.awt.Color(0, 0, 0));

        btnGuardarTipoCatalogo2.setBackground(new java.awt.Color(0, 153, 0));
        btnGuardarTipoCatalogo2.setText("NUEVO");
        btnGuardarTipoCatalogo2.setToolTipText("NUEVO REGISTRO");
        btnGuardarTipoCatalogo2.setBackgroundHover(new java.awt.Color(0, 178, 0));
        btnGuardarTipoCatalogo2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuardarTipoCatalogo2.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnGuardarTipoCatalogo2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btnGuardarTipoCatalogo2.setSizeIcon(18.0F);
        btnGuardarTipoCatalogo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarTipoCatalogo2ActionPerformed(evt);
            }
        });

        btnGuardarTipoCatalogo3.setBackground(new java.awt.Color(102, 102, 102));
        btnGuardarTipoCatalogo3.setText("LIMPIAR");
        btnGuardarTipoCatalogo3.setToolTipText("LIMPIAR BUSQUEDA");
        btnGuardarTipoCatalogo3.setBackgroundHover(new java.awt.Color(112, 113, 116));
        btnGuardarTipoCatalogo3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuardarTipoCatalogo3.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnGuardarTipoCatalogo3.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLEAR);
        btnGuardarTipoCatalogo3.setSizeIcon(18.0F);
        btnGuardarTipoCatalogo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarTipoCatalogo3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Total de registros: ");

        totalTipoCatalogo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        totalTipoCatalogo.setText("100");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBusquedaTipoCatalogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuardarTipoCatalogo1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnGuardarTipoCatalogo3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnGuardarTipoCatalogo2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusquedaTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarTipoCatalogo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarTipoCatalogo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarTipoCatalogo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(totalTipoCatalogo))
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabPanelContabilidad.addTab("Tipos de Catálogos", jPanel2);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

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
        tblCuentas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblCuentas.setFontHead(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblCuentas.setFontRowHover(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblCuentas.setFontRowSelect(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblCuentas.setPositionText(rojerusan.RSTableMetro.POSITION_TEXT.LEFT);
        tblCuentas.setRowHeight(30);
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

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Tipo de catalogo:");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        cmbTipoCatalogo2.setColorMaterial(new java.awt.Color(102, 102, 102));
        cmbTipoCatalogo2.setThemeTooltip(necesario.Global.THEMETOOLTIP.LIGHT);
        cmbTipoCatalogo2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoCatalogo2ItemStateChanged(evt);
            }
        });

        btnGuardarTipoCatalogo4.setBackground(new java.awt.Color(0, 153, 0));
        btnGuardarTipoCatalogo4.setText("NUEVO");
        btnGuardarTipoCatalogo4.setToolTipText("NUEVO REGISTRO");
        btnGuardarTipoCatalogo4.setBackgroundHover(new java.awt.Color(0, 178, 0));
        btnGuardarTipoCatalogo4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuardarTipoCatalogo4.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnGuardarTipoCatalogo4.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btnGuardarTipoCatalogo4.setSizeIcon(18.0F);
        btnGuardarTipoCatalogo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarTipoCatalogo4ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Total de registros: ");

        totalCuentas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        totalCuentas.setText("100");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbTipoCatalogo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txtQueryBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLimpiarBusquedaCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnGuardarTipoCatalogo4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoCatalogo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQueryBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarBusquedaCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarTipoCatalogo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(totalCuentas))
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void tblCicloContableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCicloContableMouseClicked
        // logica de acciones de botones
        int accion = tblCicloContable.getSelectedColumn();
        int row = tblCicloContable.getSelectedRow();
        if (accion == 5) {
            // editar
            this.setearModeloCicloContable(row);
            this.abrirDialogCicloContable(cicloContableModel);
            
        } else if (accion == 6) {
            // eliminar
            String texto = "¿Esta seguro de continuar?, Se eliminará el registro:\n" + this.listaCiclosContables.get(row).getTitulo();
            int opc = JOptionPane.showConfirmDialog(null, texto, "¡ALERTA!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opc == 0) {
                RespuestaGeneral rg = _cicloContable.eliminar(this.listaCiclosContables.get(row).getId());
                if (rg.esExitosa()) {
                    JOptionPane.showMessageDialog(this, rg.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rg));
                    this.limpiarFormCicloContable();
                    this.obtenerListadoCiclosContables();
                } else {
                    JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
                }
            }

        } else if (accion == 7) {
            // mostrar alerta indicando si esta seguro de ralizar la accion
            String texto = "¿Esta seguro de continuar?, Se cambiará el ciclo contable por defecto a:\n" + this.listaCiclosContables.get(row).getTitulo();
            int opc = JOptionPane.showConfirmDialog(null, texto, "¡ALERTA!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opc == 0) {
                // seleccionar por defecto
                sesion.configUsuario.setId_ciclo_contable(this.listaCiclosContables.get(row).getId());
                ArrayList<ConfiguracionUsuario> listaConfigUsuario = new ArrayList<>();
                RespuestaGeneral rg = _configUsuario.editar(sesion.configUsuario);
                if (rg.esExitosa()) {
                    RespuestaGeneral rg1 = _configUsuario.obtenerPorIdUsuario(sesion.usuario.getId());
                    if (rg1.esExitosa()) {
                        listaConfigUsuario = (ArrayList<ConfiguracionUsuario>)rg1.getDatos();
                        sesion.configUsuario = listaConfigUsuario.get(0);
                        JOptionPane.showMessageDialog(this, "Se establecio el ciclo contable por defecto", "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rg));
                        UtileriaVista.actualizarPerfil(sesion);
                        this.limpiarFormCicloContable();
                        this.obtenerListadoCiclosContables();
                    } else {
                        JOptionPane.showMessageDialog(this, rg1.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg1));
                    }
                } else {
                    JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
                }
            }
            
        }
    }//GEN-LAST:event_tblCicloContableMouseClicked

    public void setearModeloCicloContable(int row) {
        cicloContableModel = (CicloContable)this.listaCiclosContables.get(row);
    }
    
    public void abrirDialogCicloContable(CicloContable cicloContable) {
        dCicloContable d = new dCicloContable(null, true, cicloContable, sesion);
        d.setVisible(true);
        // validamos si realizo alguna accion para actualizar el listado o no
        if (d.getRealizoAccion()) {
            JOptionPane.showMessageDialog(this, d.getRG().getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(d.getRG()));
            this.obtenerListadoCiclosContables();
        }
    }
    
    private void tabPanelContabilidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabPanelContabilidadStateChanged
        this.cargarInfoSegunTab();
    }//GEN-LAST:event_tabPanelContabilidadStateChanged
    
    private void tblTipoCatalogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTipoCatalogoMouseClicked
        int accion = tblTipoCatalogo.getSelectedColumn();
        int row = tblTipoCatalogo.getSelectedRow();
        if (accion == 1) {
            // este es editar
            this.abrirDialogTipoCatalogo(this.listaTiposCatalogos.get(row));
            
        } else if (accion == 2) {
            // este es eliminar
            //String texto = "¿Esta seguro de continuar?, Se eliminará el registro: " + this.txtTipoCatalogo.getText();
            String texto = "¿Esta seguro de continuar?, Se eliminará el registro:\n" + this.listaTiposCatalogos.get(row).getTipo();
            int opc = JOptionPane.showConfirmDialog(null, texto, "¡ALERTA!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            //System.out.println(salida);
            if (opc == 0) {
                RespuestaGeneral rg = _tipoCatalogo.eliminar(this.listaTiposCatalogos.get(row).getId());
                if (rg.esExitosa()) {
                    JOptionPane.showMessageDialog(this, rg.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rg));
                    this.obtenerListadoTipoCatalogo();
                } else {
                    JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
                }
            }
        }
    }//GEN-LAST:event_tblTipoCatalogoMouseClicked
    
    public void abrirDialogTipoCatalogo(TipoCatalogo tipoCatalogo) {
        dTipoCatalogo d = new dTipoCatalogo(null, true, tipoCatalogo, sesion);
        d.setVisible(true);
        // validamos si realizo alguna accion para actualizar el listado o no
        if (d.getRealizoAccion()) {
            JOptionPane.showMessageDialog(this, d.getRG().getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(d.getRG()));
            UtileriaVista.actualizarPerfil(sesion);
            this.obtenerListadoTipoCatalogo();
        }
    }
    
    private void cmbTipoCatalogo2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoCatalogo2ItemStateChanged
        this.seleccionarOpcionCmbTipoCatalogo2();
        this.limiparTablaCuentas();
        int i = cmbTipoCatalogo2.getSelectedIndex();
        if (i >= 0) {
            this.obtenerListadoCuentasPorTipoCatalogo();
        }
    }//GEN-LAST:event_cmbTipoCatalogo2ItemStateChanged

    private void tblCuentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCuentasMouseClicked
        int accion = tblCuentas.getSelectedColumn();
        int row = tblCuentas.getSelectedRow();
        String tituloCatalogo = "Catalogo: " + this.listaCmbTipoCatalogo.get(this.cmbTipoCatalogo2.getSelectedIndex()).getTipo();
        if (accion == 6) {
            // este es editar
            this.setearModeloCuentas(row);
            this.abrirDialogCuentas(cuentaModel, tituloCatalogo);
            
        } else if (accion == 7) {
            String texto = "¿Esta seguro de continuar?, Se eliminará el registro: \n" + this.listaCuentas.get(row).getNombre();
            int opc = JOptionPane.showConfirmDialog(null, texto, "¡ALERTA!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (opc == 0) {
                RespuestaGeneral rg = _cuenta.eliminar(this.listaCuentas.get(row).getId());
                if (rg.esExitosa()) {
                    JOptionPane.showMessageDialog(this, rg.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rg));
                    this.limpiarFormCuentas();
                    this.obtenerListadoCuentasPorTipoCatalogo();
                } else {
                    JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
                }
            }
        }
    }//GEN-LAST:event_tblCuentasMouseClicked

    public void setearModeloCuentas(int row) {
        cuentaModel.setId(this.listaCuentas.get(row).getId());
        cuentaModel.setId_tipo_catalogo(this.listaCuentas.get(row).getId_tipo_catalogo());
        cuentaModel.setCodigo(this.listaCuentas.get(row).getCodigo());
        cuentaModel.setNivel(this.listaCuentas.get(row).getNivel());
        cuentaModel.setNombre(this.listaCuentas.get(row).getNombre());
        cuentaModel.setTipo_saldo(this.listaCuentas.get(row).getTipo_saldo());
        cuentaModel.setEgresos(this.listaCuentas.get(row).getEgresos());
        cuentaModel.setIngresos(this.listaCuentas.get(row).getIngresos());
    }
    
    public void abrirDialogCuentas(Cuenta cuenta, String titulo) {
        dCuentas d = new dCuentas(null, true, cuenta, titulo, sesion);
        d.setVisible(true);
        // validamos si realizo alguna accion para actualizar el listado o no
        if (d.getRealizoAccion()) {
            JOptionPane.showMessageDialog(this, d.getRG().getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(d.getRG()));
            this.obtenerListadoCuentasPorTipoCatalogo();
        }
    }
    
    private void btnLimpiarBusquedaCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarBusquedaCuentaActionPerformed
        this.txtQueryBusqueda.setText("");
        this.obtenerListadoCuentasPorTipoCatalogo();
    }//GEN-LAST:event_btnLimpiarBusquedaCuentaActionPerformed

    private void btnBuscarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCuentaActionPerformed
        this.obtenerListadoCuentasPorTipoCatalogo();
    }//GEN-LAST:event_btnBuscarCuentaActionPerformed

    private void btnGuardarTipoCatalogo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarTipoCatalogo1ActionPerformed
        this.obtenerListadoTipoCatalogo();
    }//GEN-LAST:event_btnGuardarTipoCatalogo1ActionPerformed

    private void btnGuardarTipoCatalogo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarTipoCatalogo2ActionPerformed
        this.abrirDialogTipoCatalogo(new TipoCatalogo());
    }//GEN-LAST:event_btnGuardarTipoCatalogo2ActionPerformed

    private void btnGuardarTipoCatalogo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarTipoCatalogo3ActionPerformed
        this.txtBusquedaTipoCatalogo.setText("");
        this.obtenerListadoTipoCatalogo();
    }//GEN-LAST:event_btnGuardarTipoCatalogo3ActionPerformed

    private void tblTipoCatalogoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTipoCatalogoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblTipoCatalogoMouseEntered

    private void btnBuscarCicloContableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCicloContableActionPerformed
        this.obtenerListadoCiclosContables();
    }//GEN-LAST:event_btnBuscarCicloContableActionPerformed

    private void btnLimpiarCicloContableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarCicloContableActionPerformed
        this.txtBusquedaCicloContable.setText("");
        this.obtenerListadoCiclosContables();
    }//GEN-LAST:event_btnLimpiarCicloContableActionPerformed

    private void btnNuevoCicloContableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoCicloContableActionPerformed
        this.abrirDialogCicloContable(new CicloContable());
    }//GEN-LAST:event_btnNuevoCicloContableActionPerformed

    private void btnGuardarTipoCatalogo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarTipoCatalogo4ActionPerformed
        String tituloCatalogo = "Catalogo: " + this.listaCmbTipoCatalogo.get(this.cmbTipoCatalogo2.getSelectedIndex()).getTipo();
        Cuenta c = new Cuenta();
        c.setId_tipo_catalogo(listaCmbTipoCatalogo.get(cmbTipoCatalogo2.getSelectedIndex()).getId());
        this.abrirDialogCuentas(c, tituloCatalogo);
    }//GEN-LAST:event_btnGuardarTipoCatalogo4ActionPerformed

    public void seleccionarOpcionCmbTipoCatalogo2() {
        int i = cmbTipoCatalogo2.getSelectedIndex();
        if (i >= 0) {
            this.cuentaModel.setId_tipo_catalogo(this.listaCmbTipoCatalogo.get(i).getId());
        }
    }
    
    public void cargarInfoSegunTab() {
        this.limiparTablaTipoCatalogo();
        this.limpiarFormCicloContable();
        this.limiparTablaCicloContable();
        this.limiparTablaCuentas();
        this.limpiarFormCuentas();
        switch (this.tabPanelContabilidad.getSelectedIndex()) {
            case 0 -> this.iniciarCicloContable();
            case 1 -> this.iniciarTiposDeCatalogos();
            case 2 -> this.iniciarCatalogos();
            default -> this.iniciarCicloContable();
        }
    }
    
    public void iniciarCicloContable() {
        this.setModelCicloContable();
        this.obtenerListadoCiclosContables();
    }
    
    public void iniciarTiposDeCatalogos() {
        this.setModelTipoCatalogo();
        this.obtenerListadoTipoCatalogo();
    }
    
    public void iniciarCatalogos() {
        this.setModelCuentas();
        this.obtenerListaCmbTipoCatalogo2();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShapeIcon btnBuscarCicloContable;
    private RSMaterialComponent.RSButtonShapeIcon btnBuscarCuenta;
    private RSMaterialComponent.RSButtonShapeIcon btnGuardarTipoCatalogo1;
    private RSMaterialComponent.RSButtonShapeIcon btnGuardarTipoCatalogo2;
    private RSMaterialComponent.RSButtonShapeIcon btnGuardarTipoCatalogo3;
    private RSMaterialComponent.RSButtonShapeIcon btnGuardarTipoCatalogo4;
    private RSMaterialComponent.RSButtonShapeIcon btnLimpiarBusquedaCuenta;
    private RSMaterialComponent.RSButtonShapeIcon btnLimpiarCicloContable;
    private RSMaterialComponent.RSButtonShapeIcon btnNuevoCicloContable;
    private RSMaterialComponent.RSComboBoxMaterial cmbTipoCatalogo2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane tabPanelContabilidad;
    private rojerusan.RSTableMetro tblCicloContable;
    private rojerusan.RSTableMetro tblCuentas;
    private rojerusan.RSTableMetro tblTipoCatalogo;
    private javax.swing.JLabel totalCicloContable;
    private javax.swing.JLabel totalCuentas;
    private javax.swing.JLabel totalTipoCatalogo;
    private RSMaterialComponent.RSTextFieldMaterial txtBusquedaCicloContable;
    private RSMaterialComponent.RSTextFieldMaterial txtBusquedaTipoCatalogo;
    private RSMaterialComponent.RSTextFieldMaterial txtQueryBusqueda;
    // End of variables declaration//GEN-END:variables
}
