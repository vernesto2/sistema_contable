/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import utils.constantes.Constantes;

/**
 *
 * @author vacev
 */
public class Usuario {
    
    int id;
    int id_persona;
    String nombre; 
    String correo;
    String salt;
    String clave;
    int resetear_clave;
    int pregunta1;
    String respuesta1;
    int pregunta2;
    String respuesta2;
    int pregunta3;
    String respuesta3;
    Persona persona; 
    public Usuario(int id, int id_persona, String nombre, String correo, String salt, String clave, int resetear_clave, int pregunta1, String respuesta1, int pregunta2, String respuesta2, int pregunta3, String respuesta3) {
        this.id = id;
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.correo = correo;
        this.salt = salt;
        this.clave = clave;
        this.resetear_clave = resetear_clave;
        this.pregunta1 = pregunta1;
        this.respuesta1 = respuesta1;
        this.pregunta2 = pregunta2;
        this.respuesta2 = respuesta2;
        this.pregunta3 = pregunta3;
        this.respuesta3 = respuesta3;
    }
    public Usuario() {
        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getResetear_clave() {
        return resetear_clave;
    }

    public void setResetear_clave(int resetear_clave) {
        this.resetear_clave = resetear_clave;
    }

    public int getPregunta1() {
        return pregunta1;
    }

    public void setPregunta1(int pregunta1) {
        this.pregunta1 = pregunta1;
    }

    public String getRespuesta1() {
        return respuesta1;
    }

    public void setRespuesta1(String respuesta1) {
        this.respuesta1 = respuesta1;
    }

    public int getPregunta2() {
        return pregunta2;
    }

    public void setPregunta2(int pregunta2) {
        this.pregunta2 = pregunta2;
    }

    public String getRespuesta2() {
        return respuesta2;
    }

    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public int getPregunta3() {
        return pregunta3;
    }

    public void setPregunta3(int pregunta3) {
        this.pregunta3 = pregunta3;
    }

    public String getRespuesta3() {
        return respuesta3;
    }

    public void setRespuesta3(String respuesta3) {
        this.respuesta3 = respuesta3;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    
}
