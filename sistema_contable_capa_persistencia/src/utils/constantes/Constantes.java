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
    public static String rutaConexion = "database/db.sqlite";
    public static final String RUTA_ARCHIVO_SQL_INICIAL = "database/config_inicial.sql";
    // constantes para manejar el tipo de PERSONA
    public static int TIPO_DOCENTE = 1;
    public static int TIPO_ALUMNO = 2;
    
    // constantes para saber si se resetea la clave al usuario
    public static int RESETEAR_CLAVE = 1;
    public static int NO_RESETEAR_CLAVE = 0;
    
    public static final dtoLista TIPO_PARTIDA_OPERATIVA = new dtoLista("0", "OPERATIVA");
    public static final dtoLista TIPO_PARTIDA_AJUSTE = new dtoLista("1", "AJUSTE");
    public static final dtoLista TIPO_PARTIDA_CIERRE = new dtoLista("2", "CIERRE");
    
    public static final String [] PREGUNTAS_SEGURIDAD = new String[] { 
        "Seleccione ...",
        "¿Cuál es el nombre de tu primera mascota?", 
        "¿Cuál sería tu trabajo ideal?", 
        "¿Qué actividad es la que más disfrutas hacer?",
        "¿Cuál es tu color favorito?",
        "¿Cuál es el nombre de tu abuela?",
        "¿Cuál es tu programa de tv favorito?",
        "¿Cuál es tu comida favorita?",
        "¿Cuál materia favorita?",
    };
    // logo en pantalla principal
    
    public static final int QUERY_SUCCESS = 1;
    
    public static final dtoLista TIPO_SALDO_DEUDOR = new dtoLista("D", "DEUDOR");
    public static final dtoLista TIPO_SALDO_ACREEDOR = new dtoLista("A", "ACREEDOR");
    public static final dtoLista TIPO_SALDO_TRANSITORIA = new dtoLista("T", "TRANSITORIA");
    public static ArrayList<dtoLista> listaTiposSaldoCuentas() {
        ArrayList<dtoLista> lista = new ArrayList<>();
        
        lista.add(TIPO_SALDO_DEUDOR);
        lista.add(TIPO_SALDO_ACREEDOR);
        lista.add(TIPO_SALDO_TRANSITORIA);
        
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
        lista.add(TIPO_PARTIDA_OPERATIVA);
        lista.add(TIPO_PARTIDA_AJUSTE);
        lista.add(TIPO_PARTIDA_CIERRE);
        return lista;
    }
    public static final dtoLista SOCIEDAD_ANONIMA = new dtoLista("0", "SOCIEDAD ANONIMA S.A de C.V");
    public static final dtoLista SOCIEDAD_DE_PERSONAS = new dtoLista("1", "SOCIEDAD DE PERSONA");
    public static ArrayList<dtoLista> listaTipoSociedad() {
        ArrayList<dtoLista> lista = new ArrayList<>();
        lista.add(SOCIEDAD_ANONIMA);
        lista.add(SOCIEDAD_DE_PERSONAS);
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
    
    // tipos de Formulas
    public static ArrayList<dtoLista> listaTiposFormulaEstadoResultado() {
        ArrayList<dtoLista> lista = new ArrayList<>();
        dtoLista op1 = new dtoLista("Estado de Resultado", "Estado de Resultado");
        dtoLista op2 = new dtoLista("Estado de Costo de Producción", "Estado de Costo de Producción");
        dtoLista op3 = new dtoLista("Estado de Costo de Venta", "Estado de Costo de Venta");
        dtoLista op4 = new dtoLista("Estado de Materia Prima Consumida", "Estado de Materia Prima Consumida");
        dtoLista op5 = new dtoLista("Estado de Material Directo Consumido", "Estado de Material Directo Consumido");
        lista.add(op1);
        lista.add(op2);
        lista.add(op3);
        lista.add(op4);
        lista.add(op5);
        return lista;
    }
    
    public static final dtoLista SIGNO_MAS = new dtoLista("(+)", "(+)");
    public static final dtoLista SIGNO_MENOS = new dtoLista("(-)", "(-)");
    public static final dtoLista SIGNO_IGUAL = new dtoLista("(=)", "(=)");
    
    // tipos de Formulas
    public static ArrayList<dtoLista> listaSignos() {
        ArrayList<dtoLista> lista = new ArrayList<>();
        lista.add(SIGNO_MAS);
        lista.add(SIGNO_MENOS);
        lista.add(SIGNO_IGUAL);
        return lista;
    }
    
    public static final dtoLista TIPO_CUENTA_ESPECIAL_CALCULADO = new dtoLista("0", "Calculado ( con signo = )");
    public static final dtoLista TIPO_CUENTA_ESPECIAL_VENTAS_TOTALES = new dtoLista("1", "Ventas totales");
    public static final dtoLista TIPO_CUENTA_ESPECIAL_SALDO = new dtoLista("2", "Saldo");
    public static final dtoLista TIPO_CUENTA_ESPECIAL_VALOR_INGRESADO = new dtoLista("3", "Valor Ingresado");
    public static final dtoLista TIPO_CUENTA_ESPECIAL_RESERVA_LEGAL = new dtoLista("4", "Reserva Legal");
    public static final dtoLista TIPO_CUENTA_ESPECIAL_IMPUESTO_SOBRE_RENTA = new dtoLista("5", "Impuesto Sobre Renta");

// tipos de Formulas
    public static ArrayList<dtoLista> listaTiposCampoEspecialEstadoResultado() {
        ArrayList<dtoLista> lista = new ArrayList<>();
        lista.add(TIPO_CUENTA_ESPECIAL_CALCULADO);
        lista.add(TIPO_CUENTA_ESPECIAL_VENTAS_TOTALES);
        lista.add(TIPO_CUENTA_ESPECIAL_SALDO);
        lista.add(TIPO_CUENTA_ESPECIAL_VALOR_INGRESADO);
        lista.add(TIPO_CUENTA_ESPECIAL_RESERVA_LEGAL);
        lista.add(TIPO_CUENTA_ESPECIAL_IMPUESTO_SOBRE_RENTA);
        return lista;
    }
    
    public static String devolverCuentaEspecial(int value) {
        ArrayList<dtoLista> listaCuentaEspecial = new ArrayList<>();
        listaCuentaEspecial = listaTiposCampoEspecialEstadoResultado();
        return listaCuentaEspecial.get(value).getLabel();
    } 
    
    // tipos de Formulas
    public static ArrayList<dtoLista> listaAvatars() {
        ArrayList<dtoLista> lista = new ArrayList<>();
        dtoLista op1 = new dtoLista("/utils/avatar/avatar1.png", "Hombre 1");
        dtoLista op2 = new dtoLista("/utils/avatar/avatar2.png", "Hombre 2");
        dtoLista op3 = new dtoLista("/utils/avatar/avatar3.png", "Hombre 3");
        dtoLista op4 = new dtoLista("/utils/avatar/avatar4.png", "Mujer 1");
        dtoLista op5 = new dtoLista("/utils/avatar/avatar5.png", "Mujer 2");
        dtoLista op6 = new dtoLista("/utils/avatar/avatar6.png", "Mujer 3");
        lista.add(op1);
        lista.add(op2);
        lista.add(op3);
        lista.add(op4);
        lista.add(op5);
        lista.add(op6);
        return lista;
    }
}