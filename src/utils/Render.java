/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author vacev
 */
public class Render extends DefaultTableCellRenderer{
    private int columna;
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof RSMaterialComponent.RSButtonCustomIcon) {
            RSMaterialComponent.RSButtonCustomIcon btn = (RSMaterialComponent.RSButtonCustomIcon)value;
            return btn;
        }
        
        if (table.getValueAt(row,getColumna()).equals("C")){
            this.setForeground(Color.BLACK);
        } else if(table.getValueAt(row,getColumna()).equals("A")){
            this.setForeground(Color.DARK_GRAY);
        }
        
        if(table.getValueAt(row,getColumna()).equals("No")){
            this.setBackground(Color.getHSBColor(25, 35, 35));
        } else if(table.getValueAt(row,getColumna()).equals("Si")){
            this.setBackground(Color.white);
        }
        
        if(table.getValueAt(row,getColumna()).equals("Por defecto")){
            this.setBackground(Color.getHSBColor(25, 35, 35));
        } else if(table.getValueAt(row,getColumna()).equals("-")){
            this.setBackground(Color.white);
        }
        
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
    
}
