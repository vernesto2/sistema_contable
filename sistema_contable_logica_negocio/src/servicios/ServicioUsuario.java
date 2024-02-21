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
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
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

    public void cerrarConexion() {
        try {
            this.cx.getCx().close();
        } catch (SQLException ex) {
            Logger.getLogger(ServicioUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setConexion(Conexion conexion) {
        this.cx = conexion;
        this.daoUsuario = new DaoUsuario(cx);
    }

    public RespuestaGeneral validarUsuario(Usuario usuario, char[] claveSinCifrar) {
        Persona persona = usuario.getPersona();
        if (persona.getNombres().isEmpty()
                || persona.getApellidos().isEmpty()
                || persona.getCarnet().isEmpty()
                || !(persona.getTipo() == Constantes.TIPO_DOCENTE || persona.getTipo() == Constantes.TIPO_ALUMNO)) {
            return RespuestaGeneral.asBadRequest("Persona con datos inválidos");
        }

        if (usuario.getCorreo().isEmpty()
                || usuario.getNombre().isEmpty()
                || usuario.getResetear_clave() == 1
                || usuario.getPregunta1() == 0 || usuario.getRespuesta1().isEmpty()
                || usuario.getPregunta2() == 0 || usuario.getRespuesta2().isEmpty()
                || usuario.getPregunta3() == 0 || usuario.getRespuesta3().isEmpty()) {
            return RespuestaGeneral.asBadRequest("Usuario con datos inválidos");
        }
        return RespuestaGeneral.asOk(null, null);
    }

    public RespuestaGeneral crear(Usuario usuario, char[] claveSinCifrar) throws NoSuchAlgorithmException, InvalidKeySpecException {
        RespuestaGeneral respValidar = validarUsuario(usuario, claveSinCifrar);
        try {
            if (respValidar.esFallida()) {
                return respValidar;
            }
            usuario.setNombre(
                    usuario.getNombre().trim().toLowerCase()
            );
            Map<String, String> obj = cifrarClave(claveSinCifrar);
            usuario.setClave(obj.get("clave"));
            usuario.setSalt(obj.get("salt"));
            usuario.setResetear_clave(Constantes.NO_RESETEAR_CLAVE);
            daoUsuario.insertar(usuario);
            return RespuestaGeneral.asOk("Se guardó correctamente", usuario);
        } catch (Exception e) {
            return RespuestaGeneral.asBadRequest(e.getMessage());
        }

    }

    public RespuestaGeneral actualizar(Usuario usuario, int id) {
        Usuario existente = daoUsuario.obtenerPorCarnet(usuario.getNombre());
        if (existente == null) {
            return RespuestaGeneral.asNotFound("No se encontró", null);
        }
        usuario.setResetear_clave(Constantes.NO_RESETEAR_CLAVE);
        daoUsuario.actualizar(usuario);
        return RespuestaGeneral.asUpdated("Se actualizó correctamente", usuario);
    }

    public Map<String, String> cifrarClave(char[] claveSinCifrar) throws InvalidKeySpecException {
        /* Plain text Password. */

        // generates the Salt value. It can be stored in a database. 
        String saltvalue = PassBasedEnc.getSaltvalue(30);

        /* generates an encrypted password. It can be stored in a database.*/
        String claveCifrada = PassBasedEnc.generateSecurePassword(claveSinCifrar, saltvalue);

        /* Print out plain text password, encrypted password and salt value. */
        System.out.println("Secure password = " + claveCifrada);
        System.out.println("Salt value = " + saltvalue);
        Map<String, String> map = new HashMap<String, String>();
        map.put("clave", claveCifrada);
        map.put("salt", saltvalue);
        return map;
    }

    public RespuestaGeneral obtenerPorCarnet(String carnet) {
        try {
            this.cx.conectar();
            Usuario usuario = daoUsuario.obtenerPorCarnet(carnet);
            this.cx.desconectar();
            if (usuario == null) {
                return RespuestaGeneral.asNotFound("No se encontró el usuario", null);
            }
            return RespuestaGeneral.asOk(null, usuario);
        } catch (Exception ex) {
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
