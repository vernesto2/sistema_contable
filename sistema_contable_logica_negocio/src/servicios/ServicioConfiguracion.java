/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import java.sql.Statement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import modelo.Usuario;
import utils.constantes.Constantes;
import utils.constantes.RespuestaGeneral;

/**
 *
 * @author student
 */
public class ServicioConfiguracion {

    public RespuestaGeneral crear(Usuario usuario, char[] claveSinCifrar)  {
        try {
            ServicioUsuario _usuario = new ServicioUsuario(null);
            RespuestaGeneral respValidarUsuario = _usuario.validarUsuario(usuario, claveSinCifrar);
            if (respValidarUsuario.esFallida()) {
                return respValidarUsuario;
            }
            //ejecutar el código SQL inicial
            String fileName = crearConfiguracionInicial();
            
            _usuario.setRutaConexion(fileName);
            //crear el nuevo usuario
            RespuestaGeneral rgUsuario = _usuario.crearDocente(usuario, claveSinCifrar);
            return rgUsuario;
        } catch (IOException ex) {
            return RespuestaGeneral.asBadRequest(ex.getMessage());
        } catch (SQLException ex) {
            return RespuestaGeneral.asBadRequest(ex.getMessage());
        }
    }

    private String crearConfiguracionInicial() throws IOException, SQLException {
        String sql = leerConfiguracionInicial();
        String strFechaHoraActual = fechaAFormatoPreferido(LocalDateTime.now());
        String fileName = "database/db-" + strFechaHoraActual + ".sqlite";
        try (Connection connection = conectar(fileName)) {
            ejecutarSQL(connection, sql);
            ejecutarSQL(connection, """
CREATE VIEW vw_cargo_abono as 
select 
	null as id_partida,
	cb.id_ciclo_contable, 
	cb.id_cuenta, 
	case tipo_saldo
		when 'D' then saldo_inicial
		when 'A' then 0
		else 0 end 
	as debe, 
	case tipo_saldo
		when 'D' then 0
		when 'A' then saldo_inicial
		else 0 end 
	as haber, 
	0 as parcial, 
    0 as eliminado
	from cuenta_balance cb 
	inner join cuenta c on cb.id_cuenta = c.id
UNION
select 
	pdi.id_partida, p.id_ciclo, pdi.id_cuenta, pdi.debe, pdi.haber, pdi.parcial, pdi.eliminado
from partida_detalle pdi
inner join partida p on pdi.id_partida = p.id;      
                                    """);
            return fileName;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    private String fechaAFormatoPreferido(LocalDateTime fechaHora) {
        //2024-02-16-1657
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm");
        return fechaHora.format(formatter); // "1986-04-08 12:30"
    }

    private void ejecutarSQL(Connection connection, String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    private Connection conectar(String fileName) {
        Connection cx = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + fileName;
            cx = DriverManager.getConnection(url);
            System.out.println("CONECTADO!");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return cx;
    }

    private void desconectar(Connection connection) {
        try {
            connection.close();
            System.out.println("Desconectado!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String leerConfiguracionInicial() throws FileNotFoundException, IOException {
        // File path is passed as parameter
        File file = new File(Constantes.RUTA_ARCHIVO_SQL_INICIAL);
        try (
                // Note:  Double backquote is to avoid compiler
                // jecu words
                // like \test as \t (ie. as a escape sequence)
                // Creating an object of BufferedReader class
                BufferedReader br = new BufferedReader(new FileReader(file));) {
            // Declaring a string variable
            StringBuilder sb = new StringBuilder();
            // Condition holds true till
            // there is character in a string
            String st;
            while ((st = br.readLine()) != null) {
                // Print the string
                System.out.println(st);
                sb.append(st);
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
