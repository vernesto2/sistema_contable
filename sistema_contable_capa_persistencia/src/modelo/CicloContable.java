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
    Double porcentaje_reserva_legal;
    boolean eliminado;
    Double monto_maximo_ventas;
    Double porcentaje_min;
    Double porcentaje_max;
    int sin_libro_diario;
    
    TipoCatalogo tipoCatalogo = new TipoCatalogo();
    
    public CicloContable() {
        this.id = -1;
        this.id_catalogo = -1;
        this.titulo = "";
        this.desde = new Date();
        this.tipo_sociedad = 0;
        this.porcentaje_reserva_legal = 7.0;
        this.hasta = new Date();
        this.eliminado = false;
        this.monto_maximo_ventas = 150000.00;
        this.porcentaje_min = 25.00;
        this.porcentaje_max = 30.00;
        this.sin_libro_diario = 0;
        
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

    public Double getPorcentaje_reserva_legal() {
        return porcentaje_reserva_legal;
    }

    public void setPorcentaje_reserva_legal(Double porcentaje_reserva_legal) {
        this.porcentaje_reserva_legal = porcentaje_reserva_legal;
    }

    public Double getMonto_maximo_ventas() {
        return monto_maximo_ventas;
    }

    public void setMonto_maximo_ventas(Double monto_maximo_ventas) {
        this.monto_maximo_ventas = monto_maximo_ventas;
    }

    public Double getPorcentaje_min() {
        return porcentaje_min;
    }

    public void setPorcentaje_min(Double porcentaje_min) {
        this.porcentaje_min = porcentaje_min;
    }

    public Double getPorcentaje_max() {
        return porcentaje_max;
    }

    public void setPorcentaje_max(Double porcentaje_max) {
        this.porcentaje_max = porcentaje_max;
    }

    public int getSin_libro_diario() {
        return sin_libro_diario;
    }

    public void setSin_libro_diario(int sin_libro_diario) {
        this.sin_libro_diario = sin_libro_diario;
    }
    
}
