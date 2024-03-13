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
    int id_tipo_catalogo;
    public String codigo;
    String ref;
    String nombre;
    int nivel;
    String tipo_saldo;
    String ingresos;
    String egresos;
    boolean eliminado;
    int disponible;

    public Cuenta(int id, int id_tipo_catalogo, String codigo, String ref, String nombre, int nivel, String tipo_saldo, String ingresos, String egresos, boolean eliminado, int disponible) {
        this.id = id;
        this.id_tipo_catalogo = id_tipo_catalogo;
        this.codigo = codigo;
        this.ref = ref;
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipo_saldo = tipo_saldo;
        this.ingresos = ingresos;
        this.egresos = egresos;
        this.eliminado = eliminado;
        this.disponible = disponible;
    }
    
    @Override
    public String toString() {
        return "Cuenta{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", ref='" + ref + '\'' +
                ", nombre='" + nombre + '\'' +
                ", id_tipo_catalogo=" + id_tipo_catalogo +
                ", nivel=" + nivel +
                ", tipo_saldo='" + tipo_saldo + '\'' +
                ", ingresos='" + ingresos + '\'' +
                ", egresos='" + egresos + '\'' +
                ", eliminado=" + eliminado +
                ", disponible=" + disponible +
                '}';
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
        this.disponible = 0;
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

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

}
