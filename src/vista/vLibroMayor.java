/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import conexion.Conexion;
import java.sql.Connection;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
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

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(800, 600));

        btnVerLibroMayor.setBackground(new java.awt.Color(251, 205, 6));
        btnVerLibroMayor.setText("Ver libro mayor");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(btnVerLibroMayor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(445, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(btnVerLibroMayor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(463, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerLibroMayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerLibroMayorActionPerformed
        verLibroMayor();
    }//GEN-LAST:event_btnVerLibroMayorActionPerformed

    public void verLibroMayor() {
         try {
            Map<String, Object> params = new HashMap<String, Object>();
            
            Connection con;
            JasperReport reporte;
            Conexion cx = new Conexion(sesion.rutaConexion);
            con = cx.conectar();
            reporte = JasperCompileManager.compileReport("src/reportes/reporte-master-libro-mayor.jrxml");

            //Currency currentyActual = Currency.getInstance(Locale.US);
            Locale locale = new Locale("es", "SV");

            params.put("param_id_ciclo", sesion.configUsuario.getId_ciclo_contable());
            params.put("param_titulo_ciclo_contable", sesion.configUsuario.getNombre_ciclo_contable());
            params.put("param_nombre_completo", sesion.usuario.getPersona().nombreCompleto());
            params.put("param_usuario", sesion.usuario.getNombre());
            
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
        } catch (JRException ex) {
            Logger.getLogger(vLibroDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShapeIcon btnVerLibroMayor;
    // End of variables declaration//GEN-END:variables
}
