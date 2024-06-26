/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportes;

import utils.constantes.Constantes;

/**
 *
 * @author student
 */
public class CuentaBalanza {

    private Integer id;
    private String tipoSaldo;
    private Integer folioMayor;
    private String nombre;
    private String codigo;
    private Double saldoInicial;
    private Double saldoDeudor;
    private Double saldoAcreedor;
    private Boolean esRestado;
    
    public CuentaBalanza() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoSaldo() {
        return tipoSaldo;
    }

    public void setTipoSaldo(String tipoSaldo) {
        this.tipoSaldo = tipoSaldo;
    }

    public Integer getFolioMayor() {
        return folioMayor;
    }

    public void setFolioMayor(Integer folioMayor) {
        this.folioMayor = folioMayor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Double getSaldoDeudor() {
        return saldoDeudor;
    }

    public void setSaldoDeudor(Double saldoDeudor) {
        this.saldoDeudor = saldoDeudor;
    }

    public Double getSaldoAcreedor() {
        return saldoAcreedor;
    }

    public void setSaldoAcreedor(Double saldoAcreedor) {
        this.saldoAcreedor = saldoAcreedor;
    }

    public Boolean getEsRestado() {
        return esRestado;
    }

    public void setEsRestado(Boolean esRestado) {
        this.esRestado = esRestado;
    }
    
    public Double saldo() {
        if (this.getTipoSaldo().equals(Constantes.TIPO_SALDO_DEUDOR.getValue())) {            
            return this.getSaldoDeudor();
        } else if (this.getTipoSaldo().equals(Constantes.TIPO_SALDO_ACREEDOR.getValue())) {
            return this.getSaldoAcreedor();
        } else {
            Double __saldoDeudor = this.getSaldoDeudor() == null ? Double.valueOf(0) : this.getSaldoDeudor();
            Double __saldoAcreedor = this.getSaldoAcreedor() == null ? Double.valueOf(0) : this.getSaldoAcreedor();
            if ( __saldoDeudor > __saldoAcreedor ) {
                return __saldoDeudor - __saldoAcreedor;
            } else if ( __saldoAcreedor < __saldoDeudor ) {
                return __saldoAcreedor - __saldoDeudor;
            } else {
                return Double.valueOf(0);
            }
            //throw new IllegalStateException("Error: id cuenta (" + this.getId() + ") no tiene un tipo saldo valido");
        }
    }
}
