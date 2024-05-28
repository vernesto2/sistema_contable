/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package formularios;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import modelo.CicloContable;
import modelo.CuentaBalance;
import modelo.TipoCatalogo;
import rojeru_san.efectos.ValoresEnum;
import servicios.ServicioCuentaBalance;
import sesion.Sesion;
import utils.Render;
import utils.UtileriaVista;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class dCuentasSaldos extends javax.swing.JDialog {

    RespuestaGeneral rg = new RespuestaGeneral();
    ServicioCuentaBalance _cBalance;
    
    ArrayList<CuentaBalance> listaCuentaBalance = new ArrayList<>();
    CicloContable cicloContable = new CicloContable();
    //TipoCatalogo tipoCatalogo = new TipoCatalogo();
    
    boolean realizoAccion = false;
    Sesion sesion;
    TipoCatalogo tipoCatalogo = new TipoCatalogo();
    
    DefaultTableModel dtm = new DefaultTableModel() {
        @Override 
        public boolean isCellEditable(int row, int column) { 
            return false;
        }
    };
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Creates new form dTipoCatalogo
     */
    public dCuentasSaldos(java.awt.Frame parent, boolean modal, CicloContable cicloContable, Sesion sesion, TipoCatalogo tipoCatalogo) {
        super(parent, modal);
        initComponents();
        this.sesion = sesion;
        _cBalance = new ServicioCuentaBalance(sesion.rutaConexion);
        this.tipoCatalogo = tipoCatalogo;
        this.cicloContable = cicloContable;
        this.cicloContable.setId_catalogo(cicloContable.getId_catalogo());
        this.iniciarVistaDialog();
    }
    
    public void iniciarVistaDialog() {
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("SALDOS PARA: " + this.cicloContable.getTitulo().toUpperCase());
        this.realizoAccion = false;
        this.setModelFormula();
        this.obtenerListaCuentaSaldo();
        this.setDatosFormula();
    }

    public void setModelFormula() {
        String[] cabecera = {"Codigo", "Concepto", "Saldo Inicial", "Folio Mayor","Editar","Eliminar"};
        dtm.setColumnIdentifiers(cabecera);
        tblCuentasSaldos.setModel(dtm);
        Render rr = new Render();
        rr.setColumna(0);
        tblCuentasSaldos.setDefaultRenderer(Object.class, rr);
    }
    
    public void setDatosFormula() {
        RSMaterialComponent.RSButtonCustomIcon btn1 = new RSMaterialComponent.RSButtonCustomIcon();
        RSMaterialComponent.RSButtonCustomIcon btn2 = new RSMaterialComponent.RSButtonCustomIcon();
        btn1.setIcons(ValoresEnum.ICONS.EDIT);
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);
        btn1.setCursor(cur);
        btn2.setIcons(ValoresEnum.ICONS.DELETE);
        btn2.setColorIcon(Color.RED);
        btn2.setCursor(cur);
        btn1.setToolTipText("EDITAR REGISTRO");
        btn2.setToolTipText("ELIMINAR REGISTRO");
        this.limiparTablaFormula();
        Object[] datos = new Object[dtm.getColumnCount()];
        // ordenamos el listado antes de mostrar
        
        for (CuentaBalance detalle : this.listaCuentaBalance) {
            datos[0] = detalle.getCuenta().getCodigo();
            datos[1] = detalle.getCuenta().getNombre();
            datos[2] = String.valueOf(detalle.getSaldo_inicial());
            //datos[3] = String.valueOf(detalle.getSaldo_final());
            datos[3] = String.valueOf(detalle.getCuenta().getFolio_mayor());
            datos[4] = btn1;
            datos[5] = btn2;
            dtm.addRow(datos);
        }
        tblCuentasSaldos.setModel(dtm);
        tblCuentasSaldos.setAutoResizeMode(tblCuentasSaldos.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tblCuentasSaldos.getColumnModel().getColumn(0).setPreferredWidth(60);
        tblCuentasSaldos.getColumnModel().getColumn(1).setPreferredWidth(300);
        tblCuentasSaldos.getColumnModel().getColumn(2).setPreferredWidth(60);
        tblCuentasSaldos.getColumnModel().getColumn(3).setPreferredWidth(60);
        tblCuentasSaldos.getColumnModel().getColumn(4).setPreferredWidth(90);
        tblCuentasSaldos.getColumnModel().getColumn(5).setPreferredWidth(90);
        
        tblCuentasSaldos.setCellSelectionEnabled(true); // Permitir la selección de celdas individuales
        tblCuentasSaldos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Mapear la acción de copiar al atajo Ctrl+C
        KeyStroke copyKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx());
        tblCuentasSaldos.getInputMap(JComponent.WHEN_FOCUSED).put(copyKeyStroke, "copy");
        tblCuentasSaldos.getActionMap().put("copy", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copyTableToClipboard(tblCuentasSaldos);
            }
        });
    }
    
    private static void copyTableToClipboard(JTable table) {
        StringBuilder sb = new StringBuilder();
        int numCols = table.getSelectedColumnCount();
        int numRows = table.getSelectedRowCount();
        int[] selectedRows = table.getSelectedRows();
        int[] selectedCols = table.getSelectedColumns();

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                sb.append(table.getValueAt(selectedRows[i], selectedCols[j]));
                if (j < numCols - 1) {
                    sb.append("\t"); // Separar las celdas por tabuladores
                }
            }
            sb.append("\n"); // Nueva línea para cada fila
        }

        StringSelection selection = new StringSelection(sb.toString());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
    }
    
    public void limiparTablaFormula() {
        for (int i = 0; i < tblCuentasSaldos.getRowCount(); i++) {
            dtm.removeRow(i);
            i-=1;
        }
    }
    
    public void obtenerListaCuentaSaldo() {
        this.totalCuentasSaldos.setText("0");
        RespuestaGeneral rgf = _cBalance.obtenerListaPorIdCicloContable(this.cicloContable.getId(), this.cicloContable.getId_catalogo());
        if (rgf.esExitosa()) {
            this.listaCuentaBalance = (ArrayList<CuentaBalance>)rgf.getDatos();
            this.totalCuentasSaldos.setText(String.valueOf(this.listaCuentaBalance.size()));
       }
    }
    
    public boolean getRealizoAccion() {
        return realizoAccion;
    }
    
    public RespuestaGeneral getRG() {
        return rg;
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
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCuentasSaldos = new rojerusan.RSTableMetro();
        btnAgregarDetallePartida = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel2 = new javax.swing.JLabel();
        totalCuentasSaldos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Detalles de Cuentas y Saldos   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        tblCuentasSaldos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblCuentasSaldos.setBackgoundHead(new java.awt.Color(153, 153, 153));
        tblCuentasSaldos.setBackgoundHover(new java.awt.Color(204, 204, 204));
        tblCuentasSaldos.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        tblCuentasSaldos.setColorSecundaryText(new java.awt.Color(51, 51, 51));
        tblCuentasSaldos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblCuentasSaldos.setFontHead(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblCuentasSaldos.setFontRowHover(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblCuentasSaldos.setFontRowSelect(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblCuentasSaldos.setPositionText(rojerusan.RSTableMetro.POSITION_TEXT.LEFT);
        tblCuentasSaldos.setRowHeight(30);
        tblCuentasSaldos.setSelectionBackground(new java.awt.Color(153, 153, 153));
        tblCuentasSaldos.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblCuentasSaldos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCuentasSaldos.setShowGrid(true);
        tblCuentasSaldos.setShowVerticalLines(false);
        tblCuentasSaldos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCuentasSaldosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCuentasSaldos);

        btnAgregarDetallePartida.setBackground(new java.awt.Color(0, 153, 0));
        btnAgregarDetallePartida.setText("AGREGAR NUEVO SALDO");
        btnAgregarDetallePartida.setToolTipText("AGREGAR DETALLE");
        btnAgregarDetallePartida.setBackgroundHover(new java.awt.Color(0, 178, 0));
        btnAgregarDetallePartida.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAgregarDetallePartida.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnAgregarDetallePartida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAgregarDetallePartida.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAgregarDetallePartida.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btnAgregarDetallePartida.setSizeIcon(18.0F);
        btnAgregarDetallePartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDetallePartidaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Total de registros: ");

        totalCuentasSaldos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        totalCuentasSaldos.setText("100");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAgregarDetallePartida, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalCuentasSaldos, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(btnAgregarDetallePartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(totalCuentasSaldos))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    public void cerrar() {
        this.dispose();
    }
    
    
    private void tblCuentasSaldosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCuentasSaldosMouseClicked
        int row = tblCuentasSaldos.getSelectedRow();
        int column = tblCuentasSaldos.getSelectedColumn();
        if (column == 4) {
            
            this.abrirDialogCuentasSaldos(this.listaCuentaBalance.get(row));
            
        } else if (column == 5) {
            
            String texto = "¿Esta seguro de continuar?, Se eliminará el registro:\n" + this.listaCuentaBalance.get(row).getCuenta().getNombre();
            int opc = JOptionPane.showConfirmDialog(null, texto, "¡ALERTA!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opc == 0) {
                RespuestaGeneral rg = _cBalance.eliminar(this.listaCuentaBalance.get(row).getId());
                if (rg.esExitosa()) {
                    JOptionPane.showMessageDialog(this, rg.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rg));
                    this.limiparTablaFormula();
                    this.obtenerListaCuentaSaldo();
                    this.setDatosFormula();
                } else {
                    JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
                }
            }
            
        }
    }//GEN-LAST:event_tblCuentasSaldosMouseClicked

    private void btnAgregarDetallePartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDetallePartidaActionPerformed
        this.abrirDialogCuentasSaldos(new CuentaBalance());
    }//GEN-LAST:event_btnAgregarDetallePartidaActionPerformed

    public void abrirDialogCuentasSaldos (CuentaBalance cuentaBalance) {
        dFormCuentasSaldos d = new dFormCuentasSaldos(null, true, sesion, cuentaBalance, this.cicloContable, this.tipoCatalogo);
        d.setVisible(true);
        // validamos si realizo alguna accion para actualizar el listado o no
        if (d.isRealizoAccion()) {
            JOptionPane.showMessageDialog(this, d.getRg().getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(d.getRg()));
            this.obtenerListaCuentaSaldo();
            this.setDatosFormula();
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShapeIcon btnAgregarDetallePartida;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private rojerusan.RSTableMetro tblCuentasSaldos;
    private javax.swing.JLabel totalCuentasSaldos;
    // End of variables declaration//GEN-END:variables
}
