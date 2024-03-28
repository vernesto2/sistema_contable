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

    public dtoCuenta(int id, int id_tipo_catalogo, String codigo, String ref, String nombre, int nivel, String tipo_saldo, String ingresos, String egresos, boolean eliminado, int disponible, int es_restado) {
        super(id, id_tipo_catalogo, codigo, ref, nombre, nivel, tipo_saldo, ingresos, egresos, eliminado, disponible, es_restado);
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
