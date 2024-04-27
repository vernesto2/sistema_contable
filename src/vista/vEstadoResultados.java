/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import dto.dtoFormula;
import java.util.ArrayList;
import java.util.List;
import modelo.Cuenta;
import modelo.Formula;
import modelo.TipoCatalogo;
import reportes.CuentaBalanza;
import servicios.CalculadoraEstadoResultados;
import servicios.ServicioCuenta;
import servicios.ServicioFormula;
import sesion.Sesion;
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
    Cuenta cuentaSeleccionada;
    public vEstadoResultados(Sesion sesion) {
        initComponents();
        this.sesion = sesion;
        this._cuenta = new ServicioCuenta(sesion.rutaConexion);
        this._formula = new ServicioFormula(sesion.rutaConexion);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVerEstadoResultados1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVerEstadoResultados2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerEstadoResultados1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerEstadoResultados2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(246, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerEstadoResultados1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerEstadoResultados1ActionPerformed
        verEstadoResultados(
            Integer.parseInt(Constantes.TIPO_PARTIDA_AJUSTE.getValue())
        );
    }//GEN-LAST:event_btnVerEstadoResultados1ActionPerformed

    private void btnVerEstadoResultados2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerEstadoResultados2ActionPerformed
        verEstadoResultados(
            Integer.parseInt(Constantes.TIPO_PARTIDA_CIERRE.getValue())
        );
    }//GEN-LAST:event_btnVerEstadoResultados2ActionPerformed
    private void verEstadoResultados(Integer tipoPartida) {
        TipoCatalogo tipoCatalogo = sesion.configUsuario.getCicloContable().getTipoCatalogo();
        ArrayList<dtoFormula> listaFormula = (ArrayList<dtoFormula>) _formula.obtenerListaPorIdTipoCatalogo(
                tipoCatalogo.getId()
        ).getDatos();
        ArrayList<CuentaBalanza> listaCuentasBalanza = (ArrayList<CuentaBalanza>)_cuenta.listarCuentaBalanzaComprobacion(
                tipoCatalogo.getId(), sesion.configUsuario.getCicloContable().getId(), tipoPartida
        ).getDatos();
        CalculadoraEstadoResultados calcEstadoResultados = new CalculadoraEstadoResultados(listaFormula, listaCuentasBalanza, null, sesion.configUsuario.getCicloContable());
        Double utilidadPerdida = calcEstadoResultados.resolverFormula();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShapeIcon btnVerEstadoResultados1;
    private RSMaterialComponent.RSButtonShapeIcon btnVerEstadoResultados2;
    // End of variables declaration//GEN-END:variables
}