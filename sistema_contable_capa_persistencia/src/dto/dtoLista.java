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
    
    String value;
    String label;

    public dtoLista(String value, String label) {
        this.value = value;
        this.label = label;
    }
    
    public dtoLista() {
        this.value = "";
        this.label = "";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
