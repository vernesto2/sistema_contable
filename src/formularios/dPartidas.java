/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package formularios;


import dto.dtoLista;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
public class dPartidas extends javax.swing.JDialog {

    Partida partidaModel = new Partida();
    RespuestaGeneral rg = new RespuestaGeneral();
    ServicioPartida _partida;
    
    ArrayList<dtoLista> listaTipoPartida = new ArrayList<>();
    ArrayList<PartidaDetalle> listaPartidaDetalles = new ArrayList<>();
    ArrayList<PartidaDetalle> listaPartidaDetallesEliminados = new ArrayList<>();
    
    boolean realizoAccion = false;
    Sesion sesion;
    
    double totalDebe = 0;
    double totalHaber = 0;
    int rowPadre = 0;
    
    DefaultTableModel dtm = new DefaultTableModel() {
        @Override 
        public boolean isCellEditable(int row, int column) { 
            return false;
        }
    };
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    boolean banderaAgregar = true;
    int contador = 0;
    /**
     * Creates new form dTipoCatalogo
     */
    public dPartidas(java.awt.Frame parent, boolean modal, Partida partida, Sesion sesion) {
        super(parent, modal);
        initComponents();
        this.sesion = sesion;
        _partida = new ServicioPartida(sesion.rutaConexion);
        this.partidaModel = (Partida)partida;
        this.iniciarVistaDialog();
    }
    
    public void iniciarVistaDialog() {
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle((this.partidaModel.getId() > 0) ? "MODIFICACIÓN DE PARTIDA": "NUEVO REGISTRO DE PARTIDA");
        this.realizoAccion = false;
        // obtenemos los catalogos
        this.obtenerListaCmbTipoPartidas();
        // seteamos la informacion
        this.setModelPartida();
        this.setearData();
    }
    
    public void obtenerUltimoNumPartida() {
        
    }
    
    public void setearData() {
        txtNumPartida.setText(String.valueOf(this.partidaModel.getNum_partida()));
        //txtNumFolio.setText(String.valueOf(this.partidaModel.getFolio()));
        txtComentario.setText(this.partidaModel.getComentario());
        txtFecha.setDate(this.partidaModel.getFecha());
        txtHora.setText(this.partidaModel.getHora());
        txtFecha.setFormatDate("dd-MM-yyyy");
        
        // PROCESO PARA SELECCION DE COMBOBOX
        int iCmb = 0, i = 0;
        for (dtoLista item : listaTipoPartida) {
            if (Integer.parseInt(item.getValue()) == this.partidaModel.getId_tipo_partida()) {
                iCmb = i;
            }
            i++;
        }
        cmbTipoPartida.setSelectedIndex(iCmb);
        
        this.listaPartidaDetalles = this.partidaModel.getListaPartidaDetalles();
        this.actualizarMontosDeTabla();
        this.setDatosPartidaDetalle();
    }
    
    public void setModelPartida() {
        String[] cabecera = {"","Codigo", "","Concepto", "FM","Parcial","Debe","Haber","Editar","Eliminar"};
        dtm.setColumnIdentifiers(cabecera);
        tblDetallePartida.setModel(dtm);
        Render rr = new Render();
        rr.setColumna(0);
        tblDetallePartida.setDefaultRenderer(Object.class, rr);
    }
    
