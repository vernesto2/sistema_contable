/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import conexion.Conexion;
import dto.dtoFormula;
import dto.dtoLista;
import formularios.dFormulaParametro;
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
import servicios.CalculadoraEstadoResultados;
import reportes.ElementoFormulaReporte;
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
public class vEstadoResultados extends javax.swing.JPanel {

    /**
     * Creates new form vEstadoResultados
     */
    Sesion sesion;
    ServicioCuenta _cuenta;
    ServicioFormula _formula;
    ServicioFormulaParametro _formulaParametro;
    Cuenta cuentaSeleccionada;
    ServicioCuentaBalance _cuentaBalance;

    public vEstadoResultados(Sesion sesion) {
        initComponents();
        this.sesion = sesion;
        this._cuenta = new ServicioCuenta(sesion.rutaConexion);
        this._formula = new ServicioFormula(sesion.rutaConexion);
        this._formulaParametro = new ServicioFormulaParametro(sesion.rutaConexion);
        this._cuentaBalance = new ServicioCuentaBalance(sesion.rutaConexion);
        iniciarVista();
    }

    private void iniciarVista() {
        ArrayList<dtoLista> listaTipoFormulaEstadoResultado = Constantes.listaTiposFormulaEstadoResultado();
        cmbEstadoFormula.removeAllItems();
        
        RespuestaGeneral rg = _formula.obtenerListaTipoFormulaPorTipoCatalogo(sesion.configUsuario.getCicloContable().getTipoCatalogo().getId());
        if (rg.esFallida()) {
            JOptionPane.showMessageDialog(this, rg.getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(rg));
            return;
        }
        List<dtoLista> listaTipoFormula = (List<dtoLista>) rg.getDatos();
        for (dtoLista dtoLista : listaTipoFormula) {
            cmbEstadoFormula.addItem(dtoLista.getValue());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVerEstadoResultados1 = new RSMaterialComponent.RSButtonShapeIcon();
        btnVerEstadoResultados2 = new RSMaterialComponent.RSButtonShapeIcon();
        btnDatos = new RSMaterialComponent.RSButtonShapeIcon();
        cmbEstadoFormula = new RSMaterialComponent.RSComboBoxMaterial();
        jLabel1 = new javax.swing.JLabel();

        btnVerEstadoResultados1.setBackground(new java.awt.Color(251, 205, 6));
        btnVerEstadoResultados1.setText("Ver hasta partidas de ajuste");
        btnVerEstadoResultados1.setAlignmentY(0.0F);
        btnVerEstadoResultados1.setBackgroundHover(new java.awt.Color(251, 174, 6));
        btnVerEstadoResultados1.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnVerEstadoResultados1.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnVerEstadoResultados1.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnVerEstadoResultados1.setForegroundText(new java.awt.Color(0, 0, 0));
        btnVerEstadoResultados1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BOOK);
        btnVerEstadoResultados1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerEstadoResultados1ActionPerformed(evt);
            }
        });

        btnVerEstadoResultados2.setBackground(new java.awt.Color(251, 205, 6));
        btnVerEstadoResultados2.setText("Ver hasta partidas de cierre");
        btnVerEstadoResultados2.setBackgroundHover(new java.awt.Color(251, 174, 6));
        btnVerEstadoResultados2.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnVerEstadoResultados2.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnVerEstadoResultados2.setForegroundIconHover(new java.awt.Color(0, 0, 0));
        btnVerEstadoResultados2.setForegroundText(new java.awt.Color(0, 0, 0));
        btnVerEstadoResultados2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BOOK);
        btnVerEstadoResultados2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerEstadoResultados2ActionPerformed(evt);
            }
        });

        btnDatos.setBackground(new java.awt.Color(33, 58, 86));
        btnDatos.setText("Agregar Datos");
        btnDatos.setAlignmentY(0.0F);
        btnDatos.setBackgroundHover(new java.awt.Color(33, 68, 86));
        btnDatos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnDatos.setForma(RSMaterialComponent.RSButtonShapeIcon.FORMA.ROUND);
        btnDatos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDatos.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BOOK);
        btnDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatosActionPerformed(evt);
            }
        });

        cmbEstadoFormula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoFormulaActionPerformed(evt);
            }
        });

        jLabel1.setText("Tipo de estado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVerEstadoResultados1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbEstadoFormula, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                            .addComponent(btnVerEstadoResultados2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cmbEstadoFormula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerEstadoResultados1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerEstadoResultados2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerEstadoResultados1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerEstadoResultados1ActionPerformed
        String tipoFormula = (String) cmbEstadoFormula.getSelectedItem();
        verEstadoResultados(
                Integer.parseInt(Constantes.TIPO_PARTIDA_AJUSTE.getValue()), 
                tipoFormula
        );
    }//GEN-LAST:event_btnVerEstadoResultados1ActionPerformed

    private void btnVerEstadoResultados2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerEstadoResultados2ActionPerformed
        String tipoFormula = (String) cmbEstadoFormula.getSelectedItem();
        verEstadoResultados(
                Integer.parseInt(Constantes.TIPO_PARTIDA_CIERRE.getValue()), 
                tipoFormula
        );
    }//GEN-LAST:event_btnVerEstadoResultados2ActionPerformed

    private void btnDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatosActionPerformed
        dFormulaParametro d = new dFormulaParametro(null, true, this.sesion.configUsuario.getCicloContable(), sesion);
        d.setVisible(true);
