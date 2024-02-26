/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesion;

import modelo.ConfiguracionUsuario;
import modelo.Usuario;

/**
 *
 * @author student
 */
public class Sesion {
    public Usuario usuario;
    public ConfiguracionUsuario configUsuario;
    public String rutaConexion;
    public Sesion(Usuario usuario, ConfiguracionUsuario configUsuario, String rutaConexion) {
        this.usuario = usuario;
        this.configUsuario = configUsuario;
        this.rutaConexion = rutaConexion;
    }
}
