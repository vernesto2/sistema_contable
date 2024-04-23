/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportes;

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
    private Double saldoDeudor;
    private Double saldoAcreedor;
    
    public CuentaBalanza() {
        
    }

    public CuentaBalanza(Integer id, String tipoSaldo, Integer folioMayor, String nombre, String codigo, Double saldoDeudor, Double saldoAcreedor) {
        this.id = id;
        this.tipoSaldo = tipoSaldo;
        this.folioMayor = folioMayor;
        this.nombre = nombre;
        this.codigo = codigo;
        this.saldoDeudor = saldoDeudor;
        this.saldoAcreedor = saldoAcreedor;
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


}
