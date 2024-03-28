/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils.constantes;

import dto.dtoLista;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author vacev
 */
public class Constantes {
    // ruta de configuracion de archivo de DB
    //final es para que no se pueda modificar ( variable = valor)
    public static final String rutaConexion = "database/db.sqlite";
    public static final String RUTA_ARCHIVO_SQL_INICIAL = "database/config_inicial.sql";
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
        dtoLista op1 = new dtoLista("D", "DEUDOR");
        dtoLista op2 = new dtoLista("A", "ACREEDOR");
        lista.add(op1);
        lista.add(op2);
        return lista;
    }
    
    public static ArrayList<dtoLista> listaEleccionSINO() {
        ArrayList<dtoLista> lista = new ArrayList<>();
        dtoLista op1 = new dtoLista("0", "NO");
        dtoLista op2 = new dtoLista("1", "SI");
        lista.add(op1);
        lista.add(op2);
        return lista;
    }
    
    public static ArrayList<dtoLista> listaTipoPartidas() {
        ArrayList<dtoLista> lista = new ArrayList<>();
        dtoLista op1 = new dtoLista("0", "OPERATIVA");
        dtoLista op2 = new dtoLista("1", "AJUSTE");
        dtoLista op3 = new dtoLista("2", "CIERRE");
        lista.add(op1);
        lista.add(op2);
        lista.add(op3);
        return lista;
    }
    
    public static ArrayList<dtoLista> listaTipoSociedad() {
        ArrayList<dtoLista> lista = new ArrayList<>();
        dtoLista op1 = new dtoLista("0", "SOCIEDAD ANONIMA S.A de C.V");
        dtoLista op2 = new dtoLista("1", "SOCIEDAD DE PERSONA");
        lista.add(op1);
        lista.add(op2);
        return lista;
    }
    
    public static ArrayList<dtoLista> listaColores() {
        ArrayList<dtoLista> lista = new ArrayList<>();
        dtoLista op0 = new dtoLista("0", "NEGRO");
        dtoLista op1 = new dtoLista("1", "AZUL");
        dtoLista op2 = new dtoLista("2", "CELESTE");
        dtoLista op3 = new dtoLista("3", "GRIS OSCURO");
        dtoLista op4 = new dtoLista("4", "GRIS");
        dtoLista op5 = new dtoLista("5", "VERDE");
        dtoLista op6 = new dtoLista("6", "GRIS CLARO");
        dtoLista op7 = new dtoLista("7", "MORADO");
        dtoLista op8 = new dtoLista("8", "NARANJA");
        dtoLista op9 = new dtoLista("9", "ROSADO");
        dtoLista op10 = new dtoLista("10", "ROJO");
        dtoLista op11 = new dtoLista("11", "AMARILLO");
        lista.add(op0);
        lista.add(op1);
        lista.add(op2);
        lista.add(op3);
        lista.add(op4);
        lista.add(op5);
        lista.add(op6);
        lista.add(op7);
        lista.add(op8);
        lista.add(op9);
        lista.add(op10);
        lista.add(op11);
        return lista;
    }
    
    // devuelve un valor de tipo Color
    public static Color devolverColor(int i) {
        Color c = Color.BLACK;
        switch (i) {
            case 0 -> c = Color.BLACK;
            case 1 -> c = Color.BLUE;
            case 2 -> c = Color.CYAN;
            case 3 -> c = Color.DARK_GRAY;
            case 4 -> c = Color.GRAY;
            case 5 -> c = Color.GREEN;
            case 6 -> c = Color.LIGHT_GRAY;
            case 7 -> c = Color.MAGENTA;
            case 8 -> c = Color.ORANGE;
            case 9 -> c = Color.PINK;
            case 10 -> c = Color.RED;
            case 11 -> c = Color.YELLOW;
            default -> c = Color.BLACK;
        }
        return c;
    }
    
    // constantes para manejar el tipo de ACCION (ABONAR, CARGAR)
    public static int TIPO_CARGO = 1;
    public static int TIPO_ABONO = 2;
    public static String TIPO_CARGO_S = "C";
    public static String TIPO_ABONO_S = "A";
    
    public static boolean validarNumeros(int key) {
        if (key >= 48 && key <= 57) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean validarPorcentaje(int key) {
        return (key >= 48 && key <= 57) || key == 46;
    }
}