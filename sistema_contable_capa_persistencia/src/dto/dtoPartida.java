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
    
    double monto;

    public dtoPartida(int id, int id_ciclo, int id_tipo_partida, String num_partida, String comentario, Date fecha, boolean eliminado, float monto, int folio, String hora) {
        super(id, id_ciclo, id_tipo_partida, num_partida, comentario, fecha, eliminado, folio, hora);
        this.monto = monto;
    }

    public dtoPartida() {
        this.monto = 0;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
}
