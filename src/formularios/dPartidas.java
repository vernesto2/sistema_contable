/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package formularios;


import dto.dtoLista;
import java.awt.Color;
import java.awt.Cursor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;
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
        txtComentario.setText(this.partidaModel.getComentario());
        txtFecha.setDate(this.partidaModel.getFecha());
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
        
        this.setDatosPartidaDetalle();
    }
    
    public void setModelPartida() {
        String[] cabecera = {"","Codigo","Concepto","Parcial","Debe","Haber","Editar","Eliminar"};
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
            datos[2] = detalle.getCuenta().getNombre();
            datos[3] = detalle.getParcial();
            datos[4] = detalle.getDebe();
            datos[5] = detalle.getHaber();
            datos[6] = detalle.getCuenta().getDisponible() == 1 ? btn1 : ' ';
            datos[7] = detalle.getCuenta().getDisponible() == 1 ? btn2 : ' ';
            dtm.addRow(datos);
        }
        tblDetallePartida.setModel(dtm);
        tblDetallePartida.setAutoResizeMode(tblDetallePartida.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tblDetallePartida.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblDetallePartida.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblDetallePartida.getColumnModel().getColumn(2).setPreferredWidth(400);
        tblDetallePartida.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblDetallePartida.getColumnModel().getColumn(4).setPreferredWidth(150);
        tblDetallePartida.getColumnModel().getColumn(5).setPreferredWidth(150);
        tblDetallePartida.getColumnModel().getColumn(6).setPreferredWidth(50);
        tblDetallePartida.getColumnModel().getColumn(7).setPreferredWidth(50);
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
        btnCancelarTipoCatalogo = new RSMaterialComponent.RSButtonShapeIcon();
        btnGuardarTipoCatalogo = new RSMaterialComponent.RSButtonShapeIcon();
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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetallePartida = new rojerusan.RSTableMetro();
        txtTotalHaber = new javax.swing.JLabel();
        txtTotalDebe = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setModal(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

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

        btnGuardarTipoCatalogo.setBackground(new java.awt.Color(33, 58, 86));
        btnGuardarTipoCatalogo.setText("GUARDAR");
        btnGuardarTipoCatalogo.setToolTipText("GUARDAR / ACTUALIZAR PARTIDA");
        btnGuardarTipoCatalogo.setBackgroundHover(new java.awt.Color(33, 68, 86));
        btnGuardarTipoCatalogo.setEnabled(false);
        btnGuardarTipoCatalogo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuardarTipoCatalogo.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnGuardarTipoCatalogo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btnGuardarTipoCatalogo.setSizeIcon(18.0F);
        btnGuardarTipoCatalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarTipoCatalogoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(801, Short.MAX_VALUE)
                .addComponent(btnCancelarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarTipoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        txtFecha.setFormatDate("dd-MM-yyyy");

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
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbTipoPartida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNumPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAgregarDetallePartida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTipoPartida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNumPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnAgregarDetallePartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtTotalDebe, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotalHaber, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnCancelarTipoCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarTipoCatalogoActionPerformed
        this.cerrar();
    }//GEN-LAST:event_btnCancelarTipoCatalogoActionPerformed

    public void cerrar() {
        this.dispose();
    }
    
    public void setearModelPartida() {
        this.partidaModel.setId_ciclo(this.sesion.configUsuario.getId_ciclo_contable());
        this.partidaModel.setId_tipo_partida(this.cmbTipoPartida.getSelectedIndex());
        this.partidaModel.setFecha(txtFecha.getDate());
        this.partidaModel.setComentario(txtComentario.getText());
    }
    
    private void btnGuardarTipoCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarTipoCatalogoActionPerformed
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
    }//GEN-LAST:event_btnGuardarTipoCatalogoActionPerformed

    private void tblDetallePartidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetallePartidaMouseClicked
        int row = tblDetallePartida.getSelectedRow();
        int column = tblDetallePartida.getSelectedColumn();
        if (column == 6) {
            // editar
            if (this.listaPartidaDetalles.get(row).getCuenta().getDisponible() == 0) {
                JOptionPane.showMessageDialog(this, "No puede realizar acciones a una cuenta padre", "¡ALERTA!", JOptionPane.WARNING_MESSAGE);
            } else {

            }
            
        } else if (column == 7) {
            // eliminar
            if (this.listaPartidaDetalles.get(row).getCuenta().getDisponible() == 0) {
                JOptionPane.showMessageDialog(this, "No puede realizar acciones a una cuenta padre", "¡ALERTA!", JOptionPane.WARNING_MESSAGE);
            } else {

            }
        }
        
        
    }//GEN-LAST:event_tblDetallePartidaMouseClicked

    private void cmbTipoPartidaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoPartidaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoPartidaItemStateChanged

    private void btnAgregarDetallePartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDetallePartidaActionPerformed
        dSeleccionarCuenta d = new dSeleccionarCuenta(null, true, sesion);
        d.setAlwaysOnTop(true);
        d.setVisible(true);
        // validamos si realizo alguna accion para actualizar el listado o no
        if (d.getRealizoAccion()) {
            //JOptionPane.showMessageDialog(this, d.getRG().getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(d.getRG()));
            this.actualizarMontosDetallesPartidas(d.getListaPartidaDetalle());
        }
    }//GEN-LAST:event_btnAgregarDetallePartidaActionPerformed

    public void actualizarMontosDetallesPartidas(ArrayList<PartidaDetalle> lista) {
        // comparamos el listado que viene con el listado que tenemos para saber si ya se tiene al padre en la tabla y no duplicar
        ArrayList<PartidaDetalle> listaAux = new ArrayList<>();
        for (PartidaDetalle detalle : lista) {
            boolean seEncontroDetalle = false;
            for (PartidaDetalle pDetalle : this.listaPartidaDetalles) {
                if (detalle.getId_cuenta() == pDetalle.getId_cuenta()) {
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
                int rowPadre = this.buscarPadre(t.getCuentaMayor());
                if (rowPadre >= 0) {
                    PartidaDetalle cuentaPadre = this.listaPartidaDetalles.get(rowPadre);
                    double acumulado = 0;
                    if (t.getTipo_cargo_abono() == Constantes.TIPO_CARGO) {
                        acumulado = cuentaPadre.getDebe();
                        acumulado += t.getParcial();
                        this.listaPartidaDetalles.get(rowPadre).setDebe(acumulado);
                    } else {
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
        } else {
            this.txtTotalDebe.setForeground(Color.black);
            this.txtTotalHaber.setForeground(Color.black);
        }
    }
    
    public int buscarPadre(Cuenta cuenta) {
        int row = -1;
        for (int i = 0; i < this.listaPartidaDetalles.size(); i++) {
            if (cuenta.getId() == this.listaPartidaDetalles.get(i).getCuenta().getId()) {
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
    private RSMaterialComponent.RSButtonShapeIcon btnCancelarTipoCatalogo;
    private RSMaterialComponent.RSButtonShapeIcon btnGuardarTipoCatalogo;
    private RSMaterialComponent.RSComboBoxMaterial cmbTipoPartida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
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
    private RSMaterialComponent.RSTextFieldMaterial txtNumPartida;
    private javax.swing.JLabel txtTotalDebe;
    private javax.swing.JLabel txtTotalHaber;
    // End of variables declaration//GEN-END:variables
}
