/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author vacev
 */
public class PartidaDetalle implements Comparable<PartidaDetalle>{
    
    int id;
    int id_partida;
    int id_cuenta;
    double parcial;
    double debe;
    double haber;
    public int tipo_cargo_abono;
    boolean eliminado;
    int folio_mayor;
    
    public Cuenta cuenta;
    Cuenta cuentaMayor;
    
    public PartidaDetalle() {
        this.id = -1;
        this.id_partida = -1;
        this.id_cuenta = -1;
        this.parcial = 0.0;
        this.debe = 0.0;
        this.haber = 0.0;
        this.tipo_cargo_abono = 1;
        this.eliminado = false;
        this.folio_mayor = 0;
        
        this.cuenta = new Cuenta();
        this.cuentaMayor = new Cuenta();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_partida() {
        return id_partida;
    }

    public void setId_partida(int id_partida) {
        this.id_partida = id_partida;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public double getParcial() {
        return parcial;
    }

    public void setParcial(double parcial) {
        this.parcial = parcial;
    }

    public double getDebe() {
        return debe;
    }

    public void setDebe(double debe) {
        this.debe = debe;
    }

    public double getHaber() {
        return haber;
    }

    public void setHaber(double haber) {
        this.haber = haber;
    }

    public int getTipo_cargo_abono() {
        return tipo_cargo_abono;
    }

    public void setTipo_cargo_abono(int tipo_cargo_abono) {
        this.tipo_cargo_abono = tipo_cargo_abono;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Cuenta getCuentaMayor() {
        return cuentaMayor;
    }

    public void setCuentaMayor(Cuenta cuentaMayor) {
        this.cuentaMayor = cuentaMayor;
    }

    public int getFolio_mayor() {
        return folio_mayor;
    }

    public void setFolio_mayor(int folio_mayor) {
        this.folio_mayor = folio_mayor;
    }
    
    @Override
    public String toString() {
        return "PartidaDetalle{" +
                "id=" + id +
                ", id_partida=" + id_partida +
                ", id_cuenta=" + id_cuenta +
                ", parcial=" + parcial +
                ", debe=" + debe +
                ", haber=" + haber +
                ", tipo_cargo_abono=" + tipo_cargo_abono +
                ", eliminado=" + eliminado +
                ", folio_mayor=" + folio_mayor +
                ", cuenta=" + cuenta +
                '}';
    }

    @Override
    public int compareTo(PartidaDetalle o) {
        // Comparar primero por tipo_cargo_abono
        int comparacionPorTipo = Integer.compare(this.tipo_cargo_abono, o.tipo_cargo_abono);
        // Si los tipo_cargo_abono son diferentes, devolver la comparación por tipo_cargo_abono
        if (comparacionPorTipo != 0) {
            return comparacionPorTipo;
        }
        // Si los tipo_cargo_abono son iguales, comparar por el código de la cuenta
        return this.cuenta.codigo.compareTo(o.cuenta.codigo);
    }

}
