package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.Moderador;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOModerador {
    private static String statement;
    private static String query;

    public static String insertarModerador(Moderador moderador) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_usuario (identificacion, nombre_completo, fecha_nacimiento, contrasena, correo_electronico, tipo_usuario, puntuacion, direccion) VALUES ('" +
                moderador.getIdentificacion() + "', '" +
                moderador.getNombreCompleto() + "', '" +
                Date.valueOf(moderador.getFechaNacimiento()) + "', '" +
                moderador.getContrasena() + "', '" +
                moderador.getCorreoElectronico() + "', 'MODERADOR', 0, NULL);";
        Conector.getConexion().ejecutarStatement(statement);
        return "Moderador registrado exitosamente. Identificacion: " + moderador.getIdentificacion();
    }

    public static boolean existeModerador() throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT COUNT(*) FROM t_usuario WHERE tipo_usuario = 'MODERADOR';";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        resultado.next();
        return resultado.getInt(1) > 0;
    }

    public static void leerModerador() throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_usuario WHERE tipo_usuario = 'MODERADOR';";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) {
            System.out.println("No hay moderador registrado.");
            return;
        }
        do {
            System.out.println("\nIdentificacion: " + resultado.getString("identificacion"));
            System.out.println("Nombre: " + resultado.getString("nombre_completo"));
            System.out.println("Fecha nacimiento: " + resultado.getDate("fecha_nacimiento"));
            System.out.println("Correo electronico: " + resultado.getString("correo_electronico"));
        } while (resultado.next());
    }
}