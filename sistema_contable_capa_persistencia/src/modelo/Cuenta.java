/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author vacev
 */
public class Cuenta {
    
    int id;
    String codigo;
    String ref;
    String nombre;
    int id_tipo_catalogo;
    int nivel;
    String tipo_saldo;
    String ingresos;
    String egresos;
    boolean eliminado;

    public Cuenta(int id, String codigo, String ref, String nombre, int id_tipo_catalogo, int nivel, String tipo_saldo, String ingresos, String egresos, boolean eliminado) {
        this.id = id;
        this.codigo = codigo;
        this.ref = ref;
        this.nombre = nombre;
        this.id_tipo_catalogo = id_tipo_catalogo;
        this.nivel = nivel;
        this.tipo_saldo = tipo_saldo;
        this.ingresos = ingresos;
        this.egresos = egresos;
        this.eliminado = eliminado;
    }
    
    public Cuenta() {
        this.id = -1;
        this.codigo = "";
        this.ref = "";
        this.nombre = "";
        this.id_tipo_catalogo = -1;
        this.nivel = 0;
        this.tipo_saldo = "";
        this.ingresos = "";
        this.egresos = "";
        this.eliminado = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_tipo_catalogo() {
        return id_tipo_catalogo;
    }

    public void setId_tipo_catalogo(int id_tipo_catalogo) {
        this.id_tipo_catalogo = id_tipo_catalogo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getTipo_saldo() {
        return tipo_saldo;
    }

    public void setTipo_saldo(String tipo_saldo) {
        this.tipo_saldo = tipo_saldo;
    }

    public String getIngresos() {
        return ingresos;
    }

    public void setIngresos(String ingresos) {
        this.ingresos = ingresos;
    }

    public String getEgresos() {
        return egresos;
    }

    public void setEgresos(String egresos) {
        this.egresos = egresos;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
}
