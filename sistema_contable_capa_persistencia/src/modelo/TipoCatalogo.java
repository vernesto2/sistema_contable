/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author vacev
 */
public class TipoCatalogo {
    
    int id;
    String tipo;
    String ref;
    boolean eliminado;

    public TipoCatalogo(int id, String tipo, String ref, boolean eliminado) {
        this.id = id;
        this.tipo = tipo;
        this.ref = ref;
        this.eliminado = eliminado;
    }
    
    public TipoCatalogo() {
        this.id = -1;
        this.tipo = "";
        this.ref = "";
        this.eliminado = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
}
