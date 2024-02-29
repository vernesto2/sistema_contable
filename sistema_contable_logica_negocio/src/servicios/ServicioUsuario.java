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
    Conexion cx;

    public ServicioUsuario(String rutaConexion) {
        setRutaConexion(rutaConexion);
    }
    public ServicioUsuario() {

    }
    public void setRutaConexion(String rutaConexion) {
        this.cx = new Conexion(rutaConexion);
        this.daoUsuario = new DaoUsuario(this.cx);
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

    public RespuestaGeneral crearDocente(Usuario usuario, char[] claveSinCifrar)  {
        RespuestaGeneral respValidar = validarUsuario(usuario, claveSinCifrar);
        try {
            if (respValidar.esFallida()) {
                return respValidar;
            }
            usuario.setNombre(
                    usuario.getNombre().trim().toLowerCase()
            );
            usuario.getPersona().setTipo(Constantes.TIPO_DOCENTE);
            Map<String, String> obj = cifrarClave(claveSinCifrar);
            usuario.setClave(obj.get("clave"));
            usuario.setSalt(obj.get("salt"));
            usuario.setResetear_clave(Constantes.NO_RESETEAR_CLAVE);
            
            this.cx.desconectar();
            daoUsuario.insertar(usuario);
            return RespuestaGeneral.asOk("Se guardó correctamente", usuario);
        } catch (Exception e) {
            return RespuestaGeneral.asBadRequest(e.getMessage());
        }
        finally {
            this.cx.desconectar();
        }
    }
    
    public RespuestaGeneral sePuedeCrearAlumno() {
        try {
            this.cx.conectar();
            int contarAlumnos = daoUsuario.contarAlumnos();
            if(contarAlumnos != 0) {
                return RespuestaGeneral.asBadRequest("Esta base de datos no soporta más alumnos");
            }
            return RespuestaGeneral.asOk("Esta base de datos si soporta 1 alumno", null);
        } catch (Exception e) {
            return RespuestaGeneral.asServerError(e.getMessage());
        } finally {
            this.cx.desconectar();
        }
        
    }
    
    public RespuestaGeneral crearAlumno(Usuario usuario, char[] claveSinCifrar) {
        RespuestaGeneral respValidar = validarUsuario(usuario, claveSinCifrar);
        try {
            if (respValidar.esFallida()) {
                return respValidar;
            }
            //sanear 
            usuario.setNombre(
                    usuario.getNombre().trim().toLowerCase()
            );
            
            usuario.getPersona().setTipo(Constantes.TIPO_ALUMNO);
            RespuestaGeneral respAlumno = sePuedeCrearAlumno();
            if(respAlumno.esFallida()) {
                return respAlumno;
            }
            
            Map<String, String> obj = cifrarClave(claveSinCifrar);
            usuario.setClave(obj.get("clave"));
            usuario.setSalt(obj.get("salt"));
            usuario.setResetear_clave(Constantes.NO_RESETEAR_CLAVE);
            this.cx.conectar();
            daoUsuario.insertar(usuario);
            return RespuestaGeneral.asOk("Se guardó correctamente", usuario);
        } catch (Exception e) {
            return RespuestaGeneral.asBadRequest(e.getMessage());
        } finally {
            this.cx.desconectar();
        }
    }
    
    public RespuestaGeneral actualizar(Usuario usuario, int id) {
        try {
            this.cx.conectar();
            Usuario existente = daoUsuario.obtenerPorCarnet(usuario.getNombre());
            if (existente == null) {
                return RespuestaGeneral.asNotFound("No se encontró", null);
            }
            usuario.setResetear_clave(Constantes.NO_RESETEAR_CLAVE);
            daoUsuario.actualizar(usuario);
            return RespuestaGeneral.asUpdated("Se actualizó correctamente", usuario);
        } catch (Exception e) {
            return RespuestaGeneral.asBadRequest(e.getMessage());
        } finally {
            this.cx.desconectar();
        }
    }
    
    public RespuestaGeneral actualizarClave(Usuario usuario, char [] claveSinCifrarActual, char [] claveSinCifrarNueva) {
        try {
            this.cx.conectar();
            RespuestaGeneral respConciden = coinciden(claveSinCifrarActual, usuario.getClave(), usuario.getSalt());
            Boolean coinciden = (Boolean) respConciden.getDatos();
            if (coinciden == false) {
                return RespuestaGeneral.asBadRequest("La clave actual proporcionada no es correcta");
            }
            Map<String, String> obj = cifrarClave(claveSinCifrarNueva, usuario.getSalt());
            daoUsuario.actualizarClave(usuario, obj.get("clave"));
            return RespuestaGeneral.asOk("Se actualizó exitosamente", null);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(ServicioUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return RespuestaGeneral.asBadRequest(ex.getMessage());
        } finally {
            this.cx.conectar();
        }
    }
    
    private Map<String, String> cifrarClave(char[] claveSinCifrar) throws  InvalidKeySpecException {
        // generates the Salt value. It can be stored in a database. 
        String saltvalue = PassBasedEnc.getSaltvalue(30);
        return cifrarClave(claveSinCifrar, saltvalue);
    }

    private Map<String, String> cifrarClave(char[] claveSinCifrar, String saltvalue) throws InvalidKeySpecException {
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
