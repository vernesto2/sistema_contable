/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package formularios;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cuenta;
import modelo.PartidaDetalle;
import servicios.ServicioCuenta;
import sesion.Sesion;
import utils.Render;
import utils.UtileriaVista;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class dSeleccionarCuentaFormula extends javax.swing.JDialog {

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
    public dSeleccionarCuentaFormula(java.awt.Frame parent, boolean modal, Sesion sesion) {
        super(parent, modal);
        initComponents();
        this.sesion = sesion;
        this._cuenta = new ServicioCuenta(sesion.rutaConexion);
        this.iniciarVistaDialog();
    }
    
    public void iniciarVistaDialog() {
        this.setLocationRelativeTo(this);
        this.setResizable(false);
        this.setTitle("SELECCIONAR CUENTA DE CATALOGO: " + this.sesion.configUsuario.getCicloContable().getTipoCatalogo().getTipo().toUpperCase());
        this.realizoAccion = false;
        // seteamos la informacion
        this.setModelCuentas();
        this.obtenerListadoCuentasPorTipoCatalogoCompleto();
        this.obtenerListadoCuentasPorTipoCatalogo();       
    }

    public void setModelCuentas() {
        //String[] cabecera = {"Disponible", "Nivel", "Codigo", "", "Concepto", "Tipo Saldo", "Ingresos", "Egresos"};
        String[] cabecera = {"Nivel", "Codigo", "", "Concepto", "Tipo Saldo", "Ingresos", "Egresos"};
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
            //datos[0] = disponible;
            datos[0] = cuenta.getNivel();
            datos[1] = cuenta.getCodigo();
            datos[2] = cuenta.getEs_restado() == 0 ? "" : "R";
            datos[3] = cuenta.getNombre();
            datos[4] = cuenta.getTipo_saldo().equals("D") ? "DEUDOR": cuenta.getTipo_saldo().equals("A") ? "ACREEDOR" : "-";
            datos[5] = cuenta.getIngresos();
            datos[6] = cuenta.getEgresos();
            dtm.addRow(datos);
        }
        tblCuentas.setModel(dtm);
        tblCuentas.setAutoResizeMode(tblCuentas.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tblCuentas.getColumnModel().getColumn(0).setPreferredWidth(90);
        tblCuentas.getColumnModel().getColumn(1).setPreferredWidth(120);
        tblCuentas.getColumnModel().getColumn(2).setPreferredWidth(25);
        tblCuentas.getColumnModel().getColumn(3).setPreferredWidth(425);
        tblCuentas.getColumnModel().getColumn(4).setPreferredWidth(90);
        tblCuentas.getColumnModel().getColumn(5).setPreferredWidth(100);
        tblCuentas.getColumnModel().getColumn(6).setPreferredWidth(100);
       
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
        btnSeleccionarCuenta = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel15 = new javax.swing.JLabel();
        txtQueryBusqueda = new RSMaterialComponent.RSTextFieldMaterial();
        btnBuscarCuenta = new RSMaterialComponent.RSButtonShapeIcon();
        btnLimpiarBusquedaCuenta = new RSMaterialComponent.RSButtonShapeIcon();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCuentas = new rojerusan.RSTableMetro();
        jLabel1 = new javax.swing.JLabel();
        totalCuentas = new javax.swing.JLabel();
        txtCuentaSeleccionada = new javax.swing.JLabel();

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

        btnSeleccionarCuenta.setBackground(new java.awt.Color(0, 153, 0));
        btnSeleccionarCuenta.setText("SELECCIONAR");
        btnSeleccionarCuenta.setToolTipText("SELECCIONAR CUENTA");
        btnSeleccionarCuenta.setBackgroundHover(new java.awt.Color(0, 178, 0));
        btnSeleccionarCuenta.setEnabled(false);
        btnSeleccionarCuenta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSeleccionarCuenta.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnSeleccionarCuenta.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CHECK);
        btnSeleccionarCuenta.setSizeIcon(18.0F);
        btnSeleccionarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarCuentaActionPerformed(evt);
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
                .addComponent(btnSeleccionarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCuentaSeleccionada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtQueryBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLimpiarBusquedaCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(txtCuentaSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(totalCuentas))
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
        this.btnSeleccionarCuenta.setEnabled(true);
        String tituloCuenta = this.cuentaSeleccionada.getCodigo() + " - " + this.cuentaSeleccionada.getNombre();
        this.txtCuentaSeleccionada.setText(tituloCuenta);
//        if (this.cuentaSeleccionada.getDisponible() == 1) {
//            String tituloCuenta = this.cuentaSeleccionada.getCodigo() + " - " + this.cuentaSeleccionada.getNombre();
//            this.txtCuentaSeleccionada.setText(tituloCuenta);
//            this.txtCuentaSeleccionada.setForeground(Color.black);
//        } else {
//            String tituloCuenta = "*CUENTA NO VALIDA*";
//            this.txtCuentaSeleccionada.setText(tituloCuenta);
//            this.txtCuentaSeleccionada.setForeground(Color.red);
//        }
        
    }//GEN-LAST:event_tblCuentasMouseClicked

    private void tblCuentasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCuentasKeyTyped
        
    }//GEN-LAST:event_tblCuentasKeyTyped

    private void btnSeleccionarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarCuentaActionPerformed
        if (this.cuentaSeleccionada.getId() < 0) {
            JOptionPane.showMessageDialog(this, "No ha seleccionado la cuenta", "!Alerta¡", JOptionPane.WARNING_MESSAGE);
        } else {
            this.realizoAccion = true;
            this.setCuentaSeleccionada(this.cuentaSeleccionada);
            this.dispose();
        }
        
    }//GEN-LAST:event_btnSeleccionarCuentaActionPerformed

    
    
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

    public Cuenta getCuentaSeleccionada() {
        return cuentaSeleccionada;
    }

    public void setCuentaSeleccionada(Cuenta cuentaSeleccionada) {
        this.cuentaSeleccionada = cuentaSeleccionada;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShapeIcon btnBuscarCuenta;
    private RSMaterialComponent.RSButtonShapeIcon btnCancelarTipoCatalogo;
    private RSMaterialComponent.RSButtonShapeIcon btnLimpiarBusquedaCuenta;
    private RSMaterialComponent.RSButtonShapeIcon btnSeleccionarCuenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private rojerusan.RSTableMetro tblCuentas;
    private javax.swing.JLabel totalCuentas;
    private javax.swing.JLabel txtCuentaSeleccionada;
    private RSMaterialComponent.RSTextFieldMaterial txtQueryBusqueda;
    // End of variables declaration//GEN-END:variables
}
