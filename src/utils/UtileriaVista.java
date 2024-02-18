package utils;


import javax.swing.JOptionPane;
import utils.constantes.RespuestaGeneral;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author student
 */
public class UtileriaVista {

    public static int devolverCodigoMensaje(RespuestaGeneral rg) {
        int opcion = -1;
        if (rg.getCodigo() >= 200 && rg.getCodigo() <= 299) {
            opcion = JOptionPane.INFORMATION_MESSAGE;
        } else if (rg.getCodigo() >= 400 && rg.getCodigo() <= 499) {
            opcion = JOptionPane.WARNING_MESSAGE;
        } else if (rg.getCodigo() >= 500 && rg.getCodigo() <= 599) {
            opcion = JOptionPane.ERROR_MESSAGE;
        } else {
            opcion = JOptionPane.DEFAULT_OPTION;
        }
        return opcion;
    }
}
