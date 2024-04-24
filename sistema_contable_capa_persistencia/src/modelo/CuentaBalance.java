/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dto.dtoCuenta;

/**
 *
 * @author vacev
 */
public class CuentaBalance {
    
    int id;
    int id_ciclo_contable;
    int id_cuenta;
    double saldo_inicial;
    double saldo_final;
    
    CicloContable cicloContable;
    Cuenta cuenta;

    public CuentaBalance() {
        this.id = -1;
        this.id_ciclo_contable = -1;
        this.id_cuenta = -1;
        this.saldo_inicial = 0.0;
        this.saldo_final = 0.0;
        
        this.cicloContable = new CicloContable();
        this.cuenta = new dtoCuenta();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_ciclo_contable() {
        return id_ciclo_contable;
    }

    public void setId_ciclo_contable(int id_ciclo_contable) {
        this.id_ciclo_contable = id_ciclo_contable;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public double getSaldo_inicial() {
        return saldo_inicial;
    }

    public void setSaldo_inicial(double saldo_inicial) {
        this.saldo_inicial = saldo_inicial;
    }

    public double getSaldo_final() {
        return saldo_final;
    }

    public void setSaldo_final(double saldo_final) {
        this.saldo_final = saldo_final;
    }

    public CicloContable getCicloContable() {
        return cicloContable;
    }

    public void setCicloContable(CicloContable cicloContable) {
        this.cicloContable = cicloContable;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    
}
