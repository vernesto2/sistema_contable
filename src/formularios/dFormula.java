/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package formularios;

import dto.dtoFormula;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import modelo.TipoCatalogo;
import rojeru_san.efectos.ValoresEnum;
import servicios.ServicioFormula;
import sesion.Sesion;
import utils.Render;
import utils.UtileriaVista;
import utils.constantes.Constantes;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class dFormula extends javax.swing.JDialog {

    RespuestaGeneral rg = new RespuestaGeneral();
    ServicioFormula _formula;

    ArrayList<dtoFormula> listaFormula = new ArrayList<>();
    TipoCatalogo tipoCatalogo = new TipoCatalogo();

    boolean realizoAccion = false;
    Sesion sesion;

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
    public dFormula(java.awt.Frame parent, boolean modal, TipoCatalogo tipoCatalogo, Sesion sesion) {
        super(parent, modal);
        initComponents();
        this.sesion = sesion;
        _formula = new ServicioFormula(sesion.rutaConexion);
        this.tipoCatalogo = tipoCatalogo;
        this.iniciarVistaDialog();
    }

    public void iniciarVistaDialog() {
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("FORMULA PARA: " + this.tipoCatalogo.getTipo().toUpperCase());
        this.realizoAccion = false;
        this.setModelFormula();
        this.obtenerListaFormula();
        this.setDatosFormula();
    }

    public void setModelFormula() {
        String[] cabecera = {"Posición", "Signo", "Codigo", "Nombre", "Tipo Formula", "Formula Padre", "Cuenta Especial", "Editar", "Eliminar", "Sub-Formula"};
        dtm.setColumnIdentifiers(cabecera);
        tblFormula.setModel(dtm);
        Render rr = new Render();
        rr.setColumna(0);
        tblFormula.setDefaultRenderer(Object.class, rr);
    }

    public void setDatosFormula() {
        RSMaterialComponent.RSButtonCustomIcon btn1 = new RSMaterialComponent.RSButtonCustomIcon();
        RSMaterialComponent.RSButtonCustomIcon btn2 = new RSMaterialComponent.RSButtonCustomIcon();
        RSMaterialComponent.RSButtonCustomIcon btn3 = new RSMaterialComponent.RSButtonCustomIcon();
        btn1.setIcons(ValoresEnum.ICONS.EDIT);
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);
        btn1.setCursor(cur);
        btn2.setIcons(ValoresEnum.ICONS.DELETE);
        btn2.setColorIcon(Color.RED);
        btn2.setCursor(cur);
        btn3.setIcons(ValoresEnum.ICONS.PLUS_ONE);
        btn3.setColorIcon(Color.green);
        btn3.setCursor(cur);
        this.limiparTablaFormula();
        Object[] datos = new Object[dtm.getColumnCount()];
        // ordenamos el listado antes de mostrar
        Collections.sort(listaFormula);

        for (dtoFormula detalle : this.listaFormula) {
            datos[0] = detalle.getFormula().getPosicion();
            datos[1] = detalle.getFormula().getSigno();
            datos[2] = detalle.getFormula().getCuenta().getCodigo();
            datos[3] = detalle.getFormula().getNombre();
            datos[4] = detalle.getFormula().getTipo_formula();
            datos[5] = detalle.getFormulaPadre().getNombre();
            datos[6] = Constantes.devolverCuentaEspecial(detalle.getFormula().getTipo_cuenta_especial());
            datos[7] = btn1;
            datos[8] = btn2;
            datos[9] = btn3;
            dtm.addRow(datos);
        }
        tblFormula.setModel(dtm);
        tblFormula.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //tblFormula.setAutoResizeMode(tblFormula.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tblFormula.getColumnModel().getColumn(0).setPreferredWidth(70);
        tblFormula.getColumnModel().getColumn(1).setPreferredWidth(60);
        tblFormula.getColumnModel().getColumn(2).setPreferredWidth(90);
        tblFormula.getColumnModel().getColumn(3).setPreferredWidth(300);
        tblFormula.getColumnModel().getColumn(4).setPreferredWidth(160);
        tblFormula.getColumnModel().getColumn(5).setPreferredWidth(280);
        tblFormula.getColumnModel().getColumn(6).setPreferredWidth(120);
        tblFormula.getColumnModel().getColumn(7).setPreferredWidth(60);
        tblFormula.getColumnModel().getColumn(8).setPreferredWidth(60);
        tblFormula.getColumnModel().getColumn(9).setPreferredWidth(80);
        
        tblFormula.setCellSelectionEnabled(true); // Permitir la selección de celdas individuales
        tblFormula.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Mapear la acción de copiar al atajo Ctrl+C
        KeyStroke copyKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx());
        tblFormula.getInputMap(JComponent.WHEN_FOCUSED).put(copyKeyStroke, "copy");
        tblFormula.getActionMap().put("copy", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copyTableToClipboard(tblFormula);
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
        for (int i = 0; i < tblFormula.getRowCount(); i++) {
            dtm.removeRow(i);
            i -= 1;
        }
    }

    public void obtenerListaFormula() {
        this.totalFormula.setText("0");
        RespuestaGeneral rgf = _formula.obtenerListaPorIdTipoCatalogo(this.tipoCatalogo.getId());
        if (rgf.esExitosa()) {
            this.listaFormula = (ArrayList<dtoFormula>) rgf.getDatos();
            this.totalFormula.setText(String.valueOf(this.listaFormula.size()));

        }
    }
