/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import conexion.Conexion;
import dao.DaoUsuario;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import modelo.Persona;
import modelo.Usuario;
import utils.constantes.Constantes;
import utils.cifrado.PassBasedEnc;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author student
 */
public class ServicioUsuario {

    DaoUsuario daoUsuario;
    Conexion cx = new Conexion();

    public ServicioUsuario() {
        this.daoUsuario = new DaoUsuario(this.cx);
    }

    public RespuestaGeneral crear(Usuario usuario, char[] claveSinCifrar) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Persona persona = usuario.getPersona();
        if (persona.getNombres().isEmpty()
                || persona.getApellidos().isEmpty()
                || persona.getCarnet().isEmpty()
                || !(persona.getTipo() == Constantes.TIPO_DOCENTE || persona.getTipo() == Constantes.TIPO_ALUMNO)) {
            throw new IllegalStateException("Persona con datos inválidos");
        }

        usuario.setNombre(
                usuario.getNombre().toString().toLowerCase()
        );
        
        this.cifrarClave(usuario, claveSinCifrar);

        if (usuario.getCorreo().isEmpty()
                || usuario.getClave().isEmpty()
                || usuario.getNombre().isEmpty()
                || usuario.getResetear_clave() == 1
                || usuario.getPregunta1() == 0 || usuario.getRespuesta1().isEmpty()
                || usuario.getPregunta2() == 0 || usuario.getRespuesta2().isEmpty()
                || usuario.getPregunta3() == 0 || usuario.getRespuesta3().isEmpty()) {
            throw new IllegalStateException("Usuario con datos inválidos");
        }

        usuario.setResetear_clave(Constantes.NO_RESETEAR_CLAVE);
        this.cx.conectar();
        daoUsuario.insertar(usuario);
        this.cx.desconectar();
        return RespuestaGeneral.asOk("Se guardó correctamente", usuario);
    }

    public void cifrarClave(Usuario usuario, char[] claveSinCifrar) throws InvalidKeySpecException {
        /* Plain text Password. */

        // generates the Salt value. It can be stored in a database. 
        String saltvalue = PassBasedEnc.getSaltvalue(30);

        /* generates an encrypted password. It can be stored in a database.*/
        String claveCifrada = PassBasedEnc.generateSecurePassword(claveSinCifrar, saltvalue);

        /* Print out plain text password, encrypted password and salt value. */
        System.out.println("Secure password = " + claveCifrada);
        System.out.println("Salt value = " + saltvalue);

        usuario.setClave(claveCifrada);
        usuario.setSalt(saltvalue);
    }
    public RespuestaGeneral obtenerPorCarnet(String carnet) {
        try {
            this.cx.conectar();
            Usuario usuario = daoUsuario.obtenerPorCarnet(carnet);
            this.cx.desconectar();
            if(usuario == null) {
                return RespuestaGeneral.asNotFound("No se encontró el usuario", null);
            }
            return RespuestaGeneral.asOk(null, usuario);
        } catch(Exception   ex) {
            return RespuestaGeneral.asNotFound("No se encontró el usuario", null);
        }
    }

    public RespuestaGeneral coinciden(char[] claveSinCifrar, String claveCifrada, String salt) {
        try {
            /* verify the original password and encrypted password */
            Boolean status = PassBasedEnc.verifyUserPassword(claveSinCifrar, claveCifrada, salt);
            return RespuestaGeneral.asOk(null, status);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(ServicioUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return RespuestaGeneral.asBadRequest(ex.getMessage());
        }

    }
}
