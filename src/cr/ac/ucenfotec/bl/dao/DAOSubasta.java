package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DAOSubasta {
    private static String statement;
    private static String query;

    // Insertar subasta
    public static String insertarSubasta(String creadorId, double precioMinimo, LocalDate fechaCreacion, LocalDate fechaCierre, String estado) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_subasta (creador_id, precio_minimo, fecha_creacion, fecha_cierre, estado) VALUES ('" +
                creadorId.trim() + "', " +
                precioMinimo + ", '" +
                Date.valueOf(fechaCreacion) + "', '" +
                Date.valueOf(fechaCierre) + "', '" +
                estado + "');";
        Conector.getConexion().ejecutarStatement(statement);
        return "Subasta creada exitosamente.";
    }

    // Listar subastas
    public static void leerSubastas() throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_subasta;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) {
            System.out.println("No hay subastas registradas.");
            return;
        }
        do {
            System.out.println("\nID: " + resultado.getInt("id"));
            System.out.println("Creador ID: " + resultado.getString("creador_id"));
            System.out.println("Precio minimo: " + resultado.getDouble("precio_minimo"));
            System.out.println("Fecha creacion: " + resultado.getDate("fecha_creacion"));
            System.out.println("Fecha cierre: " + resultado.getDate("fecha_cierre"));
            System.out.println("Estado: " + resultado.getString("estado"));
        } while (resultado.next());
    }

    // Actualizar estado de subasta
    public static String actualizarEstado(int idSubasta, String nuevoEstado) throws SQLException, IOException, ClassNotFoundException {
        statement = "UPDATE t_subasta SET estado = '" + nuevoEstado + "' WHERE id = " + idSubasta + ";";
        Conector.getConexion().ejecutarStatement(statement);
        return "Estado actualizado a: " + nuevoEstado;
    }

    public static String relacionarObjetoSubasta(int idSubasta, int idObjeto)
            throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_subasta_objeto (subasta_id, objeto_id) VALUES (" +
                idSubasta + ", " + idObjeto + ");";
        Conector.getConexion().ejecutarStatement(statement);
        return "Objeto relacionado a la subasta.";
    }

    // Insertar subasta y retornar el ID generado
    public static int insertarSubastaYObtenerId(String creadorId, double precioMinimo,
                                                LocalDate fechaCreacion, LocalDate fechaCierre,
                                                String estado)
            throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_subasta (creador_id, precio_minimo, fecha_creacion, fecha_cierre, estado) VALUES ('" +
                creadorId.trim() + "', " +
                precioMinimo + ", '" +
                Date.valueOf(fechaCreacion) + "', '" +
                Date.valueOf(fechaCierre) + "', '" +
                estado + "');";
        Conector.getConexion().ejecutarStatement(statement);

        query = "SELECT LAST_INSERT_ID() as id;";
        ResultSet rs = Conector.getConexion().ejecutarQuery(query);
        if (rs.next()) {
            return rs.getInt("id");
        }
        return -1;
    }
}