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
public class CicloContableFolio {
    
    int id;
    int id_ciclo_contable;
    int id_cuenta;
    double saldo;
    int folio_mayor;
    int id_cuenta_balance;
    
    CicloContable cicloContable;
    Cuenta cuenta;

    public CicloContableFolio() {
        this.id = -1;
        this.id_ciclo_contable = -1;
        this.id_cuenta = -1;
        this.saldo = 0.0;
        this.folio_mayor = 0;
        this.id_cuenta_balance = 0;
        
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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
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

    public int getFolio_mayor() {
        return folio_mayor;
    }

    public void setFolio_mayor(int folio_mayor) {
        this.folio_mayor = folio_mayor;
    }

    public int getId_cuenta_balance() {
        return id_cuenta_balance;
    }

    public void setId_cuenta_balance(int id_cuenta_balance) {
        this.id_cuenta_balance = id_cuenta_balance;
    }
    
}
