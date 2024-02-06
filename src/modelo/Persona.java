/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import utils.constantes.Constantes;

/**
 *
 * @author vacev
 */
public class Persona {
    
    int id;
    String nombres;
    String apellidos;
    int tipo; // 1 DOCENTE y 2 ALUMNO
    String carnet;

    public Persona(int id, String nombres, String apellidos, int tipo, String carnet) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipo = tipo;
        this.carnet = carnet;
    }
    
    public Persona() {
        this.id = -1;
        this.nombres = "Victor";
        this.apellidos = "Acevedo";
        this.tipo = Constantes.TIPO_ALUMNO;
        this.carnet = carnet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }
    
    public String nombreCompleto() {
        return getNombres() + " " + getApellidos();
    }
}
