package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOOferta;
import cr.ac.ucenfotec.bl.entities.*;

import java.io.IOException;
import java.sql.SQLException;

public class GestorOferta {

    // Las validaciones (solo coleccionistas, no ofertar en propia subasta,
    // subasta no adjudicada) las manejan los triggers de la BD.
    public static String registrarOferta(int idSubasta, String oferenteId, double precioOfertado)
            throws SQLException, IOException, ClassNotFoundException {
        return DAOOferta.insertarOferta(idSubasta, oferenteId, precioOfertado);
    }

    public static void listarOfertasPorSubasta(int idSubasta)
            throws SQLException, IOException, ClassNotFoundException {
        DAOOferta.leerOfertasPorSubasta(idSubasta);
    }
}
