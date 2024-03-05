/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package formularios;

import java.awt.Dimension;
import sesion.Sesion;
import utils.constantes.CambiaPanel;
import utils.constantes.Constantes;
import vista.pCrearUsuario;

/**
 *
 * @author student
 */
public class dCrearUsuario extends javax.swing.JDialog {

    /**
     * Creates new form dCrearUsuario
     */
    public dCrearUsuario(java.awt.Frame parent, boolean modal, Sesion sesion, int tipoUsuario) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        if( tipoUsuario == Constantes.TIPO_DOCENTE) {
            this.setTitle("Crear docente");
        }else if(tipoUsuario == Constantes.TIPO_ALUMNO) {
            this.setTitle("Crear alumno");
        } else throw new IllegalArgumentException("Tipo incorrecto");
        boolean esRestaurar = false;
        pCrearUsuario panel = new pCrearUsuario(
                        sesion, 
                        esRestaurar,
                        tipoUsuario
                );
        this.container.removeAll();
        this.container.add(panel);
        
        this.pack();
        container.revalidate();
        container.repaint();
        iniciarVistaDialog();
    }
    public void iniciarVistaDialog() {
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(794, 527));
        setResizable(false);

        container.setLayout(new java.awt.BorderLayout());
        getContentPane().add(container, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel container;
    // End of variables declaration//GEN-END:variables
}
