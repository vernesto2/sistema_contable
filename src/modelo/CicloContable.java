/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author vacev
 */
public class CicloContable {
    
    int id;
    int id_catalogo;
    String titulo;
    Date desde;
    Date hasta;
    boolean eliminado;

    public CicloContable(int id, int id_catalogo, String titulo, Date desde, Date hasta, boolean eliminado) {
        this.id = id;
        this.id_catalogo = id_catalogo;
        this.titulo = titulo;
        this.desde = desde;
        this.hasta = hasta;
        this.eliminado = eliminado;
    }
    
    public CicloContable() {
        this.id = -1;
        this.id_catalogo = -1;
        this.titulo = "";
        this.desde = new Date();
        this.hasta = new Date();
        this.eliminado = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_catalogo() {
        return id_catalogo;
    }

    public void setId_catalogo(int id_catalogo) {
        this.id_catalogo = id_catalogo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
    
    
    
}
