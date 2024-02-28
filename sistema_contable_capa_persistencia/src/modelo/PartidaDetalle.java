/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.math.BigDecimal;

/**
 *
 * @author vacev
 */
public class PartidaDetalle {
    
    int id;
    int id_partida;
    int id_cuenta;
    BigDecimal parcial;
    BigDecimal debe;
    BigDecimal haber;
    int tipo_cargo_abono;
    boolean eliminado;
    
    Cuenta cuenta;
    Cuenta cuentaMayor;

    public PartidaDetalle(int id, int id_partida, int id_cuenta, BigDecimal parcial, BigDecimal debe, BigDecimal haber, boolean eliminado) {
        this.id = id;
        this.id_partida = id_partida;
        this.id_cuenta = id_cuenta;
        this.parcial = parcial;
        this.debe = debe;
        this.haber = haber;
        this.eliminado = eliminado;
    }
    
    public PartidaDetalle() {
        this.id = -1;
        this.id_partida = -1;
        this.id_cuenta = -1;
        this.parcial = BigDecimal.valueOf(0);
        this.debe = BigDecimal.valueOf(0);
        this.haber = BigDecimal.valueOf(0);
        this.tipo_cargo_abono = 2;
        this.eliminado = false;
        
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

    public BigDecimal getParcial() {
        return parcial;
    }

    public void setParcial(BigDecimal parcial) {
        this.parcial = parcial;
    }

    public BigDecimal getDebe() {
        return debe;
    }

    public void setDebe(BigDecimal debe) {
        this.debe = debe;
    }

    public BigDecimal getHaber() {
        return haber;
    }

    public void setHaber(BigDecimal haber) {
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
    
}
