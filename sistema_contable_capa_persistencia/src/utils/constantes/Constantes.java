/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils.constantes;

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
}