//        if (d.isRealizoAccion()) {
//            JOptionPane.showMessageDialog(this, d.getRg().getMensaje(), "INFORMACIÓN", UtileriaVista.devolverCodigoMensaje(d.getRg()));
//        }
    }//GEN-LAST:event_btnDatosActionPerformed

    private void cmbEstadoFormulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoFormulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadoFormulaActionPerformed
    private void verEstadoResultados(Integer tipoPartida, String tipoFormula) {
        TipoCatalogo tipoCatalogo = sesion.configUsuario.getCicloContable().getTipoCatalogo();
        ArrayList<dtoFormula> listaFormula = (ArrayList<dtoFormula>) _formula.obtenerListaPorIdTipoCatalogo(
                tipoCatalogo.getId()
        ).getDatos();

        _cuenta.setServicioCuentaBalanza(_cuentaBalance);;

        ArrayList<CuentaBalanza> listaCuentasBalanza = (ArrayList<CuentaBalanza>) _cuenta.listarCuentaBalanzaComprobacion(
                sesion.configUsuario.getCicloContable(), tipoPartida
        ).getDatos();

        ArrayList<FormulaParametro> listaParametros = null;
        RespuestaGeneral rgFormulaParametro = this._formulaParametro.obtenerLista(this.sesion.configUsuario.getCicloContable().getId(), Integer.parseInt(Constantes.TIPO_CUENTA_ESPECIAL_VALOR_INGRESADO.getValue()));
        if (rgFormulaParametro.esExitosa()) {
            //obtener la lista de parametros para construir el estado de resultados o cualquiera de sus estados relacionados
            listaParametros = (ArrayList<FormulaParametro>) rgFormulaParametro.getDatos();
        }
        CalculadoraEstadoResultados calcEstadoResultados = new CalculadoraEstadoResultados(listaFormula, listaCuentasBalanza, listaParametros, sesion.configUsuario.getCicloContable());
        List<ElementoFormulaReporte> listElementoReporte = calcEstadoResultados.resolverFormula(tipoFormula);

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
            if (nivelAMayorizar == 0) {
                params.put("param_tamano_codigo", 0);
            } else {
                RespuestaGeneral rg = _cuenta.tamanoCodigoAMayorizar(idTipoCatalogo, nivelAMayorizar);
                Integer tamanoNivelAMayorizar = (Integer) rg.getDatos();
                if (tamanoNivelAMayorizar == null) {
                    JOptionPane.showMessageDialog(this, rg.getMensaje(), "¡ALERTA!", UtileriaVista.devolverCodigoMensaje(rg));
                } else {
                    params.put("param_tamano_codigo", tamanoNivelAMayorizar);
                }
            }
            Integer idCiclo = sesion.configUsuario.getCicloContable().getId();
            
            params.put("param_titulo_reporte", tipoFormula);
            
            JRBeanCollectionDataSource param_elem_estado_resultados = new JRBeanCollectionDataSource(listElementoReporte);
            params.put("param_elem_estado_resultados", param_elem_estado_resultados);
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
    private RSMaterialComponent.RSButtonShapeIcon btnDatos;
    private RSMaterialComponent.RSButtonShapeIcon btnVerEstadoResultados1;
    private RSMaterialComponent.RSButtonShapeIcon btnVerEstadoResultados2;
    private RSMaterialComponent.RSComboBoxMaterial cmbEstadoFormula;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
