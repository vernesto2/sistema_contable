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
    private String nombre;
    private String codigo;
    private Double saldoInicial;
    private Double totalDebe;
    private Double totalHaber;
    private Double saldoFinal;
    public CuentaBalanza() {
        
    }
    public CuentaBalanza(Integer id, String nombre, String codigo, Double saldoInicial, Double totalDebe, Double totalHaber, Double saldoFinal) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.saldoInicial = saldoInicial;
        this.totalDebe = totalDebe;
        this.totalHaber = totalHaber;
        this.saldoFinal = saldoFinal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getTotalDebe() {
        return totalDebe;
    }

    public void setTotalDebe(Double totalDebe) {
        this.totalDebe = totalDebe;
    }

    public Double getTotalHaber() {
        return totalHaber;
    }

    public void setTotalHaber(Double totalHaber) {
        this.totalHaber = totalHaber;
    }

    public Double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(Double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    
}
