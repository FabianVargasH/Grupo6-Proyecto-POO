package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOObjeto;
import cr.ac.ucenfotec.bl.dao.DAOOrdenAdjudicacion;
import cr.ac.ucenfotec.bl.dao.DAOSubasta;
import cr.ac.ucenfotec.bl.entities.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GestorOrdenAdjudicacion {

    public static String adjudicarSubasta(int idSubasta, String ganadorId, double precioTotal) throws SQLException, IOException, ClassNotFoundException {
        return DAOOrdenAdjudicacion.insertarOrden(idSubasta, ganadorId, precioTotal);
    }
}
