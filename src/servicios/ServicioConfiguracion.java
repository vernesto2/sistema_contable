/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import modelo.Usuario;

/**
 *
 * @author student
 */
public class ServicioConfiguracion {
    
    ServicioUsuario _servicioUsuario;
    
    public void crear(Usuario usuario, char [] claveSinCifrar) {
        //ejecutar el código SQL inicial
        
        //crear el nuevo usuario
        _servicioUsuario.crear(usuario, claveSinCifrar);
    }
}
