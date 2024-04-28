/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import conexion.Conexion;
import formularios.dSeleccionarCuentaFormula;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Cuenta;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import servicios.ServicioCuenta;
import sesion.Sesion;
import utils.UtileriaVista;
import utils.constantes.Constantes;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author vacev
 */
public class vLibroMayor extends javax.swing.JPanel {

    /**
     * Creates new form vLibroMayor
     */
    Sesion sesion;
    ServicioCuenta _cuenta;
    Cuenta cuentaSeleccionada;
    public vLibroMayor(Sesion sesion) {
        initComponents();
        this.sesion = sesion;
        this._cuenta = new ServicioCuenta(sesion.rutaConexion);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVerLibroMayor = new RSMaterialComponent.RSButtonShapeIcon();
        btnVerLibroMayor1 = new RSMaterialComponent.RSButtonShapeIcon();
        btnVerLibroMayor2 = new RSMaterialComponent.RSButtonShapeIcon();
        btnElegirCuenta = new RSMaterialComponent.RSButtonShapeIcon();
        btnLimpiar = new RSMaterialComponent.RSButtonShapeIcon();
        jLabel17 = new javax.swing.JLabel();
        txtCuentaSeleccionada = new javax.swing.JLabel();
        rSComboBoxMaterial1 = new RSMaterialComponent.RSComboBoxMaterial();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(800, 600));

