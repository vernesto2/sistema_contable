/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.math.BigDecimal;
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
    int tipo_sociedad;
    BigDecimal porcentaje_reserva_legal;
    boolean eliminado;
    TipoCatalogo tipoCatalogo = new TipoCatalogo();
    
    public CicloContable() {
        this.id = -1;
        this.id_catalogo = -1;
        this.titulo = "";
        this.desde = new Date();
        this.tipo_sociedad = 0;
        this.porcentaje_reserva_legal = BigDecimal.valueOf(Double.parseDouble("0"));
        this.hasta = new Date();
        this.eliminado = false;
        this.tipoCatalogo = new TipoCatalogo();
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

    public TipoCatalogo getTipoCatalogo() {
        return tipoCatalogo;
    }

    public void setTipoCatalogo(TipoCatalogo tipoCatalogo) {
        this.tipoCatalogo = tipoCatalogo;
    }

    public int getTipo_sociedad() {
        return tipo_sociedad;
    }

    public void setTipo_sociedad(int tipo_sociedad) {
        this.tipo_sociedad = tipo_sociedad;
    }

    public BigDecimal getPorcentaje_reserva_legal() {
        return porcentaje_reserva_legal;
    }

    public void setPorcentaje_reserva_legal(BigDecimal porcentaje_reserva_legal) {
        this.porcentaje_reserva_legal = porcentaje_reserva_legal;
    }
}
