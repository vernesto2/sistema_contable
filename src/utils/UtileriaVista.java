package utils;


import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.ConfiguracionUsuario;
import modelo.Usuario;
import servicios.ServicioConfigUsuario;
import sesion.Sesion;
import utils.constantes.Constantes;
import utils.constantes.RespuestaGeneral;
import vista.vPrincipal;

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
    
    public static void actualizarPerfil(Sesion sesion) {
        ServicioConfigUsuario _configUsuario = new ServicioConfigUsuario(sesion.rutaConexion);
        ArrayList<ConfiguracionUsuario> listaConfigUsuario = new ArrayList<>();
        RespuestaGeneral rg = _configUsuario.obtenerPorIdUsuario(sesion.usuario.getId());
        if (rg.esExitosa()) {
            listaConfigUsuario = (ArrayList<ConfiguracionUsuario>)rg.getDatos();
            ConfiguracionUsuario cUsuario = new ConfiguracionUsuario();
            cUsuario.setId_usuario(sesion.usuario.getId());
            if (listaConfigUsuario.isEmpty()) {
                // creamos y guardamos una configuracion
                RespuestaGeneral rg1 = _configUsuario.insertar(cUsuario);
                if (rg1.esExitosa()) {
                    cUsuario.setId(Integer.parseInt(rg1.getDatos().toString()));
                } else {
                    String mensaje = !rg1.getMensaje().equals("") ? rg1.getMensaje() : "No se pudo guardar la configuración de usuario";
                    JOptionPane.showMessageDialog(null, mensaje, "Mensaje", UtileriaVista.devolverCodigoMensaje(rg1));
                    return;
                }
                listaConfigUsuario.add(cUsuario);
            }
            sesion.configUsuario = listaConfigUsuario.get(0);
            if (sesion.configUsuario.getId_ciclo_contable() == -1) {
                vPrincipal.txtConfigCicloContable.setText("NO SE HA SELECCIONADO NINGUN CICLO CONTABLE");
            } else {
                vPrincipal.txtConfigCicloContable.setText(sesion.configUsuario.nombreCicloYCatalogo());
            }
            int color = sesion.configUsuario.getCicloContable().getTipoCatalogo().getColor();
            vPrincipal.txtConfigCicloContable.setForeground(Constantes.devolverColor(color));
            vPrincipal.txtNombreUsuario.setText(sesion.usuario.getPersona().nombreCompleto());
            
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo recuperar la configuración de usuario", "Mensaje", UtileriaVista.devolverCodigoMensaje(rg));
            return;
        }
    }
    
}
