package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.Objetos;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOObjeto {
    private static String statement;
    private static String query;

    // Insertar objeto
    public static String insertarObjeto(Objetos objeto, String propietarioId) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_objeto (nombre, descripcion, estado, fecha_compra, propietario_id) VALUES ('" +
                objeto.getNombre() + "', '" +
                objeto.getDescripcion() + "', '" +
                objeto.getEstado() + "', '" +
                Date.valueOf(objeto.getFechaCompra()) + "', '" +
                propietarioId + "');";
        Conector.getConexion().ejecutarStatement(statement);
        return "Objeto registrado exitosamente.";
    }

    // Seleccionar objeto por ID
    public static Objetos seleccionarObjeto(int id) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_objeto WHERE id = " + id + ";";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) {
            return null;
        }
        return new Objetos(
                resultado.getString("nombre"),
                resultado.getString("descripcion"),
                resultado.getString("estado"),
                resultado.getDate("fecha_compra").toLocalDate()
        );
    }

    // Listar objetos de un propietario
    public static void leerObjetosPorPropietario(String propietarioId) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_objeto WHERE propietario_id = '" + propietarioId + "';";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) {
            System.out.println("No tiene objetos registrados.");
            return;
        }
        do {
            System.out.println("ID: " + resultado.getInt("id"));
            System.out.println("Nombre: " + resultado.getString("nombre"));
            System.out.println("Estado: " + resultado.getString("estado"));
        } while (resultado.next());
    }

    // Listar objetos en plataforma
    public static void listarObjetosEnPlataforma() throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT o.id, o.nombre, o.descripcion, o.estado, o.fecha_compra, " +
                "u.nombre_completo as propietario " +
                "FROM t_objeto o " +
                "JOIN t_usuario u ON o.propietario_id = u.identificacion " +
                "ORDER BY o.nombre;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) {
            System.out.println("No hay objetos registrados en la plataforma.");
            return;
        }
        do {
            System.out.println("\nID: " + resultado.getInt("id"));
            System.out.println("Nombre: " + resultado.getString("nombre"));
            System.out.println("Descripción: " + resultado.getString("descripcion"));
            System.out.println("Estado: " + resultado.getString("estado"));
            System.out.println("Propietario: " + resultado.getString("propietario"));
        } while (resultado.next());
    }

    // Insertar objeto y retornar el ID generado
    public static int insertarObjetoYObtenerId(Objetos objeto, String propietarioId)
            throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_objeto (nombre, descripcion, estado, fecha_compra, propietario_id) VALUES ('" +
                objeto.getNombre() + "', '" +
                objeto.getDescripcion() + "', '" +
                objeto.getEstado() + "', '" +
                Date.valueOf(objeto.getFechaCompra()) + "', '" +
                propietarioId + "');";
        Conector.getConexion().ejecutarStatement(statement);

        query = "SELECT LAST_INSERT_ID() as id;";
        ResultSet rs = Conector.getConexion().ejecutarQuery(query);
        if (rs.next()) {
            return rs.getInt("id");
        }
        return -1;
    }
}