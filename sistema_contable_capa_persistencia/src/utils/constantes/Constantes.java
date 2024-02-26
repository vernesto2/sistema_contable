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
    
    public static final String [] PREGUNTAS_SEGURIDAD = new String[] { 
        "Seleccione ...",
        "¿Cuál es el nombre de tu primera mascota?", 
        "¿Cuál sería tu trabajo ideal?", 
        "¿Qué actividad es la que más disfrutas hacer?" 
    };
    // logo en pantalla principal
    
    public static final int QUERY_SUCCESS = 1;
    
    // tipos de partidas disponibles en sistema
    public static ArrayList<dtoLista> listaTiposPartidas() {
        ArrayList<dtoLista> lista = new ArrayList<>();
        dtoLista op1 = new dtoLista("0", "MOVIVIENTO");
        dtoLista op2 = new dtoLista("1", "AJUSTE DE IVA");
        dtoLista op3 = new dtoLista("2", "CIERRE");
        lista.add(op1);
        lista.add(op2);
        lista.add(op3);
        return lista;
    }
    
    public static ArrayList<dtoLista> listaTiposSaldoCuentas() {
        ArrayList<dtoLista> lista = new ArrayList<>();
        dtoLista op1 = new dtoLista("", "DEUDOR");
        dtoLista op2 = new dtoLista("R", "ACREEDOR");
        lista.add(op1);
        lista.add(op2);
        return lista;
    }
    
}