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
import servicios.ServicioCuentaBalance;
import sesion.Sesion;
import utils.UtileriaVista;
import utils.constantes.Constantes;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author student
 */
public class vBalanzaComprobacion extends javax.swing.JPanel {

    /**
     * Creates new form vBalanzaComprobacion
     */
    Sesion sesion;
    ServicioCuenta _cuenta;
    ServicioCuentaBalance _cuentaBalance;
    public vBalanzaComprobacion(Sesion sesion) {
        initComponents();
        this.sesion = sesion;
        this._cuenta = new ServicioCuenta(sesion.rutaConexion);
        this._cuentaBalance = new ServicioCuentaBalance(sesion.rutaConexion);
        this._cuenta.setServicioCuentaBalanza(_cuentaBalance);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVerBalanzaComprobacion1LibroMayor = new RSMaterialComponent.RSButtonShapeIcon();
        btnVerBalanzaComprobacion1LibroMayor2 = new RSMaterialComponent.RSButtonShapeIcon();
        btnVerBalanzaComprobacion1LibroMayor3 = new RSMaterialComponent.RSButtonShapeIcon();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(900, 600));

        btnVerBalanzaComprobacion1LibroMayor.setBackground(new java.awt.Color(251, 205, 6));
        btnVerBalanzaComprobacion1LibroMayor.setText("Ver hasta partidas operativas");
        btnVerBalanzaComprobacion1LibroMayor.setBackgroundHover(new java.awt.Color(251, 174, 6));
        btnVerBalanzaComprobacion1LibroMayor.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnVerBalanzaComprobacion1LibroMayor.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnVerBalanzaComprobacion1LibroMayor.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnVerBalanzaComprobacion1LibroMayor.setForegroundText(new java.awt.Color(0, 0, 0));
        btnVerBalanzaComprobacion1LibroMayor.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BOOK);
        btnVerBalanzaComprobacion1LibroMayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerBalanzaComprobacion1LibroMayorActionPerformed(evt);
            }
        });

        btnVerBalanzaComprobacion1LibroMayor2.setBackground(new java.awt.Color(251, 205, 6));
        btnVerBalanzaComprobacion1LibroMayor2.setText("Ver hasta partidas de ajuste");
        btnVerBalanzaComprobacion1LibroMayor2.setBackgroundHover(new java.awt.Color(251, 174, 6));
        btnVerBalanzaComprobacion1LibroMayor2.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnVerBalanzaComprobacion1LibroMayor2.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnVerBalanzaComprobacion1LibroMayor2.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnVerBalanzaComprobacion1LibroMayor2.setForegroundText(new java.awt.Color(0, 0, 0));
        btnVerBalanzaComprobacion1LibroMayor2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BOOK);
        btnVerBalanzaComprobacion1LibroMayor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerBalanzaComprobacion1LibroMayor2ActionPerformed(evt);
            }
        });

        btnVerBalanzaComprobacion1LibroMayor3.setBackground(new java.awt.Color(251, 205, 6));
        btnVerBalanzaComprobacion1LibroMayor3.setText("Ver hasta partidas de cierre");
        btnVerBalanzaComprobacion1LibroMayor3.setBackgroundHover(new java.awt.Color(251, 174, 6));
        btnVerBalanzaComprobacion1LibroMayor3.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnVerBalanzaComprobacion1LibroMayor3.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnVerBalanzaComprobacion1LibroMayor3.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnVerBalanzaComprobacion1LibroMayor3.setForegroundText(new java.awt.Color(0, 0, 0));
        btnVerBalanzaComprobacion1LibroMayor3.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BOOK);
        btnVerBalanzaComprobacion1LibroMayor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerBalanzaComprobacion1LibroMayor3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnVerBalanzaComprobacion1LibroMayor, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVerBalanzaComprobacion1LibroMayor2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVerBalanzaComprobacion1LibroMayor3, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerBalanzaComprobacion1LibroMayor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerBalanzaComprobacion1LibroMayor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerBalanzaComprobacion1LibroMayor3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(514, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerBalanzaComprobacion1LibroMayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerBalanzaComprobacion1LibroMayorActionPerformed
        verBalanzaComprobacion(
                Integer.parseInt(Constantes.TIPO_PARTIDA_OPERATIVA.getValue())
        );
    }//GEN-LAST:event_btnVerBalanzaComprobacion1LibroMayorActionPerformed

    private void btnVerBalanzaComprobacion1LibroMayor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerBalanzaComprobacion1LibroMayor2ActionPerformed
        verBalanzaComprobacion(
            Integer.parseInt(Constantes.TIPO_PARTIDA_AJUSTE.getValue())
        );
    }//GEN-LAST:event_btnVerBalanzaComprobacion1LibroMayor2ActionPerformed

    private void btnVerBalanzaComprobacion1LibroMayor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerBalanzaComprobacion1LibroMayor3ActionPerformed
        verBalanzaComprobacion(
                Integer.parseInt(Constantes.TIPO_PARTIDA_CIERRE.getValue())
        );
    }//GEN-LAST:event_btnVerBalanzaComprobacion1LibroMayor3ActionPerformed
    public void verBalanzaComprobacion(int tipoPartida) {
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
            
            Integer idTipoCatalogo = sesion.configUsuario.getCicloContable().getTipoCatalogo().getId();
            params.put("param_id_tipo_catalogo", idTipoCatalogo);
            
            Integer nivelAMayorizar = sesion.configUsuario.getCicloContable().getTipoCatalogo().getNivel_mayorizar();
            //nivel 0 es para cuando se puede cargar y abonar cualquier cuenta, no ay restriccion
            //en este tipo además no hay parcial
            if(nivelAMayorizar == 0) {
                params.put("param_tamano_codigo", 0);
            } else {
                RespuestaGeneral rg = _cuenta.tamanoCodigoAMayorizar(idTipoCatalogo);
                Integer tamanoNivelAMayorizar = ( Integer) rg.getDatos();
                if ( tamanoNivelAMayorizar == null) {
                    JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
                } else {
                    params.put("param_tamano_codigo", 4);
                }
            }
            Integer idCiclo = sesion.configUsuario.getCicloContable().getId();
            List<CuentaBalanza> listBeans = (List<CuentaBalanza>) _cuenta
                    .listarCuentaBalanzaComprobacion(sesion.configUsuario.getCicloContable(), tipoPartida)
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShapeIcon btnVerBalanzaComprobacion1LibroMayor;
    private RSMaterialComponent.RSButtonShapeIcon btnVerBalanzaComprobacion1LibroMayor2;
    private RSMaterialComponent.RSButtonShapeIcon btnVerBalanzaComprobacion1LibroMayor3;
    // End of variables declaration//GEN-END:variables
}
