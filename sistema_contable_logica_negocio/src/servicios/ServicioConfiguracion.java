/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author student
 */
public class ServicioConfiguracion {
    
    ServicioUsuario _usuario;
    public ServicioConfiguracion() {
        _usuario = new ServicioUsuario();
    }
    public ServicioConfiguracion(ServicioUsuario _usuario) {
        this._usuario = _usuario;
    }
    
    public RespuestaGeneral crear(Usuario usuario, char [] claveSinCifrar) {
        try {
            //ejecutar el código SQL inicial
            
            //crear el nuevo usuario
            return _usuario.crear(usuario, claveSinCifrar);
        } catch (NoSuchAlgorithmException ex) {
            return RespuestaGeneral.asBadRequest(ex.getMessage());
        } catch (InvalidKeySpecException ex) {
            return RespuestaGeneral.asBadRequest(ex.getMessage());
        }
    }   
}
