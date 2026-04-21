package cr.ac.ucenfotec.dl;

import java.sql.*;

public class AccesoBD {

    private Connection conexion = null;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public AccesoBD(String direccion, String usuario, String contrasenia) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection(direccion, usuario, contrasenia);
    }

    // Para INSERT, UPDATE, DELETE sin parámetros
    public void ejecutarStatement(String statement) throws SQLException {
        this.statement = conexion.createStatement();
        this.statement.executeUpdate(statement);
    }

    // Para UPDATE/DELETE con 2 parámetros String
    public void ejecutarStatement(String statement, String s1, String s2) throws SQLException {
        preparedStatement = conexion.prepareStatement(statement);
        preparedStatement.setString(1, s1);
        preparedStatement.setString(2, s2);
        preparedStatement.executeUpdate();
    }

    // Para INSERT/UPDATE con 3 parámetros String
    public void ejecutarStatement(String statement, String s1, String s2, String s3) throws SQLException {
        preparedStatement = conexion.prepareStatement(statement);
        preparedStatement.setString(1, s1);
        preparedStatement.setString(2, s2);
        preparedStatement.setString(3, s3);
        preparedStatement.executeUpdate();
    }

    // Para SELECT con 1 parámetro
    public ResultSet ejecutarQuery(String query, String s1) throws SQLException {
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setString(1, s1);
        return preparedStatement.executeQuery();
    }

    // Para SELECT con 2 parámetros
    public ResultSet ejecutarQuery(String query, String s1, String s2) throws SQLException {
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setString(1, s1);
        preparedStatement.setString(2, s2);
        return preparedStatement.executeQuery();
    }

    // Para SELECT sin parámetros
    public ResultSet ejecutarQuery(String query) throws SQLException {
        preparedStatement = conexion.prepareStatement(query);
        return preparedStatement.executeQuery();
    }
}