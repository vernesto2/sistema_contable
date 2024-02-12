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
    
    public static final int CRUD_500_ServerError = 500;
    public static final int CRUD_400_BadRequest = 400;
    public static final int CRUD_401_Unauthorized = 401;
    public static final int CRUD_402_Forbidden = 402;
    public static final int CRUD_404_NotFOund = 404;
    public static final int CRUD_200_Ok = 200;
    public static final int CRUD_201_Created = 201;
    public static final int CRUD_202_Updated = 202;
    
    public static final String GUARDADO_CORRECTAMENTE = "¡GUARDADO CORRECTAMENTE!";
    public static final String ACTUALIZADO_CORRECTAMENTE = "¡ACTUALIZADO CORRECTAMENTE!";
    public static final String ELIMINADO_CORRECTAMENTE = "¡ELIMINADO CORRECTAMENTE!";

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
        return this.codigo == CRUD_200_Ok || this.codigo == CRUD_201_Created || this.codigo == CRUD_202_Updated;
    }
    
    public static final RespuestaGeneral asServerError(String mensaje, Object datos) {
        return new RespuestaGeneral(RespuestaGeneral.CRUD_500_ServerError, mensaje, datos);
    }

    public static final RespuestaGeneral asServerError(String mensaje) {
        return new RespuestaGeneral(RespuestaGeneral.CRUD_500_ServerError, mensaje, null);
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
    
    public static final RespuestaGeneral asUpdated(String mensaje, Object datos) {
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

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
}
