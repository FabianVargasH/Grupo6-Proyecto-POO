package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.Vendedor;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOVendedor {
    private static String statement;
    private static String query;

    // Insertar vendedor
    public static String insertarVendedor(Vendedor vendedor) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_usuario (identificacion, nombre_completo, fecha_nacimiento, contrasena, correo_electronico, tipo_usuario, puntuacion, direccion) VALUES ('" +
                vendedor.getIdentificacion() + "', '" +
                vendedor.getNombreCompleto() + "', '" +
                Date.valueOf(vendedor.getFechaNacimiento()) + "', '" +
                vendedor.getContrasena() + "', '" +
                vendedor.getCorreoElectronico() + "', 'VENDEDOR', " +
                vendedor.getPuntuacion() + ", '" +
                vendedor.getDireccion() + "');";
        Conector.getConexion().ejecutarStatement(statement);
        return "Vendedor registrado exitosamente. Identificacion: " + vendedor.getIdentificacion();
    }

    // Seleccionar vendedor por identificacion
    public static Vendedor seleccionarVendedor(String identificacion) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_usuario WHERE identificacion = ? AND tipo_usuario = 'VENDEDOR';";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, identificacion);
        if (!resultado.next()) {
            return null;
        }
        return new Vendedor(
                resultado.getString("nombre_completo"),
                resultado.getString("identificacion"),
                resultado.getDate("fecha_nacimiento").toLocalDate(),
                resultado.getString("contrasena"),
                resultado.getString("correo_electronico"),
                resultado.getDouble("puntuacion"),
                resultado.getString("direccion")
        );
    }

    // Leer todos los vendedores
    public static void leerVendedores() throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_usuario WHERE tipo_usuario = 'VENDEDOR';";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) {
            System.out.println("No hay vendedores registrados.");
            return;
        }
        do {
            System.out.println("\nIdentificacion: " + resultado.getString("identificacion"));
            System.out.println("Nombre: " + resultado.getString("nombre_completo"));
            System.out.println("Fecha nacimiento: " + resultado.getDate("fecha_nacimiento"));
            System.out.println("Correo electronico: " + resultado.getString("correo_electronico"));
            System.out.println("Puntuacion: " + resultado.getDouble("puntuacion"));
            System.out.println("Direccion: " + resultado.getString("direccion"));
        } while (resultado.next());
    }

    public static boolean existeVendedor(String identificacion) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT COUNT(*) FROM t_usuario WHERE identificacion = ?";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, identificacion);
        resultado.next();
        return resultado.getInt(1) > 0;
    }
}