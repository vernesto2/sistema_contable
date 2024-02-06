/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author vacev
 */
public class CicloContable {
    
    int id;
    int id_catalogo;
    String titulo;
    String desde;
    String hasta;

    public CicloContable(int id, int id_catalogo, String titulo, String desde, String hasta) {
        this.id = id;
        this.id_catalogo = id_catalogo;
        this.titulo = titulo;
        this.desde = desde;
        this.hasta = hasta;
    }
    
    public CicloContable() {
        this.id = -1;
        this.id_catalogo = -1;
        this.titulo = "";
        this.desde = "";
        this.hasta = "";
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

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }
    
    
    
}
