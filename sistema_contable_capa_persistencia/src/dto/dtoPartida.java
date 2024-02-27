/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Date;
import modelo.Partida;

/**
 *
 * @author vacev
 */
public class dtoPartida extends Partida{
    
    float monto;

    public dtoPartida(int id, int id_ciclo, int id_tipo_partida, int num_partida, String comentario, Date fecha, boolean eliminado, float monto) {
        super(id, id_ciclo, id_tipo_partida, num_partida, comentario, fecha, eliminado);
        this.monto = monto;
    }

    public dtoPartida() {
        this.monto = 0;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
    
}
