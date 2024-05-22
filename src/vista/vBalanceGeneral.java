/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import conexion.Conexion;
import dto.dtoFormula;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.CicloContable;
import modelo.Cuenta;
import modelo.FormulaParametro;
import modelo.TipoCatalogo;
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
import reportes.ElementoFormulaReporte;
import servicios.CalculadoraEstadoResultados;
import servicios.ServicioCuenta;
import servicios.ServicioCuentaBalance;
import servicios.ServicioFormula;
import servicios.ServicioFormulaParametro;
import sesion.Sesion;
import utils.UtileriaVista;
import utils.constantes.Constantes;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author student
 */
public class vBalanceGeneral extends javax.swing.JPanel {

    /**
     * Creates new form vBalanceGeneral
     */
    
    Sesion sesion;
    ServicioCuenta _cuenta;
    ServicioCuentaBalance _cuentaBalance;
    
    public vBalanceGeneral(Sesion sesion) {
        initComponents();
        this.sesion = sesion;
        this._cuenta = new ServicioCuenta(sesion.rutaConexion);
        this._cuentaBalance = new ServicioCuentaBalance(sesion.rutaConexion);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVerBalanceGeneral = new RSMaterialComponent.RSButtonShapeIcon();

        btnVerBalanceGeneral.setBackground(new java.awt.Color(251, 205, 6));
        btnVerBalanceGeneral.setText("Ver balance general");
        btnVerBalanceGeneral.setBackgroundHover(new java.awt.Color(251, 174, 6));
        btnVerBalanceGeneral.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnVerBalanceGeneral.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnVerBalanceGeneral.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnVerBalanceGeneral.setForegroundText(new java.awt.Color(0, 0, 0));
        btnVerBalanceGeneral.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BOOK);
        btnVerBalanceGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerBalanceGeneralActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVerBalanceGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(342, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVerBalanceGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerBalanceGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerBalanceGeneralActionPerformed
        verBalanceGeneral();
    }//GEN-LAST:event_btnVerBalanceGeneralActionPerformed

    private void verBalanceGeneral() {
        
        TipoCatalogo tipoCatalogo = sesion.configUsuario.getCicloContable().getTipoCatalogo();
        _cuenta.setServicioCuentaBalanza(_cuentaBalance);;
        ArrayList<FormulaParametro> listaParametros = null;
        
        try (
                InputStream inputStream = getClass().getResourceAsStream("/reportes/reporte-estado-resultados.jrxml"); BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
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
            Integer tamanoCodigoMayorizar = null;
            if (nivelAMayorizar == 0) {
                params.put("param_tamano_codigo", 0);
            } else {
                RespuestaGeneral rg = _cuenta.tamanoCodigoAMayorizar(idTipoCatalogo, nivelAMayorizar);
                tamanoCodigoMayorizar = (Integer) rg.getDatos();
                if (tamanoCodigoMayorizar == null) {
                    JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
                } else {
                    params.put("param_tamano_codigo", tamanoCodigoMayorizar);
                }
            }
            Integer idCiclo = sesion.configUsuario.getCicloContable().getId();
            
            RespuestaGeneral rgBalanceGeneral = _cuenta.listarBalanceGeneral(sesion.configUsuario.getCicloContable(), tamanoCodigoMayorizar);
            List<ElementoFormulaReporte> listElementoReporte = (List<ElementoFormulaReporte>) rgBalanceGeneral.getDatos();
            
            JRBeanCollectionDataSource param_elem_balance_general = new JRBeanCollectionDataSource(listElementoReporte);
            params.put("param_elem_balance_general", param_elem_balance_general);
//            params.put("params_cuentas_balanza", listBeans);

            params.put(JRParameter.REPORT_LOCALE, locale);

            JasperPrint jp = JasperFillManager.fillReport(reporte, params, new JREmptyDataSource());
            final boolean EXIT_ON_CLOSE = false;
            JasperViewer.viewReport(jp, EXIT_ON_CLOSE);
        } catch (IOException | JRException ex) {
            Logger.getLogger(vLibroDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShapeIcon btnVerBalanceGeneral;
    // End of variables declaration//GEN-END:variables
}
