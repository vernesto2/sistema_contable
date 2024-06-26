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

        jPanel1 = new javax.swing.JPanel();
        btnElegirCuenta = new RSMaterialComponent.RSButtonShapeIcon();
        btnLimpiar = new RSMaterialComponent.RSButtonShapeIcon();
        txtCuentaSeleccionada = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnVerLibroMayor2 = new RSMaterialComponent.RSButtonShapeIcon();
        btnVerLibroMayor1 = new RSMaterialComponent.RSButtonShapeIcon();
        btnVerLibroMayor = new RSMaterialComponent.RSButtonShapeIcon();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(800, 600));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccione una cuenta para ver solo ese detalle   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

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

        txtCuentaSeleccionada.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtCuentaSeleccionada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCuentaSeleccionada.setText("* CUENTA NO ESPECIFICADA *");
        txtCuentaSeleccionada.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(239, 239, 239)
                .addComponent(btnElegirCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                .addGap(191, 191, 191))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCuentaSeleccionada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnElegirCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCuentaSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Libro Mayor por tipos de partidas   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("1.");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("2.");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("3.");

        btnVerLibroMayor2.setBackground(new java.awt.Color(251, 205, 6));
        btnVerLibroMayor2.setText("Ver hasta partidas de cierre");
        btnVerLibroMayor2.setBackgroundHover(new java.awt.Color(251, 174, 6));
        btnVerLibroMayor2.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor2.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor2.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor2.setForegroundText(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor2.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnVerLibroMayor2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVerLibroMayor2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BOOK);
        btnVerLibroMayor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerLibroMayor2ActionPerformed(evt);
            }
        });

        btnVerLibroMayor1.setBackground(new java.awt.Color(251, 205, 6));
        btnVerLibroMayor1.setText("Ver hasta partidas de ajuste");
        btnVerLibroMayor1.setBackgroundHover(new java.awt.Color(251, 174, 6));
        btnVerLibroMayor1.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor1.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor1.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor1.setForegroundText(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor1.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnVerLibroMayor1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVerLibroMayor1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BOOK);
        btnVerLibroMayor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerLibroMayor1ActionPerformed(evt);
            }
        });

        btnVerLibroMayor.setBackground(new java.awt.Color(251, 205, 6));
        btnVerLibroMayor.setText("Ver hasta partidas operativas");
        btnVerLibroMayor.setAlignmentY(0.0F);
        btnVerLibroMayor.setBackgroundHover(new java.awt.Color(251, 174, 6));
        btnVerLibroMayor.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor.setForegroundText(new java.awt.Color(0, 0, 0));
        btnVerLibroMayor.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnVerLibroMayor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVerLibroMayor.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BOOK);
        btnVerLibroMayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerLibroMayorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVerLibroMayor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVerLibroMayor2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVerLibroMayor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnVerLibroMayor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnVerLibroMayor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnVerLibroMayor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(214, 214, 214))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Reporteria de Libro Mayor");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(36, 36, 36)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        dSeleccionarCuentaFormula dialogoCuentas = new dSeleccionarCuentaFormula(null, true, sesion, this.sesion.configUsuario.getCicloContable().getTipoCatalogo(), false, this.sesion.configUsuario.getCicloContable().getTipoCatalogo().getNivel_mayorizar(), this.sesion.configUsuario.getCicloContable());
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
            Integer tamanoCodigoMayorizar = null;
            if(nivelAMayorizar == 0) {
                params.put("param_tamano_codigo", 0);
            } else {
                RespuestaGeneral rg = _cuenta.tamanoCodigoAMayorizar(tipoCatalogo, nivelAMayorizar);
                tamanoCodigoMayorizar = ( Integer) rg.getDatos();
                if ( tamanoCodigoMayorizar == null) {
                    JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
                } else {
                    params.put("param_tamano_codigo", tamanoCodigoMayorizar);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel txtCuentaSeleccionada;
    // End of variables declaration//GEN-END:variables
}
