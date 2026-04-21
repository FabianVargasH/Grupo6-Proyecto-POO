package cr.ac.ucenfotec;
import java.util.ArrayList;

import cr.ac.ucenfotec.bl.entities.OrdenAdjudicacion;
import cr.ac.ucenfotec.bl.entities.Subasta;
import cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.ui.Menu;

public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Subasta> subastas = new ArrayList<>();
        ArrayList<OrdenAdjudicacion> ordenes = new ArrayList<>();
        Menu menu = new Menu();
        menu.mostrarMenu(usuarios, subastas,ordenes);
    }
}