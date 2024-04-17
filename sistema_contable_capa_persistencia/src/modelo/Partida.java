/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author vacev
 */
public class Partida {
    
    int id;
    int id_ciclo;
    int id_tipo_partida;
    String num_partida;
    String comentario;
    Date fecha;
    boolean eliminado;
    int folio;
    String hora;
    
    ArrayList<PartidaDetalle> listaPartidaDetalles;
    ArrayList<PartidaDetalle> listaPartidaDetallesEliminados;

    public Partida(int id, int id_ciclo, int id_tipo_partida, String num_partida, String comentario, Date fecha, boolean eliminado, int folio, String hora) {
        this.id = id;
        this.id_ciclo = id_ciclo;
        this.id_tipo_partida = id_tipo_partida;
        this.num_partida = num_partida;
        this.comentario = comentario;
        this.fecha = fecha;
        this.eliminado = eliminado;
        this.folio = folio;
        this.hora = hora;
    }
    
    public Partida() {
        this.id = -1;
        this.id_ciclo = -1;
        this.id_tipo_partida = -1;
        this.num_partida = "0";
        this.comentario = "";
        this.fecha = new Date();
        this.eliminado = false;
        this.folio = 0;
        this.hora = "08:00";
        
        this.listaPartidaDetalles = new ArrayList<>();
        this.listaPartidaDetallesEliminados = new ArrayList<>();
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

    public String getNum_partida() {
        return num_partida;
    }

    public void setNum_partida(String num_partida) {
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

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public ArrayList<PartidaDetalle> getListaPartidaDetalles() {
        return listaPartidaDetalles;
    }

    public void setListaPartidaDetalles(ArrayList<PartidaDetalle> listaPartidaDetalles) {
        this.listaPartidaDetalles = listaPartidaDetalles;
    }

    public ArrayList<PartidaDetalle> getListaPartidaDetallesEliminados() {
        return listaPartidaDetallesEliminados;
    }

    public void setListaPartidaDetallesEliminados(ArrayList<PartidaDetalle> listaPartidaDetallesEliminados) {
        this.listaPartidaDetallesEliminados = listaPartidaDetallesEliminados;
    }
    
}
