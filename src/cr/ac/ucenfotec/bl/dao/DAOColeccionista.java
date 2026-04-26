package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.Coleccionista;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOColeccionista {
    private static String statement;
    private static String query;

    // Insertar coleccionista
    public static String insertarColeccionista(Coleccionista coleccionista) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_usuario (identificacion, nombre_completo, fecha_nacimiento, contrasena, correo_electronico, tipo_usuario, puntuacion, direccion) VALUES ('" +
                coleccionista.getIdentificacion() + "', '" +
                coleccionista.getNombreCompleto() + "', '" +
                Date.valueOf(coleccionista.getFechaNacimiento()) + "', '" +
                coleccionista.getContrasena() + "', '" +
                coleccionista.getCorreoElectronico() + "', 'COLECCIONISTA', " +
                coleccionista.getPuntuacion() + ", '" +
                coleccionista.getDireccion() + "');";
        Conector.getConexion().ejecutarStatement(statement);
        return "Coleccionista registrado exitosamente. Identificacion: " + coleccionista.getIdentificacion();
    }

    // Seleccionar coleccionista por identificacion
    public static Coleccionista seleccionarColeccionista(String identificacion) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_usuario WHERE identificacion = ? AND tipo_usuario = 'COLECCIONISTA';";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, identificacion);
        if (!resultado.next()) {
            return null;
        }
        return new Coleccionista(
                resultado.getString("nombre_completo"),
                resultado.getString("identificacion"),
                resultado.getDate("fecha_nacimiento").toLocalDate(),
                resultado.getString("contrasena"),
                resultado.getString("correo_electronico"),
                resultado.getDouble("puntuacion"),
                resultado.getString("direccion")
        );
    }

    // Insertar interes de un coleccionista
    public static String insertarInteres(String identificacion, String interes) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_interes (usuario_id, interes) VALUES ('" + identificacion + "', '" + interes + "');";
        Conector.getConexion().ejecutarStatement(statement);
        return "Interes agregado exitosamente: " + interes;
    }

    // Leer todos los coleccionistas
    public static void leerColeccionistas() throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_usuario WHERE tipo_usuario = 'COLECCIONISTA';";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) {
            System.out.println("No hay coleccionistas registrados.");
            return;
        }
        do {
            System.out.println("\nIdentificacion: " + resultado.getString("identificacion"));
            System.out.println("Nombre: " + resultado.getString("nombre_completo"));
            System.out.println("Fecha nacimiento: " + resultado.getDate("fecha_nacimiento"));
            System.out.println("Correo electronico: " + resultado.getString("correo_electronico"));
            System.out.println("Puntuacion: " + resultado.getDouble("puntuacion"));
            System.out.println("Direccion: " + resultado.getString("direccion"));

            // Mostrar intereses del coleccionista
            String queryIntereses = "SELECT interes FROM t_interes WHERE usuario_id = '" + resultado.getString("identificacion") + "';";
            ResultSet rsIntereses = Conector.getConexion().ejecutarQuery(queryIntereses);
            System.out.print("Intereses: ");
            boolean hayIntereses = false;
            while (rsIntereses.next()) {
                if (hayIntereses) {
                    System.out.print(", ");
                }
                System.out.print(rsIntereses.getString("interes"));
                hayIntereses = true;
            }
            if (!hayIntereses) {
                System.out.print("Ninguno");
            }
            System.out.println();
        } while (resultado.next());
    }

    public static boolean existeColeccionista(String identificacion) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT COUNT(*) FROM t_usuario WHERE identificacion = ? AND tipo_usuario = 'COLECCIONISTA'";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, identificacion);
        resultado.next();
        return resultado.getInt(1) > 0;
    }
}