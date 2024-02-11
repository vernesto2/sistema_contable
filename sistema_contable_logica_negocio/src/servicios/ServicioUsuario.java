/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import conexion.Conexion;
import dao.DaoUsuario;
import modelo.Persona;
import modelo.Usuario;
import utils.constantes.Constantes;

/**
 *
 * @author student
 */
public class ServicioUsuario {
    DaoUsuario daoUsuario;
    Conexion cx = new Conexion();
    public ServicioUsuario () {
        this.daoUsuario = new DaoUsuario(this.cx);
    }
    public void crear(Usuario usuario, char[] claveSinCifrar) {
        Persona persona = usuario.getPersona();
        if(persona.getNombres().isEmpty()
                || persona.getApellidos().isEmpty()
                || persona.getCarnet().isEmpty()
                || (persona.getTipo() != Constantes.TIPO_DOCENTE || persona.getTipo() != Constantes.TIPO_ALUMNO) ) {
            throw new IllegalStateException("Persona con datos inválidos");
        }
        
        usuario.setNombre(
                usuario.getNombre().toString().toLowerCase()
        );
        
        //pendiente, hay que cifrar clave
        usuario.setClave(claveSinCifrar.toString());
        
        if(usuario.getCorreo().isEmpty() 
                || usuario.getClave().isEmpty() 
                || usuario.getNombre().isEmpty()
                || usuario.getResetear_clave() == 1
                || usuario.getPregunta1() == 0 || usuario.getRespuesta1().isEmpty()
                || usuario.getPregunta2() == 0 || usuario.getRespuesta2().isEmpty()
                || usuario.getPregunta3() == 0 || usuario.getRespuesta3().isEmpty()) {
            throw new IllegalStateException("Usuario con datos inválidos");
        }
        
        usuario.setResetear_clave(Constantes.NO_RESETEAR_CLAVE);
        daoUsuario.insertar(usuario);
    }
}
