/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils.constantes;

/**
 *
 * @author vacev
 */
public class RespuestaGeneral {
    
     private String mensaje;
    // codigo es para el codigo http
    private int codigo;
    // datos puede ser 1 objeto, una lista de cualquier tipo de dato
    private Object datos;
    
    public static final int CRUD_400_BadRequest = 400;
    public static final int CRUD_401_Unauthorized = 401;
    public static final int CRUD_402_Forbidden = 402;
    public static final int CRUD_404_NotFOund = 404;
    public static final int CRUD_200_Ok = 200;
    public static final int CRUD_201_Created = 201;

    private RespuestaGeneral(int codigo, String mensaje, Object datos) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.datos = datos;
    }

    public RespuestaGeneral() {
        this.codigo = CRUD_400_BadRequest;
        this.mensaje = null;
        this.datos = null;
    }

    public boolean esFallida() {
        return this.codigo != CRUD_200_Ok && this.codigo != CRUD_201_Created;
    }
    public boolean esExitosa() {
        return this.codigo == CRUD_200_Ok || this.codigo == CRUD_201_Created;
    }
    public static final RespuestaGeneral asBadRequest(String mensaje, Object datos) {
        return new RespuestaGeneral(RespuestaGeneral.CRUD_400_BadRequest, mensaje, datos);
    }

    public static final RespuestaGeneral asBadRequest(String mensaje) {
        return new RespuestaGeneral(RespuestaGeneral.CRUD_400_BadRequest, mensaje, null);
    }

    public static final RespuestaGeneral asForbidden(String mensaje, Object datos) {
        return new RespuestaGeneral(RespuestaGeneral.CRUD_402_Forbidden, mensaje, datos);
    }
    public static final RespuestaGeneral asUnauthorized(String mensaje, Object datos) {
        return new RespuestaGeneral(RespuestaGeneral.CRUD_401_Unauthorized, mensaje, datos);
    }
    public static final RespuestaGeneral asNotFound(String mensaje, Object datos) {
        return new RespuestaGeneral(RespuestaGeneral.CRUD_404_NotFOund, mensaje, datos);
    }

    public static final RespuestaGeneral asOk(String mensaje, Object datos) {
        return new RespuestaGeneral(RespuestaGeneral.CRUD_200_Ok, mensaje, datos);
    }

    public static final RespuestaGeneral asCreated(String mensaje, Object datos) {
        return new RespuestaGeneral(RespuestaGeneral.CRUD_201_Created, mensaje, datos);
    }

    public String getMensaje() {
        return mensaje;
    }

    public int getCodigo() {
        return codigo;
    }

    public Object getDatos() {
        return datos;
    }
    
    
}
