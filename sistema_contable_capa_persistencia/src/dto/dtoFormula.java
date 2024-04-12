/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Comparator;
import modelo.Formula;

/**
 *
 * @author vacev
 */
public class dtoFormula implements Comparator<dtoFormula>{
    
    Formula formula;
    Formula formulaPadre;

    public dtoFormula() {
        this.formula = new Formula();
        this.formulaPadre = new Formula();
    }
    
    @Override
    public int compare(dtoFormula dto1, dtoFormula dto2) {
        Formula f1 = dto1.formula;
        Formula f2 = dto2.formula;

        if (f1.getPosicion() != f2.getPosicion()) {
            return Double.compare(f1.getPosicion(), f2.getPosicion());
        } else if (f1.getId_formula() != -1 && f1.getId_formula() == f2.getId_formula()) {
            // Si los dos tienen el mismo id_formula, entonces f1 debería estar debajo de f2
            return 1;
        } else {
            return Double.compare(f1.getId(), f2.getId());
        }
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public Formula getFormulaPadre() {
        return formulaPadre;
    }

    public void setFormulaPadre(Formula formulaPadre) {
        this.formulaPadre = formulaPadre;
    }
    
    
}
