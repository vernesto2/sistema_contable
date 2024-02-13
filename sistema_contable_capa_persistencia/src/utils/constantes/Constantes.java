/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils.constantes;

import javax.swing.JOptionPane;
import modelo.ConfiguracionUsuario;
import modelo.Persona;
import modelo.Usuario;

/**
 *
 * @author vacev
 */
public class Constantes {
    // ruta de configuracion de archivo de DB
    public static String rutaConexion = "database/db.sqlite";
    
    // constantes para manejar el tipo de PERSONA
    public static int TIPO_DOCENTE = 1;
    public static int TIPO_ALUMNO = 2;
    
    // constantes para saber si se resetea la clave al usuario
    public static int RESETEAR_CLAVE = 1;
    public static int NO_RESETEAR_CLAVE = 0;
    
    // modelos a usar en el proyecto general
    public static Persona persona = new Persona();
    public static Usuario usuario = new Usuario();
    public static ConfiguracionUsuario configUsuario = new ConfiguracionUsuario();
    
    public static final String [] PREGUNTAS_SEGURIDAD = new String[] { 
        "Seleccione ...",
        "¿Cuál es el nombre de tu primera mascota?", 
        "¿Cuál sería tu trabajo ideal?", 
        "¿Qué actividad es la que más disfrutas hacer?" 
    };
    // logo en pantalla principal
    
    public static final int QUERY_SUCCESS = 1;
    
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