    public void setDatosPartidaDetalle() {
        RSMaterialComponent.RSButtonCustomIcon btn1 = new RSMaterialComponent.RSButtonCustomIcon();
        RSMaterialComponent.RSButtonCustomIcon btn2 = new RSMaterialComponent.RSButtonCustomIcon();
        btn1.setIcons(ValoresEnum.ICONS.EDIT);
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);
        btn1.setCursor(cur);
        btn2.setIcons(ValoresEnum.ICONS.DELETE);
        btn2.setColorIcon(Color.RED);
        btn2.setCursor(cur);
        this.limiparTablaDetallePartida();
        Object[] datos = new Object[dtm.getColumnCount()];
        for (PartidaDetalle detalle : this.listaPartidaDetalles) {
//            String cuentaNombre = "";
//            String campo = "   ";
//            if (detalle.getTipo_cargo_abono() == Constantes.TIPO_CARGO) {
//                cuentaNombre = detalle.getCuenta().getNombre();
//            } else {
//                cuentaNombre = (campo + detalle.getCuenta().getNombre());
//            }
            datos[0] = detalle.getTipo_cargo_abono() == 1 ? Constantes.TIPO_CARGO_S : Constantes.TIPO_ABONO_S;
            datos[1] = detalle.getCuenta().getCodigo();
            datos[2] = detalle.getCuenta().getEs_restado() == 0 ? "" : "R";
            datos[3] = detalle.getCuenta().getNombre();
            datos[4] = detalle.getCuenta().getFolio_mayor() == 0 ? "" : detalle.getCuenta().getFolio_mayor();
            datos[5] = detalle.getParcial() == 0 ? "" : detalle.getParcial();
            datos[6] = detalle.getDebe() == 0 ? "" : detalle.getDebe();
            datos[7] = detalle.getHaber() == 0 ? "" : detalle.getHaber();
            datos[8] = detalle.getCuenta().getDisponible() == 1 ? btn1 : ' ';
            datos[9] = detalle.getCuenta().getDisponible() == 1 ? btn2 : ' ';
            dtm.addRow(datos);
        }
        tblDetallePartida.setModel(dtm);
        tblDetallePartida.setAutoResizeMode(tblDetallePartida.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tblDetallePartida.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblDetallePartida.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblDetallePartida.getColumnModel().getColumn(2).setPreferredWidth(30);
        tblDetallePartida.getColumnModel().getColumn(3).setPreferredWidth(400);
        tblDetallePartida.getColumnModel().getColumn(4).setPreferredWidth(50);
        tblDetallePartida.getColumnModel().getColumn(5).setPreferredWidth(150);
        tblDetallePartida.getColumnModel().getColumn(6).setPreferredWidth(150);
        tblDetallePartida.getColumnModel().getColumn(7).setPreferredWidth(150);
        tblDetallePartida.getColumnModel().getColumn(8).setPreferredWidth(50);
        tblDetallePartida.getColumnModel().getColumn(9).setPreferredWidth(50);
        
        tblDetallePartida.setCellSelectionEnabled(true); // Permitir la selección de celdas individuales
        tblDetallePartida.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Mapear la acción de copiar al atajo Ctrl+C
        KeyStroke copyKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx());
        tblDetallePartida.getInputMap(JComponent.WHEN_FOCUSED).put(copyKeyStroke, "copy");
        tblDetallePartida.getActionMap().put("copy", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copyTableToClipboard(tblDetallePartida);
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
    
    public void limiparTablaDetallePartida() {
        for (int i = 0; i < tblDetallePartida.getRowCount(); i++) {
            dtm.removeRow(i);
            i-=1;
        }
    }
    
    public void obtenerListaCmbTipoPartidas() {
        this.listaTipoPartida = Constantes.listaTipoPartidas();
        cmbTipoPartida.removeAllItems();
        for (dtoLista item : this.listaTipoPartida) {
            cmbTipoPartida.addItem(item.getLabel());
        }
    }
    
    public boolean getRealizoAccion() {
        return realizoAccion;
    }
    
    public RespuestaGeneral getRG() {
        return rg;
    }

    public Partida getPartidaModel() {
        return partidaModel;
    }

    public void setCuentaModel(Partida partida) {
        this.partidaModel = partida;
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
        btnCancelarPartida = new RSMaterialComponent.RSButtonShapeIcon();
        btnGuardarPartida = new RSMaterialComponent.RSButtonShapeIcon();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFecha = new newscomponents.RSDateChooser();
        txtNumPartida = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtComentario = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbTipoPartida = new RSMaterialComponent.RSComboBoxMaterial();
        btnAgregarDetallePartida = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel5 = new javax.swing.JLabel();
        txtHora = new RSMaterialComponent.RSTextFieldMaterial();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetallePartida = new rojerusan.RSTableMetro();
        txtTotalHaber = new javax.swing.JLabel();
        txtTotalDebe = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        btnCancelarPartida.setBackground(new java.awt.Color(251, 205, 6));
        btnCancelarPartida.setText("CERRAR");
        btnCancelarPartida.setToolTipText("CERRAR EL FORMULARIO");
        btnCancelarPartida.setBackgroundHover(new java.awt.Color(251, 174, 6));
        btnCancelarPartida.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCancelarPartida.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnCancelarPartida.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnCancelarPartida.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnCancelarPartida.setForegroundText(new java.awt.Color(0, 0, 0));
        btnCancelarPartida.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnCancelarPartida.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        btnCancelarPartida.setSizeIcon(18.0F);
        btnCancelarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPartidaActionPerformed(evt);
            }
        });

        btnGuardarPartida.setBackground(new java.awt.Color(33, 58, 86));
        btnGuardarPartida.setText("GUARDAR");
        btnGuardarPartida.setToolTipText("GUARDAR / ACTUALIZAR PARTIDA");
        btnGuardarPartida.setBackgroundHover(new java.awt.Color(33, 68, 86));
        btnGuardarPartida.setEnabled(false);
        btnGuardarPartida.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuardarPartida.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnGuardarPartida.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btnGuardarPartida.setSizeIcon(18.0F);
        btnGuardarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPartidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(856, Short.MAX_VALUE)
                .addComponent(btnCancelarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos de partida   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Fecha:");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtFecha.setBackground(new java.awt.Color(153, 153, 153));
        txtFecha.setBgColor(new java.awt.Color(153, 153, 153));
        txtFecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtFecha.setFormatDate("dd/MM/yyyy");

        txtNumPartida.setForeground(new java.awt.Color(0, 0, 0));
        txtNumPartida.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtNumPartida.setEnabled(false);
        txtNumPartida.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtNumPartida.setPhColor(new java.awt.Color(0, 0, 0));
        txtNumPartida.setPlaceholder("# de partida");
        txtNumPartida.setSelectionColor(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("# Partida:");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtComentario.setColumns(20);
        txtComentario.setRows(5);
        jScrollPane1.setViewportView(txtComentario);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Comentario:");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Tipo de Partida:");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        cmbTipoPartida.setColorMaterial(new java.awt.Color(102, 102, 102));
        cmbTipoPartida.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoPartidaItemStateChanged(evt);
            }
        });

        btnAgregarDetallePartida.setBackground(new java.awt.Color(0, 153, 0));
        btnAgregarDetallePartida.setText("AGREGAR DETALLE");
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

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Hora:");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtHora.setForeground(new java.awt.Color(0, 0, 0));
        txtHora.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtHora.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtHora.setPhColor(new java.awt.Color(0, 0, 0));
        txtHora.setPlaceholder("Hora");
        txtHora.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtHora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHoraKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbTipoPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgregarDetallePartida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNumPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTipoPartida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNumPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btnAgregarDetallePartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addGap(2, 2, 2))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Detalles de partida  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        tblDetallePartida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblDetallePartida.setBackgoundHead(new java.awt.Color(153, 153, 153));
        tblDetallePartida.setBackgoundHover(new java.awt.Color(204, 204, 204));
        tblDetallePartida.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        tblDetallePartida.setColorSecundaryText(new java.awt.Color(51, 51, 51));
        tblDetallePartida.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblDetallePartida.setFontHead(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblDetallePartida.setFontRowHover(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblDetallePartida.setFontRowSelect(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblDetallePartida.setPositionText(rojerusan.RSTableMetro.POSITION_TEXT.LEFT);
        tblDetallePartida.setRowHeight(30);
        tblDetallePartida.setSelectionBackground(new java.awt.Color(153, 153, 153));
        tblDetallePartida.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblDetallePartida.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDetallePartida.setShowGrid(true);
        tblDetallePartida.setShowVerticalLines(false);
        tblDetallePartida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetallePartidaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDetallePartida);

        txtTotalHaber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTotalHaber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtTotalHaber.setText("$ 0.00");
        txtTotalHaber.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtTotalDebe.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTotalDebe.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtTotalDebe.setText("$ 0.00");
        txtTotalDebe.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1074, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtTotalDebe, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotalHaber, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalHaber, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalDebe, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btnCancelarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPartidaActionPerformed
        this.cerrar();
    }//GEN-LAST:event_btnCancelarPartidaActionPerformed

    public void cerrar() {
        this.dispose();
    }
    
    public void setearModelPartida() {
        if (this.sesion.configUsuario.getId_ciclo_contable() < 0) {
            JOptionPane.showConfirmDialog(this, "No se ha seleccionado un ciclo contable por defecto", "¡Alerta!", JOptionPane.WARNING_MESSAGE);
        
        } else if (this.listaPartidaDetalles.isEmpty()) {
            JOptionPane.showConfirmDialog(this, "No se han ingresado detalles a la partida", "¡Alerta!", JOptionPane.WARNING_MESSAGE);
            
        } else if (this.txtHora.getText().isEmpty()) {
            JOptionPane.showConfirmDialog(this, "No se han ingresado la hora", "¡Alerta!", JOptionPane.WARNING_MESSAGE);
            
        } else {
            this.partidaModel.setId_ciclo(this.sesion.configUsuario.getId_ciclo_contable());
            this.partidaModel.setId_tipo_partida(this.cmbTipoPartida.getSelectedIndex());
            this.partidaModel.setFecha(txtFecha.getDate());
            this.partidaModel.setComentario(txtComentario.getText());
            this.partidaModel.setHora(this.txtHora.getText());
            //this.partidaModel.setNum_partida(Integer.parseInt(txtNumPartida.getText()));
            //this.partidaModel.setFolio(Integer.parseInt(txtNumFolio.getText()));

            this.partidaModel.setListaPartidaDetalles(this.listaPartidaDetalles);
            this.partidaModel.setListaPartidaDetallesEliminados(this.listaPartidaDetallesEliminados);
        }
    }
    
    private void btnGuardarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPartidaActionPerformed
        // setear info al modelo
        this.setearModelPartida();
        // guardamos la info
        if (this.partidaModel.getId() < 0) {
            this.rg = _partida.insertar(this.partidaModel);
            if (rg.esExitosa()) {
                //JOptionPane.showMessageDialog(this, rs.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rs));
                this.realizoAccion = true;
                this.cerrar();
            } else {
                JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
            }

            // verificamos si es NUEVO
        } else {
            rg = _partida.editar(this.partidaModel);
            if (rg.esExitosa()) {
                //JOptionPane.showMessageDialog(this, rs.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rs));
                this.realizoAccion = true;
                this.cerrar();
            } else {
                JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
            }
        }
    }//GEN-LAST:event_btnGuardarPartidaActionPerformed

    private void tblDetallePartidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetallePartidaMouseClicked
        int row = tblDetallePartida.getSelectedRow();
        int column = tblDetallePartida.getSelectedColumn();
        if (column == 8) {
            // editar
            if (this.listaPartidaDetalles.get(row).getCuenta().getDisponible() == 0) {
                JOptionPane.showMessageDialog(this, "No puede realizar acciones a una cuenta padre", "¡ALERTA!", JOptionPane.WARNING_MESSAGE);
            } else {
                double monto = 0;
                if (this.listaPartidaDetalles.get(row).getDebe() == 0 && this.listaPartidaDetalles.get(row).getHaber()== 0) {
                    monto = this.listaPartidaDetalles.get(row).getParcial();
                } else if (this.listaPartidaDetalles.get(row).getParcial() == 0 && this.listaPartidaDetalles.get(row).getHaber()== 0) {
                    monto = this.listaPartidaDetalles.get(row).getDebe();
                } else if (this.listaPartidaDetalles.get(row).getParcial() == 0 && this.listaPartidaDetalles.get(row).getDebe()== 0) {
                    monto = this.listaPartidaDetalles.get(row).getHaber();
                }
                // buscamos la cuenta padre que tiene el FM
                int montoFM = 0;
                Cuenta cuentaAux = new Cuenta();
                if (this.listaPartidaDetalles.get(row).getCuentaMayor().getId() > 0) {
                    this.rowPadre = this.devolverRowPadre(this.listaPartidaDetalles.get(row).getCuentaMayor());
                    montoFM = this.listaPartidaDetalles.get(this.rowPadre).getCuenta().getFolio_mayor();
                    cuentaAux = this.listaPartidaDetalles.get(this.rowPadre).getCuenta();
                } else {
                    montoFM = this.listaPartidaDetalles.get(row).getCuenta().getFolio_mayor();
                    cuentaAux = this.listaPartidaDetalles.get(row).getCuenta();
                }
                
                dModificarMonto d = new dModificarMonto(null, true, sesion, monto, montoFM, cuentaAux);
                d.setAlwaysOnTop(true);
                d.setVisible(true);
                // validamos si realizo alguna accion para actualizar el listado o no
                if (d.isRealizoAccion()) {
                    // actializamos el monto
                    if (this.listaPartidaDetalles.get(row).getDebe() == 0 && this.listaPartidaDetalles.get(row).getHaber()== 0) {
                        this.listaPartidaDetalles.get(row).setParcial(d.getMontoIngresado());
                    } else if (this.listaPartidaDetalles.get(row).getParcial() == 0 && this.listaPartidaDetalles.get(row).getHaber()== 0) {
                        this.listaPartidaDetalles.get(row).setDebe(d.getMontoIngresado());
                    } else if (this.listaPartidaDetalles.get(row).getParcial() == 0 && this.listaPartidaDetalles.get(row).getDebe()== 0) {
                        this.listaPartidaDetalles.get(row).setHaber(d.getMontoIngresado());
                    }
                    // actualizamos el FM
                    Cuenta cuentaAuxFm = new Cuenta();
                    if (this.listaPartidaDetalles.get(row).getCuentaMayor().getId() > 0) {
                        this.listaPartidaDetalles.get(this.rowPadre).getCuenta().setId_ciclo_folio(d.getId_ciclo_contable_folio());
                        this.listaPartidaDetalles.get(this.rowPadre).getCuenta().setFolio_mayor(d.getMontoFM());
                        cuentaAuxFm = this.listaPartidaDetalles.get(this.rowPadre).getCuenta();
                    } else {
                        this.listaPartidaDetalles.get(row).getCuenta().setId_ciclo_folio(d.getId_ciclo_contable_folio());
                        this.listaPartidaDetalles.get(row).getCuenta().setFolio_mayor(d.getMontoFM());
                        cuentaAuxFm = this.listaPartidaDetalles.get(row).getCuenta();
                    }
                    // actualizamos el FM en caso de que se repita varias veces la cuenta a mayorizar
                    this.actualizarFMDetalles(d.getMontoFM(), cuentaAuxFm);
                    
                    // se procede a actualizar la tabla y los acumulados
                    this.actualizarMontosDeTabla();
                    this.setDatosPartidaDetalle();
                }
            }
            
        } else if (column == 9) {
            // eliminar
            if (this.listaPartidaDetalles.get(row).getCuenta().getDisponible() == 0) {
                JOptionPane.showMessageDialog(this, "No puede realizar acciones a una cuenta padre", "¡ALERTA!", JOptionPane.WARNING_MESSAGE);
            } else {
                String texto = "¿Esta seguro de continuar?, Se eliminará el registro:\n" + this.listaPartidaDetalles.get(row).getCuenta().getNombre();
                int opc = JOptionPane.showConfirmDialog(this, texto, "¡ALERTA!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (opc == 0) {
                    // si la cuenta mayor tiene id = -1 significa que es la cuenta de nivel a mayorizar 
                    // y unicamente la pasamos al listado de eliminar
                    if (this.listaPartidaDetalles.get(row).getCuentaMayor().getId() == -1) {
                        ArrayList<PartidaDetalle> listaEliminar = new ArrayList<>();
                        listaEliminar.add(this.listaPartidaDetalles.get(row));
                        this.listaPartidaDetalles = this.eliminarDetalledePartidaDetalle(listaEliminar);
                        //this.listaPartidaDetallesEliminados.add(this.listaPartidaDetalles.get(row));
                        
                    } else {
                        // buscamos todos los detalles que tienen ese padre junto a su padre y lo devolvemos en un listado
                        this.buscarPadreYDetalles(this.listaPartidaDetalles.get(row), this.listaPartidaDetalles.get(row));
                    }
                    
                    this.actualizarMontosDeTabla();
                    this.setDatosPartidaDetalle();
                }
            }
        }
    }//GEN-LAST:event_tblDetallePartidaMouseClicked

    public void actualizarFMDetalles(int fm, Cuenta cuenta) {
        this.listaPartidaDetalles.forEach((t) -> {
            if (cuenta.getId() == t.getCuenta().getId()) {
                t.getCuenta().setFolio_mayor(fm);
            }
        });
        
    }
    
    public int devolverRowPadre(Cuenta cuenta) {
        int rowPadre = -1;
        for (int i = 0; i < this.listaPartidaDetalles.size(); i++) {
            if (this.listaPartidaDetalles.get(i).getCuenta().getId() == cuenta.getId()) {
                rowPadre = i;
            }
        }
        return rowPadre;
    }
    
    public void buscarPadreYDetalles(PartidaDetalle cuentaPadre, PartidaDetalle cuenta) {
        ArrayList<PartidaDetalle> listaEliminar = new ArrayList<>();
        // buscamos las cuentas que tengan al mismo padre
        this.contador = 0;
        this.listaPartidaDetalles.forEach((t) -> {
            if (t.getCuentaMayor().getId() == cuentaPadre.getCuentaMayor().getId() && t.getTipo_cargo_abono() == cuentaPadre.getTipo_cargo_abono()) {
                this.contador++;
            }
        });
        
        if (this.contador <= 1) {
            // si solo tenemos 1 valor debemos eliminar el padre e hijo
            this.listaPartidaDetalles.forEach((t) -> {
                if (t.getCuenta().getId() == cuenta.getCuenta().getId() && t.getTipo_cargo_abono() == cuenta.getTipo_cargo_abono()) {
                    listaEliminar.add(t);
                }
            });
            // primero buscamos al padre
            this.listaPartidaDetalles.forEach((t) -> {
                if (t.getCuenta().getId() == cuentaPadre.getCuentaMayor().getId() && t.getTipo_cargo_abono() == cuentaPadre.getTipo_cargo_abono()) {
                    listaEliminar.add(t);
                }
            });
        } else {
            this.listaPartidaDetalles.forEach((t) -> {
                if (t.getCuenta().getId() == cuenta.getCuenta().getId() && t.getTipo_cargo_abono() == cuenta.getTipo_cargo_abono()) {
                    listaEliminar.add(t);
                }
            });
        }
        
        this.listaPartidaDetalles = this.eliminarDetalledePartidaDetalle(listaEliminar);
    }
    
    public ArrayList<PartidaDetalle> eliminarDetalledePartidaDetalle(ArrayList<PartidaDetalle> lista) {
        ArrayList<PartidaDetalle> listaRetornar = new ArrayList<>();
        this.listaPartidaDetalles.forEach((t) -> {
            this.banderaAgregar = true;
            lista.forEach((t1) -> {
                if (t.getCuenta().getId() == t1.getCuenta().getId() && t.getTipo_cargo_abono() == t1.getTipo_cargo_abono()) {
                    this.banderaAgregar = false;
                    if (t.getId() > 0) {
                        this.listaPartidaDetallesEliminados.add(t);
                    }
                }
            });
            if (this.banderaAgregar) {
                listaRetornar.add(t);
            }
        });
        return listaRetornar;
    }
    
    private void cmbTipoPartidaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoPartidaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoPartidaItemStateChanged

    private void btnAgregarDetallePartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDetallePartidaActionPerformed
        dSeleccionarCuenta d = new dSeleccionarCuenta(null, true, sesion);
        d.setVisible(true);
        // validamos si realizo alguna accion para actualizar el listado o no
        if (d.getRealizoAccion()) {
            //JOptionPane.showMessageDialog(this, d.getRG().getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(d.getRG()));
            this.actualizarMontosDetallesPartidas(d.getListaPartidaDetalle());
        }
    }//GEN-LAST:event_btnAgregarDetallePartidaActionPerformed

    private void txtHoraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoraKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraKeyTyped

    public void actualizarMontosDetallesPartidas(ArrayList<PartidaDetalle> lista) {
        // comparamos el listado que viene con el listado que tenemos para saber si ya se tiene al padre en la tabla y no duplicar
        ArrayList<PartidaDetalle> listaAux = new ArrayList<>();
        for (PartidaDetalle detalle : lista) {
            boolean seEncontroDetalle = false;
            for (PartidaDetalle pDetalle : this.listaPartidaDetalles) {
                if (detalle.getId_cuenta() == pDetalle.getId_cuenta() && detalle.getTipo_cargo_abono() == pDetalle.getTipo_cargo_abono()) {
                    seEncontroDetalle = true;
                    break;
                }
            }
            // sino se encuentra el detalle lo agregamos
            if (!seEncontroDetalle) {
                listaAux.add(detalle);
            }
        }
        // una vez los detalles nuevos, se procede a agregarlos al listado 
        for (PartidaDetalle partidaDetalle : listaAux) {
            this.listaPartidaDetalles.add(partidaDetalle);
        }
        
        // una vez agregado los detalles, se muestran en la tabla
        Collections.sort(listaPartidaDetalles, Comparator.comparingInt((PartidaDetalle d) -> d.tipo_cargo_abono)
                .thenComparing((PartidaDetalle d) -> d.cuenta.codigo));
        // una vez ordenado el listado actualizamos los montos de los detalles
        this.actualizarMontosDeTabla();
        this.setDatosPartidaDetalle();
    }
    
    public void actualizarMontosDeTabla() {
        // limpiamos los calculos de los no disponibles para volver a realizar el calculo
        this.listaPartidaDetalles.forEach((t) -> {
            if (t.getCuenta().getDisponible() == 0) {
                t.setDebe(0.0);
                t.setHaber(0.0);
            }
        });
        // validamos los escenarios en los cuales debe acumular el debe y haber
        this.listaPartidaDetalles.forEach((t) -> {
            if (t.getCuentaMayor().getId() > 0 && t.getCuenta().getDisponible() == 1) {
                int rowPadre = this.buscarPadre(t.getCuentaMayor(), t.getTipo_cargo_abono());
                if (rowPadre >= 0) {
                    PartidaDetalle cuentaPadre = this.listaPartidaDetalles.get(rowPadre);
                    double acumulado = 0;
                    if (t.getTipo_cargo_abono() == Constantes.TIPO_CARGO) {
                        acumulado = cuentaPadre.getDebe();
                        acumulado += t.getParcial();
                        this.listaPartidaDetalles.get(rowPadre).setDebe(acumulado);
                    } 
                    
                    if (t.getTipo_cargo_abono() == Constantes.TIPO_ABONO) {
                        acumulado = cuentaPadre.getHaber();
                        acumulado += t.getParcial();
                        this.listaPartidaDetalles.get(rowPadre).setHaber(acumulado);
                    }
                }
            }
        });
        
        // acumulamos los totales de DEBE y HABER para validar la partida
        this.totalDebe = 0;
        this.totalHaber = 0;
        this.listaPartidaDetalles.forEach((t) -> {
            if (t.getTipo_cargo_abono() == Constantes.TIPO_CARGO) {
                totalDebe += t.getDebe();
            } else if (t.getTipo_cargo_abono() == Constantes.TIPO_ABONO) {
                totalHaber += t.getHaber();
            }
        });
        
        this.txtTotalDebe.setText(String.valueOf(totalDebe));
        this.txtTotalHaber.setText(String.valueOf(totalHaber));
        if (this.totalDebe != this.totalHaber) {
            this.txtTotalDebe.setForeground(Color.red);
            this.txtTotalHaber.setForeground(Color.red);
            this.btnGuardarPartida.setEnabled(false);
        } else {
            this.txtTotalDebe.setForeground(Color.black);
            this.txtTotalHaber.setForeground(Color.black);
            this.btnGuardarPartida.setEnabled(true);
        }
    }
    
    public int buscarPadre(Cuenta cuenta, int tipoCargoAbono) {
        int row = -1;
        for (int i = 0; i < this.listaPartidaDetalles.size(); i++) {
            if (cuenta.getId() == this.listaPartidaDetalles.get(i).getCuenta().getId() && 
                    this.listaPartidaDetalles.get(i).getTipo_cargo_abono() == tipoCargoAbono) {
                row = i;
                break;
            }
        }
        return row;
    }
    
    public ArrayList<PartidaDetalle> ordernarDetallesDePartida() {
        ArrayList<PartidaDetalle> lista = new ArrayList();
        Collections.sort(lista, new Comparator<PartidaDetalle>() {
            @Override
            public int compare(PartidaDetalle p1, PartidaDetalle p2) {
                    return new Integer(p1.getId_partida()).compareTo(new Integer(p2.getId_partida()));
            }
        });
        return lista;
    }
    
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
    private RSMaterialComponent.RSButtonShapeIcon btnAgregarDetallePartida;
    private RSMaterialComponent.RSButtonShapeIcon btnCancelarPartida;
    private RSMaterialComponent.RSButtonShapeIcon btnGuardarPartida;
    private RSMaterialComponent.RSComboBoxMaterial cmbTipoPartida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private rojerusan.RSTableMetro tblDetallePartida;
    private javax.swing.JTextArea txtComentario;
    private newscomponents.RSDateChooser txtFecha;
    private RSMaterialComponent.RSTextFieldMaterial txtHora;
    private RSMaterialComponent.RSTextFieldMaterial txtNumPartida;
    private javax.swing.JLabel txtTotalDebe;
    private javax.swing.JLabel txtTotalHaber;
    // End of variables declaration//GEN-END:variables
}
