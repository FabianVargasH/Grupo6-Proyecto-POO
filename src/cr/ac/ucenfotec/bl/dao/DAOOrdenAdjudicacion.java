package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DAOOrdenAdjudicacion {
    private static String statement;
    private static String query;

    // Insertar orden
    public static String insertarOrden(int idSubasta, String ganadorId, double precioTotal) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_orden_adjudicacion (subasta_id, ganador_id, fecha_orden, precio_total) VALUES (" +
                idSubasta + ", '" +
                ganadorId + "', '" +
                Date.valueOf(LocalDate.now()) + "', " +
                precioTotal + ");";
        Conector.getConexion().ejecutarStatement(statement);
        return "Orden de adjudicacion creada exitosamente.";
    }

    // Listar ordenes
    public static void leerOrdenes() throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_orden_adjudicacion;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) {
            System.out.println("No hay ordenes registradas.");
            return;
        }
        do {
            System.out.println("ID: " + resultado.getInt("id"));
            System.out.println("Subasta ID: " + resultado.getInt("subasta_id"));
            System.out.println("Ganador ID: " + resultado.getString("ganador_id"));
            System.out.println("Fecha: " + resultado.getDate("fecha_orden"));
            System.out.println("Total: " + resultado.getDouble("precio_total"));
        } while (resultado.next());
    }

    public static String relacionarObjetoOrden(int idOrden, int idObjeto) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_orden_objeto (orden_id, objeto_id) VALUES (" +
                idOrden + ", " + idObjeto + ");";
        Conector.getConexion().ejecutarStatement(statement);
        return "Objeto relacionado a la orden.";
    }
}