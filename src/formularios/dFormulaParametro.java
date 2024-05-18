/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package formularios;

import java.awt.Color;
import java.awt.Cursor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.CicloContable;
import modelo.FormulaParametro;
import rojeru_san.efectos.ValoresEnum;
import servicios.ServicioFormulaParametro;
import sesion.Sesion;
import utils.Render;
import utils.UtileriaVista;
import utils.constantes.Constantes;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class dFormulaParametro extends javax.swing.JDialog {

    RespuestaGeneral rg = new RespuestaGeneral();
    ServicioFormulaParametro _formulaP;

    ArrayList<FormulaParametro> listaFormula = new ArrayList<>();
    CicloContable cicloContable = new CicloContable();
    boolean realizoAccion = false;
    Sesion sesion;

    DefaultTableModel dtm = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return true;
        }
    };
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Creates new form dTipoCatalogo
     */
    public dFormulaParametro(java.awt.Frame parent, boolean modal, CicloContable cicloContable, Sesion sesion) {
        super(parent, modal);
        initComponents();
        this.sesion = sesion;
        _formulaP = new ServicioFormulaParametro(sesion.rutaConexion);
        this.cicloContable = cicloContable;
        this.iniciarVistaDialog();
    }

    public void iniciarVistaDialog() {
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("PARAMETROS PARA ESTADO RESULTADO DEL CICLO: " + this.cicloContable.getTitulo().toUpperCase());
        this.realizoAccion = false;
        this.setModelFormula();
        this.obtenerListaFormula();
        this.setDatosFormula();
    }
    
    public void obtenerListaFormula() {
        this.totalFormula.setText("0");
        RespuestaGeneral rg = this._formulaP.obtenerLista(this.cicloContable.getId(), Integer.parseInt(Constantes.TIPO_CUENTA_ESPECIAL_VALOR_INGRESADO.getValue()));
        if (rg.esExitosa()) {
            ArrayList<FormulaParametro> listaAux = (ArrayList<FormulaParametro>)rg.getDatos();
            if (!listaAux.isEmpty()) {
                this.listaFormula = listaAux;
                this.totalFormula.setText(String.valueOf(this.listaFormula.size()));
            }
        }
    }

    public void setModelFormula() {
        String[] cabecera = {"Almacenado", "Nombre", "Valor", "Editar", "Eliminar"};
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

        for (FormulaParametro detalle : this.listaFormula) {
            datos[0] = detalle.getId() > 0 ? "Si" : "No";
            datos[1] = detalle.getFormula().getNombre();
            datos[2] = detalle.getValor();
            datos[3] = detalle.getEliminado() == 0 ? btn1 : "";
            datos[4] = detalle.getEliminado() == 1 ? btn2 : "";
            dtm.addRow(datos);
        }
        tblFormula.setModel(dtm);
        tblFormula.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        //tblFormula.setAutoResizeMode(tblFormula.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tblFormula.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblFormula.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblFormula.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblFormula.getColumnModel().getColumn(3).setPreferredWidth(80);
        tblFormula.getColumnModel().getColumn(4).setPreferredWidth(80);
    }

    public void limiparTablaFormula() {
        for (int i = 0; i < tblFormula.getRowCount(); i++) {
            dtm.removeRow(i);
            i -= 1;
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
        jLabel2 = new javax.swing.JLabel();
        totalFormula = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Detalles   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

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

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Total de registros: ");

        totalFormula.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        totalFormula.setText("100");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(78, 78, 78))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
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
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 901, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        if (column == 3) {
            if (this.listaFormula.get(row).getEliminado() == 0) {
                // enviamos el valor al dialogo y luego se recupera para setearlo
                dModificarMontoFormulaParametro d = new dModificarMontoFormulaParametro(null, true, sesion, this.listaFormula.get(row));
                d.setVisible(true);
                if (d.isRealizoAccion()) {
                    this.obtenerListaFormula();
                    this.setDatosFormula();
                }
            }
            
        } else if (column == 4) {
            if (this.listaFormula.get(row).getEliminado() == 1) {
                String texto = "¿Esta seguro de continuar?, Se eliminará el registro:\n" + this.listaFormula.get(row).getFormula().getNombre();
                int opc = JOptionPane.showConfirmDialog(null, texto, "¡ALERTA!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (opc == 0) {
                    RespuestaGeneral rg = _formulaP.eliminar(this.listaFormula.get(row).getId());
                    if (rg.esExitosa()) {
                        JOptionPane.showMessageDialog(this, rg.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rg));
                        this.limiparTablaFormula();
                        this.obtenerListaFormula();
                        this.setDatosFormula();
                    } else {
                        JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
                    }
                }
            }
        }
    }//GEN-LAST:event_tblFormulaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private rojerusan.RSTableMetro tblFormula;
    private javax.swing.JLabel totalFormula;
    // End of variables declaration//GEN-END:variables
}