/*

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
        tblFormula = new rojerusan.RSTableMetro();
        btnAgregarDetallePartida = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel2 = new javax.swing.JLabel();
        totalFormula = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Detalles formula  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        tblFormula.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblFormula.setBackgoundHead(new java.awt.Color(153, 153, 153));
        tblFormula.setBackgoundHover(new java.awt.Color(204, 204, 204));
        tblFormula.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        tblFormula.setColorSecundaryText(new java.awt.Color(51, 51, 51));
        tblFormula.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblFormula.setFontHead(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblFormula.setFontRowHover(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblFormula.setFontRowSelect(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblFormula.setPositionText(rojerusan.RSTableMetro.POSITION_TEXT.LEFT);
        tblFormula.setRowHeight(30);
        tblFormula.setSelectionBackground(new java.awt.Color(153, 153, 153));
        tblFormula.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblFormula.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblFormula.setShowGrid(true);
        tblFormula.setShowVerticalLines(false);
        tblFormula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFormulaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblFormula);

        btnAgregarDetallePartida.setBackground(new java.awt.Color(0, 153, 0));
        btnAgregarDetallePartida.setText("AGREGAR DETALLE FORMULA");
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

        totalFormula.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        totalFormula.setText("100");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1119, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAgregarDetallePartida, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(totalFormula))
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


    private void tblFormulaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFormulaMouseClicked
        int row = tblFormula.getSelectedRow();
        int column = tblFormula.getSelectedColumn();
        if (column == 7) {

            dFormFormula d = new dFormFormula(null, true, sesion, this.listaFormula.get(row), this.tipoCatalogo, new dtoFormula());
            d.setVisible(true);
            // validamos si realizo alguna accion para actualizar el listado o no
            if (d.isRealizoAccion()) {
                JOptionPane.showMessageDialog(this, d.getRg().getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(d.getRg()));
                this.obtenerListaFormula();
                this.setDatosFormula();
            }

        } else if (column == 8) {

            String texto = "¿Esta seguro de continuar?, Se eliminará el registro:\n" + this.listaFormula.get(row).getFormula().getNombre();
            int opc = JOptionPane.showConfirmDialog(null, texto, "¡ALERTA!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opc == 0) {
                RespuestaGeneral rg = _formula.eliminar(this.listaFormula.get(row).getFormula().getId());
                if (rg.esExitosa()) {
                    JOptionPane.showMessageDialog(this, rg.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rg));
                    this.limiparTablaFormula();
                    this.obtenerListaFormula();
                    this.setDatosFormula();
                } else {
                    JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
                }
            }

        } else if (column == 9) {
            dFormFormula d = new dFormFormula(null, true, sesion, new dtoFormula(), this.tipoCatalogo, this.listaFormula.get(row));
            d.setVisible(true);
            // validamos si realizo alguna accion para actualizar el listado o no
            if (d.isRealizoAccion()) {
                JOptionPane.showMessageDialog(this, d.getRg().getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(d.getRg()));
                this.obtenerListaFormula();
                this.setDatosFormula();
            }
        }
    }//GEN-LAST:event_tblFormulaMouseClicked

    private void btnAgregarDetallePartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDetallePartidaActionPerformed
        dFormFormula d = new dFormFormula(null, true, sesion, new dtoFormula(), this.tipoCatalogo, new dtoFormula());
        d.setVisible(true);
        // validamos si realizo alguna accion para actualizar el listado o no
        if (d.isRealizoAccion()) {
            JOptionPane.showMessageDialog(this, d.getRg().getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(d.getRg()));
            this.obtenerListaFormula();
            this.setDatosFormula();
        }
    }//GEN-LAST:event_btnAgregarDetallePartidaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShapeIcon btnAgregarDetallePartida;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private rojerusan.RSTableMetro tblFormula;
    private javax.swing.JLabel totalFormula;
    // End of variables declaration//GEN-END:variables
}
