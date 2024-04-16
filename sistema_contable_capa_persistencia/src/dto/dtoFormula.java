/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import modelo.Formula;

/**
 *
 * @author vacev
 */
public class dtoFormula implements Comparable<dtoFormula>{
    
    Formula formula;
    Formula formulaPadre;

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
