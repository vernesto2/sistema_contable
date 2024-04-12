/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author vacev
 */
public class Formula {
    
    int id;
    int id_tipo_catalogo;
    int id_cuenta;
    String signo;
    String nombre;
    int tipo_cuenta_especial;
    protected double posicion;
    int id_formula;
    String tipo_formula;
    int eliminado;
    
    TipoCatalogo tipoCatalogo;
    Cuenta cuenta;
    
    public Formula() {
        this.id = -1;
        this.id_tipo_catalogo = -1;
        this.id_cuenta = -1;
        this.signo = "(+)";
        this.nombre = "";
        this.tipo_cuenta_especial = -1;
        this.posicion = 0.0;
        this.id_formula = -1;
        this.tipo_formula = "Estado de Resultado";
        this.tipoCatalogo = new TipoCatalogo();
        this.cuenta = new Cuenta();
        this.eliminado = 0;
    }
    
    @Override
    public String toString() {
        return "Formula{" +
                "id=" + id +
                ", posicion=" + posicion +
                ", id_formula=" + id_formula +
                ", formulaPadre=" + id_formula +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_tipo_catalogo() {
        return id_tipo_catalogo;
    }

    public void setId_tipo_catalogo(int id_tipo_catalogo) {
        this.id_tipo_catalogo = id_tipo_catalogo;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipo_cuenta_especial() {
        return tipo_cuenta_especial;
    }

    public void setTipo_cuenta_especial(int tipo_cuenta_especial) {
        this.tipo_cuenta_especial = tipo_cuenta_especial;
    }

    public double getPosicion() {
        return posicion;
    }

    public void setPosicion(double posicion) {
        this.posicion = posicion;
    }

    public int getId_formula() {
        return id_formula;
    }

    public void setId_formula(int id_formula) {
        this.id_formula = id_formula;
    }

    public String getTipo_formula() {
        return tipo_formula;
    }

    public void setTipo_formula(String tipo_formula) {
        this.tipo_formula = tipo_formula;
    }

    public TipoCatalogo getTipoCatalogo() {
        return tipoCatalogo;
    }

    public void setTipoCatalogo(TipoCatalogo tipoCatalogo) {
        this.tipoCatalogo = tipoCatalogo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public int getEliminado() {
        return eliminado;
    }

    public void setEliminado(int eliminado) {
        this.eliminado = eliminado;
    }
    
}
