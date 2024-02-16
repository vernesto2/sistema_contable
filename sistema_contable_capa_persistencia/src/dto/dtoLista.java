/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author vacev
 */
public class dtoLista {
    
    int value;
    String label;

    public dtoLista(int value, String label) {
        this.value = value;
        this.label = label;
    }
    
    public dtoLista() {
        this.value = 0;
        this.label = "";
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
