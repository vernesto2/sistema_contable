/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import conexion.Conexion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import reportes.CuentaBalanza;
import servicios.ServicioCuenta;
import servicios.ServicioTipoCatalogo;
import sesion.Sesion;
import utils.UtileriaVista;
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
        btnBalanza = new RSMaterialComponent.RSButtonShapeIcon();
        btnVerLibroMayor1 = new RSMaterialComponent.RSButtonShapeIcon();
        btnVerLibroMayor2 = new RSMaterialComponent.RSButtonShapeIcon();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(800, 600));

        btnVerLibroMayor.setBackground(new java.awt.Color(251, 205, 6));
        btnVerLibroMayor.setText("Ver libro mayor partidas operativas");
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

        btnBalanza.setBackground(new java.awt.Color(251, 205, 6));
        btnBalanza.setText("Ver balanza de comprobacion");
        btnBalanza.setBackgroundHover(new java.awt.Color(251, 174, 6));
        btnBalanza.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnBalanza.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnBalanza.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnBalanza.setForegroundText(new java.awt.Color(0, 0, 0));
        btnBalanza.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BOOK);
        btnBalanza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBalanzaActionPerformed(evt);
            }
        });

        btnVerLibroMayor1.setBackground(new java.awt.Color(251, 205, 6));
        btnVerLibroMayor1.setText("Ver libro mayor partidas de ajuste");
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
        btnVerLibroMayor2.setText("Ver libro mayor partidas de cierre");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnVerLibroMayor, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                    .addComponent(btnVerLibroMayor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBalanza, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerLibroMayor2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(657, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVerLibroMayor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVerLibroMayor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(btnVerLibroMayor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBalanza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(382, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerLibroMayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerLibroMayorActionPerformed
        verLibroMayor();
    }//GEN-LAST:event_btnVerLibroMayorActionPerformed

    private void btnBalanzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBalanzaActionPerformed
        verBalanzaComprobacion();
    }//GEN-LAST:event_btnBalanzaActionPerformed

    private void btnVerLibroMayor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerLibroMayor1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerLibroMayor1ActionPerformed

    private void btnVerLibroMayor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerLibroMayor2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerLibroMayor2ActionPerformed
    
    public void verBalanzaComprobacion() {
        try (
                InputStream inputStream = getClass().getResourceAsStream("/reportes/reporte-balanza-comprobacion.jrxml");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))
                ){
            Map<String, Object> params = new HashMap<String, Object>();
            
            Connection con;
            JasperReport reporte;
            Conexion cx = new Conexion(sesion.rutaConexion);
            con = cx.conectar();
            reporte = JasperCompileManager.compileReport(inputStream);

            //Currency currentyActual = Currency.getInstance(Locale.US);
            Locale locale = new Locale("es", "SV");

            params.put("param_titulo_ciclo_contable", sesion.configUsuario.getNombre_ciclo_contable());
            params.put("param_nombre_completo", sesion.usuario.getPersona().nombreCompleto());
            params.put("param_usuario", sesion.usuario.getNombre());
            
            params.put("param_desde", sesion.configUsuario.getCicloContable().getDesde());
            params.put("param_hasta", sesion.configUsuario.getCicloContable().getHasta());
            params.put("param_fecha_generacion", new Date());
            
            Integer tipoCatalogo = sesion.configUsuario.getCicloContable().getTipoCatalogo().getId();
            params.put("param_id_tipo_catalogo", tipoCatalogo);
            
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
            Integer idCiclo = sesion.configUsuario.getCicloContable().getId();
            List<CuentaBalanza> listBeans = (List<CuentaBalanza>) _cuenta
                    .listarCuentaBalanzaComprobacion(tipoCatalogo, idCiclo)
                    .getDatos();
            
            JRBeanCollectionDataSource param_cuentas_balanza = new JRBeanCollectionDataSource(listBeans);
            params.put("param_cuentas_balanza", param_cuentas_balanza);
//            params.put("params_cuentas_balanza", listBeans);

            

            params.put(JRParameter.REPORT_LOCALE, locale);
            
            JasperPrint jp = JasperFillManager.fillReport(reporte, params, new JREmptyDataSource() );
            final boolean EXIT_ON_CLOSE = false;
            JasperViewer.viewReport(jp, EXIT_ON_CLOSE);
        } catch (IOException | JRException ex) {
            Logger.getLogger(vLibroDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void verLibroMayor() {
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
    private RSMaterialComponent.RSButtonShapeIcon btnBalanza;
    private RSMaterialComponent.RSButtonShapeIcon btnVerLibroMayor;
    private RSMaterialComponent.RSButtonShapeIcon btnVerLibroMayor1;
    private RSMaterialComponent.RSButtonShapeIcon btnVerLibroMayor2;
    // End of variables declaration//GEN-END:variables
}
