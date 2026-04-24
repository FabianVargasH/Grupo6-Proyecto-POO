package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOOferta;

import java.io.IOException;
import java.sql.SQLException;

public class GestorOferta {

    public static String registrarOferta(int idSubasta, String oferenteId, double precioOfertado)
            throws SQLException, IOException, ClassNotFoundException {
        return DAOOferta.insertarOferta(idSubasta, oferenteId, precioOfertado);
    }

    public static void listarOfertasPorSubasta(int idSubasta)
            throws SQLException, IOException, ClassNotFoundException {
        DAOOferta.leerPorSubasta(idSubasta);
    }
}
