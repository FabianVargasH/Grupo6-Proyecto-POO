package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOOferta {
    private static String statement;
    private static String query;

    // Insertar oferta
    public static String insertarOferta(int idSubasta, String oferenteId, double precioOfertado) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_oferta (subasta_id, oferente_id, precio_ofertado) VALUES (" +
                idSubasta + ", '" +
                oferenteId + "', " +
                precioOfertado + ");";
        Conector.getConexion().ejecutarStatement(statement);
        return "Oferta registrada exitosamente.";
    }

    // Listar ofertas por subasta
    public static void leerOfertasPorSubasta(int idSubasta) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_oferta WHERE subasta_id = " + idSubasta + ";";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) {
            System.out.println("No hay ofertas registradas.");
            return;
        }
        do {
            System.out.println("\nID: " + resultado.getInt("id"));
            System.out.println("Oferente ID: " + resultado.getString("oferente_id"));
            System.out.println("Precio: " + resultado.getDouble("precio_ofertado"));
            System.out.println("Fecha: " + resultado.getTimestamp("fecha_oferta"));
        } while (resultado.next());
    }

    public static double obtenerMejorOferta(int idSubasta) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT MAX(precio_ofertado) as mejor FROM t_oferta WHERE subasta_id = " + idSubasta + ";";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (resultado.next()) {
            return resultado.getDouble("mejor");
        }
        return 0;
    }

    public static String obtenerGanadorSubasta(int idSubasta) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT oferente_id FROM t_oferta WHERE subasta_id = " + idSubasta + " ORDER BY precio_ofertado DESC LIMIT 1;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (resultado.next()) {
            return resultado.getString("oferente_id");
        }
        return null;
    }
}