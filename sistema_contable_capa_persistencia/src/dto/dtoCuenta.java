/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import modelo.Cuenta;

/**
 *
 * @author vacev
 */
public class dtoCuenta extends Cuenta{
    String catalogo = "";

    public dtoCuenta(int id, int id_tipo_catalogo, String codigo, String ref, String nombre, int nivel, String tipo_saldo, String ingresos, String egresos, boolean eliminado, int disponible, int es_restado, int folio_mayor, int id_ciclo_folio, int id_cuenta_balance) {
        super(id, id_tipo_catalogo, codigo, ref, nombre, nivel, tipo_saldo, ingresos, egresos, eliminado, disponible, es_restado, folio_mayor, id_ciclo_folio, id_cuenta_balance);
        this.catalogo = catalogo;
    }

    public dtoCuenta() {
        this.catalogo = "";
    }
    
    public String getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(String catalogo) {
        this.catalogo = catalogo;
    }
}
