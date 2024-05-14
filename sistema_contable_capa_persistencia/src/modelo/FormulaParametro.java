/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author vacev
 */
public class FormulaParametro {
    
    int id;
    int id_formula;
    int id_ciclo_contable;
    double valor;
    
    Formula formula;
    CicloContable cicloContable;
    int eliminado;

    public FormulaParametro() {
        this.id = -1;
        this.id_formula = -1;
        this.id_ciclo_contable = -1;
        this.valor = 0.0;
        this.formula = new Formula();
        this.cicloContable = new CicloContable();
        this.eliminado = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_formula() {
        return id_formula;
    }

    public void setId_formula(int id_formula) {
        this.id_formula = id_formula;
    }

    public int getId_ciclo_contable() {
        return id_ciclo_contable;
    }

    public void setId_ciclo_contable(int id_ciclo_contable) {
        this.id_ciclo_contable = id_ciclo_contable;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public CicloContable getCicloContable() {
        return cicloContable;
    }

    public void setCicloContable(CicloContable cicloContable) {
        this.cicloContable = cicloContable;
    }

    public int getEliminado() {
        return eliminado;
    }

    public void setEliminado(int eliminado) {
        this.eliminado = eliminado;
    }
}
