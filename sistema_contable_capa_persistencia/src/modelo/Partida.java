/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author vacev
 */
public class Partida {
    
    int id;
    int id_ciclo;
    int id_tipo_partida;
    int num_partida;
    String comentario;
    Date fecha;
    boolean eliminado;

    public Partida(int id, int id_ciclo, int id_tipo_partida, int num_partida, String comentario, Date fecha, boolean eliminado) {
        this.id = id;
        this.id_ciclo = id_ciclo;
        this.id_tipo_partida = id_tipo_partida;
        this.num_partida = num_partida;
        this.comentario = comentario;
        this.fecha = fecha;
        this.eliminado = eliminado;
    }
    
    public Partida() {
        this.id = -1;
        this.id_ciclo = -1;
        this.id_tipo_partida = -1;
        this.num_partida = 0;
        this.comentario = "";
        this.fecha = new Date();
        this.eliminado = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_ciclo() {
        return id_ciclo;
    }

    public void setId_ciclo(int id_ciclo) {
        this.id_ciclo = id_ciclo;
    }

    public int getId_tipo_partida() {
        return id_tipo_partida;
    }

    public void setId_tipo_partida(int id_tipo_partida) {
        this.id_tipo_partida = id_tipo_partida;
    }

    public int getNum_partida() {
        return num_partida;
    }

    public void setNum_partida(int num_partida) {
        this.num_partida = num_partida;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
}
