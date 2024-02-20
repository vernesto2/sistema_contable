/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author vacev
 */
public class ConfiguracionUsuario {
    int id;
    int id_usuario;
    int id_ciclo_contable;
    String avatar;
    String nombre_ciclo_contable;
    String nombre_catalogo;

    public ConfiguracionUsuario(int id, int id_usuario, int id_ciclo_contable, String avatar, String nombre_ciclo_contable, String nombre_catalogo) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_ciclo_contable = id_ciclo_contable;
        this.avatar = avatar;
        this.nombre_ciclo_contable = nombre_ciclo_contable;
        this.nombre_catalogo = nombre_catalogo;
    }
    
    public ConfiguracionUsuario() {
        this.id = -1;
        this.id_usuario = -1;
        this.id_ciclo_contable = -1;
        this.avatar = "/utils/avatar/avatar2.png";
        this.nombre_ciclo_contable = "";
        this.nombre_catalogo = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_ciclo_contable() {
        return id_ciclo_contable;
    }

    public void setId_ciclo_contable(int id_ciclo_contable) {
        this.id_ciclo_contable = id_ciclo_contable;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNombre_ciclo_contable() {
        return nombre_ciclo_contable;
    }

    public void setNombre_ciclo_contable(String nombre_ciclo_contable) {
        this.nombre_ciclo_contable = nombre_ciclo_contable;
    }

    public String getNombre_catalogo() {
        return nombre_catalogo;
    }

    public void setNombre_catalogo(String nombre_catalogo) {
        this.nombre_catalogo = nombre_catalogo;
    }
    
    public String nombreCicloYCatalogo() {
        String titulo = this.nombre_ciclo_contable + " ( " + this.nombre_catalogo + " ) ";
        return titulo;
    }
}