        btnVerLibroMayor.setBackground(new java.awt.Color(251, 205, 6));
        btnVerLibroMayor.setText("Ver hasta partidas operativas");
        btnVerLibroMayor.setAlignmentY(0.0F);
        btnVerLibroMayor.setBackgroundHover(new java.awt.Color(251, 174, 6));
        btnVerLibroMayor.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor.setForegroundText(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BOOK);
        btnVerLibroMayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerLibroMayorActionPerformed(evt);
            }
        });

        btnVerLibroMayor1.setBackground(new java.awt.Color(251, 205, 6));
        btnVerLibroMayor1.setText("Ver hasta partidas de ajuste");
        btnVerLibroMayor1.setBackgroundHover(new java.awt.Color(251, 174, 6));
        btnVerLibroMayor1.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor1.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor1.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor1.setForegroundText(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BOOK);
        btnVerLibroMayor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerLibroMayor1ActionPerformed(evt);
            }
        });

        btnVerLibroMayor2.setBackground(new java.awt.Color(251, 205, 6));
        btnVerLibroMayor2.setText("Ver hasta partidas de cierre");
        btnVerLibroMayor2.setBackgroundHover(new java.awt.Color(251, 174, 6));
        btnVerLibroMayor2.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor2.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor2.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor2.setForegroundText(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BOOK);
        btnVerLibroMayor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerLibroMayor2ActionPerformed(evt);
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

        btnLimpiar.setBackground(new java.awt.Color(51, 102, 255));
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setToolTipText("QUITAR LA CUENTA SELECCIONADA");
        btnLimpiar.setBackgroundHover(new java.awt.Color(51, 153, 255));
        btnLimpiar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLimpiar.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnLimpiar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnLimpiar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        btnLimpiar.setSizeIcon(18.0F);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Cuenta:");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtCuentaSeleccionada.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtCuentaSeleccionada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtCuentaSeleccionada.setText("* CUENTA NO ESPECIFICADA *");
        txtCuentaSeleccionada.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        rSComboBoxMaterial1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mes", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCuentaSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSComboBoxMaterial1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnElegirCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnVerLibroMayor, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVerLibroMayor1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVerLibroMayor2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnElegirCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCuentaSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rSComboBoxMaterial1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerLibroMayor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerLibroMayor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerLibroMayor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(447, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerLibroMayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerLibroMayorActionPerformed
        verLibroMayor(
            Integer.parseInt(Constantes.TIPO_PARTIDA_OPERATIVA.getValue())
        );
    }//GEN-LAST:event_btnVerLibroMayorActionPerformed

    private void btnVerLibroMayor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerLibroMayor1ActionPerformed
        verLibroMayor(
            Integer.parseInt(Constantes.TIPO_PARTIDA_AJUSTE.getValue())
        );
    }//GEN-LAST:event_btnVerLibroMayor1ActionPerformed

    private void btnVerLibroMayor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerLibroMayor2ActionPerformed
        verLibroMayor(
            Integer.parseInt(Constantes.TIPO_PARTIDA_CIERRE.getValue())
        );
    }//GEN-LAST:event_btnVerLibroMayor2ActionPerformed

    private void btnElegirCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElegirCuentaActionPerformed
        elegirCuenta();
    }//GEN-LAST:event_btnElegirCuentaActionPerformed
    private void elegirCuenta() {
        dSeleccionarCuentaFormula dialogoCuentas = new dSeleccionarCuentaFormula(null, true, sesion, this.sesion.configUsuario.getCicloContable().getTipoCatalogo());
        dialogoCuentas.setVisible(true);
        // validamos si realizo alguna accion para actualizar el listado o no
        if (dialogoCuentas.getRealizoAccion()) {
            cuentaSeleccionada = dialogoCuentas.getCuentaSeleccionada();
            //JOptionPane.showMessageDialog(this, dialogoCuentas.getRg().getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(dialogoCuentas.getRg()));
            this.txtCuentaSeleccionada.setText(cuentaSeleccionada.getCodigo() + " - " + cuentaSeleccionada.getNombre());
        }
    }
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        this.limpiarCuentaSeleccionada();
    }//GEN-LAST:event_btnLimpiarActionPerformed
    
    private void limpiarCuentaSeleccionada() {
        cuentaSeleccionada = null;
        this.txtCuentaSeleccionada.setText("* CUENTA NO ESPECIFICADA *");
    }
    public void verLibroMayor(int tipoPartida) {
         try (
                InputStream inputStream = getClass().getResourceAsStream("/reportes/reporte-libro-mayor.jrxml");
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
            
            Integer tipoCatalogo = sesion.configUsuario.getCicloContable().getTipoCatalogo().getId();
            params.put("param_id_tipo_catalogo", tipoCatalogo);
            

            params.put("param_tipo_partida", tipoPartida);
            params.put("param_id_cuenta_mayorizar", null);
            if(cuentaSeleccionada != null) {
                params.put("param_id_cuenta_mayorizar", cuentaSeleccionada.getId());
            }
            
            Integer nivelAMayorizar = sesion.configUsuario.getCicloContable().getTipoCatalogo().getNivel_mayorizar();
            //nivel 0 es para cuando se puede cargar y abonar cualquier cuenta, no ay restriccion
            //en este tipo además no hay parcial
            if(nivelAMayorizar == 0) {
                params.put("param_tamano_codigo", 0);
            } else {
                RespuestaGeneral rg = _cuenta.tamanoCodigoAMayorizar(tipoCatalogo);
                Integer tamanoNivelAMayorizar = ( Integer) rg.getDatos();
                if ( tamanoNivelAMayorizar == null) {
                    JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
                } else {
                    params.put("param_tamano_codigo", 4);
                }
            }
            
            params.put(JRParameter.REPORT_LOCALE, locale);
            JasperPrint jp = JasperFillManager.fillReport(reporte, params, con);
            final boolean EXIT_ON_CLOSE = false;
            JasperViewer.viewReport(jp, EXIT_ON_CLOSE);
        } catch (IOException | JRException ex) {
            Logger.getLogger(vLibroDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShapeIcon btnElegirCuenta;
    private RSMaterialComponent.RSButtonShapeIcon btnLimpiar;
    private RSMaterialComponent.RSButtonShapeIcon btnVerLibroMayor;
    private RSMaterialComponent.RSButtonShapeIcon btnVerLibroMayor1;
    private RSMaterialComponent.RSButtonShapeIcon btnVerLibroMayor2;
    private javax.swing.JLabel jLabel17;
    private RSMaterialComponent.RSComboBoxMaterial rSComboBoxMaterial1;
    private javax.swing.JLabel txtCuentaSeleccionada;
    // End of variables declaration//GEN-END:variables
}
