/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package formularios;

import dto.dtoFormula;
import dto.dtoLista;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import modelo.CicloContable;
import modelo.TipoCatalogo;
import servicios.ServicioFormula;
import sesion.Sesion;
import utils.UtileriaVista;
import utils.constantes.Constantes;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class dFormFormula extends javax.swing.JDialog {

    /**
     * Creates new form dFormFormula
     */
    
    dtoFormula formulaDtoModel = new dtoFormula();
    dtoFormula formulaPadre = new dtoFormula();
    boolean realizoAccion = false;
    Sesion sesion;
    ArrayList<dtoLista> listaSignos = new ArrayList<>();
    ArrayList<dtoLista> listaTipoEstadoResultado = new ArrayList<>();
    ArrayList<dtoLista> listaTipoCamposEspeciales = new ArrayList<>();
    ServicioFormula _formula;
    TipoCatalogo tipoCatalogo = new TipoCatalogo();
    
    RespuestaGeneral rg = new RespuestaGeneral();
    
    public dFormFormula(java.awt.Frame parent, boolean modal, Sesion sesion, dtoFormula formula, TipoCatalogo tipoCatalogo, dtoFormula formulaPadre) {
        super(parent, modal);
        initComponents();
        this.sesion = sesion;
        this._formula = new ServicioFormula(sesion.rutaConexion);
        this.tipoCatalogo = tipoCatalogo;
        this.formulaPadre = formulaPadre;
        this.formulaDtoModel = formula;
        if (this.formulaPadre.getFormula().getId() > 0) {
            this.formulaDtoModel.getFormula().setId_formula(this.formulaPadre.getFormula().getId());
        }
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle(this.formulaDtoModel.getFormula().getId() > 0 ? "MODIFICAR DETALLE DE FORMULA" : "NUEVO DETALLE DE FORMULA");
        this.setearCombos();
        this.setearData();
    }
    
    public void setearCombos() {
        this.listaSignos = Constantes.listaSignos();
        cmbSigno.removeAllItems();
        for (dtoLista item : this.listaSignos) {
            cmbSigno.addItem(item.getLabel());
        }
        
        this.listaTipoCamposEspeciales = Constantes.listaTiposCampoEspecialEstadoResultado();
        cmbCuentaEspecial.removeAllItems();
        for (dtoLista item : this.listaTipoCamposEspeciales) {
            cmbCuentaEspecial.addItem(item.getLabel());
        }
        
        this.listaTipoEstadoResultado = Constantes.listaTiposFormulaEstadoResultado();
        cmbTipoFormula.removeAllItems();
        for (dtoLista item : this.listaTipoEstadoResultado) {
            cmbTipoFormula.addItem(item.getLabel());
        }
    
    }
    
    public void limpiarCuentaSeleccionada() {
        this.formulaDtoModel.getFormula().setId_cuenta(-1);
        this.formulaDtoModel.getFormula().setNombre("");
        this.txtCuentaSeleccionada.setText("* CUENTA NO ESPECIFICADA *");
        this.txtNombre.setText("");
    }
    
    public void setearData() {
        // seteamos el encabezado de la formula o sub formula
        this.lblFormula.setText("DETALLE DE FORMULA INDEPENDIENTE");
        if (this.formulaDtoModel.getFormula().getId_formula() > 0) {
            this.lblFormula.setText("DEPENDE DE: " + this.formulaDtoModel.getFormulaPadre().getNombre().toUpperCase());
        }
        if (this.formulaPadre.getFormula().getId()> 0) {
            this.lblFormula.setText("DEPENDE DE: " + this.formulaPadre.getFormula().getNombre().toUpperCase());
        }
        
        // pasos para editar formula
        if (this.formulaPadre.getFormula().getId() < 0) {
            if (this.formulaDtoModel.getFormula().getId() < 0 && this.formulaDtoModel.getFormula().getId_formula() < 0) {
                this.lblPosicion.setText("");
                this.txtPosicion.setText("");
            } else if (this.formulaDtoModel.getFormula().getId() > 0 && this.formulaDtoModel.getFormula().getId_formula() < 0) {
                this.lblPosicion.setText("");
                // usar solo valores enteros xq no tendra hijos
                //int posicion = (int)this.formulaDtoModel.getFormula().getPosicion();
                this.txtPosicion.setText(this.formulaDtoModel.getFormula().getPosicion());
            } else if (this.formulaDtoModel.getFormula().getId() < 0 && this.formulaDtoModel.getFormula().getId_formula() > 0) {
                // tenemos q partir la posicion del padre dependiendo de que sea .0 
                // se debe quitar el 0 de lo contrario debe mostrar todo
                String valorPosPadre = String.valueOf(this.formulaDtoModel.getFormulaPadre().getPosicion()) + ".";
                String valorPosHijo = String.valueOf(this.formulaDtoModel.getFormula().getPosicion());
                // si tenemos el valor en texto podemos validar que tiene despues del .
                try {
                    String[] valorDecimal = valorPosHijo.split(Pattern.quote(valorPosPadre));
                    String v1 = valorDecimal[0] != null ? valorDecimal[0] : "";
                    String v2 = valorDecimal[1] != null ? valorDecimal[1] : "";
                    
                    this.lblPosicion.setText(valorPosPadre);
                    this.txtPosicion.setText(v2);
                } catch (Exception e) {
                }
            } else {
                // tenemos q partir la posicion del padre dependiendo de que sea .0 
                // se debe quitar el 0 de lo contrario debe mostrar todo
                String valorPosPadre = String.valueOf(this.formulaDtoModel.getFormulaPadre().getPosicion()) + ".";
                String valorPosHijo = String.valueOf(this.formulaDtoModel.getFormula().getPosicion());
                // si tenemos el valor en texto podemos validar que tiene despues del .
                try {
                    String[] valorDecimal = valorPosHijo.split(Pattern.quote(valorPosPadre));
                    String v1 = valorDecimal[0] != null ? valorDecimal[0] : "";
                    String v2 = valorDecimal[1] != null ? valorDecimal[1] : "";
                    
                    this.lblPosicion.setText(valorPosPadre);
                    this.txtPosicion.setText(v2);
                } catch (Exception e) {
                }
                
            }
            
            // pasos para nueva sub-formula
        } else {
            // 
            String valorPos = String.valueOf(this.formulaPadre.getFormula().getPosicion()) + ".";
            this.lblPosicion.setText(valorPos);
            this.txtPosicion.setText("");
        }
        // varificamos si es nuevo detalle de formula y si no depende de otra
        
        
        //txtPosicion.setText(String.valueOf(this.formulaDtoModel.getPosicion()));
        txtNombre.setText(String.valueOf(this.formulaDtoModel.getFormula().getNombre()));
        if (this.formulaDtoModel.getFormula().getId_cuenta() < 0) {
            this.txtCuentaSeleccionada.setText("* CUENTA NO ESPECIFICADA *");
        } else {
            txtCuentaSeleccionada.setText(String.valueOf(this.formulaDtoModel.getFormula().getCuenta().getCodigo()+ " - " + this.formulaDtoModel.getFormula().getCuenta().getNombre()));
        }
        
        // PROCESO PARA SELECCION DE COMBOBOX (SIGNOS)
        int iCmb = 0, i = 0;
        for (dtoLista item : listaSignos) {
            if (item.getValue().equals(this.formulaDtoModel.getFormula().getSigno())) {
                iCmb = i;
            }
            i++;
        }
        cmbSigno.setSelectedIndex(iCmb);
        
        // PROCESO PARA SELECCION DE COMBOBOX (CUENTA ESPECIAL)
        int iCmb1 = 0, i1 = 0;
        for (dtoLista item : listaTipoCamposEspeciales) {
            if (Integer.parseInt(item.getValue()) == this.formulaDtoModel.getFormula().getTipo_cuenta_especial()) {
                iCmb1 = i1;
            }
            i1++;
        }
        cmbCuentaEspecial.setSelectedIndex(iCmb1);
        
        // PROCESO PARA SELECCION DE COMBOBOX (TIPO ESTADO)
        int iCmb2 = 0, i2 = 0;
        for (dtoLista item : listaTipoEstadoResultado) {
            if (item.getValue().equals(this.formulaDtoModel.getFormula().getTipo_formula())) {
                iCmb2 = i2;
            }
            i2++;
        }
        cmbTipoFormula.setSelectedIndex(iCmb2);
    }

    public boolean isRealizoAccion() {
        return realizoAccion;
    }

    public void setRealizoAccion(boolean realizoAccion) {
        this.realizoAccion = realizoAccion;
    }

    public RespuestaGeneral getRg() {
        return rg;
    }

    public void setRg(RespuestaGeneral rg) {
        this.rg = rg;
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
        jLabel7 = new javax.swing.JLabel();
        txtNombre = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtPosicion = new RSMaterialComponent.RSTextFieldMaterial();
        lblPosicion = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cmbCuentaEspecial = new RSMaterialComponent.RSComboBoxMaterial();
        btnCancelarTipoCatalogo1 = new RSMaterialComponent.RSButtonShapeIcon();
        txtCuentaSeleccionada = new javax.swing.JLabel();
        cmbSigno = new RSMaterialComponent.RSComboBoxMaterial();
        lblFormula = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        cmbTipoFormula = new RSMaterialComponent.RSComboBoxMaterial();
        btnElegirCuenta = new RSMaterialComponent.RSButtonShapeIcon();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
        btnGuardarTipoCatalogo.setToolTipText("GUARDAR / ACTUALIZAR LA CUENTA");
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(398, Short.MAX_VALUE)
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

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Signo:");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtNombre.setPhColor(new java.awt.Color(0, 0, 0));
        txtNombre.setPlaceholder("Digite el concepto de la cuenta");
        txtNombre.setSelectionColor(new java.awt.Color(0, 0, 0));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Nombre:");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Cuenta:");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Posición:");
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtPosicion.setForeground(new java.awt.Color(0, 0, 0));
        txtPosicion.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtPosicion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtPosicion.setPhColor(new java.awt.Color(0, 0, 0));
        txtPosicion.setPlaceholder("Digite la posición");
        txtPosicion.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtPosicion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPosicionKeyTyped(evt);
            }
        });

        lblPosicion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPosicion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPosicion.setText("1.1");
        lblPosicion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Cuenta Especial:");
        jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        cmbCuentaEspecial.setColorMaterial(new java.awt.Color(102, 102, 102));
        cmbCuentaEspecial.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCuentaEspecialItemStateChanged(evt);
            }
        });

        btnCancelarTipoCatalogo1.setBackground(new java.awt.Color(51, 102, 255));
        btnCancelarTipoCatalogo1.setText("LIMPIAR");
        btnCancelarTipoCatalogo1.setToolTipText("QUITAR LA CUENTA SELECCIONADA");
        btnCancelarTipoCatalogo1.setBackgroundHover(new java.awt.Color(51, 153, 255));
        btnCancelarTipoCatalogo1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCancelarTipoCatalogo1.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnCancelarTipoCatalogo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCancelarTipoCatalogo1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        btnCancelarTipoCatalogo1.setSizeIcon(18.0F);
        btnCancelarTipoCatalogo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarTipoCatalogo1ActionPerformed(evt);
            }
        });

        txtCuentaSeleccionada.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtCuentaSeleccionada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtCuentaSeleccionada.setText("* CUENTA NO ESPECIFICADA *");
        txtCuentaSeleccionada.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        cmbSigno.setColorMaterial(new java.awt.Color(102, 102, 102));
        cmbSigno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSignoItemStateChanged(evt);
            }
        });

        lblFormula.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFormula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFormula.setText("Formula Hija");
        lblFormula.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Tipo Formula:");
        jLabel23.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        cmbTipoFormula.setColorMaterial(new java.awt.Color(102, 102, 102));
        cmbTipoFormula.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoFormulaItemStateChanged(evt);
            }
        });

        btnElegirCuenta.setBackground(new java.awt.Color(0, 153, 0));
        btnElegirCuenta.setText("ELEGIR CUENTA");
        btnElegirCuenta.setToolTipText("SELECCIONAR CUENTA");
        btnElegirCuenta.setBackgroundHover(new java.awt.Color(0, 178, 0));
        btnElegirCuenta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnElegirCuenta.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnElegirCuenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnElegirCuenta.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        btnElegirCuenta.setSizeIcon(18.0F);
        btnElegirCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElegirCuentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFormula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(155, 155, 155)
                                .addComponent(lblPosicion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPosicion, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cmbSigno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                                            .addComponent(txtCuentaSeleccionada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbCuentaEspecial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbTipoFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(btnElegirCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelarTipoCatalogo1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPosicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPosicion, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarTipoCatalogo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnElegirCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCuentaSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSigno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCuentaEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoFormula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
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
        this.dispose();
    }//GEN-LAST:event_btnCancelarTipoCatalogoActionPerformed

    private void btnGuardarTipoCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarTipoCatalogoActionPerformed
        // setear info al modelo
        boolean valido = this.setearModelo();
        
        if (valido) {
            // guardamos la info
            if (this.formulaDtoModel.getFormula().getId() < 0) {
                RespuestaGeneral rg = _formula.insertar(this.formulaDtoModel.getFormula());
                if (rg.esExitosa()) {
                    this.setRealizoAccion(true);
                    this.setRg(rg);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
                }

                // verificamos si es NUEVO
            } else {
                RespuestaGeneral rg = _formula.editar(this.formulaDtoModel.getFormula());
                if (rg.esExitosa()) {
                    this.setRealizoAccion(true);
                    this.setRg(rg);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
                }
            }
        }
        
    }//GEN-LAST:event_btnGuardarTipoCatalogoActionPerformed

    public boolean setearModelo() {
        boolean valido = true;
        
        try {
            this.formulaDtoModel.getFormula().setId_tipo_catalogo(this.tipoCatalogo.getId());
            String signo = this.listaSignos.get(this.cmbSigno.getSelectedIndex()).getValue();
            this.formulaDtoModel.getFormula().setSigno(signo);
            String tipoEstadoResultado = this.listaTipoEstadoResultado.get(this.cmbTipoFormula.getSelectedIndex()).getValue();
            this.formulaDtoModel.getFormula().setTipo_formula(tipoEstadoResultado);
            this.formulaDtoModel.getFormula().setTipo_cuenta_especial(this.cmbCuentaEspecial.getSelectedIndex());
            this.formulaDtoModel.getFormula().setNombre(this.txtNombre.getText());
            
            if (this.txtPosicion.getText().isEmpty()) {
                valido = false;
                JOptionPane.showMessageDialog(this, "No se ha ingresado una posición", "¡Alerta!", JOptionPane.WARNING_MESSAGE);
            } else {
                String posicionFinal = this.lblPosicion.getText() + this.txtPosicion.getText();
                this.formulaDtoModel.getFormula().setPosicion(posicionFinal);
            }
            
        } catch (Exception e) {
            valido = false;
            JOptionPane.showMessageDialog(this, "Por favor verifique que todos los campos han sido completados", "¡Alerta!", JOptionPane.WARNING_MESSAGE);
        }
        
        return valido;
        //this.formulaDtoModel.getFormula().setPosicion();
    }
    
    private void cmbCuentaEspecialItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCuentaEspecialItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCuentaEspecialItemStateChanged

    private void btnCancelarTipoCatalogo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarTipoCatalogo1ActionPerformed
        this.limpiarCuentaSeleccionada();
    }//GEN-LAST:event_btnCancelarTipoCatalogo1ActionPerformed

    private void cmbSignoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSignoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSignoItemStateChanged

    private void cmbTipoFormulaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoFormulaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoFormulaItemStateChanged

    private void btnElegirCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElegirCuentaActionPerformed
        dSeleccionarCuentaFormula d = new dSeleccionarCuentaFormula(null, true, sesion, this.tipoCatalogo, false, 0, new CicloContable());
        d.setVisible(true);
        // validamos si realizo alguna accion para actualizar el listado o no
        if (d.getRealizoAccion()) {
            //JOptionPane.showMessageDialog(this, d.getRg().getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(d.getRg()));
            this.formulaDtoModel.getFormula().setId_cuenta(d.getCuentaSeleccionada().getId());
            this.formulaDtoModel.getFormula().setNombre(d.getCuentaSeleccionada().getNombre());
            this.txtNombre.setText(d.getCuentaSeleccionada().getNombre());
            this.txtCuentaSeleccionada.setText(d.getCuentaSeleccionada().getCodigo() + " - " + d.getCuentaSeleccionada().getNombre());
        }
    }//GEN-LAST:event_btnElegirCuentaActionPerformed

    private void txtPosicionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPosicionKeyTyped
        if (!Constantes.validarNumeros(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPosicionKeyTyped

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
//            java.util.logging.Logger.getLogger(dFormFormula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(dFormFormula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(dFormFormula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(dFormFormula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                dFormFormula dialog = new dFormFormula(new javax.swing.JFrame(), true);
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
    private RSMaterialComponent.RSButtonShapeIcon btnCancelarTipoCatalogo;
    private RSMaterialComponent.RSButtonShapeIcon btnCancelarTipoCatalogo1;
    private RSMaterialComponent.RSButtonShapeIcon btnElegirCuenta;
    private RSMaterialComponent.RSButtonShapeIcon btnGuardarTipoCatalogo;
    private RSMaterialComponent.RSComboBoxMaterial cmbCuentaEspecial;
    private RSMaterialComponent.RSComboBoxMaterial cmbSigno;
    private RSMaterialComponent.RSComboBoxMaterial cmbTipoFormula;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblFormula;
    private javax.swing.JLabel lblPosicion;
    private javax.swing.JLabel txtCuentaSeleccionada;
    private RSMaterialComponent.RSTextFieldMaterial txtNombre;
    private RSMaterialComponent.RSTextFieldMaterial txtPosicion;
    // End of variables declaration//GEN-END:variables
}
