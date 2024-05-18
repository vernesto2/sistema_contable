/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import conexion.Conexion;
import dto.dtoLista;
import dto.dtoPartida;
import formularios.dPartidas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import modelo.Cuenta;
import modelo.Partida;
import modelo.PartidaDetalle;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import rojeru_san.efectos.ValoresEnum;
import servicios.ServicioPartida;
import sesion.Sesion;
import utils.Render;
import utils.UtileriaVista;
import utils.constantes.Constantes;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class vLibroDiario extends javax.swing.JPanel {

    ArrayList<dtoLista> listaTipoPartidas = new ArrayList<>();
    /**
     * Creates new form vLibroDiario
     */
    Sesion sesion;
    Partida partidaModel = new Partida();
    ArrayList<dtoPartida> listaPartidas = new ArrayList<>();
    ServicioPartida _partida;
    DefaultTableModel dtm = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    
    Cuenta cuentaMayor = new Cuenta();
    int cantidadCodigo = 0;
    

    public vLibroDiario(Sesion sesion) {
        initComponents();
        this.sesion = sesion;
        _partida = new ServicioPartida(sesion.rutaConexion);
        this.setModelPartida();
        this.listaTipoPartidas = Constantes.listaTipoPartidas();
        this.obtenerListadoPartidas();
    }

    public void setModelPartida() {
        String[] cabecera = {"# Partida", "Fecha", "Hora", "Tipo de Partida", "Comentario", "Monto", "Editar", "Eliminar"};
        dtm.setColumnIdentifiers(cabecera);
        tblPartidas.setModel(dtm);
        tblPartidas.setDefaultRenderer(Object.class, new Render());
    }

    public void setDatosPartida() {
        RSMaterialComponent.RSButtonCustomIcon btn1 = new RSMaterialComponent.RSButtonCustomIcon();
        RSMaterialComponent.RSButtonCustomIcon btn2 = new RSMaterialComponent.RSButtonCustomIcon();
        btn1.setIcons(ValoresEnum.ICONS.EDIT);
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);
        btn1.setCursor(cur);
        btn2.setIcons(ValoresEnum.ICONS.DELETE);
        btn2.setColorIcon(Color.RED);
        btn2.setCursor(cur);

        Object[] datos = new Object[dtm.getColumnCount()];
        for (dtoPartida partida : listaPartidas) {
            datos[0] = partida.getNum_partida();
            //datos[1] = partida.getFolio();
            datos[1] = sdf.format(partida.getFecha());
            datos[2] = partida.getHora();
            datos[3] = this.listaTipoPartidas.get(partida.getId_tipo_partida()).getLabel();
            datos[4] = partida.getComentario();
            datos[5] = partida.getMonto();
            datos[6] = btn1;
            datos[7] = btn2;
            dtm.addRow(datos);
        }
        tblPartidas.setModel(dtm);
        tblPartidas.setAutoResizeMode(tblPartidas.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tblPartidas.getColumnModel().getColumn(0).setPreferredWidth(30);
        //tblPartidas.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblPartidas.getColumnModel().getColumn(1).setPreferredWidth(40);
        tblPartidas.getColumnModel().getColumn(2).setPreferredWidth(40);
        tblPartidas.getColumnModel().getColumn(3).setPreferredWidth(70);
        tblPartidas.getColumnModel().getColumn(4).setPreferredWidth(400);
        tblPartidas.getColumnModel().getColumn(5).setPreferredWidth(100);
        tblPartidas.getColumnModel().getColumn(6).setPreferredWidth(20);
        tblPartidas.getColumnModel().getColumn(7).setPreferredWidth(20);
        
        tblPartidas.setCellSelectionEnabled(true); // Permitir la selección de celdas individuales
        tblPartidas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Mapear la acción de copiar al atajo Ctrl+C
        KeyStroke copyKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx());
        tblPartidas.getInputMap(JComponent.WHEN_FOCUSED).put(copyKeyStroke, "copy");
        tblPartidas.getActionMap().put("copy", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copyTableToClipboard(tblPartidas);
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

    public void obtenerListadoPartidas() {
        this.listaPartidas = new ArrayList<>();
        tblPartidas.clearSelection();
        this.limiparTablaPartidas();
        RespuestaGeneral rg = _partida.obtenerListaPorIdCicloContable(sesion.configUsuario.getId_ciclo_contable(), this.txtBusquedaPartidas.getText());
        this.totalPartidas.setText("0");
        if (rg.esExitosa()) {
            this.listaPartidas = (ArrayList<dtoPartida>) rg.getDatos();
            this.totalPartidas.setText(String.valueOf(this.listaPartidas.size()));
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo obtener el listado", "ALERTA", UtileriaVista.devolverCodigoMensaje(rg));
        }
        this.setDatosPartida();
    }

    public void limiparTablaPartidas() {
        for (int i = 0; i < tblPartidas.getRowCount(); i++) {
            dtm.removeRow(i);
            i -= 1;
        }
    }

    public void limpiarFormPartida() {
        partidaModel = new Partida();
        tblPartidas.clearSelection();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPartidas = new rojerusan.RSTableMetro();
        jLabel8 = new javax.swing.JLabel();
        txtBusquedaPartidas = new RSMaterialComponent.RSTextFieldMaterial();
        btnBuscarCicloContable = new RSMaterialComponent.RSButtonShapeIcon();
        btnLimpiarCicloContable = new RSMaterialComponent.RSButtonShapeIcon();
        btnVerReporteLibroDiario = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel2 = new javax.swing.JLabel();
        totalPartidas = new javax.swing.JLabel();
        btnNuevoCicloContable1 = new RSMaterialComponent.RSButtonShapeIcon();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Listado de partidas   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        tblPartidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblPartidas.setBackgoundHead(new java.awt.Color(153, 153, 153));
        tblPartidas.setBackgoundHover(new java.awt.Color(204, 204, 204));
        tblPartidas.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        tblPartidas.setColorSecundaryText(new java.awt.Color(51, 51, 51));
        tblPartidas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblPartidas.setFontHead(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblPartidas.setFontRowHover(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblPartidas.setFontRowSelect(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblPartidas.setPositionText(rojerusan.RSTableMetro.POSITION_TEXT.LEFT);
        tblPartidas.setRowHeight(30);
        tblPartidas.setSelectionBackground(new java.awt.Color(153, 153, 153));
        tblPartidas.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblPartidas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPartidas.setShowGrid(true);
        tblPartidas.setShowVerticalLines(false);
        tblPartidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPartidasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPartidas);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Busqueda:");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtBusquedaPartidas.setForeground(new java.awt.Color(0, 0, 0));
        txtBusquedaPartidas.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtBusquedaPartidas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtBusquedaPartidas.setPhColor(new java.awt.Color(0, 0, 0));
        txtBusquedaPartidas.setPlaceholder("Buscar por número de partida");
        txtBusquedaPartidas.setSelectionColor(new java.awt.Color(0, 0, 0));

        btnBuscarCicloContable.setBackground(new java.awt.Color(33, 58, 86));
        btnBuscarCicloContable.setText("BUSCAR");
        btnBuscarCicloContable.setToolTipText("BUSCAR");
        btnBuscarCicloContable.setBackgroundHover(new java.awt.Color(33, 68, 86));
        btnBuscarCicloContable.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBuscarCicloContable.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnBuscarCicloContable.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
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

        btnVerReporteLibroDiario.setBackground(new java.awt.Color(0, 153, 0));
        btnVerReporteLibroDiario.setText("Ver libro diario");
        btnVerReporteLibroDiario.setToolTipText("Ver libro diario");
        btnVerReporteLibroDiario.setBackgroundHover(new java.awt.Color(0, 178, 0));
        btnVerReporteLibroDiario.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnVerReporteLibroDiario.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnVerReporteLibroDiario.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.VIEW_LIST);
        btnVerReporteLibroDiario.setSizeIcon(18.0F);
        btnVerReporteLibroDiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerReporteLibroDiarioActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Total de registros: ");

        totalPartidas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        totalPartidas.setText("100");

        btnNuevoCicloContable1.setBackground(new java.awt.Color(0, 153, 0));
        btnNuevoCicloContable1.setText("NUEVO");
        btnNuevoCicloContable1.setToolTipText("NUEVO REGISTRO DE PARTIDA");
        btnNuevoCicloContable1.setBackgroundHover(new java.awt.Color(0, 178, 0));
        btnNuevoCicloContable1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnNuevoCicloContable1.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnNuevoCicloContable1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btnNuevoCicloContable1.setSizeIcon(18.0F);
        btnNuevoCicloContable1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoCicloContable1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBusquedaPartidas, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnLimpiarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnNuevoCicloContable1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalPartidas, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVerReporteLibroDiario, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusquedaPartidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarCicloContable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevoCicloContable1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(totalPartidas))
                        .addGap(27, 27, 27))
                    .addComponent(btnVerReporteLibroDiario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblPartidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPartidasMouseClicked
        // logica de acciones de botones
        int accion = tblPartidas.getSelectedColumn();
        int row = tblPartidas.getSelectedRow();
        if (accion == 6) {
            // editar
            this.setearModeloCicloContable(row);
            this.abrirDialogPartida(partidaModel);

        } else if (accion == 7) {
            // eliminar
            String texto = "¿Esta seguro de continuar?, Se eliminará el registro:\n" + this.listaPartidas.get(row).getComentario();
            int opc = JOptionPane.showConfirmDialog(null, texto, "¡ALERTA!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (opc == 0) {
                RespuestaGeneral rg = _partida.eliminar(this.listaPartidas.get(row).getId());
                if (rg.esExitosa()) {
                    JOptionPane.showMessageDialog(this, rg.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rg));
                    this.limpiarFormPartida();
                    this.obtenerListadoPartidas();
                } else {
                    JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
                }
            } 
        }
    }//GEN-LAST:event_tblPartidasMouseClicked

    public void setearModeloCicloContable(int row) {
        this.partidaModel = this.listaPartidas.get(row);
        RespuestaGeneral rg = _partida.obtenerDetallesPorIdPartida(this.partidaModel.getId(), this.sesion.configUsuario.getCicloContable().getTipoCatalogo().getId(), this.sesion.configUsuario.getCicloContable().getId());
        if (rg.esExitosa()) {
            ArrayList<PartidaDetalle> lista = (ArrayList<PartidaDetalle>)rg.getDatos();
            // del listado tendremos que buscar si tienen padre las cuentas cargadas o abonadas
            lista.forEach((t) -> {
                // si se cumple la condicion significa que estas son sub-cuentas
               if (t.getParcial() > 0) {
                   t.setCuentaMayor(this.devolverCuentaMayor(lista, t.getCuenta()));
               } 
            });
           this.partidaModel.setListaPartidaDetalles(lista);
        }
        // buscamos los detalles de la partida para setearlo antes de mandar a llamar al Dialog
    }
    
    public Cuenta devolverCuentaMayor(ArrayList<PartidaDetalle> lista, Cuenta cuenta) {
        cuentaMayor = new Cuenta();
        cantidadCodigo = 0;
        for (PartidaDetalle t : lista) {
            if (t.getCuenta().getNivel() == this.sesion.configUsuario.getCicloContable().getTipoCatalogo().getNivel_mayorizar()) {
                cantidadCodigo = t.getCuenta().getCodigo().length();
                break;
            }
        }
        
        String codigoPadre = cuenta.getCodigo().substring(0, cantidadCodigo);
        lista.forEach((t) -> {
            if (t.getCuenta().getCodigo().equals(codigoPadre)) {
                cuentaMayor = t.getCuenta();
            }
        });
        
        return cuentaMayor;
    }
    
    public void abrirDialogPartida(Partida partida) {
        dPartidas d = new dPartidas(null, true, partida, sesion);
        d.setVisible(true);
        // validamos si realizo alguna accion para actualizar el listado o no
        if (d.getRealizoAccion()) {
            JOptionPane.showMessageDialog(this, d.getRG().getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(d.getRG()));
            this.obtenerListadoPartidas();
        }
    }
    
    private void btnBuscarCicloContableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCicloContableActionPerformed
        this.obtenerListadoPartidas();
    }//GEN-LAST:event_btnBuscarCicloContableActionPerformed

    private void btnLimpiarCicloContableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarCicloContableActionPerformed
        this.txtBusquedaPartidas.setText("");
        this.obtenerListadoPartidas();
    }//GEN-LAST:event_btnLimpiarCicloContableActionPerformed

    private void btnVerReporteLibroDiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerReporteLibroDiarioActionPerformed
        verReporteLibroDiario();
    }//GEN-LAST:event_btnVerReporteLibroDiarioActionPerformed

    private void verReporteLibroDiario() {
        try (
                InputStream inputStream = getClass().getResourceAsStream("/reportes/reporte-libro-diario-3.jrxml");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))
                ) {
            Map<String, Object> params = new HashMap<String, Object>();
            
            Connection con;
            JasperReport reporte;
            Conexion cx = new Conexion(sesion.rutaConexion);
            con = cx.conectar();
            reporte = JasperCompileManager.compileReport(inputStream);

            //Currency currentyActual = Currency.getInstance(Locale.US);
            Locale locale = new Locale("es", "SV");

            params.put("param_id_ciclo", sesion.configUsuario.getId_ciclo_contable());
            params.put("param_titulo_ciclo_contable", sesion.configUsuario.getNombre_ciclo_contable());
            params.put("param_nombre_completo", sesion.usuario.getPersona().nombreCompleto());
            params.put("param_usuario", sesion.usuario.getNombre());
            params.put("param_desde", sesion.configUsuario.getCicloContable().getDesde());
            params.put("param_hasta", sesion.configUsuario.getCicloContable().getHasta());
            params.put("param_fecha_generacion", new Date());
            params.put(JRParameter.REPORT_LOCALE, locale);
            
            JasperPrint jp = JasperFillManager.fillReport(reporte, params, con);
            final boolean EXIT_ON_CLOSE = false;
            JasperViewer.viewReport(jp, EXIT_ON_CLOSE);
        } catch (IOException | JRException ex) {
            Logger.getLogger(vLibroDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void btnNuevoCicloContable1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoCicloContable1ActionPerformed
        // obtenemos el ultimo # de partida
        Partida partidaAux = new Partida();
        partidaAux.setNum_partida("-");
        this.abrirDialogPartida(partidaAux);
    }//GEN-LAST:event_btnNuevoCicloContable1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShapeIcon btnBuscarCicloContable;
    private RSMaterialComponent.RSButtonShapeIcon btnLimpiarCicloContable;
    private RSMaterialComponent.RSButtonShapeIcon btnNuevoCicloContable1;
    private RSMaterialComponent.RSButtonShapeIcon btnVerReporteLibroDiario;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSTableMetro tblPartidas;
    private javax.swing.JLabel totalPartidas;
    private RSMaterialComponent.RSTextFieldMaterial txtBusquedaPartidas;
    // End of variables declaration//GEN-END:variables
}
