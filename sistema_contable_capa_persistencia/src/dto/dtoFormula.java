/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.List;
import modelo.Formula;
import utils.constantes.Constantes;

/**
 *
 * @author vacev
 */
public class dtoFormula implements Comparable<dtoFormula>{
    
    Formula formula;
    Formula formulaPadre;
    List<dtoFormula> hijas;

    public dtoFormula(Formula formula, Formula formulaPadre, List<dtoFormula> hijas) {
        this.formula = formula;
        this.formulaPadre = formulaPadre;
        this.hijas = hijas;
    }

    public dtoFormula() {
        this.formula = new Formula();
        this.formulaPadre = new Formula();
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
    public void agregarHija(dtoFormula hija) {
        hijas.add(hija);
    }
   
    public List<dtoFormula> getHijas() {
        return hijas;
    }

    public void setHijas(List<dtoFormula> hijas) {
        this.hijas = hijas;
    }

    public boolean tieneHijas() {
        return hijas == null ? false : hijas.size() > 0;
    }
    
    //sumarle o restarle al acumulado el valor actual de la formula
    public Double operar(Double valorFormula, Double acumulado) {
        if (this.formula.getSigno().equals(Constantes.SIGNO_MAS.getValue())) {
            acumulado += valorFormula;
        } else if(this.formula.getSigno().equals(Constantes.SIGNO_MENOS.getValue())) {
            acumulado += valorFormula;
        }
        return acumulado;
    }
    
    @Override
    public int compareTo(dtoFormula other) {
        return comparePositions(formula.getPosicion(), other.getFormula().getPosicion());
    }

    private int comparePositions(String position1, String position2) {
        String[] parts1 = position1.split("\\.");
        String[] parts2 = position2.split("\\.");

        int minLength = Math.min(parts1.length, parts2.length);

        for (int i = 0; i < minLength; i++) {
            int part1 = Integer.parseInt(parts1[i]);
            int part2 = Integer.parseInt(parts2[i]);

            if (part1 != part2) {
                return part1 - part2;
            }
        }

        return parts1.length - parts2.length;
    }
    
    
}
