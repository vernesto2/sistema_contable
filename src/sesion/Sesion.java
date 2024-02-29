/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesion;

import modelo.ConfiguracionUsuario;
import modelo.Usuario;
import utils.constantes.Constantes;

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
    
    public boolean esAlumno() {
        return usuario.getPersona().getTipo() == Constantes.TIPO_ALUMNO;
    }
    
    public boolean esDocente() {
        return usuario.getPersona().getTipo() == Constantes.TIPO_DOCENTE;
    }
    
    public int tipoUsuario() {
        return usuario.getPersona().getTipo();
    }


    public Object clone() throws CloneNotSupportedException {
        return super.clone(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
 
}
