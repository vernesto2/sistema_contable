/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package formularios;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cuenta;
import modelo.PartidaDetalle;
import reportes.CuentaBalanza;
import servicios.ServicioCuenta;
import sesion.Sesion;
import utils.Render;
import utils.UtileriaVista;
import utils.constantes.Constantes;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class dSeleccionarCuenta extends javax.swing.JDialog {

    DefaultTableModel dtm = new DefaultTableModel() {
        @Override 
        public boolean isCellEditable(int row, int column) { 
            return true;
        }
    };
    boolean realizoAccion = false;   
    ServicioCuenta _cuenta;
    ArrayList<Cuenta> listaCuentas = new ArrayList<>();
    ArrayList<Cuenta> listaCuentasCompleta = new ArrayList<>();
    Cuenta cuentaSeleccionada = new Cuenta();
    ArrayList<PartidaDetalle> listaDetallePartida = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Sesion sesion;
    /**
     * Creates new form dSeleccionarCuenta
     */
    public dSeleccionarCuenta(java.awt.Frame parent, boolean modal, Sesion sesion) {
        super(parent, modal);
        initComponents();
        this.sesion = sesion;
        this._cuenta = new ServicioCuenta(sesion.rutaConexion);
        this.iniciarVistaDialog();
    }
    
    public void iniciarVistaDialog() {
        this.setLocationRelativeTo(this);
        this.setResizable(false);
        this.setTitle("AGREGAR DETALLE A PARTIDA");
        this.realizoAccion = false;
        // seteamos la informacion
        this.setModelCuentas();
        this.obtenerListadoCuentasPorTipoCatalogoCompleto();
        this.obtenerListadoCuentasPorTipoCatalogo();
        this.txtFM.setText("0");
    }

    public void setModelCuentas() {
        String[] cabecera = {"Disponible", "Nivel", "Codigo", "", "Concepto", "Tipo Saldo", "Ingresos", "Egresos"};
        dtm.setColumnIdentifiers(cabecera);
        tblCuentas.setModel(dtm);
        tblCuentas.setDefaultRenderer(Object.class, new Render());
    }
    
    public void obtenerListadoCuentasPorTipoCatalogo() {
        this.listaCuentas = new ArrayList<>();
        tblCuentas.clearSelection();
        this.limiparTablaCuentas();
        RespuestaGeneral rg = _cuenta.obtenerListaPorIdTipoCatalogoGeneral(this.sesion.configUsuario.getCicloContable().getTipoCatalogo().getId(), this.txtQueryBusqueda.getText());
        this.totalCuentas.setText("0");
        if (rg.esExitosa()) {
            this.listaCuentas = (ArrayList<Cuenta>)rg.getDatos();
            this.totalCuentas.setText(String.valueOf(this.listaCuentas.size()));
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo obtener el listado", "ALERTA", UtileriaVista.devolverCodigoMensaje(rg));
        }
        this.setDatosCuentas();
    }
    
    public void obtenerListadoCuentasPorTipoCatalogoCompleto() {
        this.listaCuentasCompleta = new ArrayList<>();
        RespuestaGeneral rg = _cuenta.obtenerListaPorIdTipoCatalogoGeneral(this.sesion.configUsuario.getCicloContable().getTipoCatalogo().getId(), this.txtQueryBusqueda.getText());
        if (rg.esExitosa()) {
            this.listaCuentasCompleta = (ArrayList<Cuenta>)rg.getDatos();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo obtener el listado", "ALERTA", UtileriaVista.devolverCodigoMensaje(rg));
        }
    }
    
    public void setDatosCuentas() {
        Object[] datos = new Object[dtm.getColumnCount()];
        for (Cuenta cuenta : listaCuentas) {
            String disponible = cuenta.getDisponible() == 1 ? "Si" : "No";
            datos[0] = disponible;
            datos[1] = cuenta.getNivel();
            datos[2] = cuenta.getCodigo();
            datos[3] = cuenta.getEs_restado() == 0 ? "" : "R";
            datos[4] = cuenta.getNombre();
            datos[5] = cuenta.getTipo_saldo().equals("D") ? "DEUDOR": cuenta.getTipo_saldo().equals("A") ? "ACREEDOR" : "-";
            datos[6] = cuenta.getIngresos();
            datos[7] = cuenta.getEgresos();
            dtm.addRow(datos);
        }
        tblCuentas.setModel(dtm);
        tblCuentas.setAutoResizeMode(tblCuentas.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tblCuentas.getColumnModel().getColumn(0).setPreferredWidth(90);
        tblCuentas.getColumnModel().getColumn(1).setPreferredWidth(60);
        tblCuentas.getColumnModel().getColumn(2).setPreferredWidth(120);
        tblCuentas.getColumnModel().getColumn(3).setPreferredWidth(25);
        tblCuentas.getColumnModel().getColumn(4).setPreferredWidth(425);
        tblCuentas.getColumnModel().getColumn(5).setPreferredWidth(90);
        tblCuentas.getColumnModel().getColumn(6).setPreferredWidth(100);
        tblCuentas.getColumnModel().getColumn(7).setPreferredWidth(100);
       
    }

    public void limiparTablaCuentas() {
        for (int i = 0; i < tblCuentas.getRowCount(); i++) {
            dtm.removeRow(i);
            i-=1;
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnCancelarTipoCatalogo = new RSMaterialComponent.RSButtonShapeIcon();
        btnAbonar = new RSMaterialComponent.RSButtonShapeIcon();
        btnCargar = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel15 = new javax.swing.JLabel();
        txtQueryBusqueda = new RSMaterialComponent.RSTextFieldMaterial();
        btnBuscarCuenta = new RSMaterialComponent.RSButtonShapeIcon();
        btnLimpiarBusquedaCuenta = new RSMaterialComponent.RSButtonShapeIcon();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCuentas = new rojerusan.RSTableMetro();
        jLabel1 = new javax.swing.JLabel();
        totalCuentas = new javax.swing.JLabel();
        txtCuentaSeleccionada = new javax.swing.JLabel();
        txtMonto = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel17 = new javax.swing.JLabel();
        txtFM = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel18 = new javax.swing.JLabel();
        totalCuentas1 = new javax.swing.JLabel();
        totalCuentas2 = new javax.swing.JLabel();
        lblSaldoDeudor = new javax.swing.JLabel();
        lblSaldoAcreedor = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnCancelarTipoCatalogo.setBackground(new java.awt.Color(251, 205, 6));
        btnCancelarTipoCatalogo.setText("CERRAR");
        btnCancelarTipoCatalogo.setToolTipText("CERRAR EL FORMULARIO");
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

        btnAbonar.setBackground(new java.awt.Color(153, 0, 0));
        btnAbonar.setText("ABONAR");
        btnAbonar.setToolTipText("ABONAR CUENTA");
        btnAbonar.setBackgroundHover(new java.awt.Color(204, 0, 0));
        btnAbonar.setEnabled(false);
        btnAbonar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAbonar.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnAbonar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAbonar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ARROW_DOWNWARD);
        btnAbonar.setSizeIcon(18.0F);
        btnAbonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbonarActionPerformed(evt);
            }
        });

        btnCargar.setBackground(new java.awt.Color(0, 102, 51));
        btnCargar.setText("CARGAR");
        btnCargar.setToolTipText("CARGAR CUENTA");
        btnCargar.setBackgroundHover(new java.awt.Color(0, 153, 0));
        btnCargar.setEnabled(false);
        btnCargar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCargar.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnCargar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCargar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ARROW_UPWARD);
        btnCargar.setSizeIcon(18.0F);
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
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
                .addComponent(btnCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAbonar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAbonar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCargar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Busqueda:");
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtQueryBusqueda.setForeground(new java.awt.Color(0, 0, 0));
        txtQueryBusqueda.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtQueryBusqueda.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtQueryBusqueda.setPhColor(new java.awt.Color(0, 0, 0));
        txtQueryBusqueda.setPlaceholder("Busqueda por codigo o concepto");
        txtQueryBusqueda.setSelectionColor(new java.awt.Color(0, 0, 0));

        btnBuscarCuenta.setBackground(new java.awt.Color(33, 58, 86));
        btnBuscarCuenta.setText("BUSCAR");
        btnBuscarCuenta.setToolTipText("BUSCAR POR CODIGO O CONCEPTO DE CUENTA");
        btnBuscarCuenta.setBackgroundHover(new java.awt.Color(33, 93, 86));
        btnBuscarCuenta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBuscarCuenta.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnBuscarCuenta.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        btnBuscarCuenta.setSizeIcon(18.0F);
        btnBuscarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCuentaActionPerformed(evt);
            }
        });

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
        tblCuentas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblCuentasKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tblCuentas);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Total de registros: ");

        totalCuentas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        totalCuentas.setText("100");

        txtCuentaSeleccionada.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtCuentaSeleccionada.setForeground(new java.awt.Color(204, 0, 0));
        txtCuentaSeleccionada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtCuentaSeleccionada.setText("NO HA SELECCIONADO NINGUNA CUENTA");
        txtCuentaSeleccionada.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtMonto.setForeground(new java.awt.Color(0, 0, 0));
        txtMonto.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtMonto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtMonto.setPhColor(new java.awt.Color(0, 0, 0));
        txtMonto.setPlaceholder("Digite el monto");
        txtMonto.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Monto:");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtFM.setForeground(new java.awt.Color(0, 0, 0));
        txtFM.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtFM.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtFM.setPhColor(new java.awt.Color(0, 0, 0));
        txtFM.setPlaceholder("Digite el FM");
        txtFM.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtFM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFMKeyTyped(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("FM:");
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        totalCuentas1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        totalCuentas1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalCuentas1.setText("Saldo deudor:");

        totalCuentas2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        totalCuentas2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalCuentas2.setText("Saldo acreedor:");

        lblSaldoDeudor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblSaldoDeudor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSaldoDeudor.setText("##,###.###.##");

        lblSaldoAcreedor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblSaldoAcreedor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSaldoAcreedor.setText("##,###,###,##");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtQueryBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLimpiarBusquedaCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFM, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCuentaSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(totalCuentas1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalCuentas2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(580, 580, 580)
                                .addComponent(lblSaldoDeudor, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblSaldoAcreedor, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(11, 11, 11)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQueryBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarBusquedaCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCuentaSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(totalCuentas)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalCuentas2)
                            .addComponent(totalCuentas1))))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSaldoAcreedor)
                    .addComponent(lblSaldoDeudor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void btnAbonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbonarActionPerformed
        // proceso para seleccionar cuenta y su cuenta padre
        try {
            if (txtMonto.getText().trim().equals("") || txtMonto.getText().contains("..")) {
                JOptionPane.showMessageDialog(this, "No ha ingresado un monto", "¡ALERTA!", JOptionPane.WARNING_MESSAGE);
            } else if (txtFM.getText().trim().equals("") || txtFM.getText().contains("..")) {
                JOptionPane.showMessageDialog(this, "No ha ingresado un FM", "¡ALERTA!", JOptionPane.WARNING_MESSAGE);
            }  else {
                double valor = Double.parseDouble(txtMonto.getText());
                this.realizoAccion = true;
                this.buscarCuentaPadre(Constantes.TIPO_ABONO);
                this.cerrar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto correcto", "¡ALERTA!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAbonarActionPerformed

    public void cerrar() {
        this.dispose();
    }
    
    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        // proceso para seleccionar cuenta y su cuenta padre
        try {
            if (txtMonto.getText().trim().equals("") || txtMonto.getText().contains("..")) {
                JOptionPane.showMessageDialog(this, "No ha ingresado un monto", "¡ALERTA!", JOptionPane.WARNING_MESSAGE);
            } else if (txtFM.getText().trim().equals("") || txtFM.getText().contains("..")) {
                JOptionPane.showMessageDialog(this, "No ha ingresado un FM", "¡ALERTA!", JOptionPane.WARNING_MESSAGE);
            } else {
                double valor = Double.parseDouble(txtMonto.getText());
                this.realizoAccion = true;
                this.buscarCuentaPadre(Constantes.TIPO_CARGO);
                this.cerrar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto correcto", "¡ALERTA!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnCargarActionPerformed

    public void buscarCuentaPadre(int tipoAccion) {
        // verificamos si la cuenta seleccionada tiene una longitud de 4
        PartidaDetalle pDetallePadre = new PartidaDetalle();
        Cuenta cuentaPadre = new Cuenta();
        boolean encontroPadre = false;
        if (this.cuentaSeleccionada.getNivel() > this.sesion.configUsuario.getCicloContable().getTipoCatalogo().getNivel_mayorizar()) {
            int cantidadCodigo = 0;
            for (Cuenta cuenta : this.listaCuentasCompleta) {
                if (cuenta.getNivel() == this.sesion.configUsuario.getCicloContable().getTipoCatalogo().getNivel_mayorizar()) {
                    cantidadCodigo = cuenta.getCodigo().length();
                    break;
                }
            }
            String codigoPadre = this.cuentaSeleccionada.getCodigo().substring(0, cantidadCodigo);
            for (Cuenta cuenta : this.listaCuentasCompleta) {
                if (cuenta.getCodigo().equals(codigoPadre)) {
                    cuentaPadre = cuenta;
                    encontroPadre = true;
                    break;
                }
            }
            if (encontroPadre) {
                pDetallePadre.setTipo_cargo_abono(tipoAccion);
                pDetallePadre.setId_cuenta(cuentaPadre.getId());
                pDetallePadre.setFolio_mayor(Integer.parseInt(txtFM.getText()));
                pDetallePadre.setCuenta(cuentaPadre);
                //pDetallePadre.setParcial(BigDecimal.valueOf(Double.parseDouble(txtMonto.getText())));
                listaDetallePartida.add(pDetallePadre);
            } else {
                if (this.sesion.configUsuario.getCicloContable().getTipoCatalogo().getNivel_mayorizar() > 0) {
                    JOptionPane.showMessageDialog(this, "No se encontro cuenta padre", "¡ALERTA!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            
        }
        
        // creamos el detalle seleccionado
        PartidaDetalle pDetalle = new PartidaDetalle();
        pDetalle.setCuentaMayor(cuentaPadre);
        pDetalle.setTipo_cargo_abono(tipoAccion);
        pDetalle.setId_cuenta(this.cuentaSeleccionada.getId());
        pDetalle.setCuenta(this.cuentaSeleccionada);
        if (encontroPadre) {
            pDetalle.setParcial(Double.parseDouble(txtMonto.getText()));
        } else if (!encontroPadre && Constantes.TIPO_CARGO == tipoAccion) {
            pDetalle.setDebe(Double.parseDouble(txtMonto.getText()));
            pDetalle.setFolio_mayor(Integer.parseInt(txtFM.getText()));
        } else if (!encontroPadre && Constantes.TIPO_ABONO == tipoAccion) {
            pDetalle.setHaber(Double.parseDouble(txtMonto.getText()));
            pDetalle.setFolio_mayor(Integer.parseInt(txtFM.getText()));
        }
        listaDetallePartida.add(pDetalle);
    }

    public boolean getRealizoAccion() {
        return realizoAccion;
    }

    public ArrayList<PartidaDetalle> getListaPartidaDetalle() {
        return listaDetallePartida;
    }
    
    private void btnBuscarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCuentaActionPerformed
        this.obtenerListadoCuentasPorTipoCatalogo();
    }//GEN-LAST:event_btnBuscarCuentaActionPerformed

    private void btnLimpiarBusquedaCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarBusquedaCuentaActionPerformed
        this.txtQueryBusqueda.setText("");
        this.obtenerListadoCuentasPorTipoCatalogo();
    }//GEN-LAST:event_btnLimpiarBusquedaCuentaActionPerformed

    private void tblCuentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCuentasMouseClicked
        int row = tblCuentas.getSelectedRow();
        this.cuentaSeleccionada = (Cuenta)this.listaCuentas.get(row);
        if (this.cuentaSeleccionada.getDisponible() == 1) {
            String tituloCuenta = this.cuentaSeleccionada.getCodigo() + " - " + this.cuentaSeleccionada.getNombre();
            this.txtCuentaSeleccionada.setText(tituloCuenta);
            this.btnAbonar.setEnabled((this.cuentaSeleccionada.getId() > 0));
            this.btnCargar.setEnabled((this.cuentaSeleccionada.getId() > 0));
            this.txtCuentaSeleccionada.setForeground(Color.black);
        } else {
            String tituloCuenta = "*CUENTA NO VALIDA*";
            this.txtCuentaSeleccionada.setText(tituloCuenta);
            this.txtCuentaSeleccionada.setForeground(Color.red);
            this.btnAbonar.setEnabled(false);
            this.btnCargar.setEnabled(false);
        }
        Integer tipoCatalogo = sesion.configUsuario.getCicloContable().getTipoCatalogo().getId();
        Integer idCiclo = sesion.configUsuario.getCicloContable().getId();
        
        CuentaBalanza cuentaBalanza = ( CuentaBalanza ) _cuenta
                .verCuentaBalanzaComprobacion(
                    tipoCatalogo, idCiclo, Integer.parseInt(Constantes.TIPO_PARTIDA_CIERRE.getValue()), cuentaSeleccionada.getId()
                )
        .getDatos();
        if(cuentaBalanza == null) {
            lblSaldoAcreedor.setText("...");
            lblSaldoDeudor.setText("...");
        } else {
            lblSaldoDeudor.setText(UtileriaVista.FormateadorMoneda.format(cuentaBalanza.getSaldoDeudor()));
            lblSaldoAcreedor.setText(UtileriaVista.FormateadorMoneda.format(cuentaBalanza.getSaldoAcreedor()));
        }
    }//GEN-LAST:event_tblCuentasMouseClicked

    private void tblCuentasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCuentasKeyTyped
        
    }//GEN-LAST:event_tblCuentasKeyTyped

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        if (!Constantes.validarPorcentaje(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMontoKeyTyped

    private void txtFMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFMKeyTyped
        if (!Constantes.validarNumeros(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtFMKeyTyped

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
//            java.util.logging.Logger.getLogger(dSeleccionarCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(dSeleccionarCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(dSeleccionarCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(dSeleccionarCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                dSeleccionarCuenta dialog = new dSeleccionarCuenta(new javax.swing.JFrame(), true);
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
    private RSMaterialComponent.RSButtonShapeIcon btnAbonar;
    private RSMaterialComponent.RSButtonShapeIcon btnBuscarCuenta;
    private RSMaterialComponent.RSButtonShapeIcon btnCancelarTipoCatalogo;
    private RSMaterialComponent.RSButtonShapeIcon btnCargar;
    private RSMaterialComponent.RSButtonShapeIcon btnLimpiarBusquedaCuenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblSaldoAcreedor;
    private javax.swing.JLabel lblSaldoDeudor;
    private rojerusan.RSTableMetro tblCuentas;
    private javax.swing.JLabel totalCuentas;
    private javax.swing.JLabel totalCuentas1;
    private javax.swing.JLabel totalCuentas2;
    private javax.swing.JLabel txtCuentaSeleccionada;
    private RSMaterialComponent.RSTextFieldMaterial txtFM;
    private RSMaterialComponent.RSTextFieldMaterial txtMonto;
    private RSMaterialComponent.RSTextFieldMaterial txtQueryBusqueda;
    // End of variables declaration//GEN-END:variables
}
