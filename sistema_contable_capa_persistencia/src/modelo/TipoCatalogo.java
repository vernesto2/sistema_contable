/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author vacev
 */
public class TipoCatalogo {
    
    int id;
    String tipo;
    String ref;
    boolean eliminado;
    int color;
    int libro_diario;
    int libro_mayor;
    int balanza_comprobacion;
    int estado_resultado;
    int balance_general;
    int flujo_efectivo;
    int cambios_patrimonio;

    public TipoCatalogo(int id, String tipo, String ref, boolean eliminado, int color, int libro_diario, int libro_mayor, int balanza_comprobacion, int estado_resultado, int balance_general, int flujo_efectivo, int cambios_patrimonio) {
        this.id = id;
        this.tipo = tipo;
        this.ref = ref;
        this.eliminado = eliminado;
        this.color = color;
        this.libro_diario = libro_diario;
        this.libro_mayor = libro_mayor;
        this.balanza_comprobacion = balanza_comprobacion;
        this.estado_resultado = estado_resultado;
        this.balance_general = balance_general;
        this.flujo_efectivo = flujo_efectivo;
        this.cambios_patrimonio = cambios_patrimonio;
    }

    public TipoCatalogo() {
        this.id = -1;
        this.tipo = "";
        this.ref = "";
        this.eliminado = false;
        this.color = 0;
        this.libro_diario = 1;
        this.libro_mayor = 0;
        this.balanza_comprobacion = 0;
        this.estado_resultado = 0;
        this.balance_general = 0;
        this.flujo_efectivo = 0;
        this.cambios_patrimonio = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getLibro_diario() {
        return libro_diario;
    }

    public void setLibro_diario(int libro_diario) {
        this.libro_diario = libro_diario;
    }

    public int getLibro_mayor() {
        return libro_mayor;
    }

    public void setLibro_mayor(int libro_mayor) {
        this.libro_mayor = libro_mayor;
    }

    public int getBalanza_comprobacion() {
        return balanza_comprobacion;
    }

    public void setBalanza_comprobacion(int balanza_comprobacion) {
        this.balanza_comprobacion = balanza_comprobacion;
    }

    public int getEstado_resultado() {
        return estado_resultado;
    }

    public void setEstado_resultado(int estado_resultado) {
        this.estado_resultado = estado_resultado;
    }

    public int getBalance_general() {
        return balance_general;
    }

    public void setBalance_general(int balance_general) {
        this.balance_general = balance_general;
    }

    public int getFlujo_efectivo() {
        return flujo_efectivo;
    }

    public void setFlujo_efectivo(int flujo_efectivo) {
        this.flujo_efectivo = flujo_efectivo;
    }

    public int getCambios_patrimonio() {
        return cambios_patrimonio;
    }

    public void setCambios_patrimonio(int cambios_patrimonio) {
        this.cambios_patrimonio = cambios_patrimonio;
    }
    
}
