/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils.constantes;

import dto.dtoLista;
import java.util.ArrayList;
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
            opcion = 1; //JOptionPane.INFORMATION_MESSAGE;
        } else if (rg.getCodigo() >= 400 && rg.getCodigo() <= 499) {
            opcion = 2; // JOptionPane.WARNING_MESSAGE;
        } else if (rg.getCodigo() >= 500 && rg.getCodigo() <= 599) {
            opcion = 0; // JOptionPane.ERROR_MESSAGE;
        } else {
            opcion = -1; // JOptionPane.DEFAULT_OPTION;
        }
        return opcion;
    }
    
    // tipos de partidas disponibles en sistema
    public static ArrayList<dtoLista> listaTiposPartidas() {
        ArrayList<dtoLista> lista = new ArrayList<>();
        dtoLista op1 = new dtoLista(0, "MOVIVIENTO");
        dtoLista op2 = new dtoLista(1, "AJUSTE DE IVA");
        dtoLista op3 = new dtoLista(2, "CIERRE");
        lista.add(op1);
        lista.add(op2);
        lista.add(op3);
        return lista;
    }
    
    
    
}